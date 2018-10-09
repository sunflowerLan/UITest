package model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


/** 
* @ClassName: Driver 
* @Description: 浏览器驱动类
* @author  lanfangz
* @date 2017年7月3日 下午5:19:16 
* @version V1.0 
*/
public class Driver {
	public WebDriver driver;
	private Logger logger;

	/**
	 * 配置初始化
	 * @param b - 是否添加配置
	 * @return ChromeDriver
	 * @throws IOException IO异常 
	 */
	public WebDriver browser(boolean b) throws IOException {
		//根据Chrome的路径配置系统属性
		  File directory = new File(".");
	      String sourceFile = directory.getCanonicalPath() + "\\Driver\\chromedriver32_2.33.exe";    
		  System.setProperty("webdriver.chrome.driver", sourceFile);
		  
		if (b) {
			String downloadFilepath = System.getProperty("user.dir")+"\\test-output\\download";// 设置下载路径
			// 配置浏览器的下载路径
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("download.default_directory", downloadFilepath);
			options.setExperimentalOption("prefs", prefs);
			// 带属性初始化浏览器
			driver = new ChromeDriver(options);
		} else {
			driver = new ChromeDriver();
		}
		return driver;
	}
	
    public WebDriver browser2(String browser) throws IOException {
		
		String sourceFile = System.getProperty("user.dir");//获取当前路径
		
		if (browser.toLowerCase().equals("chrome")) {
			// 根据Chrome的路径配置系统属性
			sourceFile += "\\Driver\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", sourceFile);
			driver = new ChromeDriver();
		} else if (browser.toLowerCase().equals("firefox")) {
	//		sourceFile += "\\Driver\\geckodriver.exe";
			// 指定firefox浏览器的安装位置
			System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			// 设置firefox浏览器的driver位置
	//		System.setProperty("webdriver.gecko.driver", sourceFile);
			driver = new FirefoxDriver();
		} else if (browser.toLowerCase().equals("ie")) {
			sourceFile += "\\Driver\\IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", sourceFile);
			driver = new InternetExplorerDriver();
		} else {
			logger.error("暂不支持该浏览器");
		}

		return driver;
	}
	
	public static void main(String[] arge) throws IOException {
		WebDriver driver;
		Driver dr=new Driver();
		driver=dr.browser2("ie");	
		driver.get("http://192.168.5.147:42000");
		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
		System.out.println(System.getProperty("user.dir")+"\\test-output\\download");
	}
}
