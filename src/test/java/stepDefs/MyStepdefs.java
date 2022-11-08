package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.cs.Ale;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import java.util.HashMap;
import java.util.Map;

public class MyStepdefs {

    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        wait = new WebDriverWait(driver, 20);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I visit {string} site")
    public void iVisitSite(String url) {
        driver.get(url);
    }

    @Then("page title should be {string}")
    public void pageTitleShouldBe(String title) {
        Assert.assertEquals("not equal", title, driver.getTitle());
    }

    @Then("click on a product having name {string}")
    public void clickOnAProductHavingName(String productName) {
        driver.findElement(By.linkText(productName)).click();
    }

    @When("clicked on cart button having text {string}")
    public void clickedOnCartButtonHavingText(String text) {
        driver.findElement(By.linkText(text)).click();
    }

    @Given("I visit {string} site of single product")
    public void iVisitSiteOfSingleProduct(String url) {
        driver.get(url);
    }

    @When("clicked on cart link with text {string}")
    public void clickedOnCartLinkWithText(String text) {
        driver.findElement(By.linkText(text)).click();
    }

    @Then("product with name {string} should be displayed in cart table")
    public void productWithNameShouldBeDisplayedInCartTable(String name) {
        String productName = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/tr/td[2]")).getText();
        System.out.println(productName);
        Assert.assertEquals("not added", name, productName);
    }

    @And("total should be the price of product including taxes")
    public void totalShouldBeThePriceOfProductIncludingTaxes() {
        String expectedTotalPrice = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/tr/td[3]")).getText();
        String actualTotalPrice = driver.findElement(By.id("totalp")).getText();
        Assert.assertEquals("Wrong Price", expectedTotalPrice, actualTotalPrice);
    }

    @When("clicked on delete button having text {string}")
    public void clickedOnDeleteButtonHavingText(String text) {
        driver.findElement(By.linkText(text)).click();
    }

    @Then("Item should not be in cart table")
    public void itemShouldNotBeInCartTable() {
        try {
            driver.findElement(By.className("success"));
        } catch (NoSuchElementException e) {
            System.out.println("Successfully removed from cart");
        }

    }

    @Given("I clicked on product named {string}")
    public void iClickedOnProductNamed(String name) {
        driver.findElement(By.linkText(name)).click();
    }

    @Given("added product named {string} to the cart")
    public void addedProductNamedToTheCart(String name) {
        driver.get("https://www.demoblaze.com/cart.html");
    }

    @When("clicked on a product having name {string}")
    public void clickedOnAProductHavingName(String name) {
        driver.findElement(By.linkText(name)).click();
    }

    @Then("product with name {string} should added to cart")
    public void productWithNameShouldAddedToCart(String name) {

    }

    @Then("click on cart button having text {string}")
    public void clickOnCartButtonHavingText(String text) {
        driver.findElement(By.linkText(text)).click();
    }

    @And("click on place order button")
    public void clickOnPlaceOrderButton() {
        driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
    }

    @Then("purchase modal should popup")
    public void purchaseModalShouldPopup() {
        try {
            driver.findElement(By.cssSelector(".modal.fade.show"));
            System.out.println("Place order working");
        } catch (NoSuchElementException e) {
            System.out.println("Place order not working");
        }


    }

    @And("I should be able to view list of products")
    public void iShouldBeAbleToViewListOfProducts() {
        WebElement listOfProducts = driver.findElement(By.xpath("//div[@id='tbodyid']"));
        Assert.assertTrue("List of products is not displayed on home page", listOfProducts.isDisplayed());

    }


    @Then("I am able to see the product name {string}")
    public void iAmAbleToSeeTheProductName(String name) {
        String actualName = driver.findElement(By.className("name")).getText();
        Assert.assertEquals("wrong product name", name, actualName);
    }

    @And("I am able to see the product description and price")
    public void iAmAbleToSeeTheProductDescriptionAndPrice() {
        String description = driver.findElement(By.id("more-information")).getText();
        String price = driver.findElement(By.className("price-container")).getText();
        Assert.assertNotEquals("no description found", 0, description.length());
        Assert.assertNotEquals("price not found", 0, price.length());
    }

    @Then("I am able to see the product image or not")
    public void iAmAbleToSeeTheProductImageOrNot() throws IOException {
        String imagePath = driver.findElement(By.cssSelector(".item img")).getAttribute("src");
        Assert.assertNotEquals("image not visible", 0, imagePath.length());
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(imagePath);
        HttpResponse response = client.execute(request);
        Assert.assertEquals("Image is broken", 200, response.getStatusLine().getStatusCode());
    }

    @Then("click on add to cart button")
    public void clickOnAddToCartButton() throws Exception {
        driver.findElement(By.xpath("//a[normalize-space()='Add to cart']")).click();
//        wait.until(ExpectedConditions.visibilityOf())
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String expectedAlertText = "Product added";
        Assert.assertEquals("add to cart failure", expectedAlertText, alert.getText());
        alert.accept();
    }

    @Then("open cart page")
    public void openCartPage() {
        driver.get("https://www.demoblaze.com/cart.html");

    }

    @Then("check for actual price and total price")
    public void checkForActualPriceAndTotalPrice() {
        String expectedPrice = driver.findElement(By.cssSelector("tbody tr:nth-child(1) td:nth-child(3)")).getText();
        String totalPrice = driver.findElement(By.id("totalp")).getText();
        Assert.assertEquals("Item price and total price not matching", expectedPrice, totalPrice);
    }

