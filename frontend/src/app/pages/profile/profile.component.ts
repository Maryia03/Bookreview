import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '../../core/services/user.service';


@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})

export class ProfileComponent implements OnInit{
  user: any;
  ratedBooks: any[] = [];
  comments: any[] = [];
  constructor(private userService: UserService) {}
  ngOnInit(): void{
    this.loadProfile();
  }

  loadProfile(){
    this.userService.getCurrentUser().subscribe(data => {
      this.user = data;
      this.loadUserActivity();
    });
  }

  loadUserActivity(){
    this.userService.getUserRatings().subscribe(data => this.ratedBooks = data);
    this.userService.getUserComments().subscribe(data => this.comments = data);
  }
}
