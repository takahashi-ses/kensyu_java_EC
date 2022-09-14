<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.UserInfoDto" %>

<%
session = request.getSession();
UserInfoDto userInfoOnSessionFooter = (UserInfoDto)session.getAttribute("LOGIN_INFO");
%>

<div class="footer_list">
    <ul class="list_1">
        <% if (userInfoOnSessionFooter != null) { %>
            <li><a href="ExecuteLogout">ログアウト</a></li>
            <% }else { %>
            <li><a href="Login">ログイン</a></li>
            <% } %>
        <li><a href="TopPage">トップページへ</a></li>
    </ul>
    <ul class="list_2">
        <li><a href="Inquiry">お問い合わせ</a></li>
        <li><a href="MemberInfo">会員情報</a></li>
    </ul>
    <ul class="list_3">
        <li><a href="Favorite">お気に入り</a></li>
        <li><a href="Cart">カート</a></li>
    </ul>
</div>
<p class="copyright">© 2022 Kensyu_netshop</p>
