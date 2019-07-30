import { Component, OnInit } from '@angular/core';
import { PostService } from '@ikubinfo/core/services/post.service';
import { Post } from '@ikubinfo/core/models/post';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { User } from '@ikubinfo/core/models/user';
import { AuthService } from '@ikubinfo/core/services/auth.service';
import { FormGroup, FormBuilder, Validator, Validators } from '@angular/forms';

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
    private logger: LoggerService,private authService: AuthService) { }

  ngOnInit() {
    this.loadPosts();
    this.post={};
    this.user=this.authService.loggedUser;
    this.postForm=this.fb.group({
      description: ["",Validators.required]
    });
  }

  loadPosts(){
    this.postService.getPosts().subscribe(res=>{
      this.posts=res;
    },
    err=>{
      this.logger.error("Error","Something bad happened.");
    })
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
  submit(){
    this.postService.addPost(this.getData()).subscribe(res=>{
      this.loadPosts();
      this.reset();
      this.logger.success("Success","Post was added successfully!");
    },err=>{
      this.logger.error("Error","Something bad happened.");
    });
  }
}
