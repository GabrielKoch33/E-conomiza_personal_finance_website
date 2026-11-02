import funcoes as f
#--import pandas as p

def media_gastos():
    f.read_key()
    pass
def maior_menor_despesa():
    f.read_key()
    pass
def maior_menor_entrada():
    f.read_key()
    pass
def desvio_padrao():
    f.read_key()
    pass
def percentual_categoria():
    f.read_key()
    pass

def menu_estatisticas():
    while True:
        f.limpar_tela()
        f.double_line()
        print('ESTATÍSTICAS'.center(f.size,' '))
        f.double_line()
        print(
        '1 - MÉDIA DE GASTOS\n'
        '2 - MAIOR E MENOR DESPESA\n'
        '3 - MAIOR E MENOR ENTRADA\n'
        '4 - DESVIO PADRÃO\n'
        '5 - PERCENTUAL POR CATEGORIA\n'
        '0 - VOLTAR'
        )
        f.double_line()
        opcao = f.ler_opcao_menu(5)
        f.double_line()
        
        if opcao == 1:
            media_gastos()

        elif opcao == 2:
            maior_menor_despesa()

        elif opcao == 3:
            maior_menor_entrada()

        elif opcao == 4:
            desvio_padrao()

        elif opcao == 5:
            percentual_categoria()

        elif opcao == 0:
            f.limpar_tela()
            break

if __name__ == '__main__':
    menu_estatisticas()

