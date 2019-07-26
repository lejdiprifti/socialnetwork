import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  url: string='register';
  constructor(private apiService: ApiService) { }

  register(user: User){
    return this.apiService.post(this.url,user);
  }
}
