package se.kth.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.DTOs.Amount;
import se.kth.DTOs.InventoryItemDTO;
import se.kth.DTOs.SaleDTO;

public class SaleTest {
    private Sale instanceToTest;
    private InventoryItemDTO itemToCreateDTO;
    private Item newlyCreatedTestItem;
    Amount cucumberPrice;

    @BeforeEach
    public void setUp(){
        instanceToTest = new Sale();
        cucumberPrice = new Amount(24);
        itemToCreateDTO = new InventoryItemDTO(55, "cucumber", 12, cucumberPrice, "English cucumber grade B");
        newlyCreatedTestItem = instanceToTest.createItemInSale(itemToCreateDTO);
        newlyCreatedTestItem.updateQuantity(1);
    }

    @AfterEach
    public void tearDown(){
        instanceToTest=null;
        newlyCreatedTestItem = null;
    }

    @Test
    public void testcreateItemInSale(){
        assertTrue(newlyCreatedTestItem != null, "item failed to be created");
    }

    @Test
    public void testSearchForItemByIdNoItemFound(){
        Item searchedItem = instanceToTest.searchForItemById(200);
        assertTrue(searchedItem == null, "search for item by Id with no matching item failed");
    }

    @Test
    public void testSearchForItemByIdItemFound(){
        Item searchedItem = instanceToTest.searchForItemById(55);
        assertTrue(searchedItem != null, "search for item by Id with item matching failed");
    }
    @Test
    public void testUpdateRunningTotal(){
        Amount runningTotalBeforeUpdate = instanceToTest.getRunningTotal();
        instanceToTest.updateRunningTotal(newlyCreatedTestItem, 1);
        Amount runningTotalAfterUpdate = instanceToTest.getRunningTotal();
        assertTrue(runningTotalBeforeUpdate != runningTotalAfterUpdate, "running total failed to update");
    }
    @Test
    public void testGenerateSaleDTO(){
        SaleDTO generatedSaleDTO = instanceToTest.generateSaleDTO();
        assertTrue(generatedSaleDTO != null);
    }
}
