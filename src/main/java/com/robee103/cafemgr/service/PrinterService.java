package com.robee103.cafemgr.service;

import static java.lang.String.format;

import com.robee103.cafemgr.domain.PurchaseItem;
import com.robee103.cafemgr.domain.Product;
import com.robee103.cafemgr.utils.CafeConstants;

import java.util.Map;

public class PrinterService {
    public static String printReceipt(OrderHandlerService order) {
      StringBuilder f = new StringBuilder(format("%-35s\n", "Thanks for visiting Charlene's Coffee Shop"));
      f.append(format("%-25s\n", "********************************"));
      f.append(format("%-25s %5s %10s %10s\n", "Item", "Qty", "Price", "Discount"));
      f.append(format("%-25s %5s %10s %10s\n", "----", "---", "-----", "-----"));
      for (Map.Entry<PurchaseItem, Long> productEntry : order.getProductMap().entrySet()) {
        PurchaseItem item = productEntry.getKey();
        Product currProduct =  item.getProduct();
        f.append(format("%-25.25s %5d %10.2f %10s\n",
            currProduct.getName(), productEntry.getValue(), item.getDiscounted() ?
                0.0D : currProduct.getPrice(),
            item.getDiscounted() ? (item.getBeverageFree() ? "FBEV":"FEXTRA"):"" ));
      }
      f.append(format("%-25s %5s %10s %10s\n", "", "", "-----","-----"));
      f.append(format("%-25s %5s %10.2f %3s %10s\n", "Total", "", order.getTotalAmount(), CafeConstants.CURRENCY,""));
      return f.toString();
    }
}
