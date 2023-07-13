package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DragAndDropPage;
import properties.Properties;

public class DragAndDropTest extends BasicTestClass {
    /**
     * Страница drag And drop .
     */
    private DragAndDropPage dragAndDropPage;

    @BeforeMethod
    public void beforeMethod() {
        dragAndDropPage = new DragAndDropPage(driver);
    }

    @Description(value = "Тест проверяет работу dragNDrop элемента")
    @Severity(SeverityLevel.NORMAL)
    @Epic(value = "Тесты сайта Way2Automation")
    @Feature(value = "Тестирование формы DragNDrop")
    @Story(value = "Проверка работоспособности DragNDrop элементов")
    @Test
    public void dragAndDropTest() {
        driver.get(Properties.DRAG_N_DROP_PAGE);
        dragAndDropPage.switchFrame();
        dragAndDropPage.dragNDrop();
        Assert.assertEquals(dragAndDropPage.getDroppableElementText(), "Dropped!", "DragAndDrop не произошёл");
    }
}
