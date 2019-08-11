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
    private userService: UserService
  ) {
    
  }
  user: User;
  posts: Array<Post>;
  friends: Array<User>;
  showPosts: boolean;
  showFriends: boolean;
  showLikes: boolean;
  loggedUser: User;
  ngOnInit() {
    this.posts = [];
    this.friends=[];
    this.user={};
    this.loadMyPosts();
    this.loggedUser=this.authService.loggedUser;
  }
  id = this.active.snapshot.paramMap.get("id") || this.authService.loggedUser.id;

  loadMyPosts(): void {
    this.userService.getUserById(Number(this.id)).subscribe(
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

  addFriend(user:User): void{
    this.userService.sendFriendRequest(user.id).subscribe(res=>{
      this.logger.info("Info","Request sent!");
    },
    err=>{
      this.logger.error("Error","Request could not be sent.");
    });
  }


}
