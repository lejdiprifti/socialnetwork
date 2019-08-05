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


@NgModule({
    imports: [CommonsModule, SuggestionRoutingModule, LayoutModule, FormsModule],
    exports: [],
    declarations: [DashboardComponent, PostComponent, PostsComponent, FriendsComponent, RequestsComponent],
    providers: [DatePipe]
})
export class SuggestionModule { }
