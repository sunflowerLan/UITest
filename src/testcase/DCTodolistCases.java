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
 * @Title: DCTodolistCases.java
 * @Package testcase
 * @Description: 督查待办列表测试用例
 * @author lanfangz
 * @date 2017年12月22日 下午5:23:37
 * @version V1.0
 */
public class DCTodolistCases extends BaseTest {
	public PetRegister register = null;
	public Operation opt = null;
	public String regNum = "";
	public String dcNum = "";

	/**
	 * @Title: test_DC_qx_operation
	 * @Description: 督查办理流程：区县处理(普通办理)
	 * @throws Exception
	 */
	@Test
	public void test_DC_qx_operation() throws Exception {
		logger.info("督查办理流程：区县处理(普通办理)");
		boolean flag = false;
		// 数据初始化
		Operation DcOpt = new Operation();
		DcOpt.fileName = "upload1.jpg";
		// 打开督查待办列表
		DCListPage todoListPage = new DCListPage(driver, Variables.BASEURL + "/supervisesBacklog");
		// 录入督查编号，进行处理
		todoListPage.find_by_dcNum_and_operate_action(dcNum, DcOpt);
		// 切换到已办列表
		DCListPage doneListPage = new DCListPage(driver, Variables.BASEURL + "/supervisesClose");
		Thread.sleep(2000);
		// 查找案件办理状态，如果为已办结，则将断言标志设置为true
		String status = doneListPage.get_operation_status(dcNum);
		if (status != null && status.indexOf("已办结") != -1) {
			flag = true;
		}

		// 断言
		Assert.assertTrue(flag);
	}

	/**
	 * @Title: test_DC_qx_assigin
	 * @Description: 督查办理流程:区县登记-下发到街道
	 * @throws Exception
	 */
	@Test
	public void test_DC_qx_assigin() throws Exception {
		logger.info("督查办理流程：区县登记-下发到街道");
		boolean flag = false;
		// 数据初始化
		Operation DcOpt = new Operation();
		// DcOpt.fileName = "upload1.jpg";
		// 打开督查待办列表
		DCListPage todoListPage = new DCListPage(driver, Variables.BASEURL + "/supervisesBacklog");
		// 录入督查编号，进行交办
		todoListPage.find_by_dcNum_and_assign_action(dcNum, DcOpt);

		// 查找案件办理状态，如果为已交办，则将断言标志设置为true
		String status = todoListPage.get_operation_status(dcNum);
		if (status != null && status.indexOf("已交办") != -1) {
			flag = true;
		}

		// 断言
		Assert.assertTrue(flag);
	}

	/**
	 * @Title: test_DC_assign_jd_receive_operate
	 * @Description: 督查办理流程：区县登记-下发到街道-街道确认收件-街道处理
	 * @throws Exception
	 */
	@Test
	public void test_DC_assign_jd_receive_operate() throws Exception {
		logger.info("督查办理流程：区县登记-下发到街道-街道确认收件-街道处理");
		boolean flag = false;
		// 数据初始化
		Operation DcOpt = new Operation();
		// DcOpt.fileName = "upload1.jpg";
		// 打开督查待办列表
		DCListPage todoListPage = new DCListPage(driver, Variables.BASEURL + "/supervisesBacklog");
		// 录入督查编号，进行交办
		todoListPage.find_by_dcNum_and_assign_action(dcNum, DcOpt);

		// 获取信访督查的状态
		String optStatus = todoListPage.get_operation_status(dcNum);
		if (optStatus != null && optStatus.indexOf("已交办") != -1) {
			// 街道用户登录系统
			quit();
			setUp();
			// 街道用户登录系统
			LoginPage loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
			loginPage.loginAction(Variables.ZCUSER, Variables.ZCPWD);

			// 进入督查待办列表
			todoListPage = new DCListPage(driver, Variables.BASEURL + "/supervisesBacklog");

			// 查询督查编号并进行确认收件
			todoListPage.receive_DC_action(dcNum);

			// 查询督查编号进行处理操作
			todoListPage.find_by_dcNum_and_operate_action(dcNum, DcOpt);

			// 切换到已办列表
			DCListPage doneListPage = new DCListPage(driver, Variables.BASEURL + "/supervisesClose");
			Thread.sleep(2000);

			// 查找案件办理状态，如果为已办结，则将断言标志设置为true
			String status = doneListPage.get_operation_status(dcNum);
			if (status != null && status.indexOf("已办结") != -1) {
				flag = true;
			}
		}

		// 断言
		Assert.assertTrue(flag);
	}

	/**
	 * @Title: beforeMethod
	 * @Description: 数据准备：添加信访件，处理后，信访满意度为不满意，创建督查件
	 * @throws Exception
	 */
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

	}

	@AfterMethod
	public void afterMethod() {
		quit();
	}

}
