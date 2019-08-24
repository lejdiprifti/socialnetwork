import { Injectable } from '@angular/core';
import { ApiService } from '../utilities/api.service';
import { Page } from '../models/page';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class PageService {
  url = "pages";
  constructor(private apiService: ApiService) { }

  public getMyPages(): Observable<Page>{
    return this.apiService.get(this.url);
  }
}
