<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserInfoDto" %>

<% UserInfoDto dto = (UserInfoDto)request.getAttribute("PURCHASER_INFO"); %>
<% String purchase_price = (String)request.getAttribute("PURCHASER_PRICE"); %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>HOGEHOGE SHOP-注文確定</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="css/common.css">
  <link rel="stylesheet" type="text/css" href="./css/top.css">
</head>
<body  class="skaku txt_w">
<main>
  <div>
    <div class="kakutei_title">
      <h1 style="margin-top:20px"><span>ご購入ありがとう</span><span>ございました</span></h1>
    </div>
    <div class="kingaku">
      <p>お支払合計金額は</p>
      <p><span class="money"><%= purchase_price  %></span>円です</p>
    </div>
  <div class="jouhou">

    <p>お客様登録情報</p>

      <p>名前<br>
      <%= dto.getName() %>さん
      </p>
      <p>住所<br>
      <%= dto.getAddress() %>
      </p>
      <p>メールアドレス<br>
      <%= dto.getEmail() %>
      </p>
    </div>

    <div class="tyukoku" style="color:red">
      <p>3日以内に支払いをお願いします</p>
      <p>お問い合わせはサポートダイヤルへ(XXX-XXXX-XXXX）</p>
    </div>

    <div  class="top_button" >
      <a href="TopPage" class="modoru">トップに戻る</a>
    </div>
  </div>
</main>