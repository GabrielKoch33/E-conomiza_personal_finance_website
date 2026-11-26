# Arquitetura em camadas — guia de consulta do E-conomiza

*Guia teórico e prático para consultar enquanto você codifica o CLI e, depois, a API.*

---

## 1. Por que separar em camadas

Se tudo morar no mesmo lugar (ler do teclado, validar, calcular saldo, escrever SQL, imprimir na tela), três problemas aparecem rápido:

- **Mudar uma coisa quebra outra.** Trocar o terminal por uma API REST obrigaria a reescrever o sistema inteiro.
- **Impossível testar.** Para testar a regra "saldo não pode ficar negativo" você teria que subir o banco e digitar no console.
- **Regras duplicadas.** A mesma regra copiada em vários lugares, e cada cópia evolui de um jeito.

A arquitetura em camadas resolve isso com uma ideia só: **cada parte do código tem um tipo de responsabilidade, e só conversa com a vizinha certa**. Isso é *separação de responsabilidades*. Os ganhos são baixo acoplamento (uma camada pouco sabe da outra), alta coesão (cada classe faz uma coisa) e testabilidade.

No E-conomiza isso tem um motivo extra: o CLI atual é a **base** da API. Se as camadas de baixo estiverem limpas, trocar o terminal por controllers HTTP vai ser trocar só a camada de cima.

---

## 2. O mapa das camadas

```text
┌───────────────────────────────────────────┐
│ APRESENTAÇÃO   CLI hoje / Controller depois│  fala com o usuário
└──────────────────────┬────────────────────┘
                       │ chama
┌──────────────────────▼────────────────────┐
│ SERVICE        regras de negócio           │  decide o que pode e o que acontece
└──────────────────────┬────────────────────┘
                       │ chama (via interface)
┌──────────────────────▼────────────────────┐
│ REPOSITORY     acesso a dados (SQL/JDBC)   │  lê e grava
└──────────────────────┬────────────────────┘
                       │
┌──────────────────────▼────────────────────┐
│ BANCO          PostgreSQL                  │
└───────────────────────────────────────────┘

  MODEL (e exceptions) são usados por todas as camadas,
  mas não dependem de nenhuma delas.
```

### A regra de ouro: a dependência só desce

- A apresentação conhece o service. O service conhece o repository. O repository conhece o banco.
- **Nunca o contrário.** O repository não chama service. O service não conhece `Scanner`, `System.out` nem HTTP.
- O model é o centro: não importa nada das camadas.

Se você precisar fazer uma camada de baixo "chamar para cima", é sinal de que alguma responsabilidade está no lugar errado.

---

## 3. Responsabilidades: o que cada camada faz e não faz

| Camada | Faz | Não faz |
| --- | --- | --- |
| **Model** | Representa o domínio. Garante que objeto inválido não existe (validações no construtor). | Não conhece SQL, console nem outras camadas. |
| **Repository** | Executa SQL. Converte linha do banco em objeto e objeto em parâmetros SQL. Traduz `SQLException` em `DataAccessException`. | Não decide regra de negócio. Não imprime. Não valida "pode ou não pode". |
| **Service** | Aplica as regras de negócio. Orquestra vários repositories. Controla a transação de banco. Valida propriedade dos dados. | Não escreve SQL. Não lê teclado nem imprime. |
| **Apresentação** | Lê a entrada, converte em objetos/valores, chama o service, mostra o resultado ou o erro. | Não calcula saldo, não aplica regra, não toca no banco. |
| **Database (ConnectionFactory)** | Entrega conexões. | Nada de negócio. |

### Um teste rápido para saber onde o código mora

Pergunte: **"se eu trocar o terminal por uma API, esse código muda?"**

- Muda → é apresentação.
- Não muda, mas depende do banco usado → é repository.
- Não muda e é uma regra do negócio → é service ou model.

---

## 4. Onde cada regra do E-conomiza deve morar

A mesma regra pode (e deve) ser protegida em mais de um nível, cada um com um papel. Isso se chama **defesa em profundidade**.

