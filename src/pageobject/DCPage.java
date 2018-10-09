package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import entity.DCInfo;

/**
 * @Title: DCPage.java
 * @Package pageobject
 * @Description: 督查信息页面元素
 * @author lanfangz
 * @date 2017年9月8日 下午5:04:25
 * @version V1.0
 */
public class DCPage extends BasePage {
	/**
	 * Title: 构造方法
	 */
	public DCPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @Title: setDcNum
	 * @Description: 设置督查编号
	 * @param dcNum
	 * @throws Exception
	 */
	private void setDcNum(String dcNum) throws Exception {
		super.clearAndSendKeys(By.cssSelector("div.modal-content table.Satisfaction_table input#SUPERVISESNUMBER"),
				dcNum);
	}

	/**
	 * @Title: setLxPeople
	 * @Description: 设置立项人
	 * @param name
	 * @throws Exception
	 */
	private void setLxPeople(String name) throws Exception {
		super.clearAndSendKeys(By.cssSelector("div.modal-content table.Satisfaction_table input#CREATEPROJECTPEOPLE"),
				name);
	}

	/**
	 * @Title: clickSaveBut
	 * @Description: 点击保存按钮
	 * @throws Exception
	 */
	private void clickSaveBut() throws Exception {
		super.clickElem(By.cssSelector("div.modal-content input#factSaveBtn"));
	}

	/**
	 * @Title: add_cd_action
	 * @Description: 添加督查操作
	 * @param dcInfo
	 * @throws Exception
	 */
	public void add_dc_action(DCInfo dcInfo) throws Exception {
		logger.info("打开督查信息页面，添加督查信息");
		setDcNum(dcInfo.dcNum);
		setLxPeople(dcInfo.lxPeople);
		Thread.sleep(1000);
		clickSaveBut();
		Thread.sleep(3000);
	}
}
