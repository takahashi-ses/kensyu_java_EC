<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.UserInfoDto"%>

<%
	String name = "";
	String name_kana = "";
	String nickname = "";
	String sex = "";
	String birthday = "";
	String zipcode = "";
	String address = "";
	String tell = "";
	String mail = "";

	String emailErrorMessage = "";

	boolean validation = false;
	boolean modify = false;
	if (request.getAttribute("VALIDATE") != null) {
		validation = (Boolean) request.getAttribute("VALIDATE");
	}
	if (request.getAttribute("MODIFY") != null) {
		modify = (Boolean) request.getAttribute("MODIFY");
		session = request.getSession();
		UserInfoDto dto = (UserInfoDto) session.getAttribute("USER_DATA");

		name = dto.getName();
		name_kana = dto.getName_kana();
		nickname = dto.getNickname();
		sex = String.valueOf(dto.getSex());
		birthday = dto.getBirthday();
		zipcode = dto.getZipcode();
		address = dto.getAddress();
		tell = dto.getTell();
		mail = dto.getEmail();

	}
	if (request.getAttribute("ERROR_MESSAGE") != null) {
		emailErrorMessage = (String)request.getAttribute("ERROR_MESSAGE");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOGEHOGE SHOP</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<div class="title_header">
		<h1>
			<a href="TopPage">HOGEHOGE SHOP</a>
		</h1>
	</div>
	<div class="form">
		<h2 class="form-title">新規会員登録</h2>
		<div class="row">
			<div class="col-md">
				<form action="NewRegistrationCheck" method="POST">
					<div class="form-group">
						<label>氏名：</label> <input type="text" name="NAME"
							class="form-control" placeholder="例）山田太郎" required max="20"
							<%if (modify) {%> value="<%=name%>" <%}%>>
					</div>
					<div class="form-group">
						<label>氏名(フリガナ)：</label> <input type="text" name="KANA"
							class="form-control" placeholder="例）ヤマダタロウ" required max="30"
							pattern="[ァ-ヴー\s]+" title="カタカナ" <%if (modify) {%>
							value="<%=name_kana%>" <%}%>>
					</div>
					<div class="form-group">
						<label>ニックネーム：</label> <input type="text" name="NICK_NAME"
							class="form-control" required max="20" <%if (modify) {%>
							value="<%=nickname%>" <%}%>>
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
							class="form-control" required <%if (modify) {%>
							value="<%=birthday%>" <%}%>>
					</div>
					<div class="form-group">
						<label>住所：</label> <input type="text" name="ZIPCODE"
							class="form-control" style="ime-mode: disabled"
							placeholder="123-4567" pattern="\d{3}-?\d{4}" title="郵便番号"
							required <%if (modify) {%> value="<%=zipcode%>" <%}%>>
						<input type="text" name="ADDRESS" class="form-control"
							placeholder="宮城県〇〇市〇〇区〇〇〇町X-X-X 〇〇ビル XXX号室" required
							<%if (modify) {%> value="<%=address%>" <%}%>>
					</div>
					<div class="form-group">
						<label>電話番号：</label> <input type="text" name="TELL"
							class="form-control" placeholder="0X0-1234-5678" pattern="\d{3}-?\d{4}-?\d{4}" required
							<%if (modify) {%> value="<%=tell%>" <%}%>>
					</div>
					<div class="form-group">
						<label>メールアドレス：&emsp;</label><span class="error-message"><%= emailErrorMessage %></span>
						<input type="email" name="MAIL"
							class="form-control" placeholder="hogehoge@exam.com" required
							<%if (modify) {%> value="<%=mail%>" <%}%>>
					</div>
					<div class="form-group">
						<label>パスワード：</label> <input type="password" name="PASS"
							class="form-control" id="password" style="ime-mode: disabled"
							placeholder="Abcd1234"
							pattern="(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\d)[a-zA-Z\d]{8,}"
							title="半角英大文字・半角英小文字・半角数字いれずれも必ず含む8文字以上" required>
					</div>
					<div class="form-group">
						<label>パスワード(確認)：</label> <input type="password" name="PASS_CHECK"
							class="form-control" oninput="CheckPassword(this)" required>
					</div>

					<div>
						<p>会員規約および個人情報の取り扱いについて</p>
						<p><a href="#" target="_brank">会員規約を読む</a>&emsp;
						<input type="checkbox" name="agreement" id="agreement" value="1"
							required /><label for="agreement">同意する</label></p>
					</div>
					<div class="form-btn btn-flex">
						<div>
							<a href="Login" class="btn btn-outline-primary btn-block">戻る</a>
						</div>
						<div>
							<button type="submit" class="btn btn-outline-primary btn-block">この内容で会員登録する</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="js/form.js"></script>
</body>
</html>