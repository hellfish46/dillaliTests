package ui.pageObjects;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CustomerPage {


    public CustomerPage customersClick(){
        $(By.xpath("//span[text()='Customers']")).click();
        return this;
    }

    


}
