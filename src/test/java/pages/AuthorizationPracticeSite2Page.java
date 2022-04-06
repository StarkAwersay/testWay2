package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.Waiting;

import static contsants.Constants.*;

public class AuthorizationPracticeSite2Page extends PageFactory {
    private WebDriver driver;
    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement usernameField;
    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement passwordField;
    @FindBy(css = "#formly_1_input_username_0")
    private WebElement descriptionUsernameField;
    @FindBy(xpath = "//div[contains(@class,'form-actions')]/button")
    private WebElement logInButton;
    @FindBy(xpath = "//div[contains(@class,'ng-scope')]/p[contains(@class,'ng-scope')]")
    public WebElement textLogIn;
    @FindBy(xpath = "//a[contains(@href,'#/login')]")
    public WebElement LogOutButton;


    public AuthorizationPracticeSite2Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void authorizationPracticeSite2() {
        Waiting.waitingElementsDisplay(usernameField, driver).sendKeys(USERNAMEFORPRACTICESITE);
        passwordField.sendKeys(PASSWORDFORPRACTICESITE);
        descriptionUsernameField.sendKeys(USERNAMEDESCRIPTIONFORPRACTICESITE);
        logInButton.click();
        Waiting.waitingElementsDisplay(textLogIn, driver).isDisplayed();


    }

}
