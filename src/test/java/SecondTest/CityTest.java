package SecondTest;

import Pages.ProfilePage;
import Pages.MainPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CityTest {
    public static MainPage mainPage;
    public static ProfilePage profilePage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.citilink.ru/");
    }

    @Test
    public void cityTest() throws InterruptedException {
        mainPage.setCity("Уфа");
        Thread.sleep(1000);
        Assert.assertEquals("Уфа", mainPage.getCity());
        mainPage.showForm();
        mainPage.inputLogin(("dima251200@yandex.ru"));
        mainPage.inputPassword("dimas123");
        Thread.sleep(10000);
        mainPage.clickLoginBtn();
        Thread.sleep(1000);
        mainPage.clickMyOfficeBtn();
        Thread.sleep(1000);
        Assert.assertEquals(mainPage.getCity(), profilePage.getDeliveryCity());
    }

    @AfterClass
    public static void userLogout() throws InterruptedException {
        mainPage.clickToMainPage();
        mainPage.userLogout();
        Thread.sleep(10000);
        driver.quit();
    }
}
