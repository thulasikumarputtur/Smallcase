package com.Rest.Sample;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.smallcase.FlipkartPage;
import com.utility.common.Smallcase_EnvSetup;

public class Flipkart extends Smallcase_EnvSetup{
	@BeforeTest
	public void scriptstarts()
		{
		System.out.println("Script started");
		}
	
	@Test
	public void flipksrtPageValidation() throws InterruptedException, IOException
	{
		sce=new Smallcase_EnvSetup();
		sce.Browser_Setup("flipkarturl");
		fp=new FlipkartPage(wb);
		WebDriverWait wait = new WebDriverWait(wb, 10);
		fp.loginToFlipkart(prop.getProperty("flipkartusername"),prop.getProperty("flipkartpassword"));
		fp.searchItem(wb);
		Thread.sleep(3000);
		scrnsht.ToTakeScreenshot("Flipkart Validation","Total Page","1");
		TotalCost=(fp.Flipkart_total.getText().replace("+char(63)+","")).replace(",", "");
		float totalcostinflipkart=Float.parseFloat(TotalCost.substring(1, TotalCost.length()));
		//int totalcostinflipkart=Integer.parseInt(TotalCost.substring(1, TotalCost.length()-1));  
		System.out.println("Costof Itam in cart before addition :::::"+totalcostinflipkart);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Save for later']//following::div[1]"))); 
		((JavascriptExecutor)wb).executeScript("arguments[0].click();", element);
		scrnsht.ToTakeScreenshot("Flipkart Validation","Remove confirmationpopup","1");
		Actions action = new Actions(wb);
		action.moveToElement(fp.Flipkart_removefromCArtpopup).build().perform();
		fp.Flipkart_removefromCArtpopup.click();
		Thread.sleep(3000);
}
	@AfterTest
	public void scriptEnds()
		{
		wb.quit();
		System.out.println("Script started");
		}
}
