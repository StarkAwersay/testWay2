package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waits.Waiting;

import static org.openqa.selenium.support.PageFactory.initElements;


public class BasicAuthPage {
    private WebDriver driver;

    public BasicAuthPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    @FindBy(id = "displayImage")
    private WebElement displayImageButton;
    @FindBy(id = "downloadImg")
    private WebElement authenticatedImage;

    @Step("Нажатие на кнопку Display Image")
    public BasicAuthPage clickOnDisplayImage() {
        displayImageButton.click();
        return this;
    }

    @Step("Проверка отображаения изображения с данными авторизации на странице")
    public Boolean getDisplayImageStatus() {
        Waiting.waitingElementsDisplay(authenticatedImage, driver);
        return authenticatedImage.isDisplayed();
    }
}
