<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="model.UserInfoDto" %>

<%
UserInfoDto dto = (UserInfoDto)request.getAttribute("PASSWORD");

String message = "";
if (request.getAttribute("MESSAGE") != null) {
	message = (String)request.getAttribute("MESSAGE");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOGEHOGE SHOP</title>
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
		<p class="form-title">パスワード変更</p>
		<hr>
		<div>
        <div class="row">
            <div class="col-md">
                <form action="ExcuteUpdatePassword" method="POST">
                    <div class="form-group">
                        <label>以前のパスワードを入力してください：</label>
                        <input type="password" name="OLD_PASS" class="form-control" required>
                		<span class="error-message"><%= message %></span>
                    </div>
                    <div class="form-group">
                        <label>新しいパスワード：</label>
                        <input type="password" name="PASS" class="form-control" id="password" style="ime-mode:disabled" placeholder="Abcd1234" pattern="(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\d)[a-zA-Z\d]{8,}" title="半角英大文字・半角英小文字・半角数字いれずれも必ず含む8文字以上" required>
                    </div>
                    <div class="form-group">
                        <label>パスワード(確認)：</label>
                        <input type="password" name="PASS_CHECK" class="form-control" oninput="CheckPassword(this)" required>
                    </div>
                    <div class="form-btn">
						<div>
	                    	<input type="hidden" name="ID" value="<%= dto.getId() %>">
    	                	<input type="hidden" name="INPUT_OLD_PASS" value="<%= dto.getPass() %>">
        					<button type="submit" class="btn btn-outline-primary btn-block">OK</button>
						</div>
						<div class="mt-3">
							<a href="UpdateRegistration" class="btn btn-outline-primary btn-block">戻る</a>
						</div>
        			</div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="js/form.js"></script>
</body>
</html>