package tests;

import helpers.AlertHelper;
import helpers.TabsHelper;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AlertPage;
import pages.TabsPage;
import properties.Properties;

public class AlertTest extends BasicTestClass {
    private AlertPage alertPage;

    @BeforeMethod
    public void beforeTest() {
        alertPage = new AlertPage(driver);
    }

    @Description(value = "Тест проверяет работу alert'a")
    @Severity(SeverityLevel.NORMAL)
    @Epic(value = "Тесты сайта Way2Automation")
    @Feature(value = "Тестирование alert'a")
    @Story(value = "Проверка работоспособности alert'a")
    @Test
    public void tabsTest() {
        driver.get(Properties.ALERT_PAGE);
        alertPage.clickInputAlertButtonButton();
        alertPage.switchFrame();
        alertPage.clickDisplayAnAlertButton();
        AlertHelper.fillingAlert(driver, "Ilya Ponomarev");
        Assert.assertEquals(alertPage.getAlertText(), "Hello Ilya Ponomarev! How are you today?");
    }
}
