import { Component, NgModule } from '@angular/core';
import { ApiServiceService } from '../api-service.service';
import { Route, Router } from '@angular/router';
import { FlowerI } from '../modelo/flower.interface';
import { orderI } from '../modelo/order.interface';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-flower-list',
  templateUrl: './flower-list.component.html',
  styleUrls: ['./flower-list.component.css'],
  providers: [MessageService]
})


export class FlowerListComponent {

  flowers: FlowerI[] = [];
  flowerType = 'Clasicas';
  selectedCategory: any = '';

  constructor(private api:ApiServiceService, private router:Router, private messageService: MessageService){

  }

  ngOnInit(): void {
  
    this.api.getAllFlowers().subscribe(data => {
      this.flowers = data;
    })

  }

  changeCategory(category: string):void {
    console.log('Categoría seleccionada: '+category);
    if(category === 'Todas') {
      this.api.getAllFlowers().subscribe(data => {
        this.flowers = data;
      });
    }else {
      this.flowerType = category;
      this.api.getAllFlowersByCategory(category).subscribe(data => {
        this.flowers = data;
      });
    }
    
  }

  getSeverity(stock: number): string {

    if(stock <= 0) {
      return 'danger';
    }else if (stock > 0 && stock <=5) {
      return 'warning';
    }else {
      return 'success';
    }
  }

  getStatusStock(stock: number): string {
    if(stock <= 0) {
      return 'Sin stock';
    }else if (stock > 0 && stock <=5) {
      return 'Stock bajo';
    }else {
      return 'En Stock';
    }
  }

  addToCart(product: FlowerI) {
    if(!window.sessionStorage.getItem('user_phone')) {
      this.messageService.add({ severity: 'warn', summary: 'Atención', detail: 'Inicia sesión para empezar a comprar' });
    }else {
      if(product.count == null || product.count <1) {
        this.messageService.add({ severity: 'warn', summary: 'Atención', detail: 'Debe indicar un número de artículos antes de añadir al carrito' });
      }else {
        if(!window.sessionStorage.getItem('user_order')) {
        
          let arr: FlowerI[] = [product];
          let order: orderI = {
            cart: arr,
            userPhone: String(window.sessionStorage.getItem('user_phone')),
          }
          window.sessionStorage.setItem('user_order', JSON.stringify(order));
        }else {
          let newObject  = window.sessionStorage.getItem('user_order');
    
          if(newObject) {
            let order = {} as orderI;
            order = JSON.parse(newObject) as orderI;
            
            let arr = {} as FlowerI[];
            arr = order.cart;
    
            let index = arr.findIndex((obj => obj.id == product.id));
            
            if(index != null && index != -1) {
              arr[index].count = arr[index].count+product.count;
            }else {
              arr.push(product);
              order.cart = arr;
            }
            window.sessionStorage.removeItem('user_order');
            window.sessionStorage.setItem('user_order', JSON.stringify(order));
          }
        }
        this.messageService.add({ severity: 'success', summary: 'Añadido', detail: 'Se ha añadido '+product.count+' '+product.name+ ' al carrito' });
      }
    }

    

    
  }

}
