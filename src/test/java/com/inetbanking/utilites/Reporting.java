package com.inetbanking.utilites;


//Listners class used to generate extent reports
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
        
	    public ExtentHtmlReporter htmlReporter; 
	    public ExtentReports extent; 
	    public ExtentTest logger;
	
	public void onStart(ITestContext testContext){
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) ; //timeStamp 
		String repName = "Test-Report-"+timeStamp+".html";
			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName); //specifyLocation	
		    htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
			
		    extent = new ExtentReports(); 
		    
		    extent.attachReporter(htmlReporter);
		    
		    extent.setSystemInfo("Host Name","local Host");
		    extent.setSystemInfo("Enviorement","QA");
		    extent.setSystemInfo("user","Vivek");
		    
		    htmlReporter.config().setDocumentTitle("InetBanking Test Project");  //Title of project
            htmlReporter.config().setReportName("Functional Test Report"); //name of the report
             htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // location of the chart
            htmlReporter.config().setTheme(Theme.DARK);
            
		    		
	}
	
	public void onTestSuccess(ITestResult tr){
		
		   logger = extent.createTest(tr.getName()); //create new entery in the report 
		   logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); //Send the passed information in green color
		   
			
	}
	
	public void onTestFailure(ITestResult tr){
		
		  logger = extent.createTest(tr.getName()); //create entery in the report 
		  logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); //sent the failed information in red color 
		  
		  
		  String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png" ;  
		  
		  File f = new File(screenshotPath); 
		  
		  if(f.exists())
		  {
			  try{
				  
				 logger.fail("Screenshot is beleow:"+ logger.addScreenCaptureFromPath(screenshotPath));
				 
			  }
			  catch(IOException ie)
			  {
				  ie.printStackTrace();
			  }
			  
			  
		  }	  
		  
	} 
	
	public void onTestSkipped(ITestResult tr){
		
		logger= extent.createTest(tr.getName()); // create new entery in the report;
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
		
		
	}
	
	public void onFinish(ITestContext testContext){
		
		extent.flush();
		
	}
}