| Regra | Model | Service | Banco |
| --- | --- | --- | --- |
| Valor da transação > 0 | ✅ construtor |  | ✅ `CHECK` |
| Data não pode ser futura | ✅ construtor |  |  |
| Entrada/despesa exige categoria | ✅ construtor |  | ✅ `NOT NULL` na filha |
| Saldo disponível nunca negativo |  | ✅ consulta o saldo antes |  |
| Usuário só mexe nos próprios dados |  | ✅ compara o dono com o usuário logado |  |
| Ao excluir categoria, mover transações para SEM CATEGORIA |  | ✅ | `RESTRICT` impede esquecer |
| Filha de transação do tipo certo |  |  | ✅ FK composta + `CHECK` |
| Pai e filha gravados juntos |  | ✅ transação de banco |  |
| Depósito automático ao registrar despesa |  | ✅ |  |
| Excluir cofrinho devolve saldo |  |  | ✅ `CASCADE` + saldo derivado |

**Como ler a tabela:** o model protege o objeto isolado ("este valor é válido"). O service protege o *contexto* ("este usuário pode fazer isso agora, com este saldo"). O banco protege a integridade de última instância, mesmo que o código tenha um bug.

> Validar no model não substitui a regra de negócio, e a regra de negócio não substitui a constraint do banco. Cada uma pega um tipo diferente de erro.

---

## 5. O caminho de uma operação (exemplo: registrar uma despesa)

1. **Apresentação:** lê valor, categoria, meio de pagamento e data. Monta os dados e chama `transacaoService.registrarDespesa(...)`.
2. **Service:** confirma que a categoria é padrão ou do usuário (propriedade).
3. **Service:** consulta o saldo disponível. Se a despesa o deixaria negativo, lança uma exceção de negócio.
4. **Service:** cria o objeto `TransacaoMovimentacao`. O construtor do model valida valor, data e categoria.
5. **Service:** abre a transação de banco.
6. **Repository:** insere em `tb_transacao` (pai), recebe o id gerado e insere em `tb_transacao_movimentacao` (filha).
7. **Service:** calcula e grava os depósitos automáticos possíveis, dentro da mesma transação.
8. **Service:** `commit`. Se algo falhou em qualquer passo, `rollback`: nada fica pela metade.
9. **Apresentação:** mostra sucesso. Se uma exceção chegou, mostra a mensagem.

Repare que o SQL só aparece no passo 6 e que o `System.out` só aparece nos extremos.

---

## 6. Repository na prática

### 6.1 Interface e implementação

O service deve depender de uma **interface** (`IUsuarioRepository`, `ICofrinhoRepository`), não da classe concreta que usa JDBC. Motivos:

- O contrato fica explícito: a interface lista *o que* se pode pedir ao repository.
- Dá para trocar a implementação (JDBC puro hoje, Spring Data JPA depois) sem mexer no service.
- Dá para testar o service com uma implementação falsa, sem banco.

Uma interface genérica como o seu `ICrudRepository` guarda o que é comum (salvar, buscar por id, listar, atualizar, excluir). As interfaces específicas acrescentam o que é próprio daquele domínio (por exemplo, buscar usuário por `username`).

### 6.2 Anatomia de um método de repository

Todo método JDBC segue o mesmo roteiro:

1. Obter a `Connection`.
2. Preparar o SQL com `PreparedStatement` e `?` nos parâmetros.
3. Preencher os parâmetros a partir do objeto.
4. Executar.
5. Se for consulta, converter cada linha do `ResultSet` em objeto de model.
6. Fechar tudo (`try-with-resources`).
7. Converter `SQLException` em `DataAccessException`.

Regras de ouro:

- **Nunca concatene valores no SQL.** Sempre `PreparedStatement` com parâmetros. Concatenar abre a porta para SQL injection e para erros de formatação.
- **Use `try-with-resources`** para `Connection`, `PreparedStatement` e `ResultSet`.
- **Consulta por id devolve `Optional`**, não `null`. Quem chama é obrigado a decidir o que fazer se não existir.
- **Não engula exceções.** `catch` vazio esconde o erro. Traduza e relance.
- **O repository não valida regra de negócio.** Se a linha existe, ele devolve; quem decide o que fazer com ela é o service.

### 6.3 O mapeamento linha → objeto

