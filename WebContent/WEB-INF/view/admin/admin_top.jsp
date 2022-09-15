<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.AdminDto" %>
<%@ page import="java.util.List" %>

<%
List<AdminDto> dtoList = (List<AdminDto>)request.getAttribute("PRODUCT_LIST");
int sales = 0;
int roop = dtoList.size();
if (dtoList.size() >= 3){
	roop = 3;
}

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

	<header><%@ include file="admin_header.jsp" %></header>

	<div class="admin_subtitle">
		<h2>管理者画面TOP</h2>
		<span>出品商品情報</span> <span>&nbsp;-最近の出品-</span>
	</div>

	<table class="admin_item_title">
		<tbody>
			<tr>
				<th>日時</th>
				<th>商品名</th>
				<th>価格</th>
				<th>販売数</th>
				<th>在庫数</th>
			</tr>
			<% for (int i=0; i<roop; i++) {
				AdminDto dto = dtoList.get(i);
				sales = dto.getRetention_stock() - dto.getStock();
			%>


				<tr class="admin_item">
					<td><%= dto.getCreated() %></td>
					<td><%= dto.getName() %></td>
					<td><%= dto.getPrice() %></td>
					<td><%= sales %></td>
					<td><%= dto.getStock() %></td>
				</tr>

			<% } %>
		</tbody>
	</table>

	<div class="admin_button_matome">
		<a class="btn btn-success" href="AdminPastListing">過去の出品</a>
		<a class="btn btn-success" href="AdminProductAddition">商品追加</a>
		<a class="btn btn-success" href="#">商品削除</a>
		<a class="btn btn-success" href="AdminRegistrantInfoManagement">登録者情報管理</a>
	</div>
</body>
</html>