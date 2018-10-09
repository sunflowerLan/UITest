package pageobject;

import java.io.File;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import config.Variables;
import entity.PetRegister;

/**
 * @Title: petitionRegisterPage.java
 * @Package pageobject
 * @Description: 信访登记页面
 * @author lanfangz
 * @date 2017年8月31日 下午12:15:56
 * @version V1.0
 */
public class PetitionRegisterPage extends BasePage {

	/**
	 * Title: 构造方法
	 * 
	 * @param driver
	 * @param url
	 */
	public PetitionRegisterPage(WebDriver driver, String url) {
		super(driver, url);
		super.open();
	}

	/**
	 * @Title: setXFID
	 * @Description: 设置信访编号
	 * @param xfid
	 * @throws Exception
	 */
	private void setXFID(String xfid) throws Exception {
		super.clearAndSendKeys(By.id("XFJBH_Edit"), xfid);
	}

	/**
	 * @Title: setXfxs
	 * @Description: 设置信访方式
	 * @param xfxs
	 * @throws Exception
	 */
	private void setXfxs(String xfxs) throws Exception {
		Select xftype = new Select(super.elementWait(Variables.WAITTIME, By.id("XFSX_Edit")));
		if (xfxs.equals("")) {
			xftype.selectByIndex(random.nextInt(xftype.getOptions().size()));
		} else {
			xftype.selectByVisibleText(xfxs);
		}
	}

	/**
	 * @Title: setXfname
	 * @Description: 设置信访人姓名
	 * @param name
	 * @throws Exception
	 */
	private void setXfname(String name) throws Exception {
		super.clearAndSendKeys(By.id("XM_Edit"), name);
	}

	/**
	 * @Title: setXfjd
	 * @Description: 设置信访街道
	 * @param jdname
	 * @throws Exception
	 */
	private void setXfjd() throws Exception {
		Select xfjd = new Select(super.elementWait(Variables.WAITTIME, By.id("petition_city")));
		xfjd.selectByIndex(random.nextInt(xfjd.getOptions().size()));
	}

	/**
	 * @Title: setXfc
	 * @Description: 设置信访村
	 * @param cname
	 * @throws Exception
	 */
	private void setXfc() throws Exception {
		Select xfc = new Select(super.elementWait(Variables.WAITTIME, By.id("petition_district")));
		xfc.selectByIndex(random.nextInt(xfc.getOptions().size()));
	}

	/**
	 * @Title: setXfaddress
	 * @Description: 设置信访人详细地址
	 * @throws Exception
	 */
	private void setXfaddress() throws Exception {
		super.clearAndSendKeys(By.id("petition_address"), "信访人详细地址");
	}

	/**
	 * @Title: setIdentifyType
	 * @Description: 设置证件类型
	 * @throws Exception
	 */
	private void setIdentifyType() throws Exception {
		// random = new Random();
		Select idType = new Select(super.elementWait(Variables.WAITTIME, By.id("identifyCardType")));
		idType.selectByIndex(random.nextInt(idType.getOptions().size()));
	}

	/**
	 * @Title: setIdentifyNum
	 * @Description: 设置证件号码
	 * @throws Exception
	 */
	private void setIdentifyNum() throws Exception {
		super.clearAndSendKeys(By.id("identifyCardNumber"), "" + System.currentTimeMillis());
	}

	/**
	 * @Title: setTelephton
	 * @Description: 设置联系电话
	 * @param telNum
	 * @throws Exception
	 */
	private void setTelephton(String telNum) throws Exception {
		super.clearAndSendKeys(By.id("LXDH"), telNum);
	}

	/**
	 * @Title: setXfrs
	 * @Description: 设置信访人数
	 * @throws Exception
	 */
	private void setXfrs() throws Exception {
		super.clearAndSendKeys(By.id("XFRS"), "" + random.nextInt(20));
	}

