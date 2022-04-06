package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.Waiting;

public class SeleniumTutorialPage extends PageFactory {
    private WebDriver driver;
    @FindBy(xpath = "//li[contains(@class,'dropdown')]/a")
    private WebElement profileMenu;

    public SeleniumTutorialPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkProfileMenuIsDisplayed(){
        profileMenu.isDisplayed();
    }
}
