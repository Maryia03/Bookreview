import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminService } from '../../core/services/admin.service';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})

export class AdminComponent implements OnInit{
  books: any[] = [];
  users: any[] = [];
  constructor(private adminService: AdminService) {}
  ngOnInit(): void{
    this.loadData();
  }

  loadData(){
    this.adminService.getAllBooks().subscribe(data => this.books = data);
    this.adminService.getAllUsers().subscribe(data => this.users = data);
  }

  deleteBook(id: number){
    this.adminService.deleteBook(id).subscribe(() => this.loadData());
  }

  blockUser(id: number){
    this.adminService.blockUser(id).subscribe(() => this.loadData());
  }
}
