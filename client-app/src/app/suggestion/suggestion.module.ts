import { CommonsModule } from '@ikubinfo/commons/commons.module';

import { NgModule } from '@angular/core';

import { PostComponent } from '@ikubinfo/suggestion/post/post.component';
import { PostsComponent } from '@ikubinfo/suggestion/posts/posts.component';
import { DashboardComponent } from '@ikubinfo/suggestion/dashboard/dashboard.component';
import { SuggestionRoutingModule } from '@ikubinfo/suggestion/suggestion-routing.module';
import { LayoutModule } from '@ikubinfo/layout/layout.module';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { FriendsComponent } from './friends/friends.component';
import { RequestsComponent } from './requests/requests.component';
import { MessagesComponent } from './messages/messages.component';
import { ChatComponent } from './chat/chat.component';
import { ProfileComponent } from './profile/profile.component';
import { DateAgoPipe } from '@ikubinfo/pipes/date-ago.pipe';
import { EditUserComponent } from './edit-user/edit-user.component';
import { PagesComponent } from './pages/pages.component';
import {CardModule} from 'primeng/card';
import { EditPageComponent } from './edit-page/edit-page.component';


@NgModule({
    imports: [CardModule,CommonsModule, SuggestionRoutingModule, LayoutModule, FormsModule],
    exports: [],
    declarations: [DateAgoPipe,DashboardComponent, PostComponent, PostsComponent, FriendsComponent, RequestsComponent, MessagesComponent, ChatComponent, ProfileComponent, EditUserComponent, PagesComponent, EditPageComponent],
    providers: [DatePipe]
})
export class SuggestionModule { }
