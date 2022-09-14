<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.AdminDto"%>
<%@ page import="java.util.List"%>

<%
List<AdminDto> dtoList = (List<AdminDto>) request.getAttribute("PRODUCT_LIST");
int sales = 0;
String message = "";
if (request.getAttribute("MESSAGE") != null) {
	message = (String)request.getAttribute("MESSAGE");
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

	<header><%@ include file="admin_header.jsp"%></header>

	<div class="admin_subtitle">
		<h2>出品商品情報</h2>
		<br>
		<h3><%= message %></h3>
	</div>
	<form action="" method="post">
		<div class="admin_item">
			<table>
				<tbody>
					<tr class="admin_itemtitle">
						<th>日時</th>
						<th>商品名</th>
						<th>価格</th>
						<th>販売数</th>
						<th>在庫数</th>
						<th></th>

					</tr>
					<%
						for (int i = 0; i<dtoList.size(); i++) {
							AdminDto dto = dtoList.get(i);
							sales = dto.getRetention_stock() - dto.getStock();
					%>
						<tr class="admin_item">
							<td><%=dto.getCreated()%></td>
							<td><%=dto.getName()%></td>
							<td><%=dto.getPrice()%></td>
							<td><%=sales%></td>
							<td><%=dto.getStock()%></td>
							<td>
                              <a class="btn btn-outline-danger mt-auto" href="AdminExcuteDeleteItem?id=<%= dto.getId() %>">削除</a>
                            </td>
						</tr>
					<% } %>
				</tbody>
			</table>
		</div>
		<div class="admin_button_matome">

			<a class="btn btn-success" href="AdminTop">戻る</a>
			<a class="btn btn-success" href="AdminProductAddition">商品追加</a>
		</div>
</body>
</html>