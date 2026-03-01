import { Routes } from '@angular/router';
import { BookDetailsComponent } from './pages/book-details/book-details.component';
import { HomeComponent } from './pages/home/home.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { AdminComponent } from './pages/admin/admin.component';
import { LoginComponent } from './pages/login/login.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'book/:id', component: BookDetailsComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'admin', component: AdminComponent }
];
