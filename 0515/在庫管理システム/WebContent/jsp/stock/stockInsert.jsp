<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/在庫管理システム/css/stockInsert.css" type="text/css">
<title>在庫入出庫登録・照会</title>
</head>
<body>
	<h1>在庫入出庫登録・照会</h1>
	<!-- 空白のエラーがあるかを確認する ****************************************** -->
	<% List<String> errorList = (List<String>)request.getAttribute("errorList");  %>

    <c:if test="${!empty errorList}">
    	<div class="error">
			<ul>
			<c:forEach items="${errorList}" var="msg">
				<li><c:out value="${msg}" /></li>
			</c:forEach>
			</ul>
		</div>
	</c:if>

	<form action="../stock/InsertStock" name="insertfome" method="post">
	<table>
		<tr>
			<td>入出庫ＩＤ</td>
			<td><input type="text" name="move_id" value= <c:out value="${requestScope.move_id }" /> ></td>
		</tr>
		<tr>
			<td>理由</td>
			<td><select name="reason_id" >

					<option value="1" <c:if test= "1 == ${request.reason_id }" >selected</c:if> >1:仕入</option>
					<option value="2" <c:if test= "2 == ${request.reason_id }" >selected</c:if> >2:売上</option>
					<option value="3" <c:if test= "3 == ${request.reason_id }" >selected</c:if> >3:棚卸</option>
					<option value="4" <c:if test= "4 == ${request.reason_id }" >selected</c:if> >4:その他</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>入出庫日</td>
			<td><input type="text" name="move_date" value= <c:out value="${requestScope.move_date }" /> ></td>
		</tr>
		<tr>
			<td>商品ＩＤ</td>
			<td><c:out value="${requestScope.item_id }" />
			<input type="hidden" value= <c:out value="${requestScope.item_id }" /> name=item_id ></td>
		</tr>
		<tr>
			<td>商品名</td>
			<td><c:out value="${requestScope.item_name }" />
			<input type="hidden" value= <c:out value="${requestScope.item_name }" /> name=item_name /></td>

		</tr>
		<tr>
			<td>販売単価</td>
			<td><c:out value="${requestScope.item_price}" />
			<input type="hidden" value= <c:out value="${requestScope.item_price }" /> name=item_price ></td>
		</tr>
		<tr>
			<td>商品原価</td>
			<td><c:out value="${requestScope.cost_price}" />
			<input type="hidden" value= ${requestScope.cost_price } name=cost_price ></td>
		</tr>
		<tr>
			<td>在庫数</td>
			<td><c:out value="${requestScope.stock_qty}" />
			<input type="hidden" value= ${requestScope.stock_qty } name=stock_qty ></td>
		</tr>
		<tr>
			<td>入庫/出庫</td>
			<td><label>
				<input type="radio" name="in_or_out" value="+" checked="checked" >＋：入庫
				<input type="radio" name="in_or_out" value="－"
				 	<c:if test= "${'-' == requestScope.in_or_out}" >checked="checked" </c:if> >－：出庫
				</label>
			</td>
		</tr>
		<tr>
			<td>入出庫数</td>
			<td><input type="text" name="move_qty" value=<c:out value="${requestScope.move_qty }" /> >
		</tr>

	</table>
	<c:if test="${'new' == requestScope.flag or 'error' == requestScope.flag }">

		<input type="submit" class= "insert" value="保存" />
	</c:if>

	</form>
	<form action="../stock/StockInitialize" name ="itemform" method="post">
		<input type="submit" value="戻る" />
	</form>

</body>
</html>