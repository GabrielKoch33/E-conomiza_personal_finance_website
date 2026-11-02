import funcoes as f
from categorias import listar_categorias
import estruturas_dados as est

def adicionar_despesa():
    valor_despesa = input('Digite o valor em R$ da despesa: ')
    valor_despesa = f.converte_moeda(valor_despesa)

    if isinstance(valor_despesa,str):
        return valor_despesa

    else: 
        # descrição será uma lista, assim podemos procurar por plavras chaves
        while True:
            descricao_despesa = input('Insira uma descrição para a despesa: ').strip().lower()
            if not descricao_despesa:
                print('Tente novamente!')
            else:
                descricao_despesa = descricao_despesa.split()
                break

        while True:
            data = f.converte_data()

            if not data:
                f.double_line()
                print("Erro: Digite exatamente 8 números.")
                f.double_line()
                continue # se voltar erro, pede data novamente

            else:
                break # senão, valor válido e insere data
        
        f.limpar_tela()
        listar_categorias()
        f.double_line()

        while True:
            # Parte responsável por permitir entrada válida de ID de categoria
            id_despesa = f.ler_valida_id()
            achou, indice = f.encontra_campo_e_indice(id_despesa, est.lista_categorias, 'id')

            if achou:
                id = f.gera_id(est.lista_despesas)# depois que tudo dá certo é gerado um ID

                f.hash_palavra_desc(id,est.palavras_desc_despesas,'adicionar',descricao_despesa)
  

                est.lista_despesas.append({
                                        "id":id,
                                        "valor":valor_despesa,
                                        "descricao":descricao_despesa,
                                        "categoria":est.lista_categorias[indice]["nome"],
                                        "data":data
                                        })
                
                f.insere_log('saida',id,valor_despesa)
                break

            else: 
                print('Tente Novamente!')
                continue
        
        f.limpar_tela()
        return 'Despesa cadastrada!'
    

def listar_despesas():
    if not est.lista_despesas:
        return 'Registro de despesas vazio. Nenhuma despesa para listar!'
    
    else:
        f.imprime_colunas('DESPESAS')
        print(f'{"ID":<5}{"VALOR":<15}{"DESCRIÇÃO":<30}{"CATEGORIA":<20}{"DATA":<12}')
        f.line()

        num_registros = 0
        for item in est.lista_despesas:
           descricao = ' '.join(item['descricao'])
           data_ = item['data'].strftime("%d/%m/%Y")
           num_registros += 1
           print(
                f'{item["id"]:<5}'
                f'R${item["valor"]:<13.2f}'#.2f = duas casas decimais
                f"{descricao:<30}"
                f'{item["categoria"]:<20}'
                f'{data_:<12}'
                )
        f.double_line()
        print(f'Total de registros: {num_registros}') 
        return 'Lista retornada com sucesso!'
    

def editar_despesa():
    if est.lista_despesas: #se conter logs de entrada, da inicio ao processo
        listar_despesas()
        f.double_line()

        id_despesa = f.ler_valida_id() #while true e try/except para ler id informado
        achou, indice = f.encontra_campo_e_indice(id_despesa, est.lista_despesas, 'id')
                        # verifica se o id informado pelo user existe e se existir retorna sua posição
                        # indice será usado para sabermos onde o id informado está 
        if not achou:
            return 'Despesa não cadastrada!'
                
        else:
            print('Qual campo dessa despesa você deseja editar? ')
            f.double_line()

            while True:
                campo = input(f'[1] - VALOR\n[2] - DESCRIÇÃO\n[3] - CATEGORIA\n[4] - DATA\nR: ').strip()

                if campo not in {'1','2','3','4'}:
                    continue
                else:
                    break

            match campo:

                case '1':
                    f.double_line()
                    novo_valor = input('Digite o novo valor em R$ da despesa: ')
                    novo_valor = f.converte_moeda(novo_valor)

                    if isinstance(novo_valor,str):
                        return novo_valor
                    else:
                        f.edita_log('saida',id_despesa,novo_valor)

                        est.lista_despesas[indice]["valor"] = novo_valor
                        return 'Campo "VALOR" alterado com sucesso!'
                                    
                case '2':
                    f.double_line()
                    nova_descricao = input('Digite a nova descrição: ').strip().lower().split()

                    if nova_descricao == est.lista_despesas[indice]['descricao']:
                        return 'Campo "DESCRIÇÃO" alterado com sucesso!'
                    else:
                        
                        f.hash_palavra_desc(id_despesa,est.palavras_desc_despesas,'editar',nova_descricao,est.lista_despesas,indice)
                        return 'Campo "DESCRIÇÃO" alterado com sucesso!'

                case '3':
                    f.double_line()
                    listar_categorias()
                    while True:
                        id_nova_categoria = f.ler_valida_id()                           
                        encontrou_id, indice_categoria = f.encontra_campo_e_indice(id_nova_categoria, est.lista_categorias,'id')

                        if encontrou_id:
                            est.lista_despesas[indice]['categoria'] = est.lista_categorias[indice_categoria]['nome']
                            return 'Campo "CATEGORIA" alterado com sucesso!'
                        else:
                            print('Tente novamente!')
                            continue

                case '4':
                    f.double_line()
                    while True:
                        data = f.converte_data()

                        if not data:
                            f.double_line()
                            print("Erro: Digite exatamente 8 números.")
                            f.double_line()
                            continue # se voltar erro, pede data novamente
                        else:                               
                            break # senão, valor é válido e insere data
                    est.lista_despesas[indice]['data'] = data
                    return 'Campo "DATA" alterado com sucesso!'

                case _: # _ é como se fosse um 'ELSE'. usar | (barra reta) é como um OR
                    f.double_line()
                    return 'Insira um campo válido!'    
    else:
        return 'Nenhuma despesa foi registrada ainda! Nada para editar'


