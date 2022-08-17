package tests;

import connectionHelp.UrlConnection;
import cookies.CookiesHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.Feature;
import io.qameta.allure.SeverityLevel;
import runTestAgain.RunTestAgain;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SqlMainPage;
import properties.Properties;

import java.io.IOException;
import java.net.URL;

import static capabilites.Capabilities.getCapabilities;
public class TestCookies {
    private SqlMainPage sqlMainPage;
    public static RemoteWebDriver driver;
    @BeforeMethod
    public void beforeTest() throws IOException {
        UrlConnection.urlConnection();
        Runtime.getRuntime().exec("src\\test\\resources\\BatFiles\\node2.bat");
        driver = new RemoteWebDriver(new URL(Properties.URL_HUB_GRID_SERVER), getCapabilities("chrome"));
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

