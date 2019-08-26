import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdminGuard } from '@ikubinfo/core/guards/admin-guard';
import { DashboardComponent } from '@ikubinfo/suggestion/dashboard/dashboard.component';
import { PostComponent } from '@ikubinfo/suggestion/post/post.component';
import { PostsComponent } from '@ikubinfo/suggestion/posts/posts.component';
import { FullComponent } from '@ikubinfo/layout/full/full.component';
import { FriendsComponent } from './friends/friends.component';
import { UserGuard } from '@ikubinfo/core/guards/user-guard';
import { RequestsComponent } from './requests/requests.component';
import { MessagesComponent } from './messages/messages.component';
import { ChatComponent } from './chat/chat.component';
import { ProfileComponent } from './profile/profile.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { PagesComponent } from './pages/pages.component';
import { EditPageComponent } from './edit-page/edit-page.component';

const suggestionRoutes: Routes = [
    {
        path: '',
        component: FullComponent,
        children: [
            { path: 'dashboard', component: DashboardComponent },
            { path: 'posts', component: PostsComponent, canActivate: [AdminGuard] },
            { path: 'post', component: PostComponent, canActivate: [AdminGuard] },
            { path: 'post/:id', component: PostComponent, canActivate: [AdminGuard] },
            { path: 'discover', component: FriendsComponent , canActivate: [UserGuard]},
            { path: 'messages/:id', component: MessagesComponent,canActivate:[UserGuard]},
            { path: 'chat',component: ChatComponent, canActivate:[UserGuard]},
            { path: 'requests', component: RequestsComponent, canActivate: [UserGuard]},
            { path: 'profile/:id', component: ProfileComponent, canActivate: [UserGuard]},
            { path: 'profile', component: ProfileComponent, canActivate: [UserGuard]},
            {path: 'edit/post/:id', component: PostComponent},
            {path: 'pages', component: PagesComponent, canActivate:[UserGuard]},
            {path: 'edit/page/:id',component: EditPageComponent,canActivate: [UserGuard]},
            {path: 'create/page',component: EditPageComponent,canActivate: [UserGuard]},
            { path: 'edit', component: EditUserComponent,canActivate:[UserGuard]},
            { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(suggestionRoutes)
    ],
    exports: [
        RouterModule
    ]
})
export class SuggestionRoutingModule { }
