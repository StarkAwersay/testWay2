package steps;

import hooks.Hooks;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.cucumber.java.ru.То;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.RegistrationPage;
import pages.SeleniumTutorialPage;

import static constants.Constants.REGISTRATION_PAGE;

public class RegistrationOnWay2 {
    /**
     * драйвер.
     */
    private WebDriver driver;

    public RegistrationOnWay2() {
        this.driver = Hooks.getDriver();
    }

    @Дано("Страница для Регистрации")
    public void openRegistrationPage() {
        driver.get(REGISTRATION_PAGE);
    }

    @Тогда("Появляется меню с профилем")
    public void checkingProfileMenu() {
        SeleniumTutorialPage seleniumTutorialPage = new SeleniumTutorialPage(driver);
        seleniumTutorialPage.profileMenuShouldBeDisplayed();
    }

    @Когда("Пользователь заполняет данные: логин {string}, пароль{string} и email {string}, наажимает на кнопку Agree и кнопку Sing up")
    public void registration(String fullname, String password, String email) {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(fullname, email, password);
    }

    @Если("Уже существует пользователь с такими данными: логин {string}, пароль{string} и email {string}")
    public void failRegistration(String fullname, String password, String email) {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.failRegistration(fullname, email, password);
    }

    @То("Высвечивается ошибка, что почта уже используется")
    public void alert() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        Assert.assertEquals(registrationPage.getErrorText(), "Email is already in use.");
    }
}
