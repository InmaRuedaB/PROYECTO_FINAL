import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { FlowerListComponent } from './flower-list/flower-list.component';
import { TableModule } from 'primeng/table';
import {DataViewModule} from 'primeng/dataview';
import {ButtonModule} from 'primeng/button';
import {PanelModule} from 'primeng/panel';
import {DropdownModule} from 'primeng/dropdown';
import {DialogModule} from 'primeng/dialog';
import {InputTextModule} from 'primeng/inputtext';
import {RatingModule} from 'primeng/rating';
import {RippleModule} from 'primeng/ripple';
import {TagModule} from 'primeng/tag';
import { CarouselModule } from 'primeng/carousel';
import { SelectButtonModule } from 'primeng/selectbutton';
import { InputNumberModule } from 'primeng/inputnumber';
import { CartComponent } from './cart/cart.component';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import { ToastModule } from 'primeng/toast';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ImageModule } from 'primeng/image';

/*aquí añadimos todos los componentes que vaya a contener y usar el proyecto */
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    FlowerListComponent,
    CartComponent
    

  ],

  /*aquí añadimos los componentes de librerías*/
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    TableModule,
    DataViewModule,
    RatingModule,
    RippleModule,
    InputTextModule,
    DialogModule,
    DropdownModule,
    PanelModule,
    ButtonModule,
    TagModule,
    CarouselModule,
    SelectButtonModule,
    InputNumberModule,
    FormsModule,
    MessageModule,
    MessagesModule,
    ToastModule,
    BrowserAnimationsModule,
    ImageModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
