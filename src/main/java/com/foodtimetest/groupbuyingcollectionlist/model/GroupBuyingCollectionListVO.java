package com.foodtimetest.groupbuyingcollectionlist.model;

import java.io.Serializable;
import java.util.Date;

public class GroupBuyingCollectionListVO implements Serializable{
	 
	private Integer gbId;
	private Integer memId;
	private Date createAt;
	public Integer getGbId() {
		return gbId;
	}
	public void setGbId(Integer gbId) {
		this.gbId = gbId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	
	
	
	
}
