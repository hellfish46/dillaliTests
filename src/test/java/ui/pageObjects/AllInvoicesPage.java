package ui.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.objectsUI.Customer;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class AllInvoicesPage extends BasePage {

    public AllInvoicesPage invoicesClick(){
        $(By.xpath("//span[text()='Invoices']")).shouldBe(Condition.visible).click();
        return this;
    }

    public CreateNewInvoicePage newInvoiceClick(){
        $(By.xpath("//a[@href='/admin/invoices/create']/button")).shouldBe(Condition.visible).click();
        return new CreateNewInvoicePage();
    }

    private void clickInvoiceSettings(int trPosition){
        $(By.xpath("//tbody/tr[" + trPosition + "]//div[@class='dot-icon']")).shouldBe(Condition.visible).click();
    }

    public AllInvoicesPage clickEditInvoice(int trPosition){
        clickInvoiceSettings(trPosition);
        $(By.xpath("//tbody/tr["+ trPosition +"]//a[normalize-space(text())='Edit']")).shouldBe(Condition.visible).click();
        $(By.xpath("//h3[text()='Edit Invoice']")).shouldBe(Condition.visible);
        return this;// should return edit invoice page
    }

    public AllInvoicesPage clickDownloadPDF(int trPosition){
        clickInvoiceSettings(trPosition);
        $(By.xpath("//tbody/tr["+ trPosition +"]//a[normalize-space(text())='Download pdf']"))
                .shouldBe(Condition.visible).click();
        /////////////
        return this;
    }

    public AllInvoicesPage clickSendInvoice(int trPosition){
        clickInvoiceSettings(trPosition);
        $(By.xpath("//tbody/tr["+ trPosition +"]//a[normalize-space(text())='Send Invoice']"))
                .shouldBe(Condition.visible).click();
        $(By.xpath("//div[@class='swal-modal']//button[text()='OK']")).shouldBe(Condition.visible).click();
        return this;
    }

    public AllInvoicesPage clickDeleteInvoice(int trPosition){
        clickInvoiceSettings(trPosition);
        $(By.xpath("//tbody/tr["+ trPosition +"]//a[normalize-space(text())='Delete']"))
                .shouldBe(Condition.visible).click();
        $(By.xpath("//div[@class='swal-modal']//button[text()='OK']")).shouldBe(Condition.visible).click();
        return this;
    }


    public void checkInvoiceInList(int position){
        SelenideElement setTr = $(By.xpath("//tbody/tr[" + position + "]"));
        HashMap parsedInvoice = getInfoFromTr(setTr);
//        assertThat(customer.getCompanyName()).isEqualTo(parsedCustomer.get("companyName"));
//        assertThat(customer.getContactPerson()).isEqualTo(parsedCustomer.get("contactPerson"));
//        assertThat(customer.getPhone()).isEqualTo(parsedCustomer.get("phone"));
//        assertThat(customer.getEmail()).isEqualTo(parsedCustomer.get("email"));

        System.out.println(parsedInvoice);

    }


    private HashMap getInfoFromTr(SelenideElement tr){
        String invoiceDate = tr.$(By.xpath("./td[span[text()='Date']]")).getText();
        String customerShortInfo = tr.$(By.xpath("./td[3]")).getText();
        String[] CompanyNameAndEmail = customerShortInfo.split(" ");
        String status = tr.$(By.xpath("./td/span[normalize-space(text())='Status']/following-sibling::span")).getText();
        String currency = tr.$(By.xpath("./td[span[text()='AMOUNT DUE']]/div/span")).getText().trim();
        String costInfo = tr.$(By.xpath("./td[span[text()='AMOUNT DUE']]/div")).getText();
        String[] cost = costInfo.split(" ");

        HashMap<String, String> invoice = new HashMap<String, String>();
        invoice.put("invoiceDate", invoiceDate);
        invoice.put("companyName", CompanyNameAndEmail[0]);
        invoice.put("customerEmail", CompanyNameAndEmail[1]);
        invoice.put("status", status);
        invoice.put("currency", currency);
        invoice.put("cost", cost[1]);

        return invoice;
    }








}
