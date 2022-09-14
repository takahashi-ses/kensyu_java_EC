<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.UserInfoDto" %>

<%
session = request.getSession();
UserInfoDto userInfoOnSessionHeader = (UserInfoDto)session.getAttribute("LOGIN_INFO");
%>



<div class="title_header">
    <h1><a href="TopPage">HOGEHOGE SHOP</a></h1>
</div>


<div class="header_list">
    <div class="serch">
        <div class="header-message">
            <div class="header-message-left">
            <% if (userInfoOnSessionHeader != null) { %>
                <p><%= userInfoOnSessionHeader.getNickname() %>さん、おかえりなさい</p>
            <% }else {%>
            	<p>ゲストさん、ようこそ</p>
            <% } %>
            </div>
            <div class="header-message-right">
                <form action="TopPage" method="get">
                        <input type="text" class="text_box" name="search" maxlength="100" placeholder="商品名を入力してください">
                        <button type="submit" class="btn btn-warning" id="serch_button">検索🔍</button>
                </form>
            </div>
        </div>
	</div>

        <ul class="header-link">
            <li><a href="Favorite">お気に入り</a></li>
            <li><a href="Cart">カート</a></li>
            <% if (userInfoOnSessionHeader != null) { %>
            <li><a href="ExecuteLogout">ログアウト</a></li>
            <% }else { %>
            <li><a href="Login">ログイン</a></li>
            <% } %>
            <li><a href="MemberInfo">会員情報</a></li>
        </ul>

</div>


