import { Component, OnInit, Input } from '@angular/core';
import { Chat } from '@ikubinfo/core/models/chat';
import { ChatService } from '@ikubinfo/core/services/chat.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'ikubinfo-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {
  constructor(private chatService: ChatService,private logger: LoggerService,private active: ActivatedRoute) { }
  chat: any;
  messages:Array<Chat>;
  ngOnInit() {
    this.chat={

    };
    this.messages=[];
    this.getMessages();
  }

  getMessages(): void{
    const id=this.active.snapshot.paramMap.get('id');
    this.chatService.getMessages(Number(id)).subscribe(res=>{
      this.messages=res;
      this.logger.info("Info","Loaded Messages!");
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
