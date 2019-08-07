import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from '../utilities/api.service';
import { Chat } from '../models/chat';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  url='chat';

  constructor(private apiService: ApiService) { }

  public getMessages(id:number): Observable<Array<Chat>>{
    return this.apiService.get(this.url+'/'+id);
  }

  public sendMessage(id:number,chat: Chat): Observable<any>{
    return this.apiService.post(this.url+'/'+id, chat);
  }
}
