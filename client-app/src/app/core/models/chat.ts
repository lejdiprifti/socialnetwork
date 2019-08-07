import { User } from './user';

export interface Chat{
    id: number;
    user: User;
    reciever: User;
    message: string;
    date: Date;
    flag: boolean;
}