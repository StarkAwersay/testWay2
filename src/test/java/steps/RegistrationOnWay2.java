package steps;

import driver_factory.DriverFactory;
import enums.EnumBrowsers;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.cucumber.java.ru.То;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.RegistrationPage;
import pages.SeleniumTutorialPage;


public class RegistrationOnWay2 {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private SeleniumTutorialPage seleniumTutorialPage;

    @Дано("Страница с регистрацией {string}")
    public void openRegistrationPage(String REGISTRATION_PAGE) {
        driver = DriverFactory.webDriver(EnumBrowsers.Browsers.CHROME);
        driver.get(REGISTRATION_PAGE);
        registrationPage = new RegistrationPage(driver);
        seleniumTutorialPage = new SeleniumTutorialPage(driver);
        driver.manage().window().maximize();
    }

    @Тогда("Появляется меню с профилем")
    public void checkingProfileMenu() {
        seleniumTutorialPage.profileMenuShouldBeDisplayed();
    }

    @Когда("Пользователь заполняет данные: логин {string}, пароль{string} и email {string}, наажимает на кнопку Agree и кнопку Sing up")
    public void registration(String FULLNAME, String PASSWORD, String EMAIL) {
        registrationPage.registration(FULLNAME, EMAIL, PASSWORD);
    }

    @Если("Уже существует пользователь с такими данными: логин {string}, пароль{string} и email {string}")
    public void failRegistration(String FULLNAME, String PASSWORD, String EMAIL) {
        registrationPage.failRegistration(FULLNAME, EMAIL, PASSWORD);
    }

    @То("Высвечивается ошибка, что почта уже используется")
    public void alert() {
        Assert.assertEquals(registrationPage.getErrorText(), "Email is already in use.");
    }

}
