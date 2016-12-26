package com.z.mif.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.z.mif.constants.Constants;
import com.z.mif.util.MathUtil;

@Controller
public class VerifyCodeController {

	private int height = 50;
	private int width = 70;
	private int num = 4;
	private int fontSize = 20;
	private StringBuilder sb = new StringBuilder();
	private String[] fontName = { "宋体", "华文楷体", "黑体", "微软雅黑" };
	Random rd = new Random();

	/**
	 * @return 获取当前二维码图片的长度
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param 设置二维码图片的长度
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return 获取当前二维码图片的高度
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param heigh
	 *            设置二维码图片的高度
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return 返回二维码字符个数
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num
	 *            设置二维码的字符个数.
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @return 当前二维码字体的大小
	 */
	public int getFontSize() {
		return fontSize;
	}

	/**
	 * @param 获取二维码字体大小
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * @return 返回当前生成的二维码的内容
	 */
	public String getText() {
		return sb.toString();
	}

	/**
	 * @return 返回一个图片缓冲区对象 BufferedImage 包含了绘制完成的图片.
	 */
	public BufferedImage getBufferedImage() {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		sb.delete(0, num);
		String s = null;
		for (int i = 0; i < num; i++) {
			g.setColor(getColor());
			g.setFont(getFont());
			randomLine(g);
			s = getStr();
			sb.append(s);
			g.drawString(s, i * (width / num),
					height / 2 + rd.nextInt(height / 2));
		}
		return bi;
	}

	public String getStr() {
		String s = "23456789abcdefghigkmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String ch = String.valueOf(s.charAt(rd.nextInt(s.length())));
		return ch;
	}
	/**
	 * @return 一个随机颜色的Color
	 */
	public Color getColor() {
		return new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255));
	}


	/**
	 * 绘制一条任意的线
	 * 
	 * @param 画笔对象
	 */
	public void randomLine(Graphics2D g) {
		int ax = rd.nextInt(width), ay = rd.nextInt(height);
		int bx = rd.nextInt(width), by = rd.nextInt(height);
		g.drawLine(ax, ay, bx, by);
	}

	/**
	 * @return 返回一个字体大小和字体随记.
	 */
	public Font getFont() {
		return new Font(fontName[rd.nextInt(fontName.length)], rd.nextInt(4),
				rd.nextInt(10) + fontSize);
	}
	
	@RequestMapping(value = "/code/verifyCode", method = RequestMethod.GET)
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {
		HttpSession session = req.getSession();
		// 禁止图像缓存。
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/jpeg");
		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos = resp.getOutputStream();
		ImageIO.write(this.getBufferedImage(), "jpg", sos);
		sos.close();
		session.setAttribute(Constants.SESSION_IMG_CODE, this.getText());
	}
	
	 public static void main(String[] args) {  
	        /* 
	         *  Image / ImageIo / BufferedImage / Icon / ImageIcon 
	         */  
		 VerifyCodeController c = new VerifyCodeController();
	        BufferedImage bi = c.getBufferedImage();  
	          
	        System.out.println(c.getText());  
	        try {  
	              
	            ImageIO.write(bi, "JPG", new FileOutputStream("d:/abc.jpg"));  
	          
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }   
	          
	          
	    }  
}
