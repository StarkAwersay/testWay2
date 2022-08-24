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
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "ege":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "internet explorer":
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\drivers\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            case "opera":
                System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") + "\\src\\drivers\\operadriver.exe");
                driver = new OperaDriver();
                break;
            case "grid chrome":
                driver = new RemoteWebDriver(new URL(Properties.URL_HUB_GRID_SERVER), getCapabilities("chrome"));
                break;
            case "grid firefox":
                driver = new RemoteWebDriver(new URL(Properties.URL_HUB_GRID_SERVER), getCapabilities("firefox"));
                break;
            case "grid internet explorer":
                driver = new RemoteWebDriver(new URL(Properties.URL_HUB_GRID_SERVER), getCapabilities("internet explorer"));
                break;
        }
        return driver;
    }
}