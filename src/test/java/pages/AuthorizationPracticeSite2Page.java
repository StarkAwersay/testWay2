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
    public WebElement usernameField;
    @FindBy(id = "password")
    public WebElement passwordField;
    @FindBy(css = "input[id *='input_username']")
    public WebElement descriptionUsernameField;
    @FindBy(css = "button[class*='btn']")
    public WebElement logInButton;
    @FindBy(xpath = "//div/p[contains(text(),'Yo')]")
    public WebElement textLogIn;
    @FindBy(linkText = "Logout")
    public WebElement logOutButton;
    @FindBy(xpath = "/html/body/div[3]/div/div/div/div[2]")
    public WebElement errorText;

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


    @Step("Проверка того, что авторизация была успешна")
    public void textLoginShouldBeVisible() {
        Waiting.waitingElementsDisplay(textLogIn, driver).isDisplayed();
    }

}
