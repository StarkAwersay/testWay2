package tests;


import connection_help.UrlConnection;
import cookies.CookiesHelper;
import driver_factory.DriverFactory;
import enums.EnumBrowsers;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.Feature;
import io.qameta.allure.SeverityLevel;
import run_test_again.RunTestAgain;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SqlMainPage;
import properties.Properties;

import java.io.IOException;

public class TestCookies {
    private SqlMainPage sqlMainPage;
    public static RemoteWebDriver driver;

    @BeforeMethod
    public void beforeTest() throws IOException {
        UrlConnection.urlConnection();
        Runtime.getRuntime().exec("src\\test\\resources\\BatFiles\\hub.bat");
        Runtime.getRuntime().exec("src\\test\\resources\\BatFiles\\node2.bat");
        driver = (RemoteWebDriver) DriverFactory.webDriver(String.valueOf(EnumBrowsers.Browsers.GRID_CHROME));
        driver.manage().window().maximize();
        sqlMainPage = new SqlMainPage(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Epic(value = "Тесты сайта 'Упражнения по sql'")
    @Feature(value = "Тест на авторизацию")
    @Story(value = "Авторизация с помощью cookies")
    @Test(retryAnalyzer = RunTestAgain.class)
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

