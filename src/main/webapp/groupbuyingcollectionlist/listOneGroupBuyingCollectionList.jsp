<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.foodtimetest.groupbuyingcollectionlist.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  List<GroupBuyingCollectionListVO> collectionList = (List<GroupBuyingCollectionListVO>) request.getAttribute("collectionList");
%>


<html>
<head>
<title>收藏清單資料 - listOneGroupBuyingCollectionList.jsp</title>

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
	width: 600px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>收藏清單資料 - listOneGroupBuyingCollectionList.jsp</h3>
		 <h4><a href="groupbuyingcollectionlist/select_page.jsp"><img src="groupbuyingcollectionlist/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號 (memId)</th>
		<th>團購編號 (gbId)</th>
		<th>收藏時間 (createAt)</th>
	</tr>
	<c:forEach var="collectionVO" items="${collectionList}">
    <tr>
        <td>${collectionVO.memId}</td>
        <td>${collectionVO.gbId}</td>
        <td>${collectionVO.createAt}</td>
    </tr>
</c:forEach>

</table>

</body>
</html>
