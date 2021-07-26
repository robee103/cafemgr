package com.robee103.cafemgr.service;

import com.robee103.cafemgr.domain.Product;
import com.robee103.cafemgr.domain.ProductType;
import com.robee103.cafemgr.domain.PurchaseItem;
import com.robee103.cafemgr.domain.StampCard;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderHandlerService {
  private final List<Product> products = new ArrayList<>();
  private final List<PurchaseItem> purchaseItems = new ArrayList<>();
  private Map<PurchaseItem, Long> productMap = new LinkedHashMap<>();
  private Double amount;

  public void addProduct(Product product) {
    purchaseItems.add(new PurchaseItem(product));
  }

  public Map<PurchaseItem, Long> getProductMap() {
    return productMap;
  }

  public Double getTotalAmount() {
    return amount;
  }

  public void processOrder() {
    amount = purchaseItems.stream()
        .filter(item -> !item.getDiscounted())
        .map(item -> item.getProduct().getPrice())
        .reduce(0D, Double::sum);

    productMap = purchaseItems.stream()
        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
  }

  public void processOrder(StampCard card) {
    if (Objects.nonNull(card)) {
      checkFreeExtra(this.purchaseItems);
      checkFreeBeverage(this.purchaseItems, card);
    }
    processOrder();
  }

  private Optional<PurchaseItem> checkFreeExtra(List<PurchaseItem> items) {
    Long beverageCount = items.stream()
        .filter(item -> item.getProduct().getProductType().equals(ProductType.BEVERAGE))
        .count();
    Long snackCount = items.stream()
        .filter(item -> item.getProduct().getProductType().equals(ProductType.SNACK))
        .count();
    Optional<PurchaseItem> optFreeExtra = beverageCount > 0 && snackCount > 0 ?
        items.stream()
            .filter(item -> item.getProduct().getProductType().equals(ProductType.EXTRA))
            .limit(1)
            .map(item -> {
              item.setExtraFree(true);
              item.setDiscounted(true);
              return item;
            })
            .findFirst()
        : Optional.empty();
    return optFreeExtra;
  }

  private void checkFreeBeverage(List<PurchaseItem> items, StampCard card) {
    items.stream()
        .filter(item -> item.getProduct().getProductType().equals(ProductType.BEVERAGE))
        .forEachOrdered(item -> {
          card.updateBeverageCount();
          if (card.getBeverageCount() % 5 == 0) {
            item.setBeverageFree(true);
            item.setDiscounted(true);
          }
        });
  }
}
