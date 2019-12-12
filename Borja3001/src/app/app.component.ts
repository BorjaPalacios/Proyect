import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Borja3001';

  constructor(private router:Router){}

  Calculator(){
    this.router.navigate(["calculator"]); 
  }
}


