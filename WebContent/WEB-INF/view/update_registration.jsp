<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.UserInfoDto"%>

<%
	UserInfoDto dto = (UserInfoDto) request.getAttribute("UPDATE");
	String name = dto.getName();
	String name_kana = dto.getName_kana();
	String nickname = dto.getNickname();
	String sex = String.valueOf(dto.getSex());
	String birthday = dto.getBirthday();
	String zipcode = dto.getZipcode();
	String address = dto.getAddress();
	String tell = dto.getTell();
	String email = dto.getEmail();
	String id = String.valueOf(dto.getId());
	String password = dto.getPass();

	String emailErrorMessage = "";

	if (request.getAttribute("ERROR_MESSAGE") != null) {
		emailErrorMessage = (String) request.getAttribute("ERROR_MESSAGE");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録情報変更</title>
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
	<div class="form">
		<h2 class="form-title">会員情報変更</h2>

		<form action="PasswordChange" method="post">
			<input type="hidden" name="ID" value="<%=id%>"> <input
				type="hidden" name="PASS" value="<%=password%>">
			<div style="text-align: end;">
				<button type="submit" class="btn btn-outline-primary btn-block">パスワードを変更する</button>
			</div>
		</form>

		<div class="row">
			<div class="col-md">
				<form action="ExecuteUpdateRegistration" method="POST">
					<div class="form-group">
						<label>氏名：</label> <input type="text" name="NAME"
							class="form-control" placeholder="例）山田太郎" required max="20"
							value="<%=name%>">
					</div>
					<div class="form-group">
						<label>氏名(フリガナ)：</label> <input type="text" name="KANA"
							class="form-control" placeholder="例）ヤマダタロウ" required max="30"
							pattern="[ァ-ヴー\s　]+" title="カタカナ" value="<%=name_kana%>">
					</div>
					<div class="form-group">
						<label>ニックネーム：</label> <input type="text" name="NICK_NAME"
							class="form-control" required max="20" value="<%=nickname%>">
					</div>
					<div class="form-group">
						<label>性別：</label>
						<%
							if (sex.equals("1")) {
						%>
						<input type="radio" id="male" name="SEX" value="1" checked
							required>
						<%
							} else {
						%>
						<input type="radio" id="male" name="SEX" value="1" required>
						<%
							}
						%>
						<label for="male">男性</label>
						<%
							if (sex.equals("2")) {
						%>
						<input type="radio" id="female" name="SEX" value="2" checked
							required>
						<%
							} else {
						%>
						<input type="radio" id="female" name="SEX" value="2" required>
						<%
							}
						%>
						<label for="female">女性</label>
						<%
							if (sex.equals("3")) {
						%>
						<input type="radio" id="others" name="SEX" value="3" checked
							required>
						<%
							} else {
						%>
						<input type="radio" id="others" name="SEX" value="3" required>
						<%
							}
						%>
						<label for="others">その他</label>
					</div>
					<div class="form-group">
						<label>生年月日：</label> <input type="date" name="BIRTHDAY"
							class="form-control" required value="<%=birthday%>">
					</div>
					<div class="form-group">
						<label>住所：</label> <input type="text" name="ZIPCODE"
							class="form-control" style="ime-mode: disabled"
							placeholder="123-4567" pattern="\d{3}-?\d{4}" title="郵便番号"
							required value="<%=zipcode%>"> <input type="text"
							name="ADDRESS" class="form-control"
							placeholder="宮城県〇〇市〇〇区〇〇〇町X-X-X 〇〇ビル XXX号室" required
							value="<%=address%>">
					</div>
					<div class="form-group">
						<label>電話番号：</label> <input type="text" name="TELL"
							class="form-control" placeholder="0X0-1234-5678" required
							value="<%=tell%>">
					</div>
					<div class="form-group">
						<label>メールアドレス：&emsp;</label><span class="error-message"><%=emailErrorMessage%></span><input
							type="email" name="MAIL" class="form-control"
							placeholder="hogehoge@exam.com" required value="<%=email%>">
					</div>
					<div class="form-btn btn-flex">
						<div>
							<a href="MemberInfo" class="btn btn-outline-primary btn-block">戻る</a>
						</div>
						<div>
							<input type="hidden" name="ID" value="<%=id%>">
							<button type="submit" class="btn btn-outline-primary btn-block">更新する</button>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>

	<footer><%@ include file="footer.jsp"%></footer>
	<script type="text/javascript" src="js/form.js"></script>
</body>
</html>