package com.robee103.cafemgr.service;

import com.robee103.cafemgr.domain.BeverageImpl;
import com.robee103.cafemgr.domain.ExtraImpl;
import com.robee103.cafemgr.domain.Product;
import com.robee103.cafemgr.domain.SnackImpl;
import com.robee103.cafemgr.utils.CafeConstants;
import com.robee103.cafemgr.utils.ProductStore;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InputReaderService {
  private static final ProductStore[] products = ProductStore.values();

  public List<Product> parseOrderItems(String input) {
    List<Product> parsedOrderList = Objects.isNull(input) || input.isEmpty() ? null :
        Arrays.stream(input.split(","))
            .map(String::trim)
            .map(strToProductMapper())
            .collect(Collectors.toList());
    return parsedOrderList;
  }

  private Function<String, Product> strToProductMapper() {
    return orderIdStr -> {
      Product product = null;
      if (orderIdStr.matches("[0-3]")) {
        product = new BeverageImpl(products[Integer.valueOf(orderIdStr)]);
      } else if (orderIdStr.matches("[4-6]")) {
        product = new ExtraImpl(products[Integer.valueOf(orderIdStr)]);
      } else if (orderIdStr.matches("[7]")) {
        product = new SnackImpl(products[Integer.valueOf(orderIdStr)]);
      } else {
        throw new RuntimeException(CafeConstants.INPUT_ERROR_MSG);
      }
      return product;
    };
  }
}
