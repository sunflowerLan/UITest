package config;

/**
 * @author 作者: lanfangz
 * @date 创建时间：2017年6月29日 下午4:01:56
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class Variables {

	/** 
	* @Fields BASEURL : 系统登记地址 
	*/ 
	public final static String BASEURL="http://192.168.5.71:8013/#";
	public final static String CXUSER = "lanfangz";
	public final static String CXPWD = "8888888";
	
	public final static String ZCUSER = "xh";
	public final static String ZCPWD = "8888888";
	
	public final static String XFDJURL = "/petitionRegister";
	/** 
	* @Fields WAITTIME : 元素查找等待时间 
	*/ 
	public static final long WAITTIME = 10;

	/** 
	* @Fields NEXTLEVEL : 移交方式：向下移交 
	*/ 
	public static final String NEXTLEVEL = "向下";
	/** 
	* @Fields SAMELEVEL : 移交方式：同级移交
	*/ 
	public static final String SAMELEVEL = "平级";
	/** 
	* @Fields BACKLEVEL : 移交方式：上一个节点移交 
	*/ 
	public static final String BACKLEVEL = "回退";
	/** 
	* @Fields FIRSTLEVEL : 移交方式：窗口补正
	*/ 
	public static final String FIRSTLEVEL = "窗口补正";
	
	public static final String SCDJ = "首次登记";
	

}
