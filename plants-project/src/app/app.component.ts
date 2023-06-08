import { Component } from '@angular/core';

/**indicamos el root de la aplicación, la raíz, en este caso sería el html */
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'plants-project';
}
