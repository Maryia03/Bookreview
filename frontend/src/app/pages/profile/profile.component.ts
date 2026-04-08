import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '../../core/services/user.service';
import { RouterModule } from '@angular/router';
import { AuthService} from '../../core/services/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})

export class ProfileComponent implements OnInit{
  user: any;
  ratedBooks: any[] = [];
  comments: any[] = [];
  editing = false;

  constructor(private userService: UserService, private authService: AuthService,private cdr: ChangeDetectorRef) {}

  saveProfile(){
    this.userService.updateProfile({
      username: this.user.username,
      avatarUrl: this.user.avatarUrl
    }).subscribe({
      next: updated => {
        this.user = updated;
        this.editing = false;
        this.cdr.detectChanges();
        alert('Profile updated successfully');
      },
      error: err => {
        console.error(err);
        alert('Failed to update profile');
      }
    });
  }

  ngOnInit(): void{
    this.loadProfile();
  }

  deleteComment(id: number) {
    this.userService.deleteComment(id).subscribe(() => {
      this.comments = this.comments.filter(c => c.id !== id);
      this.cdr.detectChanges();
    });
  }

  loadProfile(){
      this.userService.getCurrentUser().subscribe(data => {
        this.user = data;
        this.cdr.detectChanges();
        this.loadUserActivity();
      });
    }

  loadUserActivity(){
      this.userService.getUserRatings().subscribe(data => {
        this.ratedBooks = data;
        this.cdr.detectChanges();
      });

      this.userService.getUserComments().subscribe(data =>{
        this.comments = data;
        this.cdr.detectChanges();
      });
    }

  deleteAccount(){
    if (confirm('Are you sure you want to delete your account? This action cannot be undone.')){
      this.userService.deleteCurrentUser().subscribe({
        next: () => {
          alert('Account deleted successfully');
          this.authService.logout();
          window.location.href = '/login';
        },
        error: err => {
          console.error(err);
          alert('Failed to delete account');
        }
      });
    }
  }
  }
