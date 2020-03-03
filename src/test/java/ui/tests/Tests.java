package ui.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ui.objectsUI.Admin;
import ui.objectsUI.Customer;
import ui.pageObjects.*;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class Tests extends TestBase{


    @Test
    public void changeSettings(){
        LoginPage loginPage = new LoginPage();
        open("/login");

        loginPage.fillEmail("test@test.com");
        loginPage.fillPassword("secret");
        loginPage.clickLoginBtn();
        $(By.xpath("//h3[text()='Settings']")).shouldBe(Condition.visible);

        Admin admin = new Admin();
        admin.setFirstName("testFirst");
        admin.setLastName("testLast");
        admin.setEmail("test@test.com");
        admin.setCompany("testCompany");
        admin.setCountry("Bulgaria");
        admin.setPhone("0934546787");
        admin.setState("Alabama");
        admin.setCity("Dnipro");
        admin.setZip("18226");
        admin.setAddressFirst("Novoorlovska street h. 12 block 18");
        admin.setAddressSecond("Viktora Merzlenka");
        admin.setCurrency("KWD - Kuwaiti Dinar");
        admin.setDateFormat(String.format("%s/%s/%s", getDay(), getMonth(),getYear()));



        SettingsPage settingsPage = new SettingsPage();
        settingsPage.accountSettingsClick();
        settingsPage.setProfilePicture("/home/john/Desktop/express.png");
        settingsPage.clickSaveBtn();

        settingsPage.companyInformationClick();
        settingsPage.setCompanyLogo("/home/john/Desktop/logo.png");
        settingsPage.setCountry(admin.getCountry());
        settingsPage.fillPhone(admin.getPhone());
        settingsPage.fillState(admin.getState());
        settingsPage.fillCity(admin.getCity());
        settingsPage.fillZip(admin.getZip());
        settingsPage.fillStreetOne(admin.getAddressFirst());
        settingsPage.fillStreetTwo(admin.getAddressSecond());
        settingsPage.clickSaveBtn();

        settingsPage.preferencesClick();
        settingsPage.setDateFormat(admin.getDateFormat());
        settingsPage.setCurrency(admin.getCurrency());
        settingsPage.clickSaveBtn();

        refresh();

        sleep(3000);

        assertThat(admin).isEqualTo(settingsPage.getAllAdminsSettings());

    }


    @Test
    public void validationMessagesAccountSettings(){
        LoginPage loginPage = new LoginPage();
        open("/login");

        loginPage.fillEmail("test@test.com");
        loginPage.fillPassword("secret");
        loginPage.clickLoginBtn();
        $(By.xpath("//h3[text()='Settings']")).shouldBe(Condition.visible);

        SettingsPage settingsPage = new SettingsPage();
        settingsPage.accountSettingsClick();
        settingsPage.clearFirstName();

        List<String> firstNameValidationMsgs = settingsPage.getFirstNameValidationMessages();
        assertThat(firstNameValidationMsgs).contains(ValidationMessage.FIELDISREQUIRED.getMessage());
        assertThat(firstNameValidationMsgs).contains(ValidationMessage.WRONGINPUTE.getMessage());

        settingsPage.clearLastName();
        List<String> lastNameValidationMsgs = settingsPage.getFirstNameValidationMessages();
        assertThat(lastNameValidationMsgs).contains(ValidationMessage.FIELDISREQUIRED.getMessage());
        assertThat(lastNameValidationMsgs).contains(ValidationMessage.WRONGINPUTE.getMessage());

        settingsPage.clearEmail();
        List<String> emailValidationMsgs = settingsPage.getEmailValidationMessages();
        assertThat(emailValidationMsgs).contains(ValidationMessage.FIELDISREQUIRED.getMessage());
        settingsPage.fillEmail("Z");
        emailValidationMsgs = settingsPage.getEmailValidationMessages();
        assertThat(emailValidationMsgs).contains(ValidationMessage.INCORRECTEMAIL.getMessage());

        settingsPage.fillOldPassword("s");
        List<String> oldPasswordValidationMsgs = settingsPage.getOldPasswordValidationMessages();
        assertThat(oldPasswordValidationMsgs).contains(ValidationMessage.PASSWORDMINIMUM.getMessage());

        settingsPage.fillNewPassword("s");
        List<String> newPasswordValidationMsgs = settingsPage.getNewPasswordValidationMessages();
        assertThat(newPasswordValidationMsgs).contains(ValidationMessage.PASSWORDMINIMUM.getMessage());
        settingsPage.fillNewPassword("newpass");

        settingsPage.fillConfirmPassword("newpas");
        List<String> confirmPasswordValidationMsgs = settingsPage.getConfirmPasswordValidationMessages();
        assertThat(confirmPasswordValidationMsgs).contains(ValidationMessage.REPEATPASSWORD.getMessage());   
    }




//    @Test
//    public void getParsedCustomer(){
//        LoginPage loginPage = new LoginPage();
//        open("/login");
//
//        loginPage.fillEmail("test@test.com");
//        loginPage.fillPassword("secret");
//        loginPage.clickLoginBtn();
//        $(By.xpath("//h3[text()='Settings']")).shouldBe(Condition.visible);
//
//        Customer customer = new Customer();
//        customer.setCompanyName("BlueBox");
//        customer.setContactPerson("Adrian");
//        customer.setPhone("555-67-23");
//        customer.setEmail("adrian@o.com");
//        AllCustomersPage allCustomersPage = new AllCustomersPage();
//        allCustomersPage.customersClick();
//        allCustomersPage.checkFirstCustomerInList(customer);
//
//    }

    @Test
    public void createNewCustomer(){
        LoginPage loginPage = new LoginPage();
        open("/login");

        loginPage.fillEmail("test@test.com");
        loginPage.fillPassword("secret");
        loginPage.clickLoginBtn();
        $(By.xpath("//h3[text()='Settings']")).shouldBe(Condition.visible);

        AllCustomersPage allCustomersPage = new AllCustomersPage();
        allCustomersPage.customersClick();
        allCustomersPage.newCustomerClick();

        Customer customer = new Customer();
        customer.setCompanyName("Jacobs");
        customer.setContactPerson("Ignat Vladimirovich");
        customer.setEmail("jacobs@o.com");
        customer.setPhone("9991243");
        customer.setCountry("Bahrain");
        customer.setZipCode("94z12");
        customer.setAddress("zxcvZXCV 1234 !@#$%^&*()");

        NewCustomerPage newCustomerPage = new NewCustomerPage();
        newCustomerPage.fillNewCustomerForm(customer).clickSaveCustomerBtn();

        allCustomersPage.checkFirstCustomerInList(customer);


    }

    @Test
    public void editCustomer(){

    }



}
