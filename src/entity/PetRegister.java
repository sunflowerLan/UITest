package entity;

import java.util.HashMap;

/**
 * @Title: PetRegister.java
 * @Package entity
 * @Description: 信访登记信息实体类
 * @author lanfangz
 * @date 2017年9月6日 下午5:10:00
 * @version V1.0
 */
public class PetRegister {

	/**
	 * @Fields xfbh : 信访编号
	 */
	public String xfbh;
	/**
	 * @Fields xfName : 信访人姓名
	 */
	public String xfName;
	/**
	 * @Fields telNum : 联系电话
	 */
	public String telNum;
	/**
	 * @Fields street : 问题发生街道
	 */
	public String street;
	/**
	 * @Fields contenType : 内容分类
	 */
	public String contenType;
	/**
	 * @Fields content : 信访人述求
	 */
	public String content;
	/**
	 * @Fields fileName : 附件名称
	 */
	public String fileName;

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the xfbh
	 */
	public String getXfbh() {
		return xfbh;
	}

	/**
	 * @param xfbh
	 *            the xfbh to set
	 */
	public void setXfbh(String xfbh) {
		this.xfbh = xfbh;
	}

	/**
	 * @return the xfName
	 */
	public String getXfName() {
		return xfName;
	}

	/**
	 * @param xfName
	 *            the xfName to set
	 */
	public void setXfName(String xfName) {
		this.xfName = xfName;
	}

	/**
	 * @return the telNum
	 */
	public String getTelNum() {
		return telNum;
	}

	/**
	 * @param telNum
	 *            the telNum to set
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the contenType
	 */
	public String getContenType() {
		return contenType;
	}

	/**
	 * @param contenType
	 *            the contenType to set
	 */
	public void setContenType(String contenType) {
		this.contenType = contenType;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Title: 构造方法
	 */
	public PetRegister(boolean isqx) {
		if (isqx) {
			this.xfbh = "CX" + System.currentTimeMillis();
			this.street = "雉城街道";
		} else {
			this.xfbh = "JD" + System.currentTimeMillis();
		}
		this.xfName = "信访人";
		this.telNum = "无";
		this.contenType = "";
		this.content = "信访人述求" + System.currentTimeMillis();
		this.fileName = "";
	}

	/**
	 * @Title: setPetRegister
	 * @Description: 设置信访信息
	 * @param initData
	 */
	public void setPetRegister(HashMap<String, String> initData) {
		this.xfbh = initData.get(xfbh);
		this.xfName = initData.get(xfName);
		this.telNum = initData.get(telNum);
		this.street = initData.get(street);
		this.contenType = initData.get(contenType);
		this.content = initData.get(content);
		this.fileName = initData.get(fileName);
	}

}
