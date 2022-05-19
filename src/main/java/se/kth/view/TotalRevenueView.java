package se.kth.view;

import se.kth.DTOs.Amount;
import se.kth.model.PaymentObserver;

/**
 * observer class for payments made for sales, responsible for updating and
 * displaying total revenue.
 */
public class TotalRevenueView implements PaymentObserver {
    private Amount totalRevenue;

    /**
     * Constructor initializes totalRevenue amount to 0
     */
    public TotalRevenueView() {
        this.totalRevenue = new Amount(0);
    }

    @Override
    public void newPaymentMade(Amount paidAmount) {
        this.totalRevenue = new Amount(this.totalRevenue.plus(paidAmount));
        showTotalRevenue();
    }

    /**
     * Displays the total revenue.
     */
    private void showTotalRevenue() {
        System.out.println("Current daily total: " + totalRevenue.getAmountValue() + totalRevenue.getCurrency());
    }
}
