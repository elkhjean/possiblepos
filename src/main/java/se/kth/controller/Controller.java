package se.kth.controller;

import se.kth.DTOs.Amount;
import se.kth.DTOs.InventoryItemDTO;
import se.kth.DTOs.SaleDTO;
import se.kth.integration.AccountingRegistry;
import se.kth.integration.CashRegister;
import se.kth.integration.DiscountRegistry;
import se.kth.integration.InventoryRegistry;
import se.kth.integration.Printer;
import se.kth.model.Item;
import se.kth.model.Payment;
import se.kth.model.Reciept;
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
    private SaleDTO finishedSaleDTO;

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

    /**
     * Getter for the cotrollers current sale
     * 
     * @return An object of the class Sale
     */
    public Sale getCurrentSale() {
        return this.currentSale;
    }

    /**
     * getter for the controllers cashRegister
     * 
     * @return An object of the class cashRegister
     */
    public CashRegister getCashRegister() {
        return this.cashRegister;
    }

    /**
     * Method to create a new sale
     * 
     * @return an object currentSale of the Sale class
     */
    public void startSale() {
        currentSale = new Sale();
    }

    /**
     * Enters a quantity of items into the sale.
     * 
     * @param itemID   The unique item id number to identify item in external
     *                 inventory system.
     * @param quantity The quantity of the given item to be entered into sale.
     * @return The found item or a pointer to null if no item was found.
     */
    public InventoryItemDTO enterItemIntoSale(int itemID, int quantity) {
        Item foundItem = currentSale.searchForItemById(itemID);
        if (foundItem == null) {
            InventoryItemDTO foundItemDTO = inventoryRegistry.fetchItemFromInventory(itemID, quantity);
            if (foundItemDTO != null)
                foundItem = currentSale.createItemInSale(foundItemDTO);
        }
        if (foundItem != null) {
            foundItem.updateQuantity(quantity);
            currentSale.updateRunningTotal(foundItem, quantity);
            return foundItem.getItemDTOWithQuantity();
        }
        return null;
    }

    /**
     * Calls on method in sale to generate a saleDTO and destroys the sale object.
     * 
     * @return An object of saleDTO
     */
    public SaleDTO endSale() {
        this.finishedSaleDTO = this.currentSale.generateSaleDTO();
        this.currentSale = null;
        return this.finishedSaleDTO;
    }

    /**
     * Method to handle payment
     * 
     * @param paidAmount The amount of money paid by customer
     */
    public void pay(Amount paidAmount) {
        Payment payment = new Payment(paidAmount, finishedSaleDTO);
        inventoryRegistry.updateInventory(finishedSaleDTO);
        accountingRegistry.sendSaleInformation(finishedSaleDTO, payment);
        cashRegister.updateBalance(payment);
        Reciept reciept = new Reciept(finishedSaleDTO, payment);
        printer.printReciept(reciept);
    }
}