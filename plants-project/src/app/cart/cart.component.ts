import { Component } from '@angular/core';
import { FlowerI } from '../modelo/flower.interface';
import { ApiServiceService } from '../api-service.service';
import { Route, Router } from '@angular/router';
import { orderI } from '../modelo/order.interface';
import { ConfirmationService, MessageService } from 'primeng/api';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
  providers: [ConfirmationService, MessageService]
})
export class CartComponent {

  flowers: FlowerI[] = [];
  order = {} as orderI;

  constructor(private api:ApiServiceService, private router:Router, private confirmationService: ConfirmationService, private messageService: MessageService){

  }

  ngOnInit(): void {
    this.flowers = this.getCartProducts();
  }

  deleteProduct(product: FlowerI) {
    let newObject  = window.sessionStorage.getItem('user_order');
    if(newObject) {
      let order = {} as orderI;
        order = JSON.parse(newObject) as orderI;
        
        let arr = {} as FlowerI[];
        arr = order.cart;
        let newArray = arr.filter((e, i) => e.id !== product.id);
        order.cart = newArray;
        window.sessionStorage.setItem('user_order', JSON.stringify(order));
        this.flowers = order.cart;
    }
      
  }

  getCartProducts(): FlowerI[] {
    let aux: FlowerI[] = [];
    let newObject  = window.sessionStorage.getItem('user_order');
    if(newObject) {
      let order = {} as orderI;
      order = JSON.parse(newObject) as orderI;
      aux = order.cart;
    }
    return aux;
  }

  getTotalPrice(){
    var price : number = 0;
    let total;
    let newObject  = window.sessionStorage.getItem('user_order');
    if(newObject){
      let order = {} as orderI;
      order = JSON.parse(newObject) as orderI;
      let arr = {} as FlowerI[];
      arr = order.cart;

      for (var i = 0; i< arr.length; i++)
      {
        let cantidad = arr[i].count;
        price += parseFloat(arr[i].price)*cantidad;
      }
      total = price.toFixed(2);
    }
    return total;
  }

  makePurchase(){
    let newObject  = window.sessionStorage.getItem('user_order');
    if(newObject){
      let order = {} as orderI;
      order = JSON.parse(newObject) as orderI;
      let arr = {} as FlowerI[];
      arr = order.cart;
      this.api.makePurchase(arr);
    }
    this.messageService.add({ severity: 'success', summary: 'Compra realizada', detail: 'Gracias por su compra.' });
  }
}
