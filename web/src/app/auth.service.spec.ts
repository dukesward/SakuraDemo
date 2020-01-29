import { TestBed } from '@angular/core/testing';

import { AuthApiManager } from './auth.api.manager';

describe('AuthService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthApiManager = TestBed.get(AuthApiManager);
    expect(service).toBeTruthy();
  });
});
