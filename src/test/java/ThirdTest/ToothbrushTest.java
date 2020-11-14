package ThirdTest;

import Pages.CartPage;
import Pages.MainPage;
import Pages.CatalogPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ToothbrushTest {
    public static MainPage mainPage;
    public static CatalogPage catalogPage;
    public static CartPage cartPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        catalogPage = new CatalogPage(driver);
        cartPage = new CartPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.citilink.ru/");
    }

    @Test
    public void toothbrushTest() throws InterruptedException {
        mainPage.showForm();
        mainPage.inputLogin(("dima251200@yandex.ru"));
        mainPage.inputPassword("dimas123");
        Thread.sleep(10000);
        mainPage.clickLoginBtn();
        mainPage.clickOnBeautyAndHealthCatalog();
        catalogPage.clickOnToothBrushCatalog();
        Thread.sleep(1000);
        catalogPage.setMinPrice(999);
        catalogPage.setMaxPrice(1999);
        Thread.sleep(2000);
        List<WebElement> products = catalogPage.getProductList();
        Assert.assertFalse(products.isEmpty());
        catalogPage.addToCart(products.get(products.size() - 2));
        catalogPage.clickOnCart();
        Thread.sleep(1000);
        cartPage.clickOnCheckout();
        cartPage.selectDelivery();
        int actualFullPrice = cartPage.getProductPrice() + cartPage.getDeliveryPrice();
        Assert.assertEquals(cartPage.getFullPrice(), actualFullPrice);
        catalogPage.clickOnCart();
        cartPage.setProductCount(2);
        Thread.sleep(2000);
        cartPage.clickOnCheckout();
        cartPage.selectDelivery();
        Assert.assertTrue(cartPage.getFullPrice() > 2999);
        actualFullPrice = cartPage.getProductPrice() + cartPage.getDeliveryPrice();
        Assert.assertEquals(cartPage.getFullPrice(), actualFullPrice);
        userLogout();
    }

    public static void userLogout() throws InterruptedException {
        catalogPage.clickOnCart();
        cartPage.removeAllProducts();
        mainPage.clickToMainPage();
        mainPage.userLogout();
        Thread.sleep(10000);
    }

    @AfterClass
    public static void quit() {
        driver.quit();
    }
}
