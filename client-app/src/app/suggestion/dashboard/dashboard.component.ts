import { Component, OnInit } from '@angular/core';
import { PostService } from '@ikubinfo/core/services/post.service';
import { Post } from '@ikubinfo/core/models/post';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { User } from '@ikubinfo/core/models/user';
import { AuthService } from '@ikubinfo/core/services/auth.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';
import { LikedPost } from '@ikubinfo/core/models/likedpost';
import { ActivatedRoute, Router } from '@angular/router';
import { PageService } from '@ikubinfo/core/services/page.service';
import { Page } from '@ikubinfo/core/models/page';
import { MenuItem } from 'primeng/primeng';

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
  items:MenuItem[];
  pages: Array<Page>;
  constructor(private postService: PostService,private fb: FormBuilder,
    private logger: LoggerService,private authService: AuthService,
    private router: Router,private pageService:PageService,
    private confirmationService: ConfirmationService,private datePipe: DatePipe) { }

  ngOnInit() {
    this.pages=[];
    this.items=[];
    this.loadPosts();
    this.getMyPages();
    
    this.post={};
    this.user=this.authService.loggedUser;
    this.postForm=this.fb.group({
      description: ["",[Validators.nullValidator,Validators.required]],
      title: [""]
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
      return  this.datePipe.transform(newDate, "yyyy-MM-dd hh:mm:ss");
  }

  getData(): Post{
    return{
      description: this.postForm.value.description,
      title: this.postForm.value.title,
    }
  }
  reset(): void {
    this.fillForm(this.post);
  }
  fillForm(data: Post = {}): void {
    this.postForm.get('description').setValue(data.description);
    this.postForm.get('title').setValue(data.title);
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

  isLiked(post: Post): boolean{
    if(post.likes != null){
    for (let like of post.likes){
      if(like.user.id == this.user.id && like.flag === true){
    
     return true;
    }
    }
  return false;
  
  }
  }

  unlike(id: number): void{
     this.postService.unlike(id).subscribe(res=>{
       this.loadPosts();
       this.logger.info("Info","You unliked the post.");
     },
     err=>{
       this.logger.error("Error","Something bad happened.");
     })
  }

  countLength(post: Post): number{
    length=0;
    if (post.likes != null){
      for (let like of post.likes){
        if(like.flag === true){
          length++;
      }
    }
  }
  return length;
 }

 openProfile(id:number): void{
   this.router.navigate(['suggestion/profile/'+id]); 
 }

 getMyPages(): void{
   this.pageService.getMyPages().subscribe(res=>{
    this.pages=res;
    for (let i=0;i<this.pages.length;i++){
      this.items.push({label: this.pages[i].name , icon: 'fa fa-caret-square-o-right' , command: () => {
        this.postAsPage(this.pages[i].id,this.getData());
      }});
    }
   },
   err=>{
     this.logger.error("Error","Something bad happened.");
   })
 }

 postAsPage(id: number,post: Post): void{
   this.postService.postAsPage(id,post).subscribe(res=>{
     this.loadPosts();
     this.reset();
     this.logger.success("Success","Post was added succesfully.");
   },
   err=>{
     this.logger.error("Error","Something bad happened.");
   })
 }
}