import { Role } from './role';


export interface User {
    id?: number;
    firstName?: string;
    lastName?: string;
    password?: string;
    birthdate?: string;
    email?: string;
    role?: Role;
    job?: string;
    education?: string;
    address?: string;
    image?: string;
    flag?: boolean;
} 