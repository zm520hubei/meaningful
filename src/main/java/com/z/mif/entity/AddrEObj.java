package com.z.mif.entity;

import java.io.Serializable;

public class AddrEObj implements Serializable{

	private static final long serialVersionUID = -2027367193687722965L;

	private Integer value;
	
	private String label;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}


}
