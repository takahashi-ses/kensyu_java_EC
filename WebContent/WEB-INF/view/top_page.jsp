<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.TopItemsDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%
List<TopItemsDto> list = (List<TopItemsDto>)request.getAttribute("TOPITEMS");
String picture = "https://dummyimage.com/450x300/dee2e6/6c757d.jpg";

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOGEHOGE SHOP</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/top.css">
</head>
<body class="top_b">
	<header><%@ include file="header.jsp" %></header>

	<main>
    <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <% for (int i = 0; i < list.size(); i++) {
						TopItemsDto dto = list.get(i); %>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <% if (dto.getPicture() != null) { picture = "img/" + dto.getPicture(); } %>
                            <img class="card-img-top" src="<%= picture %>" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">商品名 : <%= dto.getName() %></h5>
                                    <!-- Product price-->
                                    価格 : <%= dto.getPrice() %>円
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center">
                                	<a class="btn btn-outline-dark mt-auto" href="ItemDetail?id=<%= dto.getId() %>">商品詳細</a>
                                </div>
                            </div>
                        </div>
                    </div>
					<% } %>
                </div>
            </div>
        </section>

	</main>

	<footer><%@ include file="footer.jsp" %></footer>


</body>
</html>