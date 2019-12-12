export class WordRecordPojo {
  id: number;
  phrase: String;
  method: String;
  result: String;
  date: Date;

  constructor(
    id: string,
    phrase: string,
    method: string,
    reult: string,
    date: Date
  ) {
    this.id = Number.parseFloat(id);
    this.phrase = phrase;
    this.method = method;
    this.result = reult;
    this.date = date;
  }
}
