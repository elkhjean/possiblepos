package se.kth.DTOs;

import java.time.LocalTime;
import java.util.List;

public class SaleDTO {
    private LocalTime saleTime;
    private List<InventoryItemDTO> itemsInSale;
    private Amount runningTotal;
    private DiscountInfoDTO discountInfo;

    /**
     * constructor for SaleDTO
     * 
     * @param saleTime        The time of the sale start
     * @param runningTotal2   List with DTO of items
     * @param discountInfo2   The final running total
     * @param saleItemDtoList Discount information
     */
    public SaleDTO(LocalTime saleTime, Amount runningTotal, DiscountInfoDTO discountInfo,
            List<InventoryItemDTO> saleItemDtoList) {
        this.saleTime = saleTime;
        this.runningTotal = runningTotal;
        this.itemsInSale = saleItemDtoList;
        this.discountInfo = discountInfo;
    }

    public LocalTime getSaleTime() {
        return saleTime;
    }

    public List<InventoryItemDTO> getItemsInSale() {
        return itemsInSale;
    }

    public Amount getRunningTotal() {
        return runningTotal;
    }

    public DiscountInfoDTO getDiscountInfo() {
        return discountInfo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Time of sale: " + this.saleTime + "\n");
        for (InventoryItemDTO inventoryItemDTO : itemsInSale) {
            sb.append(inventoryItemDTO.toString());
        }
        sb.append("Total:" + runningTotal + "\n");
        return sb.toString();
    }
}
