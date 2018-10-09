package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import config.Variables;
import entity.DCInfo;
import entity.Operation;

/**
 * @Title: PetRegReadPage.java
 * @Package pageobject
 * @Description: 信访信息查看页面
 * @author lanfangz
 * @date 2017年9月7日 下午4:25:30
 * @version V1.0
 */
public class PetRegReadPage extends BasePage {

	/**
	 * Title: 构造方法
	 * 
	 * @param driver
	 */
	public PetRegReadPage(WebDriver driver) {
		super(driver);

	}

	/**
	 * @Title: getHeadTab
	 * @Description: 查找页签栏元素
	 * @return
	 * @throws Exception
	 */
	public WebElement getHeadTab() throws Exception {
		return super.elementWait(Variables.WAITTIME, By.cssSelector("div.index_content div.home-title ul#editTab"));
	}

	/**
	 * @Title: choseNavigationBars
	 * @Description: 根据子页签名称选中该页签
	 * @param navigation
	 * @throws Exception
	 */
	protected void choseNavigationBars(String navigation) throws Exception {
		logger.info("打开信息查看页面，根据标签名称打开子标签页面");
		List<WebElement> headers = getHeadTab().findElements(By.tagName("a"));
		switch (navigation) {
		case "基本信息":
			headers.get(0).click();
			break;
		case "办理信息":
			headers.get(1).click();
			break;
		case "满意度":
			headers.get(2).click();
			break;
		case "督查情况":
			headers.get(3).click();
			break;
		default:
			logger.error("要打开的子页面不存在");
			break;
		}
	}

	/**
	 * @Title: clickBackBut
	 * @Description: 点击返回按钮
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void clickBackBut() throws Exception {
		getHeadTab().findElement(By.tagName("button")).click();
	}

	/**
	 * @Title: getDetailPage
	 * @Description: 查找详细页面元素
	 * @return
	 * @throws Exception
	 */
	public WebElement getDetailPage() throws Exception {
		return super.elementWait(Variables.WAITTIME,
				By.cssSelector("div.index_content div#detail form[name='petitionForm']"));
	}

	/**
	 * @Title: getOperateTable
	 * @Description: 找到处理子页面
	 * @return
	 * @throws Exception
	 */
	private WebElement getOperateTable() throws Exception {
		return getDetailPage()
				.findElement(By.cssSelector("#ach_management table[ng-show=\"VerifiedInfoState=='处理'\"]"));
	}

	/**
	 * @Title: choseOperationType
	 * @Description: 根据选择方式不同进行选择
	 * @param operation
	 * @throws Exception
	 */
	private void choseOperationType(String operation) throws Exception {
		logger.info("选择处理方式：" + operation);
		switch (operation.trim()) {
		case "":
			getOperateTable().findElement(By.id("dealType0")).click();
			break;
		case "普通办理":
			getOperateTable().findElement(By.id("dealType0")).click();
			break;
		case "简易答复":
			getOperateTable().findElement(By.id("dealType1")).click();
			break;
		case "不予受理告知":
			getOperateTable().findElement(By.id("dealType2")).click();
			break;
		case "不再受理告知":
			getOperateTable().findElement(By.id("dealType3")).click();
			break;
		case "转纪委告知":
			getOperateTable().findElement(By.id("dealType4")).click();
			break;
		case "其他法定途径告知":
			getOperateTable().findElement(By.id("dealType5")).click();
			break;
		default:
			logger.error("选择的处理方式不存在：" + operation);
			break;
		}
	}

	/**
	 * @Title: setOperateContent
	 * @Description: 设置处理意见
	 * @param content
	 * @throws Exception
	 */
	private void setOperateContent(String content) throws Exception {
		getOperateTable().findElement(By.id("verifiedContent")).sendKeys(content);
	}

	/**
	 * @Title: setSHContent
	 * @Description: 设置审核意见
	 * @param content
	 * @throws Exception
	 */
	private void setSHContent(String content) throws Exception {
		getOperateTable().findElement(By.id("auditOpinion")).sendKeys(content);
	}

