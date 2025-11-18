import { MenuItem } from "primeng/api";

export const MENU_DATA: MenuItem[] = [
  {
    label: 'Inicio',
    routerLink: ['/'],
    routerLinkActiveOptions: { exact: true }
  },
  {
    label: 'Participantes',
    routerLink: ['/participants'],
  },
  {
    label: 'Jueces',
    routerLink: ['/judges'],
  },
  {
    label: 'Evaluaciones',
    routerLink: ['/evaluations'],
  },
  {
    label: 'Top 3 (Ranking)',
    routerLink: ['/rankings'],
  },
]
