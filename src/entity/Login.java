package entity;

import java.util.HashMap;

/** 
* @ClassName: Login 
* @Description: 登录实体 
* @author  lanfangz
* @date 2017年7月10日 下午6:46:23 
* @version V1.0 
*/
public class Login {
	/*
	 * 用户名
	 */
	public String userName;

	/*
	 * 密码
	 */
	public String password;

	
	/** 
	* Title: 构造方法
	* @param initValue 
	*/
	public Login(boolean initValue) {
		if (initValue) {
			this.userName = "hbxf";
			this.password = "8888888";
		}
	}

	
	/** 
	* @Title: setLoginValue 
	* @Description: 给对象属性赋值
	* @param map 
	*/
	public void setLoginValue(HashMap<String, String> map) {
		this.userName = map.get("用户名");
		this.password = map.get("密码");
	}

}
