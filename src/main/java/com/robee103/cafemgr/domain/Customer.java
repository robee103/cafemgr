package com.robee103.cafemgr.domain;

import java.util.Objects;
import java.util.UUID;

public class Customer {
  private UUID id;
  private String name;

  public Customer(String name) {
    this.id = UUID.randomUUID();
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer = (Customer) o;
    return Objects.equals(id, customer.id) &&
        Objects.equals(name, customer.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
