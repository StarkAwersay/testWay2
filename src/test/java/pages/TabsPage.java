package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.Waiting;

import java.util.ArrayList;

import static org.openqa.selenium.support.PageFactory.initElements;

public class TabsPage {
    private WebDriver driver;

    public TabsPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    @FindBy(linkText = "New Browser Tab")
    private WebElement newTabButton;

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

    @Step("Переключение на следующую вкладку")
    public void switchTabs() {
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
    }

    @Step("Получение количества открытых вкладок")
    public int getCountTabs() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        return tabs.size();
    }
}
