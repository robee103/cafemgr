package com.robee103.cafemgr.service;

import static java.lang.String.format;

import com.robee103.cafemgr.domain.PurchaseItem;
import com.robee103.cafemgr.domain.Product;
import com.robee103.cafemgr.utils.CafeConstants;
import com.robee103.cafemgr.utils.LogUtil;
import com.robee103.cafemgr.utils.ProductStore;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

public class PrinterService {

  private static final String title = "Charlene's Coffee Corner";
  private static final ProductStore[] products = ProductStore.values();

  public static void printMenu() {
    String welcomeMessage = format("%s %s","Welcome to",title);
    String separatorString = "-".repeat(welcomeMessage.length())+"\n";
    LogUtil.info(separatorString+welcomeMessage);
    StringBuilder sb = new StringBuilder(separatorString);
    IntStream.range(0, products.length)
        .forEach(idx ->
            sb.append(format("%d - %s\n", idx, products[idx].getLabel()))
        );
    sb.append(separatorString);
    LogUtil.info(sb.toString());
  }

    public static void printReceipt(OrderHandlerService order) {

      final Integer RECEIPT_WIDTH = 45;
      final String TITLE_FORMATTER = "%-25s %3s %15s\n";
      final String ENTRY_FORMATTER = "%-25s %3d %11.2f %3s\n";
      final String TOTAL_FORMATTER = "%-30s %10.2f %3s\n";

      String separatorString = "-".repeat(RECEIPT_WIDTH)+"\n";
      String receiptTitle = LogUtil.printCenter(title.toUpperCase(), RECEIPT_WIDTH);

      // Print HEADER
      StringBuilder receiptStringBuilder = new StringBuilder("\n\n"+separatorString);
      receiptStringBuilder.append(receiptTitle+"\n");
      receiptStringBuilder.append(separatorString);
      receiptStringBuilder.append(format(TITLE_FORMATTER, "Item", "Qty", "Price"));
      receiptStringBuilder.append("-".repeat(RECEIPT_WIDTH));
      LogUtil.info(receiptStringBuilder.toString());

      // Process Entries
      StringBuilder nonDiscountStrBuilder = new StringBuilder();
      Boolean isDiscountApplied = false;
      StringBuilder discountProductStrBuilder = new StringBuilder(separatorString + LogUtil.printCenter("DISCOUNTS",RECEIPT_WIDTH) + "\n");
      for (Map.Entry<PurchaseItem, Long> productEntry : order.getProductMap().entrySet()) {
        PurchaseItem item = productEntry.getKey();
        Product currProduct =  item.getProduct();
        if(item.getDiscounted()){
          isDiscountApplied = true;
          discountProductStrBuilder.append(
              format(ENTRY_FORMATTER, currProduct.getName(), productEntry.getValue(), currProduct.getPrice()*-1,CafeConstants.CURRENCY)
          );
        }
        else {
          nonDiscountStrBuilder.append(
              format(ENTRY_FORMATTER,
                  currProduct.getName(), productEntry.getValue(), currProduct.getPrice(),CafeConstants.CURRENCY)
          );
        }
      }
      // Print non-discounted items
      LogUtil.info("%s",nonDiscountStrBuilder.toString());
      // Print optional PROMO discounted items
      if(isDiscountApplied){
        LogUtil.info("%s",discountProductStrBuilder.toString());
      }
      // Print FOOTER
      receiptStringBuilder = new StringBuilder(separatorString);
      receiptStringBuilder.append(format(TOTAL_FORMATTER, "TOTAL", order.getTotalAmount(), CafeConstants.CURRENCY));
      receiptStringBuilder.append(separatorString);
      receiptStringBuilder.append(LogUtil.printCenter("Thanks for visiting us.", RECEIPT_WIDTH));
      LogUtil.info(receiptStringBuilder.toString());
    }
}
