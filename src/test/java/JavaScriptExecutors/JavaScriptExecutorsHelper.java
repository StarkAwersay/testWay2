package JavaScriptExecutors;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptExecutorsHelper {
    @Step("Смена фокуса с элемента")
    public static void removeFocus(WebDriver driver, WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].blur();", element);
    }

    @Step("Получение количества пикселей, которые проскроллены на странице")
    public static Long getScrollInformation(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Long value = (Long) jse.executeScript("return window.pageYOffset;");
        return value;
    }

    @Step("Скролл")
    public static void scroll(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,0)");
    }
}
