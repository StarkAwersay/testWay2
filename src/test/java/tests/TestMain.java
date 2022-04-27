package tests;


import chromeDriver.GetChromeDriver;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.Feature;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;


import static contsants.Constants.*;

@Epic("Тесты сайта Way2Automation")
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

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты на главной странице")
    @Story("Проверка наличия элементов, а также работы блоков слайдера и свайпера")
    @Test(priority = 1)
    public void mainPageTest() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        driver.get(MAIN_PAGE);
        mainPage.contactDetailsBannerShouldBeVisible();
        mainPage.horizontalMenuShouldBeVisible();
        mainPage.certificationPanelShouldBeVisible();
        mainPage.advertisingBannerShouldBeVisible();
        mainPage.blockSliderShouldBeVisible();
        String oldBlockSliderText = mainPage.activeBlockSwiper.getText();
        mainPage.blockSliderSwipe();
        String newBlockSliderText = mainPage.activeBlockSwiper.getText();
        softAssert.assertEquals(oldBlockSliderText, newBlockSliderText, "Свайп не корректен");
        mainPage.sliderButtonClick();
        mainPage.elementSliderShouldBeVisible();
        mainPage.horizontalMenuShouldBeVisible();
        String newCoursePanel = mainPage.coursePanel.getText();
        Assert.assertEquals(newCoursePanel, "Cypress - Learn In-depth implementation on live projects\n" + "Get Started\n" + "Appium Mobile Automation Testing for Android and IOS\n" + "Get Started\n" + "Automation Architect Selenium with 7 live projects\n" + "Get Started");
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты на авторизацию/регистрацию")
    @Story("Регистрация на сайте Way2Automation")
    @Test(priority = 2)
    public void registrationTest() {
        driver.get(REGISTRATION_PAGE);
        registrationPage.registration();
        seleniumTutorialPage.profileMenuShouldBeDisplayed();
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты на авторизацию/регистрацию")
    @Story("Авторизаця на сайте Way2Automation")
    @Test(priority = 3)
    public void authorizationTest() {
        driver.get(AUTHORIZATION_PAGE);
        authorizationPage.logIn();
        seleniumTutorialPage.profileMenuShouldBeDisplayed();
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты на главной странице")
    @Story("Переход на страницу Career")
    @Test(priority = 4)
    public void goToAnotherPageTest() {
        driver.get(MAIN_PAGE);
        mainPage.careersButtonClick();
        String careersText = careersPage.careersText.getText();
        Assert.assertEquals(careersText, "CAREER");
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты на авторизацию/регистрацию")
    @Story("Авторизация на сайте PracticeSite2")
    @Test(priority = 5)
    public void practiceSite2AuthorizationTest() {
        driver.get(PRACTICE_SITE_2_AUTHORIZATION_PAGE);
        authorizationPracticeSite2Page.authorization();
        authorizationPracticeSite2Page.textLoginShouldBeVisible();
        String textLogIn = authorizationPracticeSite2Page.textLogIn.getText();
        String textLogOutButton = authorizationPracticeSite2Page.logOutButton.getText();
        Assert.assertEquals(textLogOutButton, "Logout");
        Assert.assertEquals(textLogIn, "You're logged in!!", "Регистрация не прошла");
    }

    @DataProvider(name = "LogInDataProvider")
    public Object[][] getData() {
        Object[][] data = {{"angular", "password", "description"}, {"angularr", "password", "description"}, {"angular", "passwrd", "description"}, {"angular", "password", "de"}};
        return data;
    }

    @Test(dataProvider = "LogInDataProvider")
    public void dataProviderLogin(String lg, String pw, String des) {
        driver.get(PRACTICE_SITE_2_AUTHORIZATION_PAGE);
        authorizationPracticeSite2Page.loginInput(lg);
        authorizationPracticeSite2Page.passwordInput(pw);
        authorizationPracticeSite2Page.descriptionInput(des);
        authorizationPracticeSite2Page.logIn();
        if (lg == "angular" && pw == "password" && (des.length() >= 3)) {
            authorizationPracticeSite2Page.textLoginShouldBeVisible();
            String textLogIn = authorizationPracticeSite2Page.textLogIn.getText();
            Assert.assertEquals(textLogIn, "You're logged in!!");
        } else if (des.length() <= 2) {
            authorizationPracticeSite2Page.descrText.click();
            Assert.assertTrue(Color.fromString("#A94442").equals(Color.fromString(authorizationPracticeSite2Page.descrText.getCssValue("color"))), "Text 'fieldName' color");
            System.out.println("Миша, всё хуйня, давай по новой!!!! ");

        } else {
            authorizationPracticeSite2Page.textErrorShouldBeVisible();
            String errorText = authorizationPracticeSite2Page.errorText.getText();
            Assert.assertEquals(errorText, "Username or password is incorrect");


        }
    }

        @AfterMethod
        public void close () {
            driver.quit();
        }
    }
