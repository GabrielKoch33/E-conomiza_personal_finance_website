// ============================================================
// APP SHELL — casco reutilizável.
// Toda a lógica de sidebar, header, tema e estados fica aqui.
// ============================================================

// ---- Estado da aplicação --------------------------------------

const state = {
  sidebarOpen: localStorage.getItem('sidebar-open') !== 'false',
  activeModule: 'dashboards',
  activeSubModule: undefined,
  openModule: null, // qual módulo está com subopções expandidas
}

let onModuleChange = null

// ---- Referências do DOM ---------------------------------------

const sidebar = document.getElementById('sidebar')
const sidebarToggle = document.getElementById('sidebar-toggle')
const sidebarNav = document.getElementById('sidebar-nav')
const sidebarFooter = document.getElementById('sidebar-footer')
const moduleTitle = document.getElementById('module-title')
const placeholderTitle = document.getElementById('placeholder-title')
const content = document.getElementById('content')
const accountBtn = document.getElementById('account-btn')
const accountMenu = document.getElementById('account-menu')
const settingsBtn = document.getElementById('settings-btn')
const settingsMenu = document.getElementById('settings-menu')
const themeToggle = document.getElementById('theme-toggle')
const themeLabel = document.getElementById('theme-label')

// ---- Sidebar: abrir / fechar ----------------------------------

function applySidebarState() {
  sidebar.dataset.open = String(state.sidebarOpen)
  sidebarToggle.setAttribute('aria-label', state.sidebarOpen ? 'Recolher sidebar' : 'Expandir sidebar')
  sidebarToggle.title = state.sidebarOpen ? 'Recolher sidebar' : 'Expandir sidebar'
  const iconSpanEl = sidebarToggle.querySelector('.icon')
  iconSpanEl.setAttribute('data-icon', state.sidebarOpen ? 'panel-left-close' : 'panel-left-open')
  renderIcons(sidebarToggle)
}

sidebarToggle.addEventListener('click', () => {
  state.sidebarOpen = !state.sidebarOpen
  localStorage.setItem('sidebar-open', String(state.sidebarOpen))
  applySidebarState()
})

// ---- Renderiza os módulos na sidebar --------------------------

function renderSidebar() {
  sidebarNav.innerHTML = ''

  modules.forEach((mod) => {
    const hasSubs = !!mod.subModules?.length

    // Botão do módulo
    const btn = document.createElement('button')
    btn.className = 'module-btn'
    btn.dataset.module = mod.id
    btn.append(iconSpan(mod.icon))

    const label = document.createElement('span')
    label.className = 'module-label'
    label.textContent = mod.label
    btn.append(label)

    if (hasSubs) {
      const chev = iconSpan('chevron-down')
      chev.classList.add('module-chevron')
      btn.append(chev)
    }

    // Estado ativo / expandido
    const isActiveMod = state.activeModule === mod.id && !state.activeSubModule && !hasSubs
    if (isActiveMod) btn.classList.add('active')
    if (state.openModule === mod.id) btn.classList.add('expanded')

    // Tooltip quando recolhida
    if (!state.sidebarOpen) btn.setAttribute('data-tooltip', mod.label)

    btn.addEventListener('click', () => onModuleClick(mod, hasSubs))
    sidebarNav.append(btn)

    // Submódulos (colapsáveis)
    if (hasSubs) {
      const list = document.createElement('div')
      list.className = 'submodule-list'
      if (state.openModule === mod.id) list.classList.add('open')

      mod.subModules.forEach((sub) => {
        const subBtn = document.createElement('button')
        subBtn.className = 'submodule-btn'
        if (sub.icon) subBtn.append(iconSpan(sub.icon))
        const subLabel = document.createElement('span')
        subLabel.textContent = sub.label
        subBtn.append(subLabel)

        const isActiveSub = state.activeModule === mod.id && state.activeSubModule === sub.id
        if (isActiveSub) subBtn.classList.add('active')

        subBtn.addEventListener('click', (e) => {
          e.stopPropagation()
          setActive(mod.id, sub.id)
        })
        list.append(subBtn)
      })

      sidebarNav.append(list)
    }
  })
}

