<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="width: 30%; height:auto; position: absolute;left:35%;top:0;line-height: 40px; margin:170px 0 68px 0;">
	<form class="layui-form layui-form-pane" action="" id="login_form">
		<h2>登录XXX</h2>
		<div class="layui-form-item">
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">${SocketConnected}帐号 <i class="iconfont icon-yonghuming"></i></label>
		    <div class="layui-input-block">
		      <input type="text" name="loginParam" id="userName" class="layui-input" placeholder="手机号或邮箱" value="${loginParam}">
		    </div>
	    </div>
	    
	    <div class="layui-form-item">
		    <label class="layui-form-label">密码 <i class="iconfont icon-mima2"></i></label>
		    <div class="layui-input-block">
		      <input type="password" class="layui-input" name="password" maxlength="16" id="pwd" placeholder="6至16位密码" value="${password}">
		    </div>
	    </div>
	    
	    <div class="layui-form-item">
	    	<div align="center" id="errMsg">${errMsg}</div>
		</div>
		
		<div class="layui-form-item">
 			<button type="button" id="login-submit" class="blue-button unified-submit submit">登录</button>
		 </div>
		 
		 <div class="layui-form-item rel">
		 	<a href="/qqLogin" title="QQ登录" class="login inl-blo a"><i class="iconfont icon-QQ"></i></a>
		  	<a href="javascript:;" title="新浪微博登录" class="login inl-blo wb-login a"><i class="iconfont icon-xweibo"></i></a>
			<a href="/7540240/forget" class="abs a-forget a">忘记密码？</a>
			<a href="/83746134/toRegister" class="right a">去注册</a>
		 </div>
		 <input type="hidden" name="skipUri" value="${skipUri}">
	</form>
</div>
<script>if(document.getElementById('nickName').value) window.location.href='/';</script>