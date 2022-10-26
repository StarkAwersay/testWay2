package steps;

import driver_factory.DriverFactory;
import enums.EnumBrowsers;
import io.cucumber.java.ru.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AuthorizationPage;
import pages.SeleniumTutorialPage;


public class AuthorizationOnWay2 {
    private WebDriver driver;
    public AuthorizationPage authorizationPage;
    public SeleniumTutorialPage seleniumTutorialPage;

    @Дано("Страница с авторизацией {string}")
    public void openRegistrationPage(String AUTHORIZATION_PAGE) {
        driver = DriverFactory.webDriver(EnumBrowsers.Browsers.CHROME);
        driver.get(AUTHORIZATION_PAGE);
        authorizationPage = new AuthorizationPage(driver);
        seleniumTutorialPage = new SeleniumTutorialPage(driver);
        driver.manage().window().maximize();
    }

    @Когда("Пользователь вводит данные: email {string} и пароль {string}")
    public void authorization(String EMAIL, String PASSWORD) {
        authorizationPage.logIn(EMAIL,PASSWORD);
    }
    @Тогда("Появляется авторизованная страница")
    public void checkingProfileMenu() {
        seleniumTutorialPage.profileMenuShouldBeDisplayed();driver.quit();
    }

    @Если("Логин:{string} или пароль:{string} неверны")
    public void failAuthorization(String EMAIL, String PASSWORD) {
        authorizationPage.logIn(EMAIL,PASSWORD);
    }

    @То("Высвечивается ошибка, что логин или пароль неверны")
    public void alert() {
        Assert.assertEquals(authorizationPage.getErrorText(), "Your email or password is incorrect.");
        driver.quit();
    }
}
