<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.UserInfoDto"%>

<%
	UserInfoDto dto = (UserInfoDto) session.getAttribute("USER_DATA");
	String name = dto.getName();
	String name_kana = dto.getName_kana();
	String nickname = dto.getName_kana();
	String sex = String.valueOf(dto.getSex());
	if (sex.equals("1")) {
		sex = "男性";
	} else if (sex.equals("2")) {
		sex = "女性";
	} else {
		sex = "その他";
	}
	String birthday = dto.getBirthday();
	String zipcode = dto.getZipcode();
	String address = dto.getAddress();
	String tell = dto.getTell();
	String email = dto.getEmail();
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<div class="container">
		<div class="form">
			<h2 class="form-title">登録内容確認</h2>

			<table width="100%" class="table">
				<tr>
					<th width="30%"></th>
					<th width="70%"></th>
				</tr>
				<tr>
					<td>名前</td>
					<td><%=name%></td>
				</tr>
				<tr>
					<td>名前(フリガナ)</td>
					<td><%=name_kana%></td>
				</tr>
				<tr>
					<td>ニックネーム</td>
					<td><%=nickname%></td>
				</tr>
				<tr>
					<td>性別</td>
					<td><%=sex%></td>
				</tr>
				<tr>
					<td>生年月日</td>
					<td><%=birthday%></td>
				</tr>
				<tr>
					<td>住所</td>
					<td><%=zipcode%><br><%=address%></td>
				</tr>
				<tr>
					<td>電話番号</td>
					<td><%=tell%></td>
				</tr>
				<tr>
					<td>メールアドレス</td>
					<td><%=email%></td>
				</tr>
			</table>

			<div class="form-btn btn-flex">
				<div>
					<form action="ExecuteNewRegistration" method="POST">
						<input type="hidden" name="CHECK_REGIST" value="modify">
						<button class="btn btn-outline-primary btn-block" type="submit">変更する</button>
					</form>
				</div>
				<div>
					<form action="ExecuteNewRegistration" method="POST">
						<input type="hidden" name="CHECK_REGIST" value="permit">
						<button class="btn btn-outline-primary btn-block" type="submit">登録する</button>
					</form>
				</div>
			</div>

		</div>

	</div>

</body>
</html>