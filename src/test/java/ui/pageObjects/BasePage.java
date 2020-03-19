package ui.pageObjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BasePage {

    By xpathNotificationMessage = By.xpath("//div[@class='toast-message']");

    public void checkNotificationMessage (NotificationMessage point){
        String expectedMessage = point.getMessage();
        String actualResult = $(xpathNotificationMessage).shouldBe(Condition.visible).getText();
        assertThat(actualResult).isEqualTo(expectedMessage);
        $(xpathNotificationMessage).click();
        System.out.println(expectedMessage);
        System.out.println(actualResult);
    }

}
