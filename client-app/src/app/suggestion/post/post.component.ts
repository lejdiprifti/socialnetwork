import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '@ikubinfo/core/models/post';
import { PostService } from '@ikubinfo/core/services/post.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';

@Component({
  selector: 'ikubinfo-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  postForm: FormGroup;
  post: Post;

  constructor(private fb: FormBuilder, private active: ActivatedRoute, private postService: PostService, private logger: LoggerService, private router: Router) { }

  ngOnInit() {
    this.initializeForm();
    this.loadData();
  }

  loadData(): void {
    const id = this.active.snapshot.paramMap.get('id');
    if (id) {
      this.postService.getPostById(Number(id)).subscribe(data => {
          this.post = data;
          this.postForm.get('title').setValue(this.post.title);
          this.postForm.get('description').setValue(this.post.description);
        },err => {
          this.logger.error('Error', 'An error occurred.');
        });
    }
  }

  reset(): void {
    this.fillForm(this.post);
  }

  initializeForm(): void {
    this.postForm = this.fb.group({
      title: [''],
      description: ['', Validators.required]
    });
  }

  fillForm(data: Post = {}): void {
    this.postForm.get('title').setValue(data.title);
    this.postForm.get('description').setValue(data.description);
  }

  getData(): Post {
    return {
      title: this.postForm.get('title').value,
      description: this.postForm.get('description').value 
    }

  }

  submit(): void {
    if (this.post) {
      this.postService.editPost(this.post.id, this.getData()).subscribe(res => {
        this.logger.info('Success', 'Post was updated.');
        this.router.navigate(['suggestion/profile']);

      },err => {
        this.logger.error('Error', 'An error occurred.');
      });
    }
    else {
      this.postService.addPost(this.getData()).subscribe(res => {
        this.logger.info('Success', 'Post was successfully created!');
        this.router.navigate(['suggestions/dashboard']);
      },err => {
        this.logger.error('Error', 'An error occurred.');
      });

    }

  }

}
