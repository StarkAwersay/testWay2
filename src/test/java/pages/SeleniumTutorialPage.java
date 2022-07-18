package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    @Step("Проверка наличия меню профиля")
    public void profileMenuShouldBeDisplayed() {
        Waiting.waitingElementsDisplay(profileMenu, driver).isDisplayed();
    }
}

