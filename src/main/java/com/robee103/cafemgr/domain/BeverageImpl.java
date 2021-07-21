package com.robee103.cafemgr.domain;

import com.robee103.cafemgr.utils.ProductStore;

import java.util.Objects;

public class BeverageImpl implements Beverage {
  private ProductStore storeItem;
  private String name;
  private Double price = 0D;

  public BeverageImpl(ProductStore storeItem){
    this.storeItem = storeItem;
    this.name = storeItem.getLabel();
    this.price = storeItem.getPrice();
  }

  @Override
  public String getName(){
    return String.format("%s",this.name);
  }

  @Override
  public Double getPrice(){
    return this.price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BeverageImpl extra = (BeverageImpl) o;
    return storeItem == extra.storeItem &&
        name.equals(extra.name) &&
        price.equals(extra.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(storeItem, name, price);
  }
}
