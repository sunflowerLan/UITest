package util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import model.BaseTest;

/** 
* @ClassName: TestNGListener 
* @Description: 重写监听类
* @author wenh
* @date 2017年7月3日 下午4:31:31 
* @version V1.0 
*/
public class TestNGListener extends TestListenerAdapter {
	
	private Logger logger=LogManager.getLogger(this.getClass());
		 
    @Override
    public void onTestStart(ITestResult result) {

    }
     
    @Override
    public void onTestFailure(ITestResult tr) {
    	logger.error("Error info:" + tr.getThrowable().getMessage());
    	BaseTest test = (BaseTest) tr.getInstance();
        WebDriver driver = test.getDriver(); 
        try {
			screenShot(driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
     
    @Override
    public void onTestSkipped(ITestResult tr) {
    	
    }
     
    @Override
    public void onTestSuccess(ITestResult tr) {

    }
    
	/** 
	* @Title: screenShot 
	* @Description: 截屏
	* @param method
	* @throws IOException IO异常
	*/
	private void screenShot(WebDriver driver) throws IOException {
		Date currentTime= new Date();
		Date currentDate= new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
		String time = dateFormat.format(currentTime);
		dateFormat = new SimpleDateFormat("yyyyMMdd");
		String date = dateFormat.format(currentDate);
		String filePath="test-output/screenShot/"+date+"/";
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(filePath+date+'_'+time+".png"));		
	}
}

