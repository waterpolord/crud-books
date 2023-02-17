import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Book} from "../models/books";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private url: string = 'http://localhost:8082/api/Books';
  private httpHeaders = new HttpHeaders({'Content-Type':'application/json'})

  constructor(private httpEndPoint: HttpClient) { }

  getBooks():Observable<Book[]> {
    //return of(CLIENTES);
    return this.httpEndPoint.get<Book[]>(this.url);
  }
  create(book:Book):Observable<Book>{
    return this.httpEndPoint.post<Book>(this.url,book,{headers:this.httpHeaders})
  }
  getBook(id:number): Observable<Book>{
    return this.httpEndPoint.get<Book>(`${this.url}/${id}`)
  }
  update(book: Book): Observable<Book>{
    return this.httpEndPoint.put<Book>(`${this.url}/${book.id}`,book,{headers:this.httpHeaders})
  }
  delete(id:any): Observable<Book>{
    return this.httpEndPoint.delete<Book>(`${this.url}/${id}`,{headers:this.httpHeaders})
  }

}
