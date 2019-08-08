import { Component, OnInit, Input } from '@angular/core';
import { Chat } from '@ikubinfo/core/models/chat';
import { ChatService } from '@ikubinfo/core/services/chat.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '@ikubinfo/core/models/user';
import { AuthService } from '@ikubinfo/core/services/auth.service';

@Component({
  selector: 'ikubinfo-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {
  constructor(private chatService: ChatService,private authService: AuthService,
    private logger: LoggerService,private active: ActivatedRoute) { }
  chat: any;
  user:User;
  messages:Array<Chat>;
  ngOnInit() {
    this.chat={

    };
    this.user=this.authService.loggedUser;
    this.messages=[];
    this.getMessages();
  }

  getMessages(): void{
    const id=this.active.snapshot.paramMap.get('id');
    this.chatService.getMessages(Number(id)).subscribe(res=>{
      this.messages=res;
    },
    err=>{
      this.logger.error("Error","Something bad happened.");
    })
  }

  sendMessage(message: string): void{
    const id=this.active.snapshot.paramMap.get('id');
    this.chat.message=message;
    this.chatService.sendMessage(Number(id),this.chat).subscribe(res=>{
      this.getMessages();
      this.logger.info("Info","Message sent!");
    },
    err=>{
      this.logger.error("Error","Message could not be sent");
    })
  }

}
