package com.robee103.cafemgr.domain;

public interface Product {
  String getName();
  Double getPrice();
  ProductType getProductType();
  default Double resetPrice() { return 0.0D; }
}
