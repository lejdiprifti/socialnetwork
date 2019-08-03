import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { Observable } from 'rxjs/internal/Observable';
import { User } from '../models/user';

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

  public sendFriendRequest(id: number): Observable<any>{
    return this.apiService.post(this.url+'/'+id);
  }
  
}
