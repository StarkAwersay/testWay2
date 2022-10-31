package steps;

import constants.Constants;
import driver_factory.DriverFactory;
import enums.EnumBrowsers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AuthorizationPage;
import pages.SeleniumTutorialPage;


public class AuthorizationOnWay2 {
    private WebDriver driver;
    public AuthorizationPage authorizationPage;
    public SeleniumTutorialPage seleniumTutorialPage;

    @Before
    public void openBrowser() {
        driver = DriverFactory.webDriver(EnumBrowsers.Browsers.CHROME);
        driver.manage().window().maximize();
    }

    @Дано("Страница с авторизацией")
    public void openRegistrationPage() {
        driver.get(Constants.AUTHORIZATION_PAGE);
        authorizationPage = new AuthorizationPage(driver);
        seleniumTutorialPage = new SeleniumTutorialPage(driver);
    }

    @Когда("Пользователь вводит данные: email {string} и пароль {string}")
    public void authorization(String email, String password) {
        authorizationPage.logIn(email, password);
    }

    @Тогда("Появляется авторизованная страница")
    public void checkingProfileMenu() {
        seleniumTutorialPage.profileMenuShouldBeDisplayed();
        driver.quit();
    }

    @Если("Логин:{string} или пароль:{string} неверны")
    public void failAuthorization(String email, String password) {
        authorizationPage.logIn(email, password);
    }

    @То("Высвечивается ошибка, что логин или пароль неверны")
    public void alert() {
        Assert.assertEquals(authorizationPage.getErrorText(), "Your email or password is incorrect.");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
