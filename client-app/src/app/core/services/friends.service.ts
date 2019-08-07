import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class FriendsService {
  url: string = 'requests';
  constructor(private apiService: ApiService) { }

  public acceptFriendRequest(id: number): Observable<any>{
    return this.apiService.put(this.url+'/'+id+'/accept');
  }

  public rejectFriendRequest(id: number): Observable<any>{
    return this.apiService.put(this.url+'/'+id+'/reject');
  }

  public getRequests(): Observable<Array<Object>>{
    return this.apiService.get(this.url);
  }

  public getMyRequests(id: number): Observable<Array<Object>>{
    return this.apiService.get(this.url+'/'+id);
  }

  public cancelRequest(id:number): Observable<any>{
    return this.apiService.delete(this.url+'/'+id+'/cancel');
  }

  public getFriends(): Observable<any>{
    return this.apiService.get(this.url+'/friends');
  }
}
