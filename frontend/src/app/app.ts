import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './common/navbar/navbar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent],
    template: `
      <app-navbar></app-navbar>
      <router-outlet></router-outlet>
    ` ,
  styleUrl: './app.scss'
})
export class App {}
