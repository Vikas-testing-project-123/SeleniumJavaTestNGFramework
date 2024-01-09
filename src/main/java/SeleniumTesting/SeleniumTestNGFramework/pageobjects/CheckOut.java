package SeleniumTesting.SeleniumTestNGFramework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumTestNGFramework.AbstractComponent.AbstractComponent;

public class CheckOut extends AbstractComponent {

	WebDriver driver;

	public CheckOut(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action_submit")
	WebElement submit;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]\")")
	WebElement selectCountry;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, "India").build().perform();

		waitForElementToAppear(results);
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		selectCountry.click();
	}

	public ConfirmPage submitOrder() {
		submit.click();
		ConfirmPage confirmPage = new ConfirmPage(driver);
		return confirmPage;
	}

}
