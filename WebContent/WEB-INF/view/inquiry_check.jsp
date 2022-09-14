<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.InquiryDto" %>

<%
InquiryDto dto = (InquiryDto)request.getAttribute("INQUIRY");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
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
        <div class="col-md-8 offset-md-2">
            <h2 class="fs-1 mb-5 text-center fw-bold">お問い合わせ内容確認</h2>
            <form method="post" action="ExcuteInsertInquiry">
                <div class="mb-3">
                    <input type="hidden" name="name" value="<%= dto.getName() %>">
                    <p>お名前 : <br><%= dto.getName() %></p>
                </div>
                <div class="mb-3">
                    <input type="hidden" name="email" value="<%= dto.getEmail() %>">
                    <p>メールアドレス : <br><%= dto.getEmail() %></p>
                </div>
                <div class="mb-3">
                    <input type="hidden" name="kenmei" value="<%= dto.getKenmei() %>">
                    <p>件名 : <br><%= dto.getKenmei() %></p>
                </div>
                <div class="mb-4">
                	<input type="hidden" name="inquiry_post" value="<%= dto.getInquiry_post() %>">
                	<p>お問い合わせ内容 : <br><%= dto.getInquiry_post() %></p>
                </div>



                <div class="form-btn btn-flex">
                	<div>
	                	<button type="button" class="btn btn-primary" onclick="history.back()">戻る</button>
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