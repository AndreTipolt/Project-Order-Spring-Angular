import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAdressUserComponent } from './add-adress-user.component';

describe('AddAdressUserComponent', () => {
  let component: AddAdressUserComponent;
  let fixture: ComponentFixture<AddAdressUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddAdressUserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddAdressUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
