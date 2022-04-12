package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import waits.Waiting;

import static contsants.Constants.*;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AuthorizationPracticeSite2Page {
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
    @FindBy(linkText= "Logout")
    public WebElement LogOutButton;


    public AuthorizationPracticeSite2Page(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public void authorizationPracticeSite2() {
        Waiting.waitingElementsDisplay(usernameField, driver).sendKeys(USERNAME_FOR_PRACTICE_SITE);
        passwordField.sendKeys(PASSWORD_FOR_PRACTICE_SITE);
        descriptionUsernameField.sendKeys(USERNAME_DESCRIPTION_FOR_PRACTICE_SITE);
        logInButton.click();
    }
    public void textLoginShouldBeVisible(){
        Assert.assertTrue(Waiting.waitingElementsDisplay(textLogIn,driver).isDisplayed());
    }

}
