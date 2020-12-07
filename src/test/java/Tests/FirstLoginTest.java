package Tests;

import Common.CitilinkTestWatcher;
import Common.TestSettings;
import Pages.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FirstLoginTest {
    public static MainPage mainPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", TestSettings.webDriverPath);
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

    @Rule
    public CitilinkTestWatcher watcher = new CitilinkTestWatcher(driver);
    @Epic("Тестирование сайта https://www.citilink.ru/")
    @Story(value = "Тест авторизации пользователя")
    @Test
    public void loginTest() throws InterruptedException {
        mainPage.showForm();
        mainPage.inputLogin((TestSettings.login));
        mainPage.inputPassword(TestSettings.password);
        Thread.sleep(10000);
        mainPage.clickLoginBtn();
        String user = mainPage.getUserName();
        Assert.assertEquals("Дмитрий", user);
    }

    @AfterClass
    public static void quit() {
        mainPage.clickToMainPage();
        mainPage.userLogout();
        driver.quit();
    }
}
