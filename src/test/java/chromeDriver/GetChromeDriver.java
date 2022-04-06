package chromeDriver;

import org.openqa.selenium.chrome.ChromeDriver;

public class GetChromeDriver {
    public static ChromeDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "G:\\chr\\chromedriver.exe");
        return new ChromeDriver();
    }
}
