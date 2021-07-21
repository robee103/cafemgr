package com.robee103.cafemgr.domain;

public interface Snack extends Product {
  default ProductType getProductType(){
    return ProductType.SNACK;
  }
}
