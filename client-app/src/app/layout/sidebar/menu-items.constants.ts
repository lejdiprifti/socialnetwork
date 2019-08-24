import { RoleEnum } from './../../core/models/role.enum';
import { MenuItem } from './menu-item'

export const menuItems: Array<MenuItem> = [{
    url: '/suggestion/dashboard',
    icon: 'fa-dashboard',
    label: 'News Feed',
    allowedRoles: [RoleEnum.ADMIN, RoleEnum.USER]
}, {
    url: '/suggestion/posts',
    icon: 'fa-table',
    label: 'Manage posts',
    allowedRoles: [RoleEnum.ADMIN]

},{
    url:'/suggestion/people',
    icon: 'fa fa-address-book',
    label: 'People',
    allowedRoles: [RoleEnum.USER]
},{
    url:'/suggestion/requests',
    icon: 'fa fa-users',
    label: 'Requests',
    allowedRoles: [RoleEnum.USER]
},{
    url:'/suggestion/chat',
    icon: 'fa fa-send',
    label: 'Chat',
    allowedRoles: [RoleEnum.USER]
},
{
    url: '/suggestion/pages',
    icon: 'fa fa-dashboard',
    label: 'My Pages',
    allowedRoles: [RoleEnum.USER]
},
{
    url:'/suggestion/profile',
    icon: 'fa fa-user-circle',
    label: 'Profile',
    allowedRoles: [RoleEnum.USER]
}]