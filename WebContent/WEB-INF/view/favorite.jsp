<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.FavoriteDto"%>
<%@ page import="java.util.List"%>

<%
	List<FavoriteDto> list = (List<FavoriteDto>) request.getAttribute("FAVORITE");
	String message = "お気に入りの商品はありません";
	if (list.size() != 0) {
		message = (String) request.getAttribute("MESSAGE");
	}
	String picture = "https://dummyimage.com/450x300/dee2e6/6c757d.jpg";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お気に入り</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/top.css">
</head>
<body>
	<header><%@ include file="header.jsp"%></header>
	<main>

		<section class="py-5">
			<div class="container mt-8">
				<h1><%=message%></h1>
				<br>
				<%
					for (int i = 0; i < list.size(); i++) {
						FavoriteDto dto = list.get(i);
				%>

				<div class="row">
					<div class="col-md-4">
						<%
							if (dto.getFavoriteItemPicture() != null) {
									picture = "img/" + dto.getFavoriteItemPicture();
								}
						%>
						<img class="img-fulied" src="<%=picture%>" alt="img" />
					</div>
					<div class="col-md-8">
						<h1>
							商品名 :
							<%=dto.getFavoriteItemName()%></h1>
						<br>
						<h2>
							価格 :
							<%=dto.getFavoriteItemPrice()%>円
						</h2>
						<br> <a class="btn btn-outline-dark mt-auto"
							href="ItemDetail?id=<%=dto.getItem_id()%>">商品詳細</a> <a
							class="btn btn-success"
							href="ExcuteDeleteFavorite?id=<%=dto.getItem_id()%>">お気に入りから外す</a>
					</div>
				</div>
				<%
					}
				%>
			</div>
		</section>

	</main>
	<footer><%@ include file="footer.jsp"%></footer>
	<script type="text/javascript" src="js/form.js"></script>
</body>
</html>