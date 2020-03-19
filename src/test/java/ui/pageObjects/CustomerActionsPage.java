package ui.pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.objectsUI.Customer;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class CustomerActionsPage extends BasePage{

    protected By xpathCompanyName = By.xpath("//label[text()='Company Name']/following-sibling::div/input");
    protected By xpathContactPerson = By.xpath("//label[text()='Contact person']/following-sibling::div/input");
    protected By xpathEmail = By.xpath("//label[text()='Email']/following-sibling::div/input");
    protected By xpathPhone = By.xpath("//label[text()='Phone']/following-sibling::div/input");
    protected By xpathCountry = By.xpath("//label[text()='Country']/following-sibling::div/div/span"); // <= not input
    protected By xpathPostalZipCode = By.xpath("//label[text()='Postal / Zip Code']/following-sibling::div/input");
    protected By xpathAddress = By.xpath("//label[text()='Address']/following-sibling::div/input");

    public CustomerActionsPage fillCompanyName(String companyName){
        $(xpathCompanyName).shouldBe(Condition.visible).setValue(companyName);
        return this;
    }

    public CustomerActionsPage fillContactPerson(String contactPerson){
        $(xpathContactPerson).shouldBe(Condition.visible).setValue(contactPerson);
        return this;
    }

    public CustomerActionsPage fillEmail(String email){
        $(xpathEmail).shouldBe(Condition.visible).setValue(email);
        return this;
    }

    public CustomerActionsPage fillPhone(String phone){
        $(xpathPhone).shouldBe(Condition.visible).setValue(phone);
        return this;
    }


    public CustomerActionsPage setCountry(String country){
        SelenideElement select = $(By.xpath("//label[text()='Country']/following-sibling::div[@aria-owns='listbox-null']")).scrollIntoView(true);
        sleep(1000);
        select.click();
        SelenideElement selectUl = select.$(By.xpath(".//ul[@id = 'listbox-null']")).shouldBe(Condition.visible);
        liFounderViaSpan(selectUl, country).shouldBe(Condition.visible).click();
        return this;
    }
    private SelenideElement liFounderViaSpan(SelenideElement ul, String point){
        List<SelenideElement> lis = ul.$$(By.xpath("./li")).shouldBe(CollectionCondition.sizeGreaterThan(2));
        SelenideElement foundLi = null;
        for (SelenideElement li: lis) {
            String spanText = li.$(By.xpath("./span/span")).getText();
            if(spanText.equals(point)){
                foundLi = li;
                break;
            }
        }
        foundLi.shouldBe(Condition.exist);
        return foundLi;
    }

    public CustomerActionsPage fillPostalZipCode(String postalZipCode){
        $(xpathPostalZipCode).shouldBe(Condition.visible).setValue(postalZipCode);
        return this;
    }

    public CustomerActionsPage fillAddress(String address){
        $(xpathAddress).shouldBe(Condition.visible).setValue(address);
        return this;
    }

    public AllCustomersPage clickSaveCustomerBtn(){
        $(By.xpath("//button[@type = 'submit']")).click();
        $(By.xpath("//button[@type = 'submit']")).shouldNot(Condition.disabled);
        return new AllCustomersPage();
    }

    public CustomerActionsPage fillCustomerForm(Customer customer){
        setCountry(customer.getCountry());
        fillCompanyName(customer.getCompanyName());
        fillContactPerson(customer.getContactPerson());
        fillEmail(customer.getEmail());
        fillPhone(customer.getPhone());
        fillPostalZipCode(customer.getPostalZipCode());
        fillAddress(customer.getAddress());

        return this;
    }



}
