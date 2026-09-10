import { TestBed } from '@angular/core/testing';

import { ColeccionServiceService } from './coleccion-service.service';

describe('ColeccionServiceService', () => {
  let service: ColeccionServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ColeccionServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
