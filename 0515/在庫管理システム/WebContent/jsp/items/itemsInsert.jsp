<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="items" scope="session" class="items.ItemsBean" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/在庫管理システム/css/itemInsert.css"
	type="text/css">
<title>商品登録</title>
</head>
<body>
	<h1>商品登録・編集</h1>
	<!-- 空白のエラーがあるかを確認する ****************************************** -->
	<%
	List<String> errorList = (List<String>) request.getAttribute("errorList");
	%>

	<c:if test="${!empty errorList}">
	<div class="error">
		入力エラーがあります。
		<ul>
			<c:forEach items="${errorList}" var="msg">
				<li><c:out value="${msg }" /></li>
			</c:forEach>
		</ul>
	</div>
	</c:if>

	<form action="../items/InsertItems" name="insertfome" method="post">
		<table>
			<tr>
				<td>商品ＩＤ</td>
				<td><input type="text" name="item_id"
					value= "${param['item_id']}" /></td>
			</tr>
			<tr>
				<td>商品名</td>
				<td>
					<input type="text" name="item_name" class="itemName" value= <c:out value="${item_name}" /> />
					</td>
			</tr>
			<tr>
				<td>商品単価</td>
				<td><input type="text" name="item_price" value="${item_price}"></td>
			</tr>
			<tr>
				<td>商品原価</td>
				<td><input type="text" name="cost_price" value="${cost_price}"></td>
			</tr>
			<tr>
				<td>在庫数</td>
				<td><c:out value="${stock_qty }" />
				 <input type="hidden" value="${stock_qty}" name="stock_qty" /></td>
			</tr>
		</table>
		<input type="hidden" value="${flag}" name="flag" /> <input
			type="submit" value="保存" class="insert" />
	</form>

	<form action="../items/ItemInitialize" name="itemform" method="post">
		<input type="submit" value="戻る" />
	</form>


</body>
</html>