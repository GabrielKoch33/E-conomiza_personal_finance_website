Regras de relacionamento:

Um user pode ter N cofrinhos.
Um cofrinho pode ter 1 meta associada a ele.
Um usuário pode criar metas apenas quando houverem cofrinhos criados por ele que não estejam com metas associadas. Ou seja, meta depende de cofrinho.
Um usuario poderá criar N categorias personalizadas.
Existirão categorias padrões que todos tem acesso.


Regras de valores:

Usuário conterá: Saldo, Total em Cofrinhos e Patrimônio.

Saldo e Total cofrinhos serão calculados sobre demanda através de Views.

Patrimônio é a soma de Saldo + Tot. Cofrinhos

Não é permitido de forma direta transferir de um cofrinho para outro. Deve se retirar e depois depositar.

Entradas aumentam saldo e  consequentemente o patrimônio.

Despesas diminuem o saldo e consequentemente o patrimônio.

Depositos em cofrinhos diminuem saldo porém mantém o patrimônio e aumentam o total daquele cofrinho.

Retiradas em cofrinhos aumentam o saldo, mantém o patrimônio e diminuem o total daquele cofrinho.

Ao realizar quaisquer dessas operações, uma transação deve ser emitida, afinal é por ela que o saldo e valor cofrinho vai ser calculado.

Em casos de exclusão ou edição de registro, um calculo deve ser feito em cima dos valores de saldo e valor total atual. Dessa forma o saldo pode estar atualizado e depois quando for feita uma varredura nos registros, os valores baterem.


Regras e Características:

Despesas e Entradas devem ter categoria, operações para cofrinhos não.

Usuários devem ter acesso apenas a conteúdos defaults ou registros criados por ele,seja transações, categorias, cofrinhos.





