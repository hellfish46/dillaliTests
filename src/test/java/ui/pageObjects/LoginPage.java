package ui.pageObjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage{

    //private String emailInput = "//input[@name='email']";
    private By emailInput = By.xpath("//input[@name='email']");
    private By passwordInput = By.xpath("//input[@name='password']");
    private By loginBtn = By.xpath("//button[@type='submit']");
//button[@type='submit']

    public LoginPage fillEmail(String email){
        $(emailInput).shouldBe(Condition.visible).setValue(email);
        return this;
    }

    public LoginPage fillPassword(String password){
        $(passwordInput).shouldBe(Condition.visible).setValue(password);
        return this;
    }

    public void clickLoginBtn(){
        $(loginBtn).shouldBe(Condition.visible).click();

    }






}
