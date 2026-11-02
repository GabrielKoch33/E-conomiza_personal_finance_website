import subprocess
import sys
import time
from datetime import date,datetime, timedelta
from categorias import listar_categorias
from estruturas_dados import logs_entr_saida

# variaveis importantes
size = 82

'''FUNÇÕES DE INTERFACE'''
def limpar_tela():

    if sys.platform.startswith("win"):
        subprocess.run("cls", shell=True)
    else:
        subprocess.run("clear", shell=True)

def pause():
    time.sleep(1.5)
        
def read_key():
    input("Pressione ENTER para voltar ao menu...")


def double_line():
    print('='*size) 
    
def line():
    print('-'*size)

def imprime_colunas(ref_modulo):
    
    limpar_tela()
    double_line()
    print(f'LISTA DE {ref_modulo}'.center(size,' '))
    double_line()


'''FUNÇÕES DE VALIDAÇÃO'''
def ler_opcao_menu(num_max_opcao):
    '''
    ler opção dos menus
    '''
    while True:
        try:
            op = int(input('Digite a opção que deseja acessar: '))

        except Exception as erro:
            print(f'Erro: {erro}')
            continue

        else:
            if 0 < op < num_max_opcao:
                print('Escolha uma opção dentre as apresentadas!')
                continue
            else:
                return op
        
def valida_editar_ou_excluir_categoria(lista):
    '''
    requer ser chamada para validar o ID que da entrada que o user informar
    '''
    if len(lista) == 10:
        return 'Categorias padrões não são editáveis/removíveis'
    
    else:
        listar_categorias()
        while True:
            try:
                id_informado = int(input('Digite o ID da categoria que deseja alterar ou excluir: '))

            except:
                print('Escolha uma opção (numérica) válida!')
                continue # roda esse try até que uma entrada válida seja informada

            if id_informado <= 10 or id_informado <= 0:
                return 'Categorias padrões do sistema não são editáveis/removíveis'
            else:
                return id_informado #quando a entrada for validada e não der erro, retorna o id da categoria que o user quer usar

def ler_valida_id ():
    '''força o usuário a digitar um int válido'''
    while True: # valida entrada valida de valores
        try:
            id_entrada = int(input('Digite o ID: '))

        except:
            print('Escolha uma opção (numérica) válida!')
            continue # roda esse try até que uma entrada válida seja informada

        else:
            return id_entrada
        
'''FUNÇÕES DE CONVERSÃO'''
def converte_moeda(valor):

    if valor == '':
        return 'Valores vazios não são permitidos!'
    
    try:
        valor = valor.replace('.', '')
        valor = valor.replace(',', '.')
        valor = float(valor)

    except (ValueError,AttributeError):
        return 'Formato inválido'
    
    if valor <= 0:
        return 'Valores negativos ou nulos não são permitidos!'
    
    return valor

def calcula_dias_totais(ref_inicio,ref_fim):
    periodo = ref_fim - ref_inicio
    dias = periodo.days
    return dias
              
def converte_data():
    entrada = input("Digite a data (apenas números, ex: 25122026): ").strip()

    if len(entrada) == 8 and entrada.isdigit():
        texto_data = f"{entrada[:2]}/{entrada[2:4]}/{entrada[4:]}"
        data_formatada = datetime.strptime(
            texto_data,
            "%d/%m/%Y")
        return data_formatada
        # data agora é um OBJETO DATETIME, não string
    else:
        return False

'''FUNÇÕES DE BUSCA E GERAÇÃO'''  
def encontra_campo_e_indice(ref_valor, ref_lista,campo_alvo):
    '''
    Campo alvo deve existir como key dentro do dicionário
    '''
    for index, item in enumerate(ref_lista):
        if item [campo_alvo] == ref_valor:
            return True, index
    else:  
        return False, -1
    
def gera_id(lista):
    '''
    acessa uma lista, se for vazia retorna id 1 para o 1º elemento, senão encontra o maior nº id e + 1
    '''
    if not lista:
        return 1
    else:
        return max(item["id"] for item in lista) + 1


