<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodtimetest.groupbuyingcollectionlist.model.*"%>

<% //Servlet 可能放入 request 的 collectionVO (輸入格式有錯誤時回傳)
   GroupBuyingCollectionListVO collectionVO = (GroupBuyingCollectionListVO) request.getAttribute("collectionVO");
%>
--<%= (collectionVO == null) %>--${collectionVO.memId}-- <!-- 範例檢查 -->

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增收藏清單 - addCollection.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>收藏清單新增 - addCollection.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>新增收藏清單資料:</h3>

<c:if test="${not empty errorMsgs}">
  <font style="color:red">請修正以下錯誤:</font>
  <ul>
    <c:forEach var="message" items="${errorMsgs}">
      <li style="color:red">${message}</li>
    </c:forEach>
  </ul>
</c:if>

<form method="post" action="groupbuyingcollectionlist.do" name="form1">
<table>
	<tr>
		<td>會員編號 (memId):</td>
		<td><input type="text" name="memId" value="<%= (collectionVO == null) ? "" : collectionVO.getMemId() %>" size="45"/></td>
	</tr>
	<tr>
		<td>團購編號 (gbId):</td>
		<td><input type="text" name="gbId" value="<%= (collectionVO == null) ? "" : collectionVO.getGbId() %>" size="45"/></td>
	</tr>
	<!-- 
	<tr>
		<td>收藏時間 (createAt):</td>
		<td><input type="text" name="createAt" id="f_date1" value="<%= (collectionVO == null || collectionVO.getCreateAt() == null) ? "" : collectionVO.getCreateAt() %>" size="45" readonly/></td>
	</tr>
	 -->
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</form>

<!-- datetimepicker 設定保持不變 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>
    $.datetimepicker.setLocale('zh');
    $('#f_date1').datetimepicker({
       theme: '',
       timepicker:false,
       step: 1,
       format:'Y-m-d H:i:s',
       value: '<%= (collectionVO == null || collectionVO.getCreateAt() == null) ? "" : collectionVO.getCreateAt() %>',
    });
</script>

</body>
</html>
