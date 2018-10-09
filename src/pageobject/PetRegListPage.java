package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import config.Variables;
import entity.DCInfo;
import entity.Operation;

/**
 * @Title: PetRegTodolistPage.java
 * @Package pageobject
 * @Description: 待办列表
 * @author lanfangz
 * @date 2017年9月4日 下午1:01:53
 * @version V1.0
 */
public class PetRegListPage extends BasePage {
	public PetRegReadPage regReadPage = null;

	/**
	 * Title: 构造方法
	 * 
	 * @param driver
	 * @param url
	 */
	public PetRegListPage(WebDriver driver, String url) {
		super(driver, url);
		regReadPage = new PetRegReadPage(driver);
		// 如果不在当前页面，则切换到当前页面
		if (!driver.getCurrentUrl().equals(url)) {
			super.open();
		}
	}

	/**
	 * @Title: setRegNum
	 * @Description: 设置信访编号查询条件
	 * @param regNum
	 * @throws Exception
	 */
	private void setRegNum(String regNum) throws Exception {
		super.clearAndSendKeys(By.cssSelector("#Table input[ng-model='Number']"), regNum);
	}

	/**
	 * @Title: clickSearchBut
	 * @Description: 点击搜索按钮
	 * @throws Exception
	 */
	private void clickSearchBut() throws Exception {
		super.clickElem(By.id("btnSearch"));
	}

	/**
	 * @Title: findTbodyEle
	 * @Description: 查询信访列表元素
	 * @return
	 * @throws Exception
	 */
	private WebElement findTbodyEle() throws Exception {
		return super.elementWait(Variables.WAITTIME, By.cssSelector("#petitionTable tbody"));
	}

	/**
	 * @Title: find_petReg_by_xfNum
	 * @Description: 通过信访编号查找信访记录，然后将查找的行元素返回
	 * @param xfNum
	 * @return
	 * @throws Exception
	 */
	public WebElement find_petReg_by_xfNum(String xfNum) throws Exception {
		WebElement roWebElement = null;
		List<WebElement> rows = findTbodyEle().findElements(By.tagName("tr"));
		for (WebElement row : rows) {
			String xfId = row.findElements(By.tagName("td")).get(3).getAttribute("title");
			if (xfId.trim().equals(xfNum)) {
				roWebElement = row;
			}
		}
		return roWebElement;
	}

	/**
	 * @Title: get_operation_status
	 * @Description: 查找信访记录的办理状态
	 * @param regNum
	 * @return
	 * @throws Exception
	 */
	public String get_operation_status(String regNum) throws Exception {
		// 根据信访编号进行查询
		search_by_regNum_action(regNum);
		// 从列表中进行查找
		WebElement row = find_petReg_by_xfNum(regNum);
		// 返回信访案件的状态
		return row.findElements(By.tagName("td")).get(5).getAttribute("title");
	}

	/**
	 * @Title: search_by_regNum_action
	 * @Description: 根据信访编号进行查询操作
	 * @param regNum
	 * @throws Exception
	 */
	public void search_by_regNum_action(String regNum) throws Exception {
		setRegNum(regNum);// 设置信访编号查询条件
		Thread.sleep(2000);
		clickSearchBut();// 点击查询按钮
		Thread.sleep(3000);
	}

	/**
	 * @Title: operate_on_rowEle
	 * @Description: 根据传入的参数来操作行元素
	 * @param row
	 * @param operate
	 * @throws Exception
	 */
	private void operate_on_rowEle(WebElement row, String operate) throws Exception {
		String opt = String.format("input[value='%s']", operate);// 格式化定义操作字符串的定位方法
		WebElement tdOperation = row.findElements(By.tagName("td")).get(11);// 找到当前传入行元素，操作所在的列
		tdOperation.findElement(By.cssSelector(opt)).click();
	}

