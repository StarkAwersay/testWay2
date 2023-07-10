package pages;

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

    @FindBy(css = "[href*=tab-2]")
    private WebElement inputAlertButton;

    @FindBy(css = "#example-1-tab-2 iframe")
    private WebElement frameWindow;

    @FindBy(xpath = "//button[contains(text(),'Click')]")
    private WebElement displayAnAlertButton;

    @FindBy(id = "demo")
    private WebElement alertMessage;

    @Step("Нажатие на кнопку Input Alert")
    public AlertPage clickInputAlertButton() {
        Waiting.waitingElementsDisplay(inputAlertButton, driver);
        inputAlertButton.click();
        return this;
    }

    @Step("Переключение на поле с кнопкной displayAnAlertButton")
    public AlertPage switchFrame() {
        Waiting.waitingElementsDisplay(frameWindow, driver);
        driver.switchTo().frame(frameWindow);
        return this;
    }

    @Step("Нажатие на кнопку displayAnAlertButton")
    public AlertPage clickDisplayAlertButton() {
        displayAnAlertButton.click();
        return this;
    }

    @Step("Получение текста сообщение после ввода в input alert")
    public String getAlertText() {
        return Waiting.waitingElementsDisplay(alertMessage, driver).getText();
    }

}
