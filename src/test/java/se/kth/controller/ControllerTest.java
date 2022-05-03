package se.kth.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.integration.AccountingRegistry;
import se.kth.integration.DiscountRegistry;
import se.kth.integration.InventoryRegistry;
import se.kth.model.Amount;
import se.kth.model.Item;
import se.kth.model.Sale;

public class ControllerTest {
    private Controller instanceToTest;

    @BeforeEach
    public void setUp() {
        InventoryRegistry invReg = new InventoryRegistry();

        Amount tomatoPrice = new Amount(8, "kr");
        Item tomatoItem = new Item(100, "Tomato", 12, tomatoPrice, "red tomato from italy");
        invReg.addItemToInventory(tomatoItem);

        Amount lettucePrice = new Amount(20, "kr");
        Item lettuceItem = new Item(200, "Lettuce", 12, lettucePrice, "fresh lettuce from local greenhouse");
        invReg.addItemToInventory(lettuceItem);

        AccountingRegistry acoReg = new AccountingRegistry();
        DiscountRegistry disReg = new DiscountRegistry();
        instanceToTest = new Controller(invReg, disReg, acoReg);
        Sale currentSale = instanceToTest.startSale();
        currentSale.addItemToSale(lettuceItem);
    }

    @AfterEach
    public void tearDown() {
        instanceToTest = null;
    }

    @Test
    public void testStartSale() {
        assertTrue(instanceToTest.getCurrentSale() != null, "Failed to start new sale");
    }

    @Test
    public void testEnterItemIntoSaleItemDoesNotExist() {
        String returnedItemString = instanceToTest.enterItemIntoSale(300, 1);
        String expectedOutPut = "invalid item ID";
        assertTrue(returnedItemString.contains(expectedOutPut), "returning null item failed");
    }

    @Test
    public void testEnterItemIntoSaleItemAlreadyInSale() {
        String returnedItemString = instanceToTest.enterItemIntoSale(200, 1);
        String expectedOutPut = "Lettuce";
        assertTrue(returnedItemString.contains(expectedOutPut), "adding item that already is in sale again failed");
    }

    @Test
    public void testEnterItemIntoSaleItemExistsInInventory() {
        String returnedItemString = instanceToTest.enterItemIntoSale(100, 1);
        String expectedOutPut = "Tomato";
        assertTrue(returnedItemString.contains(expectedOutPut), "adding item from inventory failed");
    }
}
