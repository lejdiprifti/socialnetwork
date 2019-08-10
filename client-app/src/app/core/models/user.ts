import { Role } from './role';
import { Post } from './post';


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
    posts?: Array<Post>;
    friends?: Array<any>;
    likes?: Array<Post>;
    flag?: boolean;
} 