package tests.ui;


import helpers.JavaScriptExecutorsHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.Feature;
import io.qameta.allure.SeverityLevel;
import listeners.FailTestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.RegistrationPage;
import pages.SeleniumTutorialPage;
import pages.AuthorizationPracticeSite2Page;
import pages.CareersPage;
import pages.YandexMainPage;


import static constants.Constants.*;

@Listeners(FailTestListener.class)
@Epic("Тесты сайта Way2Automation")
public class TestMain extends BasicTestClass {
    private MainPage mainPage;
    private AuthorizationPage authorizationPage;
    private RegistrationPage registrationPage;
    private SeleniumTutorialPage seleniumTutorialPage;
    private AuthorizationPracticeSite2Page authorizationPracticeSite2Page;
    private CareersPage careersPage;
    private YandexMainPage yandexMainPage;

    @BeforeMethod
    public void beforeMethod() {
        mainPage = new MainPage(driver);
        authorizationPage = new AuthorizationPage(driver);
        registrationPage = new RegistrationPage(driver);
        seleniumTutorialPage = new SeleniumTutorialPage(driver);
        authorizationPracticeSite2Page = new AuthorizationPracticeSite2Page(driver);
        careersPage = new CareersPage(driver);
        yandexMainPage = new YandexMainPage(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты на главной странице")
    @Story("Проверка наличия элементов, а также работы блоков слайдера и свайпера")
    @Test(priority = 1)
    public void mainPageTest() {
        SoftAssert softAssert = new SoftAssert();
        driver.get(MAIN_PAGE);
        mainPage.contactDetailsBannerShouldBeVisible();
        mainPage.horizontalMenuShouldBeVisible();
        mainPage.certificationPanelShouldBeVisible();
        mainPage.advertisingBannerShouldBeVisible();
        mainPage.blockSliderShouldBeVisible();
        String oldBlockSliderText = mainPage.getBlockSliderText();
        mainPage.blockSliderSwipe();
        String newBlockSliderText = mainPage.getBlockSliderText();
        softAssert.assertEquals(oldBlockSliderText, newBlockSliderText, "Свайп не корректен");
        mainPage.sliderButtonClick();
        mainPage.elementSliderShouldBeVisible();
        mainPage.horizontalMenuShouldBeVisible();
        String newCoursePanel = mainPage.getCoursePanelText();
        Assert.assertEquals(newCoursePanel, "Cypress - Learn In-depth implementation on live projects\n" + "Get Started\n" + "Appium Mobile Automation Testing for Android and IOS\n" + "Get Started\n" + "Automation Architect Selenium with 7 live projects\n" + "Get Started");
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты на авторизацию/регистрацию")
    @Story("Регистрация на сайте Way2Automation")
    @Test(priority = 2)
    public void registrationTest() {
        driver.get(REGISTRATION_PAGE);
        registrationPage.registration(FULL_NAME, EMAIL, PASSWORD);
        seleniumTutorialPage.profileMenuShouldBeDisplayed();
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты на авторизацию/регистрацию")
    @Story("Авторизаця на сайте Way2Automation")
    @Test(priority = 3)
    public void authorizationTest() {
        driver.get(AUTHORIZATION_PAGE);
        authorizationPage.logIn(EMAIL, PASSWORD);
        seleniumTutorialPage.profileMenuShouldBeDisplayed();
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты на главной странице")
    @Story("Переход на страницу Career")
    @Test(priority = 4)
    public void goToAnotherPageTest() {
        driver.get(MAIN_PAGE);
        mainPage.careersButtonClick();
        String careersText = careersPage.getCareersText();
        Assert.assertEquals(careersText, "CAREER");
    }

    @DataProvider(name = "LogInDataProvider")
    public Object[][] getData() {
        Object[][] data = {{"angular", "password", "description"}, {"angularr", "password", "description"}, {"angular", "passwrd", "description"}, {"angular", "password", "de"}};
        return data;
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты на авторизацию/регистрацию")
    @Story("Авторизация на сайте PracticeSite2")
    @Test(dataProvider = "LogInDataProvider")
    public void dataProviderLogin(String login, String password, String description) {
        driver.get(PRACTICE_SITE_2_AUTHORIZATION_PAGE);
        authorizationPracticeSite2Page.loginInput(login);
        authorizationPracticeSite2Page.passwordInput(password);
        authorizationPracticeSite2Page.descriptionInput(description);
        authorizationPracticeSite2Page.logIn();
        if (login == "angular" && password == "password" && (description.length() >= 3)) {
            authorizationPracticeSite2Page.textLoginShouldBeVisible();
            String textLogIn = authorizationPracticeSite2Page.getTextLogin();
            Assert.assertEquals(textLogIn, "You're logged in!!");
        } else if (description.length() <= 2) {
            authorizationPracticeSite2Page.descriptionTextClick();
            Assert.assertTrue(Color.fromString("#A94442").equals(Color.fromString(authorizationPracticeSite2Page.getDescriptionTextColor())));
        } else {
            authorizationPracticeSite2Page.textErrorShouldBeVisible();
            String errorText = authorizationPracticeSite2Page.getErrorText();
            Assert.assertEquals(errorText, "Username or password is incorrect");
        }
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты на JavaScriptExecutor")
    @Story("Смена фокуса")
    @Test
    public void searchBarTest() {
        driver.get("https://yandex.ru/");
        yandexMainPage.clickOnSearchBar();
        JavaScriptExecutorsHelper.removeFocus(driver, yandexMainPage.searchBar());
        Assert.assertFalse(driver.switchTo().activeElement().equals(yandexMainPage.searchBar()), "Фокус не изменился");
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тесты на JavaScriptExecutor")
    @Story("Проверка скроллинга страницы")
    @Test
    public void scrollTest() {
        driver.get("https://yandex.ru/");
        JavaScriptExecutorsHelper.scroll(driver, 150);
        Assert.assertEquals(JavaScriptExecutorsHelper.getScrollInformation(driver), 0, "Страница проскроллена");
    }

    @AfterMethod
    public void tearDownDriver() {
        driver.quit();
    }
}
