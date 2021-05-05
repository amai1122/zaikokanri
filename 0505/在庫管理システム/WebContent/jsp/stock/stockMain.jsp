<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="stock.StockBean"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="/在庫管理システム/css/stockMain.css" type="text/css">
<title>在庫入出庫履歴</title>
</head>
<body>
	<h1>在庫入出庫履歴</h1>
	<div class="control">
		<div class="search">
			<form action="../servlet/stock.StockSearch" method="post">
			<label> 商品名検索：<input type="text"name="itemSearch" />
			<input type="submit" value="検索" />
			</label>
			</form>
		</div>
		<form action="/在庫管理システム/jsp/メニュー画面.jsp">
			<input type="submit" value="戻る" />
		</form>
	</div>

	<% List<StockBean> stockList = (List<StockBean>) request.getAttribute("stockList"); %>

	<!-- 表示のためにテーブル内容を取得 -->
	<table>
		<tr>
			<th>Ｎｏ．</th>
			<th>入出庫ＩＤ</th>
			<th>理由</th>
			<th>入出庫日</th>
			<th>商品ＩＤ</th>
			<th>商品名</th>
			<th>入庫/出庫</th>
			<th>入出庫数</th>
			<th>在庫残数</th>
		</tr>
		<%
		int stockNo =1;
		if(stockList != null){
			for (StockBean stock : stockList) {
		%>
			<tr>
				<td><%=stockNo%></td>
				<td>
					<form action="../servlet/stock.StockMoveInsert" name ="form<%=stockNo%>" method="post">
						<input type="hidden" value= <%=stock.getMove_id()%> name=Move_id >
						<a href="javascript:form<%=stockNo%>.submit()" ><%=stock.getMove_id()%></a>
					</form>
				</td>
				<td>
				<% if("1".equals(stock.getReason_id())){ %>
					1:仕入
				<% }else if("2".equals(stock.getReason_id())){ %>
					2:売上
				<% }else if("3".equals(stock.getReason_id())){ %>
					3:棚卸
				<% }else if("4".equals(stock.getReason_id())){ %>
					4:そのた
				<% } %>
				</td>
				<td><%=stock.getMove_date()%></td>
				<td><%=stock.getItem_id()%></td>
				<td><%=stock.getItem_name()%></td>
				<td>
				<% if("+".equals(stock.getIn_or_out())){ %>
					＋:入庫
				<% }else{ %>
					－:出庫
				<% } %>
				</td>
				<td class="Move_qty"><%=stock.getMove_qty()%></td>
				<td class="Stock_qty"><%=stock.getStock_qty()%></td>
			</tr>
			<%
			stockNo++;
			}
		}
		%>
	</table>
</body>
</html>