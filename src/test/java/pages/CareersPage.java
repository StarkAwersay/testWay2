package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CareersPage extends PageFactory {
    private WebDriver driver;
    @FindBy(xpath = "//div[contains(@class,'elementor')]/h1")
    public WebElement careersText;
    public CareersPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
