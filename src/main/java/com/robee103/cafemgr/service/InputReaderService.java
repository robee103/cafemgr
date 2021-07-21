package com.robee103.cafemgr.service;

import com.robee103.cafemgr.domain.BeverageImpl;
import com.robee103.cafemgr.domain.SnackImpl;
import com.robee103.cafemgr.domain.ExtraImpl;
import com.robee103.cafemgr.domain.Product;
import com.robee103.cafemgr.utils.ProductStore;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputReaderService {
  private static final Logger LOG = Logger.getLogger(InputReaderService.class.getName());
  private static final ProductStore[] products = ProductStore.values();

  public void printMenu() {
    StringBuilder sb = new StringBuilder("Welcome to Charlene's Coffee Shop\n");
    sb.append("--------\n");

    IntStream.range(0, products.length)
        .forEach(idx ->
            sb.append(String.format("%d - %s\n", idx, products[idx].getLabel()))
        );
    sb.append("--------\n");
    LOG.info(sb.toString());
  }

  public List<Product> getOrderItems(String input){
    List<Product> parsedOrderList = Objects.isNull(input) || input.isEmpty() ? null :
        Arrays.stream(input.split(","))
        .map(String::trim)
        .map(s -> {
          Product p = null;
          if(s.matches("[0-3]")){
            p = new BeverageImpl(products[Integer.valueOf(s)]);
          }
          else if(s.matches("[4-6]")){
            p = new ExtraImpl(products[Integer.valueOf(s)]);
          }
          else if(s.matches("[7]")){
            p = new SnackImpl(products[Integer.valueOf(s)]);
          }
          else {
            throw new RuntimeException("Invalid Input");
          }
          return p;
        }).collect(Collectors.toList());
    return parsedOrderList;
  }
}
