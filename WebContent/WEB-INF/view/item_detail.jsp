<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.ItemDetailDto"%>
<%@ page import="model.ReviewsDto"%>
<%@ page import="java.util.List"%>

<%
	ItemDetailDto itemDto = (ItemDetailDto) request.getAttribute("DETAIL");
	String picture = "img/noimage.jpg";

	ReviewsDto reviewsDto = (ReviewsDto) request.getAttribute("REVIEWS");
	List<ReviewsDto> reviewsDtoList = (List<ReviewsDto>) request.getAttribute("COMMENT");

	String addMessage = (String) request.getAttribute("ADD_MESSAGE");
	String favoriteMessage = (String) request.getAttribute("FAVORITE_MESSAGE");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/top.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<header><%@ include file="header.jsp"%></header>

	<section class="py-5 form">
		<div class="container mt-8">
			<div class="row ">
				<div class="col-md-4  img-hidden">
					<%
						if (itemDto.getItemsPicture() != null) {
							picture = "img/" + itemDto.getItemsPicture();
						}
					%>
					<img class="img-fulied" src="<%=picture%>" alt="img" />
				</div>
				<div class="col-md-8">
					<h4>
						商品名 :
						<%=itemDto.getItemsName()%></h4>
					<br>
					<h5>
						価格 :
						<%=itemDto.getItemsPrice()%>円
					</h5>
					<br>
					<h5>
						レビュー :
						<%=reviewsDto.getReviewsStarAvg()%>
						/ 5 (<%=reviewsDto.getReviewsStarCount()%>人中)
					</h5>
					<br>
					<form action="ExcuteAddCart" method="get">
						<select name="number">
							<%
								for (int i = 1; i <= itemDto.getItemsStock(); i++) {
							%>
							<option value="<%=i%>"><%=i%>個
							</option>
							<%
								}
							%>
						</select> <input type="hidden" name="item_id"
							value="<%=itemDto.getItemsId()%>">
						<button type="submit" class="btn-outline-primary btn-block">カートに入れる</button>
						<br>
						<span><%=addMessage%></span>
					</form>
					<div class="form-btn btn-flex">
						<div>
							<a class="btn btn-success" href="Cart">購入に進む</a><br>
						</div>
						<div>
							<a class="btn btn-success" href="ExcuteAddFavorite?id=<%=itemDto.getItemsId()%>">お気に入りに追加</a><br>
							<span><%=favoriteMessage%></span>
						</div>
					</div>



				</div>
			</div>
			<div class="border border-info mt-4">
				<h4>商品の詳細</h4>
				<div class="overflow-hidden" style="height: 100px; padding: 12px;">
					<p><%=itemDto.getItemsSyousai()%></p>
				</div>
			</div>
			<div class="border border-info mt-4">
				<h4>商品の説明</h4>
				<div class="overflow-hidden" style="height: 100px; padding: 12px;">
					<p><%=itemDto.getItemsSetumei()%></p>
				</div>
			</div>
			<div class="border border-info mt-4">
				<h4>口コミ</h4>
				<div style="padding: 12px;">
					<%
						for (int i = 0; i < reviewsDtoList.size(); i++) {
							ReviewsDto dto = reviewsDtoList.get(i);
					%>

					<h5><%=dto.getReviewsNickname()%>さん&emsp;★&nbsp;<%=dto.getReviewsStar()%></h5>
					<p><%=dto.getReviewsComment()%></p>

					<%
						}
					%>
				</div>
			</div>

		</div>

	</section>

	<footer><%@ include file="footer.jsp"%></footer>
</body>
</html>