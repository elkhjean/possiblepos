package se.kth.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.controller.Controller;
import se.kth.integration.AccountingRegistry;
import se.kth.integration.DiscountRegistry;
import se.kth.integration.InventoryRegistry;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class viewTest {
    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        InventoryRegistry invReg = new InventoryRegistry();
        AccountingRegistry acoReg = new AccountingRegistry();
        DiscountRegistry disReg = new DiscountRegistry();
        Controller controller = new Controller(invReg, disReg, acoReg);
        instanceToTest = new View(controller);

        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testRunFakeExe() {
        instanceToTest.runFakeExe();
        String printout = printoutBuffer.toString();
        String expectedOutPut = "started";
        assertTrue(printout.contains(expectedOutPut), "UI did not start correctly");
    }
}
