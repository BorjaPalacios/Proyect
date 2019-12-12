import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../Service/service.service';
import { CalculatorRequest } from '../pojos/CalculatorRequest';
import { CalculatorResponse } from '../pojos/CalculatorResponse';
import { CalculatorRecordPojo } from '../pojos/CalculatorRecordPojo';
import { CalculatorSingleRequest } from '../pojos/CalculatorSingleRequest';
import { SingletonResponse } from '../pojos/SingletonResponse';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent implements OnInit {

  valor: string;
  operator: string;
  operation: string = "";
  operand1: string;
  operand2: string;
  split: string[];
  response: CalculatorResponse;
  remove: boolean = false;
  records: CalculatorRecordPojo[];
  filters: string[]=['+','-','x','/','log','%','sin','cos','tan','!','√','^'];
  igual: boolean = false;
  limit: number = 10;
  offset: number = 0;
  singleton: SingletonResponse;
  operaciones: number;
  typeOperator:boolean = false;
  type: string;

  constructor(private router: Router, private service: ServiceService) { }

  ngOnInit() {
    this.refreshRecords();
    this.setSingleton();
  }

  escribir(){
    this.operation = this.operation.concat(this.valor.toString());
  }

  setValor(numero){
    if(this.remove == true){
      this.operation = "";
      this.remove = false; 
    }
    if(this.igual == true){
      this.operation = ""; 
      this.igual = false;
    }
    this.valor = numero;
    this.escribir();
  }

  setOperator(operator){
    if(this.operation != ""){
      this.valor = operator;
      if(["sin","cos","tan","!"].includes(this.valor)){
        this.operand1 = this.operation;
        this.operator = this.valor;
        this.escribir();
        this.sendSingleOperation();
        setTimeout(()=>{
          this.operand1 = null;
          this.operator = null;
          this.remove = false;
          this.igual = true;
        })
      }else if(this.operand1 == null){
        if(this.igual == true) this.igual = false;
        this.operand1 = this.operation;
        this.operator = this.valor;
        this.escribir();
      }else{
        if(this.valor == "="){
        this.sendOperation();
        setTimeout(()=>{
          this.operand1 = null;
          this.operator = null;
          this.remove = false;
          this.igual = true;
        },100)
        }else{
        this.sendOperation()
        setTimeout(()=>{
          if(this.igual == true) this.igual = false;
            this.remove = false;
            this.operand1 = this.operation;
            this.operator = this.valor;
            this.escribir();         
        },100);      
        }
      }
    }
  }

  sendOperation(){
    this.split = this.operation.toString().split(this.operator.toString());
    this.operand2 = this.split[this.split.length-1];
    let request = new CalculatorRequest(this.operand1, this.operand2);
    this.doOperation(request);
  }

  sendSingleOperation(){
    let request = new CalculatorSingleRequest(this.operand1);
    this.doOperation(request);
  }

  removeValue(){
    this.operation = this.operation.substring(0,this.operation.length-1);
  }

  deleteOperation(){
    this.operation = "";
    this.operand1 = null;
    this.valor = null;
    this.operator = null;
    this.operand2 = null;
  }

  doOperation(request){
    switch(this.operator){
      case "+":
        this.service.suma(request).subscribe((data:any)=>{
          this.response = data;
          this.operation = this.response.result.toString();
          this.remove = true;
        });
        break;
      case "-":
          this.service.resta(request).subscribe((data:any)=>{
            this.response = data;
            this.operation = this.response.result.toString();
            this.remove = true;
          });
          break;
      case "x":
        this.service.producto(request).subscribe((data:any)=>{
          this.response = data;
          this.operation = this.response.result.toString();
          this.remove = true;
        });
          break;
      case "/":
        this.service.division(request).subscribe((data:any)=>{
          this.response = data;
          this.operation = this.response.result.toString();
          this.remove = true;
        });
          break;
      case "log":
          this.service.logaritmo(request).subscribe((data:any)=>{
            this.response = data;
            this.operation = this.response.result.toString();
            this.remove = true;
          });
          break;
      case "%":
        this.service.porcentaje(request).subscribe((data:any)=>{
          this.response = data;
          this.operation = this.response.result.toString();
          this.remove = true;
        });
        break;
      case "sin":
          this.service.seno(request).subscribe((data:any)=>{
            this.response = data;
            this.operation = this.response.result.toString();
            this.remove = true;
          });
          break;
      case "cos":
          this.service.coseno(request).subscribe((data:any)=>{
            this.response = data;
            this.operation = this.response.result.toString();
            this.remove = true;
          });
          break;
      case "tan":
          this.service.tangente(request).subscribe((data:any)=>{
            this.response = data;
            this.operation = this.response.result.toString();
            this.remove = true;
          });
          break;
      case "√":
          this.service.raiz(request).subscribe((data:any)=>{
            this.response = data;
            this.operation = this.response.result.toString();
            this.remove = true;
          });
          break;
      case "!":
          this.service.factorial(request).subscribe((data:any)=>{
            this.response = data;
            this.operation = this.response.result.toString();
            this.remove = true;
          });
          break;
      case "^":
          this.service.potencia(request).subscribe((data:any)=>{
            this.response = data;
            this.operation = this.response.result.toString();
            this.remove = true;
          });
          break;
    }

    this.refreshRecords();
    this.setSingleton();
  }

  refreshRecords(){
    setTimeout(()=>{
      return this.service.getAll(this.limit, this.offset).subscribe((data:any)=>{
        this.records = data;
      })
    },300);
  }
  
  setOffset(direction: string){
    if(direction == "sumar"){
      this.offset = this.offset + this.limit;
      if(this.typeOperator == true){
        this.getTypeOperator(this.type);
      }else{
        this.refreshRecords();
      }
    }else if(direction == "restar"){
      if(this.offset - this.limit < 0){
        this.offset = 0;
        if(this.typeOperator == true){
          this.getTypeOperator(this.type);
        }else{
          this.refreshRecords();
        }
      }else{
        this.offset = this.offset - this.limit;
        if(this.typeOperator == true){
          this.getTypeOperator(this.type);
        }else{
          this.refreshRecords();
        }
      }
    }
  }

  setLimit(limit: number){
    this.limit = limit;
    if(this.typeOperator == true){
      console.log(this.type)
      this.getTypeOperator(this.type);
    }else{
      this.refreshRecords();
    }
  }

  setSingleton(){
    setTimeout(()=>{
      return this.service.getSingleton().subscribe((data:SingletonResponse)=>{
        this.singleton = data;
        this.operaciones =  this.singleton.operations;
      })
    },300);
  }

  delete(id: Number){
    setTimeout(()=>{
      this.service.delete(id).subscribe((data:any)=>{
        if(this.typeOperator == true){
          this.getTypeOperator(this.type);
        }else{
          this.refreshRecords();
        }
      })
    },100);
  }

  getType(symbol: string){
    this.type = this.operatorParse(symbol);   
    this.offset = 0;
    this.getTypeOperator(this.type)
  }

  getTypeOperator(type: string){
    this.typeOperator = true;
    setTimeout(()=>{
      return this.service.getTypeRecords(this.type,this.limit, this.offset).subscribe((data:any)=>{
        this.records = data;
      })
    },300);
  }

  operatorParse(operator){  
    switch (operator){
        case "+":
            return "add";
        case "-":
            return "subtract";
        case "x":
            return "multiply";
        case "^":
            return "power";
        case "\u221A":
            return "root";
        case "%":
            return "percentage";
        case  "log":
            return "log";
        case "sin":
            return "sin";
        case "cos":
            return "cos";
        case "tan":
            return "tan";
        case "!":
            return "factorial";
        default:
            return "divide";
    }
}
}
