import { Component, OnInit } from "@angular/core";
import { PostService } from "@ikubinfo/core/services/post.service";
import { LoggerService } from "@ikubinfo/core/utilities/logger.service";
import { ActivatedRoute } from "@angular/router";
import { Post } from "@ikubinfo/core/models/post";
import { AuthService } from "@ikubinfo/core/services/auth.service";
import { User } from '@ikubinfo/core/models/user';
import { DatePipe } from '@angular/common';
@Component({
  selector: "ikubinfo-profile",
  templateUrl: "./profile.component.html",
  styleUrls: ["./profile.component.css"]
})
export class ProfileComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private postService: PostService,
    private logger: LoggerService,
    private active: ActivatedRoute,
    private datePipe: DatePipe
  ) {}
  user:User;
  posts: Array<Post>;
  ngOnInit() {
    this.posts = [];
    this.user=this.authService.loggedUser;
    this.loadMyPosts();
  }

  loadMyPosts() {
    const id = this.active.snapshot.paramMap.get("id");
    this.postService.getMyPosts(Number(id)).subscribe(
      res => {
        this.posts = res;
      },
      err => {
        this.logger.error("Error", "Something bad happened.");
      }
    );
  }

  convertDate(post: Post): string {
    const dateString = post.date;
    const newDate= new Date(dateString);
    return  this.datePipe.transform(newDate, "yyyy-MM-dd");
}


}
