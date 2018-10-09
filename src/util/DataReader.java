package util;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataReader {
	

	public String fileName;
    public String sourceFile;
	public static Logger logger;
		
	public DataReader()
	{
	  if(logger==null)
		  logger=LogManager.getLogger(this.getClass());
	}	
    /**
     * 获得文件的路径
     * @return String
     * @throws IOException IO异常
     */
    public String getPath() throws IOException {
        File directory = new File(".");
        this.sourceFile = directory.getCanonicalPath() + "\\src\\testdata\\"+ fileName;
        return sourceFile;
    }
    
}
