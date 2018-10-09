package model;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;


import model.Driver;
import util.NodeReader;
import util.ExcelReader;

/** 
* @ClassName: BaseTest 
* @Description: 测试基类 
* @author  lanfangz
* @date 2017年7月4日 下午9:04:31 
* @version V1.0 
*/
public class BaseTest {

	/**
	 * 浏览器驱动
	 */
	public WebDriver driver;
	public static Logger logger;
	public String filename;
	
	/** 
	* Title: 构造方法 
	*/
	public BaseTest()
	{
	  if(logger==null)
		  logger=LogManager.getLogger(this.getClass());
	}
	
	/** 
	* @Title: getDriver 
	* @Description: 获取driver
	* @return
	*/
	public WebDriver getDriver()
	 {
		   return this.driver;
	 }

	/**
	 * 初始化
	 * @param b
	 * @throws IOException 
	 */
	private void init(boolean b) throws IOException {
		Driver dr = new Driver();
		this.driver = dr.browser(b);
		logger.info("启动浏览器");
	}

	/**
	 * 最大化页面
	 * @throws IOException IO异常
	 */
	public void setUp() throws IOException {
		init(false);
		driver.manage().deleteAllCookies();//清除所有cookies
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	/**
	 * 最大化页面
	 * @throws IOException IO异常
	 */
	public void setUpWithOption() throws IOException {
		init(true);
		driver.manage().deleteAllCookies();//清除所有cookies
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		logger.info("设置浏览器成功");
	}

	/**
	 * 退出页面
	 */
	public void quit() {
		driver.quit();
		logger.info("退出浏览器成功");
	}
	
	/** 
	* @Title: setFile 
	* @Description: 设置文件路径
	* @param filePath
	*/
	public void setFile(String filename) {
		this.filename = filename;
	}
	
	/** 
	* @Title: getExcelData 
	* @Description: 从excel读取文件数据
	* @param method
	* @return Object 测试数据
	* @throws Exception
	*/
	@DataProvider(name = "excelData")
	public Object[][] getExcelData(Method method) throws Exception {
		logger.info("开始读取excel文件");
		ExcelReader excelData = new ExcelReader();
		//根据文件名和sheet名读取文件
		return excelData.getExcelData(this.filename, method.getName());	  
	}

	/** 
	* @Title: getXmlData 
	* @Description: 从xml文件读取测试数据
	* @param method
	* @return
	* @throws IOException 
	*/
	@DataProvider(name = "xmlData")
	public Object[][] getXmlData(Method method) throws IOException {
		NodeReader reader = new NodeReader();		
		return new Object[][] { new Object[] { reader.getXmlData("testData_login.xml") } };
	}


}
