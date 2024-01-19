import { TestBed } from '@angular/core/testing';

import { RoutingResolver } from './routing.resolver';

describe('RoutingResolver', () => {
  let resolver: RoutingResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(RoutingResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
