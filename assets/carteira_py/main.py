import time
import entradas as ent
import despesas as des
import categorias as cat
import relatorios as rel
import estatisticas as est
import graficos as views
import analises as anl
import menu_metas_cofrinho as met
import funcoes as f
import dados


''' TO DO
-> Solicitação de saldo e total em cofrinhos ao iniciar o programa pela primeira vez.
-> Salvamento de dados.
-> Função que calcula saldo.
'''

def main_menu():
    while True:
        f.limpar_tela()
        f.double_line()
        print('CARTEIRA DE GABRIEL'.center(f.size,' '))
        f.double_line()
        print(
            f'1 - ENTRADAS\n'             # FEITO
            f'2 - DESPESAS\n'             # FEITO
            f'3 - CATEGORIAS\n'           # FEITO
            f'4 - RELATÓRIOS\n'           #
            f'5 - ESTATÍSTICAS\n'         #
            f'6 - GRÁFICOS\n'             #
            f'7 - ANÁLISES AUTOMÁTICAS\n' #
            f'8 - METAS & COFRINHOS\n'    #
            f'9 - ALTERAR SALDO INICIAL\n'# FEITO
            '0 - SALVAR E SAIR'
            )
        f.double_line()
        opcao = f.ler_opcao_menu(9)
        f.double_line()
        if opcao == 1:
            ent.menu_entradas()
        elif opcao == 2:
            des.menu_despesa()
        elif opcao == 3:
            cat.menu_categorias()
        elif opcao == 4:
            rel.menu_relatorio()
        elif opcao == 5:
            est.menu_estatisticas()
        elif opcao == 6:
            views.menu_visualizacoes()
        elif opcao == 7:
            anl.menu_analises()
        elif opcao == 8:
            met.menu_cofrinhos_e_metas()
        elif opcao == 9:
            msg = f.redefine_saldo()
            print(msg)
            f.read_key()
        elif opcao == 0:
            print('Salvando...')
            f.pause()
            break

if __name__ == '__main__':

    while True:
        f.saldo_inicial = input(f'Qual é o seu saldo atual?\nSaldo: ').strip()

        if not f.saldo_inicial:
            print('Valores vazios não são permitidos!')
            continue

        try:
            f.saldo_inicial = f.saldo_inicial.replace('.', '')
            f.saldo_inicial = f.saldo_inicial.replace(',', '.')
            f.saldo_inicial = float(f.saldo_inicial)

        except:
            print('Por favor, insira um valor numérico válido.')
            continue

        if f.saldo_inicial < 0:
            print('Valores negativos não são permitidos!')
            continue
        
        f.saldo_atual = f.saldo_inicial
        break

    print('Iniciando...')
    time.sleep(1)
    print('Carregando Arquivos e Dados...')
    time.sleep(1)
    print('Pronto')
    main_menu()
