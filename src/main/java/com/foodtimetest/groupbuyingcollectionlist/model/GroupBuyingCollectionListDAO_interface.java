package com.foodtimetest.groupbuyingcollectionlist.model;

import java.util.*;


public interface GroupBuyingCollectionListDAO_interface {
	
	public void insert(GroupBuyingCollectionListVO collectionVO);    
    public void delete(Integer gbId, Integer memId);

    // 查詢單筆收藏（判斷是否已收藏）
    public GroupBuyingCollectionListVO findByPrimaryKey(Integer gbId, Integer memId);
    // 查詢所有收藏
    public List<GroupBuyingCollectionListVO> getAll();
    // 查詢某會員所有收藏
    public List<GroupBuyingCollectionListVO> findByMemId(Integer memId);
         
}
