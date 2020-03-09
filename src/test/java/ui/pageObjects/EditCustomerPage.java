package ui.pageObjects;

import com.codeborne.selenide.Condition;
import ui.objectsUI.Customer;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class EditCustomerPage extends CustomerActionsPage {

    private Condition contentIsEmptyCondition = Condition.attribute("value","");

    private String getCompanyName(){
        return $(xpathCompanyName).shouldNotHave(contentIsEmptyCondition).getValue();
    }
    private String getContactPerson(){
        return $(xpathContactPerson).shouldNotHave(contentIsEmptyCondition).getValue();
    }
    private String getEmail(){
        return $(xpathEmail).shouldNotHave(contentIsEmptyCondition).getValue();
    }
    private String getPhone(){
        return $(xpathPhone).getValue();
    }
    private String getCountry(){
        return $(xpathCountry).shouldBe(Condition.visible).getText();
    }

    private String getPostalZipCode(){
        return $(xpathPostalZipCode).getValue();
    }
    private String getAddress(){
        return $(xpathAddress).shouldNotHave(contentIsEmptyCondition).getValue();

    }

    public void checkCustomerInfo(Customer customer){

        Customer customerOnEditPage = new Customer();
        customerOnEditPage.setAddress(getAddress());
        customerOnEditPage.setPostalZipCode(getPostalZipCode());
        customerOnEditPage.setCountry(getCountry());
        customerOnEditPage.setPhone(getPhone());
        customerOnEditPage.setEmail(getEmail());
        customerOnEditPage.setContactPerson(getContactPerson());
        customerOnEditPage.setCompanyName(getCompanyName());
        System.out.println(customerOnEditPage);
        assertThat(customerOnEditPage).isEqualTo(customer);

    }
}
