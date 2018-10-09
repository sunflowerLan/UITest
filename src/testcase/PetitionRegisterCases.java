package testcase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import config.Variables;
import entity.PetRegister;
import model.BaseTest;
import pageobject.LoginPage;
import pageobject.PetitionRegisterPage;

/**
 * @Title: PetitionRegisterCases.java
 * @Package testcase
 * @Description: 信访登记测试
 * @author lanfangz
 * @date 2017年8月31日 下午6:12:04
 * @version V1.0
 */
@Listeners({ util.TestNGListener.class })
public class PetitionRegisterCases extends BaseTest {

	/**
	 * @Title: test_petReg_input_required_fiels_success
	 * @Description: 新增信访登记，录入必填字段保存成功
	 * @throws Exception
	 */
	@Test
	private void test_petReg_input_required_fiels_success() throws Exception {
		logger.info("开始执行test_petReg_input_required_fiels_success()");
		// 初始化参数
		boolean test_res = false;
		PetRegister register = new PetRegister(true);
		// 长兴县用户登录系统
		LoginPage loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
		loginPage.loginAction(Variables.CXUSER, Variables.CXPWD);
		// 信访登记页面
		PetitionRegisterPage pRegisterPage = new PetitionRegisterPage(driver, Variables.BASEURL + "/petitionRegister");
		// 所有输入项必填
		pRegisterPage.add_required_field_register_action(register);

		// 获取当前url进行断言
		String url = driver.getCurrentUrl();
		if (url.indexOf("/petitionBacklog") != -1) {
			test_res = true;
		}
		Assert.assertTrue(test_res);
	}

	/**
	 * @Title: test_petReg_input_all_fiels_success
	 * @Description: 录入所有字段保存成功
	 * @throws Exception
	 */
	@Test
	private void test_petReg_input_all_fiels_success() throws Exception {
		logger.info("开始执行test_petReg_input_all_fiels_success()");
		// 初始化参数
		boolean test_res = false;
		PetRegister register = new PetRegister(true);
		// 长兴县用户登录系统
		LoginPage loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
		loginPage.loginAction(Variables.CXUSER, Variables.CXPWD);

		// 打开信访登记页面
		PetitionRegisterPage pRegisterPage = new PetitionRegisterPage(driver, Variables.BASEURL + "/petitionRegister");
		// 只输入必填项保存
		pRegisterPage.add_full_field_register_Action(true, register);
		// 获取当前url进行断言
		String url = driver.getCurrentUrl();
		if (url.indexOf("/petitionBacklog") != -1) {
			test_res = true;
		}
		Assert.assertTrue(test_res);
	}

	/**
	 * @Title: test_add_street_petReg_success
	 * @Description: 测试街道添加案件
	 * @throws Exception
	 */
	@Test
	private void test_add_street_petReg_success() throws Exception {
		logger.info("开始执行test_petReg_input_all_fiels_success()");
		// 初始化参数
		boolean test_res = false;
		PetRegister register = new PetRegister(false);

		// 雉城用户登录系统
		LoginPage loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
		loginPage.loginAction(Variables.ZCUSER, Variables.ZCPWD);

		// 打开信访登记页面
		PetitionRegisterPage pRegisterPage = new PetitionRegisterPage(driver, Variables.BASEURL + "/petitionRegister");
		// 只输入必填项保存
		pRegisterPage.add_full_field_register_Action(false, register);
		// 获取当前url进行断言
		String url = driver.getCurrentUrl();
		if (url.indexOf("/petitionBacklog") != -1) {
			test_res = true;
		}
		Assert.assertTrue(test_res);
	}

	/**
	 * @Title: test_petReg_upload_file_success
	 * @Description: 上传文件
	 * @throws Exception
	 */
	@Test
	private void test_petReg_upload_file_success() throws Exception {
		logger.info("开始执行test_petReg_upload_file_success()");
		// 初始化参数
		boolean test_res = false;
		PetRegister register = new PetRegister(true);

		// 长兴县用户登录系统
		LoginPage loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
		loginPage.loginAction(Variables.CXUSER, Variables.CXPWD);

		// 定位上传文件路径
		register.setFileName("upload1.jpg");

		// 创建信访案件
		PetitionRegisterPage pRegisterPage = new PetitionRegisterPage(driver, Variables.BASEURL + "/petitionRegister");
		// 上传文件
		pRegisterPage.add_required_field_register_action(register);

		// 断言
		String url = driver.getCurrentUrl();
		if (url.indexOf("/petitionBacklog") != -1) {
			test_res = true;
		}
		Assert.assertTrue(test_res);
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
