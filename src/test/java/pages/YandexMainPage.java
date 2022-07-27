package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

public class YandexMainPage {
    private WebDriver driver;

    @FindBy(css = "input[aria-label='Запрос']")
    private WebElement searchBar;

    @FindBy(css = "div[class='search2__placeholder']")
    private WebElement searchBarPlaceHolder;

    public YandexMainPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    @Step("Нажатие на поисковую строку")
    public void clickOnSearchBar() {
        searchBar.click();
    }

    @Step("Передача вебэелемента 'Поисковая строка' ")
    public WebElement searchBar() {
        return searchBar;
    }
}
