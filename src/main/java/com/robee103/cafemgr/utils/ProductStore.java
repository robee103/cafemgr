package com.robee103.cafemgr.utils;

import com.robee103.cafemgr.domain.ProductType;

public enum ProductStore {
  SMALL_COFFEE("Small Coffee",2.50D, ProductType.BEVERAGE),
  MEDIUM_COFFEE("Medium Coffee", 3.00D, ProductType.BEVERAGE),
  LARGE_COFFEE("Large Coffee", 3.50D, ProductType.BEVERAGE),
  ORANGE_JUICE("Orange Juice",3.95D, ProductType.BEVERAGE),
  EXTRA_MILK("Extra Milk", 0.30D, ProductType.EXTRA),
  EXTRA_FOAM_MILK("Extra Foam", 0.50D, ProductType.EXTRA),
  EXTRA_ROAST_COFFEE("Extra Roast Coffee", 0.50D, ProductType.EXTRA),
  BACON_ROLL("Bacon Roll",4.50D, ProductType.SNACK);

  private final String label;
  private final Double price;
  private final ProductType productType;

  ProductStore(String label , Double price, ProductType productType){
    this.label = label;
    this.price = price;
    this.productType = productType;
  }

  public String getLabel() {
    return label;
  }

  public Double getPrice() {
    return price;
  }

  public ProductType getProductType() {
    return productType;
  }
}
