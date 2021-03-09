package com.utility.common;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
public class Screenshot
{
	public WebDriver driver;
	public static String text1;
	public static String screenshot_path;
	public static int step=0;
	public Screenshot(WebDriver driver)
	{
			this.driver=driver;
	}
	public String ToTakeScreenshot(String Desc,String Testcase ,String Iteration) throws IOException
		{		
			step=step+1;
			screenshot_path=System.getProperty("user.dir")+"\\ScreenShots\\";
			String screenpath=screenshot_path+Testcase+"_"+Iteration+"_"+"Step 0"+step+"_"+Desc+".png";
			System.out.println(screenpath);
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(screenpath));
			return screenpath;
			
		} 
		
		public void OTRtemplates(String Testcase,String Iteration,String Testcase_Desc) throws IOException, InvalidFormatException
		{
			  File f=new File(System.getProperty("user.dir")+"\\OTRTemplate\\WireCardOTR.doc");
			   try 
			{
				FileInputStream fis1=new FileInputStream(f);
				text1=System.getProperty("user.dir")+"\\OtrSCreens\\"+Testcase+"_"+Iteration+"_"+Testcase_Desc+".doc";
				FileUtils.copyToFile(fis1, new File(text1));
			} 
			   catch (FileNotFoundException e) 
			   {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   }
		}
		
		
		public static void printScreensToWord(String screenshot_path,String text1) {
			String imgFile =null;
			FileInputStream fin=null;
			FileOutputStream fos=null;
			XWPFDocument doc=null;
			XWPFParagraph title=null;
			XWPFRun run=null;
			try
			{
				doc = new XWPFDocument();
				title= doc.createParagraph();    
				run = title.createRun();
			    run.addBreak(BreakType.PAGE);
			    String OTRTemplatePath=text1;
			   	File[] files = new File(screenshot_path).listFiles();
				for (File filePath : files)
				{
				    if (!filePath.isFile()) continue;
				    String fileName = filePath.getName();
				    run.setText(fileName);
				    run.setBold(true);
				    title.setAlignment(ParagraphAlignment.LEFT);
				    fin = new FileInputStream(filePath);
			 	    run.addBreak();
			 	    run.addPicture(fin, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(500), Units.toEMU(300)); // 200x200 pixels
			 	    fos = new FileOutputStream(OTRTemplatePath);
			 	    run.addBreak();
			 	    run.addBreak(BreakType.TEXT_WRAPPING);
			 	    doc.write(fos);
				 }
				fin.close();
				fos.close();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			    
		}

	}