package tests.ui;

import helpers.AuthorizationHelper;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasicAuthPage;
import properties.Properties;

public class BasicAuthTest extends BasicTestClass {
    private BasicAuthPage basicAuthPage;

    @BeforeMethod
    public void beforeMethod() {
        basicAuthPage = new BasicAuthPage(driver);
    }

    @Description(value = "Тест проверяет на успешную авторизацию")
    @Severity(SeverityLevel.NORMAL)
    @Epic(value = "Тестирование пользовательского интерфейса")
    @Feature(value = "Тест алерта авторизации")
    @Story(value = "Авторизация с помощью базовой аутентификации")
    @Test
    public void basicAuthTest() {
        AuthorizationHelper.authorizationByLink(driver,"httpwatch", "httpwatch", Properties.BASIC_AUTH_PAGE);
        basicAuthPage.clickOnDisplayImage();
        Assert.assertTrue(basicAuthPage.getDisplayImageStatus(), "Авторизация не прошла");
    }

}
