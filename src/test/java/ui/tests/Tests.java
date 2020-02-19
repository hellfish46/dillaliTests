package ui.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ui.objectsUI.Admin;
import ui.pageObjects.LoginPage;
import ui.pageObjects.SettingsPage;
import ui.pageObjects.ValidationMessage;

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
        admin.setZip("49000");
        admin.setAddressFirst("Novoorlovska street h. 12 block 18, or go to the center of te city");
        admin.setAddressSecond("Viktora Merzlenka Street.17");
        admin.setCurrency("KWD - Kuwaiti Dinar");
        admin.setDateFormat("17-02-2020"); // Need to refactor this



        SettingsPage settingsPage = new SettingsPage();
        settingsPage.accountSettingsClick();

        settingsPage.companyInformationClick();
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




        //$(By.xpath("//div[@class='overlay']")).click();
        //File d = $(By.xpath("//input[@type = 'file']")).uploadFile(new File("/home/john/Downloads/add-icon-png-2468.png"));
        //Selenide.executeJavaScript("arguments[0].value =" + d);
//        sleep(2000);
//        settingsPage.clickSaveBtn();
//        sleep(2000);
//        refresh();
//        sleep(2000);
       // $(By.xpath("//button[text()='Submit']")).click();

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




}
