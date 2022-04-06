package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.Waiting;

import static contsants.Constants.EMAIL;
import static contsants.Constants.PASSWORD;

public class AuthorizationPage extends PageFactory {
    private WebDriver driver;
    @FindBy(xpath = "//a[contains(@href,'sign_up')]")
    private WebElement signUpButton;
    @FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement logInEmailForm;
    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement logInPasswordForm;
    @FindBy(xpath = "//div[contains(@class,'content-center')]/input")
    private WebElement logInButton;


    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void logIn() {
        Waiting.waitingElementsDisplay(logInEmailForm, driver).sendKeys(EMAIL);
        logInPasswordForm.sendKeys(PASSWORD);
        logInButton.click();
    }

}
