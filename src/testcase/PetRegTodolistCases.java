package testcase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import config.Variables;
import entity.Operation;
import entity.PetRegister;
import model.BaseTest;
import pageobject.LoginPage;
import pageobject.PetRegListPage;
import pageobject.PetitionRegisterPage;

/**
 * @Title: PetRegTodolistCases.java
 * @Package testcase
 * @Description: 待办列表测试用例
 * @author lanfangz
 * @date 2017年9月4日 下午12:57:50
 * @version V1.0
 */
@Listeners({ util.TestNGListener.class })
public class PetRegTodolistCases extends BaseTest {

	/**
	 * @Title: test_PetRegister_qx_operation
	 * @Description: 信访办理流程：区县登记-区县处理(普通办理)
	 * @throws Exception
	 */
	@Test
	public void test_PetRegister_qx_operation() throws Exception {
		logger.info("测试流程：区县登记-区县处理");
		boolean flag = false;
		// 数据初始化
		PetRegister register = new PetRegister(true);
		Operation opt = new Operation();
		opt.setOperate("普通办理");// 设置办理方式
		opt.fileName = "upload1.jpg";
		String regNum = register.getXfbh();

		// 长兴县用户登录系统
		LoginPage loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
		loginPage.loginAction(Variables.CXUSER, Variables.CXPWD);

		// 信访登记页面
		PetitionRegisterPage pRegisterPage = new PetitionRegisterPage(driver, Variables.BASEURL + "/petitionRegister");

		// 所有输入项必填
		pRegisterPage.add_required_field_register_action(register);

		if (driver.getCurrentUrl().indexOf("/petitionBacklog") != -1) {
			// 进入信访办理待办列表
			PetRegListPage todolistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionBacklog");
			// 查询信访编号并进行办理
			todolistPage.find_by_regNum_and_operate_action(regNum, opt);

			// 切换到已办列表
			PetRegListPage donelistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionClose");
			Thread.sleep(2000);
			// 查找案件办理状态，如果为已办结，则将断言标志设置为true
			String status = donelistPage.get_operation_status(regNum);
			if (status != null && status.indexOf("已办结") != -1) {
				flag = true;
			}
		}
		// 断言
		Assert.assertTrue(flag);
	}

	/**
	 * @Title: test_PetRegister_qx_assign
	 * @Description: 信访办理流程：区县登记-下发到街道
	 * @throws Exception
	 */
	@Test
	public void test_PetRegister_qx_assign() throws Exception {
		logger.info("测试流程：区县登记-下发到街道");
		boolean flag = false;
		// 数据初始化
		PetRegister register = new PetRegister(true);
		Operation opt = new Operation();
		opt.setOperate("普通办理");// 设置办理方式
		String regNum = register.getXfbh();

		// 长兴县用户登录系统
		LoginPage loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
		loginPage.loginAction(Variables.CXUSER, Variables.CXPWD);

		// 信访登记页面
		PetitionRegisterPage pRegisterPage = new PetitionRegisterPage(driver, Variables.BASEURL + "/petitionRegister");

		// 所有输入项必填
		pRegisterPage.add_required_field_register_action(register);

		if (driver.getCurrentUrl().indexOf("/petitionBacklog") != -1) {
			// 进入信访办理待办列表
			PetRegListPage todolistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionBacklog");
			// 查询信访编号并进行交办
			todolistPage.find_by_regNum_and_assign_action(regNum, opt);

			// 获取信访案件的状态
			String optStatus = todolistPage.get_operation_status(regNum);
			if (optStatus.indexOf("已交办") != -1) {
				flag = true;
			}
		}
		// 断言
		Assert.assertTrue(flag);
	}

	/**
	 * @Title: test_PetRegister_jd_operation
	 * @Description: 测试流程：街道登记-街道处理(不予受理告知)
	 * @throws Exception
	 */
	@Test
	public void test_PetRegister_jd_operation() throws Exception {
		logger.info("测试流程：街道登记-街道处理");
		boolean flag = false;
		// 数据初始化
		PetRegister register = new PetRegister(false);
		Operation opt = new Operation();
		opt.setOperate("不予受理告知");// 设置办理方式
		String regNum = register.getXfbh();

		// 长兴县用户登录系统
		LoginPage loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
		loginPage.loginAction(Variables.ZCUSER, Variables.ZCPWD);

		// 信访登记页面
		PetitionRegisterPage pRegisterPage = new PetitionRegisterPage(driver, Variables.BASEURL + "/petitionRegister");

		// 录入信息登记信息
		pRegisterPage.add_full_field_register_Action(false, register);

		if (driver.getCurrentUrl().indexOf("/petitionBacklog") != -1) {
			// 进入信访办理待办列表
			PetRegListPage todolistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionBacklog");
			// 查询信访编号并进行办理
			todolistPage.find_by_regNum_and_operate_action(regNum, opt);

			// 切换到已办列表
			PetRegListPage donelistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionClose");
			Thread.sleep(2000);
			// 查找案件办理状态，如果为已办结，则将断言标志设置为true
			String status = donelistPage.get_operation_status(regNum);
			if (status != null && status.indexOf("已办结") != -1) {
				flag = true;
			}
		}

		// 断言
		Assert.assertTrue(flag);
	}

