package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

public class CareersPage {
    /**
     * Драйвер.
     */
    private WebDriver driver;
    /**
     * Web element careers text.
     */
    @FindBy(xpath = "//h1[contains(text(),'CAREER')]")
    private WebElement careersText;

    public CareersPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    @Step("Получение текста после перехода в раздел Career")
    public String getCareersText() {
        String textCareers = careersText.getText();
        return textCareers;
    }
}
