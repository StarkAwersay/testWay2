package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import waits.Waiting;

import static org.openqa.selenium.support.PageFactory.initElements;


public class MainPage {
    private WebDriver driver;
    @FindBy(className = "ast-above-header")
    private WebElement contactDetailsBanner;
    @FindBy(css = "div[class*='site-primary-header-wrap ast']")
    private WebElement horizontalMenu;
    @FindBy(css = "div[id*='2']>div[class*='widget']")
    private WebElement advertisingBanner;
    @FindBy(xpath = "//a[contains(@class,'menu-link')]/span[contains(text(),'Home')]")
    private WebElement homeButton;
    @FindBy(className = "elementor-element-8e1bc6a")
    private WebElement certificationPanel;
    @FindBy(css = "li[id*='menu']>a[href*='careers']")
    private WebElement careersButton;
    @FindBy(xpath = "//li[contains(@id,'27617')]/a[contains(@class,'menu-link')]")
    private WebElement resourcesButton;
    @FindBy(css = "div[class*='close-button']")
    private WebElement closeButton;
    @FindBy(css = " div[class*='box-carousel-wrap']>div[aria-label*='Next']")
    private WebElement sliderButton;
    @FindBy(css = "div[data-id*='50']>div[class*='swiper']")
    public WebElement coursePanel;
    @FindBy(css = "div[class*='elementor']>div[class*='swiper-slide-active']")
    public WebElement activeBlockSwiper;
    @FindBy(css = "img[src*='automationlogo']")
    private WebElement elementSlider;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public void advertisingBannerShouldBeVisible() {
        resourcesButton.click();
        Waiting.waitingElementsDisplay(advertisingBanner, driver).isDisplayed();
        closeButton.click();
    }

    public void contactDetailsBannerShouldBeVisible() {
        Waiting.waitingElementsDisplay(contactDetailsBanner, driver).isDisplayed();
    }

    public void horizontalMenuShouldBeVisible() {
        Waiting.waitingElementsDisplay(horizontalMenu, driver).isDisplayed();
    }

    public void certificationPanelShouldBeVisible() {
        Waiting.waitingElementsDisplay(certificationPanel, driver).isDisplayed();
    }

    public void BlockSliderShouldBeVisible() {
        Waiting.waitingElementsDisplay(activeBlockSwiper, driver).isDisplayed();
    }

    public void blockSliderSwipe() {
        Actions move = new Actions(driver);
        move.dragAndDropBy(activeBlockSwiper, -500, 0).perform();
    }

    public void sliderButtonClick() {
        sliderButton.click();
        resourcesButton.click();
    }

    public void elementSliderShouldBeVisible() {
        Waiting.waitingElementsDisplay(elementSlider, driver).isDisplayed();
    }

    public void careersButtonClick() {
        careersButton.click();
    }
}