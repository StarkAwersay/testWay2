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

    @Step("Принимает в себя параматеры для логина и возвращает авторизованную ссылку")
    public static String authorizationAlert(String login, String password, String link) {
        String logInLink = "https://" + login + ":" + password + "@" + link;
        return logInLink;
    }
}
