package se.kth.startup;

import se.kth.controller.Controller;
import se.kth.integration.AccountingRegistry;
import se.kth.integration.DiscountRegistry;
import se.kth.integration.InventoryRegistry;
import se.kth.view.View;

/**
 * Starts the entire application. Contains the main method used to start the application.
 */
public class Main {
    /**
     * The main method used to start entire the application.
     * @param args The program has no command line parameters.
     */
    public static void main(String[] args){
        InventoryRegistry inventoryRegistry = new InventoryRegistry();
        DiscountRegistry discountRegistry = new DiscountRegistry();
        AccountingRegistry accountingRegistry = new AccountingRegistry();
        Controller controller = new Controller(inventoryRegistry, discountRegistry, accountingRegistry);
        View view = new View(controller);
        view.runFakeExe();
    }
    
}
