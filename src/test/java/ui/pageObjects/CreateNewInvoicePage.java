package ui.pageObjects;

import ui.objectsUI.Invoice;

import static com.codeborne.selenide.Selenide.sleep;

public class CreateNewInvoicePage extends InvoiceActionsPage {



    public void fillInvoice(Invoice invoice){

        //Create or set customer
        if(invoice.getCreateNewCustomer()){
            createNewCustomer(invoice.getCustomer());
        } else if (!invoice.getCreateNewCustomer()){
            setCustomer(invoice.getCustomer());
        }

        // Set PO Number
        fillPONumber(invoice.getPoNumber());

        //Add Tax
        if(invoice.getTax() != 0.0) {
            addNewTax(invoice.getTax());
        }

        //Add Discount
        if(invoice.getDiscount() != 0.0){
            addDiscount(invoice.getDiscount());
        }

        //Add items
        fillItems(invoice.getItems());

        //Add payment method
        if(invoice.getPaymentMethod() != null){
            fillPaymentMethod(invoice.getPaymentMethod());
        }

    }
}
