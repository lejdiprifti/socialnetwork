import { Role } from './role';
import { Post } from './post';
import { SocialLinks } from './socialLinks';


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
    friends?: Array<User>;
    likes?: Array<Post>;
    bio?: String;
    socialLinks?: SocialLinks;
    flag?: boolean;
} 