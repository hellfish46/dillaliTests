package ui.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.objectsUI.Customer;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class AllCustomersPage extends BasePage{

    private By firstCustomerInList = By.xpath("//tbody/tr[1]");

    public AllCustomersPage customersClick(){
        $(By.xpath("//span[text()='Customers']")).shouldBe(Condition.visible).click();
        return this;
    }

    public CreateNewCustomerPage newCustomerClick(){
        $(By.xpath("//a[@href='/admin/customers/create']/button")).shouldBe(Condition.visible).click();
        return new CreateNewCustomerPage();
    }

    public void checkFirstCustomerInList(Customer customer){
        SelenideElement firstTr = $(firstCustomerInList).shouldBe(Condition.visible);
        HashMap parsedCustomer = getInfoFromTr(firstTr);
        assertThat(customer.getCompanyName()).isEqualTo(parsedCustomer.get("companyName"));
        assertThat(customer.getContactPerson()).isEqualTo(parsedCustomer.get("contactPerson"));
        assertThat(customer.getPhone()).isEqualTo(parsedCustomer.get("phone"));
        assertThat(customer.getEmail()).isEqualTo(parsedCustomer.get("email"));
    }

    public void checkCustomerInList(int position, Customer customer){
        SelenideElement setTr = $(By.xpath("//tbody/tr[" + position + "]"));
        HashMap parsedCustomer = getInfoFromTr(setTr);
        assertThat(customer.getCompanyName()).isEqualTo(parsedCustomer.get("companyName"));
        assertThat(customer.getContactPerson()).isEqualTo(parsedCustomer.get("contactPerson"));
        assertThat(customer.getPhone()).isEqualTo(parsedCustomer.get("phone"));
        assertThat(customer.getEmail()).isEqualTo(parsedCustomer.get("email"));

    }

    private HashMap getInfoFromTr(SelenideElement tr){
        String companyName = tr.$(By.xpath("./td[span[text()='Company name']]")).getText();
        String contactPerson = tr.$(By.xpath("./td[span[text()='Contact person']]")).getText();
        String phone = tr.$(By.xpath("./td[span[text()='Phone']]")).getText();
        String email = tr.$(By.xpath("./td[span[text()='Email']]")).getText();

        HashMap<String, String> customer = new HashMap<String, String>();
        customer.put("companyName", companyName);
        customer.put("contactPerson", contactPerson);
        customer.put("phone", phone);
        customer.put("email", email);

        return customer;
    }

    private void clickCustomerSettings(int trPosition){
        $(By.xpath("//tbody/tr[" + trPosition + "]//div[@class='dot-icon']")).shouldBe(Condition.visible).click();
    }

    public AllCustomersPage clickEditCustomer(int trPosition){
        clickCustomerSettings(trPosition);
        $(By.xpath("//tbody/tr[" + trPosition + "]//a[@class='dropdown-item']")).shouldBe(Condition.visible).click();
        $(By.xpath("//h3[text()='Edit Customer']")).shouldBe(Condition.visible);
        return this;
    }

    public AllCustomersPage deleteCustomer(int trPosition){

        clickCustomerSettings(trPosition);
        $(By.xpath("//tbody/tr[" + trPosition + "]//div[@class='dropdown-item']")).shouldBe(Condition.visible).click();
        $(By.xpath("//div[@class='swal-modal']//button[text()='OK']")).shouldBe(Condition.visible).click();


        return this;
    }

    public int getCountOfCustomers(){
        $(By.xpath("//h3[text()='Customers']")).shouldBe(Condition.visible);
        $(By.xpath("//tbody")).shouldBe(Condition.visible);
        String countOfCustomersString = $(By.xpath("//p[@class='table-stats']/b[2]"))
                .shouldBe(Condition.visible).getText();
        int countOfCustomerInt = Integer.parseInt(countOfCustomersString);
        return countOfCustomerInt;
    }






}
