window.onload = function(){
	$("#waterfull").waterfall({
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
				url : '/s/masonry',
				type : 'post',
				async : false,
				data : {'start':start,'limit':limit,"parameter" : $('input[name="parameter"]').val()},
				dataType : 'json', 
				success : function(data){
					$('.start').val(restart);
					data = eval(data);
					for(var i in data){
						callData.push({
							'title':data[i]['title'],
							'pictureJpg':data[i]['pictureName'],
							'tags':data[i]['tags'],
							'content':data[i]['content'],
							'id':data[i]['id'],
							'headJpg':data[i]['headJpg'],
							'nickName':data[i]['nickName']
						});
					}
				}
			});
		
	        var str = "";
	        var templ = '<li class="box"><div class="port-1 effect-3" ><div class="image-box"><img src="images/{{pictureName}}" alt="Image-3"></div><div class="text-desc"><h3>{{title}}</h3><p>{{content}}</p><a href="/story/{{id}}" class="btn">Learn more</a></div></div><div class="extend"><div class="left labs">${tag}</div><div class="left auth"><a href="javascript:;"><img src="/images/head/{{headJpg}}"/>{{nickName}}</a></div></div></li>';
	        for(var i = 0; i < callData.length; i++) {
	            str += templ.replace("{{pictureJpg}}",callData[i].pictureName).replace("{{title}}",callData[i].title).replace("{{tags}}",callData[i].tags).replace("{{content}}",callData[i].content).replace("{{id}}",callData[i].id).replace("{{headJpg}}",callData[i].headJpg).replace("{{nickName" +
	            		"}}",callData[i].nickName);
	        }
	        $(str).appendTo($("#waterfull"));
	        success();
	        end();
	    }
	});
};