package com.robee103.cafemgr.domain;

public interface Beverage extends Product {
  default ProductType getProductType() { return ProductType.BEVERAGE; }
}
