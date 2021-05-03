<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/在庫管理システム/css/iteminsert.css" type="text/css">
<title>商品登録</title>
</head>
<body>
	<h1>商品登録・編集</h1>
	<!-- 空白のエラーがあるかを確認する ****************************************** -->
	<% List<String> errorList = (List<String>)request.getAttribute("errorList");
    if (errorList != null) { %>
    	<div class="error">
        空欄を入力してください。
			<ul>
			<% for (String msg : errorList) { %>
				<li><%=msg %></li>
			<% } %>
			</ul>
		</div>
    <% } %>

	<form action="../servlet/items.Insert_Items" name="insertfome" method="post">
	<table>
		<tr>
			<td>商品ＩＤ</td>
			<td><input type="text" name="item_id" value=<%=request.getAttribute("item_id") %> ></td>
		</tr>
		<tr>
			<td>商品名</td>
			<td><input type="text" name="item_name" value=<%=request.getAttribute("item_name") %> ></td>
		</tr>
		<tr>
			<td>商品単価</td>
			<td><input type="text" name="item_price" value=<%=request.getAttribute("item_price") %> ></td>
		</tr>
		<tr>
			<td>商品原価</td>
			<td><input type="text" name="cost_price" value=<%=request.getAttribute("cost_price") %> ></td>
		</tr>
		<tr>
			<td>在庫数</td>
			<td> <%=request.getAttribute("stock_qty") %> </td>
		</tr>
	</table>

	<input type="submit" value="保存" />
	</form>
	<form action="../servlet/items.ItemInitialize" name ="itemform" method="post">
		<input type="submit" value="戻る" />
	</form>

</body>
</html>