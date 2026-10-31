# E-conomiza — Resumo de requisitos e modelagem conceitual

> Documento de referência para desenvolver o projeto. Contém regras de negócio e relações conceituais; não define SQL, tipos de dados, constraints ou implementação de framework.

## 1. Escopo do sistema

Aplicação web de finanças pessoais com:
- Usuários e dados privados por usuário.
- Registro de entradas e despesas.
- Cofrinhos com depósitos e retiradas.
- Metas associadas aos cofrinhos.
- Categorias padrão e personalizadas.
- Cadastro simples de ativos e quantidades.
- Relatórios financeiros e evolução histórica.

## 2. Entidades e relações

### 2.1 Usuário
Representa o proprietário dos dados.

Relações:
- Um usuário possui muitas transações.
- Um usuário possui muitos cofrinhos.
- Um usuário pode criar muitas categorias personalizadas.
- Um usuário possui muitos registros de ativos.
- O patrimônio do usuário é calculado a partir do saldo disponível e do total guardado nos cofrinhos.

### 2.2 Transação
Registra qualquer movimentação financeira. O tipo da operação descreve o que aconteceu.

Tipos:
- `ENTRADA`
- `DESPESA`
- `DEPOSITO`
- `RETIRADA`

Informações conceituais:
- Usuário proprietário.
- Tipo da operação.
- Valor.
- Data da movimentação.
- Descrição.
- Categoria, quando aplicável.
- Cofrinho, quando aplicável.

Regras:
- Entradas e despesas exigem categoria e não se associam a um cofrinho.
- Depósitos e retiradas exigem cofrinho e não possuem categoria.
- Todas as transações podem ser editadas ou excluídas livremente.
- Não são permitidas movimentações com data futura.
- O saldo não pode ficar negativo.
- Não haverá mecanismo de estorno nesta versão.
- Os valores financeiros são calculados a partir dos registros de transação.

### 2.3 Categoria
Representa a classificação de uma entrada ou despesa.

Tipos conceituais:
- **Padrão:** global, disponível a todos; usuários não podem criar, editar ou excluir categorias padrão.
- **Personalizada:** criada e administrada pelo usuário que a criou.

Regras:
- Entradas e despesas podem usar qualquer categoria autorizada, sem separação obrigatória por tipo.
- Usuários só podem usar categorias padrão ou categorias personalizadas próprias.
- Ao excluir uma categoria personalizada, as transações associadas devem passar a usar a categoria padrão `SEM CATEGORIA`; não se excluem essas transações.
- `SEM CATEGORIA` precisa existir como categoria padrão global.

### 2.4 Cofrinho
Representa dinheiro separado do saldo disponível do usuário.

Informações conceituais:
- Usuário proprietário.
- Nome e descrição.
- Data de criação.
- Configuração de depósito automático.
- Valor predefinido do depósito automático, quando habilitado.

Regras:
- Um usuário pode possuir muitos cofrinhos.
- O valor atual de cada cofrinho é calculado a partir dos depósitos e retiradas registrados para ele.
- Não é permitido transferir diretamente entre cofrinhos. O usuário deve retirar de um cofrinho e depois depositar no outro.
- Cada cofrinho pode ter sua própria configuração e valor de depósito automático.
- Depósitos e retiradas não usam categorias.
- A exclusão de cofrinho e o tratamento de suas transações estão sujeitos à confirmação indicada em “Pendências”.

### 2.5 Meta
Representa um objetivo financeiro associado a um cofrinho.

Relações e regras:
- Cada meta pertence obrigatoriamente a um cofrinho.
- Um cofrinho pode ter no máximo uma meta, mas não precisa ter uma.
- O usuário só pode criar uma meta para um cofrinho próprio que ainda não tenha meta.
- A meta pode ser considerada concluída, mas isso não impede novos depósitos.
- O valor-alvo da meta pode ser ampliado.

### 2.6 Ativo
Cadastro simples da posição atual de um usuário, sem histórico de compras e vendas.

Informações conceituais:
- Usuário proprietário/criador.
- Ticker definido pelo usuário.
- Quantidade atual.
- Preço médio de compra informado pelo usuário.

Regras:
- O objetivo é acompanhar a quantidade atual, não registrar o histórico de compras.
- Cada usuário mantém um registro por ativo/ticker; atualizações de posição alteram quantidade e preço médio conforme necessário.
- Não há, nesta etapa, necessidade de classificar ativos em renda fixa/renda variável, registrar cada operação ou calcular rentabilidade.

## 3. Regras de cálculo financeiro

### 3.1 Saldo disponível do usuário
`Saldo = entradas − despesas − depósitos + retiradas`

