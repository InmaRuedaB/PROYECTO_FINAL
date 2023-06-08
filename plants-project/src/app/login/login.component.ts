import { Component } from '@angular/core';
import { ApiServiceService } from '../api-service.service';
import { UserI } from '../modelo/user.interface';
import { LoginI } from '../modelo/login.interface';
import { StorageService } from '../helpers/storage.service';
import { Router } from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import { Observable } from 'rxjs';
import { MessageService } from 'primeng/api';
import { orderI } from '../modelo/order.interface';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService]
})
export class LoginComponent {

  username: string = '';
  mail: string = '';
  phone: string = '';
  pass: string = '';
  repitPass: string = '';

  loginPhone: string = '';
  loginPass: string = '';

  user = {} as UserI;
  login = {} as LoginI;

  isSuccessful = false;
  isSignUpFailed = false;

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  order = {} as orderI;




  logedUser$!: Observable<UserI>;

  constructor(private api:ApiServiceService, private storageService: StorageService, 
    private router:Router, private messageService: MessageService){
  }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
    }
  }

  createUser() {
    this.user.name = this.username;
    this.user.phone = this.phone;
    this.user.mail = this.mail;
    this.user.password = this.pass;

    if(this.repitPass != this.pass) {
      this.repitPass = '';
      this.pass = '';
      this.messageService.add({ severity: 'warn', summary: 'Atención', detail: 'Las contraseñas no coinciden', sticky: true });
    }else {
      this.messageService.clear();
      this.api.createUser(this.user).subscribe({
        next: data => {
          console.log(data);
          this.messageService.add({ severity: 'success', summary: 'Usuario creado', sticky: true, icon:'pi-thumbs-up-fill', detail: 'Enhorabuena '+this.user.name+', ya eres usuario de Plantas A&N. Ahora inicia sesión para empezar a comprar' });
          this.router.navigate(['login'])
        },
        error: err => {
          const error : any = JSON.stringify(err);
          const responseObject = JSON.parse(error);
          const errorValue = responseObject.error.Error;
          console.log('El error: '+errorValue);
  
          this.messageService.add({ severity: 'warn', summary: 'Atención', detail: errorValue, sticky: true });
        }
      });
    }
    
    /*
    this.api.createUser(this.user).subscribe((response:any) => {
      
      console.log(response);
    });
    */


  }

  loginUser() {
    this.login.username = this.loginPhone;
    this.login.password = this.loginPass;

    this.api.login(this.login).subscribe({
      next: data => {
        this.storageService.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.storageService.getUser().roles;
        window.sessionStorage.setItem('user_phone', data.username);
        window.sessionStorage.setItem('user_token', data.accessToken);
        this.order.userPhone = data.userPhone;
        /*
        let newObject  = window.sessionStorage.getItem('user_order');
        
        if(newObject) {
          this.orderAux = JSON.parse(newObject) as orderI;
          console.log('Carrito en sessionStorage -->'+this.orderAux.userPhone);
        }
        */

        this.api.getUser().subscribe((response:UserI) => {
          window.sessionStorage.setItem('user_name', response.name);
          console.log('El usuario logado: '+ JSON.stringify(response));
        });
        
        
        this.router.navigate(['home']);
      },
      error: err => {
        this.errorMessage = err.status;
        this.isLoginFailed = true;
        console.log(this.errorMessage);
        this.isSignUpFailed = true;
      }
    });
  }

}
