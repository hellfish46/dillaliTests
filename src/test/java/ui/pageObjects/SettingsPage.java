package ui.pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ui.objectsUI.Admin;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class SettingsPage {

    private By xpathFirstName = By.xpath("//input[@placeholder = 'name']");
    private By xpathLastName = By.xpath("//input[@placeholder = 'Last Name']");
    private By xpathEmail = By.xpath("//input[@placeholder = 'Email']");
    private By xpathOldPassword = By.xpath("//input[@placeholder = 'Old Password']");
    private By xpathNewPassword = By.xpath("//input[@placeholder = 'New Password']");
    private By xpathConfirmPassword = By.xpath("//input[@placeholder = 'Confirm Password']");
    private By xpathCompanyName = By.xpath("//input[@placeholder = 'Company Name']");
    private By xpathPhone = By.xpath("//input[@placeholder = 'Phone']");
    private By xpathState = By.xpath("//input[@placeholder = 'State']");
    private By xpathCity = By.xpath("//input[@placeholder = 'City']");
    private By xpathZip = By.xpath("//input[@placeholder = 'Zip']");
    private By xpathStreetOne = By.xpath("//textarea[@placeholder = 'Street 1']");
    private By xpathStreetTwo = By.xpath("//textarea[@placeholder = 'Street 2']");
    private By xpathProfileImg = By.xpath("//div[@id='pick-avatar']/img");

    public SettingsPage SettingsClick(){
        $(By.xpath("//span[text()='Settings']")).click();
        return this;
    }

    //Account settings

    public SettingsPage accountSettingsClick(){
        $(By.xpath("//span[text()='Account Settings']")).scrollIntoView(false).click();
        return this;
    }

    public SettingsPage setProfilePicture(String path){
        sleep(1000);
        $(By.xpath("//input[@type = 'file']")).sendKeys(path);
        $(By.xpath("//button[text()='Submit']")).shouldBe(Condition.visible).click();
        return this;
    }

    public SettingsPage fillFirstName(String firstName){
        $(xpathFirstName).setValue(firstName);
        return this;
    }

    public SettingsPage clearFirstName(){
        sleep(1000);
        $(xpathFirstName).sendKeys(Keys.CONTROL + "a");
        $(xpathFirstName).sendKeys(Keys.DELETE);
        return this;
    }

    public SettingsPage fillLastName(String lastName){
        $(xpathLastName).setValue(lastName);
        return this;
    }

    public SettingsPage clearLastName(){
        sleep(1000);
        $(xpathLastName).sendKeys(Keys.CONTROL + "a");
        $(xpathLastName).sendKeys(Keys.DELETE);
        return this;
    }

    public SettingsPage fillEmail(String email){
        $(xpathEmail).setValue(email);
        return this;
    }

    public SettingsPage clearEmail(){
        sleep(1000);
        $(xpathEmail).sendKeys(Keys.CONTROL + "a");
        $(xpathEmail).sendKeys(Keys.DELETE);
        return this;
    }



    public SettingsPage fillOldPassword(String oldPassword){
        $(xpathOldPassword).setValue(oldPassword);
        return this;
    }

    public SettingsPage fillNewPassword(String newPassword){
        $(xpathNewPassword).setValue(newPassword);
        return this;
    }

    public SettingsPage fillConfirmPassword(String confirmPassword){
        $(xpathConfirmPassword).setValue(confirmPassword);
        return this;
    }


// Company information

    public SettingsPage companyInformationClick(){
        $(By.xpath("//span[text()='Company Information']")).scrollIntoView(false).click();
        return this;
    }

    public SettingsPage setCompanyLogo(String path){
        sleep(1000);
        $(By.xpath("//input[@type = 'file']")).sendKeys(path);
        $(By.xpath("//button[text()='Submit']")).shouldBe(Condition.visible).click();
        return this;
    }

    public SettingsPage fillCompanyName(String companyName){
        $(xpathCompanyName).setValue(companyName);
        return this;
    }

    public SettingsPage fillPhone(String telephone){
        $(xpathPhone).setValue(telephone);
        return this;
    }

    public SettingsPage setCountry(String country){
        SelenideElement select = $(By.xpath("//label[text()='Country']/following-sibling::div[@aria-owns='listbox-null']")).scrollIntoView(true);
        sleep(1000);
        select.click();
        SelenideElement selectUl = select.$(By.xpath(".//ul[@id = 'listbox-null']")).shouldBe(Condition.visible);
        liFounderViaSpan(selectUl, country).shouldBe(Condition.visible).click();
        return this;
    }

    public SettingsPage fillState(String state){
        $(xpathState).setValue(state);
        return this;
    }
    public SettingsPage fillCity(String city){
        $(xpathCity).setValue(city);
        return this;
    }

    public SettingsPage fillZip(String zip){
        $(xpathZip).setValue(zip);
        return this;
    }

    public SettingsPage fillStreetOne(String streetOne){
        $(xpathStreetOne).setValue(streetOne);
        return this;
    }

    public SettingsPage fillStreetTwo(String streetTwo){
        $(xpathStreetTwo).setValue(streetTwo);
        return this;
    }







// Preferences
    public SettingsPage preferencesClick(){
        $(By.xpath("//a[span[text()='Preferences']]")).scrollIntoView(false).click();
        return this;
    }


    public SettingsPage setCurrency(String country){
        SelenideElement select = $(By.xpath("//label[text()='Currency']/following-sibling::div[@aria-owns='listbox-null']")).scrollIntoView(true);
        sleep(1000);
        select.click();
        SelenideElement selectUl = select.$(By.xpath(".//ul[@id = 'listbox-null']")).shouldBe(Condition.visible);
        liFounderViaSpan(selectUl, country).shouldBe(Condition.visible).click();
        return this;
    }

    public SettingsPage setDateFormat(String dateFormat){
        SelenideElement select = $(By.xpath("//label[text()='Date Format']/following-sibling::div[@aria-owns='listbox-null']")).scrollIntoView(true);
        sleep(1000);
        select.click();
        SelenideElement selectUl = select.$(By.xpath(".//ul[@id = 'listbox-null']")).shouldBe(Condition.visible);
        liFounderViaSpan(selectUl, dateFormat).shouldBe(Condition.visible).click();
        return this;
    }


    public Admin getAllAdminsSettings(){
        Admin admin = new Admin();

        open("/admin/settings/company-info");
        sleep(3000);
        $(By.xpath("//h3[text()='Company info']")).shouldBe(Condition.visible);

        admin.setCountry($(By.xpath("//label[text()='Country']/following-sibling::div//span[@class = 'multiselect__single']"))
                .getText());
        admin.setCompany($(xpathCompanyName).getAttribute("value"));
        admin.setPhone($(xpathPhone).getAttribute("value"));
        admin.setState($(xpathState).getAttribute("value"));
        admin.setCity($(xpathCity).getAttribute("value"));
        admin.setZip($(xpathZip).getAttribute("value"));
        admin.setAddressFirst($(xpathStreetOne).getAttribute("value"));
        admin.setAddressSecond($(xpathStreetTwo).getAttribute("value"));


        open("/admin/settings/user-profile");
        sleep(3000);
        $(By.xpath("//h3[text()='Account Settings']")).shouldBe(Condition.visible);

        admin.setFirstName($(xpathFirstName).getAttribute("value"));
        admin.setLastName($(xpathLastName).getAttribute("value"));
        admin.setEmail($(xpathEmail).getAttribute("value"));

        open("/admin/settings/preferences");
        sleep(3000);
        $(By.xpath("//h3[text()='Preferences']")).shouldBe(Condition.visible);

        admin.setCurrency($(By.xpath("//label[text()='Currency']/following-sibling::div//span[@class = 'multiselect__single']"))
                .getText());
        admin.setDateFormat($(By.xpath("//label[text()='Date Format']/following-sibling::div//span[@class = 'multiselect__single']"))
                .getText());

        return admin;
    }


    private SelenideElement liFounderViaSpan(SelenideElement ul, String point){
        List<SelenideElement> lis = ul.$$(By.xpath("./li")).shouldBe(CollectionCondition.sizeGreaterThan(2));
        SelenideElement foundLi = null;
        for (SelenideElement li: lis) {
            String spanText = li.$(By.xpath("./span/span")).getText();
            if(spanText.equals(point)){
                foundLi = li;
            }
        }
        foundLi.shouldBe(Condition.exist);
        return foundLi;
    }

    public SettingsPage clickSaveBtn(){
        $(By.xpath("//button[@type = 'submit']")).click();
        $(By.xpath("//button[@type = 'submit']")).shouldNot(Condition.disabled);
        return this;
    }

    public List<String> getFirstNameValidationMessages(){
        SelenideElement input = $(xpathFirstName);
        return getValidationMessagesViaElement(input);
    }

    public List<String> getLastNameValidationMessages(){
        SelenideElement input = $(xpathLastName);
        return getValidationMessagesViaElement(input);
    }

    public List<String> getEmailValidationMessages(){
        SelenideElement input = $(xpathEmail);
        return getValidationMessagesViaElement(input);
    }

    public List<String> getOldPasswordValidationMessages(){
        SelenideElement input = $(xpathOldPassword);
        return getValidationMessagesViaElement(input);
    }

    public List<String> getNewPasswordValidationMessages(){
        SelenideElement input = $(xpathNewPassword);
        return getValidationMessagesViaElement(input);
    }

    public List<String> getConfirmPasswordValidationMessages(){
        SelenideElement input = $(xpathConfirmPassword);
        return getValidationMessagesViaElement(input);
    }

    public List<String> getCompanyNameValidationMessages(){
        SelenideElement input = $(xpathCompanyName);
        return getValidationMessagesViaElement(input);
    }

    public List<String> getPhoneValidationMessages(){
        SelenideElement input = $(xpathPhone);
        return getValidationMessagesViaElement(input);
    }

    public List<String> getStateValidationMessages(){
        SelenideElement input = $(xpathState);
        return getValidationMessagesViaElement(input);
    }

    public List<String> getCityValidationMessages(){
        SelenideElement input = $(xpathCity);
        return getValidationMessagesViaElement(input);
    }

    public List<String> getZipValidationMessages(){
        SelenideElement input = $(xpathZip);
        return getValidationMessagesViaElement(input);
    }

    public List<String> getStreetOneValidationMessages(){
        SelenideElement input = $(xpathStreetOne);
        return getValidationMessagesViaElement(input);
    }


    private List<String> getValidationMessagesViaElement(SelenideElement input){
        List<String> validationStrings = new ArrayList<>();
        List<SelenideElement> spans = input.parent().$$(By.xpath("./following-sibling::div/span"));
        for (SelenideElement span: spans) {
            validationStrings.add(span.getText());
        }
        return validationStrings;
    }



}
