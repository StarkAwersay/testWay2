package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

public class SqlMainPage {
    private WebDriver driver;
    @FindBy(css = "td>input[name='login']")
    private WebElement logIn;
    @FindBy(xpath = "td>input[name='psw']")
    private WebElement password;
    @FindBy(xpath = "td>input[value='Вход']")
    private WebElement logInButton;
    @FindBy(css = "b>a[href*='personal']")
    private WebElement profileName;

    public SqlMainPage(WebDriver driver){
        this.driver = driver;
        initElements(driver,this);
    }
    public void authorization(){
        logIn.sendKeys("Seeshstark");
        password.sendKeys("rec!vPL8aploX&");
        logInButton.click();
    }
    public String getProfileName(){
        String profileNameText = profileName.getText();
        return profileNameText;
    }
}
