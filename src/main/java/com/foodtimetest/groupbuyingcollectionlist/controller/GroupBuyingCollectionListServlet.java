package com.foodtimetest.groupbuyingcollectionlist.controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

import java.util.*;

import com.foodtimetest.groupbuyingcollectionlist.model.*;

@WebServlet("/groupbuyingcollectionlist.do")
@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)

public class GroupBuyingCollectionListServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		// 查詢某會員的所有收藏
		if ("getList_By_MemId".equals(action)) { // 來自select_page.jsp的請求

		    List<String> errorMsgs = new LinkedList<String>();
		    req.setAttribute("errorMsgs", errorMsgs);

		    /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		    String str = req.getParameter("memId");
		    if (str == null || (str.trim()).length() == 0) {
		        errorMsgs.add("請輸入會員編號");
		    }

		    if (!errorMsgs.isEmpty()) {
		        RequestDispatcher failureView = req.getRequestDispatcher("/groupbuyingcollectionlist/select_page.jsp");
		        failureView.forward(req, res);
		        return; // 程式中斷
		    }

		    Integer memId = null;
		    try {
		        memId = Integer.valueOf(str);
		    } catch (Exception e) {
		        errorMsgs.add("會員編號格式不正確");
		    }

		    if (!errorMsgs.isEmpty()) {
		        RequestDispatcher failureView = req.getRequestDispatcher("/groupbuyingcollectionlist/select_page.jsp");
		        failureView.forward(req, res);
		        return; // 程式中斷
		    }

		    /***************************2.開始查詢資料*****************************************/
		    GroupBuyingCollectionListService collectionSvc = new GroupBuyingCollectionListService();
		    List<GroupBuyingCollectionListVO> list = collectionSvc.getByMemId(memId);
		    if (list == null || list.isEmpty()) {
		        errorMsgs.add("查無收藏清單");
		    }

		    if (!errorMsgs.isEmpty()) {
		        RequestDispatcher failureView = req.getRequestDispatcher("/groupbuyingcollectionlist/select_page.jsp");
		        failureView.forward(req, res);
		        return; // 程式中斷
		    }

		    /***************************3.查詢完成,準備轉交(Send the Success view)*************/
		    req.setAttribute("collectionList", list); // 將收藏清單放到 request 範圍

		    String url = "/groupbuyingcollectionlist/listOneGroupBuyingCollectionList.jsp"; // 成功轉交該頁面（或其他展示頁）
		    RequestDispatcher successView = req.getRequestDispatcher(url);
		    successView.forward(req, res);
		}

		
		
		
		// 新增收藏
		if ("insert".equals(action)) { // 來自addCollection.jsp的請求  

		    List<String> errorMsgs = new LinkedList<String>();
		    req.setAttribute("errorMsgs", errorMsgs);

		    /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		    
		    Integer gbId = null;
		    try {
		        gbId = Integer.valueOf(req.getParameter("gbId").trim());
		    } catch (Exception e) {
		        errorMsgs.add("團購編號請輸入有效數字");
		    }

		    Integer memId = null;
		    try {
		        memId = Integer.valueOf(req.getParameter("memId").trim());
		    } catch (Exception e) {
		        errorMsgs.add("會員編號請輸入有效數字");
		    }

		    // 若有錯誤，回傳新增頁面
		    if (!errorMsgs.isEmpty()) {
		        RequestDispatcher failureView = req.getRequestDispatcher("/groupbuyingcollectionlist/addGroupBuyingCollectionList.jsp");
		        failureView.forward(req, res);
		        return;
		    }
		    
		    GroupBuyingCollectionListService svc = new GroupBuyingCollectionListService();

		    // 新增前判斷是否已存在
		    GroupBuyingCollectionListVO existing = svc.getOneCollection(gbId, memId);
		    if (existing != null) {
		        errorMsgs.add("此收藏已存在，無法重複新增！");
		        RequestDispatcher failureView = req.getRequestDispatcher("/groupbuyingcollectionlist/addGroupBuyingCollectionList.jsp");
		        failureView.forward(req, res);
		        return;
		    }
		    
		    

		    /***************************2.開始新增資料***************************************/
//		    GroupBuyingCollectionListService svc = new GroupBuyingCollectionListService();
		    svc.addCollection(gbId, memId);

		    /***************************3.新增完成,準備轉交(Send the Success view)***********/
		    req.setAttribute("success", "- (新增成功)");
		    String url = "/groupbuyingcollectionlist/listAllGroupBuyingCollectionList.jsp";
		    RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交收藏清單列表頁
		    successView.forward(req, res);             
		}


		

		
		
		// 刪除收藏
		if ("delete".equals(action)) { // 來自收藏清單列表頁的請求

		    List<String> errorMsgs = new LinkedList<String>();
		    // 將錯誤訊息放入 request 範圍
		    req.setAttribute("errorMsgs", errorMsgs);

		    /***************************1.接收請求參數***************************************/
		    Integer gbId = Integer.valueOf(req.getParameter("gbId"));
		    Integer memId = Integer.valueOf(req.getParameter("memId"));

		    /***************************2.開始刪除資料***************************************/
		    GroupBuyingCollectionListService collectionSvc = new GroupBuyingCollectionListService();
		    collectionSvc.deleteCollection(gbId, memId);

		    /***************************3.刪除完成,準備轉交(Send the Success view)***********/								
		    String url = "/groupbuyingcollectionlist/listAllGroupBuyingCollectionList.jsp";
		    RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後轉交收藏清單列表頁
		    successView.forward(req, res);
		}


//		// 查詢單筆收藏（判斷是否已收藏）
//		if ("check_If_Collected".equals(action)) { // 來自前端判斷是否已收藏請求
//
//		    List<String> errorMsgs = new LinkedList<String>();
//		    req.setAttribute("errorMsgs", errorMsgs);
//
//		    /***************************1. 接收並驗證輸入參數 ******************************/
//		    Integer gbId = null;
//		    Integer memId = null;
//
//		    try {
//		        gbId = Integer.valueOf(req.getParameter("gbId"));
//		    } catch (Exception e) {
//		        errorMsgs.add("團購編號格式錯誤");
//		    }
//		    try {
//		        memId = Integer.valueOf(req.getParameter("memId"));
//		    } catch (Exception e) {
//		        errorMsgs.add("會員編號格式錯誤");
//		    }
//
//		    if (!errorMsgs.isEmpty()) {
//		        RequestDispatcher failureView = req.getRequestDispatcher("/groupbuyingcollectionlist/checkResult.jsp");
//		        failureView.forward(req, res);
//		        return;
//		    }
//
//		    /***************************2. 查詢是否已收藏 ***********************************/
//		    GroupBuyingCollectionListService svc = new GroupBuyingCollectionListService();
//		    GroupBuyingCollectionListVO vo = svc.getOneCollection(gbId, memId);
//
//		    /***************************3. 查詢結果放入 request ******************************/
//		    req.setAttribute("collectionVO", vo); // null 表示尚未收藏，有值表示已收藏
//
//		    /***************************4. 轉交結果頁面 *************************************/
//		    String url = "/groupbuyingcollectionlist/checkResult.jsp"; // 此 JSP 負責顯示是否收藏結果
//		    RequestDispatcher successView = req.getRequestDispatcher(url);
//		    successView.forward(req, res);
//		}

		
        
	}
}













