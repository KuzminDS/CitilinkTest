package SecondTest;

import Pages.ProfilePage;
import Pages.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class CityTest {
    public static MainPage mainPage;
    public static ProfilePage profilePage;
    public static WebDriver driver;
    private String city;

    public CityTest(String city) {
        this.city = city;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parameters = new Object[][]{
                {"Анапа"},
                {"Екатеринбург"}
        };
        return Arrays.asList(parameters);
    }

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
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
    @Story(value = "Тест проверки изменения города при авторизации")
    @Test
    public void cityTest() throws InterruptedException {
        mainPage.setCity(city);
        Assert.assertEquals(city, mainPage.getCity());
        mainPage.showForm();
        mainPage.inputLogin(("dima251200@yandex.ru"));
        mainPage.inputPassword("dimas123");
        Thread.sleep(10000);
        mainPage.clickLoginBtn();
        mainPage.clickMyOfficeBtn();
        Assert.assertEquals(mainPage.getCity(), profilePage.getDeliveryCity());
        mainPage.clickToMainPage();
        mainPage.userLogout();
    }

    @AfterClass
    public static void quit() {
        driver.quit();
    }
}
