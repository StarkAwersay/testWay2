package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.Waiting;


public class MainPage extends PageFactory {
    private WebDriver driver;
    @FindBy(xpath = "//div[contains(@class,'ast-above-header-bar')]")
    private WebElement contactDetailsBanner;
    @FindBy(xpath = "//div[contains(@class,'site-primary-header-wrap ast')]")
    private WebElement horizontalMenu;
    @FindBy(xpath = "//div[contains(@class,'dialog-widget-content dialog-lightbox-widget-content animated')]")
    private WebElement advertisingBanner;
    @FindBy(xpath = "//a[contains(@class,'menu-link')]/span[contains(text(),'Home')]")
    private WebElement homeButton;
    @FindBy(xpath = "//div[contains(@class,'elementor-widget')]/div[contains(@class,'elementor-element elementor-element-8')]")
    private WebElement certificationPanel;
    @FindBy(xpath = "//li[contains(@id,'27621')]/a[contains(@href,'careers')]")
    private WebElement careersButton;
    @FindBy(xpath = "//li[contains(@id,'27617')]/a[contains(@class,'menu-link')]")
    private WebElement resourcesButton;
    @FindBy(xpath = "//div[contains(@class,'animated')]/div[contains(@class,'close-button')]")
    private WebElement closeButton;
    @FindBy(xpath = "//div[contains(@class,'pp-slider-arrow swiper-button-next swiper-button-next-c50f9f0')]")
    private WebElement sliderButton;
    @FindBy(xpath = "//div[contains(@data-id,'50')]/div[contains(@class,'swiper-container')]")
    public WebElement coursePanel;
    @FindBy(xpath = "//div[contains(@class,'swiper-wrapper elementor-slides')]/div[contains(@class,'swiper-slide-active')]")
    public WebElement blockSlider;
    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkingElements() throws InterruptedException {
        contactDetailsBanner.isDisplayed();
        horizontalMenu.isDisplayed();
        certificationPanel.isDisplayed();
        resourcesButton.click();
        Waiting.waitingElementsDisplay(advertisingBanner, driver).isDisplayed();
        closeButton.click();
    }
    public void blockSliderSwipe(){
        Actions move = new Actions(driver);
        move.dragAndDropBy(blockSlider,-500,0).perform();
    }

    public void sliderButtonClick() throws InterruptedException {
        sliderButton.click();
        Thread.sleep(2000);
        horizontalMenu.isDisplayed();
    }



    public void careersButtonClick() {
        careersButton.click();
    }


}