package com.robee103.cafemgr.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputReaderServiceTest {

  private InputReaderService inputReaderService = null;

  @BeforeEach
  void initOrder() {
    inputReaderService = new InputReaderService();
  }

  @Test
  @DisplayName("Test: Invalid Input Order")
  void invalidCustomerOrders() {
    String orderStr = "1,2,4,8";
    Assertions.assertThrows(RuntimeException.class, () -> inputReaderService.getOrderItems(orderStr));
    Assertions.assertNull(inputReaderService.getOrderItems(""));
  }

  @Test
  @DisplayName("Test: Valid Input Order")
  void validCustomerOrders() {
    String orderStr = "1,2,4,7";
    Assertions.assertNotNull(inputReaderService.getOrderItems(orderStr));
  }
}
