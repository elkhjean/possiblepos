package se.kth.model;

/**
 * Represents an item fetched from the external inventory system.
 */
public class Item {
    private int itemID;
    private String itemName;
    private int itemVAT;
    private Amount itemPrice;
    private int itemQuantity;
    private String itemDescription;

    /**
     * This is the onstructor for the Item class.
     * 
     * @param itemToCopy An object of the item to copy.
     */
    public Item(Item itemToCopy) {
        this.itemID = itemToCopy.itemID;
        this.itemName = itemToCopy.itemName;
        this.itemVAT = itemToCopy.itemVAT;
        this.itemPrice = itemToCopy.itemPrice;
        this.itemDescription = itemToCopy.itemDescription;
    }

    /**
     * This is the onstructor for the Item class.
     * 
     * @param itemID         The items unique identifier as an int
     * @param itemName       The name of the item as a string
     * @param itemVAT        The items VAT rate as an int
     * @param itemPrice      The price of the item as an object of Amount class
     * @param itemDesription The description of the Item as a string
     */
    public Item(int itemID, String itemName, int itemVAT, Amount itemPrice, String itemDesription) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemVAT = itemVAT;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDesription;
    }

    /**
     * Getter for itemID.
     * 
     * @return The Item ID.
     */
    public int getItemID() {
        return this.itemID;
    }

    /**
     * Updates the <code>ItemQuantity</code> of the item object.
     * 
     * @param quantity the amount to add to the itemQuantity.
     */
    public void updateQuantity(int quantity) {
        this.itemQuantity += quantity;
    }

    /**
     * This is the toString method for the Item class. It overrides the standard
     * toString method.
     * 
     * @return A string with information about the item.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(itemName + "\n");
        sb.append(itemPrice.toString() + "\n");
        sb.append(itemDescription);
        return sb.toString();
    }
}
