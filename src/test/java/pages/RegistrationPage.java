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
    @FindBy(id = "allow_marketing_emails")
    private WebElement agreeButton;
    @FindBy(css = "[data-testid*='signup']")
    private WebElement signUpRegistrationButton;
    @FindBy(css = "a[class*='profile']")
    private WebElement profileButton;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    @Step("Регистрация на сайте")
    public void registration() {
        Waiting.waitingElementsDisplay(fullNameForm, driver).sendKeys(FULL_NAME);
        emailForm.sendKeys(EMAIL);
        passwordForm.sendKeys(PASSWORD);
        agreeButton.click();
        signUpRegistrationButton.click();
        Waiting.waitingElementsDisplay(profileButton, driver);
    }
}
