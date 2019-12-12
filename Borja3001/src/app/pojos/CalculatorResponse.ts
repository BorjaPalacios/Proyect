export class CalculatorResponse{
    id:number;
    result:String;

    constructor(id:number, result:number){
        this.id = id;
        this.result = result.toString();
    }
}