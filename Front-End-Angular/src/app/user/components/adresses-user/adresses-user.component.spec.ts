import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdressesUserComponent } from './adresses-user.component';

describe('AdressesUserComponent', () => {
  let component: AdressesUserComponent;
  let fixture: ComponentFixture<AdressesUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdressesUserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdressesUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
