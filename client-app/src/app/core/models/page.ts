import { User } from './user';

export interface Page{
    id: number;
    name: string;
    bio: string;
    user: User;
    date: Date;
    flag: boolean;
}