import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
//import { HttpClientModule } from '@angular/common/http';


import { CalculatorComponent } from './calculator/calculator.component';
import { WordprocessComponent } from './wordprocess/wordprocess.component';

const routes: Routes = [
  {
    path:'calculator',
    component:CalculatorComponent
  },
  {
    path:'wordprocess',
    component:WordprocessComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)], /*[HttpClientModule],*/
  exports: [RouterModule]
})
export class AppRoutingModule { }
