package com.utility.common;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import com.pages.smallcase.AmazonPage;
import com.pages.smallcase.FlipkartPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Smallcase_EnvSetup
{
	public static WebDriver wb;
	public 	Smallcase_EnvSetup sce;
	public static String [] a;
	public static Properties prop;
	public static File file;
	public static Date date;
	public static FlipkartPage fp;
	public static AmazonPage ap;
	public static FileInputStream fileInput = null;
	public static Screenshot scrnsht;
	public int n=0;
	public static Date dat;
	public static String timestame;
	public Utilities ut;
	public String OTRPath;
	public String Screen=null;
	public int itr=0;
	public ExtentReports exreport=null;
	public ExtentTest logger=null;
	public static String TotalCost;
	@SuppressWarnings("deprecation")
	public void Browser_Setup(String Url) throws InterruptedException, IOException
	{
		
	    loadproperty();
		//logger = exreport.startTest("passTest");
		itr=itr+1;
		dat=new Date();
		ut=new Utilities();
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
	    System.out.println(dt1.format(dat));
		timestame=dt1.format(dat);
	    try
		{
		if(prop.getProperty("Broswer").equalsIgnoreCase("FIREFOX"))
		{
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability("log", "trace");
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+ "\\Browser_Drivers\\geckodriver.exe");
			wb=new FirefoxDriver(dc);
			logger.log(LogStatus.PASS, "FireFox Browser Selected Successfully");
		}
		else if(prop.getProperty("Broswer").equalsIgnoreCase("IE"))
		{
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		  	capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		  	System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ "\\Browser_Drivers\\IEDriverServer.exe");
		  	wb=new InternetExplorerDriver(capabilities);	
		  	logger.log(LogStatus.PASS, "IE Browser Selected Successfully");
		}
		else if(prop.getProperty("Broswer").equalsIgnoreCase("CHROME"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "\\Browser_Drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();

			options.addArguments("test-type");
			options.addArguments("start-maximized");
			options.addArguments("--js-flags=--expose-gc");
			options.addArguments("--enable-precise-memory-info");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-default-apps");
			options.addArguments("test-type=browser");
			options.addArguments("disable-infobars");
			DesiredCapabilities capabilities = new DesiredCapabilities();
		    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			//driver = new ChromeDriver(options);
			wb=new ChromeDriver();
			String appUrl=prop.getProperty(Url);
			wb.get(appUrl);
			//wb.navigate().to(prop.getProperty("url"));
			System.out.println("Chrome Browser Selected Successfully with URL"+appUrl);
			//logger.log(LogStatus.PASS, "Chrome Browser Selected Successfully");
		}
		 wb.manage().window().maximize();
	        wb.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        scrnsht=new Screenshot(wb);
	      //  ToTakeScreenshot(String Desc,String Testcase ,String Iteration)
	        scrnsht.ToTakeScreenshot("LogintoApplication","Flipkart Validate","1");
		}
		
		
		catch(Exception e)
		
		{
			System.out.println(e.getMessage());
		//	logger.log(LogStatus.WARNING, "Browser not configured in xml file");
		}
	}	
	
	/*public void Flipkart_Login(String Testcase,String Iteration,String Testcase_Desc) throws InterruptedException, InvalidFormatException, IOException
	{
		wb.navigate().to(prop.getProperty("url"));
		Utilities.sleeptime();
		FlipkartPage fp=new FlipkartPage(wb);
		scrnsht=new Screenshot(wb);
		fp.clikonloginbtn();
		fp.enterusername(prop.getProperty("username"));
		Reporter.log("Username Entered SuccessFully. : "+prop.getProperty("username"));
		fp.enterpassword(prop.getProperty("password"));
		Reporter.log("Username Entered SuccessFully:"+prop.getProperty("password"));
		String Login_path=scrnsht.ToTakeScreenshot("LogintoApplication",Testcase,Iteration);
		//scrnsht.ToTakeScreenshot("LogintoApplication",Screen);
		logger.log(LogStatus.PASS, Login_path);
		fp.clickonlogin();
		Utilities.sleeptime();
		
		
		if(fp.validatehomepage().isEnabled())
		{
			Reporter.log("Login SuccessFully For Wirrecard application");
		}
		Utilities.sleeptime();
	}*/
	
	public static void loadproperty()
	{
		final String propFile =System.getProperty("user.dir")+"\\PropertyFiles\\smallcase.properties";
		
		file = new File(propFile);
		try 
		{
			fileInput = new FileInputStream(file);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
			prop = new Properties();
		try 
		{
			prop.load(fileInput);
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
/*	
	public  void teardown() throws InterruptedException
	{
		whome.Headername().click();
		Utilities.sleeptime();
		Utilities.sleeptime();
		whome.logout().click();
		Reporter.log("Validation SuccessFully Done");
		Utilities.sleeptime();
	}*/
}
