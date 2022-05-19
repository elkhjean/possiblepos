package se.kth.view;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

import se.kth.DTOs.Amount;
import se.kth.model.PaymentObserver;

/**
 * Observer class for new payments made for sales, responsible for updating and
 * loggning total revenue.
 */
public class TotalRevenueFileOutput implements PaymentObserver {
    private PrintWriter logStream;
    private Amount totalRevenue;

    /**
     * Constructor for the TotalRevenueFleOutput class, sets up a printWriter and
     * creates a .txt file.
     */
    public TotalRevenueFileOutput() {
        this.totalRevenue = new Amount(0);
        try {
            this.logStream = new PrintWriter(new FileWriter("revenueLog.txt"), true);
        } catch (IOException ioe) {
            System.out.println("COULD NOT CREATE LOG");
            ioe.printStackTrace();
        }
    }

    @Override
    public void newPaymentMade(Amount paidAmount) {
        this.totalRevenue = new Amount(this.totalRevenue.plus(paidAmount));
        logRevenue();
    }

    /**
     * The total revenue is logged to a log file.
     */
    private void logRevenue() {
        logStream.println("Total Revenue: " + this.totalRevenue.getAmountValue() + this.totalRevenue.getCurrency());
    }

}
