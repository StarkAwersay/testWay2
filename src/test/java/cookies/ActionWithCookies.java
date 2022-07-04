package cookies;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class ActionWithCookies {
    private WebDriver driver;

    public ActionWithCookies(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Добавление cookies")
    public void addingCookies(String sessionId) {
        Cookie cookie = new Cookie("PHPSESSID", sessionId);
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }

    @Step("Чтенине cookies")
    public String returnSessionId() throws IOException {
        File file = new File("src/test/resources/Cookies.data");
        try (BufferedReader BuffReader = new BufferedReader(new FileReader(file))) {
            return BuffReader.readLine();
        }
    }

    @Step("Обновление cookies")
    public void saveCookies() throws IOException {
        File file = new File("src/test/resources/Cookies.data");
        try (FileWriter fileWrite = new FileWriter(file)) {
            file.delete();
            file.createNewFile();
            fileWrite.write(driver.manage().getCookieNamed("PHPSESSID").getValue());
        }
    }

}
