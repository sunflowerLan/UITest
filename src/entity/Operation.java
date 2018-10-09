package entity;

/**
 * @Title: Operation.java
 * @Package entity
 * @Description: 操作实体类
 * @author lanfangz
 * @date 2017年9月7日 下午5:36:57
 * @version V1.0
 */
public class Operation {

	/**
	 * @Fields navigation : 子页签名称
	 */
	public String navigation;
	/**
	 * @Fields operate : 处理方式
	 */
	public String operate;
	/**
	 * @Fields content : 处理意见
	 */
	public String content;
	/** 
	* @Fields spUser : 审批人员 
	*/ 
	public String spUser;
	/**
	 * @return the spUser
	 */
	public String getSpUser() {
		return spUser;
	}

	/**
	 * @param spUser the spUser to set
	 */
	public void setSpUser(String spUser) {
		this.spUser = spUser;
	}

	/**
	 * @Fields fileName : 附件名称
	 */
	public String fileName;

	/**
	 * @Fields VisitResult : 满意度
	 */
	public String VisitResult;
	
	/** 
	* @Fields transHandleType : 交办-办理方式 
	*/ 
	public String transHandleType;
	
	/** 
	* @Fields sponsor : 主办人 
	*/ 
	public String sponsor;
	
	/**
	 * @return the sponsor
	 */
	public String getSponsor() {
		return sponsor;
	}

	/**
	 * @param sponsor the sponsor to set
	 */
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	/**
	 * @return the coordinator
	 */
	public String getCoordinator() {
		return coordinator;
	}

	/**
	 * @param coordinator the coordinator to set
	 */
	public void setCoordinator(String coordinator) {
		this.coordinator = coordinator;
	}

	/** 
	* @Fields coordinator : 协办人 
	*/ 
	public String coordinator;

	/**
	 * @return the transHandleType
	 */
	public String getTransHandleType() {
		return transHandleType;
	}

	/**
	 * @param transHandleType the transHandleType to set
	 */
	public void setTransHandleType(String transHandleType) {
		this.transHandleType = transHandleType;
	}

	/**
	 * @return the visitResult
	 */
	public String getVisitResult() {
		return VisitResult;
	}

	/**
	 * @param visitResult
	 *            the visitResult to set
	 */
	public void setVisitResult(String visitResult) {
		VisitResult = visitResult;
	}

	/**
	 * @return the navigation
	 */
	public String getNavigation() {
		return navigation;
	}

	/**
	 * @param navigation
	 *            the navigation to set
	 */
	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}

	/**
	 * @return the operate
	 */
	public String getOperate() {
		return operate;
	}

	/**
	 * @param operate
	 *            the operate to set
	 */
	public void setOperate(String operate) {
		this.operate = operate;
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
	 * Title: 构造方法
	 */
	public Operation() {
		this.navigation = "";
		this.operate = "";
		this.transHandleType="普通程序";
		this.content = "处理意见";
		this.spUser="张兰芳";
		this.fileName = "";
		this.VisitResult = "信访满意";
		this.sponsor="雉城街道国土所";
		this.coordinator="";
	}
}
