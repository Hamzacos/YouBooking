import { TestBed } from '@angular/core/testing';

import { SignupuserService } from './signupuser-service.service';

describe('SignupuserServiceService', () => {
  let service: SignupuserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SignupuserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
