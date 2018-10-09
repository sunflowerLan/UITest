package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import config.Variables;
import entity.DCInfo;
import entity.Operation;

/**
 * @Title: DCReadPage.java
 * @Package pageobject
 * @Description: 督查信息查看页面
 * @author lanfangz
 * @date 2017年9月14日 上午11:14:33
 * @version V1.0
 */
public class DCReadPage extends PetRegReadPage {

	/**
	 * Title: 构造方法
	 * 
	 * @param driver
	 */
	public DCReadPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @Title: getHeadTab
	 * @Description: 查找页签栏元素
	 * @return
	 * @throws Exception
	 */
	@Override
	public WebElement getHeadTab() throws Exception {
		return super.elementWait(Variables.WAITTIME, By.cssSelector("div.index_content ul#supervises_editTab"));
	}

	/**
	 * @Title: getDetailPage
	 * @Description: 查找详细页面元素
	 * @return
	 * @throws Exception
	 */
	@Override
	public WebElement getDetailPage() throws Exception {
		return super.elementWait(Variables.WAITTIME,
				By.cssSelector("div.index_content div#supervises_detail form[name='petitionForm']"));
	}

	/**
	 * @Title: getReportTable
	 * @Description: 找到汇报子页面
	 * @return
	 * @throws Exception
	 */
	private WebElement getReportTable() throws Exception {
		return getDetailPage()
				.findElement(By.cssSelector("#ach_management table[ng-show=\"VerifiedInfoState=='汇报'\"]"));
	}

	/**
	 * @Title: setReportLeader
	 * @Description: 设置审批领导
	 * @param leader
	 * @throws Exception
	 */
	private void setReportLeader(String leader) throws Exception {
		getReportTable().findElement(By.id("SupervisesSpld")).sendKeys(leader);
	}

	/**
	 * @Title: setReportContent
	 * @Description: 设置审批意见
	 * @param content
	 * @throws Exception
	 */
	private void setReportContent(String content) throws Exception {
		getReportTable().findElement(By.id("SupervisesSqyj")).sendKeys(content);
	}

	/**
	 * @Title: setReportText
	 * @Description: 设置汇报内容
	 * @param text
	 * @throws Exception
	 */
	private void setReportText(String text) throws Exception {
		getReportTable().findElement(By.id("SupervisesHbnr")).sendKeys(text);
	}

	/**
	 * @Title: getStatisTable
	 * @Description: 找到督查回访table元素
	 * @return
	 * @throws Exception
	 */
	private WebElement getDCStatisTable() throws Exception {
		return getDetailPage().findElements(By.cssSelector("#ach_management table.Satisfaction_table")).get(1);
	}

	/**
	 * @Title: setVisitPeople
	 * @Description: 录入督查回访人
	 * @param uName
	 * @throws Exception
	 */
	private void setDCVisitPeople() throws Exception {
		getDCStatisTable().findElement(By.id("IVisitPeople")).sendKeys("督查回访人");
	}

	/**
	 * @Title: clickUploadBut
	 * @Description: 点击上传按钮
	 * @throws Exception
	 */
	public void clickUploadBut() throws Exception {
		getReportTable().findElement(By.tagName("button")).click();
	}

	/**
	 * @Title: clickSaveBut
	 * @Description: 点击保存按钮
	 * @throws Exception
	 */
	public void clickSaveBut() throws Exception {
		getDetailPage().findElement(By.cssSelector("div#ach_management div.case_operation button#factSubmit")).click();
	}

	/**
	 * @Title: setVisitResult
	 * @Description: 选择督查评价结果
	 * @param result
	 * @throws Exception
	 */
	private void setDCVisitResult(String result) throws Exception {
		Select viSelect = new Select(getDCStatisTable().findElement(By.id("IEvaluationResults")));
		switch (result) {
		case "督办满意":
			viSelect.selectByIndex(0);
			break;
		case "督办基本满意":
			viSelect.selectByIndex(1);
			break;
		case "督办不满意":
			viSelect.selectByIndex(2);
			break;
		case "督办其他":
			viSelect.selectByIndex(3);
			break;
		default:
			logger.error("输入的评价结果不存在", result);
			break;
		}
	}

	/**
	 * @Title: setInstruction1
	 * @Description: 设置督查回访说明1
	 * @throws Exception
	 */
	private void setDCInstruction1() throws Exception {
		String content = "督查回访说明1" + System.currentTimeMillis();
		getDCStatisTable().findElement(By.id("IInstruction1")).sendKeys(content);
	}

	/**
	 * @Title: setInstruction2
	 * @Description: 设置督查回访说明2
	 * @throws Exception
	 */
	private void setDCInstruction2() throws Exception {
		String content = "督查回访说明2" + System.currentTimeMillis();
		getDCStatisTable().findElement(By.id("IInstruction2")).sendKeys(content);
	}

	/**
	 * @Title: setInstruction3
	 * @Description: 设置督查回访说明3
	 * @throws Exception
	 */
	private void setDCInstruction3() throws Exception {
		String content = "督查回访说明3" + System.currentTimeMillis();
		getDCStatisTable().findElement(By.id("IInstruction3")).sendKeys(content);
	}

	/**
	 * @Title: clickDcSaveBut
	 * @Description: 点击督查保存按钮
	 * @throws Exception
	 */
	private void clickDcSaveBut() throws Exception {
		getDetailPage().findElement(By.id("factSubmit")).click();
	}

	/**
	 * @Title: operate_action
	 * @Description: 督查处理操作
	 * @param opt
	 * @throws Exception
	 */
	public void dc_operate_action(Operation opt) throws Exception {
		logger.info("开始办理督查");
		// 选中子页签
		choseNavigationBars(opt.navigation);
		Thread.sleep(2000);
		// 设置审批领导
		setReportLeader(opt.spUser);
		// 录入审批意见
		setReportContent(opt.content);
		// 设置汇报内容
		setReportText("汇报内容：" + System.currentTimeMillis());
		// 如果附件名不为空，则上传附件
		if (opt.fileName != null && opt.fileName != "") {
			UploadPage uPage = new UploadPage(driver);
			clickUploadBut();
			uPage.upload_file_action(opt.fileName);
		}
		clickSaveBut();
		Thread.sleep(2000);
	}

	/**
	 * @Title: add_DC_CSR_action
	 * @Description: 增加督查满意度操作
	 * @param opt
	 * @throws Exception
	 */
	public void add_DC_CSR_action(Operation opt) throws Exception {
		logger.info("开始新增督查满意度");
		// getStatisTable();// 选择满意度子页签
		Thread.sleep(2000);
		// 选中子页签
		choseNavigationBars(opt.navigation);
		Thread.sleep(2000);
		setDCVisitPeople();// 设置DC回访人
		setDCVisitResult(opt.VisitResult);// DC回访结果
		setDCInstruction1();// DC回访说明
		setDCInstruction2();
		setDCInstruction3();
		clickDcSaveBut();// 点击DC保存按钮
		Thread.sleep(3000);
	}

	/**
	 * @Title: add_DCInfo_action_again
	 * @Description: 对督查不满意案件再次添加督查
	 * @param opt
	 * @throws Exception
	 */
	public void add_DCInfo_action_again(Operation opt, DCInfo dcInfo) throws Exception {
		logger.info("对督查不满意案件再次添加督查任务");
		add_DC_CSR_action(opt);// 新增督查满意度
		clickOKBut();// 是否进行督查提示页面点击确定
		DCPage dcPage = new DCPage(driver);
		dcPage.add_dc_action(dcInfo);// 添加督查操作
	}

}
