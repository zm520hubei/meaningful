package com.z.mif.constants;

import java.util.Random;


public class Constants {
	
	public final static String Y = "Y";
	
	public final static String N = "N";
	
	/** 未读消息 **/
	public final static String UNREAD = "unread";
	
	/** 手机或邮箱验证码有效期为300000毫秒 == 5min **/
	public final static Long SESSION_CODE_PERIOD = 300000l;
	

	/** 邮箱验证码 **/
	public final static String SESSION_EMAIL_CODE = "emailCode";
	/** 手机验证码 **/
	public final static String SESSION_PHONE_CODE = "phoneCode";
	/** 图片验证码 **/
	public final static String SESSION_IMG_CODE = "imgCode";
	/** 登录用户 **/
	public final static String SESSION_USER = "user";
	/** 登录后跳转的url **/
	public final static String SESSION_URI = "skip_uri";
	/** 登录后跳转的url里面的参数 **/
	public final static String SESSION_URI_PARAMS = "skip_uri_params";
	
	public final static String PAGE_TYPE = "page_type";
	
	/** 注册  **/
	public final static Integer TYPE_REGISTER = 83746134;
	/** 登录  **/
	public final static Integer TYPE_LOGIN = 94071001;
	/** 设置 **/
	public final static Integer TYPE_SET = 48824877;
	/** 忘记密码 **/
	public final static Integer TYPE_FORGET_PASSWORD = 7540240;
	/** 修改头像 **/
	public final static Integer TYPE_UPDATE_HEAD = 2553574;
	/** 修改绑定手机号 **/
	public final static Integer TYPE_CHANGE_PHONE = 4612455;
	/** 修改绑定邮箱 **/
	public final static Integer TYPE_CHANGE_EMAIL = 2349606;
	/** 绑定邮箱或手机 **/
	public final static Integer TYPE_BIND = 9482396;
	
	
	
	/** 文件类型   头像  **/
	public final static String FILE_TYPE_HEAD = "head";
	
	/** 上传照片最大限制 **/
	public final static String UPLOAD_PHOTO_SIZE = "upload.photo.size";
	/** 默认图片保存路径 **/
	public final static String PIC_URL = "pic.url";
	/** 头像保存路径 **/
	public final static String HEAD_URL = "head.url";
	/** 临时存储路径 **/
	public final static String TEMP_URL = "file.temp.url";
	
	public static void main(String[] args) {
		System.out.println(new Random().nextInt(10000000-1));
	}
	
}
