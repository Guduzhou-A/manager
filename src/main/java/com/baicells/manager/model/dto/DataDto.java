/**
 * AoDataDto.java
 * cn.videoworks.publisher.dto
 *
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2015年4月30日 		meishen
 *
 * Copyright (c) 2015, TNT All Rights Reserved.
*/

package com.baicells.manager.model.dto;

public class DataDto {
	private String name;
	
	private String value;
	
	public DataDto() {
	}

	public DataDto(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
}

