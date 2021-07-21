package com.robee103.cafemgr.domain;

import java.util.UUID;

public class StampCard {
  private UUID id;
  private Long beverageCount;
  private Customer customer;

  public StampCard(Customer customer){
    this.id = UUID.randomUUID();
    this.beverageCount = 0L;
    this.customer = customer;
  }

  public void updateBeverageCount() {
    this.beverageCount = this.beverageCount + 1;
  }

  public Long getBeverageCount() {
    return beverageCount;
  }


}
