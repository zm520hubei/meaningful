<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/css/index.css" type="text/css" rel="stylesheet"/>
<div class="dialog-title" style="padding:5px 0 0 20px">
<form action="" method="post" id="register_form">
	<h1>用户名：</h1>
	<input type="text" class="lp" placeholder="用户名或邮箱" id="userName" name="account"/>
	<h1>密码：</h1>
	<input type="password" class="lp" placeholder="密码" id="pwd" name="password"/>
	<h1>验证码：</h1>
	<input type="text" class="sp" placeholder="验证码" id="code" name="code"/>
	 <img id="imgCode" onclick="changeCode()" src="/code/verifyCode" style="vertical-align: middle;cursor:pointer"/>
	<div style="margin-top:20px;">
		<a href="javascript:;" class="login">已有账号？登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" class="submit blue-button">注册</button>
	</div>             
</form>
</div>
<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/layer/layer.js"></script>
<script src="/js/register.js"></script>