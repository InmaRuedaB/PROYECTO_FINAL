import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FlowerI } from './modelo/flower.interface';
import { Observable } from 'rxjs';
import { UserI } from './modelo/user.interface';
import { LoginI } from './modelo/login.interface';
import { cartI } from './modelo/cart.interface';
 

@Injectable({
  providedIn: 'root'
})
export class ApiServiceService implements OnInit{

  url:string = "http://localhost:8080/api/";
  loginUrl:string = "http://localhost:8080/auth/login";
  headers = { 'content-type': 'application/json'};

  flowers$!: Observable<FlowerI[]>;

  constructor(private http:HttpClient) { }

  ngOnInit(): void {
    this.getAllFlowers();
  }

  getAllFlowers():Observable<FlowerI[]>{
    let direction = this.url + "plant";
    //this.flowers$ = this.http.get<FlowerI[]>(direction);
    return this.http.get<FlowerI[]>(direction);
  }

  getAllFlowersByCategory(s: string):Observable<FlowerI[]> {
    let direction = this.url + "plant/category?categoryName="+s;
    console.log(this.http.get<FlowerI[]>(direction));
    this.flowers$ = this.http.get<FlowerI[]>(direction);
    return this.http.get<FlowerI[]>(direction);
  }

  getFlowers():Observable<FlowerI[]>{
    return this.flowers$;
  }
 
  createUser(user: UserI): Observable<any> {
    let direction = this.url + "user";
    return this.http.post<any>(direction, JSON.stringify(user),{'headers':this.headers});
  }

  login(login: LoginI): Observable<any> {
    return this.http.post<any>(this.loginUrl, JSON.stringify(login),{'headers':this.headers});
  }

  getUser(): Observable<UserI> {
    let direction = this.url+'user/'+window.sessionStorage.getItem("user_phone");
    console.log('URL para obtener usuario: '+direction);
    return this.http.get<UserI>(direction);
  }

  makePurchase(cart: cartI) {
    let direction = this.url+'purchase';

    try {
      const response = fetch(direction, {
        method: 'POST',
        body: JSON.stringify(cart),
        headers: {
          'Content-Type': 'application/json',
          Accept: 'application/json',
        },
      });
    }catch (error) {
        console.log(error);
    }

  }

}
