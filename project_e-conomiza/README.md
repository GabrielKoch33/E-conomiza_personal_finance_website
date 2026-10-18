# Controle Financeiro — Casco de App

Este é um **casco (esqueleto) reutilizável** para um app de controle financeiro.
Ele tem a estrutura básica que quase todo app precisa: uma barra lateral com
menus, um cabeçalho, área de conteúdo e troca de tema claro/escuro.

Tudo foi feito em **HTML, CSS e JavaScript puro** — sem React, sem TypeScript,
sem bibliotecas de interface. É fácil de entender e de modificar.

---

## 1. As pastas e arquivos do projeto

A estrutura de arquivos é a seguinte:

```
projeto/
├── index.html              ← a página principal (a "cara" do app)
├── package.json            ← só metadados do projeto (sem pacotes nem build)
├── public/
│   └── favicon.svg         ← o ícone que aparece na aba do navegador
└── src/                    ← TODO o código-fonte do app
    ├── app.js              ← a lógica principal do casco
    ├── styles.css          ← todo o visual (cores, tamanhos, layout)
    ├── icons.js            ← os ícones em SVG
    ├── navigation.js       ← a lista de menus da barra lateral
    └── footer-links.js     ← os links do rodapé da barra lateral
```

Como o projeto não usa nenhuma ferramenta de build, você pode rodá-lo
**simplesmente abrindo o `index.html` no navegador** (duplo clique no arquivo).
Não precisa de servidor, nem de terminal, nem de instalar nada.

Abaixo, o que cada arquivo faz.

### `index.html` — a página principal

É o "esqueleto" da tela. Ele define o que aparece na página:

- a **barra lateral** (sidebar) à esquerda, com um botão para encolher/expandir
- o **cabeçalho** (header) no topo, com o título do módulo atual e dois botões
  à direita: **conta** (menu com "Perfil" e "Sair") e **configurações** (painel
  com a troca de tema)
- a **área de conteúdo** no centro, que hoje mostra um placeholder (um
  "espaço reservado") que você vai substituir pelas suas páginas reais

No final do arquivo, quatro `<script>` carregam os arquivos JavaScript na ordem
certa: ícones → menus → links do rodapé → lógica principal. São scripts
comuns (não módulos ES), então não há build nem servidor — é só HTML, CSS e JS
puros que o navegador entende direto.

### `src/styles.css` — todo o visual

Aqui ficam todas as regras de estilo. O arquivo está dividido em seções com
comentários explicativos no topo de cada uma:

1. **Temas** — as cores do app. Tudo usa "variáveis CSS" (`--bg`, `--fg`,
   `--border`, etc.), então para mudar a paleta inteira você só altera os
   valores no topo do arquivo. Há dois blocos: um para o tema claro
   (`[data-theme="light"]`) e outro para o tema escuro
   (`[data-theme="dark"]`).
2. **Layout base** — o reset e o flexbox que põe a sidebar e o conteúdo lado
   a lado.
3. **Sidebar** — largura, bordas, animação ao encolher.
4. **Botões de ícone** — o visual dos botões redondos.
5. **Itens de navegação** — os botões dos módulos e submódulos, com estado
   ativo e animação de "abrir/fechar" das subopções.
6. **Links do rodapé** — os links de GitHub e LinkedIn no rodapé da sidebar.
7. **Header** — o cabeçalho fixo no topo.
8. **Dropdowns** — os menus pop-up de conta e configurações.
9. **Conteúdo** — a área central onde vão as suas páginas.
10. **Tooltip** — a dica que aparece ao passar o mouse quando a sidebar está
    recolhida.

### `src/icons.js` — os ícones

Contém todos os ícones do app como SVG (imagens vetoriais desenhadas com código,
no estilo "Lucide"). Para cada ícone há um nome e o respectivo SVG.

Funções exportadas:

- `renderIcons(root)` — procura na página todos os elementos com o atributo
  `data-icon="nome"` e preenche com o SVG correspondente. É chamada
  automaticamente ao carregar o app.
