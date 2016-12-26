package com.z.mif.util;

import java.util.Random;

import com.z.mif.service.BaseService;

public class MathUtil {

	static Random rd = new Random();
	
	/**
	 * 返回一个唯一ID
	 * @param <T>
	 * @return
	 */
	public static <T> String getUnId(BaseService<T> service){
		StringBuilder sb = new StringBuilder();
		String id = null;
		for(int i = 0; i < 6; i++){
			if(rd.nextInt(9)%2 == 0)
				sb.append(MathUtil.getRanNum());
			else sb.append(MathUtil.getRanStr());
		}
		id = sb.toString();
		Integer i = service.isUnId(id);
		if(i > 0)
			getUnId(service);
		return id;
	}
	
	/**
	 * @return 一个随机的数字
	 */
	public static String getRanNum() {
		String s = "123456789";
		String ch = String.valueOf(s.charAt(rd.nextInt(s.length())));
		return ch;
	}
	

	/**
	 * @return 一个随机的字母
	 */
	public static String getRanStr() {
		String s = "abcdefghigkmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String ch = String.valueOf(s.charAt(rd.nextInt(s.length())));
		return ch;
	}
}
