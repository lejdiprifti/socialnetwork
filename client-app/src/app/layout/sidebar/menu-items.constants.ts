import { RoleEnum } from './../../core/models/role.enum';
import { MenuItem } from './menu-item'

export const menuItems: Array<MenuItem> = [{
    url: '/suggestion/dashboard',
    icon: 'fa-dashboard',
    label: 'Dashboard',
    allowedRoles: [RoleEnum.ADMIN, RoleEnum.USER]
}, {
    url: '/suggestion/posts',
    icon: 'fa-table',
    label: 'Manage posts',
    allowedRoles: [RoleEnum.ADMIN]

}]