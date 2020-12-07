package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.List;

public class CatalogPage {
    public WebDriver driver;
    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(partialLinkText = "Зубные щетки и аксессуары")
    private WebElement toothbrushesAndAccessors;

    @FindBy(partialLinkText = "Зубные щетки")
    private WebElement toothbrushes;

    public void clickOnToothBrushCatalog() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Зубные щетки и аксессуары")));
        toothbrushesAndAccessors.click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Зубные щетки")));
        toothbrushes.click();
    }

    @FindBy(className = "min")
    private WebElement inputMin;

    public void setMinPrice(Integer minPrice) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("min")));
        inputMin.sendKeys("\b\b\b\b\b\b\b\b");
        inputMin.sendKeys(minPrice.toString());
    }

    @FindBy(className = "max")
    private WebElement inputMax;

    public void setMaxPrice(Integer maxPrice) throws InterruptedException {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("max")));
        inputMax.sendKeys("\b\b\b\b\b\b\b\b");
        inputMax.sendKeys(maxPrice.toString());
        Thread.sleep(2000);
    }

    public List<WebElement> getProductList() {
        List<WebElement> products = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("js--subcategory-product-item")));
        return products;
    }

    @FindBy(className = "popup-basket__close-btn")
    private WebElement popupCloseBtn;

    public void addToCart(WebElement product) {
        product.findElement(By.className("add_to_cart")).click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-basket__close-btn")));
        popupCloseBtn.click();
    }

    @FindBy(className = "cart")
    private WebElement cartBtn;

    public void clickOnCart() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("cart")));
        cartBtn.click();
    }

    @FindBy(id = "basket_storage")
    private WebElement storage;

    public String getCartText() {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("basket_storage"))).getText();
    }
}