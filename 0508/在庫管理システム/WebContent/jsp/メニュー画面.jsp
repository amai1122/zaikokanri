<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="items" scope="session" class="items.ItemsBean" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="/在庫管理システム/css/menu.css"
	type="text/css">
<title>天井文具店在庫管理システム</title>
</head>
<body>
<br>
<br>
<h1 class="menu"><strong>天井文具店　在庫管理システム</strong></h1>

<div class="items" >
	<form action="../servlet/items.ItemInitialize" name ="itemform" method="post">
		<img src="/在庫管理システム/gif/items.gif">
		<a href="javascript:itemform.submit()" >商品マスタ</a><br>
	</form>
</div>
<br>
<div class="stock_move">
	<form action="../servlet/stock.StockInitialize" name ="stockform" method="post">
		<img src="/在庫管理システム/gif/stock.gif">
		<a  href="javascript:stockform.submit()">在庫入出庫</a>
	</form>
</div>

</body>
</html>