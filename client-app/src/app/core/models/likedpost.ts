import { Post } from './post';
import { User } from './user';

export interface LikedPost {
    id: Object;
    post: Post;
    user: User;
    flag: boolean;
    date: Date;
}