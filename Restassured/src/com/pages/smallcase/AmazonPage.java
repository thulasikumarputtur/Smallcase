package com.pages.smallcase;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.utility.common.Smallcase_EnvSetup;

public class AmazonPage extends Smallcase_EnvSetup{
	public AmazonPage(WebDriver driver)
	{
        PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//input[@id='ap_email']")
    public WebElement amazon_Username;
	
	@FindBy(xpath="//span[@id='continue']")
	public WebElement amazon_Continue;
	
	
	
	@FindBy(xpath="//a[text()='Login']")
	public WebElement amazon_Login;
	
	@FindBy(xpath="//input[@id='ap_password']")
	public WebElement amazon_Password;

	@FindBy(xpath="//input[@id='signInSubmit']")
	public WebElement amazon_Loginbtn;
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	public WebElement amazon_Search;
	
	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	public WebElement amazon_Search_btn;
	
	
	@FindBy(xpath="//input[@id='add-to-cart-button']")
	public WebElement amazonGoto_Kart;
	
	
	@FindBy(xpath="//a[@id='hlb-view-cart-announce']")
	public WebElement amazon_carticon;
	
	@FindBy(xpath="//span[@id='hlb-ship-message']//preceding::span[1]")
	public WebElement amazon_total;
	
	@FindBy(xpath="//div[text()='Save for later']//following::div[1]")
	public WebElement amazon_removefromCArt;
	
	@FindBy(xpath="//div[text()='Cancel']//following::div[1]")
	public WebElement amazon_removefromCArtpopup;
	
	@FindBy(xpath="//input[contains(@name,'submit.delete')]")
	public List<WebElement> amazon_removeallitems;
	
	
	
	
	
	
	public WebElement amazon_Continue()
	{

        return amazon_Continue;
             

    }
	
	
	public WebElement amazon_total()
	{

        return amazon_total;
             

    }
	public WebElement amazon_removefromCArtpopup()
	{

        return amazon_removefromCArtpopup;
             

    }
	
	
	public WebElement amazonGoto_Kart()
	{

        return amazonGoto_Kart;
             

    }
	
	
	public WebElement amazon_Search_btn()
	{

        return amazon_Search_btn;
             

    }
	public WebElement amazon_Username()
	{

        return amazon_Username;
             

    }
	public WebElement amazon_Search()
	{

        return amazon_Search;
             

    }
	public WebElement amazon_Login()
	{
		return amazon_Login;
	}
	
	public WebElement amazon_Password()
	{

        return amazon_Password;

    }
	public WebElement amazon_Loginbtn()
	{

         return amazon_Loginbtn;
             

    }
	
	public WebElement amazon_carticon()
	{

         return amazon_carticon;
             

    }
	
	
	public void searchItem(WebDriver driver) throws InterruptedException, IOException
	{
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		action.moveToElement(amazon_Search).build().perform();
		amazon_Search.sendKeys("VU TV");
		amazon_Search_btn.submit();
		scrnsht.ToTakeScreenshot("amazon Validation","SearchSuccessfully","1");
		wb.get("https://www.amazon.in/dp/B089KVCGCV/ref=redir_mobile_desktop?_encoding=UTF8&aaxitk=sYpOD11lSCftyp3XEAAgvA&hsa_cr_id=8069565240802&pd_rd_plhdr=t&pd_rd_r=c0b13629-b3e1-41d5-b5d0-54d59cce5fc1&pd_rd_w=fZJxX&pd_rd_wg=fPuyP&ref_=sbx_be_s_sparkle_lsi3d_asin_1_title");
		scrnsht.ToTakeScreenshot("amazon Validation","SearchResultPage","1");
		amazonGoto_Kart.click();
		Thread.sleep(3000);
		scrnsht.ToTakeScreenshot("amazon Validation","Added to kart","1");
		//amazon_carticon.click();
		//Thread.sleep(4000);
	}
	
	
	
	
public void loginToamazon(String userName,String passWord) throws IOException
{

	amazon_Username.sendKeys(userName);
	amazon_Continue.click();
	amazon_Password.sendKeys(passWord);
	scrnsht.ToTakeScreenshot("UsernamePassword Popup","UsernamePassword Popup","1");
	amazon_Loginbtn.click();
}

}
