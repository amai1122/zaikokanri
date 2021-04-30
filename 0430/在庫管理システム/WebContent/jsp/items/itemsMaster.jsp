<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="商品マスタ.ItemsBean"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品マスタ一覧</title>
</head>
<body>
	<h1>商品マスタ一覧</h1>
	<div>
		<form action="../servlet/商品マスタ.NewItems.java" method="post">
			<input type="submit" value="新規登録" />
		</form>
		<form action="../servlet/商品マスタ.ItemSearch.java" method="post">
			<label> 商品名検索：<input type="search"name="itemSearch"
				value="検索">
			</label>
		</form>
		<form action="/在庫管理システム/jsp/メニュー画面.jsp">
			<input type="submit" value="戻る" />
		</form>
	</div>

	<% List<ItemsBean> itemList = (List<ItemsBean>) request.getAttribute("itemList"); %>

	<!-- 表示のためにテーブル内容を取得 -->
	<table border="1">
		<tr>
			<th>Ｎｏ．</th>
			<th>商品ＩＤ</th>
			<th>商品名</th>
			<th>販売単価</th>
			<th>商品原価</th>
			<th>在庫数</th>
			<th>入出庫</th>
			<th>削除</th>
		</tr>
		<%
		int itemNo =1;
		if(itemList != null){
			for (ItemsBean item : itemList) {
		%>
			<tr>
				<td><%=itemNo%></td>
				<td><a href="../servlet/商品マスタ.NewItems.java"><%=item.getItem_id()%></a></td>

				<td><%=item.getItem_name()%></td>
				<td><%=item.getItem_price()%></td>
				<td><%=item.getCost_price()%></td>
				<td><%=item.getStock_qty()%></td>
				<td>
				<form action="../servlet/在庫入出庫.ItemSearch.java" method="post">
					<input type="submit" value="入出庫" />
				</form>
				</td>
				<td>
				<form action="../servlet/商品マスタ.ItemSearch.java" method="post">
					<input type="submit" value="削除" />
				</form>
				</td>
			</tr>
		<%
			itemNo++;
			}
		}
		%>
	</table>

</body>
</html>