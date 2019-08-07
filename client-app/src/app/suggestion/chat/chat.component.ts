import { Component, OnInit } from '@angular/core';
import { ChatService } from '@ikubinfo/core/services/chat.service';
import { FriendsService } from '@ikubinfo/core/services/friends.service';
import { User } from '@ikubinfo/core/models/user';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { Router } from '@angular/router';

@Component({
  selector: 'ikubinfo-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  friends: Array<User>;
  constructor(private chatService: ChatService,private router: Router,
    private friendsService: FriendsService,private logger: LoggerService) { }

  ngOnInit() {
    this.loadFriends();
  }

  loadFriends(): void {
    this.friendsService.getFriends().subscribe(res=>{
      this.friends=res;
    },
    err=>{
      this.logger.error("Error","Something bad happened.");
    })
  }

  openConversation(id: number): void{
    this.router.navigate(['suggestion/messages/'+id]);
  }
}
