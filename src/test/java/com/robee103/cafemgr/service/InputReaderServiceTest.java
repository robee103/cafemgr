package com.robee103.cafemgr.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputReaderServiceTest {

  private InputReaderService orderReader = null;

  @BeforeEach
  void initOrder() {
    orderReader = new InputReaderService();
  }

  @Test
  @DisplayName("Test: Invalid Input Order")
  void invalidCustomerOrders() {
    String orderStr = "1,2,4,8";
    Assertions.assertThrows(RuntimeException.class, () -> orderReader.parseOrderItems(orderStr));
    Assertions.assertNull(orderReader.parseOrderItems(""));
  }

  @Test
  @DisplayName("Test: Valid Input Order")
  void validCustomerOrders() {
    String orderStr = "1,2,4,7";
    Assertions.assertNotNull(orderReader.parseOrderItems(orderStr));
  }
}
