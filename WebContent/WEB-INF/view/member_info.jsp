<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String name = (String)request.getAttribute("NAME");
String message = "";
if (request.getAttribute("UPDATE_MESSAGE") != null) {
	message = (String)request.getAttribute("UPDATE_MESSAGE");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/common.css">
<body>
	<header><%@ include file="header.jsp" %></header>
	<main>

	<div class="container">
		<section>
			<h1>会員情報</h1>
			<hr>
			<p><span class="text-danger"><%= message %></span></p>
			<h3><a href="RegistrationInfo">登録情報を確認する</a></h3>
			<h3><a href="UpdateRegistration">登録情報を変更する</a></h3>
			<h3><a href="OrderHistory">注文履歴</a></h3>
		</section>
	</div>

	</main>
	<footer><%@ include file="footer.jsp" %></footer>
</body>
</html>