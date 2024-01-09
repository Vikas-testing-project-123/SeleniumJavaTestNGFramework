package SeleniumTesting.SeleniumTestNGFramework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumTestNGFramework.AbstractComponent.AbstractComponent;

public class Landingpag extends AbstractComponent{
	
	WebDriver driver;
	public Landingpag(WebDriver driver) {
		
		super (driver);
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='toast-container']")
	WebElement errorMsg;
	
	public ProductCatelog LoginApplication(String username, String password) {
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatelog productcatelog = new ProductCatelog(driver);
		return productcatelog;
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	public WebElement getErrorMsg() {
		waitForWebElementToAppear(errorMsg);
		errorMsg.getText();
		return errorMsg;
	}
	
	
	
}
