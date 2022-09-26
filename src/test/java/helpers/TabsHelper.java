package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class TabsHelper {

    @Step("Переключение на следующую вкладку")
    public static void switchTabs(WebDriver driver, String handleHost) {
        for (String handle : driver.getWindowHandles()) {
            if (handle != handleHost) {
                driver.switchTo().window(handle);
                driver.switchTo().activeElement();
            }
        }
    }

    @Step("Получение количества открытых вкладок")
    public static int getCountTabs(WebDriver driver) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        return tabs.size();
    }
}
