package testcase;

import org.testng.annotations.Test;

import config.Variables;
import entity.DCInfo;
import entity.Operation;
import entity.PetRegister;
import model.BaseTest;
import pageobject.LoginPage;
import pageobject.PetRegListPage;
import pageobject.PetitionRegisterPage;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

/**
 * @Title: PetRegDonelistCases.java
 * @Package testcase
 * @Description: 已办列表测试用例
 * @author lanfangz
 * @date 2017年9月8日 下午3:55:22
 * @version V1.0
 */
public class PetRegDonelistCases extends BaseTest {
	public PetRegister register = null;
	public Operation opt = null;
	public String regNum = "";

	/**
	 * @Title: test_add_CSR
	 * @Description: 测试满意度(信访满意)
	 * @throws Exception
	 */
	@Test
	public void test_add_CSR() throws Exception {
		logger.info("测试新增满意度");
		boolean flag = false;
		// 数据初始化

		// 切换到已办列表
		PetRegListPage donelistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionClose");

		// 从已办列表查找信访案件并添加满意度信息
		donelistPage.find_by_regNum_and_add_CSR(regNum, opt);
		flag = true;
		Assert.assertTrue(flag);
	}

	/**
	 * @Title: test_add_DC_info
	 * @Description: 测试添加督查信息
	 * @throws Exception
	 */
	@Test
	public void test_add_DC_info() throws Exception {
		logger.info("测试添加督查信息");
		boolean flag = false;
		// 数据初始化
		DCInfo dcInfo = new DCInfo();

		// 切换到已办列表
		PetRegListPage donelistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionClose");

		// 从已办列表查找信访案件，添加满意度为不满意，并添加督查信息
		donelistPage.find_by_regNum_and_add_DCinfo(regNum, opt, dcInfo);

		// 获取当前url进行断言，如果调转到督办模块待办列表则断言成功
		String url = driver.getCurrentUrl();
		if (url.indexOf("/supervisesBacklog") != -1) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	@BeforeMethod
	public void beforeMethod() throws Exception {
		setUp();
		// 长兴县用户登录系统
		LoginPage loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
		loginPage.loginAction(Variables.CXUSER, Variables.CXPWD);

		// 数据初始化
		register = new PetRegister(true);
		opt = new Operation();
		opt.setOperate("简易答复");// 设置办理方式
		regNum = register.getXfbh();

		// 信访登记页面
		PetitionRegisterPage pRegisterPage = new PetitionRegisterPage(driver, Variables.BASEURL + "/petitionRegister");
		// 输入必填项
		pRegisterPage.add_required_field_register_action(register);

		if (driver.getCurrentUrl().indexOf("/petitionBacklog") != -1) {
			// 进入信访办理待办列表
			PetRegListPage todolistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionBacklog");
			// 查询信访编号并进行办理
			todolistPage.find_by_regNum_and_operate_action(regNum, opt);
		}

	}

	@AfterMethod
	public void afterMethod() {
		quit();
	}

	@BeforeClass
	public void beforeClass() {
	}

}
