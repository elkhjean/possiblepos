package se.kth.model;

import se.kth.DTOs.Amount;
import se.kth.DTOs.InventoryItemDTO;

/**
 * Represents an item fetched from the external inventory system.
 */
public class Item {
    private InventoryItemDTO itemDTO;
    private int itemQuantity;

    /**
     * This is the onstructor for the Item class.
     * 
     * @param itemToCopy An object of the item to copy.
     */
    public Item(InventoryItemDTO itemToCopy) {
        this.itemDTO = itemToCopy;

    }

    /**
     * Getter for item's DTO
     * 
     * @return an instance of itemDTO
     */
    public InventoryItemDTO getItemDTO() {
        return this.itemDTO;
    }

    /**
     * getter for item's quantity
     * 
     * @return quantity of the item
     */
    public int getQuantity() {
        return this.itemQuantity;
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
     * Method to calculate price of one item with VAT
     * 
     * @return A isntance of Amount representing the items price after VAT
     */
    public Amount calculatePriceForItemWithVat() {
        Amount itemPrice = this.itemDTO.getItemPrice();
        Amount priceWithVAT = new Amount(
                itemPrice.getAmountValue() + (itemPrice.getAmountValue() * this.itemDTO.getItemVAT() / 100));
        return priceWithVAT;
    }

    /**
     * Method to retrieve an itemDTO that also contains items quantity
     * 
     * @return An instance of InventoryItemDTO
     */
    public InventoryItemDTO getItemDTOWithQuantity() {
        InventoryItemDTO dtoWithQuantity = new InventoryItemDTO(this.itemDTO, this.itemQuantity);
        return dtoWithQuantity;
    }
}
