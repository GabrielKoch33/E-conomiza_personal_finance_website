import funcoes as f
from estruturas_dados import lista_cofrinhos, tabela_assoc_metas, lista_metas

def criar_cofrinho():
    while True:
        nome_cofr = input('Digite um nome para o cofrinho: ').strip().lower()
        if not nome_cofr:
            f.double_line()
            print('ERRO: Tente novamente!')
            f.double_line()
        else:
            break

    while True:
        dt_criacao_cofr = f.converte_data()
        if not dt_criacao_cofr:
            f.double_line()
            print('ERRO: Digite exatamente 8 números.')
            f.double_line()
        else:
            break

    while True:
        valor_cofr = input('Digite o valor de seu cofrinho em R$: ').strip()
        if valor_cofr == '0':
            valor_cofr = 0
        else:
            valor_cofr = f.converte_moeda(valor_cofr)
            if isinstance(valor_cofr,str):
                f.double_line()
                print(f'{valor_cofr}\nTente novamente!')
                f.double_line()
                continue
        break

    opt_auto_depo = input(f'Deseja ativar o Auto-Depósito para esse Cofrinho? [Y/any]\nR: ').strip().upper()
    if opt_auto_depo == 'Y':
        bool_auto_dep = True

        while True:
            valor_auto_depo = input('Digite o valor (em R$) para ser depositado automaticamente: ').strip()
            valor_auto_depo = f.converte_moeda(valor_auto_depo)
            if isinstance(valor_auto_depo,str):
                f.double_line()
                print(f'{valor_auto_depo}\nTente novamente!')
                f.double_line()
                continue
            break

    else:
        bool_auto_dep = False
        valor_auto_depo = '--' # Posteriormente, caso ativado, assumirá um Float, usei '--' por estilo
        print('Caso deseje ativar o Auto-Depósito posteriormente, vá para a opção de "Editar Cofrinho"')
        f.pause()

    id_cofr = f.gera_id(lista_cofrinhos,'id_cofr')
    lista_cofrinhos.append({
        "id_cofr":id_cofr,
        "nome_cofr":nome_cofr,
        "dt_cofr":dt_criacao_cofr,
        "val_atual_cofr":valor_cofr,
        "auto_depo":bool_auto_dep,
        "val_auto_depo":valor_auto_depo,
        "id_meta":None,
        })

    tabela_assoc_metas[id_cofr] = None
    # Usamos o id_cofr para que todo cofrinho criado esteja mapeado
    # Iremos inicialmente assumir None, posteriormente acessamos
    # a chave e inserimos a meta. 
    return 'Cofrinho cadastrado!'

def editar_cofrinho():
    if not lista_cofrinhos:
        return 'Registro de entradas vazio. Nenhum cofrinho para listar!'
    else:
        f.double_line()
        listar_cofrinhos()
        f.double_line()

        id_cofrinho = f.ler_valida_id()
        achou, indice = f.encontra_campo_e_indice(id_cofrinho,lista_cofrinhos,'id_cofr')

        if not achou:
            return 'Cofrinho não cadastrado!'
        else:
            print('Qual campo desse cofrinho você deseja editar? ')
            f.double_line()

            while True:
                campo = input(
                            f'[1] - NOME\n'
                            f'[2] - DATA\n'
                            f'[3] - AUTO DEPO.\n'
                            f'[4] - VALOR AUTO DEPO.\n'
                            f'[5] - QUANTIA COFRINHO\n'
                            f'R: ').strip()

                if campo not in {'1','2','3','4','5'}:
                    continue
                else:
                    break

            match campo:
                case "1":
                    f.double_line()

                    while True:
                        novo_nome = input("Digite o novo nome: ").strip()
                        if not novo_nome:
                            print("Tente novamente!")
                            continue
                        break
                    lista_cofrinhos[indice]["nome_cofr"] = novo_nome
                    return "Campo NOME alterado com sucesso!"

                case '2':
                    f.double_line()
                    while True:
                        data = f.converte_data()
                        if not data:
                            f.double_line()
                            print("Erro: Digite exatamente 8 números.")
                            f.double_line()
                            continue
                        else:
                            break
                    lista_cofrinhos[indice]['dt_cofr'] = data
                    return "Campo DATA alterado com sucesso!"

                case '3':
                    f.double_line()
                    if lista_cofrinhos[indice]['auto_depo'] == True:
                        opt_auto_depo = input(f'Deseja desativar o Auto-Depósito para esse Cofrinho? [Y/any]\nR: ').strip().upper()
                        if opt_auto_depo == 'Y':
                            bool_auto_dep = False
                            valor_auto_depo = '--'
                            lista_cofrinhos[indice]['auto_depo'] = bool_auto_dep
                            lista_cofrinhos[indice]['val_auto_depo'] = valor_auto_depo 
                            return "Campo AUTO DEPÓSITO alterado com sucesso!"
                        else:
                            return 'Operação cancelada'
                    else:
                        opt_auto_depo = input(f'Deseja ativar o Auto-Depósito para esse Cofrinho? [Y/any]\nR: ').strip().upper()
                        if opt_auto_depo == 'Y':
                            bool_auto_dep = True
                            while True:
                                valor_auto_depo = input('Digite o valor (em R$) para ser depositado automaticamente: ').strip()
                                valor_auto_depo = f.converte_moeda(valor_auto_depo)
                                if isinstance(valor_auto_depo,str):
                                    f.double_line()
                                    print(f'{valor_auto_depo}\nTente novamente!')
                                    f.double_line()
                                    continue
                                break
                            lista_cofrinhos[indice]['auto_depo'] = bool_auto_dep
                            lista_cofrinhos[indice]['val_auto_depo'] = valor_auto_depo
                            return "Campo AUTO DEPÓSITO alterado com sucesso!"
                        else:
                            return 'Operação cancelada'

                case '4':
                    f.double_line()
                    while True:
                        novo_val_auto_depo = input("Digite o valor (em R$) para ser depositado automaticamente: ").strip()
                        novo_val_auto_depo = f.converte_moeda(novo_val_auto_depo)
                        if isinstance(novo_val_auto_depo,str):
                            print('Tente novamente!')
                            continue
                        else:
                            break
                    lista_cofrinhos[indice] = novo_val_auto_depo
                    return "Campo VALOR AUTO DEPÓSITO alterado com sucesso!"

                case '5':
                    f.double_line()
                    while True:
                        novo_valor = input("Qual será o novo valor (REDEFINIDO) desse cofrinho?: ")
                        novo_valor = f.converte_moeda(novo_valor)

                        if isinstance(novo_valor,str):
                            print(f"{novo_valor} Tente novamente.")
                            continue
                        else:
                            break
                    lista_cofrinhos[indice]["val_atual_cofr"] = novo_valor
                    f.limpar_tela()
                    return 'Campo QUANTIA alterado com sucesso!'

                case _:
                    return 'Insira uma opção válida!'

