import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { UserService, UserDTO } from '../../core/services/user.service';
import { CommentDTO, RatingDTO } from '../../core/services/book.service';

@Component({
  selector: 'app-other-profile',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './other-profile.component.html',
  styleUrls: ['./other-profile.component.scss']
})

export class OtherProfileComponent implements OnInit{
  user: UserDTO | null = null;
  currentUserId: number | null = null;

  constructor(private route: ActivatedRoute,private router: Router, private userService: UserService,private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
      this.userService.getCurrentUser().subscribe({
        next: u => {
          this.currentUserId = u.id;
          this.loadProfile();
        },
        error: err => {
          console.error('Failed to get current user', err);
          this.loadProfile();
        }
      });
    }

    loadProfile(): void {
      const userIdStr = this.route.snapshot.paramMap.get('id');
      if (userIdStr && !isNaN(+userIdStr)){
        const userId = +userIdStr;
        if (this.currentUserId && userId === this.currentUserId){
          this.router.navigate(['/profile']);
          return;
        }
        this.userService.getUserById(userId).subscribe({
          next: u => {
            this.user = u;
            this.cdr.detectChanges();
          },
          error: err => {
            console.error('Failed to load user', err);
            this.router.navigate(['/']);
          }
        });
      } else {
        console.error('Invalid user ID in route:', userIdStr);
        this.router.navigate(['/']);
      }
    }

    getUserLink(userId: number): string[]{
      if (this.currentUserId && userId === this.currentUserId){
        return ['/profile'];
      }
      return ['/user', userId.toString()];
    }
  }
