import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { CalculatorResponse } from "../pojos/CalculatorResponse";
import { CalculatorRequest } from "../pojos/CalculatorRequest";
import { CalculatorRecordPojo } from "../pojos/CalculatorRecordPojo";
import { SingletonResponse } from "../pojos/SingletonResponse";
import { WordRequest } from "../pojos/WordRequest";
import { CaesarRequest } from "../pojos/CaesarRequest";
import { WordRecordPojo } from "../pojos/WordRecordPojo";

@Injectable({
  providedIn: "root"
})
export class ServiceService {
  httpheaders = new HttpHeaders({
    "Content-Type": "application/json",
    "Acces-Control-Request-Headers": "*"
  });
  options = { headers: this.httpheaders };
  constructor(private http: HttpClient) {}

  UrlCalculator = "http://localhost:8080/calculator/api/calcule";
  UrlWordEditor = "http://localhost:8080/calculator/api/word";
  Url = "";

  getAll(limit: number, offset: number) {
    let params = new HttpParams({
      fromString: "limit=" + limit + "&offset=" + offset
    });
    return this.http.get<CalculatorRecordPojo[]>(
      this.UrlCalculator + "/records",
      { params }
    );
  }

  suma(request: CalculatorRequest) {
    this.Url = this.UrlCalculator + "/add";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  resta(request: CalculatorRequest) {
    this.Url = this.UrlCalculator + "/subtract";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  producto(request: CalculatorRequest) {
    this.Url = this.UrlCalculator + "/multiply";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  division(request: CalculatorRequest) {
    this.Url = this.UrlCalculator + "/divide";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  logaritmo(request: CalculatorRequest) {
    this.Url = this.UrlCalculator + "/log";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  porcentaje(request: CalculatorRequest) {
    this.Url = this.UrlCalculator + "/percentage";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  seno(request: CalculatorRequest) {
    this.Url = this.UrlCalculator + "/sin";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  coseno(request: CalculatorRequest) {
    this.Url = this.UrlCalculator + "/cos";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  tangente(request: CalculatorRequest) {
    this.Url = this.UrlCalculator + "/tan";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  raiz(request: CalculatorRequest) {
    this.Url = this.UrlCalculator + "/root";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  factorial(request: CalculatorRequest) {
    this.Url = this.UrlCalculator + "/factorial";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  potencia(request: CalculatorRequest) {
    this.Url = this.UrlCalculator + "/power";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  getSingleton() {
    this.Url = this.UrlCalculator + "/singleton";
    return this.http.get<SingletonResponse>(this.Url);
  }

  delete(id: Number) {
    this.Url = this.UrlCalculator + "/remove/" + id;
    return this.http.delete(this.Url);
  }

  getTypeRecords(type: string, limit: number, offset: number) {
    let params = new HttpParams({
      fromString: "limit=" + limit + "&offset=" + offset
    });
    return this.http.get<CalculatorRecordPojo[]>(
      this.UrlCalculator + "/records/operator/" + type,
      { params }
    );
  }

  lower(request: WordRequest) {
    this.Url = this.UrlWordEditor + "/lower";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  upper(request: WordRequest) {
    this.Url = this.UrlWordEditor + "/upper";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  toCamel(request: WordRequest) {
    this.Url = this.UrlWordEditor + "/camel";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  fromCamel(request: WordRequest) {
    this.Url = this.UrlWordEditor + "/fromcamel";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  caesar(request: CaesarRequest) {
    this.Url = this.UrlWordEditor + "/caesar";
    return this.http.post<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }

  getAllWords(limit: number, offset: number) {
    let params = new HttpParams({
      fromString: "limit=" + limit + "&offset=" + offset
    });
    return this.http.get<WordRecordPojo[]>(this.UrlWordEditor + "/records", {
      params
    });
  }

  deleteWord(id: Number) {
    this.Url = this.UrlWordEditor + "/remove/" + id;
    return this.http.delete(this.Url);
  }

  record(id: number) {
    this.Url = this.UrlWordEditor + "/records/" + id;
    return this.http.get<WordRecordPojo>(this.Url);
  }

  update(request: WordRequest, id: number, method: string) {
    this.Url = this.UrlWordEditor + "/update/" + id + "/" + method;
    return this.http.put<string>(
      this.Url,
      JSON.stringify(request),
      this.options
    );
  }
}
