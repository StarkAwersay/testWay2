package pages;

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

    public void clickOnSearchBar() {
        searchBar.click();
    }

    public void remoteFocus() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].blur();", searchBar);
    }

    public String getSearchBarText() {
        String searchBarText = searchBarPlaceHolder.getText();
        return searchBarText;
    }

    public Long checkScroll() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Long value = (Long) jse.executeScript("return window.pageYOffset;");
        return value;
    }

    public void Scroll() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)");
    }
}
