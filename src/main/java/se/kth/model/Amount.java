package se.kth.model;
/**
 * Represents represents amounts of a certain currency.
 */
public class Amount {
    private int amount;
    private String currency;

    /**
     * Constructor for Amount class which represents an amount of money.
     * @param amount The amount of money.
     * @param currency The currency of the money.
     */
    public Amount(int amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }

/**
 * This is the toString method for the amount class. It overrides the standard toString method.
 * @return A string with information about the amount.
 */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(amount);
        sb.append(currency);
        return sb.toString();
    }
}
