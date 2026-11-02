import funcoes as f
import estruturas_dados as est

def listar_categorias():
    f.double_line()
    print('LISTA DE CATEGORIAS'.center(f.size,' '))
    f.double_line()
    print(f'{'ID':<5}{'CATEGORIA':<10}')
    f.line()
    for categoria in est.lista_categorias:
        print(f'{categoria["id"]:<5}{categoria["nome"]:<10}')


def criar_cat_personalizada():
    '''
    Para o python, '' é visto como Falsy, enquanto '  ' é Truthy
    O que determina isso é a quantidade de caracteres, se == 0 -> false, se <> 0 -> true
    Se o user digitasse '   ' ele seria válido. Por isso usamos strip(), ele removerá todos os espaços em branco.
    Se mesmo tirando os espaços em branco o tamanho for > 0 então temos uma entrada válida
    '''
    while True:
        nome_categoria = input('Digite o nome da nova categoria: ').strip()

        if nome_categoria: 
            nome_categoria = nome_categoria.lower()
            break
        else:    
            print('Insira um nome válido (não vazio)!')
            continue       
    # verifica se o nome que o user inserir ja existe na lista de categorias
    if f.encontra_campo_e_indice(nome_categoria,est.lista_categorias,'nome'):
        return 'Essa categoria já existe, duplicadas não são permitidas!'
    
    else:
        est.lista_categorias.append({"id": f.gera_id(est.lista_categorias),
                                    "nome": nome_categoria,
                                    "default": False})
        
        return 'Categoria adicionada!'


def editar_cat_personalizada():
# impede o user de por valores inexistentes, editar cat defaults ou valores inválidos
    id_categoria = f.valida_editar_ou_excluir_categoria(est.lista_categorias)
# retorna o id que o user inseriu 
    if isinstance(id_categoria,int):
        # vai usar o valor de id informado para checar se existe essa categoria criada
        achou, indice = f.encontra_campo_e_indice(id_categoria,est.lista_categorias,'id')

        if achou: # ao encontrar, vai retornar a posição de alteração correta    
            novoValor = input('Qual será o novo nome?: ').lower()
            est.lista_categorias[indice]["nome"] = novoValor
            return 'Campo alterado com sucesso!'

        else:
           return 'Não foi possível encontrar esse item!'
        
    else:
        return id_categoria


def excluir_cat_personalizada():
    id_categoria = f.valida_editar_ou_excluir_categoria(est.lista_categorias)

    if isinstance(id_categoria,int):

        achou, indice = f.encontra_campo_e_indice(id_categoria,est.lista_categorias,'id')
        if achou:
            est.lista_categorias.pop(indice)
            return 'Campo removido com sucesso!'
            
        else:
            return 'Não foi possível encontrar esse item!'

    else:
        return id_categoria


def menu_categorias():
    while True:
        f.limpar_tela()
        f.double_line()
        print('CATEGORIAS'.center(f.size,' '))
        f.double_line()
        print(
        '1 - LISTAR CATEGORIAS\n'
        '2 - CRIAR CATEGORIA PERSONALIZADA\n'
        '3 - EDITAR CATEGORIA PERSONALIZADA\n'
        '4 - EXCLUIR CATEGORIA PERSONALIZADA\n'
        '0 - VOLTAR'
        )
        f.double_line()
        opcao = f.ler_opcao_menu(4)
        f.double_line()
        
        if opcao == 1:
            f.limpar_tela()
            listar_categorias()
            f.double_line()
            f.read_key()

        elif opcao == 2:
            f.limpar_tela()
            msg = criar_cat_personalizada()
            print(msg)
            f.double_line()
            f.read_key()

        elif opcao == 3:
            f.limpar_tela()
            msg = editar_cat_personalizada()
            print(msg)
            f.double_line()
            f.read_key()

        elif opcao == 4:
            f.limpar_tela()
            msg = excluir_cat_personalizada()
            print(msg)
            f.double_line()
            f.read_key()

        elif opcao == 0:
            f.limpar_tela()
            break

if __name__ == '__main__':
    menu_categorias()