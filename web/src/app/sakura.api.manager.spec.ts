import { TestBed } from '@angular/core/testing';

import { SakuraApiManager } from './sakura.api.manager';

describe('SakuraService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SakuraApiManager = TestBed.get(SakuraApiManager);
    expect(service).toBeTruthy();
  });
});
