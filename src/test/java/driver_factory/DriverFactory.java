package driver_factory;

import enums.EnumBrowsers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import properties.Properties;

import java.net.MalformedURLException;
import java.net.URL;

import static capabilites.Capabilities.getCapabilities;

public class DriverFactory {
    public static WebDriver webDriver(EnumBrowsers.Browsers browser) {
        WebDriver driver = null;
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case IE:
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\drivers\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
        }
        return driver;
    }

    public static RemoteWebDriver remoteWebDriver(EnumBrowsers.GridBrowsers gridBrowser) throws MalformedURLException {
        RemoteWebDriver remoteWebDriver = null;
        switch (gridBrowser) {
            case GRID_CHROME:
                remoteWebDriver = new RemoteWebDriver(new URL(Properties.URL_HUB_GRID_SERVER), getCapabilities("chrome"));
                break;
            case GRID_FIREFOX:
                remoteWebDriver = new RemoteWebDriver(new URL(Properties.URL_HUB_GRID_SERVER), getCapabilities("firefox"));
                break;
            case GRID_IE:
                remoteWebDriver = new RemoteWebDriver(new URL(Properties.URL_HUB_GRID_SERVER), getCapabilities("internet explorer"));
                break;
        }
        return remoteWebDriver;
    }
}