package se.kth.controller;

import java.util.ArrayList;
import java.util.List;

import se.kth.DTOs.*;
import se.kth.integration.*;
import se.kth.model.*;
import se.kth.utility.ExceptionLogHandler;

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
    private List<PaymentObserver> paymentObservers = new ArrayList<>();
    private ExceptionLogHandler exceptionLogger;

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
        this.exceptionLogger = new ExceptionLogHandler();
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
     * @throws NoMatchingItemIdException if there is no match in the inventory
     *                                   system for the specified itemID.
     * @throws OperationFailureException if a DatabaseFailureException is caught
     *                                   returning from call to inventoryRegistry
     */
    public InventoryItemDTO enterItemIntoSale(int itemID, int quantity)
            throws NoMatchingItemIdException, OperationFailureException {
        try {
            Item foundItem = currentSale.searchForItemById(itemID);
            if (foundItem == null) {
                InventoryItemDTO foundItemDTO = inventoryRegistry.fetchItemFromInventory(itemID, quantity);
                foundItem = currentSale.createItemInSale(foundItemDTO);
            }
            foundItem.updateQuantity(quantity);
            currentSale.updateRunningTotal(foundItem, quantity);
            return foundItem.getItemDTOWithQuantity();
        } catch (DatabaseFailureException dbFailException) {
            exceptionLogger.logException(dbFailException);
            throw new OperationFailureException("Could not add item to sale\n", dbFailException);
        }
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
        Payment payment = new Payment(paidAmount, this.finishedSaleDTO, this.paymentObservers);
        inventoryRegistry.updateInventory(this.finishedSaleDTO);
        accountingRegistry.sendSaleInformation(this.finishedSaleDTO, payment);
        cashRegister.updateBalance(payment);
        Reciept reciept = new Reciept(this.finishedSaleDTO, payment);
        printer.printReciept(reciept);
    }

    /**
     * Method to add a paymentObserver to the controllers list of PayMentObservers'
     * 
     * @param observer an object of class which implements onterface PaymentObserver
     */
    public void addPaymentObserver(PaymentObserver observer) {
        this.paymentObservers.add(observer);
    }
}