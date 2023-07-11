package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import waits.Waiting;

import static org.openqa.selenium.support.PageFactory.initElements;

public class MainPage {
    /**
     * драйвер.
     */
    private WebDriver driver;
    /**
     * Web element contactDetailsBanner.
     */
    @FindBy(className = "ast-above-header")
    private WebElement contactDetailsBanner;
    /**
     * Web element horizontalMenu.
     */
    @FindBy(css = "[class*='site-primary-header-wrap ast']")
    private WebElement horizontalMenu;
    /**
     * Web element advertisingBanner.
     */
    @FindBy(css = "[id*='2']>[class*='widget']")
    private WebElement advertisingBanner;
    /**
     * Web element homeButton.
     */
    @FindBy(css = "[id*='2757']>a>[class*='menu']")
    private WebElement homeButton;
    /**
     * Web element certificationPanel.
     */
    @FindBy(className = "elementor-element-8e1bc6a")
    private WebElement certificationPanel;
    /**
     * Web element careersButton.
     */
    @FindBy(css = "[id*='menu']>[href*='careers']")
    private WebElement careersButton;
    /**
     * Web element resourcesButton.
     */
    @FindBy(css = "[id*='276']>[href='#']")
    private WebElement resourcesButton;
    /**
     * Web element closeButton.
     */
    @FindBy(css = "[class*='close-button']")
    private WebElement closeButton;
    /**
     * Web element sliderButton.
     */
    @FindBy(css = "[class*='box']>[aria-label*='Next']")
    private WebElement sliderButton;
    /**
     * Web element coursePanel.
     */
    @FindBy(css = "[data-id*='50']>[class*='swiper']")
    public WebElement coursePanel;
    /**
     * Web element activeBlockSwiper.
     */
    @FindBy(css = "[class*='elem']>[class*='slide-active']")
    public WebElement activeBlockSwiper;
    /**
     * Web element elementSlider.
     */
    @FindBy(css = "[src*='automationlogo']")
    private WebElement elementSlider;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    @Step("Проверка наличия рекламного баннера на странице и его закрытие")
    public void advertisingBannerShouldBeVisible() {
        resourcesButton.click();
        Waiting.waitingElementsDisplay(advertisingBanner, driver).isDisplayed();
        closeButton.click();
    }

    @Step("Проверка наличия баннера с контактами")
    public void contactDetailsBannerShouldBeVisible() {
        Waiting.waitingElementsDisplay(contactDetailsBanner, driver).isDisplayed();
    }

    @Step("Проверка наличия горизонтального меню")
    public void horizontalMenuShouldBeVisible() {
        Waiting.waitingElementsDisplay(horizontalMenu, driver).isDisplayed();
    }

    @Step("Проверка наличия панели с сертификатами")
    public void certificationPanelShouldBeVisible() {
        Waiting.waitingElementsDisplay(certificationPanel, driver).isDisplayed();
    }

    @Step("Проверка наличия блокслайдера")
    public void blockSliderShouldBeVisible() {
        Waiting.waitingElementsDisplay(activeBlockSwiper, driver).isDisplayed();
    }

    @Step("Свайп блок слайдера")
    public void blockSliderSwipe() {
        Actions move = new Actions(driver);
        move.dragAndDropBy(activeBlockSwiper, -500, 0).perform();
    }

    @Step("Нажатие на кнопку блока свайпера")
    public void sliderButtonClick() {
        sliderButton.click();
        resourcesButton.click();
    }

    @Step("Получение текста блока-слайдера")
    public String getBlockSliderText() {
        String blockSliderText = activeBlockSwiper.getText();
        return blockSliderText;
    }

    @Step("Получение текста панели с курсами")
    public String getCoursePanelText() {
        String coursePanelText = coursePanel.getText();
        return coursePanelText;
    }

    @Step("Проверка наличия элементов слайдера")
    public void elementSliderShouldBeVisible() {
        Waiting.waitingElementsDisplay(elementSlider, driver).isDisplayed();
    }

    @Step("Нажатие на кнопку Career")
    public void careersButtonClick() {
        careersButton.click();
    }
}