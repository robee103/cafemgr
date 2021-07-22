package com.robee103.cafemgr.service;

import com.robee103.cafemgr.domain.Product;
import com.robee103.cafemgr.domain.StampCard;
import com.robee103.cafemgr.utils.ProductStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderHandlerServiceTest {

  private OrderHandlerService orderHandlerService = null;
  private InputReaderService inputReaderService = null;
  private StampCard stampCard = null;

  @BeforeEach
  void initOrder() {
    orderHandlerService = new OrderHandlerService();
    inputReaderService = new InputReaderService();
    stampCard = new StampCard(null);
  }

  @Test
  @DisplayName("Test: No Promo Confirm Total")
  public void checkTotalAmount(){
    String orderStr = "1,4,7";
    List<Product> products = inputReaderService.parseOrderItems(orderStr);
    products.forEach(orderHandlerService::addProduct);
    orderHandlerService.processOrder();
    assertEquals((ProductStore.BACON_ROLL.getPrice() +
            ProductStore.MEDIUM_COFFEE.getPrice()+
            ProductStore.EXTRA_MILK.getPrice()),
        orderHandlerService.getTotalAmount().doubleValue());
  }

  @Test
  @DisplayName("Test: Apply Bonus Program For Extra Free - EXTRA_MILK is Free")
  void testExtraFreePromo() {
    String orderStr = "1,4,7";
    List<Product> products = inputReaderService.parseOrderItems(orderStr);
    products.forEach(orderHandlerService::addProduct);
    orderHandlerService.processOrder(stampCard);
    assertEquals((ProductStore.BACON_ROLL.getPrice() + ProductStore.MEDIUM_COFFEE.getPrice()),
        orderHandlerService.getTotalAmount().doubleValue());
  }

  @Test
  @DisplayName("Test: Apply Bonus Program For Extra Free")
  void testExtraBeveragePromo() {
    String orderStr = "0,0,1,1,2,4";

    List<Product> products = inputReaderService.parseOrderItems(orderStr);
    products.forEach(orderHandlerService::addProduct);
    orderHandlerService.processOrder(stampCard);

    // Large Coffee will be skipped
    ProductStore[] expectedProducts = {ProductStore.SMALL_COFFEE,ProductStore.SMALL_COFFEE,
        ProductStore.MEDIUM_COFFEE,ProductStore.MEDIUM_COFFEE,
        ProductStore.EXTRA_MILK};
    Double expectedTotal = Arrays.asList(expectedProducts).stream().map(p->p.getPrice()).reduce(0D, Double::sum);

    assertEquals(expectedTotal.doubleValue(),
        orderHandlerService.getTotalAmount().doubleValue());
  }

}
