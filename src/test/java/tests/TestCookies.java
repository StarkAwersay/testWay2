package tests;

import chromeDriver.GetChromeDriver;
import cookies.ActionWithCookies;
import io.qameta.allure.*;
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
    private ActionWithCookies cookies;

    @BeforeMethod
    public void BeforeTest() {
        driver = GetChromeDriver.getChromeDriver();
        driver.manage().window().maximize();
        sqlMainPage = new SqlMainPage(driver);
        cookies = new ActionWithCookies(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Epic(value = "Тесты сайта 'Упражнения по sql'")
    @Feature(value = "Тест на авторизацию")
    @Story(value = "Авторизация с помощью cookies")
    @Test
    public void testCookie() throws IOException {
        driver.get(Properties.SQL_PAGE_URL);
        String sessionId = cookies.returnSessionId();
        if (sessionId != null) {
            cookies.addingCookies(sessionId);
            System.out.println(sessionId);
        } else {
            sqlMainPage.authorization();
            cookies.saveCookies();
        }
        Assert.assertEquals(sqlMainPage.getProfileName(), Properties.PROFILE_NAME_SQL_PAGE);
    }


    @AfterMethod
    public void tearDownDriver() {
        driver.quit();
    }
}

