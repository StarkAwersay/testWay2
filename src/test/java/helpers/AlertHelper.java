package helpers;

import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertHelper {

    @Step("Ввод текста в alert")
    public static void fillingAlert(WebDriver driver, String message) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(message);
        driver.switchTo().alert().accept();
    }
}
