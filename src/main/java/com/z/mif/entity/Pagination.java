package com.z.mif.entity;

import java.util.List;

public class Pagination {
	
	private int start;
	private int limit;
	private int total;
	private List<?> datas;
	
	public Pagination() {
	}
	
	public Pagination(int start,int limit){
		this.start = start;
		this.limit = limit;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<?> getDatas() {
		return datas;
	}
	public void setDatas(List<?> datas) {
		this.datas = datas;
	}

}
