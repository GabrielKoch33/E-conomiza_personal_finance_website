// ============================================================
// MÓDULOS E SUBMÓDULOS — edite aqui para renomear, adicionar
// ou remover itens da sidebar. Cada item tem um id único.
// ============================================================

const modules = [
  {
    id: 'dashboards',
    label: 'Dashboards',
    icon: 'layout-dashboard',
  },
  {
    id: 'movimentacoes',
    label: 'Movimentações',
    icon: 'arrow-down-left',
    subModules: [
      { id: 'entradas-saidas', label: 'Entradas e Saídas', icon: 'arrow-up-down' },
      { id: 'categorias', label: 'Categorias', icon: 'tag' },
    ],
  },
  {
    id: 'planejamento',
    label: 'Planejamento',
    icon: 'piggy-bank',
    subModules: [
      { id: 'cofrinhos', label: 'Cofrinhos', icon: 'piggy-bank' },
      { id: 'metas', label: 'Metas', icon: 'target' },
    ],
  },
  {
    id: 'investimentos',
    label: 'Investimentos',
    icon: 'wallet',
    subModules: [
      { id: 'carteira', label: 'Carteira', icon: 'wallet-minimal' },
      { id: 'indicadores-bolsa', label: 'Indicadores Bolsa', icon: 'trending-up-down' },
    ],
  },
  {
    id: 'relatorios',
    label: 'Relatórios',
    icon: 'file-text',
  },
]

// Encontra um módulo pelo id
function findModule(id) {
  return modules.find((m) => m.id === id)
}

// Encontra um submódulo dentro de um módulo
function findSubModule(moduleId, subId) {
  const mod = findModule(moduleId)
  if (!mod || !mod.subModules) return undefined
  return mod.subModules.find((s) => s.id === subId)
}

// Monta o título exibido no header a partir do módulo ativo
// Exemplo: "Movimentações · Entradas e Saídas"
function resolveTitle(moduleId, subId) {
  const mod = findModule(moduleId)
  if (!mod) return 'Controle Financeiro'
  if (!subId) return mod.label
  const sub = mod.subModules?.find((s) => s.id === subId)
  return sub ? `${mod.label} · ${sub.label}` : mod.label
}
