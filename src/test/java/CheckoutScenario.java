import Pages.CartPage;
import Pages.HomePage;
import Pages.PDPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutScenario extends TestBase {
    HomePage homeObject;
    PDPage productPageObject;
    CartPage cartObject;

    Faker faker = new Faker();
    String name = faker.name().name();
    String country = faker.country().countryCode2();
    String city = faker.country().capital();
    String cardNumber = faker.number().digits(16);
    int month = faker.number().numberBetween(01, 12);
    int year = faker.number().numberBetween(22, 40);

    @Test(priority = 1)
    public void Login() throws InterruptedException {
        homeObject = new HomePage(driver);
        homeObject.doLogin();
        homeObject.goToFirstPDP();
    }

    @Test(dependsOnMethods = "Login")
    public void addProductToCart() throws InterruptedException {
        productPageObject = new PDPage(driver);
        productPageObject.addProductToCart();
        productPageObject.goToCart();
    }

    @Test(dependsOnMethods = "addProductToCart")
    public void submitOrderWithEmptyFields() {
        cartObject = new CartPage(driver);
        cartObject.clickPlaceOrder();
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(cartObject.nameField));
        scroll.executeScript("arguments[0].scrollIntoView(true);", cartObject.purchaseBtn);
        cartObject.purchaseBtn.click();
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Please fill out Name and Creditcard."));
        driver.switchTo().alert().accept();
    }

    @Test(dependsOnMethods = "submitOrderWithEmptyFields")
    public void resubmitOrderWithValid() {
        cartObject = new CartPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("arguments[0].scrollIntoView(true);", cartObject.nameField);
        cartObject.nameField.sendKeys(name);
        cartObject.countryField.sendKeys(country);
        cartObject.cityField.sendKeys(city);
        cartObject.cardNumberField.sendKeys(cardNumber);
        cartObject.expMonthField.sendKeys(String.valueOf(month));
        cartObject.expYearField.sendKeys(String.valueOf(year));
        JavascriptExecutor scroll2 = (JavascriptExecutor) driver;
        scroll2.executeScript("arguments[0].scrollIntoView(true);", cartObject.purchaseBtn);
        cartObject.purchaseBtn.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]")).getText().contains("Thank you for your purchase!"));
    }
}
