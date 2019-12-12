export class CalculatorRecordPojo{
    id:Number;
    input1:String;
    input2:String;
    operator:String;
    result:String;
    date:Date;

    constructor(id:string, imp1:string, imp2:string, operator:string, reult:string, date:Date){
        this.id = Number.parseFloat(id);
        this.input1 = imp1;
        this.input2 = imp2;
        this.operator = operator;
        this.result = reult;
        this.date = date;
    }
}