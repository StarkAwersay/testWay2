package steps;

import constants.Constants;
import driver_factory.DriverFactory;
import enums.EnumBrowsers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CareersPage;
import pages.MainPage;

public class SwitchingToCareerPage {
    private WebDriver driver;
    private MainPage mainPage;
    private CareersPage careersPage;

    @Before
    public void openBrowser(){
        driver = DriverFactory.webDriver(EnumBrowsers.Browsers.CHROME);
        driver.manage().window().maximize();
    }
    @Дано("Главная страница")
    public void openMainPage() {
        driver.get(Constants.MAIN_PAGE);
        mainPage = new MainPage(driver);
        careersPage = new CareersPage(driver);
    }

    @Когда("Пользователь нажимает на кнопку Career на главном меню")
    public void switchOnCareerPage() {
        mainPage.careersButtonClick();
    }

    @Тогда("Произошёл того, что был выполнен переход на страницу Career")
    public void checkingCareerPage() {
        Assert.assertEquals(careersPage.getCareersText(), "CAREER");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

}
