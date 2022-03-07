package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PDPage extends PageBase {
    public PDPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 10);

    @FindBy(linkText = "Add to cart")
    WebElement addToCartBtn;
    @FindBy(linkText = "Cart")
    WebElement cartBtn;

    public void addProductToCart() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        addToCartBtn.click();
        Thread.sleep(1500);
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Product added."));
        driver.switchTo().alert().accept();
    }

    public void goToCart() {
        cartBtn.click();
    }
}
