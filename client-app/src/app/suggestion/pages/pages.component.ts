import { Component, OnInit } from '@angular/core';
import { PageService } from '@ikubinfo/core/services/page.service';
import { Page } from '@ikubinfo/core/models/page';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { Router } from '@angular/router';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';

@Component({
  selector: 'ikubinfo-pages',
  templateUrl: './pages.component.html',
  styleUrls: ['./pages.component.css']
})
export class PagesComponent implements OnInit {
  pages: Array<Page>;
  constructor(private confirmationService: ConfirmationService,
    private pageService: PageService,private logger: LoggerService,private router: Router) { }

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


  editPage(id: number): void{
    this.router.navigate(['suggestion/edit/page/'+id]);
  }

  deletePage(id: number): void{
    this.confirmationService.confirm({
      message: "Are you sure you want to delete this page?",
      header: "Delete Confirmation",
      icon: "pi pi-info-circle",
      accept: () => {
    this.pageService.deletePage(id).subscribe(res=>{
      this.logger.info("Info","Page was deleted succesfully.");
      this.getMyPages();
    },
    err=>{
      this.logger.error("Error","Something bad happened.")
    })
  }
});
  }
  createPage(): void{
    this.router.navigate(['suggestion/create/page']);
  }
}