// Clique em um módulo: se tem submódulos, expande/recolhe;
// se não tem, define como ativo.
function onModuleClick(mod, hasSubs) {
  if (hasSubs) {
    state.openModule = state.openModule === mod.id ? null : mod.id
    renderSidebar()
  } else {
    setActive(mod.id, undefined)
    state.openModule = null
    renderSidebar()
  }
}

// ---- Renderiza os links do rodapé ----------------------------

function renderFooter() {
  sidebarFooter.innerHTML = ''
  footerLinks.forEach((link) => {
    const a = document.createElement('a')
    a.className = 'footer-link'
    a.href = link.href
    a.target = '_blank'
    a.rel = 'noopener noreferrer'
    a.setAttribute('aria-label', link.label)
    a.title = link.label
    a.append(iconSpan(link.icon))

    const label = document.createElement('span')
    label.className = 'footer-label'
    label.textContent = link.label
    a.append(label)

    if (!state.sidebarOpen) a.setAttribute('data-tooltip', link.label)

    sidebarFooter.append(a)
  })
}

// ---- Define o módulo ativo e atualiza a UI -------------------

function setActive(moduleId, subId) {
  state.activeModule = moduleId
  state.activeSubModule = subId
  state.openModule = subId ? moduleId : state.openModule
  updateTitle()
  renderSidebar()
  if (onModuleChange) onModuleChange(moduleId, subId)
}

// ---- Atualiza o título no header e no placeholder ------------

function updateTitle() {
  const title = resolveTitle(state.activeModule, state.activeSubModule)
  moduleTitle.textContent = title
  placeholderTitle.textContent = title
}

// ---- Dropdowns (conta e configurações) -----------------------

function setupDropdown(btn, menu) {
  btn.addEventListener('click', (e) => {
    e.stopPropagation()
    const isOpen = menu.dataset.open === 'true'
    closeAllDropdowns()
    menu.dataset.open = String(!isOpen)
  })
}

function closeAllDropdowns() {
  accountMenu.dataset.open = 'false'
  settingsMenu.dataset.open = 'false'
}

document.addEventListener('click', (e) => {
  if (!e.target.closest('.dropdown')) closeAllDropdowns()
})

document.addEventListener('keydown', (e) => {
  if (e.key === 'Escape') closeAllDropdowns()
})

setupDropdown(accountBtn, accountMenu)
setupDropdown(settingsBtn, settingsMenu)

// Ações do menu de conta (preparação para login futuro)
accountMenu.querySelectorAll('.dropdown-item').forEach((item) => {
  item.addEventListener('click', () => {
    const action = item.dataset.action
    console.log('Ação da conta:', action)
    closeAllDropdowns()
  })
})

// ---- Tema claro / escuro -------------------------------------

function applyTheme(theme) {
  document.documentElement.dataset.theme = theme
  themeLabel.textContent = theme === 'dark' ? 'Escuro' : 'Claro'
  const iconSpanEl = themeToggle.querySelector('.icon')
  iconSpanEl.setAttribute('data-icon', theme === 'dark' ? 'moon' : 'sun')
  renderIcons(themeToggle)
  localStorage.setItem('theme', theme)
}

themeToggle.addEventListener('click', () => {
  const current = document.documentElement.dataset.theme
  applyTheme(current === 'dark' ? 'light' : 'dark')
})

// ---- API pública do casco ------------------------------------

// Registra uma função para rodar quando o usuário troca de módulo.
// Use isso para renderizar suas páginas reais.
function setOnModuleChange(fn) {
  onModuleChange = fn
}

// Retorna o módulo e submódulo ativos no momento
function getActiveModule() {
  return {
    module: findModule(state.activeModule),
    subModule: findSubModule(state.activeModule, state.activeSubModule),
    moduleId: state.activeModule,
    subModuleId: state.activeSubModule,
    title: resolveTitle(state.activeModule, state.activeSubModule),
  }
}

// Navega programaticamente para um módulo/submódulo
function navigate(moduleId, subId) {
  setActive(moduleId, subId)
}

// Retorna o elemento onde o conteúdo da página deve ser inserido
function getContentRoot() {
  return content
}

// ---- Inicialização -------------------------------------------

applyTheme(localStorage.getItem('theme') || 'light')
applySidebarState()
renderSidebar()
renderFooter()
renderIcons(document)
updateTitle()
