package util;

import java.io.File;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



/** 
* @ClassName: NodeReader 
* @Description: xml节点读取 
* @author lanfangz
* @date 2017年7月11日 下午12:37:08 
* @version V1.0 
*/
public class NodeReader extends DataReader{
	
	/**
	 * 使用dom4j解析xml文件，并读取节点内容
	 * @param doc
	 * @param firstnode
	 * @param secondnode
	 * @return String
	 */
	public String readNodeValue(Document doc, String firstnode, String secondnode) {
		Element root = doc.getRootElement();//得到xml根标签
		Element testCaseNode = root.element(firstnode);
		String nodeValue = testCaseNode.element(secondnode).getStringValue();
		return nodeValue;
	}
	
	/** 
	* @Title: getXmlData 
	* @Description: 读取xml文件
	* @param filename
	* @return
	* @throws IOException
	*/
	public Document getXmlData(String fileName) throws IOException{
		 super.fileName = fileName;
	     Document document = null;
	     SAXReader reader = new SAXReader();
	     super.getPath();
	     reader.setEncoding("utf-8");
	     try {
			   document = reader.read(new File(super.getPath()));// 读取xml文件内容到document文档中
			 } catch (DocumentException e) {
				logger.info("读取xml文件内容失败" + e.getMessage());
				// e.printStackTrace();
			 }
		 return document;
	}
	
	public static void main(String[] args) throws IOException{
		NodeReader reader = new NodeReader();
		Document doc = reader.getXmlData("testData_login.xml");
		String userName = reader.readNodeValue(doc, "loginsuccess", "username");
		String passWord = reader.readNodeValue(doc, "loginsuccess", "password");
		System.out.println(userName+'_'+passWord);
		logger.info("读取xml成功");
	}
}
