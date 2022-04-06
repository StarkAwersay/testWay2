package tests;


import chromeDriver.GetChromeDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;


import java.io.IOException;

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
        driver.get(MAINPAGE);
        mainPage.checkingElements();
        Thread.sleep(2000);
        String oldBlockSliderText = mainPage.blockSlider.getText();
        mainPage.blockSliderSwipe();
        String newBlockSliderText = mainPage.blockSlider.getText();
        softAssert.assertEquals(oldBlockSliderText,newBlockSliderText,"Свайп корректен");
        mainPage.sliderButtonClick();
        String newCoursePanel = mainPage.coursePanel.getText();
        Assert.assertEquals(newCoursePanel, "Appium Mobile Automation Testing for Android and IOS\n" +
                "Get Started\n" +
                "Automation Architect Selenium with 7 live projects\n" +
                "Get Started\n" +
                "Protractor End to End testing for Angular JS App\n" +
                "Get Started");
    }

    @Test(priority = 2)
    public void registrationTest() throws InterruptedException {
        driver.get(REGISTRATIONPAGE);
        registrationPage.registration();
        Thread.sleep(500);
        seleniumTutorialPage.checkProfileMenuIsDisplayed();
    }
    @Test(priority = 3)
    public void authorizationTest() throws InterruptedException {
        driver.get(AUTHORIZATIONPAGE);
        authorizationPage.logIn();
        Thread.sleep(500);
        seleniumTutorialPage.checkProfileMenuIsDisplayed();
    }

    @Test(priority = 4)
    public void goToAnotherPageTest() {
        driver.get(MAINPAGE);
        mainPage.careersButtonClick();
        String careersText = careersPage.careersText.getText();
        Assert.assertEquals(careersText,"CAREER");
    }

    @Test(priority = 5)
    public void practiceSite2Authorization() throws InterruptedException, IOException {
        driver.get(PRACTICESITE2AUTHORIZATIONPAGE);
        authorizationPracticeSite2Page.authorizationPracticeSite2();
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
