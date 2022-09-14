<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.OrderHistoryDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%
List<OrderHistoryDto> list = (List<OrderHistoryDto>)request.getAttribute("HISTORY");
String message = (String)request.getAttribute("MESSAGE");
String updateMessage = (String)request.getAttribute("UPDATE_MESSAGE");


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/common.css">
</head>
<body>
	<header><%@ include file="header.jsp" %></header>
	<main>
		<div class="container">
		<h1><%= message %></h1>
		<h4><span><%= updateMessage %></span></h4>
		<% for (int i = 0; i < list.size(); i++) {
			OrderHistoryDto dto = list.get(i); %>
			<table>
	            <tbody>
	              <tr>
	                <td rowspan="5" class="table-img">
	                    <a href="#?id=<%= dto.getItemId() %>">
	                    <% if (dto.getPicture() == null ) { %>
	                      <img src="img/noimage.png">
	                    <% }else { %>
	                      <img src="img/<%= dto.getPicture() %>" >
	                    <% } %>
	                    </a>
	                </td>
	                <td>商品名 : <%= dto.getItemName() %></td>
	              </tr>
	              <tr>
	                <td>金額 : <%= dto.getPrice() %></td>
	              </tr>
	              <tr>
	                <td>購入日 : <%= dto.getCreated() %></td>
	              </tr>
	              <tr>
	                <td>
	                    <a class="btn btn-success" href="ReviewPosts?id=<%= dto.getItemId() %>">商品レビューを書く</a>
	                </td>
	              </tr>
	              <tr>
	                <td>
	                    <a class="btn btn-warning" href="ItemDetail?id=<%= dto.getItemId() %>">再度購入</a>
	                </td>
	              </tr>
	            </tbody>
	          </table>
	          <% } %>
		</div>

	</main>

<footer><%@ include file="footer.jsp" %></footer>

</body>
</html>