package se.kth.view;

import se.kth.DTOs.Amount;
import se.kth.DTOs.InventoryItemDTO;
import se.kth.DTOs.SaleDTO;
import se.kth.controller.Controller;

/**
 * This is a placeholder for the real view. It contains hardcoded execution calls to system
 * operations in the controller.
 */
public class View {
    private Controller controller;
    private InventoryItemDTO itemInfoToDisplay;
    private SaleDTO saleInfoToDisplay;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void runFakeExe(){
        controller.startSale();
        System.out.println("New sale started");
        itemInfoToDisplay = controller.enterItemIntoSale(100, 3);
        System.out.println(itemInfoToDisplay);
        itemInfoToDisplay = controller.enterItemIntoSale(200, 1);
        System.out.println(itemInfoToDisplay);
        itemInfoToDisplay = controller.enterItemIntoSale(300, 5);
        System.out.println(itemInfoToDisplay);
        itemInfoToDisplay = controller.enterItemIntoSale(100, 2);
        System.out.println(itemInfoToDisplay);
        saleInfoToDisplay = controller.endSale();
        System.out.println(saleInfoToDisplay);
        Amount paidAmount = new Amount(0);
        controller.pay(paidAmount);
        System.out.println("sale confirmed paid");
    }
    
}
