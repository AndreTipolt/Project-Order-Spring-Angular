import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAdressComponent } from './show-adress.component';

describe('ShowAdressComponent', () => {
  let component: ShowAdressComponent;
  let fixture: ComponentFixture<ShowAdressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowAdressComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowAdressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
