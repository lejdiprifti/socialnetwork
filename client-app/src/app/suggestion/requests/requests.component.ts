import { Component, OnInit } from '@angular/core';
import { FriendsService } from '@ikubinfo/core/services/friends.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { AuthService } from '@ikubinfo/core/services/auth.service';

@Component({
  selector: 'ikubinfo-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {
  requests: Array<Object>;
  constructor(private friendsService: FriendsService,private logger: LoggerService,private authService:AuthService) { }

  ngOnInit() {
    this.loadRequests();
  }

  loadRequests():void {
    this.friendsService.getMyRequests(this.authService.loggedUser.id).subscribe(res=>{
      this.requests=res;
    },
    err=>{
      this.logger.error("Error","Something bad happened.");
    });
  }

  accept(id:number): void {
    this.friendsService.acceptFriendRequest(id).subscribe(res=>{
      this.loadRequests();
      this.logger.success("Success","You are friends now!");
    },
    err=>{
      this.logger.error("Error","Could not accept friend request!");
    });
  }

  decline(id: number): void {
    this.friendsService.rejectFriendRequest(id).subscribe(res=>{
      this.loadRequests();
      this.logger.info("Info","You declined the request!");
    },
    err=>{
      this.logger.error("Error","Could not decline friend request!");
    });
  }
}
