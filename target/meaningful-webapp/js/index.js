$(function(){
		$("#register").click(function(){
			layer.closeAll();
			layer.open({
				type : 2,
				title : '注册',
				shadeClose : false,
				shade : 0.4,
				move: false,
				offset : '100px',
				area : ['304px','350px'],
				content : '/pages/register.jsp',
				success : function(layero,index){
					var body = layer.getChildFrame('body',index);
					var login = body.find('.login').click(function(){
						$("#login").trigger('click');
					});
					
				}
			});
		});
		$('body').on('click','#login',function(){
			layer.closeAll();
			layer.open({
				type : 2,
				title : '登录',
				shadeClose : false,
				move: false,
				shade : 0.4,
				offset : '100px',
				area : [ '304px', '270px' ],
				content : '/pages/login.jsp' //iframe的url
			});
		});
		$('body').on('click','#logout',function(){
			$.ajax({
				url : "/user/logout",
				type : "post",
				success : function(data){
					var html = '<a id="login" class="header-link settings-top_color settings-tophover_color" href="javascript:;">'+
	           		'<span class="headerpc-link">登录</span>'+
	           		'</a>'+
	           	'<span class="header-link settings-top_color">|</span>'+
	            '<a id="register" class="header-link settings-top_color settings-tophover_color" href="javascript:;">注册</a>';
			    	$('.searchlogin').empty().html(html);
				},
				error : function(data){
					layer.msg("哎呀，出错了！",{icon:5});
				}
			});
		});
	});