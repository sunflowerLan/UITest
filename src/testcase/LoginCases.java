package testcase;

import org.testng.annotations.Test;
import config.Variables;
import model.BaseTest;
import pageobject.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

@Listeners({ util.TestNGListener.class })
public class LoginCases extends BaseTest {

	/**
	 * @Title: test_login_success
	 * @Description: 县级用户登录成功
	 * @throws Exception
	 */
	@Test
	public void test_X_login_success() throws Exception {
		logger.info("开始执行test_X_login_success()");
		LoginPage loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
		// 登录
		loginPage.loginAction(Variables.CXUSER, Variables.CXPWD);
		// 断言
		String url = loginPage.getUrl();
		Assert.assertTrue(url.indexOf("/home") != -1);
	}

	/** 
	* @Title: test_S_login_success 
	* @Description: 所级用户登录成功
	* @throws Exception
	*/
	@Test
	public void test_S_login_success() throws Exception {
		logger.info("开始执行test_S_login_success()");
		LoginPage loginPage = new LoginPage(driver, Variables.BASEURL);

		// 登录
		loginPage.loginAction(Variables.ZCUSER, Variables.ZCPWD);
		// 断言
		Assert.assertTrue(loginPage.getUrl().indexOf("/home") != -1);
	}

	@BeforeClass
	public void beforeClass() {
	}

	@BeforeMethod
	public void beforeMethod() throws Exception {
		setUp();
	}

	@AfterMethod
	public void afterMethod() {
		quit();
	}

}
