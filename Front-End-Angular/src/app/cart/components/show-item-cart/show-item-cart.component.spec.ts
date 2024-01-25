import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowItemCartComponent } from './show-item-cart.component';

describe('ShowItemCartComponent', () => {
  let component: ShowItemCartComponent;
  let fixture: ComponentFixture<ShowItemCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowItemCartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowItemCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
