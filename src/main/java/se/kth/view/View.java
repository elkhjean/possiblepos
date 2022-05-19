package se.kth.view;

import se.kth.DTOs.Amount;
import se.kth.DTOs.InventoryItemDTO;
import se.kth.DTOs.SaleDTO;
import se.kth.controller.Controller;
import se.kth.controller.OperationFailureException;
import se.kth.integration.NoMatchingItemIdException;

/**
 * This is a placeholder for the real view. It contains hardcoded execution
 * calls to system
 * operations in the controller.
 */
public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
        this.controller.addPaymentObserver(new TotalRevenueView());
        this.controller.addPaymentObserver(new TotalRevenueFileOutput());
    }

    /**
     * A simulation of a user interaction with the view.
     */
    public void runFakeExe() {
        controller.startSale();
        System.out.println("New sale started");

        enterItemIntoSale(100, 5);
        enterItemIntoSale(200, 1);
        enterItemIntoSale(300, 2);
        enterItemIntoSale(100, 3);
        enterItemIntoSale(401, 1);
        enterItemIntoSale(200, 1);

        System.out.println(showCompletedSaleInView(controller.endSale()));

        controller.pay(new Amount(100));
        System.out.println("sale confirmed paid");
    }

    /**
     * Calls method in controller to enter an item into sale if itemID exists.
     * 
     * @param itemID   the itemID of the item to be entered into the sale.
     * @param quantity the quantity of the item to be entered into the sale.
     */
    private void enterItemIntoSale(int itemID, int quantity) {
        try {
            System.out.println(showItemInfoInView(controller.enterItemIntoSale(itemID, quantity)));
        } catch (NoMatchingItemIdException noMatchingIdFoundException) {
            System.out.println(noMatchingIdFoundException.getMessage() + "\n");
        } catch (OperationFailureException operationFailedException) {
            System.out.println(operationFailedException.getMessage());
        }
    }

    /**
     * Formats a string containing information of the item.
     * 
     * @param objToPrint the item to be printed.
     * @return A formatted string.
     */
    private String showItemInfoInView(InventoryItemDTO objToPrint) {
        StringBuilder sb = new StringBuilder();
        sb.append(objToPrint.getQuantity() + "      ");
        sb.append(objToPrint.getItemName() + " : ");
        sb.append(objToPrint.getItemDescription() + "       ");
        sb.append(objToPrint.getItemPrice().getAmountValue() + objToPrint.getItemPrice().getCurrency() + "\n");
        return sb.toString();
    }

    /**
     * Formats a string containing information of a sale.
     * 
     * @param saleInfoToBeDisplayed the sale to be displayed in the view.
     * @return A formatted string.
     */
    private String showCompletedSaleInView(SaleDTO saleInfoToBeDisplayed) {
        StringBuilder sb = new StringBuilder();
        sb.append("Time of sale: " + saleInfoToBeDisplayed.getSaleTime() + "\n");
        for (InventoryItemDTO inventoryItemDTO : saleInfoToBeDisplayed.getItemsInSale()) {
            sb.append(showItemInfoInView(inventoryItemDTO));
        }
        sb.append("Total:" + saleInfoToBeDisplayed.getRunningTotal().getAmountValue() + "\n");
        return sb.toString();
    }
}
