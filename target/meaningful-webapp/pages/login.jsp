<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/css/index.css" type="text/css" rel="stylesheet"/>
<div class="dialog-title" style="padding:5px 0 0 20px">
<form action="" method="post" id="login_form">
	<h1>用户名：<br/></h1>
	<input type="text" class="lp" placeholder="用户名或邮箱" id="userName" name="loginParam"/><br/>
	<h1>密码：<br/></h1>
	<input type="password" class="lp" placeholder="密码" id="pwd" name="password"/>
	<div style="margin-top:20px;">
		社交账号登录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" class="submit blue-button">登录</button>
	</div>             
</form>
</div>
<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/layer/layer.js"></script>
<script src="/js/login.js"></script>