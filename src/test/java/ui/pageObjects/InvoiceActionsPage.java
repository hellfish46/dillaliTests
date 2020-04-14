package ui.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.objectsUI.Customer;
import ui.objectsUI.Invoice;
import ui.objectsUI.Item;

import java.util.HashMap;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

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
    private By xpathTotalAmount = By.xpath("//label[@class='invoice-amount total']/div");

    private By xpathSearchCustomerField = By.xpath("//input[@placeholder='Search']");

    private By xpathSelectedCustomerInfo = By.xpath("//div[@class='customer-content-info']");

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

    public void fillDiscount(double discount){
        String discountString = Double.toString(discount);
        $(xpathDiscount).scrollIntoView(true).shouldBe(Condition.visible).setValue(discountString);
    }

    public double getDiscount(){
        String discountString = $(xpathDiscount).scrollIntoView(true).shouldBe(Condition.visible).getValue();
        double discountDouble = 0.0;
        if(!discountString.equals("")){
            discountDouble = Double.parseDouble(discountString);
        }
        return  discountDouble;
    }

    private void addCustomerClick(){
        $(xpathAddCustomer).scrollIntoView(false).shouldBe(Condition.visible).click();
    }

    public InvoiceActionsPage createNewCustomer(Customer customer){
        addCustomerClick();
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
        int countOfItems = items.size();
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

    public long getTotalAmount(){
        String string = $(xpathTotalAmount).scrollIntoView(false).getText();
        String[] totalAmountArray = string.split(" ");
        String totalAmountString = totalAmountArray[1].replaceAll("[.,]", "");
        return Long.parseLong(totalAmountString);
    }

    public void searchCustomer(Customer customer){
        addCustomerClick();
        $(xpathSearchCustomerField).shouldBe(Condition.visible).setValue(customer.getCompanyName());
    }

    private void clickExactCustomer(List<SelenideElement> allCustomersOfSearch, Customer customer){
        for(SelenideElement item : allCustomersOfSearch){
            List<SelenideElement> companyNameAndName = item.$$x("./div/label");
            String companyName = companyNameAndName.get(0).getText();
            System.out.println(companyName);
            String customerName = companyNameAndName.get(1).getText();
            if(customer.getCompanyName().equals(companyName) && customer.getContactPerson().equals(customerName)){
                item.click();
                break;
            }
        }
    }

    public void setCustomer(Customer customer){
        searchCustomer(customer);
        SelenideElement list = $x("//div[@class='list']").shouldBe(Condition.visible);
        List<SelenideElement> allCustomersOfSearch = list.$$x("./div");
        clickExactCustomer(allCustomersOfSearch, customer);
    }

    public HashMap getSelectedCustomer(){
        SelenideElement selectedCustomerInfoDiv = $(xpathSelectedCustomerInfo)
                .scrollIntoView(true).shouldBe(Condition.visible);
        String customerCompanyName = selectedCustomerInfoDiv.$x("./label[1]").getText();
        String customerContactPerson = selectedCustomerInfoDiv.$x("./label[2]").getText();
        String customerCompanyAddress = selectedCustomerInfoDiv.$x("./label[3]").getText();
        String customerCountry = selectedCustomerInfoDiv.$x("./label[4]").getText();
        HashMap<String, String> customerInfo = new HashMap<String, String>();
        customerInfo.put("companyName", customerCompanyName);
        customerInfo.put("contactPerson", customerContactPerson);
        customerInfo.put("address", customerCompanyAddress);
        customerInfo.put("country", customerCountry);
        return customerInfo;
    }

    public void checkCustomerInfo(Customer customer){
        HashMap selectedCustomerInfo = getSelectedCustomer();
        System.out.println(selectedCustomerInfo);
        assertThat(selectedCustomerInfo.get("companyName")).isEqualTo(customer.getCompanyName());
        assertThat(selectedCustomerInfo.get("contactPerson")).isEqualTo(customer.getContactPerson());
        assertThat(selectedCustomerInfo.get("address")).isEqualTo(customer.getAddress());
        assertThat(selectedCustomerInfo.get("country")).isEqualTo(customer.getCountry());
    }









}
