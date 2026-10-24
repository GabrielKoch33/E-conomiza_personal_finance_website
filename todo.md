# E-conomiza — Pendências da Modelagem do Banco de Dados

## 1. Estrutura das movimentações financeiras

- [ ] Decidir se `tbmovimentacao` será a tabela central para registrar todas as operações financeiras, substituindo `tbtransacao`.
- [ ] Definir quais tipos de operação serão suportados: entradas, saídas, depósitos em cofrinhos, retiradas e possíveis transferências futuras.
- [ ] Diferenciar a direção do fluxo financeiro da natureza da operação, para que depósitos em cofrinhos não sejam contabilizados como despesas de consumo.
- [ ] Decidir como representar depósitos e retiradas: uma movimentação que descreve toda a operação ou registros separados e relacionados.
- [ ] Definir os campos necessários para identificar a origem e o destino do dinheiro, quando aplicável.
- [ ] Definir quais operações devem aparecer nos relatórios de receitas, despesas e saldo.

## 2. Cálculo e consistência dos saldos

- [ ] Decidir se o saldo disponível do usuário será calculado pelo histórico de movimentações ou armazenado e atualizado.
- [ ] Decidir se `tbcofrinho.valor_atual` será armazenado ou calculado a partir do histórico.
- [ ] Definir as fórmulas para calcular o saldo disponível, o saldo de cada cofrinho e o patrimônio total do usuário.
- [ ] Garantir que depósitos e retiradas alterem a distribuição do dinheiro sem duplicar ou modificar indevidamente o patrimônio total.
- [ ] Definir como tratar a edição e a exclusão de movimentações que já afetaram os saldos.
- [ ] Definir como tratar estornos, correções e possíveis inconsistências entre o histórico e os saldos armazenados.

## 3. Cofrinhos

- [ ] Manter a associação entre cofrinho e usuário por meio de uma chave estrangeira.
- [ ] Definir se cada movimentação relacionada a um cofrinho precisa armazenar seu identificador.
- [ ] Definir as regras para depósitos, retiradas e controle do valor acumulado.
- [ ] Definir o comportamento ao excluir um cofrinho que contém dinheiro.
- [ ] Decidir se um cofrinho poderá ser editado ou excluído quando estiver associado a uma meta.
- [ ] Definir como funcionará o depósito automático: frequência, execução, disponibilidade de saldo e registro no histórico.
- [ ] Avaliar se o histórico do cofrinho será composto pelas movimentações gerais ou por registros específicos.

## 4. Categorias

- [ ] Definir se as categorias padrão serão compartilhadas por todos os usuários ou se cada usuário terá suas próprias categorias.
- [ ] Definir como representar categorias padrão e personalizadas.
- [ ] Estabelecer regras para impedir a edição ou exclusão de categorias padrão.
- [ ] Definir o comportamento ao tentar excluir uma categoria que já esteja associada a movimentações.
- [ ] Decidir se categorias serão utilizadas exclusivamente em movimentações ou se terão alguma relação com cofrinhos.
- [ ] Manter separado o conceito de categoria, que classifica uma operação, do conceito de cofrinho, que representa dinheiro reservado para uma finalidade.

## 5. Metas

- [ ] Definir se cada meta deverá estar obrigatoriamente associada a um cofrinho.
- [ ] Determinar a cardinalidade: um cofrinho poderá ter várias metas ou cada cofrinho estará associado a, no máximo, uma meta?
- [ ] Revisar a redundância entre `tbcofrinho.id_meta` e `tbmeta.id_cofrinho`.
- [ ] Definir os atributos de uma meta, como nome, valor-alvo, data-limite e status, conforme os requisitos desejados.
- [ ] Definir como calcular e exibir o progresso de uma meta.
- [ ] Definir o comportamento quando uma meta for atingida, editada ou excluída.

## 6. Relacionamentos e integridade dos dados

- [ ] Revisar todas as chaves estrangeiras e seus tipos de dados.
- [ ] Definir quais entidades pertencem diretamente ao usuário e quais herdam essa relação por meio de outra entidade.
- [ ] Evitar armazenar informações redundantes quando elas puderem ser obtidas por relacionamentos.
- [ ] Definir regras para impedir que um usuário acesse ou altere movimentações, categorias, metas e cofrinhos de outro usuário.
- [ ] Definir o comportamento de exclusão das entidades relacionadas: impedir, propagar ou preservar os registros históricos.

## 7. Tipos de dados e restrições do banco

- [ ] Revisar os tipos de dados dos identificadores e padronizar chaves primárias e estrangeiras.
- [ ] Definir precisão e escala para valores monetários utilizando `NUMERIC`/`DECIMAL`.
- [ ] Definir restrições para impedir valores inválidos, como montantes negativos quando não permitidos ou metas sem valor positivo.
- [ ] Definir campos obrigatórios, valores padrão e regras de unicidade.
- [ ] Substituir o tipo `password` por uma representação textual adequada para armazenar hashes de senha, nunca senhas em texto puro.
- [ ] Avaliar o uso de datas e horários, incluindo fuso horário quando necessário.
- [ ] Corrigir nomes inconsistentes, como `dt_cricao`, e padronizar a nomenclatura das tabelas e colunas.

## 8. Arquitetura e implementação futura

- [ ] Finalizar o modelo conceitual e esclarecer as regras de negócio.
- [ ] Revisar o modelo relacional no dbdiagram.
- [ ] Gerar e revisar o script SQL para PostgreSQL.
- [ ] Implementar as entidades e os relacionamentos no backend Java com Spring Boot.
- [ ] Definir DTOs, validações e regras de negócio na camada de serviço.
- [ ] Garantir operações financeiras atômicas, especialmente depósitos e retiradas.
- [ ] Implementar autenticação e autorização para isolar os dados de cada usuário.
- [ ] Criar testes para validar saldos, transferências, exclusões e integridade dos relacionamentos.

## Ordem sugerida para retomar

1. Definir o conceito de movimentação financeira.
2. Estabelecer as regras de cálculo do saldo disponível e dos cofrinhos.
3. Revisar a estrutura de `tbmovimentacao` e decidir se `tbtransacao` será eliminada.
4. Definir os relacionamentos entre usuários, categorias, cofrinhos e metas.
5. Revisar as restrições e os tipos de dados.
6. Atualizar o diagrama e só então avançar para o SQL e a implementação.

**Objetivo:** construir um modelo consistente com as regras de negócio antes de se preocupar com detalhes de implementação ou recursos avançados do PostgreSQL.