# cafemgr
A cafe invoicing and promo application that takes order and optionally applies promo.

After showing menu with numbered items, it will take a comma seperate input
An additional question for stamp card is asked ? Y/N

Based on the input codes it will build an order and print the receipt

## Requirements
For building and running the application you need:
- JDK 8
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
 * User input is from the command line till 'q' is pressed
 * Inputs are the number values of menu item to keep it simple.
 * The stamp is checked against the Y/N input provided
 * No customer information is stored, and each order is checked against a new stamp card
 * The order then updates the stamp card against the promo offers (FEXTRA, FBEV)

## Sample run 

    Welcome to Charlene's Coffee Shop
    --------
    0 - Small Coffee
    1 - Medium Coffee
    2 - Large Coffee
    3 - Orange Juice
    4 - Extra Milk
    5 - Extra Foam
    6 - Extra Roast Coffee
    7 - Bacon Roll
    --------

* Order = large coffee, extra milk, bacon roll
* Stamp card = Y


    Enter menu item numbers (',' separated items). 'q' to exit: 
    2,3,7
    
    Is stamp card present? 'Y'/'N' :
    Y


Output:

    Thanks for visiting Charlene's Coffee Shop
    ********************************
    Item                        Qty      Price   Discount
    ----                        ---      -----      -----
    Large Coffee                  1       3.50           
    Extra Milk                    1       0.00     FEXTRA
    Bacon Roll                    1       4.50           
                                         -----      -----
    Total                                 8.00 CHF        