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

  public getRequests(): Observable<any>{
    return this.apiService.get(this.url);
  }

  public cancelRequest(id:number): Observable<any>{
    return this.apiService.delete(this.url+'/'+id+'/cancel');
  }
}
