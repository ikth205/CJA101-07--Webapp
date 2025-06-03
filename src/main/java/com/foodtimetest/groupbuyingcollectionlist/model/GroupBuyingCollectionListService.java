package com.foodtimetest.groupbuyingcollectionlist.model;

import java.util.Date;
import java.util.List;

public class GroupBuyingCollectionListService {
	
	private GroupBuyingCollectionListDAO_interface dao;

	public GroupBuyingCollectionListService() {
	    dao = new GroupBuyingCollectionListJDBCDAO(); 
	}

	// 新增收藏
	public GroupBuyingCollectionListVO addCollection(Integer gbId, Integer memId) {
	    GroupBuyingCollectionListVO collectionVO = new GroupBuyingCollectionListVO();
	    collectionVO.setGbId(gbId);
	    collectionVO.setMemId(memId);
	    collectionVO.setCreateAt(new Date());
	    dao.insert(collectionVO);
	    return collectionVO;
	}

	// 刪除收藏
	public void deleteCollection(Integer gbId, Integer memId) {
	    dao.delete(gbId, memId);
	}

	// 查詢單筆收藏（判斷是否已收藏）
	public GroupBuyingCollectionListVO getOneCollection(Integer gbId, Integer memId) {
	    return dao.findByPrimaryKey(gbId, memId);
	}

	// 查詢所有收藏
	public List<GroupBuyingCollectionListVO> getAll() {
	    return dao.getAll();
	}

	// 查詢某會員的所有收藏
	public List<GroupBuyingCollectionListVO> getByMemId(Integer memId) {
	    return dao.findByMemId(memId);
	}

}
