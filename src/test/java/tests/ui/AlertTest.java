package tests.ui;

import helpers.AlertHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.Feature;
import io.qameta.allure.Description;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AlertPage;
import properties.Properties;

public class AlertTest extends BasicTestClass {
    /**
     * Страница alertPage.
     */
    private AlertPage alertPage;

    @BeforeMethod
    public void beforeMethod() {
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
        alertPage.clickInputAlertButton()
                .switchFrame()
                .clickDisplayAlertButton();
        AlertHelper.fillingAlert(driver, "Ilya Ponomarev");
        Assert.assertEquals(alertPage.getAlertText(), "Hello Ilya Ponomarev! How are you today?");
    }
}
