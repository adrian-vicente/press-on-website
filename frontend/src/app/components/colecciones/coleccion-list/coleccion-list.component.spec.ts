import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ColeccionListComponent } from './coleccion-list.component';

describe('ColeccionListComponent', () => {
  let component: ColeccionListComponent;
  let fixture: ComponentFixture<ColeccionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ColeccionListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ColeccionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
