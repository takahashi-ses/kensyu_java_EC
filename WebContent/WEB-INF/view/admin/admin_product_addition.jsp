<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<h2>商品追加</h2>
		<p>追加する商品の情報を入力してください</p>
	</div>
	<form action="AdminProductAddition" method="post" enctype="multipart/form-data">

		<div class="admin_form">
			<div class="form-group">
				<label>商品名 <span class="badge bg-danger">必須</span></label><br>
				<input type="text" name="name" class="form-control" value="" min="1" max="30" required>
			</div>

			<div class="form-group">
				<label>価格 <span class="badge bg-danger">必須</span></label><br>
				<input type="text" name="price" class="form-control" value="" required>
			</div>

			<div class="form-group">
				<label>個数 <span class="badge bg-danger">必須</span></label><br>
				<input type="text" name="stock" class="form-control" value="" required>
			</div>

			<div class="form-group">
				<label>画像 <span class="badge bg-danger">必須</span></label><br>
				<input type="file" name="picture" class="form-control" required>
			</div>

			<div class="form-group">
				<label>商品説明 <span class="badge bg-danger">必須</span></label>
				<textarea name="setumei" class="form-control" maxlength="255" required></textarea>
			</div>

			<div class="form-group">
				<label>詳細情報 <span class="badge bg-danger">必須</span></label>
				<textarea name="syousai" class="form-control" maxlength="255" required></textarea>
			</div>
		</div>

		<div class="admin_button_matome">
			<a class="btn btn-success" href="AdminTop">戻る</a>
			<input type="hidden" name="add_check" value="true">
			<button type="submit" class="btn btn-primary">追加する</button>
		</div>
	</form>
</body>
</html>