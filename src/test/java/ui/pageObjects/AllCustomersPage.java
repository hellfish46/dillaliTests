package ui.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.objectsUI.Customer;

import java.util.HashMap;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class AllCustomersPage {

    private By firstCustomerInList = By.xpath("//tbody/tr[1]");

    public AllCustomersPage customersClick(){
        $(By.xpath("//span[text()='Customers']")).shouldBe(Condition.visible).click();
        return this;
    }

    public NewCustomerPage newCustomerClick(){
        $(By.xpath("//a[@href='/admin/customers/create']/button")).shouldBe(Condition.visible).click();
        return new NewCustomerPage();
    }

    public void checkFirstCustomerInList(Customer customer){
        SelenideElement firstTr = $(firstCustomerInList).shouldBe(Condition.visible);
        HashMap parsedCustomer = getInfoFromTr(firstTr);
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

    private void clickFirstCustomerSettings(int trPosition){
        $(By.xpath("//tbody/tr[" + trPosition + "]//div[@class='dot-icon']")).shouldBe(Condition.visible).click();
    }

    public AllCustomersPage clickEditCustomer(int trPosition){
        clickFirstCustomerSettings(trPosition);
        $(By.xpath("//tbody/tr[" + trPosition + "]//a[@class='dropdown-item']")).shouldBe(Condition.visible).click();
        return this;
    }






}
