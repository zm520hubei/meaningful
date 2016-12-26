package com.z.mif.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.util.StringUtils;

import com.z.mif.util.DesUtils;


public class UserEObj implements Serializable {

	private static final long serialVersionUID = -8784113067183781255L;

	private Integer id;

	private String nickName;
	
	private String sex;

	private String password;
	
	private String headName;

	private String cellphone;

	private String email;
	
	private String loginParam;
	
	private Integer cityId;

	/** 创建时间 */
	private Timestamp createTime;
	
	private String imgCode;
	
	private String phoneOrEamilCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getPassword() throws Exception {
		return password;
	}
	
	public String getDecryptPassword() throws Exception{
		String decryptPassword = null;
		if(!StringUtils.isEmpty(password) && password.length() >= 16){
			DesUtils des = new DesUtils();
			decryptPassword = des.decrypt(password);
		}
		return decryptPassword;
	}

	public void setPassword(String password) throws Exception {
		DesUtils des = new DesUtils();
		password = des.encrypt(password);
		this.password = password;
	}

	public String getLoginParam() {
		return loginParam;
	}

	public void setLoginParam(String loginParam) {
		this.loginParam = loginParam;
	}

	public String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	public String getPhoneOrEamilCode() {
		return phoneOrEamilCode;
	}

	public void setPhoneOrEamilCode(String phoneOrEamilCode) {
		this.phoneOrEamilCode = phoneOrEamilCode;
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
