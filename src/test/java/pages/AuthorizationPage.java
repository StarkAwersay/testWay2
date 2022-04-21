package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.Waiting;

import static contsants.Constants.EMAIL;
import static contsants.Constants.PASSWORD;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AuthorizationPage {
    private WebDriver driver;
    @FindBy(css = "#email")
    private WebElement logInEmailForm;
    @FindBy(css = "#password")
    private WebElement logInPasswordForm;
    @FindBy(css = "input[name='commit']")
    private WebElement logInButton;
    
    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public void logIn() {
        Waiting.waitingElementsDisplay(logInEmailForm, driver).sendKeys(EMAIL);
        logInPasswordForm.sendKeys(PASSWORD);
        logInButton.click();
    }

}
