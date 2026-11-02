import funcoes as f
import estruturas_dados as est

'''
COFRINHO (SEM META)
->ID, NOME, DATA CRIACAO, VALOR ATUAL, META

COFRINHO (COM META)
->ID, NOME, DATA CRIACAO, VALOR ATUAL, VALOR FINAL DESEJADO,...
...PORCENTAGEM PARA COMPLETAR.

Relação com saldo:
    -> adicionar um valor ao cofrinho implica em descontar do saldo.
    -> deverá ser registrado um log.
    -> ao apagar um cofrinho, todo o valor dentro dele deve retornar ao saldo.
    -> editar valor inserido: 
    --quando o novo valor editado for menor que o antigo: COFRINHO -= vl_velho_inserido - nv_vl_inserido
                                                             SALDO += vl_velho_inserido - nv_vl_inserido
                                                        
    --quando o novo valor editado for maior que o antigo: COFRINHO += vl_novo - vl_antigo
                                                             SALDO -= vl_novo - vl_antigo

-> cofrinhos podem ser criados livremente.
-> cofrinhos podem ser criados sem ter nenhuma quantidade dentro deles.
-> excluir um cofrinho faz com que o valor presente nele seja...
depositado no saldo.
-> deverá ter opção de corrigir o valor, isso não terá efeito no saldo...
logo poderá ser feito livremente

METAS
-> metas adicionarão atributos como VALOR FINAL DESEJADO e...
PORCENTAGEM PARA COMPLETAR ao cofrinho.
-> para uma meta existir é necessário que existam cofrinhos criados e...
sem metas à eles atribuídos.
->
'''
    # {
    #   "id": 1, 
    #   "nome": 'Reserva Financeira', ok
    #   "data_criacao": '25/01/2025', ok
    #   "valor_atual": 5434.00, ok
    #   "auto_deposito": True, 
    #   "qtd_automatica": 10.00,
    #   "meta": False
    # }
    
def criar_cofrinho():
    while True:
        nome_cofrinho = input('Dê um nome para o cofrinho: ').strip().lower()

        if not nome_cofrinho:
            return 'Nomes vazios não são válidos! Voltando para o menu...'
        else:
            break
    
    while True:
        data_cofrinho = f.converte_data()

        if not data_cofrinho:
            f.double_line()
            print("Erro: Digite exatamente 8 números.")
            f.double_line()
            continue # se voltar erro, pede data novamente
        else:
            break

    while True:
        valor_cofrinho = input('Digite o valor em R$ da entrada: ')
        valor_cofrinho = f.converte_moeda(valor_cofrinho)

        if isinstance(valor_cofrinho,str):
            print(valor_cofrinho)
            continue
        else:
            break

    id_cofrinho = f.gera_id(est.lista_cofrinhos)
    est.lista_cofrinhos.append({
                            "id": id_cofrinho,
                            "nome": nome_cofrinho,
                            "data_criacao": data_cofrinho,
                            "valor atual": valor_cofrinho,
                            "auto_deposito": False,
                            "qtd_automatica": 0,
                            "meta": False,
                            })

    return 'Cofrinho cadastrado com sucesso'


def editar_cofrinho():
    f.read_key()
    pass


def excluir_cofrinho():
    f.read_key()
    pass


def depositar_vl_cofrinho():
    f.read_key()
    pass


def retirar_vl_cofrinho():
    f.read_key()
    pass


def listar_cofrinhos():
    if not est.lista_cofrinhos:
        return 'Registro de cofrinhos vazio. Nenhum cofrinho para listar'
    
    else:
        f.imprime_colunas('COFRINHOS')
        print(
            f'{"ID":<5}'
            f'{"NOME":<20}'
            f'{"DATA CRIAÇÃO":<15}'
            f'{"VALOR ATUAL":<16}'
            f'{"DEPÓSITO AUTOMÁTICO":<22}'   
            f'{"QUANTIA AUTOMATICA":22}'
            f'{"META":15}'
            )
        f.line()

        num_registros = 0
        for item in est.lista_cofrinhos:
            data_ = item['data_criacao'].strftime("%d/%m/%Y")
            num_registros += 1
            pass

        f.read_key()
        pass


def consultar_dados_do_cofrinho():
    f.read_key()
    pass


def criar_meta():    
    f.read_key()
    pass


def editar_meta():
    f.read_key()
    pass


def excluir_meta():
    f.read_key()
    pass


def listar_metas_e_porcentagem():
    f.read_key()
    pass


def habilitar_auto_deposito():
    f.read_key()
    pass


def menu_metas():
    while True:
        f.limpar_tela()
        f.double_line()
        print('METAS & COFRINHOS'.center(f.size,' '))
        f.double_line()
        print(
        '1  - CRIAR COFRINHO\n'
        '2  - EDITAR COFRINHO\n'
        '3  - EXCLUIR COFRINHO\n'
        '4  - DEPOSITAR QUANTIA NO COFRINHO\n'
        '5  - RETIRAR QUANTIA DO COFRINHO\n'
        '6  - LISTAR COFRINHOS\n'
        '7  - CONSULTAR DADOS DO COFRINHO\n'
        '8  - CRIAR META\n'
        '9  - EDITAR META\n'# mudar valor desejado
        '10 - EXCLUIR META\n'
        '11 - LISTAR METAS EXISTENTES\n'
        # ID   NOME_COFRINHO          VALOR DESEJADO PORCENTAGEM ATUAL
        # 2    reserva de emergencia  10.000.00       55%
        '12 - HABILITAR DEPÓSITO AUTOMÁTICO NO COFRINHO\n'
        '0  - VOLTAR'
        )
        f.double_line()
        opcao = f.ler_opcao_menu(12)
        f.double_line()
        
        if opcao == 1:
            f.limpar_tela()
            msg = criar_cofrinho()
            print(msg)
            f.double_line()
            f.read_key()            

        elif opcao == 2:
            f.limpar_tela()
            msg = editar_cofrinho()
            print(msg)
            f.double_line()
            f.read_key()


        elif opcao == 3:
            f.limpar_tela()
            msg = excluir_cofrinho()
            print(msg)
            f.double_line()
            f.read_key()


        elif opcao == 4:
            f.limpar_tela()
            msg = depositar_vl_cofrinho()
            print(msg)
            f.double_line()
            f.read_key()


        elif opcao == 5:
            f.limpar_tela()
            msg = retirar_vl_cofrinho()
            print(msg)
            f.double_line()
            f.read_key()


        elif opcao == 6:
            f.limpar_tela()
            msg = listar_cofrinhos()
            print(msg)
            f.double_line()
            f.read_key()


        if opcao == 7:
            f.limpar_tela()
            msg = consultar_dados_do_cofrinho()
            print(msg)
            f.double_line()
            f.read_key()
            

        elif opcao == 8:
            f.limpar_tela()
            msg = criar_meta()
            print(msg)
            f.double_line()
            f.read_key()

        elif opcao == 9:
            f.limpar_tela()
            msg = editar_meta()
            print(msg)
            f.double_line()
            f.read_key()

        elif opcao == 10:
            f.limpar_tela()
            msg = excluir_meta()
            print(msg)
            f.double_line()
            f.read_key()
            
        elif opcao == 11:
            f.limpar_tela()
            msg = listar_metas_e_porcentagem()
            print(msg)
            f.double_line()
            f.read_key()
        
        elif opcao == 12:
            f.limpar_tela()
            msg = habilitar_auto_deposito()
            print(msg)
            f.double_line()
            f.read_key()            

        elif opcao == 0:
            f.limpar_tela()
            break

if __name__ == '__main__':
    menu_metas()

