import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ColeccionDetailComponent } from './coleccion-detail.component';

describe('ColeccionDetailComponent', () => {
  let component: ColeccionDetailComponent;
  let fixture: ComponentFixture<ColeccionDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ColeccionDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ColeccionDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
