package se.kth.model;

import se.kth.DTOs.Amount;
import se.kth.DTOs.SaleDTO;

/**
 * This is the payment class, it represents one payment made by a customer for
 * one sale.
 */
public class Payment {
    private Amount paidAmount;
    private Amount change;

    /**
     * Constructor for Payment class
     * 
     * @param paidAmount      An object of Amount class containing information about
     *                        the amount of money paid by customer
     * @param finishedSaleDTO A DTO containing information about the sale
     */
    public Payment(Amount paidAmount, SaleDTO finishedSaleDTO) {
        this.paidAmount = paidAmount;
        Amount change = new Amount(paidAmount.getAmountValue() - finishedSaleDTO.getRunningTotal().getAmountValue());
        this.change = change;
    }

    /**
     * Getters
     * 
     * @return
     */
    public Amount getPaidAmount() {
        return paidAmount;
    }

    public Amount getChange() {
        return change;
    }
}
