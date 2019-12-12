import { Component, OnInit, HostListener } from "@angular/core";
import { Router } from "@angular/router";
import { ServiceService } from "../Service/service.service";
import { WordResponse } from "../pojos/WordResponse";
import { WordRequest } from "../pojos/WordRequest";
import { CaesarRequest } from "../pojos/CaesarRequest";
import { WordRecordPojo } from "../pojos/WordRecordPojo";

@Component({
  selector: "app-wordprocess",
  templateUrl: "./wordprocess.component.html",
  styleUrls: ["./wordprocess.component.css"]
})
export class WordprocessComponent implements OnInit {
  phrase: string = "";
  result: string = "";
  request: WordRequest;
  response: WordResponse;
  recordData: WordRecordPojo;
  caesarRequest: CaesarRequest;
  limit: number = 10;
  offset: number = 0;
  records: WordRecordPojo[];
  fupdate: boolean = false;

  constructor(private router: Router, private service: ServiceService) {}

  ngOnInit() {
    this.refreshRecords();
  }

  toLower(frase) {
    this.phrase = frase;
    this.request = new WordRequest(this.phrase);
    if (this.fupdate == true) {
      this.update(this.recordData.id, "LOWER");
      this.fupdate = false;
    } else {
      this.doOperation("lower");
    }
  }

  toUpper(frase) {
    this.phrase = frase;
    this.request = new WordRequest(this.phrase);
    this.doOperation("upper");
  }

  toCamel(frase) {
    this.phrase = frase;
    this.request = new WordRequest(this.phrase);
    this.doOperation("toCamel");
  }

  fromCamel(frase) {
    this.phrase = frase;
    this.request = new WordRequest(this.phrase);
    this.doOperation("fromCamel");
  }

  caesar(frase, convert) {
    this.phrase = frase;
    this.caesarRequest = new CaesarRequest(this.phrase, convert);
    this.doOperation("caesar");
  }

  doOperation(operation) {
    switch (operation) {
      case "lower":
        this.service.lower(this.request).subscribe((data: any) => {
          this.response = data;
          this.result = this.response.result.toString();
        });
        break;
      case "upper":
        this.service.upper(this.request).subscribe((data: any) => {
          this.response = data;
          this.result = this.response.result.toString();
        });
        break;
      case "toCamel":
        this.service.toCamel(this.request).subscribe((data: any) => {
          this.response = data;
          this.result = this.response.result.toString();
        });
        break;
      case "fromCamel":
        this.service.fromCamel(this.request).subscribe((data: any) => {
          this.response = data;
          this.result = this.response.result.toString();
        });
        break;
      case "caesar":
        this.service.caesar(this.caesarRequest).subscribe((data: any) => {
          this.response = data;
          this.result = this.response.result.toString();
        });
        break;
    }

    this.refreshRecords();
  }

  setOffset(direction: string) {
    if (direction == "sumar") {
      this.offset = this.offset + this.limit;
    } else if (direction == "restar") {
      if (this.offset - this.limit < 0) {
        this.offset = 0;
      } else {
        this.offset = this.offset - this.limit;
      }
    }
    this.refreshRecords();
  }

  setLimit(limit: number) {
    this.limit = limit;
    this.refreshRecords();
  }

  refreshRecords() {
    setTimeout(() => {
      return this.service
        .getAllWords(this.limit, this.offset)
        .subscribe((data: any) => {
          this.records = data;
        });
    }, 300);
  }

  delete(id: Number) {
    setTimeout(() => {
      this.service.deleteWord(id).subscribe((data: any) => {
        this.refreshRecords();
      });
    }, 100);
  }

  getRecord(id: number) {
    this.service.record(id).subscribe((data: any) => {
      this.recordData = data;
      this.result = this.recordData.result.toString();
      this.phrase = this.recordData.phrase.toString();
      this.fupdate = true;
    });
  }

  update(id: number, method: string) {
    this.service.update(this.request, id, method).subscribe((data: any) => {
      this.response = data;
      this.result = this.response.result.toString();
    });
  }
}
