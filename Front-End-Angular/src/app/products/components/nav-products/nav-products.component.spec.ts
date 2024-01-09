import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavProductsComponent } from './nav-products.component';

describe('NavProductsComponent', () => {
  let component: NavProductsComponent;
  let fixture: ComponentFixture<NavProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavProductsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
