package steps;

import constants.Constants;
import hooks.Hooks;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import pages.AuthorizationPracticeSite2Page;

public class DataProvider {
    private WebDriver driver;

    public DataProvider() {
        this.driver = Hooks.getDriver();
    }

    @Дано("Страница с авторизацией на сайте PracticeSite2")
    public void openAuthorizationPage() {
        driver.get(Constants.PRACTICE_SITE_2_AUTHORIZATION_PAGE);
    }

    @Когда("Пользователь вводит данные: login{string}, password{string}, description{string} и нажимает кнопку Login")
    public void authorization(String login, String password, String description) {
        AuthorizationPracticeSite2Page authorizationPracticeSite2Page = new AuthorizationPracticeSite2Page(driver);
        authorizationPracticeSite2Page.loginInput(login);
        authorizationPracticeSite2Page.passwordInput(password);
        authorizationPracticeSite2Page.descriptionInput(description);
        authorizationPracticeSite2Page.logIn();
    }

    @Тогда("Происходит проверка того, что авторизация прошла успешно")
    public void checkingAuthorization() {
        AuthorizationPracticeSite2Page authorizationPracticeSite2Page = new AuthorizationPracticeSite2Page(driver);
        authorizationPracticeSite2Page.textLoginShouldBeVisible();
        String textLogIn = authorizationPracticeSite2Page.getTextLogin();
        Assert.assertEquals(textLogIn, "You're logged in!!");
    }

    @Когда("Пользователь вводит данные с некорректный логином: login{string}, password{string}, description{string} и нажимает кнопку Login")
    public void incorrectLoginAuthorization(String login, String password, String description) {
        AuthorizationPracticeSite2Page authorizationPracticeSite2Page = new AuthorizationPracticeSite2Page(driver);
        authorizationPracticeSite2Page.loginInput(login);
        authorizationPracticeSite2Page.passwordInput(password);
        authorizationPracticeSite2Page.descriptionInput(description);
        authorizationPracticeSite2Page.logIn();
    }

    @Тогда("Высвечивается ошибка, что введённые логин или пароль неверны")
    public void getErrorText() {
        AuthorizationPracticeSite2Page authorizationPracticeSite2Page = new AuthorizationPracticeSite2Page(driver);
        Assert.assertEquals(authorizationPracticeSite2Page.getErrorText(), "Username or password is incorrect");
    }

    @Когда("Пользователь вводит данные с некорректным паролем: login{string}, password{string}, description{string} и нажимает кнопку Login")
    public void incorrectPasswordAuthorization(String login, String password, String description) {
        AuthorizationPracticeSite2Page authorizationPracticeSite2Page = new AuthorizationPracticeSite2Page(driver);
        authorizationPracticeSite2Page.loginInput(login);
        authorizationPracticeSite2Page.passwordInput(password);
        authorizationPracticeSite2Page.descriptionInput(description);
        authorizationPracticeSite2Page.logIn();
    }

    @Когда("Пользователь вводит данные с некорректными данными для поля 'description': login{string}, password{string}, description{string} и нажимает кнопку Loginssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
    public void incorrectDescriptionAuthorization(String login, String password, String description) {
        AuthorizationPracticeSite2Page authorizationPracticeSite2Page = new AuthorizationPracticeSite2Page(driver);
        authorizationPracticeSite2Page.loginInput(login);
        authorizationPracticeSite2Page.passwordInput(password);
        authorizationPracticeSite2Page.descriptionInput(description);
        authorizationPracticeSite2Page.logIn();
    }

    @Тогда("Кнопка Login не активна, а поле 'description' подсвечивается краснымм цветом")
    public void checkDisableLoginButton() {
        AuthorizationPracticeSite2Page authorizationPracticeSite2Page = new AuthorizationPracticeSite2Page(driver);
        authorizationPracticeSite2Page.descriptionTextClick();
        Assert.assertTrue(Color.fromString("#A94442").equals(Color.fromString(authorizationPracticeSite2Page.getDescriptionTextColor())));
    }
}
