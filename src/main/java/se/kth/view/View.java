package se.kth.view;

import se.kth.controller.Controller;

/**
 * This is a placeholder for the real view. It contains hardcoded execution calls to system
 * operations in the controller.
 */
public class View {
    private Controller controller;
    private String itemInfoToDisplay;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void runFakeExe(){
        controller.startSale();
        System.out.println("New sale started");
        itemInfoToDisplay = controller.enterItemIntoSale(552, 3);
        System.out.println(itemInfoToDisplay);
        itemInfoToDisplay = controller.enterItemIntoSale(514, 1);
        System.out.println(itemInfoToDisplay);
        itemInfoToDisplay = controller.enterItemIntoSale(897, 5);
        System.out.println(itemInfoToDisplay);
        itemInfoToDisplay = controller.enterItemIntoSale(514, 2);
        System.out.println(itemInfoToDisplay);
    }
    
}
