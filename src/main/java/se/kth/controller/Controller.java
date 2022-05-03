package se.kth.controller;

import se.kth.integration.AccountingRegistry;
import se.kth.integration.CashRegister;
import se.kth.integration.DiscountRegistry;
import se.kth.integration.InventoryRegistry;
import se.kth.integration.Printer;
import se.kth.model.Item;
import se.kth.model.Sale;

/**
 * This is the application's controller. All calls to the model layer pass
 * through objects of this class
 */

public class Controller {
    private InventoryRegistry inventoryRegistry;
    private DiscountRegistry discountRegistry;
    private AccountingRegistry accountingRegistry;
    private CashRegister cashRegister;
    private Printer printer;
    private Sale currentSale;

    /**
     * This is the constructor for the controller. It creates a new instance of the
     * class controller through which all calls to model layer pass.
     * 
     * @param invReg The inventory registry handler to use for calls to the external
     *               inventory system.
     * @param disReg The discount registry handler to use for calls to discount
     *               database.
     * @param acoReg The accounting registry handler to use for calls to external
     *               accounting system.
     */
    public Controller(InventoryRegistry invReg, DiscountRegistry disReg, AccountingRegistry acoReg) {
        this.accountingRegistry = acoReg;
        this.discountRegistry = disReg;
        this.inventoryRegistry = invReg;
        this.cashRegister = new CashRegister();
        this.printer = new Printer();
    }

    public Sale startSale() {
        currentSale = new Sale();
        return currentSale;
    }

    /**
     * Enters a quantity of items into the sale.
     * 
     * @param itemID   The unique item id number to identify item in external
     *                 inventory system.
     * @param quantity The quantity of the given item to be entered into sale.
     * @return The found item or a pointer to null if no item was found.
     */
    public String enterItemIntoSale(int itemID, int quantity) {
        Item foundItem = currentSale.searchForItemById(itemID);
        if (foundItem == null) {
            foundItem = inventoryRegistry.fetchItemFromInventory(itemID);
            if (foundItem != null)
                currentSale.addItemToSale(foundItem);
        }
        if (foundItem != null) {
            foundItem.updateQuantity(quantity);
            return foundItem.toString();
        }
        return "invalid item ID";
    }

    /**
     * Getter for the cotrollers current sale
     * 
     * @return An object of the class Sale
     */
    public Object getCurrentSale() {
        return this.currentSale;
    }
}