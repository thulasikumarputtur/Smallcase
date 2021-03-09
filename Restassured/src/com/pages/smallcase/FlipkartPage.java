package com.pages.smallcase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.common.Smallcase_EnvSetup;


public class FlipkartPage extends Smallcase_EnvSetup{




	public FlipkartPage(WebDriver driver)
	{
        PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[@class='IiD88i _351hSN']/input[@type='text']")
    public WebElement Flipkart_Username;
	
	@FindBy(xpath="//a[text()='Login']")
	public WebElement Flipkart_Login;
	
	@FindBy(xpath="//div[@class='IiD88i _351hSN']/input[@type='password']")
	public WebElement Flipkart_Password;

	@FindBy(xpath="//button[@class='_2KpZ6l _2HKlqd _3AWRsL' and @type='submit']")
	public WebElement Flipkart_Loginbtn;
	
	@FindBy(xpath="//input[@placeholder='Search for products, brands and more']")
	public WebElement Flipkart_Search;
	
	@FindBy(xpath="//button[@class='L0Z3Pu']")
	public WebElement Flipkart_Search_btn;
	
	
	@FindBy(xpath="//button[text()='ADD TO CART']")
	public WebElement FlipkartGoto_Kart;
	

	@FindBy(xpath="//div[text()='Save for later']//preceding::button[1]")
	public WebElement FlipkartGoto_cartincrease;
	
	
	@FindBy(xpath="//a[contains(@href,'Cart')]")
	public WebElement Flipkart_carticon;
	
	@FindBy(xpath="//div[text()='Total Amount']//following::div[1]")
	public WebElement Flipkart_total;
	
	@FindBy(xpath="//div[text()='Save for later']//following::div[1]")
	public WebElement Flipkart_removefromCArt;
	
	@FindBy(xpath="//div[text()='Cancel']//following::div[1]")
	public WebElement Flipkart_removefromCArtpopup;
	
	
	public WebElement Flipkart_total()
	{

        return Flipkart_total;
             

    }
	public WebElement Flipkart_removefromCArtpopup()
	{

        return Flipkart_removefromCArtpopup;
             

    }
	
	public WebElement FlipkartGoto_cartincrease()
	{

        return FlipkartGoto_cartincrease;
             

    }
	
	
	public WebElement FlipkartGoto_Kart()
	{

        return FlipkartGoto_Kart;
             

    }
	
	
	public WebElement Flipkart_Search_btn()
	{

        return Flipkart_Search_btn;
             

    }
	public WebElement Flipkart_Username()
	{

        return Flipkart_Username;
             

    }
	public WebElement Flipkart_Search()
	{

        return Flipkart_Search;
             

    }
	public WebElement Flipkart_Login()
	{
		return Flipkart_Login;
	}
	
	public WebElement Flipkart_Password()
	{

        return Flipkart_Password;

    }
	public WebElement Flipkart_Loginbtn()
	{

         return Flipkart_Loginbtn;
             

    }
	
	public WebElement Flipkart_carticon()
	{

         return Flipkart_carticon;
             

    }
	
	
	public void searchItem(WebDriver driver) throws InterruptedException, IOException
	{
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		action.moveToElement(Flipkart_Search).build().perform();
		Flipkart_Search.sendKeys("VU TV");
		scrnsht.ToTakeScreenshot("Flipkart Validation","SearchSuccessfully","1");
		wb.get("https://www.flipkart.com/vu-premium-139-cm-55-inch-ultra-hd-4k-led-smart-android-tv/p/itm15edd0f5f73f4?pid=TVSFPENZUMTBQAHG&lid=LSTTVSFPENZUMTBQAHGKKIHQX&marketplace=FLIPKART&srno=s_1_1&otracker=search&otracker1=search&fm=SEARCH&iid=b75a5ffd-9cbd-476a-924d-d7335a21fe09.TVSFPENZUMTBQAHG.SEARCH&ppt=hp&ppn=homepage&ssid=hphqu3hq5s0000001615207017139&qH=de08163c2de9f914");
		Flipkart_Search_btn.submit();
		scrnsht.ToTakeScreenshot("Flipkart Validation","SearchResultPage","1");
		FlipkartGoto_Kart.click();
		Thread.sleep(3000);
		scrnsht.ToTakeScreenshot("Flipkart Validation","Added to kart","1");
		//Flipkart_carticon.click();
		//Thread.sleep(4000);
	}
	
	
	
	
public void loginToFlipkart(String userName,String passWord) throws IOException
{

	Flipkart_Username.sendKeys(userName);
	Flipkart_Password.sendKeys(passWord);
	scrnsht.ToTakeScreenshot("UsernamePassword Popup","UsernamePassword Popup","1");
	Flipkart_Loginbtn.click();
}

}
