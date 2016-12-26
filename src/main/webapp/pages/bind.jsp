<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div style="width: 30%; height:auto; position: absolute;left:35%;top:0;line-height: 40px; margin:170px 0 68px 0;">
		<form action="" id="bind_form" class="form layui-form layui-form-pane">
			<h2>绑定${type eq 'phone' ? '手机号' : '邮箱'}</h2>
			<div class="layui-form-item"></div>
			<c:if test="${type eq 'email'}">
			<div class="layui-form-item">
			    <label class="layui-form-label">邮箱 <i class="iconfont icon-youxiang"></i></label>
			    <div class="layui-input-block">
			      <input type="text" class="layui-input" id="userName" name="loginParam" placeholder="邮箱" value="${loginParam}" alt="邮箱">
			    </div>
		   </div>
		   </c:if>
		   <c:if test="${type eq 'phone'}">
		   <div class="layui-form-item">
			    <label class="layui-form-label">手机号 <i class="iconfont icon-cp-number"></i></label>
			    <div class="layui-input-block">
			      <input type="text" class="layui-input" id="userName" name="loginParam" placeholder="手机号" value="${loginParam}" alt="手机号">
			    </div>
		   </div>
		   </c:if>
		   
		    <div class="layui-form-item rel">
			    <label class="layui-form-label">验证码 <i class="iconfont icon-yanzhengma"></i></label>
			    <div class="layui-input-block" id="code">
			      <input type="text" class="layui-input validateCode" id="${type}Code" name="${type}Code" placeholder="${type eq 'phone' ? '手机' : '邮箱'}验证码" style="padding-right: 35%">
		    </div>
			    <button class="layui-btn btn-send-code" id="sendCode" type="button" >发送验证码</button>
		   </div>
		   
		    <div class="layui-form-item">
		    	<div align="center" id="errMsg">${errMsg}</div>
		    </div>
		   
		 <div class="layui-form-item">
 			<button type="button" class="blue-button unified-submit submit confirm" id="bind_submit">确定</button>
		 </div>
	</form>
</div>