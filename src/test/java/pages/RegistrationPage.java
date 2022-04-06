package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.Waiting;

import static contsants.Constants.*;

public class RegistrationPage extends PageFactory {
    private WebDriver driver;
    @FindBy(xpath = "//*[@id=\"user_name\"]")
    private WebElement fullNameForm;
    @FindBy(xpath = "//*[@id=\"user_email\"]")
    private WebElement emailForm;
    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement passwordForm;
    @FindBy(xpath = "//*[@id=\"allow_marketing_emails\"]")
    private WebElement agreeButton;
    @FindBy(xpath = "//div[contains(@class,'m-b-1')]/input")
    private WebElement signUpRegistrationButton;
    @FindBy(xpath = "//a[contains(@class,'my-profile-dropdown')]")
    private WebElement profileButton;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void registration() {
        Waiting.waitingElementsDisplay(fullNameForm, driver).sendKeys(FULLNAME);
        emailForm.sendKeys(EMAIL);
        passwordForm.sendKeys(PASSWORD);
        agreeButton.click();
        signUpRegistrationButton.click();
        Waiting.waitingElementsDisplay(profileButton,driver);
    }
}
