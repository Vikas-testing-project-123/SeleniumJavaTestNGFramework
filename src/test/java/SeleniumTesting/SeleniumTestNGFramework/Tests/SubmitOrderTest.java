package SeleniumTesting.SeleniumTestNGFramework.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumTesting.SeleniumTestNGFramework.TestComponents.BaseTest;
import SeleniumTesting.SeleniumTestNGFramework.data.DataReader;
import SeleniumTesting.SeleniumTestNGFramework.pageobjects.CartPage;
import SeleniumTesting.SeleniumTestNGFramework.pageobjects.CheckOut;
import SeleniumTesting.SeleniumTestNGFramework.pageobjects.ConfirmPage;
import SeleniumTesting.SeleniumTestNGFramework.pageobjects.OrderPage;
import SeleniumTesting.SeleniumTestNGFramework.pageobjects.ProductCatelog;

public class SubmitOrderTest extends BaseTest {

	//String producttoadd = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchase" })
	// public void submitOrder(String email, String password ,String producttoadd)
	// throws IOException, InterruptedException {
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		// Hit url and login
		ProductCatelog productcatelog = landingpage.LoginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productcatelog.getProductList();
		productcatelog.addProductToCart(input.get("product"));
		// Go to cart page

		CartPage cartPage = productcatelog.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOut checkoutPage = cartPage.goToCheckout();

		// Check if the product is added safely in the cart or not
		checkoutPage.selectCountry("india");
		ConfirmPage confirmPage = checkoutPage.submitOrder();

		// Verify the order is completed or not
		String confirmMessage = confirmPage.getConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistory(String producttoadd) {

		ProductCatelog productcatelog = landingpage.LoginApplication("vikashtest@test.com", "Test@123");
		OrderPage ordersPage = productcatelog.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(producttoadd));

	}
	
	

	@DataProvider
	public Object[][] getData() throws IOException {	

		
		DataReader datareader = new DataReader();
		List<HashMap<String, String>> data = datareader.getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//SeleniumTesting//SeleniumTestNGFramework//data//PurchaseOrder.json");
		
		return new Object[][] { {data.get(0)}, {data.get(1)} };
		
	}

}