- `iconSpan(name)` — cria um `<span>` de ícone pronto para inserir no DOM. Usada
  pelo `app.js` quando monta os botões da sidebar.

Para adicionar um ícone novo, basta colar o SVG dele dentro do objeto `icons`
neste arquivo.

### `src/navigation.js` — a lista de menus

É onde você define **quais módulos aparecem na barra lateral**. Hoje tem 5:

1. **Dashboards** (sem submódulos)
2. **Movimentações** → Entradas e Saídas, Categorias
3. **Planejamento** → Cofrinhos, Metas
4. **Investimentos** → Carteira, Indicadores Bolsa
5. **Relatórios** (sem submódulos)

Cada item tem um `id` único, um `label` (texto exibido), um `icon` (nome do
ícone em `icons.js`) e, opcionalmente, uma lista de `subModules`.

Funções exportadas:

- `modules` — a lista completa dos módulos (importada pelo `app.js`).
- `findModule(id)` — encontra um módulo pelo seu id.
- `findSubModule(moduleId, subId)` — encontra um submódulo dentro de um módulo.
- `resolveTitle(moduleId, subId)` — monta o título exibido no header a partir
  do módulo ativo (ex.: "Movimentações · Entradas e Saídas").

Para renomear, adicionar ou remover itens da sidebar, você edita **só este
arquivo** — o resto do app se ajusta automaticamente.

### `src/footer-links.js` — os links do rodapé

Os links que aparecem no rodapé da sidebar (hoje: "Repositório do projeto",
"Meu GitHub" e "Meu LinkedIn"). Cada um tem um `id`, um `label`, uma `href`
(a URL) e um `icon`.

**Importante:** troque as URLs de exemplo (`https://github.com/seu-usuario/...`)
pelos seus endereços reais antes de publicar o app.

### `src/app.js` — a lógica principal

O "cérebro" do casco. Ele conecta o HTML, o CSS e os outros módulos JS e faz
tudo funcionar. Suas responsabilidades:

1. **Estado da aplicação** — guarda qual módulo está ativo, se a sidebar está
   aberta e qual módulo está com subopções expandidas.
2. **Sidebar** — renderiza os botões dos módulos e submódulos, controla
   expandir/recolher e salva a preferência no `localStorage` (assim o estado
   da sidebar persiste entre recargas).
3. **Rodapé da sidebar** — renderiza os links do rodapé.
4. **Header** — atualiza o título do módulo ativo.
5. **Dropdowns** — abre e fecha os menus de conta e configurações, incluindo
   fechar ao clicar fora ou apertar Esc.
6. **Tema claro/escuro** — troca o tema e salva a escolha no `localStorage`.
7. **API pública** — exporta funções que você vai usar quando criar suas
   páginas reais:
   - `getActiveModule()` — retorna o módulo e submódulo ativos no momento
   - `setOnModuleChange(fn)` — registra uma função para rodar quando o usuário
     troca de módulo (é aqui que você vai chamar a sua função de renderizar a
     página correspondente)
   - `navigate(moduleId, subId)` — navega programaticamente para um módulo
   - `getContentRoot()` — retorna o elemento onde o conteúdo da página deve ser
     inserido

---

## 2. Como os arquivos se relacionam

A relação entre os arquivos é assim:

```
index.html
   │  (carrega no final)
   ▼
src/app.js  ←─── importa ──── src/icons.js        (fornece os SVGs)
   │                             src/navigation.js (fornece os menus)
   │                             src/footer-links.js (fornece os links)
   │                             src/styles.css    (fornece o visual)
   │
   │  (manipula)
   ▼
Elementos do index.html (sidebar, header, conteúdo)
```

1. O navegador abre o `index.html`, que tem a estrutura vazia da página.
2. No final do `index.html`, os `<script>` carregam os módulos JS na ordem
   certa.
3. O `app.js` é o maestro: ele importa os ícones, a lista de menus, os links e
   o CSS, e então **monta** a sidebar, o header e os menus dentro do HTML.
