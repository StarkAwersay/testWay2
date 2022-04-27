package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.Waiting;

import static contsants.Constants.*;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AuthorizationPracticeSite2Page {
    private WebDriver driver;
    @FindBy(id = "username")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(css = "input[id *='input_username']")
    private WebElement descriptionUsernameField;
    @FindBy(css = "button[class*='btn']")
    private WebElement logInButton;
    @FindBy(xpath = "//div/p[contains(text(),'Yo')]")
    public WebElement textLogIn;
    @FindBy(linkText = "Logout")
    public WebElement logOutButton;
    @FindBy(css = "[class*=alert-danger]")
    public WebElement errorText;
    @FindBy(css = "[class*=control-label] ")
    public WebElement descrText;

    public AuthorizationPracticeSite2Page(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    @Step("Авторизация на странице")
    public void authorization() {
        Waiting.waitingElementsDisplay(usernameField, driver).sendKeys(USERNAME_FOR_PRACTICE_SITE);
        passwordField.sendKeys(PASSWORD_FOR_PRACTICE_SITE);
        descriptionUsernameField.sendKeys(USERNAME_DESCRIPTION_FOR_PRACTICE_SITE);
        logInButton.click();
    }

    @Step("Ввод данных в поле login")
    public void loginInput(String login) {
        Waiting.waitingElementsDisplay(usernameField, driver).sendKeys(login);
    }

    @Step("Ввод данных в поле password")
    public void passwordInput(String password) {
        passwordField.sendKeys(password);
    }

    @Step("Ввод данных в поле description")
    public void descriptionInput(String description) {
        descriptionUsernameField.sendKeys(description);
    }

    @Step("Проверка наличия ошибки")
    public void textErrorShouldBeVisible() {
        Waiting.waitingElementsDisplay(errorText, driver).isDisplayed();
    }

    @Step("Нажатие на кнопку login")
    public void logIn() {
        logInButton.click();
    }

    @Step("Проверка того, что авторизация была успешна")
    public void textLoginShouldBeVisible() {
        Waiting.waitingElementsDisplay(textLogIn, driver).isDisplayed();
    }

}
