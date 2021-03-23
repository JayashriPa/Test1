package pageFactory;

import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {

	public CheckOutPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	
	//driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"))
	//findelements is a list of web elements
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	public List<WebElement> productList;
	
	//driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	public List<WebElement> totalAmount;
	
}
