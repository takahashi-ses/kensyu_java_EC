<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.ReviewsDto"%>
<%@ page import="model.OrderHistoryDto"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="javax.servlet.http.HttpSession"%>



<%
	List<ReviewsDto> userReviewList = (List<ReviewsDto>) request.getAttribute("REVIEW_POST_USER");
	HashMap<String, Integer> map = (HashMap<String, Integer>) request.getAttribute("REVIEW_POST_ID");
	OrderHistoryDto historyDto = (OrderHistoryDto) request.getAttribute("REVIEW_ITEM");

	String message = "";
	if (request.getAttribute("MESSAGE") != null) {
		message = (String) request.getAttribute("MESSAGE");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レビュー投稿</title>
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
		<div class="form">

			<h2 class="form-title">
				レビュー投稿&emsp;&emsp;<span><%=message%></span>
			</h2>


			<div class="row">
				<div class="col-md">
					<form action="ExcuteReviewPosts" method="post">
						<div class="form-group">
							<label>商品名：</label> <input type="text" class="form-control"
								value="<%=historyDto.getItemName()%>" disabled>
						</div>
						<div class="form-group">
							<label>星数：</label> <select class="form-control" name="star">
								<option value="5">★5</option>
								<option value="4">★4</option>
								<option value="3">★3</option>
								<option value="2">★2</option>
								<option value="1">★1</option>
							</select>
						</div>
						<div class="form-group">
							<label>レビュー：</label>
							<textarea class="form-control" name="comment" rows="3" required></textarea>
						</div>
						<div class="form-btn btn-flex">
							<div>
								<a href="Login" class="btn btn-outline-primary btn-block">戻る</a>
							</div>
							<div>
								<input type="hidden" name="user_id"
									value="<%=map.get("USER_ID")%>"> <input type="hidden"
									name="item_id" value="<%=map.get("ITEM_ID")%>">
								<button type="submit" class="btn btn-outline-primary btn-block">投稿する</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

		<br> <br>
		<hr>
		<br>
		<h2>過去の投稿</h2>
		<div class="row">
			<div class="col-md">

				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">商品名</th>
							<th scope="col">★数</th>
							<th scope="col">レビュー内容</th>
							<th scope="col">投稿日</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < userReviewList.size(); i++) {
								ReviewsDto dto = userReviewList.get(i);
						%>
						<tr>
							<th scope="row"><%=dto.getReviewsItem_name()%></th>
							<td>★<%=dto.getReviewsStar()%></td>
							<td><%=dto.getReviewsComment()%></td>
							<td><%=dto.getReviewsCreated()%></td>
							<td><a class="btn btn-outline-danger mt-auto btn-delete"
								href="ExcuteDeleteReview?deleteId=<%=dto.getReviewsId()%>">削除</a>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
		<br>

	</div>

	</main>
	<footer><%@ include file="footer.jsp"%></footer>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>