def remover_despesa():
    if est.lista_despesas: #se conter logs de entrada, da inicio ao processo
        listar_despesas()
        f.double_line()

        id_despesa = f.ler_valida_id()
        achou, indice = f.encontra_campo_e_indice(id_despesa, est.lista_despesas,'id')
                        # verifica se o id informado pelo user existe e se existir retorna sua posição
                        # indice será usado para sabermos onde o id informado está 
        if not achou:
            return 'Despesa não cadastrada!'
                
        else:
            id_removido = est.lista_despesas[indice]['id']

            f.hash_palavra_desc(id_removido,est.palavras_desc_despesas,'excluir',est.lista_despesas[indice]['descricao'],est.lista_despesas,indice)

            valor_saida_removido = est.lista_despesas[indice]['valor']
            f.exclui_log('saida',id_removido, valor_saida_removido)

            est.lista_despesas.pop(indice)
            return f'Despesa de ID: {id_removido} foi removida!'
            
    else:
        return 'Nenhuma despesa foi registrada ainda! Nada para remover!'
    

def buscar_por_descricao():
    if est.lista_despesas:

        while True:
            palavra_chave_busca = input('Insira uma palavra-chave para a busca: ').strip().lower()
            if not palavra_chave_busca:
                print('Tente novamente!')
            else:
                break

        if palavra_chave_busca not in est.palavras_desc_despesas:
            return 'Nenhuma descrição com essa palavra-chave foi encontrada!'
        else:
            ids_encontrados = est.palavras_desc_despesas[palavra_chave_busca] #recebe os id das entradas da respectiva palavra chave

            f.imprime_colunas('DESPESAS')
            print(f'{"ID":<5}{"VALOR":<15}{"DESCRIÇÃO":<30}{"CATEGORIA":<20}{"DATA":<12}')
            f.line()
            
            num_registros = 0
            for item in est.lista_despesas: #percorre a lista de entradas comparando [id] com os id relacionados a palavra-chave
                if item['id'] in ids_encontrados:
                    descricao = ' '.join(item['descricao'])
                    data_ = item['data'].strftime("%d/%m/%Y")
                    num_registros += 1
                    print(
                        f'{item["id"]:<5}'
                        f'R${item["valor"]:<13.2f}'
                        f'{descricao:<30}'
                        f'{item["categoria"]:<20}'
                        f'{data_:<12}'
                        )
            f.double_line()
            print(f'Total de registros: {num_registros}')
            return 'Consulta por descrição retornada com sucesso!'
    else:
        return 'Nenhuma despesa foi registrada ainda! Nada para consultar!'


def buscar_por_categoria():
    if est.lista_despesas: #verifica se a lista de entrada possui elementos

        listar_categorias()
        f.double_line()

        id_despesa = f.ler_valida_id()
        achou, indice = f.encontra_campo_e_indice(id_despesa, est.lista_categorias,'id')
                        # verifica se o id informado pelo user existe e se existir retorna sua posição
                        # indice será usado para sabermos onde o id informado está 
        if not achou: 
            return 'Não existe categoria com o ID informado!'
            #verifica se existem categoria com o ID informado
        else:
            f.limpar_tela()
            nome_categoria = est.lista_categorias[indice]['nome']

            if f.encontra_campo_e_indice(nome_categoria, est.lista_despesas,'categoria')[0]:
                
                f.imprime_colunas('DESPESAS')
                print(f'{"ID":<5}{"VALOR":<15}{"DESCRIÇÃO":<30}{"CATEGORIA":<20}{"DATA":<12}')
                f.line()

                num_registros = 0
                for item in est.lista_despesas:
                    if item['categoria'] == nome_categoria:
                        descricao = ' '.join(item['descricao'])
                        data_ = item['data'].strftime("%d/%m/%Y")
                        num_registros += 1
                        print(
                            f'{item["id"]:<5}'
                            f'R${item["valor"]:<13.2f}'
                            f'{descricao:<30}'
                            f'{item["categoria"]:<20}'
                            f'{data_:<12}'
                            )
                f.double_line()
                print(f'Total de registros: {num_registros}')
                return 'Lista retornada com sucesso!'
            else:
                return 'Não existem despesas com a categoria indicada!'

    else:
        return('Nenhuma despesa foi registrada ainda! Nada para consultar!')


