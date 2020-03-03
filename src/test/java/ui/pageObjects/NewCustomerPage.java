package ui.pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.objectsUI.Customer;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class NewCustomerPage {

    private By xpathCompanyName = By.xpath("//label[text()='Company Name']/following-sibling::div/input");
    private By xpathContactPerson = By.xpath("//label[text()='Contact person']/following-sibling::div/input");
    private By xpathEmail = By.xpath("//label[text()='Email']/following-sibling::div/input");
    private By xpathPhone = By.xpath("//label[text()='Phone']/following-sibling::div/input");
    private By xpathCountry = By.xpath("//label[text()='Country']/following-sibling::div/div/input");
    private By xpathZipCode = By.xpath("//label[text()='Zip Code']/following-sibling::div/input");
    private By xpathAddress = By.xpath("//label[text()='Address']/following-sibling::input");

    public NewCustomerPage fillCompanyName(String companyName){
        $(xpathCompanyName).shouldBe(Condition.visible).setValue(companyName);
        return this;
    }

    public NewCustomerPage fillContactPerson(String contactPerson){
        $(xpathContactPerson).shouldBe(Condition.visible).setValue(contactPerson);
        return this;
    }

    public NewCustomerPage fillEmail(String email){
        $(xpathEmail).shouldBe(Condition.visible).setValue(email);
        return this;
    }

    public NewCustomerPage fillPhone(String phone){
        $(xpathPhone).shouldBe(Condition.visible).setValue(phone);
        return this;
    }


    public NewCustomerPage setCountry(String country){
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

    public NewCustomerPage fillZipCode(String zipCode){
        $(xpathZipCode).shouldBe(Condition.visible).setValue(zipCode);
        return this;
    }

    public NewCustomerPage fillAddress(String address){
        $(xpathAddress).shouldBe(Condition.visible).setValue(address);
        return this;
    }

    public AllCustomersPage clickSaveCustomerBtn(){
        $(By.xpath("//button[@type = 'submit']")).click();
        $(By.xpath("//button[@type = 'submit']")).shouldNot(Condition.disabled);
        return new AllCustomersPage();
    }

    public NewCustomerPage fillNewCustomerForm(Customer customer){
        fillCompanyName(customer.getCompanyName());
        fillContactPerson(customer.getContactPerson());
        fillEmail(customer.getEmail());
        fillPhone(customer.getPhone());
        setCountry(customer.getCountry());
        fillZipCode(customer.getZipCode());
        //fillAddress(customer.getAddress());

        return this;
    }



}
