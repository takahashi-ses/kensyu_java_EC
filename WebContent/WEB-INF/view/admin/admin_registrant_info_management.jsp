<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.AdminDto"%>
<%@ page import="java.util.List"%>

<%
List<AdminDto> userList = (List<AdminDto>)request.getAttribute("USER_LIST");
List<AdminDto> userReviewList = (List<AdminDto>)request.getAttribute("USER_REVIEW_LIST");
int reviewNum = 0;
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
		<span>登録者情報参照</span>
	</div>

	<table class="admin_item_title">
		<tbody>
			<tr>
				<th>ID</th>
				<th>名前</th>
				<th>購入金額</th>
				<th>購入回数</th>
				<th>レビュー数</th>
				<th>レビュー詳細</th>
			</tr>

			<% for (int i=0; i<userList.size(); i++) {
					AdminDto dto = userList.get(i);
					for (int j=0; j<userReviewList.size(); j++){
						AdminDto reviewDto = userReviewList.get(j);
							if (dto.getId() == reviewDto.getUserReviewId()) {
								reviewNum = reviewDto.getReviewNumber();
							}
					}
			%>

			<tr class="admin_item">
					<td>
						<%= dto.getId() %>
					</td>
					<td>
						<%= dto.getName() %>
					</td>
					<td>
						<%= dto.getPurchase_price() %>
					</td>
					<td>
						<%= dto.getPurchase_number() %>
					</td>
					<td>
						<%= reviewNum %>
					</td>
					<td>
						<form action="AdminReviewList" method="post">
							<input type="hidden" name="name" value="<%= dto.getName() %>">
							<input type="hidden" name="id" value="<%= dto.getId() %>">
							<button type="submit">詳細</button>
						</form>
					</td>
			</tr>
			<% } %>

		</tbody>
	</table>
</body>
</html>