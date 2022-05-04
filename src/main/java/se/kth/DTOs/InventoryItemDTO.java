package se.kth.DTOs;

/**
 * This is the inventoryItemDTO, it stores information about an item from the intem inventory
 */
public class InventoryItemDTO {
    private int itemID;
    private String itemName;
    private int itemVAT;
    private Amount itemPrice;
    private String itemDescription;
    private int quantity;
/**
 * Constructor for InventoryItemDTO
 * @param itemToCopy object of InventoryItemDTO to copy
 * @param quantity Quantity of the item descibed in the DTO
 */
    public InventoryItemDTO(InventoryItemDTO itemToCopy, int quantity) {
        this.itemID = itemToCopy.itemID;
        this.itemName = itemToCopy.itemName;
        this.itemVAT = itemToCopy.itemVAT;
        this.itemPrice = itemToCopy.itemPrice;
        this.itemDescription = itemToCopy.itemDescription;
        this.quantity = quantity;
    }
    /**
     * constructor for IteminventoryDTO
     * @param itemID unique item identifier
     * @param itemName the name of the item
     * @param itemVAT the VAT of the item
     * @param itemPrice the price of the item
     * @param itemDescription a description of the item
     */
    public InventoryItemDTO(int itemID, String itemName, int itemVAT, Amount itemPrice, String itemDescription) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemVAT = itemVAT;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.quantity = 0;
    }
    /**
     * Getter for item name
     * @return The items name
     */
    public String getItemName() {
        return this.itemName;
    }
    /**
     * Getter for item price
     * @return The price of the item
     */
    public Amount getItemPrice() {
        return this.itemPrice;
    }
    /**
     * Getter for itemDescription
     * @return The items description
     */
    public String getItemDescription() {
        return this.itemDescription;
    }
    /**
     * Getter for itemID
     * @return the items unique ID
     */
    public int getItemID() {
        return this.itemID;
    }
    /**
     * Getter for itemVAT
     * @return the VAT for the item
     */
    public int getItemVAT(){
        return this.itemVAT;
    }
    /**
     * Getter for items quantity
     * @return the quantity of the item
     */
    public int getQuantity(){
        return this.quantity;
    }
}
