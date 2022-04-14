package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.PageFactory.initElements;

public class CareersPage {
    private WebDriver driver;
    @FindBy(xpath = "//h1[contains(text(),'CAREER')]")
    public WebElement careersText;

    public CareersPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }
}
