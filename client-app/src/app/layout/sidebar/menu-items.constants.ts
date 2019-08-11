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
    url:'/suggestion/friends',
    icon: 'fa fa-address-book',
    label: 'Friends',
    allowedRoles: [RoleEnum.USER]
},{
    url:'/suggestion/requests',
    icon: 'fa fa-users',
    label: 'Requests',
    allowedRoles: [RoleEnum.USER]
},{
    url:'/suggestion/chat',
    icon: 'fa fa-users',
    label: 'Chat',
    allowedRoles: [RoleEnum.USER]
},
{
    url:'/suggestion/profile',
    icon: 'fa fa-user-circle',
    label: 'Profile',
    allowedRoles: [RoleEnum.USER]
}]