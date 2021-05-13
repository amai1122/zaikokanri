<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="items.ItemsBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="bn" scope="session" class="items.ItemsBean" />
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
		<form action="../items/NewItems" method="post">
			<input type="hidden" value= "new" name="flag" >
			<% bn.setItem_id("");
			bn.setItem_name("");
			bn.setItem_price("0");
			bn.setCost_price("0");
			bn.setStock_qty("0");
			bn.setError("");
			session.setAttribute("items",bn);

			%>

			<input type="submit" value="新規登録" class="new" />
		</form>
		</div>
		<div class="search">
		<form action="../items/ItemSearch" method="post">
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
				<!-- 番号表示 -->
				<td>${itemNo } </td>
				<!-- 商品ID表示 -->
				<td>
					<form action="../items/UpdateItems" name ="form<%=itemNo %>" method="post">
						<input type="hidden" value= <%=item.getItem_id()%> name=item_id >
						<input type="hidden" value= "update" name="flag" >
						<a href="javascript:form<%=itemNo %>.submit()" ><%=item.getItem_id()%></a>
					</form>
				</td>
				<!-- 商品名表示 -->
				<td><pre><%=item.getItem_name()%></pre></td>
				<!-- 定価表示 -->
				<td class="price"><pre><label><%=item.getItem_price()%></label></pre></td>
				<!-- 原価表示 -->
				<td class="c_price"><pre><%=item.getCost_price()%></pre></td>
				<!-- 在庫数表示 -->
				<td class="stock"><pre><%=item.getStock_qty()%></pre></td>
				<td>

				<form action="../stock/NewStock" method="post">
					<input type="hidden" value= <%=item.getItem_id()%> name=item_id >
					<input type="hidden" value= "new" name="flag" >
					<input type="submit" value="入出庫" />
				</form>
				</td>
				<td>
				<form action="../items/DeleteItems" method="post">
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