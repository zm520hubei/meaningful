package com.z.mif.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author Sayid
 *
 */
public class CommentEObj implements Serializable {

	private static final long serialVersionUID = 2924613384674933538L;

	private Integer id;

	private Integer parentId;

	private String storyId;

	private String content;
	
	/** 未读消息？ **/
	private String read;

	private Timestamp createTime;

	private String headName;

	private Integer level;

	private Integer userId;

	private String nickName;

	private String parentNickName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getStoryId() {
		return storyId;
	}

	public void setStoryId(String storyId) {
		this.storyId = storyId;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getParentNickName() {
		return parentNickName;
	}

	public void setParentNickName(String parentNickName) {
		this.parentNickName = parentNickName;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

}
