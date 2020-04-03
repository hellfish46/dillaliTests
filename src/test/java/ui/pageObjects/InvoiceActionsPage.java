package ui.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.objectsUI.Customer;
import ui.objectsUI.Item;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class InvoiceActionsPage {

    private By xpathInvoiceNumber = By.xpath("//label[normalize-space(text()) = 'Invoice Number']/following-sibling::div/input");
    private By xpathPONumber = By.xpath("//label[normalize-space(text()) = 'PO Number']/following-sibling::div/input");
    private By xpathPaymentMethods = By.xpath("//div[label[text()='Payment methods']]/textarea");
    private By xpathDiscount = By.xpath("//input[@class='input-field item-discount']");


    private By xpathAddCustomer = By.xpath("//div[@class='add-customer-action']");
    private By xpathAddNewCustomerBtn = By.xpath("//label[text()='Add New Customer']");

    private By xpathSaveCustomerBtn = By.xpath("//div[@class='modal-body']//button[@type = 'submit']");

    private By xpathMainItemTable = By.xpath("//table[@class='item-table']");
    private By xpathLastTableItem = By.xpath(".//tr[last()]//table");
    private By xpathInputNameFieldOfParticularItem = By.xpath(".//div[@class='multiselect__tags']/input");
    private By xpathDescriptionOfParticularItem = By.xpath(".//textarea");
    private By xpathQuantityOfParticularItem = By.xpath(".//input[@class='input-field small-input']");
    private By xpathPriceOfParticularItem = By.xpath(".//input[@class='v-money input-field']");

    private By xpathAddItemFormBtn = By.xpath("//div[@class='add-item-action']");

    private By xpathSubTotalCounter = By.xpath("//label[@class='invoice-amount']/div");

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
        SelenideElement mainTable = $(xpathMainItemTable).shouldBe(Condition.visible).scrollIntoView(true);
        SelenideElement lastItem = mainTable.$(xpathLastTableItem).shouldBe(Condition.visible);
        //fill item name
        lastItem.$(xpathInputNameFieldOfParticularItem).setValue(item.getName());
        //fill item description if it exists
        if(item.getDescription() != null) {
            lastItem.$(xpathDescriptionOfParticularItem).setValue(item.getDescription());
        }
        //fill quantity
        if (item.getQuantity() != 0) {
            lastItem.$(xpathQuantityOfParticularItem).setValue(Integer.toString(item.getQuantity()));
        }
        //fill price
        lastItem.$(xpathPriceOfParticularItem).setValue(Integer.toString(item.getPrice()));

    }

    public void clickAddItemFormBtn(){
        $(xpathAddItemFormBtn).scrollIntoView(true).shouldBe(Condition.visible).click();
    }

    public void fillItems(List<Item> items){
        int countOfItems = items.size(); //5
        int howMuchClickedAddNewItemBtn = 0;

        for(Item item : items){
            fillItem(item);
            if(howMuchClickedAddNewItemBtn != countOfItems-1){
                clickAddItemFormBtn();
                howMuchClickedAddNewItemBtn++;
            }

        }
    }

    public long getSubTotal(){
        String string = $(xpathSubTotalCounter).scrollIntoView(false).getText();
        String[] subTotalArray = string.split(" ");
        String fullSubTotalString = subTotalArray[1].replaceAll("[.,]", "");
        return Long.parseLong(fullSubTotalString);
    }







}
