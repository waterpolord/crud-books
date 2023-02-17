import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './shared/header/header.component';
import {RouterModule, RouterOutlet, Routes} from "@angular/router";
import { BookListComponent } from './books/pages/book-list/book-list.component';
import { BookFormComponent } from './books/pages/book-form/book-form.component';
import {FormsModule} from "@angular/forms";
import {BookService} from "./books/services/book.service";
import { HttpClientModule } from '@angular/common/http';

const routes:Routes = [
  {path: '', redirectTo: '/books', pathMatch: 'full'},
  {path: 'books', component: BookListComponent},
  {path: 'books/form', component: BookFormComponent},
  {path: 'books/form/:id', component: BookFormComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    BookListComponent,
    BookFormComponent
  ],
  imports: [
    BrowserModule,
    RouterOutlet,
    RouterModule.forRoot(routes),
    FormsModule,
    HttpClientModule
  ],
  providers: [BookService],
  bootstrap: [AppComponent]
})
export class AppModule { }
