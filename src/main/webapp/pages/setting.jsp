<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div style="width: 30%; height:auto; position: absolute;left:35%;top:0;line-height: 40px; margin:100px 0 0 0;">
			<div class="portlet-title" style="    position: absolute;
    width: 190%;
    left: -25%; text-align:left;margin:0 auto;   border-bottom: 1px solid #eef1f5;padding: 0;margin-bottom: 10px;">
				<div class="caption font-green bold" style="font-size: 18px;line-height: 18px;padding: 10px 0;color: #32c5d2;font-weight: 700;">
					设置个人信息
				</div>
			</div>
			<form class="layui-form layui-form-pane" style="margin-top:50px;" id="set_form">
			  <div class="layui-form-item about">
			  	<img onclick="javascript:window.location.href = '/u/head'" src="${fn:substring(sessionScope.user.headName,46,-1)}" ${fn:contains(sessionScope.user.headName,'defaultHead.jpg') ? 'class="headTit"' : ''}>
			  </div>
			  
			  <c:if test="${empty sessionScope.user.cellphone}">
			  	<div class="layui-form-item" style="overflow: hidden;height: 40px;line-height: 40px;" align="center">
			  		还未绑定手机号？绑定后还可以使用手机登录 &nbsp;<a href="/u/bind/phone">去绑定吧</a>
			  	</div>
			  </c:if>
			  
			  <c:if test="${empty sessionScope.user.email}">
			  	<div class="layui-form-item" style="overflow: hidden;height: 40px;line-height: 40px;" align="center">
			  		还未绑定邮箱？绑定后还可以使用邮箱登录<a href="/u/bind/email">去绑定</a>
			  	</div>
			  </c:if>
			  
			  <c:if test="${not empty sessionScope.user.cellphone}">
				  <div class="layui-form-item rel">
				    <label class="layui-form-label">手机号 <i class="iconfont icon-cp-number"></i></label>
				    <div class="layui-input-block">
				      <input type="text" name="cellphone" id="cellphone" class="layui-input ${empty sessionScope.user.cellphone ? '' : 'disabled'}" value="${sessionScope.user.cellphone}" onkeyup='this.value=this.value.replace(/\D/gi,"")' ${empty sessionScope.user.cellphone ? '' : 'disabled'} placeholder="手机号">
				    </div>
	 				<div class="layui-form-mid layui-word-aux abs"><a href="/u/changePhone">更换绑定手机号</a></div>
				  </div>
			  </c:if>
			 
			  <c:if test="${not empty sessionScope.user.email}">
			   <div class="layui-form-item rel">
			    <label class="layui-form-label">邮箱 <i class="iconfont icon-youxiang2"></i></label>
			    <div class="layui-input-block">
			      <input type="text" name="email" id="email" class="layui-input ${empty sessionScope.user.email ? '' : 'disabled'}" value="${sessionScope.user.email}" ${empty sessionScope.user.email ? '' : 'disabled'} placeholder="邮箱">
			    </div>
			    
	 				<div class="layui-form-mid layui-word-aux abs"><a href="/u/changeEmail">更换绑定邮箱</a></div>
			  </div>
			  </c:if>
			  
			   <div class="layui-form-item">
			    <label class="layui-form-label">昵称 <i class="iconfont icon-nicheng"></i></label>
			    <div class="layui-input-block">
			      <input type="text" name="nickName" id="nickName" class="layui-input" value="${sessionScope.user.nickName}" placeholder="昵称">
			    </div>
			  </div>
			  <div class="layui-form-item rel">
			    <label class="layui-form-label">性别  <i class="iconfont icon-xingbie"></i></label>
			    <div class="layui-input-inline" id="sex">
			      <input type="radio" name="sex" value="M" title="男" ${sessionScope.user.sex eq 'M' ? 'checked' : ''}>
			      <input type="radio" name="sex" value="L" title="女" ${sessionScope.user.sex eq 'L' ? 'checked' : ''}  >
			    </div>
			     <c:if test="${empty sessionScope.user.sex}">
	 				<div class="layui-form-mid layui-word-aux abs">性别一旦设置便不可修改</div>
	 			</c:if>
			  </div>
			   <div class="layui-form-item rel">
			    <label class="layui-form-label">城市 <i class="iconfont icon-dizhi"></i></label>
			    <div class="layui-input-block">
			      <input type="text" id="addr" class="layui-input" placeholder="居住城市">
			      <input type="hidden" class="addr" name="cityId" id="cityId"/>
			    </div>
			   <div class="layui-form-mid layui-word-aux abs">请选择提示框下面的选项</div>
			  </div>
			  
			  <div class="layui-form-item">
	 			<button type="button" id="save" class="blue-button unified-submit submit">保存</button>
			 </div>
 			<input type="hidden" value="${sessionScope.user.cellphone}" id="bf-cellphone">
 			<input type="hidden" value="${sessionScope.user.email}" id="bf-email">
		</form>
	</div>