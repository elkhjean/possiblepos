package se.kth.model;

import se.kth.DTOs.SaleDTO;

/**
 * This is the reciept class it represents a reciept for one payment of one
 * sale.
 */
public class Reciept {
    private SaleDTO finishedSale;
    private Payment performedPayment;

    /**
     * Constructor for the Reciept class
     * 
     * @param finishedSale     A DTO containing sale information
     * @param performedPayment A DTO containing payment information
     */
    public Reciept(SaleDTO finishedSale, Payment performedPayment) {
        this.finishedSale = finishedSale;
        this.performedPayment = performedPayment;
    }

    /**
     * Getters
     * 
     * @return
     */
    public SaleDTO getSaleDTO() {
        return this.finishedSale;
    }

    public Payment getPayment() {
        return this.performedPayment;
    }
}
