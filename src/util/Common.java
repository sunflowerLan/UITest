
package util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @Title: Common.java
 * @Package util
 * @Description: 公用方法
 * @author lanfangz
 * @date 2017年8月18日 下午2:46:24
 * @version V1.0
 */
public class Common {

	/**
	 * @Title: getRandom
	 * @Description: 生成随机数
	 * @return
	 */
	public String getRandom() {
		Random random = new Random();
		float num = random.nextFloat() * 100;
		String formatNum = String.format("%.2f", num);
		return formatNum;
	}

	/**
	 * @Title: getRandomDate
	 * @Description: 随机生成日期
	 * @return
	 */
	public String getRandomDate(boolean nowdate) {
		Date date = new Date();
		if (!nowdate) {
			// 创建 Calendar 对象
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, +1);
			date = calendar.getTime();
		}
		return String.format("%tF", date);
	}

	/**
	 * @Title: getRandomString
	 * @Description: 获取随机字符串
	 * @param length
	 * @return
	 */
	public String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * @Title: main
	 * @Description: TODO
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
