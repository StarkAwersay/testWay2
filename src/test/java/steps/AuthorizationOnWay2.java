package steps;

import hooks.Hooks;
import constants.Constants;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.То;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AuthorizationPage;
import pages.SeleniumTutorialPage;


public class AuthorizationOnWay2 {
    private WebDriver driver;
    public AuthorizationPage authorizationPage;
    public SeleniumTutorialPage seleniumTutorialPage;

    public AuthorizationOnWay2() {
        this.driver = Hooks.getDriver();
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
}