	/**
	 * @Title: find_by_regNum_and_operate_action
	 * @Description: 根据信访编号查找信访记录，并进行处理
	 * @param regNum
	 * @param opt
	 * @throws Exception
	 */
	public void find_by_regNum_and_operate_action(String regNum, Operation opt) throws Exception {
		logger.info("查找信访记录开始处理");
		// 查找信访案件
		search_by_regNum_action(regNum);
		// 从列表查找信访编号所在行，并点击处理按钮
		operate_on_rowEle(find_petReg_by_xfNum(regNum), "处理");
		// Thread.sleep(1000);
		opt.setNavigation("办理信息");
		regReadPage.operate_action(opt);
	}

	/**
	 * @Title: find_by_regNum_and_assign_action
	 * @Description: 根据信访编号查找信访记录，并进行交办
	 * @param regNum
	 * @param opt
	 * @throws Exception
	 */
	public void find_by_regNum_and_assign_action(String regNum, Operation opt) throws Exception {
		logger.info("查找信访记录开始交办");
		// 查找信访案件
		search_by_regNum_action(regNum);
		// 从列表查找信访编号所在行，并点击处理按钮
		operate_on_rowEle(find_petReg_by_xfNum(regNum), "交办");
		Thread.sleep(3000);
		opt.setNavigation("办理信息");
		regReadPage.assign_jd_action(opt);
	}

	/**
	 * @Title: retract_action
	 * @Description: 根据信访编号查找信访记录，并进行撤回
	 * @param regNum
	 * @throws Exception
	 */
	public void retract_action(String regNum) throws Exception {
		logger.info("撤回交办案件");
		// 查找信访案件
		search_by_regNum_action(regNum);
		// 从列表查找信访编号所在行，并点击撤回按钮
		operate_on_rowEle(find_petReg_by_xfNum(regNum), "撤回");
		Thread.sleep(2000);
		clickOKBut();// 点击撤回确定按钮
		Thread.sleep(3000);
	}

	/**
	 * @Title: receive_action
	 * @Description: 确认收件操作
	 * @param regNum
	 * @throws Exception
	 */
	public void receive_action(String regNum) throws Exception {
		logger.info("区县确认收件操作");
		// 查找信访案件
		search_by_regNum_action(regNum);
		// 从列表查找信访编号所在行，并点击撤回按钮
		operate_on_rowEle(find_petReg_by_xfNum(regNum), "确认收件");
		Thread.sleep(2000);
		clickOKBut();// 点击确认收件确认按钮
		Thread.sleep(3000);
	}

	/**
	 * @Title: find_by_regNum_and_add_CSR
	 * @Description: 已办列表进入，添加满意度操作
	 * @param regNum
	 * @param opt
	 * @throws Exception
	 */
	public void find_by_regNum_and_add_CSR(String regNum, Operation opt) throws Exception {
		logger.info("添加满意度操作");
		// 查找信访案件
		search_by_regNum_action(regNum);
		// 从列表查找信访编号所在行，并点击查看按钮
		operate_on_rowEle(find_petReg_by_xfNum(regNum), "查看");
		Thread.sleep(2000);
		opt.setNavigation("满意度");
		regReadPage.add_CSR_action(opt);
	}

	/**
	 * @Title: find_by_regNum_and_add_DCinfo
	 * @Description: 督查结果不满意，添加督查信息
	 * @param regNum
	 * @param opt
	 * @param dcInfo
	 * @throws Exception
	 */
	public void find_by_regNum_and_add_DCinfo(String regNum, Operation opt, DCInfo dcInfo) throws Exception {
		logger.info("添加督查信息操作");
		// 查找信访案件
		search_by_regNum_action(regNum);
		// 从列表查找信访编号所在行，并点击查看按钮
		operate_on_rowEle(find_petReg_by_xfNum(regNum), "查看");
		Thread.sleep(2000);
		opt.setNavigation("满意度");
		opt.setVisitResult("信访不满意");
		regReadPage.add_DCInfo_action(opt, dcInfo);
	}

}
