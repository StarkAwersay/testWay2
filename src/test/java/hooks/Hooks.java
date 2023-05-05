package hooks;

import driver_factory.DriverFactory;
import enums.EnumBrowsers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private static WebDriver driver;

    @Before()
    public void openBrowser() {
        driver = DriverFactory.webDriver(EnumBrowsers.Browsers.CHROME);
        driver.manage().window().maximize();
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
