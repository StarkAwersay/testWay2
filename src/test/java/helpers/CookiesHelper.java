package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class CookiesHelper {

    @Step("Добавление cookies")
    public static void addingCookies(WebDriver driver, String sessionId) {
        Cookie cookie = new Cookie("PHPSESSID", sessionId);
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }

    @Step("Чтенине cookies")
    public static String returnSessionId() throws IOException {
        File file = new File("src/test/resources/Cookies.data");
        try (BufferedReader BuffReader = new BufferedReader(new FileReader(file))) {
            return BuffReader.readLine();
        }
    }

    @Step("Обновление cookies")
    public static void saveCookies(WebDriver driver) throws IOException {
        File file = new File("src/test/resources/Cookies.data");
        try (FileWriter fileWrite = new FileWriter(file)) {
            file.delete();
            file.createNewFile();
            fileWrite.write(driver.manage().getCookieNamed("PHPSESSID").getValue());
        }
    }

}
