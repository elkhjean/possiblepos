package se.kth.DTOs;

public class InventoryItemDTO {
    private int itemID;
    private String itemName;
    private int itemVAT;
    private Amount itemPrice;
    private String itemDescription;
    private int quantity;

    public InventoryItemDTO(InventoryItemDTO itemToCopy, int quantity) {
        this.itemID = itemToCopy.itemID;
        this.itemName = itemToCopy.itemName;
        this.itemVAT = itemToCopy.itemVAT;
        this.itemPrice = itemToCopy.itemPrice;
        this.itemDescription = itemToCopy.itemDescription;
        this.quantity = quantity;
    }
    public InventoryItemDTO(int itemID, String itemName, int itemVAT, Amount itemPrice, String itemDescription) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemVAT = itemVAT;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.quantity = 0;
    }

    public String getItemName() {
        return this.itemName;
    }

    public Amount getItemPrice() {
        return this.itemPrice;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public int getItemID() {
        return this.itemID;
    }
    
    public int getItemVAT(){
        return this.itemVAT;
    }
    public int getQuantity(){
        return this.quantity;
    }

        /**
     * This is the toString method for the InventoryItemDToClass class. It overrides the standard
     * toString method.
     * 
     * @return A string with information about the item.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.quantity + " ");
        sb.append(this.itemName + "      ");
        sb.append(this.itemPrice.toString() + "     ");
        sb.append(itemDescription + "       \n");
        return sb.toString();
    }

}
