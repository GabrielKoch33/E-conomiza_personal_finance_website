import funcoes as f
#--import pandas as p

def saldo_atual():
    print()
    f.read_key()
    pass
def total_entradas():
    f.read_key()
    pass
def total_despesas():
    f.read_key()
    pass
def gastos_por_categoria(): 
    f.read_key()
    pass
def entradas_por_categoria():
    f.read_key()
    pass
def relatorio_mensal():
    f.read_key()
    pass
def relatorio_anual():
    f.read_key()
    pass

def menu_relatorio():
    while True:
        f.limpar_tela()
        f.double_line()
        print('RELATÓRIOS'.center(f.size,' '))
        f.double_line()
        print(
        '1 - TAXA DE CRESCIMENTO: SALDO INICIAL X SALDO ATUAL\n'
        '2 - TOTAL DE ENTRADAS\n'
        '3 - TOTAL DE DESPESAS\n'
        '4 - GASTOS POR CATEGORIA\n'
        '5 - ENTRADAS POR CATEGORIA\n'
        '6 - RELATÓRIO MENSAL\n'
        '7 - RELATÓRIO ANUAL\n'
        '0 - VOLTAR'
        )
        f.double_line()
        opcao = f.ler_opcao_menu(7)
        f.double_line()
        
        if opcao == 1:
            f.limpar_tela()
            saldo_atual()

        elif opcao == 2:
            f.limpar_tela()
            total_entradas()

        elif opcao == 3:
            f.limpar_tela()
            total_despesas()

        elif opcao == 4:
            f.limpar_tela()
            gastos_por_categoria()

        elif opcao == 5:
            f.limpar_tela()
            entradas_por_categoria()

        elif opcao == 6:
            f.limpar_tela()
            relatorio_mensal()

        elif opcao == 7:
            f.limpar_tela()
            relatorio_anual()

        elif opcao == 0:
            f.limpar_tela()
            break

if __name__ == '__main__':
    menu_relatorio()

#Campos: Data, Descrição, Categoria, Valor