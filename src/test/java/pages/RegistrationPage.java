package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.Waiting;

import static constants.Constants.*;
import static org.openqa.selenium.support.PageFactory.initElements;

public class RegistrationPage {
    private WebDriver driver;
    @FindBy(id = "user_name")
    private WebElement fullNameForm;
    @FindBy(id = "user_email")
    private WebElement emailForm;
    @FindBy(id = "password")
    private WebElement passwordForm;
    @FindBy(css = "[type*='check']")
    private WebElement agreeButton;
    @FindBy(css = "[data-testid*='signup']")
    private WebElement signUpRegistrationButton;
    @FindBy(css = "a[class*='profile']")
    private WebElement profileButton;
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