	/**
	 * @Title: setSHUser
	 * @Description: 设置审核人
	 * @param username
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void setSHUser(String username) throws Exception {
		getOperateTable().findElement(By.id("auditStaff")).sendKeys(username);
	}

	/**
	 * @Title: clickUploadBut
	 * @Description: 点击上传按钮
	 * @throws Exception
	 */
	public void clickUploadBut() throws Exception {
		getOperateTable().findElement(By.tagName("button")).click();
	}

	/**
	 * @Title: getAssignTable
	 * @Description: 找到交办子页面
	 * @return
	 * @throws Exception
	 */
	private WebElement getAssignTable() throws Exception {
		return getDetailPage()
				.findElement(By.cssSelector("#ach_management table[ng-show=\"VerifiedInfoState=='交办'\"]"));
	}

	/**
	 * @Title: setAssignContent
	 * @Description: 设置交办意见
	 * @throws Exception
	 */
	private void setAssignContent(String content) throws Exception {
		// String content = "交办意见：" + System.currentTimeMillis();
		getAssignTable().findElement(By.tagName("textarea")).sendKeys(content);
	}

	/**
	 * @Title: chosePTCX
	 * @Description: 选择普通程序
	 * @throws Exception
	 */
	private void chosePTCX() throws Exception {
		getAssignTable().findElement(By.id("dealType_PTCX")).click();
	}

	/**
	 * @Title: choseJYCX
	 * @Description: 选择简易程序
	 * @throws Exception
	 */
	private void choseJYCX() throws Exception {
		getAssignTable().findElement(By.id("dealType_JYCX")).click();
	}

	/**
	 * @Title: choseSponsor
	 * @Description: 设置主办人
	 * @param spString
	 * @throws Exception
	 */
	private void choseSponsor(String spString) throws Exception {
		Select sponsor = new Select(getAssignTable().findElement(By.id("Entrepreneur")));
		sponsor.selectByVisibleText(spString);
	}

	/**
	 * @Title: choseCoordinator
	 * @Description: 设置协办人
	 * @param coString
	 * @throws Exception
	 */
	private void choseCoordinator(String coString) throws Exception {
		// WebElement coordinator =
		// getAssignTable().findElement(By.id("Coordinator"));
		String jString = "$(\"#Coordinator span button:first\").empty();$(\"#Coordinator span button:first\").append('<div class=\"buttonLabel\">"
				+ coString + "<span class=\"caret\"></span></div>');";
		super.executJs(jString);
		Thread.sleep(2000);
	}

	/**
	 * @Title: getStatisTable
	 * @Description: 找到满意度子页签
	 * @return
	 * @throws Exception
	 */
	private WebElement getStatisTable() throws Exception {
		return getDetailPage().findElement(By.cssSelector("#ach_management table.Satisfaction_table"));
	}

	/**
	 * @Title: setVisitPeople
	 * @Description: 录入回访人
	 * @param uName
	 * @throws Exception
	 */
	private void setVisitPeople() throws Exception {
		getDetailPage().findElement(By.id("VisitPeople")).sendKeys("回访人");
	}

	/**
	 * @Title: setVisitResult
	 * @Description: 选择评价结果
	 * @param result
	 * @throws Exception
	 */
	private void setVisitResult(String result) throws Exception {
		Select viSelect = new Select(getDetailPage().findElement(By.id("EvaluationResults")));
		switch (result) {
		case "信访满意":
			viSelect.selectByIndex(0);
			break;
		case "信访基本满意":
			viSelect.selectByIndex(1);
			break;
		case "信访不满意":
			viSelect.selectByIndex(2);
			break;
		case "信访其他":
			viSelect.selectByIndex(3);
			break;
		default:
			logger.error("输入的评价结果不存在", result);
			break;
		}
	}

	/**
	 * @Title: setInstruction1
	 * @Description: 设置回访说明1
	 * @throws Exception
	 */
	private void setInstruction1() throws Exception {
		String content = "回访说明1" + System.currentTimeMillis();
		getDetailPage().findElement(By.id("Instruction1")).sendKeys(content);
	}

