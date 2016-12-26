<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="width: 30%; height:auto; position: absolute;left:35%;top:0;line-height: 40px; margin:200px 0 68px 0;">
	<form class="layui-form layui-form-pane" action="" id="forget_form">
		<h2>找回密码</h2>
		<div class="layui-form-item">
		</div>
		
		<div class="layui-form-item">
		    <label class="layui-form-label">
		    	${empty step ? '帐号<i class="iconfont icon-yonghuming">' : (eviType eq 'email' ? '邮箱<i class="iconfont icon-youxiang2">' : '手机号<i class="iconfont icon-cp-number">')} </i>
		    </label>
		    <div class="layui-input-block">
		      <input type="text" id="userName" name="loginParam" class="layui-input ${not empty loginParam ? 'disabled' : ''}" placeholder="手机号或邮箱"  value="${loginParam}" ${not empty loginParam ? 'disabled="disabled"' : ''}>
		    </div>
	    </div>
	    
	    <c:if test="${empty step}">
	    	<div class="layui-form-item rel">
		    	<label class="layui-form-label">验证码 <i class="iconfont icon-yanzhengma"></i></label>
			    <div class="layui-input-block" id="imgCodeTitle">
			    	<input type="text" class="layui-input" id="imgCode" name="imgCode" placeholder="图片验证码"/>
			    </div>
			    <img id="imgCodeUrl" onclick="changeCode()" src="/code/verifyCode" class="btn-img-code"/>
			    <div class="layui-form-mid layui-word-aux abs"><a href="javascript:;" onclick="changeCode()">看不清？</a></div>
		    </div>
	    </c:if>
	    
	    <c:if test="${step eq 2}">
	    	<div class="layui-form-item rel">
		   		<label class="layui-form-label">验证码 <i class="iconfont icon-yanzhengma"></i></label>
			    <div class="layui-input-block" id="code">
			    	<input type="text" class="layui-input" id="phoneOrEamilCode" name="phoneOrEamilCode" alt="${eviType eq 'email' ? 'emailCode' : 'phoneCode'}" placeholder="${eviType eq 'email' ? '邮箱' : '手机号'}验证码"/>
			    </div>
			    <button class="layui-btn btn-send-code" id="sendCode" type="button" >发送验证码</button>
			 </div>
	    </c:if>
	    
	    <c:if test="${step eq 3}">
	    	<div class="layui-form-item">
		    	<label class="layui-form-label">新密码 <i class="iconfont icon-mima2"></i></label>
			    <div class="layui-input-block">
			    	<input type="password" name="password" id="pwd" class="layui-input" placeholder="6至16位密码" maxlength="16"/>
			    </div>
		    </div>
	    </c:if>
	    
	    <div class="layui-form-item">
	    	<div id="errMsg" align="center"></div>
	    </div>
	    
	    <div class="layui-form-item">
 			<button type="button" class="blue-button unified-submit submit ${step eq 3  ? 'confirm' : 'next-step'}">${step eq 3 ? '确定' : '下一步'}</button>
		 </div>
		 <input type="hidden" value="${empty step ? 1 : step}" id="step" name="step"/>
	</form>
</div>