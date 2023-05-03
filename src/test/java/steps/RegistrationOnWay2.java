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
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private SeleniumTutorialPage seleniumTutorialPage;

    public RegistrationOnWay2() {
        this.driver = Hooks.getDriver();
    }


    @Дано("Страница для регистрации {string}")
    public void openRegistrationPage(String page) {
        driver.get(page);
        registrationPage = new RegistrationPage(driver);
        seleniumTutorialPage = new SeleniumTutorialPage(driver);
    }

    @Тогда("Появляется меню с профилем")
    public void checkingProfileMenu() {
        seleniumTutorialPage.profileMenuShouldBeDisplayed();
    }

    @Когда("Пользователь заполняет данные: логин {string}, пароль{string} и email {string}, наажимает на кнопку Agree и кнопку Sing up")
    public void registration(String fullname, String password, String email) {
        registrationPage.registration(fullname, email, password);
    }

    @Если("Уже существует пользователь с такими данными: логин {string}, пароль{string} и email {string}")
    public void failRegistration(String fullname, String password, String email) {
        registrationPage.failRegistration(fullname, email, password);
    }

    @То("Высвечивается ошибка, что почта уже используется")
    public void alert() {
        Assert.assertEquals(registrationPage.getErrorText(), "Email is already in use.");
    }
}
