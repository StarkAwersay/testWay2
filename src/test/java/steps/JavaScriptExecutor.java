package steps;

import driver_factory.DriverFactory;
import enums.EnumBrowsers;
import helpers.JavaScriptExecutorsHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.YandexMainPage;

public class JavaScriptExecutor {
    private WebDriver driver;
    private YandexMainPage yandexMainPage;

    @Before
    public void openBrowser() {
        driver = DriverFactory.webDriver(EnumBrowsers.Browsers.CHROME);
        driver.manage().window().maximize();
    }

    @Дано("Страница с поисковой строкой")
    public void openYandexPage() {
        driver.get("https://yandex.ru/");
        yandexMainPage = new YandexMainPage(driver);
    }

    @Когда("Пользователь нажимает на поисковую строку и с помощью javascriptExecutor убирается фокус с поисковой строки")
    public void clickOnSearchBar() {
        yandexMainPage.clickOnSearchBar();
        JavaScriptExecutorsHelper.removeFocus(driver, yandexMainPage.searchBar());
    }

    @Тогда("Происходит проверка того, что фокус сменился")
    public void checkingFocus() {
        Assert.assertFalse(driver.switchTo().activeElement().equals(yandexMainPage.searchBar()), "Фокус не изменился");
        driver.quit();
    }

    @Если("Пользователь нажимает поисковую строку и фокус не убирается")
    public void dontChangeFocus() {
        yandexMainPage.clickOnSearchBar();
    }

    @То("Выводится ошибка о том, что фокус не изменился")
    public void error() {
        Assert.assertFalse(driver.switchTo().activeElement().equals(yandexMainPage.searchBar()), "Фокус не изменился");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