Considerar apenas transações válidas do usuário e os efeitos de seus tipos.

### 3.2 Total guardado nos cofrinhos
Para cada cofrinho:

`Saldo do cofrinho = depósitos do cofrinho − retiradas do cofrinho`

O total guardado é a soma dos saldos de todos os cofrinhos do usuário.

### 3.3 Patrimônio
`Patrimônio = saldo disponível + total guardado nos cofrinhos`

Consequências:
- Uma entrada aumenta o saldo e o patrimônio.
- Uma despesa diminui o saldo e o patrimônio.
- Um depósito diminui o saldo disponível e aumenta o valor guardado, sem alterar o patrimônio.
- Uma retirada aumenta o saldo disponível e diminui o valor guardado, sem alterar o patrimônio.

### 3.4 Fonte dos valores
Saldo, total em cofrinhos e patrimônio são derivados dos registros. A primeira versão não precisa manter esses valores como totais independentes persistidos.

## 4. Depósitos automáticos

Quando uma despesa externa é registrada:
1. Registrar a despesa, desde que a operação seja válida e o saldo não fique negativo.
2. Identificar todos os cofrinhos do usuário com depósito automático habilitado.
3. Considerar o valor automático configurado individualmente para cada cofrinho.
4. Distribuir os depósitos que couberem no saldo disponível após a despesa.
5. Um cofrinho que não puder receber o valor automático integral não recebe depósito nessa ocasião.
6. Não permitir que qualquer depósito automático torne o saldo negativo.

**Pendente:** definir a ordem/prioridade quando o saldo restante não comportar todos os depósitos automáticos. A ordem pode alterar quais cofrinhos recebem quando só alguns valores cabem.

## 5. Exclusão, edição e integridade funcional

- Transações: podem ser editadas ou excluídas livremente.
- Categorias personalizadas: ao excluir, substituir sua referência por `SEM CATEGORIA`, preservando as transações.
- Categorias padrão: não podem ser alteradas por usuários.
- Cofrinhos: há uma regra definida de que seu valor deve retornar ao saldo e de que suas transações serão apagadas, mas o modo exato de conciliar esses dois efeitos precisa ser confirmado.
- Não permitir saldo negativo em despesas, depósitos manuais ou automáticos.
- Validar no backend a propriedade dos registros: um usuário só pode consultar e modificar seus próprios dados, além das categorias padrão globais.

## 6. Relatórios e histórico

O sistema deverá permitir:
- Seleção de período personalizado.
- Análise de gastos e recebimentos por categoria.
- Comparação de gastos e recebimentos entre períodos.
- Evolução do saldo disponível.
- Evolução dos valores guardados nos cofrinhos.
- Evolução do patrimônio ao longo do tempo.

Depósitos entre saldo e cofrinhos são movimentações internas e não devem ser contabilizados como gastos de consumo.

## 7. Mapa conceitual resumido

```text
USUARIO
 ├── 1:N TRANSACAO
 │      ├── tipo: ENTRADA | DESPESA | DEPOSITO | RETIRADA
 │      ├── N:1 CATEGORIA (somente ENTRADA/DESPESA)
 │      └── N:1 COFRINHO (somente DEPOSITO/RETIRADA)
 ├── 1:N COFRINHO
 │      ├── 1:N TRANSACAO
 │      └── 0:1 META
 ├── 1:N CATEGORIA PERSONALIZADA
 └── 1:N ATIVO

CATEGORIA PADRAO: global e compartilhada
SEM CATEGORIA: categoria padrão usada após exclusão de categoria personalizada
META: pertence a exatamente um cofrinho; cofrinho pode ter zero ou uma meta
```

## 8. Pendências não relacionadas a SQL

1. **Prioridade dos depósitos automáticos:** quando o saldo não comportar todos, em qual ordem avaliar os cofrinhos? Por exemplo, ordem de criação, prioridade escolhida pelo usuário ou outra regra.
2. **Exclusão de cofrinho:** confirmar se a regra final é apagar o cofrinho e suas transações e deixar que os cálculos derivados reflitam o saldo liberado, sem criar uma `RETIRADA` adicional. Apagar as transações e registrar também uma retirada pode contar o valor duas vezes.
3. **Ativo por ticker:** confirmar se “um por ativo” significa um único registro por ticker e usuário, com a quantidade total atualizada, e não um registro separado para cada compra ou fração adquirida. O documento adota a interpretação de registro único por ticker/ativo e quantidade acumulada.

## 9. Fora do escopo desta etapa

- Tipos de dados, constraints e sintaxe SQL.
- Implementação específica de backend/frontend.
- Histórico individual de compras e vendas de ativos.
- Cálculo de rentabilidade de investimentos.
- Estornos de transações.