package practice.appium;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageFactory.CheckOutPage;
import pageFactory.FormPage;


public class ECommerceTestCase2 extends BaseClass{

	
	@Test(dataProvider="InputData",dataProviderClass=TestData.class)
	public void totalValidation(String input) throws IOException
	{
		
		service=StartServer();
		AndroidDriver<AndroidElement> driver=Capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		FormPage formPage=new FormPage(driver);
		
		formPage.nameField.sendKeys(input);
		driver.hideKeyboard();
		formPage.femaleOption.click();
		formPage.getCountrySelection().click();
		
		
		driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Aruba\"))");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByXPath("//*[@text='Aruba']").click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Adding 2 items in the cart
				driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
				driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();

				driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();


				WebDriverWait wait = new WebDriverWait(driver,60);
				int count=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();


				double sum=0;

				CheckOutPage checkoutpage=new CheckOutPage(driver);
				for(int i=0;i<count;i++)

				{


					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					String amount1= checkoutpage.productList.get(i).getText();

					double amount=getAmount(amount1);
					sum=sum+amount;//280.97+116.97

				}

				System.out.println(sum+"sum of products");



				String total=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
				total= total.substring(1);// It excludes special character in the amount for example $120.
				double totalValue=Double.parseDouble(total);
				System.out.println(totalValue+"Total value of products");
				Assert.assertEquals(sum, totalValue,0.01);
				
				service.stop();
				//Mobile Gesture tap on chckbox and long press on terms and conditions icon
				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				WebElement checkbox=driver.findElementByClassName("android.widget.CheckBox");
				TouchAction t=new TouchAction(driver);
				t.tap(tapOptions().withElement(element(checkbox))).perform();
				WebElement tc=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
				t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
				driver.findElement(By.id("android:id/button1")).click();
				driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
}

	@BeforeTest
	public void KillAllNodes() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
			public static double getAmount(String value)

			{

				value= value.substring(1);

				double amount2value=Double.parseDouble(value);

				return amount2value;

	}
}





