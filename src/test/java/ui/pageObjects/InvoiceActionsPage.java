package ui.pageObjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ui.objectsUI.Customer;
import ui.objectsUI.Item;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class InvoiceActionsPage {

    private By xpathInvoiceNumber = By.xpath("//label[normalize-space(text()) = 'Invoice Number']/following-sibling::div/input");
    private By xpathPONumber = By.xpath("//label[normalize-space(text()) = 'PO Number']/following-sibling::div/input");
    private By xpathPaymentMethods = By.xpath("//div[label[text()='Payment methods']]/textarea");
    private By xpathDiscount = By.xpath("//input[@class='input-field item-discount']");

    private By xpathAddCustomer = By.xpath("//div[@class='add-customer-action']");
    private By xpathAddNewCustomerBtn = By.xpath("//label[text()='Add New Customer']");

    private By xpathSaveCustomerBtn = By.xpath("//div[@class='modal-body']//button[@type = 'submit']");

    public InvoiceActionsPage fillInvoiceNumber(String invoiceNumber){
        $(xpathInvoiceNumber).scrollIntoView(true).shouldBe(Condition.visible).setValue(invoiceNumber);
        return this;
    }

    public InvoiceActionsPage fillPONumber(String poNumber){
        $(xpathPONumber).scrollIntoView(true).shouldBe(Condition.visible).setValue(poNumber);
        return this;
    }

    public InvoiceActionsPage fillPaymentMethod(String paymentMethod){
        $(xpathPaymentMethods).scrollIntoView(true).shouldBe(Condition.visible).setValue(paymentMethod);
        return this;
    }

    public InvoiceActionsPage fillDiscount(double discount){
        String discountString = Double.toString(discount);
        $(xpathDiscount).scrollIntoView(true).shouldBe(Condition.visible).setValue(discountString);
        return this;
    }

    public InvoiceActionsPage createNewCustomer(Customer customer){
        $(xpathAddCustomer).scrollIntoView(false).shouldBe(Condition.visible).click();
        $(xpathAddNewCustomerBtn).shouldBe(Condition.visible).click();
        $x("//div[@class='modal-body']").shouldBe(Condition.visible);

        CreateNewCustomerPage createNewCustomerPage = new CreateNewCustomerPage();
        createNewCustomerPage.fillCustomerForm(customer);

        $(xpathSaveCustomerBtn).shouldBe(Condition.visible).click();
        $(xpathSaveCustomerBtn).shouldNot(Condition.disabled);
        return this;
    }

    public void fillItem(Item item){

    }




}
