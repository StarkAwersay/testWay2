package tests;

import chromeDriver.GetChromeDriver;
import cookies.CookiesHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.Feature;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SqlMainPage;
import properties.Properties;

import java.io.IOException;

public class TestCookies {
    private WebDriver driver;
    private SqlMainPage sqlMainPage;

    @BeforeMethod
    public void BeforeTest() {
        driver = GetChromeDriver.getChromeDriver();
        driver.manage().window().maximize();
        sqlMainPage = new SqlMainPage(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Epic(value = "Тесты сайта 'Упражнения по sql'")
    @Feature(value = "Тест на авторизацию")
    @Story(value = "Авторизация с помощью cookies")
    @Test
    public void testCookie() throws IOException {
        driver.get(Properties.SQL_PAGE_URL);
        String sessionId = CookiesHelper.returnSessionId();
        if (sessionId != null) {
            CookiesHelper.addingCookies(driver, sessionId);
        } else {
            sqlMainPage.authorization();
            CookiesHelper.saveCookies(driver);
        }
        Assert.assertEquals(sqlMainPage.getProfileName(), Properties.PROFILE_NAME_SQL_PAGE, "Что-то не так...");
    }


    @AfterMethod
    public void tearDownDriver() {
        driver.quit();
    }
}

