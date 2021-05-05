<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="items.ItemsBean"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="/在庫管理システム/css/itemMaster.css" type="text/css">
<title>商品マスタ一覧</title>
</head>
<body>
	<div class="all">
	<h1 class="master">商品マスタ一覧</h1>
		<div class="newitem">
		<form action="../servlet/items.NewItems" method="post">
			<input type="submit" value="新規登録" class="new" />
		</form>
		</div>
		<div class="search">
		<form action="../servlet/items.ItemSearch" method="post">
			<label> 商品名検索：<input type="text"name="itemSearch" />
			<input type="submit" value="検索" />
			</label>
		</form>
		</div>
		<div class="return">
		<form action="/在庫管理システム/jsp/メニュー画面.jsp">
			<input type="submit" value="戻る"  />
		</form>
		</div>


	<% List<ItemsBean> itemList = (List<ItemsBean>) request.getAttribute("itemList"); %>

	<!-- 表示のためにテーブル内容を取得 -->
	<table>
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
				<td>
					<form action="../servlet/items.UpdateItems" name ="form<%=itemNo%>" method="post">
						<input type="hidden" value= <%=item.getItem_id()%> name=item_id >
						<a href="javascript:form<%=itemNo%>.submit()" ><%=item.getItem_id()%></a>
					</form>
				</td>
				<td><%=item.getItem_name()%></td>
				<td class="price"><%=item.getItem_price()%></td>
				<td class="c_price"><%=item.getCost_price()%></td>
				<td class="stock"><%=item.getStock_qty()%></td>
				<td>

				<form action="../servlet/stock.NewHistory" method="post">
					<input type="hidden" value= <%=item.getItem_id()%> name=item_id >
					<input type="submit" value="入出庫" />
				</form>
				</td>
				<td>
				<form action="../servlet/items.DeleteItems" method="post">
					<input type="hidden" value= <%=item.getItem_id()%> name=item_id >
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
	</div>
</body>
</html>