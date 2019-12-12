export class CaesarRequest {
  phrase: string;
  convert: number;

  constructor(phrase: string, convert: number) {
    this.phrase = phrase;
    this.convert = convert;
  }
}
