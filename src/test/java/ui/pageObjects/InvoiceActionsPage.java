package ui.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.objectsUI.Customer;
import ui.objectsUI.Invoice;
import ui.objectsUI.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;

public class InvoiceActionsPage extends BasePage{

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

    private By xpathAddTaxBtn = By.xpath("//div[normalize-space(text())='+ Add Tax']");

    private By xpathSaveBtn = By.xpath("//button[@type = 'submit']");



    private class Calendar {
        private String xpathDueDate = "//label[normalize-space(text()) = 'Due Date']/following-sibling::div//input";
        private String xpathInvoiceDate = "//label[normalize-space(text()) = 'Invoice Date']/following-sibling::div//input";
        private String xpathMainCalendarPanel = "./../following-sibling::div[@class = 'vdp-datepicker__calendar'][1]";
        private String xpathMainYearSelectPanel = "./../following-sibling::div[@class = 'vdp-datepicker__calendar'][2]";

        private String xpathPrevArrow = "./header/span[@class = 'prev']";
        private String xpathNextArrow = "./header/span[@class = 'next']";

        private String xpathMonthYear = "./../following-sibling::div[1]//span[@class='day__month_btn up']";
        private String xpathYear = "./../following-sibling::div[2]//span[@class='month__year_btn up']";


        private void clickMonthYearBtn(String inputName){
            $x(inputName).$x(xpathMonthYear).shouldBe(Condition.visible).click();
        };

        private void clickDueDateInput() {
            $x(xpathDueDate).scrollIntoView(false).shouldBe(Condition.visible).click();
            $x(xpathDueDate).$x(xpathMainCalendarPanel).scrollIntoView(true).shouldBe(Condition.visible);
        }

        private void clickInvoiceDate(){
            $x(xpathInvoiceDate).scrollIntoView(false).shouldBe(Condition.visible).click();
            $x(xpathInvoiceDate).$x(xpathMainCalendarPanel).scrollIntoView(true).shouldBe(Condition.visible);
        }

        private void clickSetDateInput(String xpathInput) {
            $x(xpathInput).scrollIntoView(false).shouldBe(Condition.visible).click();
            $x(xpathInput).$x(xpathMainCalendarPanel).scrollIntoView(true).shouldBe(Condition.visible);
        }

        private Map<String, String> getDateInInput (String inputName){
            String fullDate = $x(inputName).scrollIntoView(false).shouldBe(Condition.visible).getValue();
            String[] fullDateArray = fullDate.split(" ");
            Map<String, String> fullDateMap = new HashMap<String, String>();

            fullDateMap.put("day", fullDateArray[0]);
            fullDateMap.put("month", fullDateArray[1]);
            fullDateMap.put("year", fullDateArray[2]);

            return fullDateMap;
        }

        private void setDayInCalendar(int day, String inputName){
            $x(inputName).$x(xpathMainCalendarPanel).$x("./div/span[text()='"+ day +"']")
                    .scrollIntoView(false).shouldBe(Condition.visible).click();
        }

        private void setMonthInCurrentYear (int monthIndex, String inputName){
            Map<String, String> currentDate = getDateInInput(inputName);
            int indexOfCurrentMonth = Month.valueOf(currentDate.get("month").toUpperCase()).ordinal();
            int internalMonthIndex = monthIndex - 1;

            if(internalMonthIndex == indexOfCurrentMonth){
                return;
            }
            if(indexOfCurrentMonth < internalMonthIndex){
                int steps = internalMonthIndex - indexOfCurrentMonth;
                $x(inputName).$x(xpathMainCalendarPanel).$x(xpathNextArrow).scrollIntoView(false);
                for(int i = 1; i <= steps; i++){
                    $x(inputName).$x(xpathMainCalendarPanel).$x(xpathNextArrow).shouldBe(Condition.visible).click();
                }
            }
            if(indexOfCurrentMonth > internalMonthIndex){
                int steps = indexOfCurrentMonth - internalMonthIndex;
                $x(inputName).$x(xpathMainCalendarPanel).$x(xpathPrevArrow).scrollIntoView(false);
                for(int i = 1; i <= steps; i++){
                    $x(inputName).$x(xpathMainCalendarPanel).$x(xpathPrevArrow).shouldBe(Condition.visible).click();
                }
            }
        }

