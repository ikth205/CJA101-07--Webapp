<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.foodtimetest.groupbuyingcollectionlist.model.*" %>

<%
    GroupBuyingCollectionListService collectionSvc = new GroupBuyingCollectionListService();
    List<GroupBuyingCollectionListVO> list = collectionSvc.getAll();
    pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有收藏清單 - listAllCollections.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有收藏清單 - listAllCollections.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號 (memId)</th>
		<th>團購編號 (gbId)</th>
		<th>收藏時間 (createAt)</th>
	
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="collectionVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${collectionVO.memId}</td>
			<td>${collectionVO.gbId}</td>
			<td>${collectionVO.createAt}</td>
<!--  			<td>
			  <form method="post" action="<%=request.getContextPath()%>/groupbuyingcollectionlist.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="memId"  value="${collectionVO.memId}">
			     <input type="hidden" name="gbId"  value="${collectionVO.gbId}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </form>
			</td>
-->			
			<td>
			  <form method="post" action="groupbuyingcollectionlist.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="memId"  value="${collectionVO.memId}">
			     <input type="hidden" name="gbId"  value="${collectionVO.gbId}">
			     <input type="hidden" name="action" value="delete">
			  </form>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
