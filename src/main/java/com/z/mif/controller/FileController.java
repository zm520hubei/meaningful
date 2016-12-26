package com.z.mif.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.z.mif.constants.Constants;
import com.z.mif.util.Configuration;
import com.z.mif.util.FileContentTypeUtils;
import com.z.mif.util.ImgUtil;

@Controller
@RequestMapping("/file")
public class FileController {

	private static Logger log = Logger.getLogger(FileController.class);
	
	/**
	 * 
	 * @param filedata 上传的文件
	 * @param fileType 文件类型  头像or other
	 * @param width 压缩后的宽度
	 * @param height 压缩后的高度
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> execute(@RequestParam(value = "file") MultipartFile filedata,
			@RequestParam(value = "fileType")String fileType,
			@RequestParam(value = "width",required = false)Integer width,
			@RequestParam(value = "height", required = false)Integer height,
			HttpServletRequest req) throws Exception {
		
		FileContentTypeUtils.deletFilesInFolder(Configuration.getProperty(Constants.TEMP_URL));
		Map<String,Object> result = new HashMap<String,Object>();
		if (filedata.getSize() > Long.valueOf(Configuration.getProperty(Constants.UPLOAD_PHOTO_SIZE))) {
			result.put("status", false);
            result.put("errMsg","图片大小不应超过1M");
            return result;
        }
		if (filedata != null && !filedata.isEmpty()) {
            // 获取图片的文件名
            String fileName = filedata.getOriginalFilename();
            // 获取图片的扩展名
            String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 新的图片文件名 = 获取时间戳+"."图片扩展名
            String newFileName = String.valueOf(System.currentTimeMillis())  + "." + extensionName;

            try {
            	result.putAll(saveFile(newFileName, filedata, width, height,fileType));
            } catch (Exception e) {
                log.error("上传图片失败.", e);
                result.put("status", false);
                result.put("errMsg", "上传图片失败.");
                return result;
            }
        }else{
        	result.put("status", false);
            result.put("errMsg", "上传图片失败.文件为空");
            return result;
        }
		result.put("status", true);
		
		return result;
	}
	
	/**
	 * 
	 * @param newFileName 保存后的文件名
	 * @param filedata 上传文件对象
	 * @param newWidth 保持在服务器上图片宽度
	 * @param newHeight 保持在服务器上图片高度
	 * @param fileType 上传图片的类型  头像/封面.....
	 * @return
	 * @throws Exception
	 */
    private Map<String,Object> saveFile(String newFileName, MultipartFile filedata,Integer newWidth,Integer newHeight,String fileType) throws Exception {
    	Map<String,Object> result = new HashMap<String,Object>();
        String saveFilePath = Configuration.getProperty(Constants.PIC_URL);//默认图片保存路径
//        if(fileType.equals(Constants.FILE_TYPE_HEAD))//头像
        	saveFilePath = Configuration.getProperty(Constants.TEMP_URL); 

        //头像保存在临时路径里面   裁剪后再保存再正式路径
        //上传封面的时候也保存在临时目录里面   点击保存后再保存到正式路径
        
        
        String absSaveFilePath = saveFilePath + File.separator + newFileName; //头像路径 + 头像名字
        
        /* 构建文件目录 */
        File fileDir = new File(saveFilePath);
        File file = new File(saveFilePath + File.separator + filedata.getOriginalFilename());
        
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        
       
        if(fileType.equals(Constants.FILE_TYPE_HEAD)){//头像
        	//TEMP_URL
        	filedata.transferTo(file);
        	BufferedImage image = ImageIO.read(file);//图片流
        	image = ImgUtil.resize(image, newWidth, newHeight);
        	ImageIO.write(image, "png", new FileOutputStream(absSaveFilePath));
        }else{//非头像
        	filedata.transferTo(file);
        	BufferedImage image = ImageIO.read(file);
        	Integer w = image.getWidth();
        	Integer h = image.getHeight();
        	double rate = Float.valueOf(w.toString())/Float.valueOf("310");//  这取小数宽度不用计算  在页面固定死为310px  这里只需要按照比例处理图片高度即可
        	
        	//最小高度为215px
        	int nh = (int)Math.ceil(h/rate);
        	if(nh < 215) nh = 215;
        	result.put("h", nh);
        	//最合适的宽高 300 * 215  勉强按照黄金比例来 
//        	if(image.getHeight() > 400){
        		image = ImgUtil.resize(image, 260, nh);
            	ImageIO.write(image, "png", new FileOutputStream(absSaveFilePath));
//        	}else{
//            FileOutputStream out = new FileOutputStream(absSaveFilePath);
//            // 写入文件
//            out.write(filedata.getBytes());
//            out.flush();
//            out.close();
//        	}
        }
        
        
      //TODO  这里暂时切割  放到服务器上时 修改配置文件的文件上传目录后  则去掉  
  		result.put("saveFile", absSaveFilePath.substring(46));
//			result.put("saveFile", saveFilePath);
        return result;
    }
    
}