O model valida no construtor. Ao reconstruir um objeto a partir do banco, o construtor valida de novo. Isso é bom, mas tem uma consequência: **dados antigos que violem as regras atuais** (por exemplo, uma data de vencimento que já passou) lançariam exceção ao serem lidos. Mantenha isso em mente quando for escrever as validações de data.

### 6.4 Hierarquias de transação

Para salvar uma `TransacaoMovimentacao`, o repository precisa gravar **duas** linhas: pai (`tb_transacao`) e filha. Para um investimento de renda fixa, **três** (pai, `tb_transacao_investimento` e `tb_investimento_renda_fixa`). Pontos de atenção:

- O id do pai só existe depois do `INSERT`, então a ordem é sempre pai → filha → neta.
- No PostgreSQL, `INSERT ... RETURNING id_transacao` ou `getGeneratedKeys()` devolve o id gerado.
- Pense em como o objeto Java recebe esse id (ele é construído antes de saber o id). As saídas comuns são devolver um objeto novo já com o id ou ter um método para atribuí-lo uma única vez.
- A leitura faz o caminho inverso: decide qual subtipo instanciar a partir de `tipo_transacao` (e `tipo_investimento`).

---

## 7. Transações de banco: quem controla o commit

Uma operação de negócio pode envolver vários repositories e vários `INSERT`s (pai, filha, depósitos automáticos). Ou tudo acontece, ou nada acontece. Isso é uma **transação** (propriedade de atomicidade).

O ponto central: **quem conhece a operação inteira é o service**, então é ele quem decide quando começa e quando termina a transação. O repository executa comandos dentro da transação que o service abriu.

Para isso, todos os comandos precisam usar a **mesma `Connection`**. No JDBC puro, as formas mais comuns são:

| Abordagem | Como funciona | Prós / contras |
| --- | --- | --- |
| Passar a `Connection` como parâmetro | O service abre a conexão e a entrega a cada método do repository. | Simples e explícita. Polui as assinaturas. |
| Repository com conexão própria por método | Cada método abre e fecha a sua conexão. | Fácil, mas **não permite** transação cobrindo vários métodos. |
| Gerenciador de transação | Uma classe guarda a conexão da operação em andamento. | Assinaturas limpas, mais código de infraestrutura. |

No Spring isso vira `@Transactional` no service, e o framework cuida da conexão. Entender o mecanismo manual primeiro é justamente o que torna essa anotação compreensível depois.

A escolha entre as três é uma **decisão de design sua**, e vale pensar nela antes de escrever os repositories de transação, porque muda a assinatura de todos os métodos.

---

## 8. Injeção de dependência (por construtor)

O service não deve criar seus repositories com `new` dentro dele. Ele **recebe** o que precisa:

```java
public class CofrinhoService {
    private final ICofrinhoRepository cofrinhoRepository;

    public CofrinhoService(ICofrinhoRepository cofrinhoRepository) {
        this.cofrinhoRepository = cofrinhoRepository;
    }
}
```

- A classe declara o que precisa e depende da interface.
- Quem monta o sistema (hoje o `Main`, depois o Spring) escolhe a implementação concreta e a entrega.
- Isso é a base da *inversão de dependência*. No Spring você só anota as classes e o framework faz esse "encaixe" por você.

---

## 9. Model, DTO e exceptions

- **Model:** representa o conceito de domínio e garante suas invariantes. É o que você já construiu.
- **DTO:** objeto que define o formato de dados que **entra e sai** da fronteira do sistema. No CLI você pode passar valores simples. Na API, o DTO passa a ser importante: ele evita expor o model inteiro (por exemplo, a senha do usuário) e evita que o cliente defina campos que não deveria.
- **Exceptions:** sua hierarquia (`EconomizaException`, uma por domínio, `DataAccessException`) está bem pensada. Duas orientações:
  - Exceção de **negócio** (saldo insuficiente, categoria inválida) nasce no model ou no service e vai até a apresentação.
  - Exceção de **infraestrutura** (`DataAccessException`) nasce no repository. A apresentação mostra uma mensagem genérica e o detalhe técnico vai para log, não para o usuário.

---

## 10. Organização de pacotes e a migração para Spring

