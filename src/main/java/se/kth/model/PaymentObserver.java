package se.kth.model;

import se.kth.DTOs.Amount;

/**
 * This is an observer interface for the recieving notifications about payments
 * This interface is implemented by such classes that are interested in
 * notifications of when a new payment has been made.
 */
public interface PaymentObserver {
    /**
     * Method that is called when a new payment has been created.
     * 
     * @param paidAmount the payment for a finished sale.
     */
    void newPaymentMade(Amount paidAmount);
}
