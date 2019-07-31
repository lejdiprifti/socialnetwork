import { Component, OnInit } from '@angular/core';
import { PostService } from '@ikubinfo/core/services/post.service';
import { Post } from '@ikubinfo/core/models/post';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { User } from '@ikubinfo/core/models/user';
import { AuthService } from '@ikubinfo/core/services/auth.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';

@Component({
  selector: 'ikubinfo-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  user:User;
  posts: Array<Post>;
  postForm: FormGroup;
  post: Post;
  constructor(private postService: PostService,private fb: FormBuilder,
    private logger: LoggerService,private authService: AuthService,
    private confirmationService: ConfirmationService,private datePipe: DatePipe) { }

  ngOnInit() {
    this.loadPosts();
    this.post={};
    this.user=this.authService.loggedUser;
    this.postForm=this.fb.group({
      description: ["",[Validators.minLength(1),Validators.required]]
    });
  
  }


  loadPosts(): void{
    this.postService.getPosts().subscribe(res=>{
      this.posts=res;
    },
    err=>{
      this.logger.error("Error","Something bad happened.");
    })
  }

  convertDate(post: Post): string {
      const dateString = post.date;
      const newDate= new Date(dateString);
      return  "Published on "+this.datePipe.transform(newDate, "yyyy-MM-dd hh:mm:ss");
  }

  getData(): Post{
    return{
      description: this.postForm.value.description,
    
    }
  }
  reset(): void {
    this.fillForm(this.post);
  }
  fillForm(data: Post = {}): void {
    this.postForm.get('description').setValue(data.description);
  }
  submit(): void {
    this.confirmationService.confirm({
      message: 'Do you want to post this record?',
      header: 'Post Confirmation',
      icon: 'pi pi-info-circle',
      accept: () => {
    this.postService.addPost(this.getData()).subscribe(res=>{
      this.loadPosts();
      this.reset();
      this.logger.success("Success","Post was added successfully!");
    },err=>{
      this.reset();
      this.logger.error("Error","Something bad happened.");
    });
  }
});
  }

  like(id: number): void {
    this.postService.like(id).subscribe(res=>{
      this.loadPosts();
      this.logger.success("Success","You liked the post.");
    },
    err=>{
      this.logger.error("Error","Something bad happened.");
    });
  }
}
