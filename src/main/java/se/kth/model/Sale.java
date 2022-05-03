package se.kth.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * represents one sale of certain items made by one customer.
 */
public class Sale {
    private LocalTime saleTime;
    private List<Item> itemsInSale;
    private Amount runningTotal;
    private DiscountInfoDTO discountInfo;

    /**
     * Creates new instance of sale, saves the time that the sale was created and
     * creates an amount object to store the running total.
     */
    public Sale() {
        this.itemsInSale = new ArrayList<>();
        this.saleTime = LocalTime.now();
        this.runningTotal = new Amount(0, "kr");
    }

    /**
     * Searches <code>itemList</code> array for items with matching id
     * 
     * @param itemID The ID for the item to be searched.
     * @return An instance of to the item with matching ID or null if no item was
     *         found.
     */
    public Item searchForItemById(int itemID) {
        for (Item searchedItem : itemsInSale) {
            if (searchedItem.getItemID() == itemID)
                return searchedItem;
        }
        return null;
    }

    public void addItemToSale(Item foundItem) {
        itemsInSale.add(foundItem);
    }
}
