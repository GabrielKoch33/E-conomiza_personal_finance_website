lista_categorias = [ 
  {"id":1,"nome":"alimentação","default":True},
  {"id":2,"nome":"transporte","default":True},
  {"id":3,"nome":"moradia","default":True},
  {"id":4,"nome":"saúde","default":True},
  {"id":5,"nome":"educação","default":True},
  {"id":6,"nome":"lazer","default":True},
  {"id":7,"nome":"parcelas","default":True},
  {"id":8,"nome":"salário","default":True},
  {"id":9,"nome":"investimentos","default":True},
  {"id":10,"nome":"outros","default":True}
]

lista_entradas = [
    # {
    #     "id": 2,
    #     "valor": 120.50,
    #     "descricao": ["mercado", "mensal"],
    #     "categoria": "alimentação",
    #     "data": "06/06/2026"
    # },
    # {
    #  ...
    # }...
]

lista_despesas = [
    # {
    #     "id": 4,
    #     "valor": 212.00,
    #     "descricao": ["presente", "namorada"],
    #     "categoria": "presentes",
    #     "data": "02/04/2026"
    # },
    # {
    # ...
    # }...
]

logs_entr_saida = [
    # {
    #   "tipo": 'saida', 
    #   "id_lista": 4,
    #   "valor": 212.00,
    #   "acao": 'adicionar',
    # },
    # {
    # ...
    # }...
]

lista_cofrinhos = [
    # {
    #   "id": 1, 
    #   "nome": 'Reserva Financeira',
    #   "data_criacao": '25/01/2025',
    #   "valor_atual": 5434.00,
    #   "auto_deposito": True,
    #   "qtd_automatica": 10.00,
    #   "meta": False
    # }
    # {
    #   "id": 1, 
    #   "nome": 'Reserva Financeira',
    #   "data_criacao": '25/01/2025',
    #   "valor_atual": '5434.00',
    #   "meta": True,
    #   "valor_final_desejado": 10.000,00,
    #   "porcentagem_conclusao": 52
    # }
]

logs_metas = [
    # {
    #   "id_cofrinho": 2,
    #   "valor_meta": 10,000.00,
    #   ""
    # }
]

'''
Abaixo estão duas Tabelas Hash em que cada palavra fornecida dentro do campo descrição
é armazenada como chave do dicionário, e os valores dentro dos sets{} são os ids
dos registros.
'''
palavras_desc_entradas = {
    # "mercado": {2, 7}, -> a palavra mercado aparece nos registro de id 2 e 7
    # "churrasco": {7}...
}

palavras_desc_despesas = {
    # "presente": {2, 3}, -> a palavra mercado aparece nos registro de id 2 e 7
    # "namorada": {3},
    # "amigo":{2},...
}

