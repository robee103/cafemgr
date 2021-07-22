package com.robee103.cafemgr;

import com.robee103.cafemgr.domain.Product;
import com.robee103.cafemgr.domain.StampCard;
import com.robee103.cafemgr.service.InputReaderService;
import com.robee103.cafemgr.service.OrderHandlerService;
import com.robee103.cafemgr.service.PrinterService;
import com.robee103.cafemgr.utils.LogUtil;

import java.util.List;
import java.util.Scanner;

public class CafeApp {
  public static void main(String[] args) {
    PrinterService.printMenu();

    LogUtil.info("Enter menu item numbers (',' separated items) :");
    Scanner sc = new Scanner(System.in);
    String orderInput = sc.nextLine();
    LogUtil.info("Is stamp card present? 'Y'/'N' :");
    String stampCardInput = sc.nextLine();

    boolean hasStampCard = "Y".equalsIgnoreCase(stampCardInput);

    StampCard stampCard = null;
    if(hasStampCard){
      stampCard = new StampCard(null);
    }

    try {
      InputReaderService orderReader = new InputReaderService();
      List<Product> parsedOrderList = orderReader.parseOrderItems(orderInput);

      OrderHandlerService order = new OrderHandlerService();
      parsedOrderList.forEach(order::addProduct);
      order.processOrder(stampCard);

      PrinterService.printReceipt(order);
    }
    catch(RuntimeException ex){
      LogUtil.error(ex.getMessage());
    }

  }
}
