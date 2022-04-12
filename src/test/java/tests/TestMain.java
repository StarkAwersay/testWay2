package tests;


import chromeDriver.GetChromeDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;


import static contsants.Constants.*;

public class TestMain {
    private WebDriver driver;
    private MainPage mainPage;
    private AuthorizationPage authorizationPage;
    private RegistrationPage registrationPage;
    private SeleniumTutorialPage seleniumTutorialPage;
    private AuthorizationPracticeSite2Page authorizationPracticeSite2Page;
    private CareersPage careersPage;

    @BeforeMethod
    public void BeforeTest() {
        driver = GetChromeDriver.getChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        authorizationPage = new AuthorizationPage(driver);
        registrationPage = new RegistrationPage(driver);
        seleniumTutorialPage = new SeleniumTutorialPage(driver);
        authorizationPracticeSite2Page = new AuthorizationPracticeSite2Page(driver);
        careersPage = new CareersPage(driver);
    }
    @Test(priority = 1)
    public void mainPageTest() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        driver.get(MAIN_PAGE);
        mainPage.contactDetailsBannerShouldBeVisible();
        mainPage.horizontalMenuShouldBeVisible();
        mainPage.certificationPanelShouldBeVisible();
        mainPage.advertisingBannerShouldBeVisible();
        mainPage.BlockSliderShouldBeVisible();
        String oldBlockSliderText = mainPage.activeBlockSwiper.getText();
        mainPage.blockSliderSwipe();
        String newBlockSliderText = mainPage.activeBlockSwiper.getText();
        softAssert.assertEquals(oldBlockSliderText,newBlockSliderText,"Свайп корректен");
        mainPage.sliderButtonClick();
        mainPage.horizontalMenuShouldBeVisible();
        Thread.sleep(300);
        String newCoursePanel = mainPage.coursePanel.getText();
        System.out.println(newCoursePanel);
        Assert.assertEquals(newCoursePanel, "Cypress - Learn In-depth implementation on live projects\n" +
                "Get Started\n" +
                "Appium Mobile Automation Testing for Android and IOS\n" +
                "Get Started\n" +
                "Automation Architect Selenium with 7 live projects\n" +
                "Get Started");
    }

    @Test(priority = 2)
    public void registrationTest(){
        driver.get(REGISTRATION_PAGE);
        registrationPage.registration();
        seleniumTutorialPage.profileMenuShouldBeDisplayed();
    }
    @Test(priority = 3)
    public void authorizationTest(){
        driver.get(AUTHORIZATION_PAGE);
        authorizationPage.logIn();
        seleniumTutorialPage.profileMenuShouldBeDisplayed();
    }

    @Test(priority = 4)
    public void goToAnotherPageTest() {
        driver.get(MAIN_PAGE);
        mainPage.careersButtonClick();
        String careersText = careersPage.careersText.getText();
        Assert.assertEquals(careersText,"CAREER");
    }

    @Test(priority = 5)
    public void practiceSite2AuthorizationTest(){
        driver.get(PRACTICE_SITE_2_AUTHORIZATION_PAGE);
        authorizationPracticeSite2Page.authorizationPracticeSite2();
        authorizationPracticeSite2Page.textLoginShouldBeVisible();
        String textLogIn = authorizationPracticeSite2Page.textLogIn.getText();
        String textLogOutButton = authorizationPracticeSite2Page.LogOutButton.getText();
        Assert.assertEquals(textLogOutButton, "Logout");
        Assert.assertEquals(textLogIn, "You're logged in!!", "Регистрация не прошла");
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}
