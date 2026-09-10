import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ColeccionFormComponent } from './coleccion-form.component';

describe('ColeccionFormComponent', () => {
  let component: ColeccionFormComponent;
  let fixture: ComponentFixture<ColeccionFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ColeccionFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ColeccionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
