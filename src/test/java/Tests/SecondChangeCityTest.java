package Tests;

import Common.CitilinkTestWatcher;
import Common.TestSettings;
import Pages.ProfilePage;
import Pages.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class SecondChangeCityTest {
    public static MainPage mainPage;
    public static ProfilePage profilePage;
    public static WebDriver driver;
    private String city;

    public SecondChangeCityTest(String city) {
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
        System.setProperty("webdriver.chrome.driver", TestSettings.webDriverPath);
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

    @Rule
    public CitilinkTestWatcher watcher = new CitilinkTestWatcher(driver);
    @Epic("Тестирование сайта https://www.citilink.ru/")
    @Story(value = "Тест проверки изменения города при авторизации")
    @Test
    public void changeCityTest() throws InterruptedException {
        mainPage.setCity(city);
        Assert.assertEquals(city, mainPage.getCity());
        mainPage.showForm();
        mainPage.inputLogin((TestSettings.login));
        mainPage.inputPassword(TestSettings.password);
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
