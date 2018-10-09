package entity;

/**
 * @Title: DCInfo.java
 * @Package entity
 * @Description: 督查实体类
 * @author lanfangz
 * @date 2017年9月8日 下午5:06:25
 * @version V1.0
 */
public class DCInfo {
	/**
	 * @Fields dcNum : 督查编号
	 */
	public String dcNum;
	/**
	 * @return the dcNum
	 */
	public String getDcNum() {
		return dcNum;
	}

	/**
	 * @param dcNum the dcNum to set
	 */
	public void setDcNum(String dcNum) {
		this.dcNum = dcNum;
	}

	/**
	 * @return the lxPeople
	 */
	public String getLxPeople() {
		return lxPeople;
	}

	/**
	 * @param lxPeople the lxPeople to set
	 */
	public void setLxPeople(String lxPeople) {
		this.lxPeople = lxPeople;
	}

	/**
	 * @Fields lxPeople : 立项人
	 */
	public String lxPeople;

	public DCInfo() {
		this.dcNum = "DC" + System.currentTimeMillis();
		this.lxPeople = "立项人";
	}
}
