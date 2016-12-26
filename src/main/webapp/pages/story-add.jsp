<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/jquery.tag-editor.css" type="text/css" rel="stylesheet"/>
<title>写你所想</title>
</head>
<body>
	<%@ include file="top.jsp"%>
	<div class="p-nav">
		<div class="pp-nav">
			<form class="layui-form" action="" id="story-form">
				<i class="iconfont icon-tianjiatupian1" id="upload-img" title="选择封面图片"></i>
				<img src="" style="" id="cover-img"/>
				<div class="layui-form-item story-title">
					<div class="layui-input-block fixOnScroll" id="title-tip">
						<input id="title" type="text" name="title"placeholder="请输入故事标题" class="layui-input cont fixOnScroll" />
						<span class="fixOnScroll surp">还可以输入<span class="char" data-dead="12">12</span>字</span>
					</div>
				</div>
				<div class="layui-form-item mgt">
					<div class="layui-input-block offcent">
						<div id="content" contenteditable="true" placeholder='有些话，我想写下来'></div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<label class="layui-form-label" id="fmtit">把封面图片作为查看时的背景图片</label>
						 <div class="layui-input-block">
					      <input type="checkbox" name="backgroundInd" lay-skin="switch" value="Y"/>
					    </div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block">
						<label class="layui-form-label tags-tit" >标签</label>
						 <div class="layui-input-inline tagEditor-nav" id="tagEditor-tips">
					      <input type="text"  id="tagEditor" placeholder="标签"/>
					    </div>
					</div>
				</div>
				
				<div class="layui-input-block">
					<button type="button" class="layui-btn layui-btn-normal layui-btn-radius" id="save">保&nbsp;存</button>
				</div>
				<input type="hidden" name="pictureName" />
				<input type="hidden" name="tags" />
				<textarea class="hide" name="content"></textarea>
			</form>
			<form id="img_form" enctype="multipart/form-data">
				<input type="file" id="upload-file" class="hide" name="file" accept="image/jpeg,image/jpg,image/png,image/bmp,image/gif"/>
				<input type="hidden" name="width" value="260" />
				<input type="hidden" name="height" value="400"/>
				<input type="hidden" name="fileType" value="storyCover"/>
			</form>
		</div>
	</div>	
<input type="hidden" class="adsorbent" value="nav1"/><!-- 吸附导航选中 -->
<div id="upload-dialog" class="hide story-dialog">
	<img src="" alt="封面" id="img-preview"/>
</div>
<%@ include file="bottom.jsp"%>
<script src="/js/jquery.form.min.js"></script>
<script src="/js/jquery.tag-editor.min.js"></script>
<script src="/js/jquery.caret.min.js"></script>
<script>
$(function(){
	var tags;
	$('#tagEditor').tagEditor({
		placeholder : '不超过5个标签',
		 delimiter: '、',
		maxLength : 7,//单个标签最多字符数
		beforeTagSave: function(field, editor, tags, tag, val) {
			tags = $('#tagEditor').tagEditor('getTags')[0].tags;
			if(tags.length == 5) $('#tagEditor').tagEditor('removeTag',tags[0])
	    }
	});
	layui.use('form', function(){
	    var form = layui.form();
	});
	$(window).scroll(function(){
		if($(this).scrollTop() > 21) $('.fixOnScroll').addClass('fix');
		else $('.fixOnScroll').removeClass('fix');
	});
	$('#save').click(function(){
		tags = $('#tagEditor').tagEditor('getTags')[0].tags;
		var m = true;
		if(!$('#title').val().replace(/\s+/g,'')){
			m = false;
			layer.tips('请输入标题', "#title-tip", {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if(!$('#content').html().replace(/&nbsp;/ig, '').replace(/\s+/g,'')){
			m = false;
			layer.tips('请输入内容', "#content", {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if(!$('#cover-img').attr('src')){
			m = false;
			layer.tips('请选择封面图片', "#upload-img", {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if(tags.length == 0){
			m = false;
			layer.tips('请输入标签', "#tagEditor-tips", {
				  tips: [2,'#F7B824'],
				  tipsMore: true
			});
		}
		if(!m) return;
		layer.confirm('确定提交吗？',{
			btn : ['确定','返回重新编辑'],
			title : '提示'
		},function(){
			$("input[name='pictureName']").val($('#cover-img').attr('src'));
			$("input[name='tags']").val(tags);
			$("textarea[name='content']").html($('#content').html());
			$.ajax({
				url : '/u/story/save',
				type : 'post',
				data : $('#story-form').serialize(),
				async : false,
				success : function(){
					 layer.msg('保存成功', {icon: 6},function(){
						 location.href = '/';
					 });
				}
			});
		});
	});
	var flag;
	$('#upload-file').change(function(){
		if(!flag) return;
		var index;
		$("#img_form").ajaxSubmit({
			url : "/file/upload",
			type : "post",
			async : false,
    		processData : false,
    		contentType : false,
    		dataType : "json",
    		success : function(data){
    			if(data.status == false)
    				layer.msg(data.msg,{icon:5});
    			else{
    				var h = Number(data.h)+ 200+ 'px'; 
	    			index = layer.open({
	    				title : '上传封面图片',
	    				type : 1,
	    				content : $("#upload-dialog"),
	    				area : ['500px',h],
	    				shadeClose : true,
	    				btn : ["保存","重新选择"],
	    				yes : function(){
	    					flag = false;
	    					layer.close(index);
	    					$('#cover-img').attr('src',$('#img-preview').attr('src'));
	    				},
	    				btn2 : function(){
	    					$('#upload-file').val('');
	    					$('#upload-file').trigger('click');
	    				},
	    				end : function(){
	    					$('#upload-file').val('');
	    				}
	    			});
	    			$('#img-preview').attr('src',data.saveFile);
				}
    		}
		});
	});
	$('#upload-img').click(function(){
		flag = true;
		$('#upload-file').trigger('click');
	});
});
</script>
</body>
</html>