### Hoje (CLI)

```text
com.gabriel.prototype
├── database/        DBConnectionFactory
├── exceptions/      uma pasta por domínio
├── models/          + enums
├── repositories/    + interfaces
├── services/        (a criar)
└── (apresentação)   Main e menus do CLI (a criar)
```

### Como cada peça muda na API

| No CLI | Na API Spring Boot |
| --- | --- |
| Menus/`Main` (apresentação) | `@RestController` (controllers) |
| Services | Services com `@Service` e `@Transactional` (as regras permanecem) |
| Repositories JDBC | Interfaces do Spring Data JPA |
| `DBConnectionFactory` | `DataSource` configurado pelo Spring |
| Models com validação no construtor | Entidades JPA + DTOs de entrada/saída |
| Tratamento de exceção no menu | Tratamento global de exceções (`@RestControllerAdvice`) |

O documento do projeto prevê organização por funcionalidade (*package by feature*) no backend final. No protótipo, separar por camada (como está) é bom para aprender. Quando migrar, os módulos passam a agrupar controller, service, repository, model e dto de cada domínio (`categoria`, `cofrinho`, `transacao`...).

**O que vale mais no CLI é manter o service limpo.** Se ele não conhece console nem SQL, ele migra quase intacto.

---

## 11. Erros comuns (e como reconhecê-los)

| Sintoma | Problema | Correção |
| --- | --- | --- |
| `System.out`/`Scanner` dentro do service ou do repository | Apresentação vazou para baixo | O service devolve valor ou lança exceção; a apresentação imprime |
| `if (saldo < ...)` dentro do repository | Regra de negócio na camada de dados | Mover para o service |
| SQL ou `Connection` dentro do menu | Apresentação falando com o banco | Chamar o service |
| `new JdbcUsuarioRepository()` dentro do service | Acoplamento à implementação | Receber a interface por construtor |
| Repository devolvendo `null` | Ausência sem contrato claro | Devolver `Optional` |
| Um `FinanceiroService` que faz tudo | "Classe deus", baixa coesão | Um service por domínio (usuário, categoria, cofrinho, transação) |
| Vários `commit` em uma operação | Operação de negócio pela metade se falhar | Uma transação por operação, controlada pelo service |
| `catch (Exception e) {}` | Erro engolido | Traduzir e relançar, ou tratar de fato |

---

## 12. Roteiro para implementar uma funcionalidade

Use esta ordem para cada módulo novo (de baixo para cima, para sempre ter o que testar):

1. **Model** com validações e exceptions do domínio.
2. **Interface do repository** com os métodos que o negócio vai precisar.
3. **Implementação JDBC** do repository, testada isoladamente contra o banco.
4. **Service** com as regras de negócio, recebendo a interface por construtor.
5. **Apresentação** (menu do CLI) só no fim, chamando o service.

Antes de escrever o código, responda por escrito: *qual é a regra? em que camada ela mora? o que acontece se falhar?*

Sugestão de ordem dos módulos (a mesma do documento do projeto): usuários → categorias → transações → cofrinhos → depósitos automáticos → metas → investimentos → cálculos e relatórios.

---

## 13. Autoteste

Se você responder a estas perguntas sem consultar, a base está firme:

1. Por que o service depende de uma interface e não da classe JDBC?
2. Em qual camada fica a regra "o saldo não pode ficar negativo"? Por que não no model?
3. Quem decide o momento do `commit` ao registrar uma despesa com depósito automático, e por quê?
4. Se o banco rejeita uma filha de transação do tipo errado, ainda faz sentido o service validar isso? Por quê?
5. O que muda e o que continua igual quando o CLI virar uma API REST?
6. Por que consulta por id devolve `Optional`?
7. Por que nunca concatenar valores dentro do SQL?

---

## 14. Decisões que ficam com você

Anote a escolha de cada uma antes de escrever o código dependente:

- Como a transação de banco será controlada (seção 7).
- Como o objeto Java recebe o id gerado pelo banco (seção 6.4).
- Se `dataTransacao` será `LocalDate` ou `LocalDateTime`, alinhando model e coluna.
- Quais métodos cada interface de repository realmente precisa expor.
