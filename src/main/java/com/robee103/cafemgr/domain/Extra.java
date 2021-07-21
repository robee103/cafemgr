package com.robee103.cafemgr.domain;

public interface Extra extends Product{
  default ProductType getProductType() { return ProductType.EXTRA; }
}
