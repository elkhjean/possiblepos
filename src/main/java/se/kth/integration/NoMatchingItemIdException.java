package se.kth.integration;

/**
 * Thrown when an operation to fetch an item from inventory system fails because
 * no matching item exists.
 */
public class NoMatchingItemIdException extends Exception {
    private int itemID;

    /**
     * creates a new instance with a message specifiying which itemID did not match
     * any ID in the inventory system.
     * 
     * @param itemID the ID of the item which caused the exception.
     */
    public NoMatchingItemIdException(int itemID) {
        super("Invalid Item Identifier (" + itemID + ") no matching item in inventory");
        this.itemID = itemID;
    }

    /**
     * 
     * @return the itemID of the item that could not be found.
     */
    public int getItemID(){
        return this.itemID;
    }
}