def buscar_por_periodo():
    if est.lista_despesas:
        while True:

            while True:
                f.double_line()
                print('Data inicial: ')
                data_inicio = f.converte_data()

                if not data_inicio:
                    f.double_line()
                    print("Erro: Digite exatamente 8 números.")
                    f.double_line()
                    continue # se voltar erro, pede data novamente
                break

            while True:
                f.double_line()
                print('Data final: ')
                data_fim = f.converte_data()

                if not data_fim:
                    print("Erro: Digite exatamente 8 números.")
                    f.double_line()
                    continue # se voltar erro, pede data novamente
                break

            if data_inicio > data_fim:
                f.double_line()
                print('A data de inicial não pode ser mais que a data final! Tente novamente.')
                continue
            else:
                break    
        
        indice_inicio = 0
        while indice_inicio < len(est.lista_despesas) and est.lista_despesas[indice_inicio]['data'] < data_inicio:
            indice_inicio += 1
        
        if indice_inicio == len(est.lista_despesas):
            return 'Não existem despesas dentro do período fornecido, considere buscar uma data de início mais antiga!'

        primeiro_reg = est.lista_despesas[indice_inicio]['data']

        f.imprime_colunas('DESPESAS')
        print(f'{"ID":<5}{"VALOR":<15}{"DESCRIÇÃO":<30}{"CATEGORIA":<20}{"DATA":<12}')
        f.line()
        
        num_registros = 0

        while indice_inicio <= len(est.lista_despesas)-1 and est.lista_despesas[indice_inicio]['data'] <= data_fim:

            descricao = ' '.join(est.lista_despesas[indice_inicio]['descricao'])
            data_ = est.lista_despesas[indice_inicio]['data'].strftime("%d/%m/%Y")

            print(
                f'{est.lista_despesas[indice_inicio]["id"]:<5}'
                f'R${est.lista_despesas[indice_inicio]["valor"]:<13.2f}'
                f'{descricao:<30}'
                f'{est.lista_despesas[indice_inicio]["categoria"]:<20}'
                f'{data_:<12}'
                )
            indice_inicio += 1
            num_registros += 1

        f.double_line()

        if num_registros == 0:
            print('Nenhum registro encontrado dentro do período informado.')
        else:
            ultimo_reg = est.lista_despesas[indice_inicio-1]['data']
            dias = f.calcula_dias_totais(primeiro_reg, ultimo_reg)
            print(f'Foram registrados um total de registros: {num_registros}\nEm um período de {dias} dias!')

        f.double_line()
        return 'Lista retornada com sucesso!'

    else:
        return 'Nenhuma despesa foi registrada ainda! Nada para consultar!'


def menu_despesa():
    while True:
        f.limpar_tela()
        f.double_line()
        print('DESPESAS'.center(f.size,' '))
        f.double_line()
        print(
        '1 - ADICIONAR DESPESA\n'
        '2 - EDITAR DESPESA\n'
        '3 - REMOVER DESPESA\n'
        '4 - LISTAR DESPESAS\n'
        '5 - BUSCA POR DESCRIÇÃO\n'
        '6 - BUSCA POR CATEGORIA\n'
        '7 - BUSCA POR PERÍODO\n'
        '0 - VOLTAR'
        )
        f.double_line()
        opcao = f.ler_opcao_menu(7)
        f.double_line()
        
        if opcao == 1:
            f.limpar_tela()
            msg = adicionar_despesa()
            f.double_line()
            f.read_key()

        elif opcao == 2:
            f.limpar_tela()
            msg = editar_despesa()
            f.double_line()
            f.read_key()

        elif opcao == 3:
            f.limpar_tela()
            msg = remover_despesa()
            f.double_line()
            f.read_key()

        elif opcao == 4:
            f.limpar_tela()
            msg = listar_despesas()
            print(msg)
            f.double_line()
            f.read_key()

        elif opcao == 5:
            f.limpar_tela()
            msg = buscar_por_descricao()
            print(msg)
            f.double_line()
            f.read_key()

        elif opcao == 6:
            f.limpar_tela()
            msg = buscar_por_categoria()
            print(msg)
            f.double_line()
            f.read_key()

        elif opcao == 7:
            f.limpar_tela()
            msg = buscar_por_periodo()
            print(msg)
            f.double_line()
            f.read_key()

        elif opcao == 0:
            f.limpar_tela()
            break

if __name__ == '__main__':
    menu_despesa()