'''FUNÇÕES DE MANIPULAÇÃO DE ESTRUTURAS E SALDO'''
def hash_palavra_desc(ref_id,
                      lista_hash,
                      caso, # Criar/Excluir/Editar
                      ref_desc=[], # nova descricao
                      tipo_lista=[], # estamos percorrendo uma descricao de uma lista de entradas ou saída?
                      ref_indice=0): # indice não é usado na entrada

    match caso:
        case 'adicionar':

            for palavra in ref_desc:
                if palavra not in lista_hash:
                    lista_hash[palavra] = set()
                    lista_hash[palavra].add(ref_id)
                else:
                    lista_hash[palavra].add(ref_id)
    
        case 'excluir':
            
            for palavra in tipo_lista[ref_indice]['descricao']:
                lista_hash[palavra].discard(ref_id)
                if not lista_hash[palavra]:
                    del lista_hash[palavra]
            
        case 'editar':
            
            for palavra in tipo_lista[ref_indice]['descricao']:
                lista_hash[palavra].discard(ref_id)
                if not lista_hash[palavra]:
                    del lista_hash[palavra]
                
            for palavra in ref_desc:
                if palavra not in lista_hash:
                    lista_hash[palavra] = set()
                    lista_hash[palavra].add(ref_id)
                else:
                    lista_hash[palavra].add(ref_id)

            tipo_lista[ref_indice]['descricao'] = ref_desc

'''
common cases:
*Entradas*
-> add entrada: saldo += valor_entr
-> exc entrada: saldo -= valor_entr_excluida
-> upd entrada: 
    -> vl_novo > vl_velho: saldo += (vl_novo - vl_velho)
    -> vl_novo < vl_velho: saldo -= (vl_velho - vl_novo)
*Saídas*
-> add saida: saldo -= vl_saida
-> exc saida: saldo += vl_excl
-> upd saida: 
    -> vl_novo > vl_velho: saldo -= (vl_novo - vl_velho)
    -> vl_novo < vl_velho: saldo += (vl_velho - vl_novo) 
*Atualizar Saldo*
    saldo += (ultimo_reg_saldo - saldo_inicial_informado)
    -> sistema vai armazenar o ultimo valor automaticamente
    '''

saldo_atual = 0 # valor que será alterado sempre que alguma movimentação for feita
saldo_inicial = 0 # valor que é alterado quando: usuário loga pela primeira vez E quando solicita
#corrigir/alterar o saldo informado no primeiro login

def insere_log(tipo_operacao,ref_id,ref_vl_entr):
    '''
    Função chamada ao:
    -> criar entrada/despesa
    '''
    global saldo_atual

    logs_entr_saida.append({"tipo": tipo_operacao,"valor":ref_vl_entr,"id":ref_id})

    if tipo_operacao == 'entrada':
        saldo_atual += ref_vl_entr
    else:
        saldo_atual -= ref_vl_entr

def edita_log(tipo_operacao,ref_id,ref_vl_novo):
    '''
    Função chamada ao:
    -> editar o campo 'valor' de entrada ou despesa 
    '''
    global saldo_atual

    for log in logs_entr_saida:
        if log['tipo'] == tipo_operacao and log['id'] == ref_id:
            vl_velho = log['valor']

            if tipo_operacao == 'entrada':
                if ref_vl_novo > vl_velho:
                    saldo_atual += (ref_vl_novo - vl_velho)
                elif ref_vl_novo < vl_velho:
                    saldo_atual -= (vl_velho - ref_vl_novo)
                else:
                    saldo_atual += 0
                log['valor'] = ref_vl_novo
                break

            else:
                if ref_vl_novo > vl_velho:
                    saldo_atual -= (ref_vl_novo - vl_velho)
                elif ref_vl_novo < vl_velho:
                    saldo_atual += (vl_velho - ref_vl_novo)
                else:
                    saldo_atual -= 0
                log['valor'] = ref_vl_novo
                break
            

def exclui_log(tipo_operacao,ref_id,ref_vl_removido):

    global saldo_atual

    for indice,log in enumerate(logs_entr_saida):

        if log['tipo'] == tipo_operacao and log['id'] == ref_id:

            if tipo_operacao == 'entrada':
                saldo_atual -= ref_vl_removido
            else:
                saldo_atual += ref_vl_removido

            _ = log.pop(indice)
            break
                

def redefine_saldo():
    global saldo_atual
    global saldo_inicial

    limpar_tela()
    double_line()
    print(saldo_atual)
    double_line()
    
    confirm = input('Você tem certeza que deseja redefinir seu saldo inicial?\nY/any: ')
    if confirm.upper() == 'Y':

        double_line()
        novo_saldo_inicial = input('Digite seu novo saldo: ')
        novo_saldo_inicial = converte_moeda(novo_saldo_inicial)

        if isinstance(novo_saldo_inicial,str):
            time.sleep(1)
            return 'Erro ao alterar valor, verifique valor informado'
        
        else:
            print('Alterando saldo...')

            diff = novo_saldo_inicial - saldo_inicial
            saldo_atual += diff

            saldo_inicial = novo_saldo_inicial

            time.sleep(1)
            return 'Saldo alterado com sucesso!'        
        

    else:
        return 'Operação Cancelada'
