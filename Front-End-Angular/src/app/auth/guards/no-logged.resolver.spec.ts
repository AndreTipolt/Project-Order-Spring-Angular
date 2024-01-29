import { TestBed } from '@angular/core/testing';

import { NoLoggedResolver } from './no-logged.resolver';

describe('NoLoggedResolver', () => {
  let resolver: NoLoggedResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(NoLoggedResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
