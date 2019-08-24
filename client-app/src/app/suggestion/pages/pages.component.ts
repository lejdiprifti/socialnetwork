import { Component, OnInit } from '@angular/core';
import { PageService } from '@ikubinfo/core/services/page.service';
import { Page } from '@ikubinfo/core/models/page';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';

@Component({
  selector: 'ikubinfo-pages',
  templateUrl: './pages.component.html',
  styleUrls: ['./pages.component.css']
})
export class PagesComponent implements OnInit {
  pages: Array<Page>;
  constructor(private pageService: PageService,private logger: LoggerService) { }

  ngOnInit() {
    this.pages=[
    
    ];
    this.getMyPages();
  }

  public getMyPages():void{
    this.pageService.getMyPages().subscribe(res=>{
      this.pages=res;
    }, err=>{
      this.logger.error("Error","Something bad happened.");
    })
  }
}
