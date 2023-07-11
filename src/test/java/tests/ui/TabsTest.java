package tests.ui;

import helpers.TabsHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.Feature;
import io.qameta.allure.Description;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.TabsPage;
import properties.Properties;

public class TabsTest extends BasicTestClass {
    /**
     * tabs Page.
     */
    private TabsPage tabsPage;

    @BeforeMethod
    public void beforeMethod() {
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
        tabsPage.switchFrame();
        tabsPage.clickOnNewTabButton();
        TabsHelper.switchLastTab(driver);
        tabsPage.clickOnNewTabButton();
        TabsHelper.switchLastTab(driver);
        Assert.assertEquals(TabsHelper.getCountTabs(driver), 3, "Новая вкладка не открылась");
    }
}
