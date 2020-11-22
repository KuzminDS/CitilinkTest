package FirstTest;

import Pages.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    public static MainPage mainPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.citilink.ru/");
        try {
            mainPage.goToOldPage();
        }
        catch (Exception exception) {
        }
    }

    @Epic("Тестирование сайта https://www.citilink.ru/")
    @Story(value = "Тест авторизации пользователя")
    @Test
    public void loginTest() throws InterruptedException {
        mainPage.showForm();
        mainPage.inputLogin(("dima251200@yandex.ru"));
        mainPage.inputPassword("dimas123");
        Thread.sleep(10000);
        mainPage.clickLoginBtn();
        String user = mainPage.getUserName();
        Assert.assertEquals("Дмитрий", user);
        mainPage.clickToMainPage();
        mainPage.userLogout();
    }

    @AfterClass
    public static void quit() {
        driver.quit();
    }
}
