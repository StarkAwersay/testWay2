package steps;

import driver_factory.DriverFactory;
import enums.EnumBrowsers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
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

    @Before
    public void openBrowser() {
        driver = DriverFactory.webDriver(EnumBrowsers.Browsers.CHROME);
        driver.manage().window().maximize();
    }

    @Дано("Страница с регистрацией")
    public void openRegistrationPage() {
        driver.get(REGISTRATION_PAGE);
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

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