	/**
	 * @Title: setXfjd
	 * @Description: 设置问题街道
	 * @param jdname
	 * @throws Exception
	 */
	private void setCoursejd(String jdname) throws Exception {
		Select xfjd = new Select(super.elementWait(Variables.WAITTIME, By.id("city_Area")));
		if (jdname.equals("")) {
			xfjd.selectByIndex(0);
		} else {
			xfjd.selectByVisibleText(jdname);
		}
	}

	/**
	 * @Title: setXfc
	 * @Description: 设置问题村
	 * @param cname
	 * @throws Exception
	 */
	private void setCoursec() throws Exception {
		Select xfc = new Select(super.elementWait(Variables.WAITTIME, By.id("district_Area")));
		xfc.selectByIndex(random.nextInt(xfc.getOptions().size()));
	}

	/**
	 * @Title: setXfaddress
	 * @Description: 设置问题发生详细地址
	 * @throws Exception
	 */
	private void setCourseaddress() throws Exception {
		super.clearAndSendKeys(By.id("petitionModel.Address"), "问题发生详细地址");
	}

	/**
	 * @Title: setXfDate
	 * @Description: 设置信访日期
	 * @throws Exception
	 */
	private void setXfDate() throws Exception {
		super.clickElem(By.id("PetitionTime"));
		Thread.sleep(2000);
		super.clickElem(By.cssSelector("div#jedatebox .jedatetodaymonth"));
	}

	/**
	 * @Title: setPetitionPurpose
	 * @Description: 设置信访目的
	 * @throws Exception
	 */
	private void setPetitionPurpose() throws Exception {
		Select purpose = new Select(super.elementWait(Variables.WAITTIME, By.id("PetitionPurpose")));
		purpose.selectByIndex(random.nextInt(purpose.getOptions().size()));
	}

	/**
	 * @Title: setContenType
	 * @Description: 设置内容分类
	 * @throws Exception
	 */
	private void setContenType() throws Exception {
		/* 内容分类变更
		// 点击页面上的内容分类输入框
		WebElement contenType = super.elementWait(Variables.WAITTIME, By.id("ContentType"));
		Actions action = new Actions(driver);
		action.doubleClick(contenType).perform();// 双击内容分类按钮
		Thread.sleep(3000);
		choseContenType();
		 */
		Select contenType = new Select(super.elementWait(Variables.WAITTIME, By.id("ContentType")));
		contenType.selectByIndex(random.nextInt(contenType.getOptions().size()));
	}

	/**
	 * @Title: inputContenType
	 * @Description: 设置内容分类-输入设置
	 * @param contenType
	 * @throws Exception
	 */
//	private void inputContenType(String contenType) throws Exception {
//		// 点击页面上的内容分类输入框
//		super.clearAndSendKeys(By.id("ContentType"), contenType);
//	}

	/**
	 * @Title: choseContenType
	 * @Description: 内容分类页面随机选择一个问题小类
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void choseContenType() throws Exception {
		// 找到弹出的内容分类页面，定位到tbody元素
		WebElement tbody = super.elementWait(Variables.WAITTIME, By.cssSelector("div#NRFLPage tbody"));
		// 查找到所有的行，然后随机选择一行
		List<WebElement> rows = tbody.findElements(By.tagName("tr"));
		int rowindex = random.nextInt(rows.size());

		// 获取该随机行的所有内容大类，然后随机找到其中一个
		List<WebElement> itemTypes = rows.get(rowindex).findElements(By.id("itemTypes"));
		WebElement NRDLType = itemTypes.get(random.nextInt(itemTypes.size()));
		// 鼠标模拟摸到定位的问题大类上
		Actions action = new Actions(driver);
		action.moveToElement(NRDLType);
		action.perform();
		// 找到该问题大类下面所有的问题小类，然后获取其中一个，点击选择
		List<WebElement> types = NRDLType.findElements(By.tagName("li"));
		types.get(random.nextInt(types.size())).click();
	}

	/**
	 * @Title: setContent
	 * @Description: 信访人述求
	 * @throws Exception
	 */
	private void setContent(String content) throws Exception {
		super.clearAndSendKeys(By.id("Content"), content);
	}

