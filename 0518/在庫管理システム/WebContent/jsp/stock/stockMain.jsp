<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="stock.StockBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="/在庫管理システム/css/stockMain.css" type="text/css">
<title>在庫入出庫履歴</title>
</head>
<body>
	<h1>${"在庫入出庫履歴"}</h1>
	<div class="control">
		<div class="search">
			<form action="../stock/StockSearch" method="post">
			<label> 商品名検索：<input type="text"name="itemSearch" />
			<input type="submit" value="検索" />
			</label>
			</form>
		</div>
		<form action="/在庫管理システム/jsp/メニュー画面.jsp">
			<input type="submit" value="戻る" />
		</form>
	</div>

	<c:set var="stockList" value="${stockList }"></c:set>

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
		<c:set var="stockNo" value="1" />
		<c:if test= "${stockList != null}" >
			<c:forEach items="${stockList}" var="stock">
			<tr>
				<td><c:out value="${stockNo }"  /></td>
				<td>
					<form action="../stock/UpdateStock" name = "${'form' += stockNo }" method="post">
						<input type="hidden" value= "${stock.move_id }" name=Move_id />
						<a href="javascript:form<c:out value="${stockNo }" />.submit()" ><c:out value="${stock.move_id }" /></a>
					</form>
				</td>
				<td>

				<c:choose>
					<c:when test="${stock.reason_id == '1' }" >1:仕入</c:when>
					<c:when test="${stock.reason_id == '2' }" >2:売上</c:when>
					<c:when test="${stock.reason_id == '3' }" >3:棚卸</c:when>
					<c:when test="${stock.reason_id == '4' }" >4:そのた</c:when>
				</c:choose>
				</td>
				<td><c:out value="${stock.move_date }"  /></td>
				<td><c:out value="${stock.item_id }"  /></td>
				<td><c:out value="${stock.item_name }"  /></td>
				<td>
				<c:choose>
					<c:when test="${stock.in_or_out =='+' }" >＋:入庫</c:when>
					<c:otherwise>－:出庫</c:otherwise>
				</c:choose>
				</td>
				<td class="Move_qty">${stock.move_qty }</td>
				<td class="Stock_qty">${stock.stock_qty }</td>
			</tr>
			<c:set var="stockNo"  value="${stockNo+1 }" />
			</c:forEach>
		</c:if>
	</table>
</body>
</html>