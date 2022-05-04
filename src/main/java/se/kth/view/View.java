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
        System.out.println(showItemInfoInView(itemInfoToDisplay));
        itemInfoToDisplay = controller.enterItemIntoSale(200, 1);
        System.out.println(showItemInfoInView(itemInfoToDisplay));
        itemInfoToDisplay = controller.enterItemIntoSale(300, 5);
        System.out.println(showItemInfoInView(itemInfoToDisplay));
        itemInfoToDisplay = controller.enterItemIntoSale(100, 2);
        System.out.println(showItemInfoInView(itemInfoToDisplay));
        saleInfoToDisplay = controller.endSale();
        System.out.println(showCompletedSaleInView(saleInfoToDisplay));
        Amount paidAmount = new Amount(0);
        controller.pay(paidAmount);
        System.out.println("sale confirmed paid");
    }
    
    public String showItemInfoInView(InventoryItemDTO objToPrint){
        if(objToPrint == null)
            return("Invalid item ID \n");
        StringBuilder sb = new StringBuilder();
        sb.append(objToPrint.getQuantity() + "      ");
        sb.append(objToPrint.getItemName() + " : ");
        sb.append(objToPrint.getItemDescription() + "       ");
        sb.append(objToPrint.getItemPrice().getAmountValue() + objToPrint.getItemPrice().getCurrency() + "\n");
        return sb.toString();
    }

    public String showCompletedSaleInView(SaleDTO saleInfoToBeDisplayed){
        StringBuilder sb = new StringBuilder();
        sb.append("Time of sale: " + saleInfoToBeDisplayed.getSaleTime() + "\n");
        for (InventoryItemDTO inventoryItemDTO : saleInfoToBeDisplayed.getItemsInSale()) {
            sb.append(showItemInfoInView(inventoryItemDTO));
        }
        sb.append("Total:" + saleInfoToBeDisplayed.getRunningTotal().getAmountValue() + "\n");
        return sb.toString();
    }
}
