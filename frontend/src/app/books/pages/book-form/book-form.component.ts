import { Component, OnInit } from '@angular/core';
import {Book} from "../../models/books";
import {ActivatedRoute, Router} from "@angular/router";
import {BookService} from "../../services/book.service";
import swal from "sweetalert2";

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {

  public book:Book = new Book();

  constructor(private router:Router, private activatedRoute:ActivatedRoute, private bookService:BookService) { }

  ngOnInit(): void {
    this.loadBook();
  }

  public loadBook():void{
    this.activatedRoute.params.subscribe(
      params => {
        let id = params['id']
        if(id){
          this.bookService.getBook(id).subscribe(
            response => {
              console.log(response)
              this.book = response
            },
            error => {
              console.log(error)
              swal.fire(
                'Error '+error.status,
                error.message + " " + error.error.message,
                'error'
              );
            }
          );

        }
      }
    );

  }

  public create():void{
    const swalWithBootstrapButtons = swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    this.bookService.create(this.book).subscribe(
      Response => {
        swalWithBootstrapButtons.fire(
          'Libro creado',
          Response.name+' ha sido creado con exito',
          'success'
        );
      },
      error => {
        swal.fire(
          'Error '+error.status,
          error.message + " " + error.error.message,
          'error'
        );
      }
    );

  }

  update():void{
    const swalWithBootstrapButtons = swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    });
    this.bookService.update(this.book).subscribe(
      (Response:any) => {
        swalWithBootstrapButtons.fire(
          'Actualizado',
          Response.message,
          'success'
        );
      },
      error => {
        swal.fire(
          'Error '+error.status,
          error.message + " " + error.error.message,
          'error'
        );
      }
    );
  }

}
