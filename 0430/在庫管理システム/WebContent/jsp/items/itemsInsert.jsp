<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録</title>
</head>
<body>
	<h1>商品登録・編集</h1>
	<!-- 空白のエラーがあるかを確認する ****************************************** -->
	List<String> errorList = (List<String>) request.getAttribute("errorList");


	<input type="submit" value="保存" />

	<form action="../servlet/商品マスタ.NewItems.java " method="post">
	<table>
		<tr>
			<td>商品ＩＤ</td>
			<td><input type="text" name="item_id" /></td>
		</tr>
		<tr>
			<td>商品名</td>
			<td><input type="text" name="item_name" /></td>
		</tr>
		<tr>
			<td>商品単価</td>
			<td><input type="text" name="item_price" /></td>
	</table>


</body>
</html>