export class WordResponse {
  id: number;
  result: String;

  constructor(id: number, result: string) {
    this.id = id;
    this.result = result;
  }
}
