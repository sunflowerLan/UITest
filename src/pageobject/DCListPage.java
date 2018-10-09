package pageobject;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import config.Variables;
import entity.DCInfo;
import entity.Operation;

/**
 * @Title: DCListPage.java
 * @Package pageobject
 * @Description: 督查列表元素及操作
 * @author lanfangz
 * @date 2017年9月13日 下午5:23:45
 * @version V1.0
 */
public class DCListPage extends BasePage {

	public DCReadPage dcReadPage = null;

	/**
	 * Title: 构造方法
	 * 
	 * @param driver
	 * @param url
	 */
	public DCListPage(WebDriver driver, String url) {
		super(driver, url);
		dcReadPage = new DCReadPage(driver);
		// 如果不在当前页面，则切换到当前页面
		if (!driver.getCurrentUrl().equals(url)) {
			super.open();
		}
	}

	/**
	 * @Title: setDcNum
	 * @Description: 设置督查编号查询条件
	 * @param dcNum
	 * @throws Exception
	 */
	private void setDcNum(String dcNum) throws Exception {
		super.clearAndSendKeys(By.cssSelector("div#inspecton_table input[ng-model='SupervisesNumber']"), dcNum);
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
	 * @Description: 查询督查列表元素
	 * @return
	 * @throws Exception
	 */
	private WebElement findTbodyEle() throws Exception {
		return super.elementWait(Variables.WAITTIME, By.cssSelector("#petitionTable tbody"));
	}

	/**
	 * @Title: find_row_by_dcNum
	 * @Description: 通过督查编号查找督查记录，然后将查找的行元素返回
	 * @param dcNum
	 * @return
	 * @throws Exception
	 */
	public WebElement find_row_by_dcNum(String dcNum) throws Exception {
		WebElement roWebElement = null;
		List<WebElement> rows = findTbodyEle().findElements(By.tagName("tr"));
		for (WebElement row : rows) {
			String xfId = row.findElements(By.tagName("td")).get(4).getAttribute("title");
			if (xfId.trim().equals(dcNum)) {
				roWebElement = row;
			}
		}
		return roWebElement;
	}

	/**
	 * @Title: get_operation_status
	 * @Description: 查找督查记录的办理状态
	 * @param dcNum
	 * @return
	 * @throws Exception
	 */
	public String get_operation_status(String dcNum) throws Exception {
		// 根据督查编号进行查询
		search_by_dcNum_action(dcNum);
		// 从列表中进行查找
		WebElement row = find_row_by_dcNum(dcNum);
		// 返回督查案件的状态
		return row.findElements(By.tagName("td")).get(6).getAttribute("title");
	}

	/**
	 * @Title: search_by_dcNum_action
	 * @Description: 根据督查编号进行查询操作
	 * @param dcNum
	 * @throws Exception
	 */
	public void search_by_dcNum_action(String dcNum) throws Exception {
		setDcNum(dcNum);// 设置督查编号查询条件
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
		WebElement tdOperation = row.findElements(By.tagName("td")).get(12);// 找到当前传入行元素，操作所在的列
		tdOperation.findElement(By.cssSelector(opt)).click();
	}

	/**
	 * @Title: find_by_dcNum_and_operate_action
	 * @Description: 根据督查编号查找督查记录，并进行处理
	 * @param dcNum
	 * @param opt
	 * @throws Exception
	 */
	public void find_by_dcNum_and_operate_action(String dcNum, Operation opt) throws Exception {
		logger.info("查找督查记录开始处理");
		// 查找督查案件
		search_by_dcNum_action(dcNum);
		// 从列表查找督查编号所在行，并点击处理按钮
		operate_on_rowEle(find_row_by_dcNum(dcNum), "处理");
		// 打开办理信息页签
		opt.setNavigation("办理信息");
		// 进行办理操作
		dcReadPage.dc_operate_action(opt);
	}

	/**
	 * @Title: find_by_dcNum_and_assign_action
	 * @Description: 根据督查编号查找督查记录，并进行交办
	 * @param dcNum
	 * @param opt
	 * @throws Exception
	 */
	public void find_by_dcNum_and_assign_action(String dcNum, Operation opt) throws Exception {
		logger.info("查找督查记录开始交办");
		// 查找督查案件
		search_by_dcNum_action(dcNum);
		// 从列表查找督查编号所在行，并点击处理按钮
		operate_on_rowEle(find_row_by_dcNum(dcNum), "交办");
		Thread.sleep(3000);
		opt.setNavigation("办理信息");
		dcReadPage.assign_jd_action(opt);//督查交办
	}

	/**
	 * @Title: retract_action
	 * @Description: 根据督查编号查找督查记录，并进行撤回
	 * @param dcNum
	 * @throws Exception
	 */
	public void retract_DC_action(String dcNum) throws Exception {
		logger.info("撤回督查交办案件");
		// 查找督查案件
		search_by_dcNum_action(dcNum);
		// 从列表查找督查编号所在行，并点击撤回按钮
		operate_on_rowEle(find_row_by_dcNum(dcNum), "撤回");
		Thread.sleep(2000);
		clickOKBut();// 点击撤回确定按钮
		Thread.sleep(3000);
	}

	/**
	 * @Title: receive_action
	 * @Description: 确认收件操作
	 * @param dcNum
	 * @throws Exception
	 */
	public void receive_DC_action(String dcNum) throws Exception {
		logger.info("区县确认收件操作");
		// 查找督查案件
		search_by_dcNum_action(dcNum);
		// 从列表查找督查编号所在行，并点击撤回按钮
		operate_on_rowEle(find_row_by_dcNum(dcNum), "确认收件");
		Thread.sleep(2000);
		clickOKBut();// 点击确认收件确认按钮
		Thread.sleep(3000);
	}

	/**
	 * @Title: find_by_dcNum_and_add_CSR
	 * @Description: 已办列表进入，添加满意度操作
	 * @param dcNum
	 * @param opt
	 * @throws Exception
	 */
	public void find_by_dcNum_and_add_CSR(String dcNum, Operation opt) throws Exception {
		logger.info("添加满意度操作");
		// 查找督查案件
		search_by_dcNum_action(dcNum);
		// 从列表查找督查编号所在行，并点击查看按钮
		operate_on_rowEle(find_row_by_dcNum(dcNum), "查看");
		Thread.sleep(2000);
		opt.setNavigation("满意度");
		dcReadPage.add_DC_CSR_action(opt);
	}

	/**
	 * @Title: find_by_dcNum_and_add_DCinfo
	 * @Description: 督查结果不满意，添加督查信息
	 * @param dcNum
	 * @param opt
	 * @param dcInfo
	 * @throws Exception
	 */
	public void find_by_dcNum_and_add_DCinfo(String dcNum, Operation opt, DCInfo dcInfo) throws Exception {
		logger.info("添加督查信息操作");
		// 查找督查案件
		search_by_dcNum_action(dcNum);
		// 从列表查找督查编号所在行，并点击查看按钮
		operate_on_rowEle(find_row_by_dcNum(dcNum), "查看");
		Thread.sleep(2000);
		opt.setNavigation("满意度");
		opt.setVisitResult("督查不满意");
		dcReadPage.add_DCInfo_action(opt, dcInfo);
	}
}
