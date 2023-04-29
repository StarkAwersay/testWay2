package steps;

import helpers.JavaScriptExecutorsHelper;
import hooks.Hooks;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.То;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.YandexMainPage;

public class JavaScriptExecutor {
    private WebDriver driver;
    private YandexMainPage yandexMainPage;

    public JavaScriptExecutor() {
        this.driver = Hooks.getDriver();
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
}
