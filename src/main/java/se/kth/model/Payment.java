package se.kth.model;

import java.util.ArrayList;
import java.util.List;

import se.kth.DTOs.Amount;
import se.kth.DTOs.SaleDTO;

/**
 * This is the payment class, it represents one payment made by a customer for
 * one sale.
 */
public class Payment {
    private Amount priceToBePaid;
    private Amount paidAmount;
    private Amount change;
    private List<PaymentObserver> paymentObservers = new ArrayList<>();

    /**
     * Constructor for Payment class
     * 
     * @param paidAmount       An object of Amount class containing information
     *                         about
     *                         the amount of money paid by customer
     * @param finishedSaleDTO  A DTO containing information about the sale
     * 
     * @param paymentObservers a list of objects that implements the PaymentObserver
     *                         interface
     */
    public Payment(Amount paidAmount, SaleDTO finishedSaleDTO, List<PaymentObserver> paymentObservers) {
        this.paidAmount = paidAmount;
        this.priceToBePaid = finishedSaleDTO.getRunningTotal();
        this.change = new Amount(paidAmount.minus(priceToBePaid));
        this.paymentObservers = paymentObservers;
        notifyObservers();
    }

    private void notifyObservers() {
        for (PaymentObserver paymentObserver : paymentObservers) {
            paymentObserver.newPaymentMade(this.priceToBePaid);
        }
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

    public Amount getPriceToBePaid() {
        return priceToBePaid;
    }
}
