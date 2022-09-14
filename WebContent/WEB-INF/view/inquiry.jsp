<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String message = "";
	if (request.getAttribute("MESSAGE") != null) {
		request.getAttribute("MESSAGE");
	}
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body class="bg-dark">
	<div class="title_header">
		<h1>
			<a href="TopPage">HOGEHOGE SHOP</a>
		</h1>
	</div>
	<div class="container">
		<div class="main container-fluid">
			<div class="row bg-light text-dark py-5">
				<h3 class="fs-1 mb-5 text-center fw-bold"><%=message%></h3>

				<div class="col-md-8 offset-md-2">
					<h2 class="fs-1 mb-5 text-center fw-bold">お問い合わせ</h2>
					<form method="post" action="Inquiry">
						<div class="mb-3">
							<input type="text" class="form-control" name="name"
								placeholder="名前（必須）" value="" required max="20">
						</div>
						<div class="mb-3">
							<input type="email" class="form-control" name="email"
								placeholder="メールアドレス（必須）" value="" required>
						</div>
						<div class="mb-3">
							<input type="text" class="form-control" name="kenmei"
								placeholder="件名" value="">
						</div>
						<div class="mb-4">
							<textarea class="form-control" name="inquiry_post" rows="5"
								placeholder="メッセージを入力してください" required></textarea>
						</div>
						<div class="form-check mb-4">
							<input type="checkbox" name="agreement" id="agreement" value="1"
								required /> <label class="form-check-label" for="agreement">
								利用規約に同意します。<a href="#" target="_blank" rel="noopener noreferrer"
								class="text-decoration-underline text-dark">プライバシーポリシーはこちら</a>
							</label>
						</div>
						<div class="form-btn btn-flex">
							<div>
								<a href="Login" class="btn btn-outline-primary btn-block">戻る</a>
							</div>
							<div>
								<button type="submit" class="btn btn-primary">送信</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>