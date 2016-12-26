$("#userName").blur(function(){
		if($("#userName").val()){
			$.ajax({
				url : "/user/isExistUser",
				type : "post",
				data : {"account":$(this).val()},
				dataType : "json",
				success : function(data){
					if(data.exist){
						layer.tips('该用户名已存在', '#userName', {
							  tips: [1,'0077d9']
						});
					}
				}
			});
		}
	});
	$(".submit").click(function(){
		var flag = true;
		if(!$("#userName").val()){
			flag = false;
			layer.tips('请输入用户名', '#userName', {
				  tips: [1,'0077d9']
			});
		}
		if(!$("#pwd").val()){
			flag = false;
			layer.tips('请输入密码', '#pwd', {
				  tips: [1,'0077d9'],
				  tipsMore: true
			});
		}else if($("#pwd").val().length < 6){
			flag = false;
			layer.tips('密码长度不能小于6', '#pwd', {
				  tips: [1,'0077d9'],
				  tipsMore: true
			});
		}
		if(!$("#code").val()){
			flag = false;
			layer.tips('请输入验证码', '#code', {
				  tips: [1,'0077d9'],
				  tipsMore: true
			});
		}
		if(!flag) return;
		$.ajax({
			url : "/user/register",
			type : "post",
			data : $("#register_form").serialize(),
			dataType : "json",
			success : function(data){
				if(data.register == "success"){
					var html = '<div class="userHead">'+
					'<a href="/user/" >'+
			        	'<img src="/images/defaultHead.jpg">'+
			        	'<cite>'+ data.account +'</cite>'+
			    	'</a>'+
			    	'<a id="logout" href="javascript:;"><i class="iconfont icon-shezhi"></i> 设置</a>'+
			    	'<a id="logout" href="javascript:;"><i class="iconfont icon-tuichu"></i> 登出</a></div>';
			    	$('.searchlogin', parent.document).empty().html(html);
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}else{
					layer.tips('验证码错误', '#code', {
						  tips: [1,'0077d9'],
						  tipsMore: true
					});
				}
			},
			error : function(data){
				layer.tips('验证码错误', '#code', {
					  tips: [1,'0077d9'],
					  tipsMore: true
				});
			}
		});
	});
	function changeCode(){   
	    var imgSrc = $("#imgCode");     
	    var src = imgSrc.attr("src");     
	    imgSrc.attr("src",src+"?"+Math.random());     
	}     