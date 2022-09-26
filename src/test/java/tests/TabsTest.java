package tests;

import helpers.TabsHelper;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.TabsPage;
import properties.Properties;

public class TabsTest extends BasicTestClass {
    private TabsPage tabsPage;

    @BeforeMethod
    public void beforeTest() {
        tabsPage = new TabsPage(driver);
    }

    @Description(value = "Тест проверяет работу кнопки New Browser Tab")
    @Severity(SeverityLevel.NORMAL)
    @Epic(value = "Тесты сайта Way2Automation")
    @Feature(value = "Тестирование формы New Browser Tab")
    @Story(value = "Проверка работоспособности кнопки New Browser Tab")
    @Test
    public void tabsTest() {
        driver.get(Properties.TABS_PAGE);
        String handleHost = driver.getWindowHandle();
        tabsPage.switchFrame();
        tabsPage.clickOnNewTabButton();
        TabsHelper.switchTabs(driver, handleHost);
        tabsPage.clickOnNewTabButton();
        TabsHelper.switchTabs(driver, handleHost);
        Assert.assertEquals(TabsHelper.getCountTabs(driver), 3, "Новая вкладка не открылась");
    }
}
