import { User } from './user';
import { LikedPost } from './likedpost';

export interface Post {
  id?:number;
  title?:string;
  description?:string;
  date?: Date;
  user?: User;
  likes?: Set<LikedPost>;
  flag?: boolean;
}