    @And("check submitting blank form")
    public void checkSubmittingBlankForm() {
        driver.findElement(By.cssSelector("button[onclick='purchaseOrder()']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String expectedText = "Please fill out Name and Creditcard.";
        Assert.assertEquals("should not allow to submit blank form", expectedText, alert.getText());
        alert.accept();
    }

    @Then("check submitting with name only")
    public void checkSubmittingWithNameOnly() {
        driver.findElement(By.id("name")).sendKeys("John");
        driver.findElement(By.cssSelector("button[onclick='purchaseOrder()']")).click();
        String expectedText = "Please fill out Name and Creditcard.";
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals("should not allow to submit blank form", expectedText, alert.getText());
        alert.accept();
    }

    @And("check submitting with all fields")
    public void checkSubmittingWithAllFields() {
        driver.findElement(By.id("name")).sendKeys("John");
        driver.findElement(By.id("country")).sendKeys("india");
        driver.findElement(By.id("city")).sendKeys("mumbai");
        driver.findElement(By.id("card")).sendKeys("1234567801");
        driver.findElement(By.id("month")).sendKeys("12");
        driver.findElement(By.id("year")).sendKeys("24");
        driver.findElement(By.cssSelector("button[onclick='purchaseOrder()']")).click();
    }

    @And("I fill name as {string}")
    public void iFillNameAs(String name) {
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @And("I fill country as {string}")
    public void iFillCountryAs(String country) {
        driver.findElement(By.id("country")).sendKeys(country);
    }

    @And("I fill card as {string}")
    public void iFillCardAs(String cardNumber) {
        driver.findElement(By.id("card")).sendKeys(cardNumber);
    }

    @And("I fill city as {string}")
    public void iFillCityAs(String city) {
        driver.findElement(By.id("city")).sendKeys(city);
    }

    @And("I fill month as {string}")
    public void iFillMonthAs(String month) {
        driver.findElement(By.id("month")).sendKeys(month);
    }

    @And("I fill year as {string}")
    public void iFillYearAs(String year) {
        driver.findElement(By.id("year")).sendKeys(year);
    }

    @Then("I click submit")
    public void iClickSubmit() {
        driver.findElement(By.cssSelector("button[onclick='purchaseOrder()']")).click();
    }

    @Then("I should have order details name as {string}, country as {string}, card number as {string}, city as  {string}, month as {string}, year as  {string}")
    public void iShouldHaveOrderDetailsNameAsCountryAsCardNumberAsCityAsMonthAsYearAs(String name, String country, String cardNumber, String city, String month, String year) {
        String orderDetails = driver.findElement(By.xpath("//p[@class='lead text-muted ']")).getText();
        System.out.println(orderDetails);
        String[] splittedDetails = orderDetails.split("\n");
        HashMap<String,String> orderDetailsMap = new HashMap<String,String>();
        for (int i = 0; i < splittedDetails.length; i++) {
            String key = splittedDetails[i].substring(0, splittedDetails[i].indexOf(":"));
            String value = splittedDetails[i].substring(splittedDetails[i].indexOf(": ")+2);
            orderDetailsMap.put(key,value);
        }
        System.out.println(orderDetailsMap);
        Assert.assertEquals("Card Number in order details not matching", cardNumber,orderDetailsMap.get("Card Number"));
        Assert.assertEquals("Name in order details not matching", name,orderDetailsMap.get("Name"));
//        Assert.assertEquals("order details not matching", ,orderDetailsMap.get("Name"));

    }

    @And("add another product to the cart named {string}")
    public void addAnotherProductToTheCart(String productName) {
        driver.get("https://www.demoblaze.com/index.html");
        driver.findElement(By.linkText(productName)).click();
        driver.findElement(By.xpath("//a[normalize-space()='Add to cart']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String expectedAlertText = "Product added";
        Assert.assertEquals("add to cart failure", expectedAlertText, alert.getText());
        alert.accept();
    }

    @Then("check the total again")
    public void checkTheTotalAgain() {
        driver.findElement(By.linkText("Cart")).click();
        int itemPrice1 = Integer.parseInt(driver.findElement(By.cssSelector("tbody tr:nth-child(1) td:nth-child(3)")).getText());
        int itemPrice2 =  Integer.parseInt(driver.findElement(By.cssSelector("tbody tr:nth-child(2) td:nth-child(3)")).getText());
        int expectedPrice = itemPrice1 + itemPrice2;
        int actualPrice = Integer.parseInt(driver.findElement(By.id("totalp")).getText());
        Assert.assertEquals("total price not matching",expectedPrice,actualPrice);
    }

    @Then("check for price whether it is {int} or not")
    public void checkForPriceWhetherItIsOrNot(int expectedPrice) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("totalp"))));
        int actualPrice = Integer.parseInt(driver.findElement(By.id("totalp")).getText());
        Assert.assertEquals("total price not matching",expectedPrice,actualPrice);
    }

    @When("click on a category laptop category {string}")
    public void clickOnACategoryLaptopCategory(String name) {
        driver.findElement(By.cssSelector("body > div:nth-child(6) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(3)")).click();
    }

    @And("click on a category monitors category {string}")
    public void clickOnACategoryMonitorsCategory(String name) {
        driver.findElement(By.cssSelector("body > div:nth-child(6) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(4)")).click();
    }

    @And("click on a category phone category {string}")
    public void clickOnACategoryPhoneCategory(String name) {
        driver.findElement(By.cssSelector("body > div:nth-child(6) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(2)"));
    }
}
