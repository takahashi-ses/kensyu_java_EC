<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.CartDto" %>
<%@ page import="java.util.List" %>

<%
List<CartDto> CartDtoList = (List<CartDto>)request.getAttribute("CART");
String picture = "https://dummyimage.com/450x300/dee2e6/6c757d.jpg";
int purchase_price = 0;
for (int i = 0; i < CartDtoList.size(); i++) {
	 CartDto dto = CartDtoList.get(i);
	 purchase_price += dto.getTotalPrice();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/common.css">
</head>
<body>
<header><%@ include file="header.jsp" %></header>
<main>
	<div class="container">
	<h2>カート内容</h2>
		<div>
			<ul>
				<li>カートの合計&emsp;<span><%= purchase_price %>円</span></li>
				<li>
					<form action="Cart" method="post">
						<input type="hidden" name="purchase-check" value="true">
						<% if (purchase_price > 0) { %>
							<button type="submit" class="btn btn-outline-primary btn-block" name="purchase">購入する</button>
						<% } else { %>
							<button type="submit" name="purchase" disabled >購入する</button>
						<% } %>
					</form>
				</li>
			</ul>
		</div>
        <div class="row cart-table">
            <div class="col-md">

                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">商品名</th>
                            <th scope="col">価格</th>
                            <th scope="col">数量</th>
                            <th scope="col">合計金額</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                    	 <% for (int i = 0; i < CartDtoList.size(); i++) {
                    		 CartDto dto = CartDtoList.get(i); %>
                        <tr>
                            <td scope="row">
                            	<% if (dto.getItems_picture() != null) { picture = "img/" + dto.getItems_picture(); } %>
                            	<img class="cart-img" src="<%= picture %>" alt="..." />
                            </td>
                            <td><%= dto.getItems_name() %></td>
                            <td><%= dto.getItems_price() %>円</td>
                            <td><%= dto.getNumber() %></td>
                            <td><%= dto.getTotalPrice() %>円</td>
                            <td>
                              <a class="btn btn-outline-danger mt-auto btn-delete" href="ExcuteDeleteCartItem?id=<%= dto.getId() %>">削除</a>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
	</div>
</main>
<footer><%@ include file="footer.jsp" %></footer>
<script type="text/javascript" src="js/form.js"></script>
</body>
</html>