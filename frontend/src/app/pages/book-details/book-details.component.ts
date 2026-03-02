import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { BookService } from '../../core/services/book.service';
import { BookDetails, CommentDTO } from '../../core/models/book-details.model';

@Component({
  selector: 'app-book-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss']
})

export class BookDetailsComponent implements OnInit{
  book!: BookDetails;
    comments: CommentDTO[] = [];
    page = 0;
    sortBy = 'new';
    loading = false;

    constructor(
      private route: ActivatedRoute,
      private bookService: BookService){}

    ngOnInit(): void {
      const id = Number(this.route.snapshot.paramMap.get('id'));
      this.loadBook(id);
    }

    loadBook(id: number){
      this.bookService.getBookDetails(id, this.sortBy).subscribe(data =>{
          this.book = data;
          this.comments = data.comments;
          this.page = data.currentPage;
        });
    }

    loadMore(){
      const id = this.book.id;
      this.loading = true;
      this.bookService.getComments(id, this.page + 1, 10, this.sortBy)
        .subscribe(res => {
          this.comments = [...this.comments, ...res.content];
          this.page = res.number;
          this.loading = false;
        });
    }

    changeSort(sort: string){
      this.sortBy = sort;
      this.page = 0;
      this.loadBook(this.book.id);
    }
  }

