package se.kth.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import se.kth.DTOs.Amount;
import se.kth.DTOs.DiscountInfoDTO;
import se.kth.DTOs.InventoryItemDTO;
import se.kth.DTOs.SaleDTO;

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
        this.runningTotal = new Amount(0);
    }

    /**
     * getter for running total
     * 
     * @return The current running total for the sale
     */
    public Amount getRunningTotal() {
        return this.runningTotal;
    }

    /**
     * getter for saleTime
     * 
     * @return The start time for the sale
     */
    public LocalTime getSaleTime() {
        return this.saleTime;
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
            if (searchedItem.getItemDTO().getItemID() == itemID)
                return searchedItem;
        }
        return null;
    }

    /**
     * creates a new Item object in the sale.
     * 
     * @param itemToCreateDTO A DTO if the item to be created.
     * @return The created Item
     */
    public Item createItemInSale(InventoryItemDTO itemToCreateDTO) {
        Item itemToCreate = new Item(itemToCreateDTO);
        itemsInSale.add(itemToCreate);
        return itemToCreate;
    }

    /**
     * updates the running total
     * 
     * @param itemToAdd The item which price to add
     * @param quantity  How many of the items that are added
     */
    public void updateRunningTotal(Item itemToAdd, int quantity) {
        Amount itemPriceWithVat = itemToAdd.calculatePriceForItemWithVat();
        Amount newRunningTotal = new Amount(
                this.runningTotal.getAmountValue() + (itemPriceWithVat.getAmountValue() * quantity));
        this.runningTotal = newRunningTotal;
    }

    /**
     * Method to generate a DTO of the complete sale
     * 
     * @return Created instance of saleDTO
     */
    public SaleDTO generateSaleDTO() {
        List<InventoryItemDTO> saleItemDtoList = new ArrayList<>();
        for (Item item : itemsInSale) {
            InventoryItemDTO itemDtoToAdd = new InventoryItemDTO(item.getItemDTO(), item.getQuantity());
            saleItemDtoList.add(itemDtoToAdd);
        }
        SaleDTO saleDtoToGenerate = new SaleDTO(this.saleTime, this.runningTotal, this.discountInfo, saleItemDtoList);
        return saleDtoToGenerate;
    }
}
