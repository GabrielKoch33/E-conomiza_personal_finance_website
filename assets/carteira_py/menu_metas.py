import funcoes as f
from estruturas_dados import lista_cofrinhos, tabela_assoc_metas, lista_metas


def criar_meta():
    '''
    INT ID
    INT ID COFRINHO
    STR OBJETIVO
    FLOAT VALOR DESEJADO
    (% será calculada no momento exibição)
    '''
    if not lista_cofrinhos:
        return 'Para que uma meta seja criada é necessário que existam cofrinhos criados!'
    id_cofr_livres = f.busca_cofr_livre(tabela_assoc_metas)
    if not id_cofr_livres:
        return 'Todos os cofrinhos existentes já possuem uma meta! Remova alguma já existência ou crie um novo cofrinho'
    else:
        f.imprime_colunas("COFRINHOS")
        print(
            f'{"ID":<5}'
            f'{"NOME":<22}'
            f'{"DATA":<12}'
            f'{"QUANTIA":<15}'
            f'{"AUTO DEPO.":<15}'
            f'{"VAL. AUTO DEPO.":<18}'
            f'{"META":<15}'
            )
        f.line()
        num_registros = 0
        for item in lista_cofrinhos:
            # Exibe apenas aqueles registros os quais estão 'livres'
            if item['id_cofr'] in id_cofr_livres:
                num_registros += 1

                data_ = item["dt_cofr"].strftime("%d/%m/%Y")
                campo_autodep_str = "Ativado" if item["auto_depo"] == True else "Desativado"

                achou_meta, indice = f.encontra_campo_e_indice(
                                                        tabela_assoc_metas[item["id_cofr"]],
                                                        lista_metas, "id_meta"
                                                        )
                campo_valor_meta = (
                    "Nenhuma"  # <-- Atribui 'nenhuma' caso não exista Meta associada ao Cofrinho
                    if not achou_meta
                    else lista_metas[indice]["val_meta"]  # <-- Atribui o valor da meta
                )

                campo_valor_meta = f.formata_moeda(campo_valor_meta)
                campo_valor_autodep = f.formata_moeda(item["val_auto_depo"])
                campo_quantia_cofr = f.formata_moeda(item["val_atual_cofr"])
                print(
                    f'{item["id_cofr"]:<5}'
                    f'{item["nome_cofr"]:<22}'
                    f"{data_:<12}"
                    f"{campo_quantia_cofr:<15}"
                    f"{campo_autodep_str:<15}"
                    f"{campo_valor_autodep:<18}"
                    f"{campo_valor_meta:<15}"
                    )
        f.double_line()
        print(f"Total de registros: {num_registros}")

        while True:
            # Valida que o user informe um ID de cofrinho (VAZIO) e existênte
            id_cofr_meta = f.ler_valida_id()
            achou_cofr, indice = f.encontra_campo_e_indice(id_cofr_meta,lista_cofrinhos,"id_cofr")

            if achou_cofr:
                valor_meta = input(f"Defina um valor para ser a meta desse cofrinho [ESC] PARA SAIR: ")
                valor_meta = f.converte_moeda(valor_meta)

                if isinstance(valor_meta,str):
                    return valor_meta
                else:
                    id_meta =  f.gera_id(lista_metas,'id_meta')
                    lista_metas.append({
                        "id_meta": id_meta,
                        "val_meta": valor_meta,    
                    })
                    tabela_assoc_metas[id_cofr_meta] = id_meta
                break
            else:
                print("Tente Novamente!")
                continue
        return "Meta criada com sucesso!"
    
def editar_meta():
    pass

def excluir_meta():
    pass

def listar_metas():
    pass

def menu_metas():
    while True:
        f.limpar_tela()
        f.double_line()
        print('METAS'.center(f.size,' '))
        f.double_line()
        print(
            f' 1 - ATRIBUIR META\n'
            f' 2 - EDITAR META\n'
            f' 3 - EXCLUIR META\n'
            f' 4 - LISTAR METAS\n'
            f' 0 - VOLTAR'
        )

        f.double_line()
        opcao = f.ler_opcao_menu(4)
        f.double_line()
        
        if opcao == 1:
            f.limpar_tela()
            msg = criar_meta()
            print(msg)
            f.double_line()
            f.read_key()            

        elif opcao == 2:
            f.limpar_tela()
            msg = editar_meta()
            print(msg)
            f.double_line()
            f.read_key()


        elif opcao == 3:
            f.limpar_tela()
            msg = excluir_meta()
            print(msg)
            f.double_line()
            f.read_key()


        elif opcao == 4:
            f.limpar_tela()
            msg = listar_metas()
            print(msg)
            f.double_line()
            f.read_key()

        elif opcao == 0:
            f.limpar_tela()
            break

if __name__ == '__main__':
    menu_metas()