	/**
	 * @Title: setInstruction2
	 * @Description: 设置回访说明2
	 * @throws Exception
	 */
	private void setInstruction2() throws Exception {
		String content = "回访说明2" + System.currentTimeMillis();
		getDetailPage().findElement(By.id("Instruction2")).sendKeys(content);
	}

	/**
	 * @Title: setInstruction3
	 * @Description: 设置回访说明3
	 * @throws Exception
	 */
	private void setInstruction3() throws Exception {
		String content = "回访说明3" + System.currentTimeMillis();
		getDetailPage().findElement(By.id("Instruction3")).sendKeys(content);
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
	 * @Title: clickSaveBut
	 * @Description: 点击保存按钮
	 * @throws Exception
	 */
	public void clickSaveBut() throws Exception {
		getDetailPage().findElement(By.cssSelector("div#ach_management div.case_operation button#btnSubmit1")).click();
	}

	/**
	 * @Title: operate_action
	 * @Description: 处理操作
	 * @param opt
	 * @throws Exception
	 */
	public void operate_action(Operation opt) throws Exception {
		logger.info("开始办理");
		// 选中子页签
		choseNavigationBars(opt.navigation);
		Thread.sleep(2000);
		// 选择处理方式
		choseOperationType(opt.operate);
		// 录入处理意见
		setOperateContent(opt.content.concat(opt.operate));
		// 录入审核意见
		setSHContent(opt.content);
		// 录入审核人员
		// setSHUser("张兰芳");
		// 如果附件名不为空，则上传附件
		if (!opt.fileName.equals("")) {
			UploadPage uPage = new UploadPage(driver);
			clickUploadBut();
			uPage.upload_file_action(opt.fileName);
		}
		clickSaveBut();
		Thread.sleep(2000);
	}

	/**
	 * @Title: add_CSR_action
	 * @Description: 增加满意度操作
	 * @param opt
	 * @throws Exception
	 */
	public void add_CSR_action(Operation opt) throws Exception {
		logger.info("开始新增满意度");
		getStatisTable();// 选择满意度子页签
		Thread.sleep(2000);
		// 选中子页签
		choseNavigationBars(opt.navigation);
		Thread.sleep(2000);
		setVisitPeople();// 设置回访人
		setVisitResult(opt.VisitResult);// 回访结果
		setInstruction1();// 回访说明
		setInstruction2();
		setInstruction3();
		clickDcSaveBut();// 点击保存按钮
		Thread.sleep(3000);
	}

	/**
	 * @Title: add_DC_action
	 * @Description: 添加督查
	 * @param opt
	 * @throws Exception
	 */
	public void add_DCInfo_action(Operation opt, DCInfo dcInfo) throws Exception {
		logger.info("开始新增督查任务");
		add_CSR_action(opt);// 新增满意度
		clickOKBut();// 是否进行督查提示页面点击确定
		DCPage dcPage = new DCPage(driver);
		dcPage.add_dc_action(dcInfo);// 添加督查操作
	}

	/**
	 * @Title: assign_jd_action
	 * @Description: 交办操作
	 * @param opt
	 * @throws Exception
	 */
	public void assign_jd_action(Operation opt) throws Exception {
		logger.info("开始交办");
		// 选中子页签
		choseNavigationBars(opt.navigation);
		Thread.sleep(2000);
		// 选择办理程序--普通程序、简易程序
		if (opt.transHandleType.equals("普通程序")) {
			chosePTCX();
		} else {
			choseJYCX();
		}
		choseSponsor(opt.sponsor);// 设置主办人
		// 协办人有值时，设置协办人
		if (opt.coordinator!="" && opt.coordinator != null) {
			choseCoordinator(opt.coordinator);
		}
		// 录入领导批示意见
		setAssignContent(opt.content.concat(opt.operate));
		clickSaveBut();
		Thread.sleep(2000);
	}
}
