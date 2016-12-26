function editEmpty(html){
	return html.replace(/&nbsp;/ig, "").replace(/<p>/ig, "").replace(/<\/p>/ig, "").replace(/<br>/ig, "").trim();
}
function getSessionAttr(key){
	var result = "";
	$.ajax({
		url : "/getSessionAttr",
		data : {"key" : key}, 
		type : "post",
		async : false,
		success : function(data){
			result = data;
		}
	});
	return result;
}
function changeCode(){   
    var imgSrc = $("#imgCodeUrl");     
    var src = imgSrc.attr("src");     
    imgSrc.attr("src",src+"?"+Math.random());     
}
//验证手机号或邮箱输入是否正确并且是否存在
function validateUserNameExist(account,$tip,type){
	var result = validateUserName(account,$tip,type);
	if(result[0])
		return  validateExist({"loginParam":account},result[1],$tip);
	else 
		return result[0];
}
//验证手机号或邮箱输入是否正确并且是否不存在
function validateUserNameNotExist(account,$tip){
	var result = validateUserName(account,$tip);
	if(result[0] && account)
		return validateNotExist({"loginParam":account},result[1],$tip);
	else 
		return result[0];
}
//验证手机号或邮箱输入是否正确
function validateUserName(account,$tip,type){
	var style = "手机号";
	//判断是手机号还是邮箱
	//如果不是纯数字  则是邮箱   否则是手机号
	if(!type){
		if(!isNaN(account)){
			return [validatePhone(account,$tip),style];
		}else{
			style = "邮箱";
			return [validateEmail(account,$tip),style];
		}
	}else{
		if(type == "邮箱")
			return [validateEmail(account,$tip),type];
		else
			return [validatePhone(account,$tip),type];
	}		
	
}
function validateEmail(email,$tip){
	var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	if(!pattern.test(email)){
		layer.tips('请输入正确的邮箱', $tip, {
			  tips: [2,'#F7B824'],
			  tipsMore: true
		});
		return false;
	}
	return true;
}
function validatePhone(cellPhone,$tip){
	var pattern = /^1[34578]\d{9}$/;
	if(!pattern.test(cellPhone)){
		layer.tips('请输入正确的手机号', $tip, {
			  tips: [2,'#F7B824'],
			  tipsMore: true
		});
		return false;
	}
	return true;
}
//验证参数是否存在
function validateExist(data,type,selector){
	var flag = true;
	$.ajax({
		url : "/isExistUser",
		type : "post",
		async : false,
		data : data,
		success : function(data){
			if(data){
				flag = false;
				layer.tips('该' + type + '已存在', selector, {
					  tips: [2,'#F7B824']
				});
			}
		}
	});
	return flag;
}
//验证参数是否不存在
function validateNotExist(data,type,selector){
	var flag = true;
	$.ajax({
		url : "/isExistUser",
		type : "post",
		async : false,
		data : data,
		success : function(data){
			if(!data){
				flag = false;
				layer.tips('该' + type + '不存在', selector, {
					  tips: [2,'#F7B824']
				});
			}
		}
	});
	return flag;
}
function sendValidateCode(target,_this){
	$.ajax({
		url : "/sendCode",
		data : {"target" : target.val().trim()},
		type : "post",
		beforeSend : function(){
			 var count = 60;
	            var resend = setInterval(function(){
	                count--;
	                if (count > 0){
	                	_this.html("重新发送("+ count + ")");
	                	_this.css("background-color","#d4d7d9");
	                	_this.addClass("reSend");
	                }else {
	                    clearInterval(resend);
	                    _this.html("重新发送").removeAttr('disabled style');
	                }
	            }, 1000);
	            _this.attr('disabled',true).css('cursor','not-allowed');
		}
	});
}
function sur($charSelector,$centSelector,dead){
	var centLength = $centSelector.val().length;
	var sur = Number(dead) - centLength;
	if (sur < 0){
		sur = 0;
		$centSelector.val($centSelector.val().substr(0,dead));
	}
	$charSelector.html(sur);
}
(function(){
	window.onbeforeunload = function(){
        WS.disconnect();
    };
    layui.use(['layer','util'], function(){
		var layer = layui.layer;
		var util = layui.util;
		util.fixbar();
	}); 
	//导航栏选中
	$('.' + $('.adsorbent').val()).addClass('active');
	
	if($('.adsorbent').val() == 'nav2'){
		$('.write').addClass('add-note');
		$('#search-form').attr('action','/n/ls');
	}
	
	$('.search-icon').click(function(){
		$(this).parents("form").submit();
	});
	
	$('.add-note').click(function(e){
		e.preventDefault();
		$.ajax({
			url : '/n/il',
			type : 'post',
			data : {'skipUrl' : '/n/ls'},
			async : false,
			success : function(data){
				if(data == false) location.href = '/94071001/login';
				else
					layer.open({
						type : 1,
						content : $('#note-dialog'),
//						area : ['336px','410px'],
						title : "写个便利贴",
						btn : ['保存'],
						yes : function(){
							if(!$('#note-dialog .cont').val().trim()){
								layer.tips('写点什么吧', '#note-dialog .cont', {
									  tips: [2,'#F7B824']
								});
								return false;
							}
							$.ajax({
								url : '/u/n/sv',
								type : 'post',
								asyc : false,
								data : {'content' : $('#note-dialog .cont').val()},
								success : function(){
									layer.msg('保存成功',{icon:6},function(){
										location.reload();
									});
								}
							});
						},
						end : function(){
							$('#note-dialog .cont').val('');
							$('#note-dialog .char').html($('#note-dialog .char').attr('data-dead'));
						}
					});
			}
		});
		
	});
	$('.cont').keyup(function(){
		sur($('.char'),$(this),$('.char').attr("data-dead"));
	});
	$("input,select").focus(function(){
		$("#errMsg").html("");
	});
	
	if($("#set_form .headTit").length > 0)
	layer.open({
		  type: 4,
		  shade : false,
		  closeBtn : 0,
		  maxWidth : 200,
		  content: ['头像不满意？点击头像更换新头像', '.headTit'] //数组第二项即吸附元素选择器或者DOM
		});  
	
	//统一回车触发提交事件
	$("body").keydown(function(event) {
	    if (event.keyCode == "13") {//keyCode=13是回车键
	        $('.unified-submit').trigger('click');
	    }
	});    
	
	
	//绑定手机  邮箱 确定
	$("#bind_submit").click(function(){
		var type =$("#userName").attr("alt"), _code = $(".validateCode");
		$("#errMsg").empty();
		var flag = true;
		if(!$("#userName").val().trim()){
			flag = false;
			layer.tips('请输入' + type, '#userName', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}else if(!validateUserNameExist($("#userName").val(),"#userName",type))//手机号或邮箱是否已经存在
			flag = false;
		
		if(!_code.val().trim()){
			flag = false;
			layer.tips('请输入验证码', '#code', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if(_code.val() && _code.val().toLowerCase() != getSessionAttr(_code.attr("id"))){
			flag = false;
			layer.tips('验证码错误', '#code', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if(flag)
			$.ajax({
				url : "/u/bind/confirm",
				type : "post",
				data : $(this).parents("form").serialize(),
				success : function(){
					layer.msg("绑定成功",{icon:6},function(){
						location.href = "/u/48824877/setting";
					});
				}
			});
			
	});
	
	//个人信息设置  保存   // 待修改
	$("#set_form #save").click(function(){
		var flag = true;
		if(!$("#nickName").val().trim()){
			flag = false;
			layer.tips('请输入昵称', "#nickName", {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if(!$("#cityId").val().trim() || $("#cityId").attr("data-hork") != $("#addr").val()){
			flag = false;
			layer.tips('输入城市后请选择提示框下的选项', "#addr", {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if($("input[name='sex']:checked").length == 0){
			flag = false;
			layer.tips('请选择性别', "#sex", {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if(!flag) return;
		$.ajax({
			url : '/u/profile/sv',
			type : 'post',
			async : false,
			data : $('#set_form').serialize(),
			success : function(){
				layer.msg("保存成功",{icon:6},function(){
					location.reload();
				});
			}
		})
	});
	//更换绑定邮箱 下一步
	$('#changeEmail_form .next-step,#changePhone_form .next-step').click(function(){
		var url = "/u/changeEmail/nextStep",selector = "#changeEmail_form";
		if($(this).parents('form').attr("id") == "changePhone_form"){
			 url = "/u/changePhone/nextStep";
			 selector = "#changePhone_form";
		}
		$("#errMsg").empty();
		var flag = true;
		var step = $("#step").val();
		
		if(step == 1){
			if($("#imgCode").val().toLowerCase() != getSessionAttr("imgCode")){
				flag = false;
				layer.tips('验证码错误', '#imgCodeTitle', {
					  tips: [2,'#F7B824'],
					  tipsMore: true
				});
				changeCode();
			}
		}else if(step == 2){
			if(!$(".validateCode").val().trim() || $(".validateCode").val().toLowerCase() != getSessionAttr($(".validateCode").attr("id"))){
				flag = false;
				layer.tips('验证码错误', '#code', {
					  tips: [2,'#F7B824'],
					  tipsMore: true
				});
			}
		}
		if(!flag) return;
		$("#step").val(parseInt(step) + 1);
		$(selector).attr("action",url).attr("method","post").submit();
	});
	//更换绑定邮箱  确认
	$('#changeEmail_form .confirm,#changePhone_form .confirm').click(function(){
		var selector = "#changeEmail_form",type = "邮箱",codeSelector = "#emailCode",sessionCode = "emailCode";
		if($(this).parents('form').attr("id") == "changePhone_form"){
			 selector = "#changePhone_form";
			 type = "手机号";
			 codeSelector = "#phoneCode";
			 sessionCode = "phoneCode";
		}
		$("#errMsg").empty();
		var flag = true;
		if(!$("#userName").val().trim()){
			flag = false;
			layer.tips('请输入' + type, '#userName', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}else if(!validateUserNameExist($("#userName").val(),"#userName",$("#userName").attr("alt")))//手机号或邮箱是否已经存在
			flag = false;
		
		if(!$(codeSelector).val().trim()){
			flag = false;
			layer.tips('请输入验证码', '#code', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if($(codeSelector).val() && $(codeSelector).val().toLowerCase() != getSessionAttr(sessionCode)){
			flag = false;
			layer.tips('验证码错误', '#code', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if(!flag)return;
		$.ajax({
			url : '/u/change/confirm',
			data : $(selector).serialize(),
			dataType : 'json',
			async : false,
			success : function(data){
				if(data.url){
					layer.msg("修改成功",{icon : 6},function(){
						window.location.href = data.url;
					});
				}else if(data.errMsg)
					$("#errMsg").html(data.errMsg);
			}
		})
		
	});
	//找回密码  下一步
	$('#forget_form .next-step').click(function(){
		var step = $("#step").val();
		var flag = true;
		var account = $("#userName");
		
		if(!account.attr("disabled")){//第一个下一步
			flag = validateUserNameNotExist(account.val(),"#userName");
			if($("#imgCode").val().toLowerCase() != getSessionAttr("imgCode")){
				flag = false;
				layer.tips('验证码错误', '#imgCodeTitle', {
					  tips: [2,'#F7B824'],
					  tipsMore: true
				});
				changeCode();
			}
		}else{//第二个下一步
			if(!$("#phoneOrEamilCode").val().trim() || $("#phoneOrEamilCode").val().toLowerCase() != getSessionAttr($("#phoneOrEamilCode").attr("alt"))){
				flag = false;
				layer.tips('验证码错误', '#code', {
					  tips: [2,'#F7B824'],
					  tipsMore: true
				});
			}
		}
		
		if(!flag) return;
		$("#step").val(parseInt(step) + 1);
		account.attr("disabled",false);
		$("#forget_form").attr("action","/forget/nextStep").attr("method","post").submit();
	});
	//找回密码  确认
	$('#forget_form .confirm').click(function(){
		if(!$("#pwd").val().trim()){
			layer.tips('密码长度应在6至16位', '#pwd', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
			return;
		}else if($("#pwd").val().length < 6){
			layer.tips('密码长度应在6至16位', '#pwd', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
			return;
		}
		$("#userName").attr("disabled",false);
		$.ajax({
			url : "/forget/confirm",
			type : "post",
			data : $("#forget_form").serialize(),
			success : function(data){
				layer.msg("重置密码成功，请使用新密码登录",{icon : 6},function(){
					window.location.href = "/94071001/login";
				});
			}
		});
	});
	$('#login-submit').click(function(){
		$("#errMsg").empty();
		var flag = true;
		if(!$("#userName").val().trim()){
			flag = false;
			layer.tips('请输入手机号或邮箱', '#userName', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}else if(!validateUserName($("#userName").val(),'#userName')[0]){
			flag = false;
		}
		if(!$("#pwd").val().trim()){
			flag = false;
			layer.tips('请输入密码', '#pwd', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if(!flag) return;
		$("#login_form").attr("action","/logined").attr("method","post").submit();
	});
	
	$('#register-submit').click(function(){
		$("#errMsg").empty();
		var loginId = $("#userName").val().trim();
		var flag = true;
		if(!loginId){
			flag = false;
			layer.tips('请输入手机号或邮箱', '#userName', {
				  tips: [2,'#F7B824']
			});
		}else if(!validateUserNameExist(loginId,"#userName"))//手机号或邮箱是否已经存在
			flag = false;
		
		if(!$("#pwd").val().trim()){
			flag = false;
			layer.tips('密码长度应在6至16位', '#pwd', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}else if($("#pwd").val().length < 6){
			flag = false;
			layer.tips('密码长度应在6至16位', '#pwd', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if(!$("#nickName").val().trim()){
			flag = false;
			layer.tips('请输入昵称', '#nickName', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}else if(!validateExist({"nickName" : $("#nickName").val()},"昵称",$("#nickName")))//判断昵称是否存在
			flag = false;
		
		if(!$("#imgCode").val().trim()){
			flag = false;
			layer.tips('请输入图片验证码', '#imgCodeTitle', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if(!$("#phoneOrEamilCode").val().trim()){
			flag = false;
			layer.tips('请输入验证码', '#code', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if($("#imgCode").val() && $("#imgCode").val() != getSessionAttr("imgCode")){
			flag = false;
			layer.tips('验证码错误', '#imgCodeTitle', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
			changeCode();
		}
		var phoneOrEamilCode = $("#phoneOrEamilCode").val().trim();
		var sessionCode = "phoneCode";
		if(loginId.indexOf("@") > -1)
			sessionCode = "emailCode";
		
		if(phoneOrEamilCode != getSessionAttr(sessionCode)){
			flag = false;
			layer.tips('验证码错误', '#code', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if(!flag) return;
		$.ajax({
			url : "/register",
			type : "post",
			data : $("#register_form").serialize(),
			success : function(data){
				if(data){
					$("#errMsg").html(data);
					if(data == "图片验证码错误")
						changeCode();
				}else{
					layer.msg("注册成功，请使用此帐号登录",{icon:6},function(){
						var skipUri = $('input[name="skipUri"]').val();
						if(skipUri) window.location.href = "/94071001/login?skipUri=" + $('input[name="skipUri"]').val();
						else window.location.href = "/94071001/login";
					});
				}
			}
		});
	});
	//用户注册/更换绑定邮箱、手机/绑定邮箱、手机  时判断用户名是否存在
	$("#register_form #userName,#changeEmail_form #userName,#changePhone_form #userName,#bind_form #userName").blur(function(){
		if($("#userName").val())
			validateUserNameExist($("#userName").val(),"#userName",$(this).attr("alt"));
	});
	//用户注册时判断昵称是否存在
	$("#register_form #nickName").blur(function(){
		if($("#nickName").val())
			validateExist({"nickName" : $("#nickName").val()},"昵称",$("#nickName"));
	});

	//更换绑定邮箱下的 发送邮箱验证码/发送短信验证码   绑定手机邮箱发送验证码
	$("#changeEmail_form #sendCode,#changePhone_form #sendCode,#bind_form #sendCode").click(function(){
		var disabled = $("#userName").attr("disabled");
		var flag = true;
		if(!disabled){
			var type =$("#userName").attr("alt");
			if(!$("#userName").val().trim()){
				flag = false;
				layer.tips('请输入' + type, '#userName', {
					  tips: [2,'#F7B824'],
					  tipsMore: true
				});
			}else if(!validateUserNameExist($("#userName").val(),"#userName",type))//手机号或邮箱是否已经存在
				flag = false;
		}
		if(flag){
			$("#userName").attr("disabled",false);
			sendValidateCode($("#userName"),$(this));
			$("#userName").attr("disabled",disabled);
		}
	});
	
	//找回密码下的发送验证码
	$("#forget_form #sendCode").click(function(){
		sendValidateCode($("#userName"),$(this));
	});
	//个人信息设置下的发送验证码
	$("#set_form #sendCode").click(function(){
		var $cellphone = $("#cellphone");
		if(!$cellphone.val().trim()){
			layer.tips('请输入手机号码', "#cellphone", {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
			return;
		}else if(!validatePhone($cellphone.val(),"#cellphone"))
			return;
		else if($cellphone.val() && !validateExist({"cellphone":$cellphone.val()},"手机号","#cellphone"))
			return;
		sendValidateCode($cellphone,$(this));
	});
	//注册下的发送验证码
	$("#register_form #sendCode").click(function(){
		if($(this).hasClass("reSend")){
			changeCode();
			$(this).removeClass("reSend");
			layer.tips('验证码错误', '#imgCodeTitle', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
			return;
		}
		var flag = true;
		if(!$("#userName").val().trim()){
			flag = false;
			layer.tips('请输入手机号或邮箱', '#userName', {
				  tips: [2,'#F7B824']
			});
		}else if(!validateUserNameExist($("#userName").val(),"#userName")){
			flag = false;
		}
		if(!$("#imgCode").val().trim()){
			flag = false;
			layer.tips('请输入图片验证码', '#imgCodeTitle', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}else if($("#imgCode").val().toLowerCase() != getSessionAttr("imgCode")){
			flag = false;
			layer.tips('验证码错误', '#imgCodeTitle', {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
			changeCode();
		}
		if(!flag)
			return;
		sendValidateCode($("#userName"),$(this));
	});
})($);