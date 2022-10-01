package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class TabsHelper {

    @Step("Переключение на последнюю вкладку")
    public static void switchLastTab(WebDriver driver) {
        ArrayList<String> tabList = new ArrayList<String>(driver.getWindowHandles());
        int countTabs = new ArrayList<>(driver.getWindowHandles()).size();
        String lastTab = tabList.get(countTabs - (1));
        driver.switchTo().window(lastTab);
    }

    @Step("Получение количества открытых вкладок")
    public static int getCountTabs(WebDriver driver) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        return tabs.size();
    }
}
