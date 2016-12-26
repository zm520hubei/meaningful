<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="width: 30%; height:auto; position: absolute;left:35%;top:0;line-height: 40px; margin:100px 0 68px 0;">
	<form class="layui-form layui-form-pane" action="" id="register_form">
		<h2>注册XXX</h2>
		<div class="layui-form-item">
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">帐号 <i class="iconfont icon-yonghuming"></i></label>
		    <div class="layui-input-block">
		      <input type="text" id="userName" name="loginParam" class="layui-input" placeholder="手机号或邮箱">
		    </div>
	    </div>
	    
	    <div class="layui-form-item">
		    <label class="layui-form-label">密码 <i class="iconfont icon-mima2"></i></label>
		    <div class="layui-input-block">
		      <input type="password" id="pwd" name="password" maxlength="16" class="layui-input" placeholder="6至16位密码">
		    </div>
	    </div>
	    
	    <div class="layui-form-item">
		    <label class="layui-form-label">昵称 <i class="iconfont icon-yonghuming"></i></label>
		    <div class="layui-input-block">
		      <input type="text" class="layui-input" id="nickName" name="nickName" maxlength="6" placeholder="昵称最长为6位">
		    </div>
	    </div>
	    
	    <div class="layui-form-item rel">
		    <label class="layui-form-label">验证码 <i class="iconfont icon-yanzhengma"></i></label>
		    <div class="layui-input-block" id="imgCodeTitle">
		      <input type="text" class="layui-input" id="imgCode" name="imgCode" placeholder="图片验证码">
		    </div>
		    <img id="imgCodeUrl" onclick="changeCode()" src="/code/verifyCode" class="btn-img-code"/>
		    <div class="layui-form-mid layui-word-aux abs"><a href="javascript:;" onclick="changeCode()">看不清？</a></div>
	    </div>
	    
	    <div class="layui-form-item rel">
		    <label class="layui-form-label">验证码 <i class="iconfont icon-yanzhengma"></i></label>
		    <div class="layui-input-block" id="code">
		      <input type="text" class="layui-input" id="phoneOrEamilCode" name="phoneOrEamilCode" placeholder="手机或邮箱验证码" style="padding-right: 35%">
		    </div>
		    <button class="layui-btn btn-send-code" id="sendCode" type="button" >发送验证码</button>
	    </div>
	    
	    <div class="layui-form-item">
	    	<div align="center" id="errMsg">${errMsg}</div>
	    </div>
	    
	     <div class="layui-form-item">
 			<button type="button" id="register-submit" class="blue-button unified-submit submit">注册</button>
		 </div>
		 
		 <div class="layui-form-item ">
			<a href="/94071001/login" class="login" >已有账号？去登录</a>
		</div>
		<input type="hidden" name="skipUri" value="${skipUri}">
	</form>
</div>
<script>if(document.getElementById('nickName').value) window.location.href='/';</script>