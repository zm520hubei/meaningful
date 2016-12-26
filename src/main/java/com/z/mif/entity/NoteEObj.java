package com.z.mif.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author Sayid
 *
 */
public class NoteEObj implements Serializable {

	private static final long serialVersionUID = -8328875578871147970L;

	private String id;

	private Integer userId;

	private String content;

	private Timestamp createTime;
	
	private String nickName;
	
	private String parameter;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

}
