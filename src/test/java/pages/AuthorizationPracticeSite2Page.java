package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.Waiting;

import static constants.Constants.*;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AuthorizationPracticeSite2Page {
    /**
     * драйвер.
     */
    private WebDriver driver;
    /**
     * Web Element usernameField.
     */
    @FindBy(id = "username")
    private WebElement usernameField;
    /**
     * Web Element passwordField.
     */
    @FindBy(id = "password")
    private WebElement passwordField;
    /**
     * Web Element descriptionUsernameField.
     */
    @FindBy(css = "input[id *='input_username']")
    private WebElement descriptionUsernameField;
    /**
     * Web Element logInButton.
     */
    @FindBy(css = "button[class*='btn']")
    private WebElement logInButton;
    /**
     * Web Element textLogIn.
     */
    @FindBy(xpath = "//div/p[contains(text(),'Yo')]")
    private WebElement textLogIn;
    /**
     * Web Element logOutButton.
     */
    @FindBy(linkText = "Logout")
    private WebElement logOutButton;
    /**
     * Web Element errorText.
     */
    @FindBy(css = "[class*=alert-danger]")
    private WebElement errorText;
    /**
     * Web Element descriptionText.
     */
    @FindBy(css = "[class*=control-label] ")
    private WebElement descriptionText;

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

    @Step("Получение текста при удачной авторизации")
    public String getTextLogin() {
        String textLogin = textLogIn.getText();
        return textLogin;
    }

    @Step("Нажатие на текст описания")
    public void descriptionTextClick() {
        descriptionText.click();
    }

    @Step("Получение цвета текста описания")
    public String getDescriptionTextColor() {
        String descriptionColor = descriptionText.getCssValue("color");
        return descriptionColor;
    }

    @Step("Получения текста ошибки")
    public String getErrorText() {
        String textError = Waiting.waitingElementsDisplay(errorText, driver).getText();
        return textError;
    }

}
