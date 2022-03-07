package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends PageBase {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 10);

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    public WebElement placeOrderBtn;
    public @FindBy(id = "name")
    WebElement nameField;
    public @FindBy(id = "country")
    WebElement countryField;
    public @FindBy(id = "city")
    WebElement cityField;
    public @FindBy(id = "card")
    WebElement cardNumberField;
    public @FindBy(id = "month")
    WebElement expMonthField;
    public @FindBy(id = "year")
    WebElement expYearField;
    public @FindBy(xpath = "//button[contains(text(),'Purchase')]")
    WebElement purchaseBtn;

    public void clickPlaceOrder() {
        placeOrderBtn.click();
    }
}