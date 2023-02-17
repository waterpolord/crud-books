import { Component, OnInit } from '@angular/core';
import {Book} from "../../models/books";
import swal from 'sweetalert2'
import {BookService} from "../../services/book.service";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  books:Book[] = [
    {
      id:1,
      name:"48 leyes del poder",
      auth:"Robert Greene",
      date:"2022-06-13",
      gender:"Superación",
      isbn:"4848484848484"
    }
  ];

  constructor(private booksService:BookService) { }

  ngOnInit(): void {
    this.booksService.getBooks().subscribe(
      (Response) => {
        this.books = Response
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

  delete(book:Book){
    const swalWithBootstrapButtons = swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
      title: 'Eliminar',
      text: "Esta seguro que desea eliminar el libro "+book.name,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar ',
      cancelButtonText: ' No, cancelar',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.booksService.delete(book.id).subscribe(
          _ => {
            this.books = this.books.filter(cli => cli !== book)
            swalWithBootstrapButtons.fire(
              'Eliminado',
              book.name+' ha sido eliminado con exito',
              'success'
            )},
          error => {
            console.log(error)
            swal.fire(
              'Error '+error.status,
              error.message + " " + error.error.message,
              'error'
            );
          }

        )

      }
    })
  }

}
