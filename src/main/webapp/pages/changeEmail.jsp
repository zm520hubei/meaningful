<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="width: 30%; height:auto; position: absolute;left:35%;top:0;line-height: 40px; margin:170px 0 68px 0;">
	<form action="" id="changeEmail_form" class="form layui-form layui-form-pane">
		<h2>更换绑定邮箱</h2>
		<div class="layui-form-item"></div>
		<c:if test="${step eq 2 and way eq 'phone'}">
			<div class="layui-form-item">
			    <label class="layui-form-label">手机号 <i class="iconfont icon-cp-number"></i></label>
			    <div class="layui-input-block">
			      <input type="text" class="layui-input disabled" id="userName" name="loginParam" value="${sessionScope.user.cellphone}" disabled placeholder="手机号">
			    </div>
		   </div>
	       <div class="layui-form-item rel">
			    <label class="layui-form-label">验证码 <i class="iconfont icon-yanzhengma"></i></label>
			    <div class="layui-input-block" id="code">
			      <input type="text" class="layui-input" id="phoneCode" name="phoneCode" class="validateCode" placeholder="手机验证码" style="padding-right: 35%">
		    </div>
			    <button class="layui-btn btn-send-code" id="sendCode" type="button">发送验证码</button>
		   </div>
		</c:if>
		
		<c:if test="${empty way}">
			<div class="layui-form-item">
			    <label class="layui-form-label">${step eq 3 ? '新' : '原'}邮箱 <i class="iconfont icon-youxiang"></i></label>
			    <div class="layui-input-block">
			      <input type="text" class="layui-input ${step eq 3 ? '' : 'disabled'}" placeholder="邮箱" id="userName" name="loginParam" value="${step eq 3 ? '' : sessionScope.user.email}" ${step eq 3 ? '' : 'disabled="disabled"'} alt="邮箱" >
			    </div>
		   </div>
		</c:if>
		
		<c:if test="${empty step}">
			<div class="layui-form-item rel" id="imgCodeTitle">
			    <label class="layui-form-label">验证码 <i class="iconfont icon-yanzhengma"></i></label>
			    <div class="layui-input-block" id="imgCodeTitle">
			      <input type="text" class="layui-input" placeholder="图片验证码"  id="imgCode" name="imgCode" styLe="padding-right: 25%"/>
				<img id="imgCodeUrl" onclick="changeCode()" src="/code/verifyCode" class="btn-img-code" style=""/>
		    </div>
			<div class="layui-form-mid layui-word-aux abs"><a href="javascript:;" onclick="changeCode()">看不清？</a></div>
		   </div>
		</c:if>
		
		<c:if test="${(step eq 2 || step eq 3) and empty way}">
			<div class="layui-form-item rel">
			    <label class="layui-form-label">验证码 <i class="iconfont icon-yanzhengma"></i></i></label>
			    <div class="layui-input-block">
			      <input type="text"  class="layui-input validateCode"  style="padding-right: 35%" placeholder="邮箱验证码" id="emailCode" name="emailCode"/>
			    </div>
				<button type="button" id="sendCode" class="layui-btn btn-send-code">发送验证码</button>
		   </div>
		</c:if>
		<div id="errMsg" style="margin:-10px 0 10px 0;color: #ff5c57;line-height: 30px;" align="center">${errorMsg}
			<c:if test="${step eq 2 and way eq 'phone' and empty sessionScope.user.cellphone}">绑定手机后可通过手机更换绑定邮箱，去<a href="/u/48824877/setting">绑定手机号</a></c:if>
		</div>
		<div class="layui-form-item">
 			<button type="button" class="blue-button unified-submit submit ${step eq 3  ? 'confirm' : 'next-step'}">${step eq 3 ? '确定' : '下一步'}</button>
		 </div>
		<input type="hidden" value="${empty step ? 1 : step}" id="step" name="step"/>
	</form>
	<c:if test="${step eq 2 and empty way}">
 		<div class="input-title ">
 			<a href="/u/changeEmail/phone" class="login" >邮箱不能接收验证码？</a>
 		</div>
 	</c:if>
</div>