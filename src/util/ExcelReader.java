
package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * @author 作者：工具
 * @date 创建时间：2017年6月9日 下午4:21:20 
 * @version V1.0
 * @Description 描述：Excel工具类
 */

public class ExcelReader extends DataReader{
    public Workbook workbook;
    public Sheet sheet;
    public Cell cell;
    int rows;
    int columns;
    public String caseName;
    public ArrayList<String> arrkey = new ArrayList<String>();

    /** 
    * @Title: getExcelData 
    * @Description: 获得excel表中的数据
    * @param fileName 带后缀文件名
    * @param caseName 数据分类名
    * @return Object 数组
    * @throws BiffException 异常
    * @throws IOException IO异常
    */
    
    @SuppressWarnings("unchecked")
	public HashMap<String, String>[][] getExcelData(String fileName, String caseName) throws BiffException, IOException {

        super.fileName = fileName;
        this.caseName = caseName;
        
        workbook = Workbook.getWorkbook(new File(super.getPath()));
        sheet = workbook.getSheet(caseName);
        rows = sheet.getRows();
        columns = sheet.getColumns();
        // 为了返回值是Object[][],定义一个多行单列的二维数组
		HashMap<String, String>[][] arrmap = new HashMap[rows - 1][1];
        // 对数组中所有元素hashmap进行初始化
        if (rows > 1) {
            for (int i = 0; i < rows - 1; i++) {
                arrmap[i][0] = new HashMap<>();
            }
        } else {
            logger.error("excel中没有数据");
        }

        // 获得首行的列名，作为hashmap的key值
        for (int c = 0; c < columns; c++) {
            String cellvalue = sheet.getCell(c, 0).getContents();
            arrkey.add(cellvalue);
        }
        // 遍历所有的单元格的值添加到hashmap中
        for (int r = 1; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                String cellvalue = sheet.getCell(c, r).getContents();
                arrmap[r - 1][0].put(arrkey.get(c), cellvalue);
            }
        }
        return arrmap;
    }


	public static void main(String[] args) throws IOException, BiffException {
		ExcelReader excel = new ExcelReader();
		HashMap<String, String>[][] map=excel.getExcelData("data.xls", "user");
		HashMap<String, String> arrmap = map[1][0];  
        Iterator<?> iter = arrmap.entrySet().iterator();  
        while (iter.hasNext()) {  
            @SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();  
            Object key = entry.getKey();  
            Object value = entry.getValue();  
            System.out.println(key + " " + value);    
		}
	}
}


