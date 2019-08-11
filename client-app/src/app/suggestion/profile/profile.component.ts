import { Component, OnInit } from "@angular/core";
import { PostService } from "@ikubinfo/core/services/post.service";
import { LoggerService } from "@ikubinfo/core/utilities/logger.service";
import { ActivatedRoute } from "@angular/router";
import { Post } from "@ikubinfo/core/models/post";
import { AuthService } from "@ikubinfo/core/services/auth.service";
import { User } from "@ikubinfo/core/models/user";
import { DatePipe } from "@angular/common";
import { UserService } from "@ikubinfo/core/services/user.service";
@Component({
  selector: "ikubinfo-profile",
  templateUrl: "./profile.component.html",
  styleUrls: ["./profile.component.css"]
})
export class ProfileComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private logger: LoggerService,
    private active: ActivatedRoute,
    private datePipe: DatePipe,
    private userService: UserService
  ) {}
  user: User;
  posts: Array<Post>;
  friends: Array<User>;
  showPosts: boolean;
  showFriends: boolean;
  showLikes: boolean;
  ngOnInit() {
    this.posts = [];
    this.friends=[];
    this.loadMyPosts();
  }

  loadMyPosts(): void {
    const id = this.active.snapshot.paramMap.get("id") || this.authService.loggedUser.id;
    this.userService.getUserById(Number(id)).subscribe(
      res => {
        this.showPosts=true;
        this.showLikes=false;
        this.showFriends=false;
        this.user = res;
        this.posts=this.user.posts;
      },
      err => {
        this.logger.error("Error", "User not found.");
      }
    );
      
  }

  loadFriends(): void{
    this.showFriends=true;
    this.showLikes=false;
    this.showPosts=false;
    this.friends=this.user.friends;
  }

  loadLikes(): void {
    this.showFriends=false;
    this.showLikes=true;
    this.showPosts=false;
    this.posts=this.user.likes;
  }
  convertDate(post: Post): string {
    const dateString = post.date;
    const newDate = new Date(dateString);
    return this.datePipe.transform(newDate, "yyyy-MM-dd");
  }
}
