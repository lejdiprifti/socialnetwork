import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { Observable } from 'rxjs/internal/Observable';
import { User } from '../models/user';
import { ObservedValueOf } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url='users'
  constructor(private apiService: ApiService) {
    
   }

  public getUsers(): Observable<Array<User>>{
    return this.apiService.get(this.url);
  }

  public getUserById(id:number): Observable<User>{
    return this.apiService.get(this.url+'/'+id);
  }

  public sendFriendRequest(id: number): Observable<any>{
    return this.apiService.post(this.url+'/'+id);
  }
  
  public update(id:number,user: User): Observable<User>{
    return this.apiService.put(this.url+'/'+id,user);
  }
  
}
