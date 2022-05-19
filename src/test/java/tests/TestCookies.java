package tests;

import chromeDriver.GetChromeDriver;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SqlMainPage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileWriter;

public class TestCookies {
    private WebDriver driver;
    private SqlMainPage sqlMainPage;
    @BeforeMethod
    public void BeforeTest(){
        driver= GetChromeDriver.getChromeDriver();
        driver.manage().window().maximize();
        sqlMainPage = new SqlMainPage(driver);
    }
    @Test
    public void testCookie() throws IOException {
        driver.get("https://www.sql-ex.ru/");
        String Cookie;
        File file2 = new File("C:\\Users\\4fran\\github.com\\StarkAwersay\\Nado\\testWay2\\cookies");
        try (BufferedReader Buffreader = new BufferedReader(new FileReader(file2))) {
            Cookie = Buffreader.readLine();
        }
        if (Cookie != null) {
            org.openqa.selenium.Cookie cookie = new Cookie("PHPSESSID", Cookie);
            driver.manage().addCookie(cookie);
            driver.navigate().refresh();
        } else {
            sqlMainPage.authorization();
            File file1 = new File("C:\\Users\\4fran\\github.com\\StarkAwersay\\Nado\\testWay2\\cookies");
            try (FileWriter fileWrite = new FileWriter(file1)) {
                file1.delete();
                file1.createNewFile();
                fileWrite.write(driver.manage().getCookieNamed("PHPSESSID").getValue());
            }
        }
        Assert.assertEquals(sqlMainPage.getProfileName(),"StarklAwersa");

    }


    @AfterMethod
    public void tearDownDriver() {
        driver.quit();
    }
}

