package SecondTest;

import Pages.ProfilePage;
import Pages.MainPage;
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
    private final String expectedCity;
    private final String realCity;

    public CityTest(String expectedCity, String realCity) {
        this.realCity = realCity;
        this.expectedCity = expectedCity;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parameters = new Object[][]{
                {"Анапа", "Анапа"},
                {"Екатеринбург", "Екатеринбург"},
                {"Волгоград", "Волгоград"},
                {"Сочи", "Сочи"},
                {"Краснодар", "Краснодар"}
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
    }

    @Test
    public void cityTest() throws InterruptedException {
        mainPage.setCity("Уфа");
        Thread.sleep(2000);
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
        userLogout();
    }

    @Test
    public void changeCityTest() throws InterruptedException {
        mainPage.setCity(realCity);
        Thread.sleep(2000);
        Assert.assertEquals(expectedCity, mainPage.getCity());
    }

    public static void userLogout() throws InterruptedException {
        mainPage.clickToMainPage();
        mainPage.userLogout();
        Thread.sleep(10000);
    }

    @AfterClass
    public static void quit() {
        driver.quit();
    }
}
