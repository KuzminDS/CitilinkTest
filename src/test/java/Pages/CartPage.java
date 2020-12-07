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

public class CartPage {
    public WebDriver driver;
    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(text(), \"Оформить заказ\")]")
    private WebElement makeAnOrder;

    public void clickOnCheckout() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), \"Оформить заказ\")]")));
        makeAnOrder.click();
    }

    @FindBy(xpath = "//*[contains(text(), \"Далее\")]")
    private WebElement nextBtn;

    @FindBy(className = "for_delivery_type_0")
    private WebElement forDeliveryBtn;

    @FindBy(xpath = "//*[@id=\"delivery_save\"]")
    private WebElement saveDeliveryBtn;

    public void selectDelivery() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), \"Далее\")]")));
        nextBtn.click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("for_delivery_type_0")));
        forDeliveryBtn.click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"delivery_save\"]")));
        saveDeliveryBtn.click();
    }

    @FindBy(className = "js--order-amount__amount-num")
    private WebElement orderAmount;

    public int getFullPrice() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("js--order-amount__amount-num")));
        return Integer.parseInt(orderAmount.getText());
    }

    @FindBy(className = "js--order-amount-without-discount__amount-num")
    private WebElement orderWithoutDiscountNum;

    public int getProductPrice() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("js--order-amount-without-discount__amount-num")));
        return Integer.parseInt(orderWithoutDiscountNum.getText());
    }

    @FindBy(xpath = "//tr[./td[contains(text(), \"Доставка\")]]")
    private WebElement deliveryPriceBlock;

    public int getDeliveryPrice() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[./td[contains(text(), \"Доставка\")]]")));
        String price = deliveryPriceBlock.findElement(By.className("num")).getText();
        return Integer.parseInt(price);
    }

    @FindBy(className = "product_amount_control")
    private WebElement productCountBlock;

    public void setProductCount(Integer count) throws InterruptedException {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("product_amount_control")));
        productCountBlock.sendKeys("\b\b\b\b\b\b");
        productCountBlock.sendKeys(count.toString());
        Thread.sleep(2000);
    }

    @FindBy(className = "remove_all")
    private WebElement removeAllBtn;

    public void removeAllProducts() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("remove_all")));
        removeAllBtn.click();
    }
}