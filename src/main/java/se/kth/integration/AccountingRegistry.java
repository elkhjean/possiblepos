package se.kth.integration;

import se.kth.DTOs.SaleDTO;
import se.kth.model.Payment;

/**
 * This is the applications accounting registry handler, all calls to external accounting system 
 * pass through objects of this class.
 */

public class AccountingRegistry {

    public void sendSaleInformation(SaleDTO finishedSaleDTO, Payment payment) {
        //placeholder
        //sends all available information about the sale and payment to the external accounting system
    }
    
}
