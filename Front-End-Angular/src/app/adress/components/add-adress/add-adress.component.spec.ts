import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAdressComponent } from './add-adress.component';

describe('AddAdressComponent', () => {
  let component: AddAdressComponent;
  let fixture: ComponentFixture<AddAdressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddAdressComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddAdressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
