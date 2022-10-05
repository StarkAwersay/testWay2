package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class AuthorizationHelper {
    @Step("Принимает в себя параматеры для логина и драйвер, после чего переходит по авторизованной ссылке")
    public static void authorizationByLink(WebDriver driver, String login, String password, String link) {
        driver.get("https://" + login + ":" + password + "@" + link);
    }
}
