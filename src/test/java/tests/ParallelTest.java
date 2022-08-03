package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

import static capabilites.Capabilities.getCapabilities;

public class ParallelTest {

    public static RemoteWebDriver driver;

    @BeforeTest
    public RemoteWebDriver beforeTest() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("C:\\Users\\Илья Шпалочкин\\YandexDisk\\github.com\\StarkAwersay\\Nado\\testWay2\\src\\test\\resources\\BatFiles\\hub.bat");
        Runtime.getRuntime().exec("C:\\Users\\Илья Шпалочкин\\YandexDisk\\github.com\\StarkAwersay\\Nado\\testWay2\\src\\test\\resources\\BatFiles\\node1.bat");
        return new RemoteWebDriver(new URL("http://26.115.101.38:4444/wd/hub"), getCapabilities("chrome"));
    }
    @Test
    public void f(){
        driver.get("https://yandex.ru/");

    }
}
