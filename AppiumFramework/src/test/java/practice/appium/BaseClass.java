package practice.appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;





import org.openqa.selenium.remote.DesiredCapabilities;


public class BaseClass {
	 
	public static AndroidDriver<AndroidElement> driver=null;
    public static AppiumDriverLocalService service;
	public static AppiumDriverLocalService StartServer()
	{
		service=AppiumDriverLocalService.buildDefaultService();
		service.start();
		return service;
	}
	
	
	
	public static AndroidDriver<AndroidElement> Capabilities(String appName) throws IOException
	{
		
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+("\\src\\test\\java\\global.properties"));
		Properties prop=new Properties();
		prop.load(fis);
		
		
		
		File appDir=new File("src");
		File app=new File(appDir,(String) prop.get(appName));
		String device=(String) prop.get("device");
		//String device=System.getProperty("deviceName");
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "device");
		cap.setCapability("automatorName", "UiAutomator1");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 15);
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "com.androidsample.generalstore.*");
		
		driver=new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		}
	
	
}