	/**
	 * @Title: test_PetRegister_assign_retract
	 * @Description: 测试流程：区县登记-下发到街道-撤回
	 * @throws Exception
	 */
	@Test
	public void test_PetRegister_assign_retract() throws Exception {
		logger.info("测试流程：区县登记-下发到街道-撤回");
		boolean flag = false;
		// 数据初始化
		PetRegister register = new PetRegister(true);
		Operation opt = new Operation();
		// opt.setOperate("普通办理");// 设置办理方式
		String regNum = register.getXfbh();

		// 长兴县用户登录系统
		LoginPage loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
		loginPage.loginAction(Variables.CXUSER, Variables.CXPWD);

		// 信访登记页面
		PetitionRegisterPage pRegisterPage = new PetitionRegisterPage(driver, Variables.BASEURL + "/petitionRegister");

		// 所有输入项必填
		pRegisterPage.add_required_field_register_action(register);

		if (driver.getCurrentUrl().indexOf("/petitionBacklog") != -1) {
			// 进入信访办理待办列表
			PetRegListPage todolistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionBacklog");
			// 查询信访编号并进行交办
			todolistPage.find_by_regNum_and_assign_action(regNum, opt);
			// 查询信访编号并进行撤回
			todolistPage.retract_action(regNum);

			// 获取信访案件的状态
			String optStatus = todolistPage.get_operation_status(regNum);
			if (optStatus.indexOf("撤回") != -1) {
				flag = true;
			}
		}
		// 断言
		Assert.assertTrue(flag);
	}

	/**
	 * @Title: test_PetRegister_assign_receive
	 * @Description: 测试流程：区县登记-下发到街道-街道确认收件
	 * @throws Exception
	 */
	@Test
	public void test_PetRegister_assign_receive() throws Exception {
		logger.info("测试流程：区县登记-下发到街道-街道确认收件");
		boolean flag = false;
		// 数据初始化
		PetRegister register = new PetRegister(true);
		Operation opt = new Operation();
		// opt.setOperate("普通办理");// 设置办理方式
		String regNum = register.getXfbh();

		// 长兴县用户登录系统
		LoginPage loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
		loginPage.loginAction(Variables.CXUSER, Variables.CXPWD);

		// 信访登记页面
		PetitionRegisterPage pRegisterPage = new PetitionRegisterPage(driver, Variables.BASEURL + "/petitionRegister");

		// 所有输入项必填
		pRegisterPage.add_required_field_register_action(register);

		if (driver.getCurrentUrl().indexOf("/petitionBacklog") != -1) {
			// 进入信访办理待办列表
			PetRegListPage todolistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionBacklog");
			// 查询信访编号并进行交办
			todolistPage.find_by_regNum_and_assign_action(regNum, opt);

			// 获取信访案件的状态
			String optStatus = todolistPage.get_operation_status(regNum);
			if (optStatus.indexOf("已交办") != -1) {
				// 街道用户登录系统
				quit();
				setUp();
				// 街道用户登录系统
				loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
				loginPage.loginAction(Variables.ZCUSER, Variables.ZCPWD);

				// 进入信访办理待办列表
				todolistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionBacklog");

				// 查询信访编号并进行确认收件
				todolistPage.receive_action(regNum);

				String newStatus = todolistPage.get_operation_status(regNum);
				if (newStatus.indexOf("已收件") != -1) {
					flag = true;
				}
			}
		}
		// 断言
		Assert.assertTrue(flag);
	}

	/**
	 * @Title: test_PetRegister_assign_receive_operate
	 * @Description: 测试流程：区县登记-下发到街道-街道确认收件-街道处理
	 * @throws Exception
	 */
	@Test
	public void test_PetRegister_assign_receive_operate() throws Exception {
		logger.info("测试流程：区县登记-下发到街道-街道确认收件-街道处理");
		boolean flag = false;
		// 数据初始化
		PetRegister register = new PetRegister(true);
		Operation opt = new Operation();
		opt.setOperate("普通办理");// 设置办理方式
		String regNum = register.getXfbh();

		// 长兴县用户登录系统
		LoginPage loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
		loginPage.loginAction(Variables.CXUSER, Variables.CXPWD);

		// 信访登记页面
		PetitionRegisterPage pRegisterPage = new PetitionRegisterPage(driver, Variables.BASEURL + "/petitionRegister");

		// 所有输入项必填
		pRegisterPage.add_required_field_register_action(register);

		if (driver.getCurrentUrl().indexOf("/petitionBacklog") != -1) {
			// 进入信访办理待办列表
			PetRegListPage todolistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionBacklog");
			// 查询信访编号并进行交办
			todolistPage.find_by_regNum_and_assign_action(regNum, opt);

			// 获取信访案件的状态
			String optStatus = todolistPage.get_operation_status(regNum);
			if (optStatus.indexOf("已交办") != -1) {
				// 街道用户登录系统
				quit();
				setUp();
				// 街道用户登录系统
				loginPage = new LoginPage(driver, Variables.BASEURL + "/login");
				loginPage.loginAction(Variables.ZCUSER, Variables.ZCPWD);

				// 进入信访办理待办列表
				todolistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionBacklog");

				// 查询信访编号并进行确认收件
				todolistPage.receive_action(regNum);

				// 查询信访编号进行处理操作
				todolistPage.find_by_regNum_and_operate_action(regNum, opt);

				// 切换到已办列表
				PetRegListPage donelistPage = new PetRegListPage(driver, Variables.BASEURL + "/petitionClose");
				Thread.sleep(2000);
				// 查找案件办理状态，如果为已办结，则将断言标志设置为true
				String status = donelistPage.get_operation_status(regNum);
				if (status != null && status.indexOf("已办结") != -1) {
					flag = true;
				}
			}
		}
		// 断言
		Assert.assertTrue(flag);
	}

	@BeforeClass
	public void beforeClass() {
	}

	@BeforeMethod
	public void beforeMethod() throws Exception {
		setUp();
		// LoginPage loginPage = new LoginPage(driver, Variables.BASEURL +
		// "/login");
		// loginPage.loginAction(Variables.CXUSER, Variables.CXPWD);
	}

	@AfterMethod
	public void afterMethod() {
		quit();
	}
}
