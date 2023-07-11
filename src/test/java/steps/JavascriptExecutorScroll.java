package steps;

import helpers.JavaScriptExecutorsHelper;
import hooks.Hooks;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class JavascriptExecutorScroll {
    /**
     * драйвер.
     */
    private WebDriver driver;

    public JavascriptExecutorScroll() {
        this.driver = Hooks.getDriver();
    }

    @Дано("Главная страница яндекса")
    public void openYandexPage() {
        driver.get("https://yandex.ru/");
    }

    @Когда("Пользователь скроллит страницу на {string} пикселей")
    public void scroll(String pixels) {
        JavaScriptExecutorsHelper.scroll(driver, Integer.parseInt(pixels));
    }

    @Тогда("Происходит проверка того, что страница проскролилась на {string}")
    public void error(String pixels) {
        Assert.assertEquals(JavaScriptExecutorsHelper.getScrollInformation(driver), (Integer.parseInt(pixels)), "Страница не проскроллена");
        driver.quit();
    }

    @Если("Если страница не просролилась")
    public void dontScroll() {
        JavaScriptExecutorsHelper.scroll(driver, 0);
    }

    @Тогда("Выводится ошибка о том, что страница не проскроллилась")
    public void errorMessage() {
        Assert.assertEquals(JavaScriptExecutorsHelper.getScrollInformation(driver), (0), "Страница не проскроллена");
    }
}
