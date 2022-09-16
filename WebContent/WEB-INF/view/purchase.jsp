<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.CartDto"%>
<%@ page import="java.util.List"%>

<%
List<CartDto> CartDtoList = (List<CartDto>) request.getAttribute("CART");
int purchase_price = 0;
String picture = "https://dummyimage.com/450x300/dee2e6/6c757d.jpg";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入手続</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<header><%@ include file="header.jsp"%></header>
	<main>
	<div class="container">
		<h2>こちらの商品を購入します</h2>

		<div class="row mt-5">
			<div class="col-md">

				<table class="table cart-table">
					<thead class="thead-dark">
						<tr>
							<th scope="col"></th>
							<th scope="col">商品名</th>
							<th scope="col">価格</th>
							<th scope="col">数量</th>
							<th scope="col">金額</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < CartDtoList.size(); i++) {
								CartDto dto = CartDtoList.get(i);
								purchase_price += dto.getTotalPrice();
						%>
						<tr>
							<td scope="row">
                            	<% if (dto.getItems_picture() != null) { picture = "img/" + dto.getItems_picture(); } %>
                            	<img class="cart-img" src="<%= picture %>" alt="..." />
                            </td>
							<td><%=dto.getItems_name()%></td>
							<td><%=dto.getItems_price()%>円</td>
							<td><%=dto.getNumber()%></td>
							<td><%=dto.getTotalPrice()%>円</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
				<div>
					<h5 class="mt-5 text-center">
						購入金額&emsp;<span><%=purchase_price%>円</span>
					</h5>
				</div>
				<div class="form-btn btn-flex">
					<div>
						<a class="btn btn-success" href="Cart">カートへ戻る</a><br>
					</div>
					<form action="Purchase" method="post">
						<input type="hidden" name="purchase" value="true">
						<input type="hidden" name="purchase_price"
							value="<%=purchase_price%>">
						<button type="submit" class="btn btn-success">確定</button>
					</form>
				</div>


			</div>
		</div>
		</main>
		<script type="text/javascript" src="js/form.js"></script>
</body>
</html>