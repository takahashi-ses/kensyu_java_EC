<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Boolean error_display = false;
	if (request.getAttribute("DISPLAY") != null) {
		error_display = (Boolean) request.getAttribute("DISPLAY");
	}
%>
<html>
<head>
<title>ログイン</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<div class="title_header">
		<h1>
			<a href="TopPage">HOGEHOGE SHOP</a>
		</h1>
	</div>

	<div class="form-login">
		<p class="form-title">Login</p>
		<hr>
		<div>
			<%
				if (error_display) {
			%>
			<p>メールアドレスまたはパスワードが違います</p>
			<%
				}
			%>
		</div>
		<form action="ExecuteLogin" method="POST">
			<div class="form-input">
				<label>メールアドレス：</label><br> <input type="email" name="MAIL"
					maxlength="40" id="ID_MAIL" class="form-control" required>

			</div>
			<div class="form-input">
				<label>パスワード：</label><br> <input type="password"
					name="PASSWORD" maxlength="20" id="ID_PASSWORD" class="form-control" required>

			</div>
			<div class="form-input">
				<a href="NewRegistration">アカウント未登録の方はこちら</a>
			</div>
			<div class="form-btn">
				<button type="submit" class="btn-submit" id="ID_SUBMIT">ログイン</button>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="js/form.js"></script>
</body>
</html>
