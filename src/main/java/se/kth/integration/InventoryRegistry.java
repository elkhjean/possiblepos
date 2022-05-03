package se.kth.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.model.Item;

/**
 * This is the applications inventory registry handler, all calls to external
 * inventory system
 * pass through objects of this class.
 */
public class InventoryRegistry {
    private List<Item> itemInventoryList;

    public InventoryRegistry() {
        itemInventoryList = new ArrayList<>();
    }

    /**
     * Checks for item with matching id in inventory, creates and returns an item if
     * match is found.
     * 
     * @param itemID   unique item identifier for searched item.
     * @param quantity The quantity of the given item to be created.
     * @return The created item if match is found or null if no match was found
     */
    public Item fetchItemFromInventory(int itemID) {
        Item foundItem = null;
        for (Item itemToBeFetched : itemInventoryList) {
            if (itemToBeFetched.getItemID() == itemID) {
                foundItem = new Item(itemToBeFetched);
                return foundItem;
            }
        }
        return null;
    }

    /**
     * Method to add an item to inventory, used for testing.
     * 
     * @param testItem Object of Item class.
     */
    public void addItemToInventory(Item testItem) {
        itemInventoryList.add(testItem);
    }

}
