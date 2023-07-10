package pages;

import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import waits.Waiting;

import static org.openqa.selenium.support.PageFactory.initElements;

public class DragAndDropPage {
    private WebDriver driver;
    @FindBy(id = "draggable")
    private WebElement draggableElement;

    @FindBy(id = "droppable")
    private WebElement droppableElement;

    @FindBy(css = "[src*='default.html']")
    private WebElement frame;

    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    @Step("Переключает на поле для работы с DragAndDrop")
    public void switchFrame() {
        driver.switchTo().frame(Waiting.waitingElementsDisplay(frame, driver));
    }

    @Step("Совершает DragAndDrop")
    public void dragNDrop() {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(Waiting.waitingElementsDisplay(draggableElement, driver), droppableElement).build().perform();
    }

    @Step("Получает текст droppable элемента")
    public String getDroppableElementText() {
        return droppableElement.getText();
    }
}
