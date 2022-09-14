<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.AdminDto"%>
<%@ page import="java.util.List"%>

<%
String userName = (String)request.getAttribute("USER_NAME");
List<AdminDto> dtoList = (List<AdminDto>)request.getAttribute("REVIEW_DETAIL");
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
		<h2>登録者情報</h2>
		<p><span>登録者情報参照</span>&emsp;<%= userName %>さん</p>
		<h4><%= message %></h4>
	</div>

	<table class="admin_item_title">
		<tbody>
			<tr>
				<th>ID</th>
				<th>コメント</th>
				<th>星</th>
				<th>商品名</th>
				<th>日時</th>
				<th>-</th>
			</tr>

			<% for (int i=0; i<dtoList.size(); i++) {
					AdminDto dto = dtoList.get(i);
			%>

			<tr class="admin_item">
					<td>
						<%= dto.getReviewId() %>
					</td>
					<td>
						<%= dto.getComment() %>
					</td>
					<td>
						★<%= dto.getStar() %>
					</td>
					<td>
						<% if (dto.getItemsName() != null) { %>
						<%= dto.getItemsName() %>
						<% }else {%>
							削除済み
						<% } %>
					</td>
					<td>
						<%= dto.getReviewsCreated() %>
					</td>
					<td>
						<a class="btn btn-outline-danger mt-auto" href="AdminExcuteDeleteReview?deleteId=<%= dto.getReviewId() %>">削除</a>
					</td>
			</tr>
			<% } %>

		</tbody>
	</table>

	<div class="admin_button_matome">
		<a class="btn btn-success" href="AdminRegistrantInfoManagement">戻る</a>
	</div>

</body>
</html>