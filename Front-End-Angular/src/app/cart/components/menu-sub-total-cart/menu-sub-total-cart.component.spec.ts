import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuSubTotalCartComponent } from './menu-sub-total-cart.component';

describe('MenuSubTotalCartComponent', () => {
  let component: MenuSubTotalCartComponent;
  let fixture: ComponentFixture<MenuSubTotalCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuSubTotalCartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuSubTotalCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
