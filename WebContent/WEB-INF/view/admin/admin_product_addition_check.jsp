<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.TopItemsDto"%>

<%
TopItemsDto dto = (TopItemsDto)request.getAttribute("ADD_CHECK");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/admin.css">
<title>HOGEHOGE SHOP</title>
</head>
<body>

	<header><%@ include file="admin_header.jsp"%></header>

	<div class="admin_subtitle">
		<h2>商品追加</h2>
		<p>以下の商品を追加します</p>
	</div>
	<form action="AdminExcuteProductAddition" method="post" enctype="multipart/form-data">

		<div class="admin_form">
			<div class="form-group">
				<label>商品名<br><%= dto.getName() %></label>
				<input type="hidden" name="name" class="form-control" value="<%= dto.getName() %>" >
			</div>

			<div class="form-group">
				<label>価格<br><%= dto.getPrice() %></label>
				<input type="hidden" name="price" class="form-control" value="<%= dto.getPrice() %>">
			</div>

			<div class="form-group">
				<label>個数<br><%= dto.getStock() %></label>
				<input type="hidden" name="stock" class="form-control" value="<%= dto.getStock() %>">
			</div>

			<div class="form-group">
				<label>画像<br><%= dto.getPicture() %></label>
				<input type="file" name="picture" class="form-control" required>
			</div>

			<div class="form-group">
				<label>商品説明<br><%= dto.getSetumei() %></label>
				<input type="hidden" name="setumei" class="form-control" value="<%= dto.getSetumei() %>">
			</div>

			<div class="form-group">
				<label>詳細情報<br><%= dto.getSyousai() %></label>
				<input type="hidden" name="syousai" class="form-control" value="<%= dto.getSyousai() %>">
			</div>
		</div>

		<div class="admin_button_matome">
			<a class="btn btn-success" href="AdminTop">戻る</a>
			<input type="hidden" name="add_check" value="true">
			<button type="submit" class="btn btn-primary">追加する</button>
		</div>
	</form>
</body>
</html>