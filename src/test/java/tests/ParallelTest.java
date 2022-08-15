package tests;

import JavaScriptExecutors.JavaScriptExecutorsHelper;
import connectionHelp.UrlConnection;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

import static capabilites.Capabilities.getCapabilities;

public class ParallelTest {

    public static RemoteWebDriver driver;

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("src\\test\\resources\\BatFiles\\hub.bat").waitFor();
        UrlConnection.urlConnection();
        Runtime.getRuntime().exec("src\\test\\resources\\BatFiles\\node1.bat");
        driver = new RemoteWebDriver(new URL("http://26.115.101.38:4444"), getCapabilities("chrome"));
        driver.manage().window().maximize();
    }

    @Test
    public void f() {
        driver.get("https://yandex.ru/");
        JavaScriptExecutorsHelper.scroll(driver);

    }

    @AfterMethod
    public void tearDownDriver() throws IOException {
        driver.quit();
    }
}

