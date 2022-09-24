package tests;

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
    public void tabsTest() throws InterruptedException {
        driver.get(Properties.TABS_PAGE);
        tabsPage.switchFrame();
        tabsPage.clickOnNewTabButton();
        tabsPage.switchTabs();
        tabsPage.clickOnNewTabButton();
        Assert.assertEquals(tabsPage.getCountTabs(), 3, "Новая вкладка не открылась");
    }
}