def excluir_cofrinho():
    if not lista_cofrinhos:
        return 'Registro de entradas vazio. Nenhum cofrinho para listar!'
    else:
        f.double_line()
        listar_cofrinhos()
        f.double_line()

        id_cofrinho = f.ler_valida_id()
        achou, indice = f.encontra_campo_e_indice(id_cofrinho,lista_cofrinhos,'id_cofr')

        if not achou:
            return 'Cofrinho não cadastrado!'
        else:
            lista_cofrinhos.pop(indice) # <-- remove o item da Lista de Cofrinhos

            if tabela_assoc_metas[id_cofrinho] == None:
                del tabela_assoc_metas[id_cofrinho] # <-- remove 'registro' da ligação entre COFR x META
                return f'O Cofrinho de ID: {id_cofrinho} foi removido!'
            else:
                id_meta_relacionada = tabela_assoc_metas[id_cofrinho]# <- Recebe o ID da Meta associado ao Cofrinho 
                achou_meta, indice_meta = f.encontra_campo_e_indice(id_meta_relacionada,lista_metas,'id_meta')# <- Retorna o indice na lista dessa meta, para então remove-la

                lista_metas.pop(indice_meta) # <- remove a meta da lista de metas
                return f'O Cofrinho de ID: {id_cofrinho} foi removido junto com sua respectiva Meta!'


def listar_cofrinhos():
    if not lista_cofrinhos:
        return 'Registro de entradas vazio. Nenhum cofrinho para listar!'
    else:
        f.imprime_colunas('COFRINHOS')
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
            num_registros += 1

            data_ = item['dt_cofr'].strftime("%d/%m/%Y")
            campo_autodep_str = "Ativado" if item['auto_depo'] == True else "Desativado"

            # Usando o ID atual do Cofrinho, verifica se há alguma meta associada
            # ao registro de cofrinho atual
            achou_meta, indice = f.encontra_campo_e_indice(
                                                tabela_assoc_metas[item["id_cofr"]],
                                                lista_metas,
                                                "id_meta"
                                                )
            campo_valor_meta = (
                "Nenhuma" # <-- Atribui 'nenhuma' caso não exista Meta associada ao Cofrinho
                if not achou_meta
                else lista_metas[indice]['val_meta'] # <-- Atribui o valor da meta
            )

            campo_valor_meta = f.formata_moeda(campo_valor_meta)
            campo_valor_autodep = f.formata_moeda(item["val_auto_depo"])
            campo_quantia_cofr = f.formata_moeda(item['val_atual_cofr'])
            print(
                f'{item["id_cofr"]:<5}'
                f'{item["nome_cofr"]:<22}'
                f"{data_:<12}"
                f'{campo_quantia_cofr:<15}'
                f"{campo_autodep_str:<15}"
                f'{campo_valor_autodep:<18}'
                f"{campo_valor_meta:<15}"
            )
        f.double_line()
        print(f'Total de registros: {num_registros}') 
        return 'Lista retornada com sucesso!'


def menu_cofrinhos():
    while True:
        f.limpar_tela()
        f.double_line()
        print('COFRINHOS'.center(f.size,' '))
        f.double_line()
        print(
            f' 1 - CRIAR COFRINHO\n'
            f' 2 - EDITAR COFRINHO\n'
            f' 3 - EXCLUIR COFRINHO\n'
            f' 4 - LISTAR COFRINHOS\n'
            f' 0 - VOLTAR'
        )

        f.double_line()
        opcao = f.ler_opcao_menu(4)
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
            msg = listar_cofrinhos()
            print(msg)
            f.double_line()
            f.read_key()

        elif opcao == 0:
            f.limpar_tela()
            break

if __name__ == '__main__':
    menu_cofrinhos()
