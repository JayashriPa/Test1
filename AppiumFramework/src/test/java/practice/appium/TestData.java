package practice.appium;

import org.testng.annotations.DataProvider;


//parameterizing test data from external sources using data provider.
public class TestData {
   
	@DataProvider(name="InputData")
	public Object[] getDataForEditField()
	{
		Object[] obj=new Object[]
		{
				("Jayashri")
		};
		return obj;
	}
}

