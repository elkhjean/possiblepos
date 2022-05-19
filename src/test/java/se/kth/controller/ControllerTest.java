package se.kth.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.DTOs.Amount;
import se.kth.DTOs.InventoryItemDTO;
import se.kth.integration.AccountingRegistry;
import se.kth.integration.DiscountRegistry;
import se.kth.integration.InventoryRegistry;
import se.kth.integration.NoMatchingItemIdException;
import se.kth.model.Item;
import se.kth.model.Sale;

public class ControllerTest {
    private InventoryRegistry invReg;
    private AccountingRegistry acoReg;
    private DiscountRegistry disReg;
    private Controller instanceToTest;
    private Amount paidAmount;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() throws NoMatchingItemIdException, OperationFailureException {
        invReg = new InventoryRegistry();
        acoReg = new AccountingRegistry();
        disReg = new DiscountRegistry();
        instanceToTest = new Controller(invReg, disReg, acoReg);
        instanceToTest.startSale();
        instanceToTest.enterItemIntoSale(200, 2);
        paidAmount = new Amount(50);

        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown() {
        invReg = null;
        acoReg = null;
        disReg = null;
        instanceToTest = null;
        paidAmount = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testControllerConstructor() {
        assertTrue(instanceToTest != null, "failed to construct controller");
    }

    @Test
    public void testStartSale() {
        assertTrue(instanceToTest.getCurrentSale() != null, "Failed to start new sale");
    }

    @Test
    public void testEnterItemIntoSaleItemDoesNotExistThrowsException() throws OperationFailureException {
        Exception expectedException = null;
        try {
            instanceToTest.enterItemIntoSale(42, 20);
        } catch (NoMatchingItemIdException noMatchingIdException) {
            expectedException = noMatchingIdException;
        }
        assertTrue(expectedException != null,
                "Failed to throw exception when trying to add item that does not exist in inventory");
    }

    @Test
    public void testEnterItemIntoSaleItemExistsInInventoryDoesNotThrowException() throws OperationFailureException {
        Exception expectedException = null;
        try {
            instanceToTest.enterItemIntoSale(100, 20);
        } catch (NoMatchingItemIdException noMatchingIdException) {
            expectedException = noMatchingIdException;
        }
        assertTrue(expectedException == null,
                "Exception thrown even though item existed in inventory");
    }

    @Test
    public void testOperationFailureExceptionThrown() throws NoMatchingItemIdException {
        String expectedMessage = "Could not add item to sale";
        try {
            instanceToTest.enterItemIntoSale(401, 1);
            fail("no exception thrown despite operation failure");
        } catch (Exception exc) {
            assertTrue(exc.getMessage().contains(expectedMessage),
                    " wrong exception thrown, does not contain the expected message (" + expectedMessage + ")");
        }
    }

    @Test
    public void testEnterItemIntoSaleItemAlreadyInSale() throws NoMatchingItemIdException, OperationFailureException {
        InventoryItemDTO returnedItemDTO = instanceToTest.enterItemIntoSale(200, 1);
        assertTrue(returnedItemDTO.getQuantity() == 3, "Failed to add item that already exists in sale to sale");
    }

    @Test
    public void testEnterItemIntoSaleItemExistsInInventory()
            throws NoMatchingItemIdException, OperationFailureException {
        InventoryItemDTO returnedItemDTO = instanceToTest.enterItemIntoSale(100, 1);
        assertTrue(returnedItemDTO != null, "adding item from inventory failed");
    }

    @Test
    public void testEndSale() {
        instanceToTest.endSale();
        assertTrue(instanceToTest.getCurrentSale() == null, "Failed to end sale");
    }

    @Test
    public void testPay() {
        Amount balanceBeforePayment = instanceToTest.getCashRegister().getBalance();
        instanceToTest.endSale();
        instanceToTest.pay(paidAmount);
        Amount balanceAfterPayment = instanceToTest.getCashRegister().getBalance();
        assertTrue(balanceAfterPayment.getAmountValue() != balanceBeforePayment.getAmountValue(), "payment failed");
    }
}