        private void setYear (int year, String inputName){
            Map<String, String> currentDate = getDateInInput(inputName);
            int yearInInputInt = parseInt(currentDate.get("year"));
            if(year == yearInInputInt){
                return;
            }
            if (yearInInputInt < year){
                int steps = year - yearInInputInt;
                for(int i = 1; i <= steps; i++){
                    $x(inputName).$x(xpathMainYearSelectPanel).$x(xpathNextArrow).shouldBe(Condition.visible).click();
                }
            }
            if(yearInInputInt > year){
                int steps = yearInInputInt - year;
                for(int i = 1; i <= steps; i++){
                    $x(inputName).$x(xpathMainYearSelectPanel).$x(xpathPrevArrow).shouldBe(Condition.visible).click();
                }
            }

        }

        private void setMothInNotCurrentYear(int monthIndex, String inputName){
            int internalMonthIndex = monthIndex - 1;
            String fullNameOfMonth = Month.values()[internalMonthIndex].getFullNameOfMonth();
            String xpathFullMonthName = "./span[text()='"+ fullNameOfMonth +"']";
            $x(inputName).$x(xpathMainYearSelectPanel).$x(xpathFullMonthName).shouldBe(Condition.visible).click();
        }

        private void setDate(String xpathInput,String dueDate){
            clickSetDateInput(xpathInput);

            String[] dueDateArray = dueDate.split("\\.");
            Map<String, String> currentDate = getDateInInput(xpathInput);

            int dayInt = parseInt(dueDateArray[0]);
            int monthInt = parseInt(dueDateArray[1]);
            int yearInt = parseInt(dueDateArray[2]);

            if(!dueDateArray[2].equals(currentDate.get("year"))){
                $x(xpathInput).$x(xpathMonthYear).scrollIntoView(false).shouldBe(Condition.visible).click();
                setYear(yearInt, xpathInput);
                assertThat(yearInt).isEqualTo(parseInt($x(xpathInput).$x(xpathYear).getText()));
                setMothInNotCurrentYear(monthInt,xpathInput);
            }
            else if (dueDateArray[2].equals(currentDate.get("year"))) {
                setMonthInCurrentYear(monthInt, xpathInput);
            }
            setDayInCalendar(dayInt,xpathInput);
        }

        public void setDueDate(String dueDate){
            setDate(xpathDueDate, dueDate);
        }

        public void setInvoiceDate(String invoiceDate){
            setDate(xpathInvoiceDate, invoiceDate);
        }

    }

    Calendar calendar = new Calendar();

    public void setDueDate(String dueDate){
        calendar.setDueDate(dueDate);
    }

    public void setInvoiceDate(String invoiceDate){
        calendar.setInvoiceDate(invoiceDate);
    }




    public InvoiceActionsPage fillInvoiceNumber(String invoiceNumber){
        $(xpathInvoiceNumber).scrollIntoView(true).shouldBe(Condition.visible).setValue(invoiceNumber);
        return this;
    }

    public void fillPONumber(String poNumber){
        $(xpathPONumber).scrollIntoView(true).shouldBe(Condition.visible).setValue(poNumber);
    }

    public InvoiceActionsPage fillPaymentMethod(String paymentMethod){
        $(xpathPaymentMethods).scrollIntoView(true).shouldBe(Condition.visible).setValue(paymentMethod);
        return this;
    }

    public void addDiscount(double discount){
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

        //fill price
        lastItem.$(xpathPriceOfParticularItem).setValue(String.format("%.0f", item.getPrice() * 100));
        //fill item name
        if(item.getName() != null) {
            lastItem.$(xpathInputNameFieldOfParticularItem).setValue(item.getName());
        }
        //fill item description if it exists
        if(item.getDescription() != null) {
            lastItem.$(xpathDescriptionOfParticularItem).setValue(item.getDescription());
        }
        //fill quantity
        if (item.getQuantity() != 0) {
            lastItem.$(xpathQuantityOfParticularItem).setValue(Integer.toString(item.getQuantity()));
        }

    }

