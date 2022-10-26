package steps;

import driver_factory.DriverFactory;
import enums.EnumBrowsers;
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

    @Дано("Главная страница {string}")
    public void openMainPage(String MainPage) {
        driver = DriverFactory.webDriver(EnumBrowsers.Browsers.CHROME);
        driver.get(MainPage);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        careersPage = new CareersPage(driver);
    }

    @Когда("Пользователь нажимает на кнопку Career на главном меню")
    public void switchOnCareerPage() {
        mainPage.careersButtonClick();
    }

    @Тогда("Произошёл того, что был выполнен переход на струницу Career")
    public void checkingCareerPage() {
        Assert.assertEquals(careersPage.getCareersText(), "CAREER");
        driver.quit();
    }

}
