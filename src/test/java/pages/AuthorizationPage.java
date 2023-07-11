package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.Waiting;

import static constants.Constants.EMAIL;
import static constants.Constants.PASSWORD;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AuthorizationPage {
    /**
     *драйвер.
     */
    private WebDriver driver;
    /**
     * Web Element logInEmailForm.
     */
    @FindBy(css = "#email")
    private WebElement logInEmailForm;
    /**
     * Web Element logInPasswordForm.
     */
    @FindBy(css = "#password")
    private WebElement logInPasswordForm;
    /**
     * Web Element logInButton.
     */
    @FindBy(css = "input[name='commit']")
    private WebElement logInButton;
    /**
     * Web Element errorAlert.
     */
    @FindBy(css = "[class*='Bold']")
    private WebElement errorAlert;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    @Step("Авторизация на странице")
    public void logIn(String email, String password) {
        Waiting.waitingElementsDisplay(logInEmailForm, driver).sendKeys(email);
        logInPasswordForm.sendKeys(password);
        logInButton.click();
    }

    public String getErrorText() {
        return Waiting.waitingElementsDisplay(errorAlert, driver).getText();
    }
}
