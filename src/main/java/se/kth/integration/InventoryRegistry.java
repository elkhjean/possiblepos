package se.kth.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.DTOs.Amount;
import se.kth.DTOs.InventoryItemDTO;
import se.kth.DTOs.SaleDTO;

/**
 * This is the applications inventory registry handler, all calls to external
 * inventory system
 * pass through objects of this class.
 */
public class InventoryRegistry {
    private List<InventoryItemDTO> itemInventoryList;

    /**
     * The inventoryRegistry constructor
     */
    public InventoryRegistry() {
        itemInventoryList = new ArrayList<>();
        Amount tomatoPrice = new Amount(6);
        InventoryItemDTO tomatoItemDTO = new InventoryItemDTO(100, "tomato", 12, tomatoPrice, "sun ripened italian tomato");
        itemInventoryList.add(tomatoItemDTO);
        Amount lettucePrice = new Amount(20);
        InventoryItemDTO lettuceItemDTO  = new InventoryItemDTO(200, "lettuce", 12, lettucePrice, "spanish A-class lettuce");
        itemInventoryList.add(lettuceItemDTO);
    }

    /**
     * Checks for item with matching id in inventory, creates and returns an itemDTO
     * if match is found.
     * 
     * @param itemID unique item identifier for searched item.
     * @return The created itemDTO if match is found or null if no match was found.
     */
    public InventoryItemDTO fetchItemFromInventory(int itemID, int quantity) {
        for (InventoryItemDTO itemToBeFetched : itemInventoryList) {
            if (itemToBeFetched.getItemID() == itemID) {
                InventoryItemDTO foundItemDTO = new InventoryItemDTO(itemToBeFetched, quantity);
                return foundItemDTO;
            }
        }
        return null;
    }

    /**
     * Method to update inventory status in inventory database
     * @param finishedSaleDTO A DTO of the finished sale containing all items
     */
    public void updateInventory(SaleDTO finishedSaleDTO) {
        //placeholder
        List<InventoryItemDTO> itemsToUpdateInInventory = finishedSaleDTO.getItemsInSale();
        //Extracts the list of items sold and calls methods inside database to update inventory status.
    }
}
