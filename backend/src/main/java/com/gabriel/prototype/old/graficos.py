import funcoes as f
#--import pandas as p
#--import matplotlib as mp

def grafico_pizza_por_categoria():
    f.read_key()
    pass
def grafico_barras_categoria(): 
    f.read_key()
    pass
def evolucao_saldo(): 
    f.read_key()
    pass
def grafico_linha_despesas():
    f.read_key()
    pass

def menu_visualizacoes():
    while True:
        f.limpar_tela()
        f.double_line()
        print('GRÁFICOS'.center(f.size,' '))
        f.double_line()
        print(
        '1 - PIZZA POR CATEGORIA\n'
        '2 - BARRAS POR CATEGORIA\n'
        '3 - EVOLUÇÃO SALDO\n'
        '4 - LINHA DE DESPESAS\n'
        '0 - VOLTAR'
        )
        f.double_line()
        opcao = f.ler_opcao_menu(4)
        f.double_line()
        if opcao == 1:
            f.limpar_tela()
            grafico_pizza_por_categoria()

        elif opcao == 2:
            f.limpar_tela()
            grafico_barras_categoria()

        elif opcao == 3:
            f.limpar_tela()
            evolucao_saldo()

        elif opcao == 4:
            f.limpar_tela()
            grafico_linha_despesas()

        elif opcao == 0:
            f.limpar_tela()
            break
        
if __name__ == '__main__':
    menu_visualizacoes()
