package se.kth.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.DTOs.Amount;
import se.kth.DTOs.InventoryItemDTO;
import se.kth.integration.AccountingRegistry;
import se.kth.integration.DiscountRegistry;
import se.kth.integration.InventoryRegistry;
import se.kth.model.Item;
import se.kth.model.Sale;

public class ControllerTest {
    private InventoryRegistry invReg;
    private AccountingRegistry acoReg;
    private DiscountRegistry disReg;
    private Controller instanceToTest;
    private Amount paidAmount;

    @BeforeEach
    public void setUp() {
        invReg = new InventoryRegistry();
        acoReg = new AccountingRegistry();
        disReg = new DiscountRegistry();
        instanceToTest = new Controller(invReg, disReg, acoReg);
        instanceToTest.startSale();
        instanceToTest.enterItemIntoSale(200, 2);
        paidAmount = new Amount(50);
    }

    @AfterEach
    public void tearDown() {
        invReg = null;
        acoReg = null;
        disReg = null;
        instanceToTest = null;
        paidAmount = null;
    }

    @Test
    public void testControllerConstructor(){
        assertTrue(instanceToTest != null, "failed to construct controller");
    }

    @Test
    public void testStartSale() {
        assertTrue(instanceToTest.getCurrentSale() != null, "Failed to start new sale");
    }

    @Test
    public void testEnterItemIntoSaleItemDoesNotExist() {
        InventoryItemDTO returnedItemDTO = instanceToTest.enterItemIntoSale(42, 20);
        assertTrue(returnedItemDTO == null, "Failed to detect non existant item");
    }

    @Test
    public void testEnterItemIntoSaleItemAlreadyInSale() {
        InventoryItemDTO returnedItemDTO = instanceToTest.enterItemIntoSale(200, 1);
        assertTrue(returnedItemDTO.getQuantity() == 3, "Failed to add item that already exists in sale to sale");
    }

    @Test
    public void testEnterItemIntoSaleItemExistsInInventory() {
        InventoryItemDTO returnedItemDTO = instanceToTest.enterItemIntoSale(100, 1);
        assertTrue(returnedItemDTO != null, "adding item from inventory failed");
    }

    @Test
    public void testEndSale(){
        instanceToTest.endSale();
        assertTrue(instanceToTest.getCurrentSale() == null, "Failed to end sale");
    }

    @Test
    public void testPay(){
        Amount balanceBeforePayment = instanceToTest.getCashRegister().getBalance();
        instanceToTest.endSale();
        instanceToTest.pay(paidAmount);
        Amount balanceAfterPayment = instanceToTest.getCashRegister().getBalance();
        assertTrue(balanceAfterPayment.getAmountValue() != balanceBeforePayment.getAmountValue(), "payment failed");
    }
}
