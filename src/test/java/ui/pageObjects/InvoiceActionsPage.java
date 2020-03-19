package ui.pageObjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class InvoiceActionsPage {

    private By xpathInvoiceNumber = By.xpath("//label[normalize-space(text()) = 'Invoice Number']/following-sibling::div/input");
    private By xpathPONumber = By.xpath("//label[normalize-space(text()) = 'PO Number']/following-sibling::div/input");

    public InvoiceActionsPage fillInvoiceNumber(String invoiceNumber){
        $(xpathInvoiceNumber).shouldBe(Condition.visible).setValue(invoiceNumber);
        return this;
    }

    public InvoiceActionsPage fillPONumber(String poNumber){
        $(xpathPONumber).shouldBe(Condition.visible).setValue(poNumber);
        return this;
    }




}
