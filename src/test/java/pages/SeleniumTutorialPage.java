package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import waits.Waiting;

import static org.openqa.selenium.support.PageFactory.initElements;

public class SeleniumTutorialPage {
    private WebDriver driver;
    @FindBy(css = "a[class*='profile']")
    private WebElement profileMenu;

    public SeleniumTutorialPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public void profileMenuShouldBeDisplayed() {
        Waiting.waitingElementsDisplay(profileMenu, driver).isDisplayed();
    }
}

