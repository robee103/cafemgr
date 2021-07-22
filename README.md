# cafemgr
A cafe invoicing and promo application that takes order and optionally applies promo.

After showing menu with numbered items, it will take a comma separated input (eg. 1,5).
An additional question for stamp card is asked in Y/N (eg. 'n').
Based on the input codes it will build an order and print the receipt.

Promos are applied based on stamp card with 2 criteria:
1. Beverage and snack is ordered, then 1 extra free
2. Every 5th Beverage ordered is free

## Requirements
For building and running the application you need:
- JDK 11
- Gradle

## Tests
 * In the root folder 'cafemgr' run the following:
  ```
     ./gradlew clean test
  ```
  
## Running application locally
 * In the root folder 'cafemgr' run the following:
  ```
     ./gradlew clean run
  ```
## Approach / assumptions
 * User input is from the command line.
 * Inputs are the numeric values of menu item to keep it simple.
 * The stamp is checked against the Y/N input provided.
 * No customer information is stored, and each order is checked against a new stamp card.
 * The order then updates the stamp card against the promo offers.
 * Snack+Beverage Free Promo is applied to 1st EXTRA ordered in the list.
 * 5th Free Beverage PROMO is applied to 5th BEVERAGE irrespective of cost.
 
 ## Test Cases Covered in JUnit
  * User inputs for both valid and invalid cases.
  * Order processing for simple orders w/o stamp card, stamp card EXTRA PROMO and BEVERAGE PROMO. 
  
## Sample run 

    -----------------------------------
    Welcome to Charlene's Coffee Corner
    -----------------------------------
    0 - Small Coffee
    1 - Medium Coffee
    2 - Large Coffee
    3 - Orange Juice
    4 - Extra Milk
    5 - Extra Foam
    6 - Extra Roast Coffee
    7 - Bacon Roll
    -----------------------------------

    Enter menu item numbers (',' separated items) :
    2,5,7
    Is stamp card present? 'Y'/'N' :
    Y

Output:
        
    ---------------------------------------------
              CHARLENE'S COFFEE CORNER
    ---------------------------------------------
    Item                      Qty           Price
    ---------------------------------------------
    Medium Coffee               1        3.00 CHF
    Bacon Roll                  1        4.50 CHF
    ---------------------------------------------
                      DISCOUNTS
    Extra Foam                  1       -0.50 CHF
    ---------------------------------------------
    TOTAL                                7.50 CHF
    ---------------------------------------------
               Thanks for visiting us.
