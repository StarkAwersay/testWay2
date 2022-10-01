package pages;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.Waiting;

import static org.openqa.selenium.support.PageFactory.initElements;


public class AlertPage {
    private WebDriver driver;

    public AlertPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    @FindBy(css = ".internal_navi :nth-child(2) > a")
    private WebElement inputAlertButton;

    @FindBy(css = "#example-1-tab-2 iframe")
    private WebElement frameWindow;

    @FindBy(xpath = "//button[contains(text(),'Click')]")
    private WebElement displayAnAlertButton;

    @FindBy(id = "demo")
    private WebElement alertMessage;

    @Step("Нажатие на кнопку Input Alert")
    public void clickInputAlertButtonButton() {
        Waiting.waitingElementsDisplay(inputAlertButton, driver);
        inputAlertButton.click();
    }

    @Step("Переключение на поле с кнопкной displayAnAlertButton")
    public void switchFrame() {
        Waiting.waitingElementsDisplay(frameWindow, driver);
        driver.switchTo().frame(frameWindow);
    }

    @Step("Нажатие на кнопку displayAnAlertButton")
    public void clickDisplayAnAlertButton() {
        displayAnAlertButton.click();
    }

    @Step("Получение текста сообщение после ввода в input alert")
    public String getAlertText() {
        return Waiting.waitingElementsDisplay(alertMessage, driver).getText();
    }

}
