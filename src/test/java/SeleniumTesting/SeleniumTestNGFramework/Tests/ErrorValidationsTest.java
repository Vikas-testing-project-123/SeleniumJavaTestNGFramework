package SeleniumTesting.SeleniumTestNGFramework.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import SeleniumTesting.SeleniumTestNGFramework.TestComponents.BaseTest;
import SeleniumTesting.SeleniumTestNGFramework.pageobjects.CartPage;
import SeleniumTesting.SeleniumTestNGFramework.pageobjects.ProductCatelog;

public class ErrorValidationsTest extends BaseTest {
	// for iretrier i am getting the issue so removing it to run syntax: (@Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class))
	@Test(groups= {"ErrorHandling"})
	public void ErrorLogin() throws IOException, InterruptedException {

		
		landingpage.LoginApplication("vikashtest123@test.com", "Test@123");
//		Thread.sleep(2000);
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMsg());
//		System.out.println(landingpage.getErrorMsg());	
	
	}
	
	@Test
	public void ErrorProductAdd() throws IOException, InterruptedException 
	{

		
		String producttoadd = "ZARA COAT 3";

		// Hit url and login
		ProductCatelog productcatelog = landingpage.LoginApplication("vikashtest@test.com", "Test@123");

		List<WebElement> products = productcatelog.getProductList();
		productcatelog.addProductToCart(producttoadd);
		// Go to cart page

		CartPage cartPage = productcatelog.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(producttoadd);
		Assert.assertTrue(match);

	}


}
