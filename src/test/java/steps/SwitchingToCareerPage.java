package steps;

import constants.Constants;
import hooks.Hooks;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CareersPage;
import pages.MainPage;

public class SwitchingToCareerPage {
    private WebDriver driver;

    public SwitchingToCareerPage() {
        this.driver = Hooks.getDriver();
    }

    @Дано("Главная страница")
    public void openMainPage() {
        driver.get(Constants.MAIN_PAGE);
    }

    @Когда("Пользователь нажимает на кнопку Career на главном меню")
    public void switchOnCareerPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.careersButtonClick();
    }

    @Тогда("Произошёл того, что был выполнен переход на страницу Career")
    public void checkingCareerPage() {
        CareersPage careersPage = new CareersPage(driver);
        Assert.assertEquals(careersPage.getCareersText(), "CAREER");
    }

}