4. Quando você clica em um módulo, o `app.js` atualiza o estado, re-renderiza a
   sidebar e o título do header. Se você registrou uma função com
   `setOnModuleChange(fn)`, ela roda nessa hora — é aí que você vai encaixar a
   sua página real.

Cada arquivo tem **uma responsabilidade só**, e eles se comunicam por funções
importadas/exportadas. Isso é o que se chama de "modularidade": em vez de um
arquivo gigante com tudo, temos vários arquivos pequenos que se encaixam.

---

## 3. O `package.json` (e por que ele é quase vazio)

O `package.json` é o arquivo que descreve o projeto. No nosso caso, ele é bem
enxuto porque **não usamos nenhum pacote externo** — só HTML, CSS e JavaScript
puro, sem framework nem ferramenta de build:

```json
{
  "name": "controle-financeiro",
  "private": true,
  "version": "0.0.0",
  "description": "Casco de app de controle financeiro em HTML, CSS e JavaScript puro, sem build."
}
```

- **`name`** — o nome do projeto.
- **`private: true`** — impede que o projeto seja publicado no npm por engano.
- **`version`** — a versão atual (sempre que você publicar uma versão nova,
  sobe esse número).
- **`description`** — uma frase explicando o que é o projeto.

Como não há dependências, **não precisamos rodar `npm install`** — o projeto
funciona só abrindo o `index.html` no navegador.

### Outros arquivos de configuração

- **`.gitignore`** — diz ao Git quais arquivos ignorar (por exemplo, pastas
  temporárias do sistema ou de editor).
- **`.env`** — variáveis de ambiente (senhas, chaves de API, etc.). **Nunca**
  compartilhe o conteúdo deste arquivo publicamente.
- **`.bolt/`** — pasta interna da ferramenta Bolt, não precisa se preocupar
  com ela.

---

## 4. Como baixar o projeto na sua máquina

Como o projeto é só HTML, CSS e JavaScript puro (sem build, sem dependências),
baixar e rodar é bem simples.

### Passo 1 — Baixe o código-fonte

Se o projeto está no GitHub, o jeito mais simples é baixar o ZIP:

1. Abra a página do repositório no GitHub.
2. Clique no botão verde **"Code"** e depois em **"Download ZIP"**.
3. Extraia o ZIP em uma pasta do seu computador.

Se você já usa Git, pode clonar direto:

```
git clone https://github.com/seu-usuario/seu-repo.git
cd seu-repo
```

### Passo 2 — Abra no navegador

Basta dar **duplo clique no arquivo `index.html`**. Ele vai abrir no seu
navegador e o app já funciona. Não precisa instalar Node.js, nem rodar
`npm install`, nem iniciar servidor nenhum.

> Dica: se você quiser ver as alterações de código ao vivo enquanto
> programa, é só salvar o arquivo e apertar **F5** no navegador para
> recarregar.
>
> Se um dia quiser um servidor de desenvolvimento (para recarregar
> automaticamente ao salvar, por exemplo), basta adicionar a extensão
> **"Live Server"** no VS Code e clicar em "Go Live" — mas isso é opcional.

### Passo 3 — Publique (quando estiver pronto)

Para colocar no ar, suba os arquivos em qualquer hospedagem de site estático:
**GitHub Pages**, **Netlify**, **Vercel**, etc. Basta enviar a pasta inteira
como está — não há etapa de build.

---

## 5. Próximos passos

Quando for criar as páginas reais de cada módulo (a parte de Dashboards, de
Movimentações, etc.), você vai:

1. Criar um arquivo JS para cada página (ex.: `src/pages/dashboards.js`).
2. No `src/app.js`, usar `setOnModuleChange(fn)` para chamar a função certa
   quando o usuário troca de módulo.
3. Usar `getContentRoot()` para saber onde inserir o HTML da página.

Como o backend será em Java + Spring, essas páginas vão conversar com a sua API
fazendo `fetch()` para os endpoints que você criar lá.
