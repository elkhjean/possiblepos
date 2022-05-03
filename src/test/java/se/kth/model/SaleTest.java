package se.kth.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaleTest {
    private Sale instanceToTest;

    @BeforeEach
    public void setUp(){
        Amount tomatoPrice = new Amount(8, "kr");
        Item tomatoItem = new Item(100, "Tomato", 12, tomatoPrice, "red tomato from italy");
        instanceToTest = new Sale();
        instanceToTest.addItemToSale(tomatoItem);
    }

    @AfterEach
    public void tearDown(){
        instanceToTest=null;
    }

    @Test
    public void testTimeSetWhenInstanceCreated(){

    }

    @Test
    public void testSearchForItemByIdNoItemFound(){
        Item searchedItem = instanceToTest.searchForItemById(200);
        assertTrue(searchedItem == null, "search for item by Id with no matching item failed");
    }

    @Test
    public void testSearchForItemByIdItemFound(){
        Item searchedItem = instanceToTest.searchForItemById(100);
        assertTrue(searchedItem != null, "search for item by Id with item matching failed");
    }
}
