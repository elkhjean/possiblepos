package se.kth.integration;

import se.kth.DTOs.Amount;
import se.kth.model.Payment;

/**
 * This is the cash register class. Calls to cash register system pass through 
 * objects of this class.
 */
public class CashRegister {
    private Amount balance;

    /**
     * Constructor for cashRegister class
     */
    public CashRegister(){
        Amount balance = new Amount(2000);
        this.balance = balance;
    }

    /**
     * Getter for Amount balance in cash register
     * @return An object of class Amount holding balance.
     */
    public Amount getBalance(){
        return this.balance;
    }

    /**
     * Method to update balance in cashRegister
     * @param payment The payment made by customer.
     */
    public void updateBalance(Payment payment) {
        Amount change = payment.getChange();
        Amount paidAmount = payment.getPaidAmount();
        Amount amountToAdd = new Amount(paidAmount.getAmountValue() - change.getAmountValue());
        Amount newBalance = new Amount(this.balance.getAmountValue() + amountToAdd.getAmountValue());
        this.balance = newBalance;
    }
    
}
