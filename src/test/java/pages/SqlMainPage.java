package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import properties.Properties;
import waits.Waiting;

import static org.openqa.selenium.support.PageFactory.initElements;

public class SqlMainPage {
    private WebDriver driver;
    @FindBy(css = "td>[name='loginn']")
    private WebElement logIn;

    @FindBy(css = "td>[name='psw']")
    private WebElement password;

    @FindBy(css = "td>[value='Вход']")
    private WebElement logInButton;

    @FindBy(css = "b>a[href*='personal']")
    private WebElement profileName;

    public SqlMainPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    @Step("Авторизация на странице")
    public void authorization() {
        logIn.sendKeys(Properties.LOGIN_SQL_PAGE);
        password.sendKeys(Properties.PASSWORD_SQL_PAGE);
        logInButton.click();
    }

    @Step("Получение имени профиля")
    public String getProfileName() {
        String profileNameText = Waiting.waitingElementsDisplay(profileName, driver).getText();
        return profileNameText;
    }
}
