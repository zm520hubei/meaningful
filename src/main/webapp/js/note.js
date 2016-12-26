window.onload = function(){
	$("#note").waterfall({
	    itemClass: ".box",
	    minColCount: 2,
	    spacingHeight: 20,
	    spacingWidth: 20,
	    resizeable: true,
	    ajaxCallback: function(success, end) {
	    	var callData = [],
	    		total = Number($('.total').val()),
				start = Number($('.start').val()), 
				limit = 5, 
				restart = start + 5;
			if(total - (start + limit) < limit){
				limit = total - start;
				restart = 0;
			}
			if(start == total){
				start = 0;
				limit = 5;			
			}
			$.ajax({
				url : '/n/masonry',
				type : 'post',
				async : false,
				data : {'start':start,'limit':limit,"parameter" : $('input[name="parameter"]').val()},
				dataType : 'json', 
				success : function(data){
					$('.start').val(restart);
					data = eval(data);
					for(var i in data){
						callData.push({
							'nickName':data[i]['nickName'],
							'content':data[i]['content']
						});
					}
				}
			});
		
	        var str = "";
	        var templ = '<li class="box"><a href="javascript:;"><i class="iconfont icon-tuding"></i><h2>{{nickName}}</h2><p>{{content}}</p></a></li>';
	        for(var i = 0; i < callData.length; i++) {
	            str += templ.replace("{{nickName}}",callData[i].nickName).replace("{{content}}",callData[i].content);
	        }
	        $(str).appendTo($("#note"));
	        success();
	        end();
	    }
	});
};