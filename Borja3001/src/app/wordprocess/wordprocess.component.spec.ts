import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WordprocessComponent } from './wordprocess.component';

describe('WordprocessComponent', () => {
  let component: WordprocessComponent;
  let fixture: ComponentFixture<WordprocessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WordprocessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WordprocessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
