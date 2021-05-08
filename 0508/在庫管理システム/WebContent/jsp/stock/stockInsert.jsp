<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
	<% List<String> errorList = (List<String>)request.getAttribute("errorList");
    if (errorList != null) { %>
    	<div class="error">
			<ul>
			<% for (String msg : errorList) { %>
				<li><%=msg %></li>
			<% } %>
			</ul>
		</div>
    <% } %>

	<form action="../servlet/stock.InsertStock" name="insertfome" method="post">
	<table>
		<tr>
			<td>入出庫ＩＤ</td>
			<td><input type="text" name="move_id" value=<%=request.getAttribute("move_id") %> ></td>
		</tr>
		<tr>
			<td>理由</td>
			<td><select name="reason_id" >
					<option value="1"<% if("1".equals(request.getAttribute("reason_id"))){ %>selected<% } %>>1:仕入</option>
					<option value="2"<% if("2".equals(request.getAttribute("reason_id"))){ %>selected<% } %>>2:売上</option>
					<option value="3"<% if("3".equals(request.getAttribute("reason_id"))){ %>selected<% } %>>3:棚卸</option>
					<option value="4"<% if("4".equals(request.getAttribute("reason_id"))){ %>selected<% } %>>4:その他</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>入出庫日</td>
			<td><input type="text" name="move_date" value=<%=request.getAttribute("move_date") %> ></td>
		</tr>
		<tr>
			<td>商品ＩＤ</td>
			<td><%=request.getAttribute("item_id") %>
			<input type="hidden" value= <%=request.getAttribute("item_id") %> name=item_id ></td>
		</tr>
		<tr>
			<td>商品名</td>
			<td><%=request.getAttribute("item_name") %>
			<input type="hidden" value= <%=request.getAttribute("item_name") %> name=item_name ></td>

		</tr>
		<tr>
			<td>販売単価</td>
			<td><%=request.getAttribute("item_price") %>
			<input type="hidden" value= <%=request.getAttribute("item_price") %> name=item_price ></td>
		</tr>
		<tr>
			<td>商品原価</td>
			<td><%=request.getAttribute("cost_price") %>
			<input type="hidden" value= <%=request.getAttribute("cost_price") %> name=cost_price ></td>
		</tr>
		<tr>
			<td>在庫数</td>
			<td> <%=request.getAttribute("stock_qty") %>
			<input type="hidden" value= <%=request.getAttribute("stock_qty") %> name=stock_qty ></td>
		</tr>
		<tr>
			<td>入庫/出庫</td>
			<td><label>
				<input type="radio" name="in_or_out" value="+" checked="checked" >＋：入庫
				<input type="radio" name="in_or_out" value="－"
				 	<% if("-".equals(request.getAttribute("in_or_out"))){ %>checked="checked" <%}%> >－：出庫
				</label>
			</td>
		</tr>
		<tr>
			<td>入出庫数</td>
			<td> <input type="text" name="move_qty" value=<%=request.getAttribute("move_qty") %> >
		</tr>

	</table>
	<% if( "new".equals(request.getAttribute("flag")) || "error".equals(request.getAttribute("flag"))){
	%>
		<input type="submit" class= "insert" value="保存" />
	<% } %>

	</form>
	<form action="../servlet/stock.StockInitialize" name ="itemform" method="post">
		<input type="submit" value="戻る" />
	</form>

</body>
</html>