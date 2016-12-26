package com.z.mif.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class PictureStoryEObj implements Serializable {

	private static final long serialVersionUID = -7741325108597846045L;

	private String id;

	private String pictureName;
	
	private String title;

	private String content;

	private String backgroundInd;
	
	/** 创建时间 */
	protected Timestamp createTime;
	
	private String tags;
	
	private String parameter;

	private Integer userId;
	
	private String nickName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getBackgroundInd() {
		return backgroundInd;
	}

	public void setBackgroundInd(String backgroundInd) {
		this.backgroundInd = backgroundInd;
	}

	public String getNickName() {
		return nickName;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
}
