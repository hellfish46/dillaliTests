package ui.tests;

import api.ApiRequests;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Credentials;
import com.codeborne.selenide.proxy.SelenideProxyServer;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ui.objectsUI.Admin;
import ui.objectsUI.Customer;
import ui.objectsUI.Invoice;
import ui.objectsUI.Item;
import ui.pageObjects.*;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getSelenideProxy;

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

    @Test
    public void test(){
        LoginPage loginPage = new LoginPage();
        open("/login");

        loginPage.fillEmail("test@test.com");
        loginPage.fillPassword("secret");
        loginPage.clickLoginBtn();
        loginPage.checkNotificationMessage(NotificationMessage.LOGINSUCCESS);

        AllInvoicesPage allInvoicesPage = new AllInvoicesPage();
        allInvoicesPage.invoicesClick();
        allInvoicesPage.newInvoiceClick();
        sleep(2000);

        Customer customer = new Customer();
        customer.setCompanyName("PetClinic");
        customer.setContactPerson("John Smith");
        customer.setEmail("duda@duda.com");
        customer.setPhone("110123123");
        customer.setCountry("El Salvador");
        customer.setPostalZipCode("00z12x3");
        customer.setAddress("Address for out company 12 123123 123ZXC!zxc");

        CreateNewInvoicePage createNewInvoicePage = new CreateNewInvoicePage();
        createNewInvoicePage.createNewCustomer(customer);
        sleep(2000);

        createNewInvoicePage.fillDiscount(12.2);
        sleep(2000);
        createNewInvoicePage.fillInvoiceNumber("123423");
        sleep(2000);
        createNewInvoicePage.fillPaymentMethod("ds ads asds adas ad asdd sad ");
        sleep(5000);

    }

    @Test
    public void createInvoice(){
        LoginPage loginPage = new LoginPage();
        open("/login");

        loginPage.fillEmail("test@test.com");
        loginPage.fillPassword("secret");
        loginPage.clickLoginBtn();
        loginPage.checkNotificationMessage(NotificationMessage.LOGINSUCCESS);

        AllInvoicesPage allInvoicesPage = new AllInvoicesPage();
        allInvoicesPage.invoicesClick();
        allInvoicesPage.newInvoiceClick();
        Invoice invoice = new Invoice();

        Item item1 = new Item();
        item1.setDescription("description description description description description");
        item1.setName("Bananas !");
        item1.setQuantity(45);
        item1.setPrice(32420);

        Item item2 = new Item();
        item2.setDescription("decription description description");
        item2.setName("apples !");
        item2.setQuantity(707);
        item2.setPrice(32420);

        Item item3 = new Item();
        //item2.setDescription("description description description description description");
        item3.setName("sugar !");
        //item2.setQuantity(70);
        item3.setPrice(44888);

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        long subTotalSum = item1.getAmount() + item2.getAmount() + item3.getAmount();
        CreateNewInvoicePage createNewInvoicePage = new CreateNewInvoicePage();
        createNewInvoicePage.fillItems(items);
        long subtotalForInvoice = createNewInvoicePage.getSubTotal();

        assertThat(subTotalSum).isEqualTo(subtotalForInvoice);

    }


    @Test
    public void restRequest(){
        ApiRequests apiRequests = new ApiRequests();
        String token = apiRequests.getAuthToken("test@test.com", "secret");


        Configuration.proxyEnabled = true;
        open("/admin/customers");
        SelenideProxyServer selenideProxy = getSelenideProxy();


        selenideProxy.addRequestFilter("AuthorizationWithToken", (request, contents, messageInfo) -> {
            String authorization = "BASIC " + new Credentials("test@test.com", "secret").encode();
            request.headers().add("Authorization", authorization);
            return null;
        });
        open("/admin/customers");
        sleep(50000);

    }

    @Test
    public void getSubtotal(){
        LoginPage loginPage = new LoginPage();
        open("/login");

        loginPage.fillEmail("test@test.com");
        loginPage.fillPassword("secret");
        loginPage.clickLoginBtn();
        loginPage.checkNotificationMessage(NotificationMessage.LOGINSUCCESS);

        AllInvoicesPage allInvoicesPage = new AllInvoicesPage();
        allInvoicesPage.invoicesClick();
        allInvoicesPage.newInvoiceClick();
        Invoice invoice = new Invoice();

        Item item1 = new Item();
        item1.setDescription("description description description description description");
        item1.setName("Bananas !");
        item1.setQuantity(45000);
        item1.setPrice(32420);

        CreateNewInvoicePage createNewInvoicePage = new CreateNewInvoicePage();
        createNewInvoicePage.fillItem(item1);
        createNewInvoicePage.getSubTotal();

    }



}
