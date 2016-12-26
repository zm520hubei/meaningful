package com.z.mif.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


/**
 * <B>文件类型工具</B>
 * @author henry
 * @since 2014年1月23日
 */
public class FileContentTypeUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(FileContentTypeUtils.class);
	
//	private static Properties ContentsProperties = new Properties();
//	
//	static{
//		try {
//			ContentsProperties.load(FileContentTypeUtils.class.getResourceAsStream("/content.properties"));
//		} catch (IOException e) {
//			logger.error(e.getMessage());
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * <B>通过文件获得ContentType</B>
	 * @param fileName 文件名
	 * @return
	 * @throws ProcessException 
	 */
//	public static String getContentType(String fileName) throws Exception{
//		if(fileName == null) throw new Exception("文件不存在.");
//		String[] items = fileName.split("\\.");
//		if(items == null || items.length == 1) throw new Exception("请检查文件名, 是否规范?");
//		String contentType = ContentsProperties.getProperty(items[items.length-1].toLowerCase());
//		return contentType == null ? "text/html" : contentType;
//	}
	
	/**
	 * 删除文件或文件夹下面的所有文件  保留文件夹
	 * @param files
	 * @throws Exception 
	 */
	public static void deletFilesInFolder(String files) throws Exception{
		if(StringUtils.isEmpty(files)) throw new Exception("文件或文件夹不存在.");
		File file = new File(files);
		if(file.isDirectory()){
			File[] fs = file.listFiles();
			for (File f : fs) {
				f.delete();
			}
		}else{
			file.delete();
		}
	}
	
}
