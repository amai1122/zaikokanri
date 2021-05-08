<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<jsp:useBean id="bn" scope="session" class="items.ItemsBean" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/在庫管理システム/css/itemInsert.css" type="text/css">
<title>商品登録</title>
</head>
<body>
	<h1>商品登録・編集</h1>
	<!-- 空白のエラーがあるかを確認する ****************************************** -->
	<% List<String> errorList = (List<String>)request.getAttribute("errorList");
    if (errorList != null) { %>
    	<div class="error">
        入力エラーがあります。
			<ul>
			<% for (String msg : errorList) { %>
				<li><%=msg %></li>
			<% } %>
			</ul>
		</div>
    <% } %>

	<form action="../servlet/items.InsertItems" name="insertfome" method="post">
	<table>
		<tr>
			<td>商品ＩＤ</td>
			<td><input type="text" name="item_id" value="${item_id}" ></td>
		</tr>
		<tr>
			<td>商品名</td>
			<td><input type="text" name="item_name" class="itemName" value="${item_name}" ></td>
		</tr>
		<tr>
			<td>商品単価</td>
			<td><input type="text" name="item_price" value="${item_price}" ></td>
		</tr>
		<tr>
			<td>商品原価</td>
			<td><input type="text" name="cost_price" value="${cost_price}" ></td>
		</tr>
		<tr>
			<td>在庫数</td>
			<td>${stock_qty}
			<input type="hidden" value= ${stock_qty } name="stock_qty" >
			</td>
		</tr>
	</table>
	<input type="hidden" value= ${flag} name="flag" >
	<input type="submit" value="保存" class="insert" />
	</form>
	<form action="../servlet/items.ItemInitialize" name ="itemform" method="post">
		<input type="submit" value="戻る" />
	</form>

</body>
</html>