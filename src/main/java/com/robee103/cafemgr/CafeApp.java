package com.robee103.cafemgr;

import com.robee103.cafemgr.domain.Product;
import com.robee103.cafemgr.domain.StampCard;
import com.robee103.cafemgr.service.InputReaderService;
import com.robee103.cafemgr.service.OrderHandlerService;
import com.robee103.cafemgr.service.PrinterService;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/*
* Coffee (S/M/L) - 2.5/3/3.5
* Bacon Roll - 4.5
* Orange Juice (0.25L) - 3.95
*
* Extras
* Extra Milk - 0.3
* Foamed Milk - 0.5
* Roasted Coffee - 0.9
*
* Print a receipt - similar to supermarket
* Input is list of menu items
*
* */
public class CafeApp {
  private static final Logger LOG = Logger.getLogger(CafeApp.class.getName());

  public static void main(String[] args) {

    InputReaderService irs = new InputReaderService();
    irs.printMenu();

    System.out.printf("%s", "Enter menu item numbers (',' separated items). 'q' to exit: ");
    Scanner sc = new Scanner(System.in);
    String orderInput = "";
    String stampCardInput = "";
    while (sc.hasNext()) {
      orderInput = sc.nextLine();
      if (orderInput.equalsIgnoreCase("q")) {
        break;
      }
      System.out.printf("%s", "Is stamp card present? 'Y'/'N' :");
      stampCardInput = sc.nextLine();
    }
    boolean hasStampCard = "Y".equalsIgnoreCase(stampCardInput);

    StampCard stampCard = null;
    if(hasStampCard){
      stampCard = new StampCard(null);
    }

    List<Product> parsedOrderList = irs.getOrderItems(orderInput);

    OrderHandlerService order = new OrderHandlerService();
    parsedOrderList.forEach(order::addProduct);
    order.processOrder(stampCard);

    LOG.info(PrinterService.printReceipt(order));
  }
}
