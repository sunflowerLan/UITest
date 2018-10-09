package pageobject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.Variables;
import util.Common;

import org.openqa.selenium.JavascriptExecutor;

/**
 * @ClassName: BasePage
 * @Description: 页面基类
 * @author lanfangz,fangz
 * @date 2017年6月26日 下午4:03:42
 * @version V1.0
 */
public class BasePage {

	public WebDriver driver;
	private String baseUrl;
	public static Logger logger;
	public static Common common = new Common();
	public static Random random = new Random();

	/**
	 * Title: 默认构造方法
	 */
	public BasePage() {
		logger = LogManager.getLogger(this.getClass());
	}

	/**
	 * Title: 构造方法
	 * 
	 * @param driver
	 *            浏览器对象
	 * @param url
	 *            页面
	 */
	public BasePage(WebDriver driver, String url) {
		this.driver = driver;
		this.baseUrl = url;
		logger = LogManager.getLogger(this.getClass());
	}

	/**
	 * 不带url参数的初始化方法
	 * 
	 * @param driver
	 *            浏览器驱动
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.baseUrl = getUrl();
		logger = LogManager.getLogger(this.getClass());
	}

	/**
	 * @Title: open
	 * @Description: 打开指定页面
	 */
	public void open() {
		this.driver.get(this.baseUrl);
	}

	/**
	 * @Title: navigate
	 * @Description: 跳转指定页面
	 * @param url
	 *            页面
	 */
	public void navigate(String url) {
		this.driver.get(this.baseUrl + url);
	}

	/**
	 * @Title: getUrl
	 * @Description: 获取当前url并返回
	 * @return String
	 */
	public String getUrl() {
		return this.driver.getCurrentUrl();
	}

	/**
	 * @Title: getTitle
	 * @Description: 返回当前页面名称
	 * @return String
	 */
	public String getTitle() {
		return this.driver.getTitle();
	}

	/**
	 * @Title: clearAndSendKeys
	 * @Description: 清空内容并赋值
	 * @param by
	 *            元素对象
	 * @param value
	 *            输入值
	 * @throws Exception
	 *             异常
	 */
	public void clearAndSendKeys(By by, String value) throws Exception {
		WebElement element = elementWait(Variables.WAITTIME, by);
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * @Title: getElemText
	 * @Description: 获取元素文本
	 * @param by
	 *            元素对象
	 * @return String 文本
	 * @throws Exception
	 *             异常
	 */
	public String getElemText(By by) throws Exception {
		WebElement element = elementWait(Variables.WAITTIME, by);
		return element.getText();
	}

	/**
	 * @Title: clickElem
	 * @Description: 单击元素
	 * @param by
	 *            元素对象
	 * @throws Exception
	 *             异常
	 */
	public void clickElem(By by) throws Exception {
		WebElement element = elementWait(Variables.WAITTIME, by);
		element.click();
	}

	/**
	 * @Title: clickElemIndex
	 * @Description: 点击集合索引元素
	 * @param by
	 *            元素对象
	 * @param index
	 *            索引值
	 * @throws Exception
	 *             异常
	 */
	public void clickElemIndex(By by, int index) throws Exception {
		List<WebElement> list = ElementListWait(Variables.WAITTIME, by);
		list.get(index).click();
	}

	/**
	 * 执行js脚本
	 * 
	 * @param js
	 *            脚本
	 */
	public void executJs(String js) {
		((JavascriptExecutor) driver).executeScript(js);
	}

	/**
	 * @Title: screenShot
	 * @Description: 截屏
	 * @param driver
	 * @throws IOException
	 */
	private void screenShot(WebDriver driver) throws IOException {
		Date currentTime = new Date();
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
		String time = dateFormat.format(currentTime);
		dateFormat = new SimpleDateFormat("yyyyMMdd");
		String date = dateFormat.format(currentDate);
		String filePath = "test-output/screenShot/" + date + "/";
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(filePath + "by" + date + '_' + time + ".png"));
	}

	/**
	 * @Title: elementWait
	 * @Description: 获取单个元素
	 * @param sec
	 *            超时时间
	 * @param by
	 *            元素对象
	 * @throws Exception
	 *             异常
	 * @return WebElement
	 */
	public WebElement elementWait(long sec, By by) throws Exception {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, sec);
		try {
			element = wait.until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver dr) {
					return dr.findElement(by);
				}
			});
		} catch (TimeoutException e) {
			// 异常处理
			screenShot(driver);
			logger.error("找不到元素:" + by.toString() + '_' + e.getMessage());
			driver.quit();
			throw e;
		}
		// logger.debug("找到元素:"+by.toString());
		return element;
	}

	/**
	 * @Title: ElementListWait
	 * @Description: 获取一组元素
	 * @param sec
	 *            超时时间
	 * @param by
	 *            元素对象
	 * @return List 元素组
	 * @throws Exception
	 *             异常
	 */
	public List<WebElement> ElementListWait(long sec, By by) throws Exception {
		List<WebElement> eleList = null;
		WebDriverWait wait = new WebDriverWait(driver, sec);
		try {
			eleList = wait.until(new ExpectedCondition<List<WebElement>>() {
				@Override
				public List<WebElement> apply(WebDriver dr) {
					return dr.findElements(by);
				}
			});
		} catch (TimeoutException e) {
			// 异常处理
			screenShot(driver);
			logger.debug("找到元素:" + by.toString());
			logger.error("找不到组元素:" + by.toString() + '_' + e.getMessage());
			throw e;
		}
		return eleList;
	}

	/**
	 * @Title: clickReceiveEle
	 * @Description: 确认提示页面点击确定按钮
	 * @throws Exception
	 */
	public void clickOKBut() throws Exception {
		clickElem(By.cssSelector(".ngdialog-open .modal-footer button[ng-click='ok()']"));
	}

	public static void main(String[] args) {

	}
}
