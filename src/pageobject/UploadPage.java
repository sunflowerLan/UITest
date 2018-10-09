package pageobject;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import config.Variables;

/**
 * @Title: UploadPage.java
 * @Package pageobject
 * @Description: 附件上传
 * @author lanfangz
 * @date 2017年9月7日 下午5:18:01
 * @version V1.0
 */
public class UploadPage extends BasePage {

	/**
	 * Title: 构造方法
	 */
	public UploadPage(WebDriver driver) {
		super(driver);
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
	 * @Title: upload_file_action
	 * @Description: 附件上传
	 * @param filepath
	 * @throws Exception
	 */
	public void upload_file_action(String fileName) throws Exception {
		logger.info("开始上传附件操作");
		File directory = new File(".");
		String filePath = directory.getCanonicalPath() + "\\src\\testdata\\attachment\\" + fileName;
		uploadEle().sendKeys(filePath);
		submitUploadBut();
		Thread.sleep(2000);
	}
}
