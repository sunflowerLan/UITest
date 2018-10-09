package testcase;

import org.testng.annotations.Test;

import config.Variables;
import entity.DCInfo;
import entity.Operation;
import entity.PetRegister;
import model.BaseTest;
import pageobject.DCListPage;
import pageobject.LoginPage;
import pageobject.PetRegListPage;
import pageobject.PetitionRegisterPage;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

/**
 * @Title: DCDonelistCases.java
 * @Package testcase
 * @Description: 督查已办列表测试用例
 * @author lanfangz
 * @date 2017年12月27日 下午1:15:52
 * @version V1.0
 */
public class DCDonelistCases extends BaseTest {
	public PetRegister register = null;
	public Operation opt = null;
	public Operation DcOpt = null;
	public String regNum = "";
	public String dcNum = "";

	/**
	 * @Title: test_add_DC_CSR
	 * @Description: 测试满意度(督查满意)
	 * @throws Exception
	 */
	@Test
	public void test_add_DC_CSR() throws Exception {
		logger.info("测试督查满意度");
		// 数据初始化
		boolean flag = false;
		DcOpt.setVisitResult("督办满意");
		
		// 切换到已办列表
		DCListPage doneListPage = new DCListPage(driver, Variables.BASEURL + "/supervisesClose");
		Thread.sleep(2000);
		
		// 从已办列表查找督查案件并添加满意度信息
		doneListPage.find_by_dcNum_and_add_CSR(dcNum, DcOpt);
		flag = true;
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
		// 督查信息初始化
		DCInfo dcInfo = new DCInfo();
		dcNum = dcInfo.getDcNum();
		// 督查处理初始化
		DcOpt = new Operation();
		DcOpt.fileName = "upload1.jpg";

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

		// 切换到已办列表
		PetRegListPage donelistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionClose");

		// 从已办列表查找信访案件，添加满意度为不满意，并添加督查信息
		donelistPage.find_by_regNum_and_add_DCinfo(regNum, opt, dcInfo);

		// 打开督查待办列表
		DCListPage todoListPage_dc = new DCListPage(driver, Variables.BASEURL + "/supervisesBacklog");

		// 录入督查编号，进行处理
		todoListPage_dc.find_by_dcNum_and_operate_action(dcNum, DcOpt);
	}

	@AfterMethod
	public void afterMethod() {
	}

}
