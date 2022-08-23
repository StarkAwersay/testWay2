package driverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

    public static WebDriver getWebDriver(String browser) throws MalformedURLException {
        WebDriver driver = null;
        if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("Internet explorer")) {
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\drivers\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        } else if (browser.equalsIgnoreCase("Opera")) {
            System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") + "\\src\\drivers\\operadriver.exe");
            driver = new OperaDriver();
        } else if (browser.equalsIgnoreCase("grid chrome")) {
            driver = new RemoteWebDriver(new URL(Properties.URL_HUB_GRID_SERVER), getCapabilities("chrome"));
        } else if (browser.equalsIgnoreCase("grid internet explorer")) {
            driver = new RemoteWebDriver(new URL(Properties.URL_HUB_GRID_SERVER), getCapabilities("internet explorer"));
        } else if (browser.equalsIgnoreCase("grid firefox")) {
            driver = new RemoteWebDriver(new URL(Properties.URL_HUB_GRID_SERVER), getCapabilities("firefox"));
        }
        return driver;
    }
}