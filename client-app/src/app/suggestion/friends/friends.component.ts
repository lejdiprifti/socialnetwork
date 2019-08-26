import { Component, OnInit } from '@angular/core';
import { UserService } from '@ikubinfo/core/services/user.service';
import { User } from '@ikubinfo/core/models/user';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { FriendsService } from '@ikubinfo/core/services/friends.service';
import { AuthService } from '@ikubinfo/core/services/auth.service';
import { Router } from '@angular/router';
import { PageService } from '@ikubinfo/core/services/page.service';
import { Page } from '@ikubinfo/core/models/page';
import { MenuItem } from 'primeng/components/common/menuitem';

@Component({
  selector: 'ikubinfo-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit {
  
  constructor(private userService: UserService,private authService: AuthService,
    private logger: LoggerService,private friendsService: FriendsService,
    private router: Router, private pageService: PageService) { }
  users: Array<User>;
  requests: Array<any>;
  user: User;
  pages: Array<Page>;
  items: MenuItem[];
  showReq: boolean = true;
  showPages: boolean= true;
  showPeople: boolean= true;
  ngOnInit() {
    this.loadUsers();
    this.loadRequests();
    this.user=this.authService.loggedUser;
    this.pages=[];
    this.getAllPages();
    this.items = [ 
            {label: 'All', icon: 'fa fa-fw fa-bar-chart' , command: ()=>{this.displayAll()}},
            {label: 'People', icon: 'fa fa-handshake-o', command: () => {this.displayPeople()}},
            {label: 'Pages', icon: 'fa fa-fw fa-book', command: () =>{this.displayPages()}},
            {label: 'Sent Requests', icon: 'fa fa-mail-forward' , command: () =>{this.displayReq()}}
        ];
    
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


  getAllPages(): void{
    this.pageService.getAllPages().subscribe(res=>{
      this.pages=res;
    },
    err=>{
      this.logger.error("Error","Something bad happened.");
    })
  }

  likePage(id: number): void{
    this.pageService.likePage(id).subscribe(res=>{
      this.logger.success("Success","Page was liked successfully.");
      this.getAllPages();
    },
    err=>{
      this.logger.error("Error","Something bad happened.");
    })
  }

  isLiked(page: Page): boolean{
    for (let i=0;i<page.followers.length;i++){
      if (page.followers[i].user.id == this.user.id && page.followers[i].flag == true){
        return true;
      }
    }
    return false;
  }

  unlikePage(id: number): void{
    this.pageService.unlikePage(id).subscribe(res=>{
      this.logger.info("Info","Page was unliked successfully.");
      this.getAllPages();
    },
    err=>{
      this.logger.error("Error","Something bad happened.");
    })
  }

  displayPeople(): void{
    this.showPeople = true;
    this.showPages= false;
    this.showReq = false;
  }

  displayPages() : void{
    this.showPages=true;
    this.showPeople= false;
    this.showReq= false;
  }

  displayReq() : void{
    this.showPages= false;
    this.showPeople=false;
    this.showReq = true;
  }

  displayAll(): void{
    this.showPages=true;
    this.showPeople=true;
    this.showReq= true;
  }
}
