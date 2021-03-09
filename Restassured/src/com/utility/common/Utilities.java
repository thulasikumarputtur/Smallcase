package com.utility.common;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Utilities

{
	public static Select s;
	public static XSSFRow  Selectrow;
	public static void sleeptime() throws InterruptedException 
	{
		Thread.sleep(5000);
	}
	
	
	public static void selectdropdown(WebElement data, String Exp)
	{
		data.click();
		s=new Select(data);
		s.selectByVisibleText(Exp);
	}
	public String setfolders(String type,int i,String timestame) throws IOException
	{
		String out_putpath=null;
		String text5="ScreenPrints";
		String text6=".doc"; 
		//C:\Users\thulasi.kumar\workspace\wirecards\OTRTemplate\Wirecard_POC_OTR.docx
		File OTRTempFolder=new File(System.getProperty("user.dir")+"\\OTRTemplate\\Wirecard_POC_OTR.doc");
		String screen=System.getProperty("user.dir")+"ScreenShots\\";
		String text4="Wirecard";
		String text3="TC";
		String text7="Iteration";
		String textH="_";
		String text8=".png"; 
		String text2="\\";
		String text9="Step";
		String otr=System.getProperty("user.dir")+"\\OtrSCreens\\";
		String text10="OTR";
		String text11=".doc";
		if(type.equalsIgnoreCase("OTR"))
		{
		out_putpath=otr+text4+textH+text5+textH+text7+textH+i+timestame+text2+text3+"00"+i+textH+"Wirecard_OTR_Doc"+text6;
		FileUtils.copyFile(OTRTempFolder, new File(out_putpath));
		return out_putpath;
		}
		if(type.equalsIgnoreCase("SCREENS")) 
		{
			out_putpath=text2=screen+text4+textH+timestame;
		}
		return out_putpath;
	}

	public static void writeexcel(String value1,int rownum,int cellnum) throws IOException,  InvalidFormatException
	{
		File f=new File(System.getProperty("user.dir")+ "\\ExcelUtilities\\Wirecard_Dat.xlsx");
		FileInputStream	fin = new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fin);
		Sheet s= wb.getSheetAt(0);
		Row r=s.getRow(rownum);
		Cell c1=r.createCell(cellnum);
		c1.setCellType(CellType.STRING);
		c1.setCellValue(value1);
		FileOutputStream fos=new FileOutputStream(f);
		wb.write(fos);
		fos.close();
		fin.close();
	     }
	
	@SuppressWarnings("deprecation")
	public static void WordWriter(String dataDir, String strImg) throws InvalidFormatException, FileNotFoundException, IOException
	{
   	XWPFDocument doc = new XWPFDocument();
   	XWPFParagraph p = doc.createParagraph();
   	XWPFRun r = p.createRun();
   	String id = doc.addPictureData(new FileInputStream(new File(strImg)), doc.PICTURE_TYPE_PNG);
   	int format = XWPFDocument.PICTURE_TYPE_PNG;
   	r.setText(strImg);
   	r.getCTR().addNewDrawing().addNewInline();	
   	r.addBreak();
   	r.addPicture(new FileInputStream(strImg), format, strImg, Units.toEMU(500), Units.toEMU(300)); // 200x200 pixels
   	r.addBreak(BreakType.PAGE);
   	System.out.println(r.getParagraph());
   	FileOutputStream out = new FileOutputStream(dataDir);
   	doc.write(out);
   	out.flush();
   	out.close();
    }
	public static void deletefiles() throws IOException
	{	
	  File file = new File("C:\\Users\\thulasi.kumar\\Downloads\\");
      String[] myFiles;
      if (file.isDirectory()) 
      {
          myFiles = file.list();
          for (int i = 0; i < myFiles.length; i++)
          {
              File myFile = new File(file, myFiles[i]);
              System.out.println(myFile);
              myFile.delete();
          }
       }
	}

	public static void CopyFilestoDest(String Reportcategory,String Reports) throws IOException
	{
		String Dest_path=null;
		String Report_path=System.getProperty("user.dir")+"\\Wirecard_Reports\\" + Reportcategory +"\\"+ Reports;
		System.out.println(Report_path);
		String Download_Path="C:\\Users\\thulasi.kumar\\Downloads\\";
			File[] files = new File(Download_Path).listFiles();
			for (File filePath : files)
			{
				if (!filePath.isFile()) continue;
			    String fileName = filePath.getName();
			    int i = fileName.lastIndexOf('.');
			    if (i > 0) 
			    {
			        String extension = fileName.substring(i+1);
			        if(extension.equalsIgnoreCase("xlsx"))
			        {
			        	Dest_path=Report_path+ "\\" + "xlsx"+"\\" +fileName;
			        	//filePath.renameTo(new File("C:\\Users\\thulasi.kumar\\Downloads\\ExcelDoc.docx"));
			        }
			        if(extension.equalsIgnoreCase("xml"))
			        {
			        	Dest_path=Report_path+ "\\" +"xml"+"\\" +fileName;
			        	//filePath.renameTo(new File("C:\\Users\\thulasi.kumar\\Downloads\\xmldoc.xml"));
			        }
			        if(extension.equalsIgnoreCase("docx"))
			        {
			        	Dest_path=Report_path+ "\\" +"DOC"+"\\" +fileName;
			        	//filePath.renameTo(new File("C:\\Users\\thulasi.kumar\\Downloads\\WordDoc.docx"));
			        }
			        if(extension.equalsIgnoreCase("pdf"))
			        {
			        	Dest_path=Report_path+ "\\" +"pdf"+"\\" +fileName;
			        	//filePath.renameTo(new File("C:\\Users\\thulasi.kumar\\Downloads\\WordDoc.docx"));
			        }
			        
			        
			    }
			    System.out.println(Dest_path);
			    FileUtils.copyFile(filePath, new File(Dest_path));
			}
		}
}