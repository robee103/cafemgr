package com.robee103.cafemgr.domain;

import java.util.Objects;

public class PurchaseItem {
  private Product product;
  private Boolean isBeverageFree;
  private Boolean isExtraFree;
  private Boolean isDiscounted;

  public PurchaseItem(Product product) {
    this.product = product;
    this.isBeverageFree = false;
    this.isExtraFree = false;
    this.isDiscounted = false;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Boolean getBeverageFree() {
    return isBeverageFree;
  }

  public void setBeverageFree(Boolean beverageFree) {
    isBeverageFree = beverageFree;
  }

  public Boolean getExtraFree() {
    return isExtraFree;
  }

  public void setExtraFree(Boolean extraFree) {
    isExtraFree = extraFree;
  }

  public Boolean getDiscounted() {
    return isDiscounted;
  }

  public void setDiscounted(Boolean discounted) {
    isDiscounted = discounted;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PurchaseItem that = (PurchaseItem) o;
    return product.equals(that.product) &&
        isDiscounted.equals(that.isDiscounted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, isDiscounted);
  }
}
