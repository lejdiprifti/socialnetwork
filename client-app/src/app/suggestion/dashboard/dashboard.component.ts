import { Component, OnInit } from '@angular/core';
import { PostService } from '@ikubinfo/core/services/post.service';
import { Post } from '@ikubinfo/core/models/post';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';

@Component({
  selector: 'ikubinfo-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  posts: Array<Post>;
  constructor(private postService: PostService,private logger: LoggerService) { }

  ngOnInit() {
    this.loadPosts();

  }

  loadPosts(){
    this.postService.getPosts().subscribe(res=>{
      this.posts=res;
    },
    err=>{
      this.logger.error("Error","Something bad happened.");
    })
  }
}
