package steps;

import driver_factory.DriverFactory;
import enums.EnumBrowsers;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import pages.RegistrationPage;
import pages.SeleniumTutorialPage;

import static constants.Constants.REGISTRATION_PAGE;

public class RegistrationOnWay2 {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private SeleniumTutorialPage seleniumTutorialPage;

    @Дано("Страница с регистрацией")
    public void openRegistrationPage() {
        driver = DriverFactory.webDriver(EnumBrowsers.Browsers.CHROME);
        driver.get(REGISTRATION_PAGE);
        registrationPage = new RegistrationPage(driver);
        seleniumTutorialPage = new SeleniumTutorialPage(driver);
        driver.manage().window().maximize();
    }

    @Когда("Пользователь заполняет данные, наажимает на кнопку Agree и кнопку Sing up")
    public void registration() {
        registrationPage.registration();
    }

    @Тогда("Появляется меню с профилем")
    public void checkingProfileMenu() {
        seleniumTutorialPage.profileMenuShouldBeDisplayed();
    }

    @Тогда("Происходит закрытие драйвера")
    public void tearDownDriver() {
        driver.quit();
    }
}
