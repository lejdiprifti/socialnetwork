import { User } from './user';
import { Post } from './post';

export interface Page{
    id?: number;
    name?: string;
    bio?: string;
    user?: User;
    date?: Date;
    followers?: Array<any>;
    posts?: Array<Post>;
    flag?: boolean;
}