package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**   
* @Title: LoginPage.java 
* @Package pageobject 
* @Description: 登录页面 
* @author lanfangz  
* @date 2017年8月31日 上午11:44:01 
* @version V1.0   
*/
public class LoginPage extends BasePage {
    
	/** 
	* Title: 构造方法
	* @param driver 浏览器对象
	* @param url 页面
	*/
	public LoginPage(WebDriver driver, String url) {
		super(driver,url);
	}
		
	/** 
	* @Title: userName 
	* @Description: 输入用户名
	* @param user 用户名
	* @throws Exception 异常
	*/
	private void setUserName(String user) throws Exception {
		super.clearAndSendKeys(By.id("account"),user);
	}

	
	/** 
	* @Title: passWord 
	* @Description: 输入密码
	* @param pwd 密码
	* @throws Exception 
	*/
	private void setPassWord(String pwd) throws Exception {
		super.clearAndSendKeys(By.id("password"),pwd);
	}
	
	/** 
	* @Title: loginButton 
	* @Description: 点击登录
	* @throws Exception
	* @return void
	*/
	private void clickLoginButton() throws Exception {
		super.clickElem(By.id("login_button"));
	}
	
	/** 
	* @Title: getErrorMsg 
	* @Description: 获取登录错误提示信息
	* @return
	* @throws Exception
	*/
	public String getErrorMsg() throws Exception {
		return super.getElemText(By.cssSelector(".login_error_message span"));
	}
	
	/** 
	* @Title: loginAction 
	* @Description: 登录操作
	* @param user 用户名
	* @param pwd 密码
	* @throws Exception 异常
	*/
	public void loginAction(String user, String pwd) throws Exception {
		logger.info("开始登陆系统");
		super.open();
		setUserName(user);
		setPassWord(pwd);
		clickLoginButton();
		Thread.sleep(2000);
	}
		
	/** 
	* @Title: loginSuccess 
	* @Description: 登录成功
	* @param user 用户名
	* @param pwd 密码
	* @return MainPage 主页类
	* @throws Exception 异常
	*/
	public boolean loginSuccess(String user, String pwd) throws Exception{
		boolean flag = true;
		loginAction(user, pwd);
		return flag;
	}

}
