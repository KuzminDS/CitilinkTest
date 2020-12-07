package Tests;

import Common.CitilinkTestWatcher;
import Common.TestSettings;
import Pages.CartPage;
import Pages.MainPage;
import Pages.CatalogPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ThirdCheckToothbrushPriceTest {
    public static MainPage mainPage;
    public static CatalogPage catalogPage;
    public static CartPage cartPage;
    public static WebDriver driver;


    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", TestSettings.webDriverPath);
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        catalogPage = new CatalogPage(driver);
        cartPage = new CartPage(driver);
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
    @Story(value = "Тест проверки работы пользователя с товарами")
    @Test
    public void checkToothbrushPriceTest() throws InterruptedException {
        mainPage.showForm();
        mainPage.inputLogin((TestSettings.login));
        mainPage.inputPassword(TestSettings.password);
        Thread.sleep(10000);
        mainPage.clickLoginBtn();
        if (catalogPage.getCartText().contains("товар")) {
            catalogPage.clickOnCart();
            cartPage.removeAllProducts();
            mainPage.clickToMainPage();
        }
        mainPage.clickOnBeautyAndHealthCatalog();
        catalogPage.clickOnToothBrushCatalog();
        catalogPage.setMinPrice(999);
        catalogPage.setMaxPrice(1999);
        List<WebElement> products = catalogPage.getProductList();
        Assert.assertFalse(products.isEmpty());
        catalogPage.addToCart(products.get(products.size() - 2));
        catalogPage.clickOnCart();
        cartPage.clickOnCheckout();
        cartPage.selectDelivery();
        int actualFullPrice = cartPage.getProductPrice() + cartPage.getDeliveryPrice();
        int count;
        if(cartPage.getProductPrice() * 2 + cartPage.getDeliveryPrice() > 2999) {
            count = 2;
        }
        else {
            count = 3;
        }
        Assert.assertEquals(cartPage.getFullPrice(), actualFullPrice);
        catalogPage.clickOnCart();
        cartPage.setProductCount(count);
        cartPage.clickOnCheckout();
        cartPage.selectDelivery();
        Assert.assertTrue(cartPage.getFullPrice() > 2999);
        actualFullPrice = cartPage.getProductPrice() + cartPage.getDeliveryPrice();
        Assert.assertEquals(cartPage.getFullPrice(), actualFullPrice);
        catalogPage.clickOnCart();
        cartPage.removeAllProducts();
    }

    @AfterClass
    public static void quit() {
        mainPage.clickToMainPage();
        mainPage.userLogout();
        driver.quit();
    }
}