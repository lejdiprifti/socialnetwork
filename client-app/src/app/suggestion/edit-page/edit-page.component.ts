import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Page } from '@ikubinfo/core/models/page';
import { ActivatedRoute, Router } from '@angular/router';
import { PageService } from '@ikubinfo/core/services/page.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { ConfirmationService } from 'primeng/primeng';

@Component({
  selector: 'ikubinfo-edit-page',
  templateUrl: './edit-page.component.html',
  styleUrls: ['./edit-page.component.css']
})
export class EditPageComponent implements OnInit {
  
  pageForm: FormGroup;
  page: Page;
  constructor(
    private fb: FormBuilder, private active: ActivatedRoute,
     private pageService: PageService, private logger: LoggerService,
      private router: Router,private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.initializeForm();
    this.loadData();
  }

  loadData(): void {
    const id = this.active.snapshot.paramMap.get('id');
    if (id) {
      this.pageService.getPageById(Number(id)).subscribe(data => {
          this.page = data;
          this.pageForm .get('name').setValue(this.page.name);
          this.pageForm.get('description').setValue(this.page.bio);
        },err => {
          this.logger.error('Error', 'An error occurred.');
        });
    }
  }

  reset(): void {
    this.fillForm(this.page);
  }

  initializeForm(): void {
    this.pageForm = this.fb.group({
      name: ['' , Validators.required],
      description: ['', Validators.required]
    });
  }

  fillForm(data: Page = {}): void {
    this.pageForm.get('name').setValue(data.name);
    this.pageForm.get('description').setValue(data.bio);
  }

  getData(): Page {
    return {
      name: this.pageForm.get('name').value,
      bio: this.pageForm.get('description').value 
    }

  }

  submit(): void {
    this.confirmationService.confirm({
      message: "Do you want to save your data?",
      header: "Save Confirmation",
      icon: "pi pi-info-circle",
      accept: () => {
      this.pageService.editPage(this.page.id, this.getData()).subscribe(res => {
        this.logger.info('Success', 'Page was updated.');
        this.router.navigate(['suggestion/pages']);
      },err => {
        this.logger.error('Error', 'An error occurred.');
      });
    }
  });
  }
}
