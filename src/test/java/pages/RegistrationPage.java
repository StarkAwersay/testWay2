package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.Waiting;

import static constants.Constants.*;
import static org.openqa.selenium.support.PageFactory.initElements;

public class RegistrationPage {
    /**
     * Драйвер.
     */
    private WebDriver driver;
    /**
     * Web element full name form.
     */
    @FindBy(id = "user_name")
    private WebElement fullNameForm;
    /**
     * Web element email form.
     */
    @FindBy(id = "user_email")
    private WebElement emailForm;
    /**
     * Web element password form.
     */
    @FindBy(id = "password")
    private WebElement passwordForm;
    /**
     * Web element agree button.
     */
    @FindBy(css = "[type*='check']")
    private WebElement agreeButton;
    /**
     * Web element signup registration button.
     */
    @FindBy(css = "[data-testid*='signup']")
    private WebElement signUpRegistrationButton;
    /**
     * Web element profile button.
     */
    @FindBy(css = "a[class*='profile']")
    private WebElement profileButton;
    /**
     * Web element error alert.
     */
    @FindBy(css = "[class*='Bold']")
    private WebElement errorAlert;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    @Step("Регистрация на сайте")
    public void registration(String fullName, String email, String password) {
        Waiting.waitingElementsDisplay(fullNameForm, driver).sendKeys(fullName);
        emailForm.sendKeys(email);
        passwordForm.sendKeys(password);
        agreeButton.click();
        signUpRegistrationButton.click();
        Waiting.waitingElementsDisplay(profileButton, driver);
    }

    public void failRegistration(String fullname, String email, String password) {
        Waiting.waitingElementsDisplay(fullNameForm, driver).sendKeys(fullname);
        emailForm.sendKeys(email);
        passwordForm.sendKeys(password);
        agreeButton.click();
        signUpRegistrationButton.click();
    }

    public String getErrorText() {
        return Waiting.waitingElementsDisplay(errorAlert, driver).getText();
    }
}
