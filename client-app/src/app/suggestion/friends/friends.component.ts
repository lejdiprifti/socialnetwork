import { Component, OnInit } from '@angular/core';
import { UserService } from '@ikubinfo/core/services/user.service';
import { User } from '@ikubinfo/core/models/user';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { FriendsService } from '@ikubinfo/core/services/friends.service';
import { AuthService } from '@ikubinfo/core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'ikubinfo-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit {
  
  constructor(private userService: UserService,private authService: AuthService,
    private logger: LoggerService,private friendsService: FriendsService,
    private router: Router) { }
  users: Array<User>;
  requests: Array<any>;
  user: User;
  ngOnInit() {
    this.loadUsers();
    this.loadRequests();
    this.user=this.authService.loggedUser;
  }

  loadUsers() : void{
    this.userService.getUsers().subscribe(res=>{
      this.users=res;
    },
    err=>{
      this.logger.error("Error","Something bad happened.");
      
    });
  }

  addFriend(user: User): void{
    this.userService.sendFriendRequest(user.id).subscribe(res=>{
      this.loadUsers();
      this.loadRequests();
      this.logger.info("Info","Request sent!");
    },
    err=>{
      this.logger.error("Error","Request coud not be sent!");
    });
  }

  loadRequests(): void {
    this.friendsService.getRequests().subscribe(res=>{
     this.requests=res;
    },
    err=>{
      this.logger.error("Error","Something bad happened.");
    })
  }

  cancelRequest(id: number): void{
    this.friendsService.cancelRequest(id).subscribe(res=>{
      this.loadRequests();
      this.loadUsers();
      this.logger.warning("Warning","You canceled the request.");
    },
    err=>{
      this.logger.error("Error","Something bad happened.");
    });
  }

  openProfile(id: number): void{
    this.router.navigate(['suggestion/profile/'+id]);
  }
}
