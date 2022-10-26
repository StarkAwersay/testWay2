package steps;

import driver_factory.DriverFactory;
import enums.EnumBrowsers;
import helpers.JavaScriptExecutorsHelper;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class JavascriptExecutorScroll {
    private WebDriver driver;

    @Дано("Главная страница яндекса {string}")
    public void openYandexPage(String YandexUrl) {
        driver = DriverFactory.webDriver(EnumBrowsers.Browsers.CHROME);
        driver.get(YandexUrl);
        driver.manage().window().maximize();
    }

    @Когда("Пользователь скроллит страницу на {string} пикселей")
    public void scroll(String Pixels) {
        JavaScriptExecutorsHelper.scroll(driver, Integer.parseInt(Pixels));
    }

    @Тогда("Происходит проверка того, что страница проскролилась на {string}")
    public void error(String Pixels) {
        Assert.assertEquals(JavaScriptExecutorsHelper.getScrollInformation(driver), (Integer.parseInt(Pixels)), "Страница не проскроллена");
        driver.quit();
    }

    @Если("Если страница не просролилась")
    public void dontScroll() {
        JavaScriptExecutorsHelper.scroll(driver, 0);
    }

    @Тогда("Выводится ошибка о том, что страница не проскроллилась")
    public void errorMessage() {
        Assert.assertEquals(JavaScriptExecutorsHelper.getScrollInformation(driver), (0), "Страница не проскроллена");
        driver.quit();
    }
}
