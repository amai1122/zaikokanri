<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<img src="/在庫管理システム/gif/items.gif">
	<a href="/在庫管理システム/jsp/items/itemsMaster.jsp">商品マスタ</a><br>
	<a href="/servlet/商品マスタ.ItemInitialize">商品マスタ</a><br>
</div>
<br>
<div class="stock_move">
	<img src="/在庫管理システム/gif/stock.gif">
	<a  href="/在庫管理システム/jsp/stock/リンク先">在庫入出庫</a>
</div>

</body>
</html>