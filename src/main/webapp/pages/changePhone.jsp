<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div style="width: 30%; height:auto; position: absolute;left:35%;top:0;line-height: 40px; margin:170px 0 68px 0;">
		<form action="" id="changePhone_form" class="form layui-form layui-form-pane">
			<h2>更换绑定手机号</h2>
			<div class="layui-form-item"></div>
		   <c:if test="${step eq 2 and way eq 'email'}">
			<div class="layui-form-item">
			    <label class="layui-form-label">邮箱 <i class="iconfont icon-youxiang"></i></label>
			    <div class="layui-input-block">
			      <input type="text" class="layui-input disabled" id="userName" name="loginParam" value="${sessionScope.user.email}" disabled="disabled" placeholder="邮箱">
			    </div>
		   </div>
		    <div class="layui-form-item rel">
			    <label class="layui-form-label">验证码 <i class="iconfont icon-yanzhengma"></i></label>
			    <div class="layui-input-block" id="code">
			      <input type="text" class="layui-input" id="emailCode" name="emailCode" class="validateCode" placeholder="邮箱验证码" style="padding-right: 35%">
		    </div>
			    <button class="layui-btn btn-send-code" id="sendCode" type="button" >发送验证码</button>
		   </div>
		  </c:if>
		  <c:if test="${empty way}">
		  	<div class="layui-form-item">
			    <label class="layui-form-label">${step eq 3 ? '新' : '原'}手机 <i class="iconfont icon-cp-number"></i></label>
			    <div class="layui-input-block">
			      <input type="text" class="layui-input ${step eq 3 ? '' : 'disabled'}" id="userName" name="loginParam" value="${step eq 3 ? '' : sessionScope.user.cellphone}" ${step eq 3 ? '' : 'disabled="disabled"'} alt="手机号" placeholder="手机号">
			    </div>
		   </div>
		  </c:if>
		  <c:if test="${empty step}">
		  	<div class="layui-form-item rel" id="imgCodeTitle">
			    <label class="layui-form-label">验证码 <i class="iconfont icon-yanzhengma"></i></label>
			    <div class="layui-input-block">
			      <input type="text" class="layui-input" placeholder="图片验证码"  id="imgCode" name="imgCode" styLe="padding-right: 25%"/>
				<img id="imgCodeUrl" onclick="changeCode()" src="/code/verifyCode" class="btn-img-code"/>
		    </div>
			<div class="layui-form-mid layui-word-aux abs"><a href="javascript:;" onclick="changeCode()">看不清？</a></div>
		   </div>
		  </c:if>
		  
		  <c:if test="${(step eq 2 || step eq 3) and empty way}">
			<div class="layui-form-item rel" id="code">
				<label class="layui-form-label">验证码 <i class="iconfont icon-yanzhengma"></i></label>
				<div class="layui-input-block" >
					<input type="text" class="layui-input validateCode" placeholder="手机验证码" id="phoneCode" name="phoneCode" style="padding-right: 35%"/>
				</div>
			      <button class="layui-btn btn-send-code" id="sendCode" type="button">发送验证码</button>
			</div>
			
			
		 </c:if>
		 <div class="layui-form-item">
 			<button type="button" class="blue-button unified-submit submit ${step eq 3  ? 'confirm' : 'next-step'}">${step eq 3 ? '确定' : '下一步'}</button>
		 </div>
		 <input type="hidden" value="${empty step ? 1 : step}" id="step" name="step"/>
		</form>
		<c:if test="${step eq 2 and empty way}">
	 		<div class="input-title ">
	 			<a href="/u/changePhone/email" class="login" >手机不能接收验证码？</a>
	 		</div>
 		</c:if>
</div>