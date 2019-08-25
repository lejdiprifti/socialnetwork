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

  public getMyPages(): Observable<Array<Page>>{
    return this.apiService.get(this.url);
  }

  public getPageById(id: number): Observable<Page>{
    return this.apiService.get(this.url+"/"+id);
  } 
  public createPage(page: Page): Observable<Page>{
    return this.apiService.post(this.url,page);
  }

  public editPage(id: number,page: Page): Observable<Page>{
    return this.apiService.put(this.url+"/"+id,page);
  }

  public deletePage(id: number): Observable<void>{
    return this.apiService.delete(this.url+"/"+id);
  }
}
