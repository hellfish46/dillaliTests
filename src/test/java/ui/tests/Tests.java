package ui.tests;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ui.objectsUI.Admin;
import ui.objectsUI.Customer;
import ui.pageObjects.*;

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
        loginPage.checkNotificationMessage(NotificationMessage.LOGINSUCCESS);

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
        admin.setDateFormat(String.format("%s.%s.%s", getDay(), getMonth(),getYear()));

        admin.setCompanyLogoPath("/home/john/Desktop/");
        admin.setCompanyLogoName("houseLogo.png");

        admin.setProfilePicturePath("/home/john/Desktop/");
        admin.setProfilePictureName("mrX.jpg");



        SettingsPage settingsPage = new SettingsPage();
        settingsPage.accountSettingsClick();
        settingsPage.setProfilePicture(admin.getFullPathOfProfilePicture());
        settingsPage.fillFirstName(admin.getFirstName());
        settingsPage.fillLastName(admin.getLastName());
        settingsPage.clickSaveBtn();
        settingsPage.checkNotificationMessage(NotificationMessage.SETTINGSUPDATED);

        settingsPage.companyInformationClick();
        settingsPage.setCompanyLogo(admin.getFullPathOfCompanyLogo());
        settingsPage.fillCompanyName(admin.getCompany());
        settingsPage.setCountry(admin.getCountry());
        settingsPage.fillPhone(admin.getPhone());
        settingsPage.fillState(admin.getState());
        settingsPage.fillCity(admin.getCity());
        settingsPage.fillZip(admin.getZip());
        settingsPage.fillStreetOne(admin.getAddressFirst());
        settingsPage.fillStreetTwo(admin.getAddressSecond());
        settingsPage.clickSaveBtn();
        settingsPage.checkNotificationMessage(NotificationMessage.SETTINGSUPDATED);

        settingsPage.preferencesClick();
        settingsPage.setDateFormat(admin.getDateFormat());
        settingsPage.setCurrency(admin.getCurrency());
        settingsPage.clickSaveBtn();
        settingsPage.checkNotificationMessage(NotificationMessage.SETTINGSUPDATED);

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
        loginPage.checkNotificationMessage(NotificationMessage.LOGINSUCCESS);

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



    @Test
    public void createNewCustomerWithAllFields(){
        LoginPage loginPage = new LoginPage();
        open("/login");

        loginPage.fillEmail("test@test.com");
        loginPage.fillPassword("secret");
        loginPage.clickLoginBtn();
        loginPage.checkNotificationMessage(NotificationMessage.LOGINSUCCESS);

        AllCustomersPage allCustomersPage = new AllCustomersPage();
        allCustomersPage.customersClick();
        allCustomersPage.newCustomerClick();

        Customer customer = new Customer();
        customer.setCompanyName("Jacobs");
        customer.setContactPerson("Ignat Vladimirovich");
        customer.setEmail("jacobs@o.com");
        customer.setPhone("9991243");
        customer.setCountry("Bahrain");
        customer.setPostalZipCode("94z12");
        customer.setAddress("zxcvZXCV 1234 !@#$%^&*()");


        CreateNewCustomerPage createNewCustomerPage = new CreateNewCustomerPage();
        createNewCustomerPage.fillCustomerForm(customer).clickSaveCustomerBtn();
        loginPage.checkNotificationMessage(NotificationMessage.CUSTOMERCREATED);

        allCustomersPage.checkFirstCustomerInList(customer);
        allCustomersPage.clickEditCustomer(1);

        EditCustomerPage editCustomerPage = new EditCustomerPage();
        editCustomerPage.checkCustomerInfo(customer);

        allCustomersPage.customersClick().deleteCustomer(1);
        allCustomersPage.checkNotificationMessage(NotificationMessage.CUSTOMERDELETED);
    }

    @Test
    public void createNewCustomerWithoutPhone(){
        LoginPage loginPage = new LoginPage();
        open("/login");

        loginPage.fillEmail("test@test.com");
        loginPage.fillPassword("secret");
        loginPage.clickLoginBtn();
        loginPage.checkNotificationMessage(NotificationMessage.LOGINSUCCESS);

        AllCustomersPage allCustomersPage = new AllCustomersPage();
        allCustomersPage.customersClick();
        allCustomersPage.newCustomerClick();

        Customer customer = new Customer();
        customer.setCompanyName("Jacobs");
        customer.setContactPerson("Misha");
        customer.setEmail("jacobs12@o.com");
        customer.setCountry("Bahrain");
        customer.setPostalZipCode("94z12");
        customer.setAddress("zxcvZXCV 1234 !@#$%^&*()");



        CreateNewCustomerPage createNewCustomerPage = new CreateNewCustomerPage();
        createNewCustomerPage.fillCustomerForm(customer).clickSaveCustomerBtn();
        loginPage.checkNotificationMessage(NotificationMessage.CUSTOMERCREATED);

        allCustomersPage.checkFirstCustomerInList(customer);
        allCustomersPage.clickEditCustomer(1);

        EditCustomerPage editCustomerPage = new EditCustomerPage();
        editCustomerPage.checkCustomerInfo(customer);

        allCustomersPage.customersClick().deleteCustomer(1);
        allCustomersPage.checkNotificationMessage(NotificationMessage.CUSTOMERDELETED);

    }

    @Test
    public void createNewCustomerWithoutPostalZipCode(){
        LoginPage loginPage = new LoginPage();
        open("/login");

        loginPage.fillEmail("test@test.com");
        loginPage.fillPassword("secret");
        loginPage.clickLoginBtn();
        loginPage.checkNotificationMessage(NotificationMessage.LOGINSUCCESS);

        AllCustomersPage allCustomersPage = new AllCustomersPage();
        allCustomersPage.customersClick();
        allCustomersPage.newCustomerClick();

        Customer customer = new Customer();
        customer.setCompanyName("Jacobs");
        customer.setContactPerson("Ignat Vladimirovich");
        customer.setEmail("jacobs@o.com");
        customer.setPhone("9991243");
        customer.setCountry("Bahrain");
        customer.setAddress("zxcvZXCV 1234 !@#$%^&*()");


        CreateNewCustomerPage createNewCustomerPage = new CreateNewCustomerPage();
        createNewCustomerPage.fillCustomerForm(customer).clickSaveCustomerBtn();
        loginPage.checkNotificationMessage(NotificationMessage.CUSTOMERCREATED);

        allCustomersPage.checkFirstCustomerInList(customer);
        allCustomersPage.clickEditCustomer(1);

        EditCustomerPage editCustomerPage = new EditCustomerPage();
        editCustomerPage.checkCustomerInfo(customer);

        allCustomersPage.customersClick().deleteCustomer(1);
        allCustomersPage.checkNotificationMessage(NotificationMessage.CUSTOMERDELETED);
    }

    @Test
    public void editCustomer(){
        LoginPage loginPage = new LoginPage();
        open("/login");

        loginPage.fillEmail("test@test.com");
        loginPage.fillPassword("secret");
        loginPage.clickLoginBtn();
        loginPage.checkNotificationMessage(NotificationMessage.LOGINSUCCESS);


        Customer customer = new Customer();
        customer.setCompanyName("Mr.Medvezhonkin");
        customer.setContactPerson("Alina Malina");
        customer.setEmail("medvezhonkin1@o.com");
        customer.setPhone("11001232v");
        customer.setCountry("El Salvador");
        customer.setPostalZipCode("00z12x3");
        customer.setAddress("Any Address for out company 123ZXC!zxc");

        AllCustomersPage allCustomersPage = new AllCustomersPage();
        allCustomersPage.customersClick();
        allCustomersPage.clickEditCustomer(1);

        EditCustomerPage editCustomerPage = new EditCustomerPage();
        editCustomerPage.fillCustomerForm(customer);
        editCustomerPage.clickSaveCustomerBtn();

        editCustomerPage.checkNotificationMessage(NotificationMessage.CUSTOMERUPDATED);
        allCustomersPage.checkCustomerInList(1, customer);

        allCustomersPage.clickEditCustomer(1);
        editCustomerPage.checkCustomerInfo(customer);


    }

    @Test
    public void deleteCustomer(){
        LoginPage loginPage = new LoginPage();
        open("/login");

        loginPage.fillEmail("se@da.cx");
        loginPage.fillPassword("secret");
        loginPage.clickLoginBtn();
        loginPage.checkNotificationMessage(NotificationMessage.LOGINSUCCESS);


        AllCustomersPage allCustomersPage = new AllCustomersPage();
        allCustomersPage.customersClick();

        int beforeDeletingCountOfCustomers = allCustomersPage.getCountOfCustomers();

        allCustomersPage.deleteCustomer(1);
        allCustomersPage.checkNotificationMessage(NotificationMessage.CUSTOMERDELETED);

        refresh();

        int afterDeletingCountOfCustomers = allCustomersPage.getCountOfCustomers();

        assertThat(afterDeletingCountOfCustomers).isEqualTo(beforeDeletingCountOfCustomers - 1);

    }

    @Test
    public void getFirstInvoiceInList(){
        LoginPage loginPage = new LoginPage();
        open("/login");

        loginPage.fillEmail("test@test.com");
        loginPage.fillPassword("secret");
        loginPage.clickLoginBtn();
        loginPage.checkNotificationMessage(NotificationMessage.LOGINSUCCESS);

        AllInvoicesPage allInvoicesPage = new AllInvoicesPage();
        allInvoicesPage.invoicesClick();
        allInvoicesPage.checkInvoiceInList(1);
    }



}