	/**
	 * @Title: setOverview
	 * @Description: 问题概况
	 * @throws Exception
	 */
	private void setOverview() throws Exception {
		super.clearAndSendKeys(By.id("Overview"), "问题概况");
	}

	/**
	 * @Title: clickSaveBut
	 * @Description: 点击保存按钮
	 * @throws Exception
	 */
	private void clickSaveBut() throws Exception {
		super.clickElem(By.id("btnSubmit"));
	}

	/**
	 * @Title: clickUploadBut
	 * @Description: 点击上传按钮
	 * @throws Exception
	 */
	private void clickUploadBut() throws Exception {
		super.clickElem(By.id("UploadButton"));
	}

	/**
	 * @Title: uploadEle
	 * @Description: 浏览附件
	 * @return
	 * @throws Exception
	 */
	private WebElement uploadEle() throws Exception {
		return super.elementWait(Variables.WAITTIME, By.cssSelector("form[ng-app='fileUpload'] input#uploader_input"));
	}

	/**
	 * @Title: submitUploadBut
	 * @Description: 点击确定上传按钮
	 * @throws Exception
	 */
	private void submitUploadBut() throws Exception {
		super.clickElem(By.cssSelector("div.modal-content button[ng-click='submit()']"));
	}

	/**
	 * @Title: addPetitionRegisterAction
	 * @Description: 录入所有字段，添加信访登记信息操作
	 * @param isCXX：如果是所级用户，不用设置信访人街道和事件发生地的街道
	 * @throws Exception
	 */
	public void add_full_field_register_Action(boolean isqx, PetRegister register) throws Exception {
		logger.info("开始添加信访登记，所有字段都填写");
		Thread.sleep(2000);
		setXFID(register.xfbh);
		setXfxs("");
		setXfname(register.xfName);
		if (isqx) {
			setXfjd();
			setCoursejd(register.street);
		}
		Thread.sleep(2000);
		setXfc();
		setXfaddress();
		setCoursec();
		setCourseaddress();
		setIdentifyType();
		setIdentifyNum();
		setTelephton(register.telNum);
		setXfrs();
		setXfDate();
		setPetitionPurpose();
		setContenType();		
		setContent(register.content);
		setOverview();
		Thread.sleep(2000);
		// 如果附件的上次路径非空，则上传附件
		if (!register.fileName.equals("")) {
			upload_file_action(register.fileName);
		}
		clickSaveBut();
		Thread.sleep(3000);
	}

	/**
	 * @Title: add_required_field_register_action
	 * @Description: 录入必填字段，添加信访登记信息操作
	 * @throws Exception
	 */
	public void add_required_field_register_action(PetRegister register) throws Exception {
		logger.info("开始添加信访登记，只填写必填字段");
		Thread.sleep(2000);
		setXFID(register.xfbh);
		setXfxs("");
		setXfname(register.xfName);
		setTelephton(register.telNum);		
		setContenType();		
		setContent(register.content);
		Thread.sleep(2000);
		// 如果附件的上次路径非空，则上传附件
		if (!register.fileName.equals("")) {
			upload_file_action(register.fileName);
		}
		clickSaveBut();
		Thread.sleep(3000);
	}

	/**
	 * @Title: upload_file_action
	 * @Description: 附件上传
	 * @param filepath
	 * @throws Exception
	 */
	public void upload_file_action(String fileName) throws Exception {
		logger.info("开始上传附件操作");
		File directory = new File(".");
		String filePath = directory.getCanonicalPath() + "\\src\\testdata\\attachment\\" + fileName;
		// 上传附件
		clickUploadBut();
		Thread.sleep(1000);
		uploadEle().sendKeys(filePath);
		submitUploadBut();
		Thread.sleep(2000);
	}

}
