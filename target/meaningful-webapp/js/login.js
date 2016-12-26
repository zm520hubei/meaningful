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
		}
		if(!flag) return;
		$.ajax({
			url : "/user/getUser",
			type : "post",
			data : $("#login_form").serialize(),
			dataType : "json",
			success : function(data){
				if(data.login == "success"){
					var html = '<div class="userHead">'+
					'<a href="/user/" >'+
			        	'<img src="/images/defaultHead.jpg">'+
			        	'<cite>'+ data.account +'</cite>'+
			    	'</a>'+
			    	'<a id="setting" href="javascript:;"><i class="iconfont icon-shezhi"></i> 设置</a>'+
			    	'<a id="logout" href="avascript:;"><i class="iconfont icon-tuichu"></i> 登出</a></div>';
			    	$('.searchlogin', parent.document).empty().html(html);
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}else{
					layer.msg("用户名或密码错误",{icon:5});	
				}
			},
			error : function(data){
				layer.msg("用户名或密码错误");
			}
		});
	});