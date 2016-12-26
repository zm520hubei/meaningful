<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico"/>
<style type="text/css">

</style>
<c:if test="${page_type eq '2553574'}">
<style type="text/css">
body{
 min-width: 700px; 
}
</style>
</c:if>
<c:if test="${page_type eq '2553574'}">
<link href="/css/jquery.jcrop.min.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
/* Apply these styles only when #preview-pane has
   been placed within the Jcrop widget */
.jcrop-tracker{
    border: 1px rgba(0,0,0,0.075)solid;
}
.jcrop-holder #preview-pane {
  display: block;
  position: absolute;
  z-index: 100;
  top: 10px;
  right: -280px;
  padding: 6px;
/*   border: 1px rgba(0,0,0,.4) solid; */
  background-color: white;

/*   -webkit-border-radius: 6px; */
/*   -moz-border-radius: 6px; */
/*   border-radius: 6px; */

/*   -webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2); */
/*   -moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2); */
/*   box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2); */
}

/* The Javascript code will set the aspect ratio of the crop
   area based on the size of the thumbnail preview,
   specified here */
#preview-pane .preview-container {
  width: 250px;
  height: 250px;
  overflow: hidden;
  border-radius:50%;
  border: 1px solid rgba(0,0,0,0.075);
}
</style>
</c:if>
<title>meaningful-
<c:if test="${page_type eq '83746134'}">注册</c:if>
<c:if test="${page_type eq '94071001'}">登录</c:if>
<c:if test="${page_type eq '48824877'}">设置</c:if>
<c:if test="${page_type eq '7540240'}">找回密码</c:if>
<c:if test="${page_type eq '2553574'}">修改头像</c:if>
<c:if test="${page_type eq '4612455'}">修改手机号</c:if>
<c:if test="${page_type eq '2349606'}">修改邮箱</c:if>
<c:if test="${page_type eq '9482396'}">绑定${type eq 'phone' ? '手机' : '邮箱'}</c:if>
</title>
</head>
<body ${page_type eq '94071001' ? 'class="body-ico-white"' : ''}>
	<%@ include  file="top.jsp"%>
	<div class="clearfic"></div>
	<c:if test="${page_type eq '83746134'}">
		<%@ include file="register.jsp" %>
	</c:if>
	<c:if test="${page_type eq '94071001'}">
		<%@ include file="login.jsp" %>
	</c:if>
	<c:if test="${page_type eq '7540240'}">
		<%@ include file="forget.jsp" %>
	</c:if>
	<c:if test="${page_type eq '48824877'}">
		<%@ include file="setting.jsp" %>
	</c:if>
	<c:if test="${page_type eq '2553574'}">
		<%@ include file="head.jsp" %>
	</c:if>
	<c:if test="${page_type eq '4612455'}">
		<%@ include file="changePhone.jsp" %>
	</c:if>
	<c:if test="${page_type eq '2349606'}">
		<%@ include file="changeEmail.jsp" %>
	</c:if>
	<c:if test="${page_type eq '9482396'}">
		<%@ include file="bind.jsp" %>
	</c:if>
	
<input type="hidden" class="adsorbent" value="nav1"/><!-- 吸附导航选中 -->
<%@ include file="bottom.jsp"%>

<script>
layui.use('form', function(){
	layui.form();
});
</script>


<c:if test="${page_type eq '2553574'}">
	<script src="/js/jcrop/jquery.Jcrop.min.js"></script>
	<script src="/js/jquery.form.min.js"></script>
<script>
jQuery(function($){

    // Create variables (in this scope) to hold the API and image size
    var jcrop_api,
        boundx,
        boundy,

        // Grab some information about the preview pane
        $preview = $('#preview-pane'),
        $pcnt = $('#preview-pane .preview-container'),
        $pimg = $('#preview-pane .preview-container img'),

        xsize = $pcnt.width(),
        ysize = $pcnt.height();
    
    $('#target').Jcrop({
      onChange: updatePreview,
      onSelect: updatePreview,
      addClass: 'jcrop'
//       setSelect : [0,0,400,400]
    },function(){
      // Use the API to get the real image size
      var bounds = this.getBounds();
      boundx = bounds[0];
      boundy = bounds[1];
      // Store the API in the jcrop_api variable
      jcrop_api = this;

      // Move the preview into the jcrop container for css positioning
      $preview.appendTo(jcrop_api.ui.holder);
    });

    function updatePreview(c)
    {
      if (parseInt(c.w) > 0)
      {
        var rx = xsize / c.w;
        var ry = ysize / c.h;

        $pimg.css({
          width: Math.round(rx * boundx) + 'px',
          height: Math.round(ry * boundy) + 'px',
          marginLeft: '-' + Math.round(rx * c.x) + 'px',
          marginTop: '-' + Math.round(ry * c.y) + 'px'
        });
      }
    };
    $(".save").click(function(){
    	
    	var t = jcrop_api.tellScaled();
    	if(t.w == 0){
    		layer.msg("请画出裁剪区域.",{icon : 5})
    		return;
    	}
    	
    	$.ajax({
    		url : "/u/updateHead",
    		type : "post",
    		async : false,
    		data : {
    			x : t.x,
    			y : t.y,
    			x2: t.x2,
    			y2: t.y2,
    			h : t.h,
    			w : t.w,
    			path : $("#preview").attr("src")
    		},
    		success : function(){
    			layer.msg("修改成功",{icon : 1},function(){
    				location.reload();
    			})
    		},
    		error : function(){
    			layer.msg("呃，遇到了点小错误",{icon:5});     
    				  
    		}
    	});
    });
    
    $("#upload").change(function(){
    	
    	$("#head_form").ajaxSubmit({
			url : "/file/upload",
			type : "post",
			async : false,
    		processData : false,
    		contentType : false,
    		dataType : "json",
    		success : function(data){
    			$("#preview").attr("src",data.saveFile);
    			jcrop_api.setImage(data.saveFile);
    		}
		});
//     	$.ajax({
//     		url : "/file/upload",
//     		type : "post",
//     		cache : false,
//     		data : new FormData($("#head_form")[0]),
//     		processData : false,
//     		contentType : false,
//     		dataType : "json",
//     		success : function(data){
//     			$("#preview").attr("src",data.saveFile);
//     			jcrop_api.setImage(data.saveFile);
//     		}
//     	});
    });
  });

</script>
</c:if>
<c:if test="${page_type eq '48824877'}">
<script src="/js/jquery.autocompleter.min.js"></script>
<script>
	$(function(){
		var datas = [];
		$.getJSON("/addr/list", function(data, status, xhr) {
			for ( var i in data) {
				datas.push({
					"id" : data[i].value,
					"label" : data[i].label
				})
			}
		});
		 $('#addr').autocompleter({
		        // marker for autocomplete matches
		        highlightMatches: true,

		        // object to local or url to remote search
		        source: datas,

		        // custom template
		        template: '{{ label }}',

		        // show hint
		        hint: true,

		        // abort source if empty field
		        empty: false,

		        // max results
		        limit: 5,

		        callback: function (value, index, selected) {
		            if (selected) {
		            	$('.addr').val(selected.id);
		            	$('.addr').attr("data-hork",selected.label);
		            	//判断城市的时候    保证$('.addr')的value不为空  并且$('.addr')的data-hork属性值 == $('#addr')的value
		            }
		        }
		    });
	});
</script>					
</c:if>
</body>
</html>
