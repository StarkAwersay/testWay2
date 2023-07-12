package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.Waiting;

import java.util.ArrayList;

import static org.openqa.selenium.support.PageFactory.initElements;

public class TabsPage {
    /**
     * Драйвер.
     */
    private WebDriver driver;

    public TabsPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }
    /**
     * Web element new tab button.
     */

    @FindBy(linkText = "New Browser Tab")
    private WebElement newTabButton;
    /**
     * Web element frame window.
     */
    @FindBy(css = "#example-1-tab-1  iframe")
    private WebElement frameWindow;

    @Step("Переключение на поле для доступа к кнопке New Browser Tab")
    public void switchFrame() {
        Waiting.waitingElementsDisplay(frameWindow, driver);
        driver.switchTo().frame(frameWindow);
    }

    @Step("Нажатие на кнопку New Browser Tab")
    public void clickOnNewTabButton() {
        Waiting.waitingElementsDisplay(newTabButton, driver).click();
    }
}
