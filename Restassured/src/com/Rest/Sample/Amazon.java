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

import com.pages.smallcase.AmazonPage;
import com.pages.smallcase.FlipkartPage;
import com.utility.common.Smallcase_EnvSetup;

public class Amazon extends Smallcase_EnvSetup {
	
	@BeforeTest
	public void scriptstarts()
		{
		System.out.println("Script started");
		}
	@Test
	public void flipksrtAmazonCostCompare() throws InterruptedException, IOException
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
		wb.quit();
		sce.Browser_Setup("amazonurl");
		ap=new AmazonPage(wb);
		ap.loginToamazon(prop.getProperty("amazonusername"),prop.getProperty("amazonpassword"));
		ap.searchItem(wb);
		Thread.sleep(3000);
		scrnsht.ToTakeScreenshot("amazon Validation","Total Page","1");
		String AmazonTotalCost=(ap.amazon_total.getText().replace("+char(63)+","").replace(" ", "")).replace(",", "");
		//int totalcostinamazon=Integer.parseInt(AmazonTotalCost.substring(1, AmazonTotalCost.length()-1)); 
		float totalcostinamazon=Float.parseFloat(AmazonTotalCost.substring(1, AmazonTotalCost.length()));
		System.out.println("Costof Itam in cart is :::::"+totalcostinamazon);
		//WebDriverWait wait = new WebDriverWait(wb, 10);
		ap.amazon_carticon.click();
		if(ap.amazon_removeallitems.size()>1)
		{
			for(int i=0;i<=ap.amazon_removeallitems.size()-1;i++)
			ap.amazon_removeallitems.get(i).click();
			
		}
		else
		{
			WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='submit.delete.C2ccf0afa-b34c-4188-98ba-19f233235c54']"))); 
			((JavascriptExecutor)wb).executeScript("arguments[0].click();", element2);
			scrnsht.ToTakeScreenshot("amazon Validation","Remove confirmationpopup","1");
		}
		
		if(totalcostinamazon==totalcostinflipkart)
		{
			System.out.println("Amazon and Flipkart no cost deference:::::"+totalcostinamazon+ "=="+totalcostinflipkart);
		}
		else if(totalcostinamazon<totalcostinflipkart)
		{
			System.out.println("Amazon is lesser cost then Flipkart:::::"+totalcostinamazon+ "<"+totalcostinflipkart);
		}
		else
		{
			System.out.println("Amazon is lesser cost then Flipkart:::::"+totalcostinamazon+ ">"+totalcostinflipkart);
		}
		
	
}
	@AfterTest
	public void scriptEnds()
		{
		wb.quit();
		System.out.println("Script started");
		}
}