package se.kth.DTOs;

/**
 * Represents represents amounts of a certain currency.
 */
public class Amount {
    private float amount;
    private String currency;
    /**
     * Constructor for Amount class which represents an amount of money.
     * 
     * @param amount   The amount of money.
     * @param currency The currency of the money.
     */
    public Amount(float amount) {
        this.amount = amount;
        this.currency = "kr";
    }

    /**
     * getter for currency
     * @return The cureency of the amount
     */
    public String getCurrency() {
        return this.currency;
    }

    /**
     * getter for amount
     * @return the amount value
     */
    public float getAmountValue() {
        return this.amount;
    }
}