    public void clickAddItemFormBtn(){
        $(xpathAddItemFormBtn).scrollIntoView(true).shouldBe(Condition.visible).click();
    }

    public void fillItems(List<Item> items){
        int countOfItems = items.size();
        int howMuchClickedAddNewItemBtn = 0;

        for(Item item : items){
            fillItem(item);
            //sleep(1000);
            if(howMuchClickedAddNewItemBtn != countOfItems-1){
                clickAddItemFormBtn();
                howMuchClickedAddNewItemBtn++;
            }

        }
    }

    public double getSubTotal(){
        String string = $(xpathSubTotalCounter).shouldBe(Condition.visible).scrollIntoView(true).getText();
        String[] subTotalArray = string.split(" ");
//        String fullSubTotalString = subTotalArray[1].replaceAll("[.,]", "");
//        return Long.parseLong(fullSubTotalString);
//        String fullSubTotalString = subTotalArray[1].replaceAll("[,]", "");
//        return new BigDecimal(fullSubTotalString);
        String fullSubTotalString = subTotalArray[1].replaceAll("[,]", "");
        return Double.parseDouble(fullSubTotalString);
    }

    public double getTotalAmount(){
        String string = $(xpathTotalAmount).shouldBe(Condition.visible).scrollIntoView(true).getText();
        String[] totalAmountArray = string.split(" ");
//        String totalAmountString = totalAmountArray[1].replaceAll("[.,]", "");
//        return Long.parseLong(totalAmountString);
        String totalAmountString = totalAmountArray[1].replaceAll("[,]", "");
        return Double.parseDouble(totalAmountString);
    }

    private void searchCustomer(Customer customer){
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

    public void clickAddTaxBtn(){
        $(xpathAddTaxBtn).scrollIntoView(true).shouldBe(Condition.visible).click();
    }

    public void addNewTax(double tax){
        String taxString = String.format("%.0f", tax * 100);
        String taxName = tax + " %";

        clickAddTaxBtn();
        $x("//div[@class='tax-select']//button[@class='list-add-button']//label").
                shouldBe(Condition.visible).click();
        $x("//h5[@class='modal-heading']").shouldBe(Condition.visible);
        $x("//div[@class='modal-body']//form//input[@class='input-field']").
                shouldBe(Condition.visible).setValue(taxName);

        $x("//div[@class='modal-body']//form//input[@class='v-money input-field']").
                shouldBe(Condition.visible).setValue(taxString);

        $x("//div[@class='modal-body']//button[@class='base-button btn btn-primary default-size ']").
                shouldBe(Condition.visible).click();
    }


    private double cutNumberAfterComma(double hardDoube){
       double calculatedTax = (double)Math.round(hardDoube * 100d) / 100d;
       return calculatedTax;
    }

    public void checkTotalAmount(Invoice invoice){
        double totalAmount = getTotalAmount();
        double subTotal = getSubTotal();

        double discount = invoice.getDiscount();
        double tax = invoice.getTax();

        List<Item> items = invoice.getItems();
        double sumOfMoney = 0.0;
        for(Item item : items){
            sumOfMoney = sumOfMoney + item.getAmount();
        }
        double calculatedSumAndDiscount = (sumOfMoney - ((sumOfMoney * discount)/100));
        double taxWithoutRounded = ((calculatedSumAndDiscount * tax)/100);
        double calculatedTax = cutNumberAfterComma(taxWithoutRounded);
        double calculatedTotalAmount = cutNumberAfterComma(calculatedSumAndDiscount + calculatedTax);


        System.out.println("Total Amount on the page = "+ totalAmount);
        System.out.println("Calculated Total Amount  = "+ calculatedTotalAmount);
        System.out.println("Sub Total on the page = "+ subTotal);
        System.out.println("Calculated Sub Total = " + sumOfMoney);

        assertThat(totalAmount).isEqualTo(calculatedTotalAmount);
        assertThat(sumOfMoney).isEqualTo(subTotal);

    }


    public void clickSaveBtn(){
        $(xpathSaveBtn).scrollIntoView(true).shouldBe(Condition.visible).click();
        $(xpathSaveBtn).shouldNotBe(Condition.disabled);
    };















}
