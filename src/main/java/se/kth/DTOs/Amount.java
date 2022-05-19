package se.kth.DTOs;

/**
 * Represents represents amounts of a certain currency.
 */
public final class Amount {
    private final float amount;
    private final String currency;

    /**
     * Constructor for Amount class
     * 
     * @param amount   The amount of money.
     * @param currency The currency of the money.
     */
    public Amount(float amount) {
        this.amount = amount;
        this.currency = "kr";
    }

    /**
     * Constructor for Amount class that copies existing amount
     * 
     * @param amountToCopy the amount to copy
     */
    public Amount(Amount amountToCopy) {
        this.amount = amountToCopy.getAmountValue();
        this.currency = amountToCopy.getCurrency();
    }

    /**
     * Calculates the result of a subtraction between this amount and another.
     * 
     * @param otherAmount amount to subtract.
     * @return the resulting amount after subtraction.
     */
    public Amount minus(Amount otherAmount) {
        return new Amount(this.amount - otherAmount.amount);
    }

    /**
     * Calculates the result of an addition between this amount and another.
     * 
     * @param otherAmount amount to add.
     * @return the resulting amount after addition.
     */
    public Amount plus(Amount otherAmount) {
        return new Amount(this.amount + otherAmount.amount);
    }

    /**
     * getter for currency
     * 
     * @return The cureency of the amount
     */
    public String getCurrency() {
        return this.currency;
    }

    /**
     * getter for amount
     * 
     * @return the amount value
     */
    public float getAmountValue() {
        return this.amount;
    }
}
