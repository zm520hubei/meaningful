<%@ page language="java"
 contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${story.title}</title>
<style type="text/css">
 body {background:#2f373a;font-family:Arial,Helvetica,sans-serif;font-size:100%;line-height:1em;color:#4e4e4e;}
 .comment{
 	line-height: 25px;
 }
 .reply{
 	background-color: #f7f8fa;
 }
 #content{
 	min-height:300px;text-align:left;padding:10px;border-radius:5px;
 }
</style>
</head>
<body>
<c:if test="${story.backgroundInd eq 'Y'}">
	<button class="layui-btn layui-btn-radius toggBg" style="position: fixed;left: 75%;top: 40%;">不显示背景图片</button>
</c:if>
	<%@ include file="top.jsp"%>
	<div style="margin: 0 auto;position: relative;min-width: 750px; ">
		<div style="width: 52%; height:auto; position: absolute;left:20%;top:0;line-height: 40px; margin:30px 0 68px 0;">
			<!-- 内容 -->
			<h2><strong>${story.title}</strong></h2>
			
			<div id="content" style="background: url(/images/${story.backgroundInd eq 'Y' ? story.pictureName : ''}) no-repeat;background-size: cover">
				${story.content}
			</div>
			
			<!-- 评论按时间从先到后排列   发表评论后显示在最后面，并且页面显示最底层  -->
			<div class="comment">
				<!-- 分为跟评论  和  跟回复   -->
				<c:forEach items="${roots}" var="root" varStatus="rs">
					<div style="margin:10px auto 5px auto;padding:10px;border: 1px #ccc solid;border-radius:5px;">
						<div class="left" style="width: 17%;font-size: 14px;font-weight: 700;" align="center">
							<img src="${fn:substring(root.headName,46,-1)}" style="width:60px;height:60px;border-radius:50%;border:1px #ccc solid"/> <br/>
							<a href="/用户中心" class="a">${root.nickName}</a>
						</div>
						<div class="right ctarea" align="left" style="width:83%">
							<div class="hover-del">
								<div id="${root.id}">${root.content}</div>
								<div class="right py" style="margin-top:100px;padding:10px;font-size: 12px;">
									<fmt:formatDate value="${root.createTime}" pattern="yyyy年MM月dd日 HH时mm分ss秒 E"/> 
									<c:if test="${empty user or root.userId != user.id}">
										<a href="javascript:;" class="${empty user ? 'goComment' : 'reply'} root-reply" alt="${root.id}" data-reply="${root.nickName}">
											<i title="回复" class="iconfont icon-huifu"></i>
										</a>
									</c:if>
									<c:if test="${not empty user and root.userId == user.id}">
										<a href="javascript:;" class="del font-small hide" alt="${root.id}">
											<i title="删除" class="iconfont icon-shanchu"></i>
										</a>
									</c:if> 
									<span class="font-small">#${rs.index+1}</span>
								</div>
								<div class="clearfix"></div>
							</div>
								<!--  下面是回复  -->
							<div class="reply" style="word-break:break-all;border-radius:10px;">
								<c:forEach items="${child}" var="map">
									<c:if test="${map.key == root.id}">
									<c:forEach items="${map.value}" var="c" varStatus="cs">
									
									<c:if test="${cs.last}">
										<input type="hidden" value="${root.id}${c.id+1}" class="replay-anchor"/>
									</c:if>
									<div id="${root.id}${c.id}" class="py hover-del" style="padding:10px 10px 0 10px;${cs.last ? '' : 'border-bottom: 1px gray dashed;'}">
										<div>
											<img src="${fn:substring(c.headName,46,-1)}" style="width:30px;height:30px;border-radius:50%;border:1px #ccc solid"/> 
											<span style="font-size: 12px;"><a href="/用户中心" class="a">${c.nickName}</a></span> 
											<c:if test="${c.level > 1}">
												 <span style="font-size: 13px;">回复</span> 
												 <span style="font-size: 12px;"><a href="/用户中心" class="a">${c.parentNickName}</a></span>
											</c:if>
											:${c.content}
										</div>
										<div class="right" style="font-size: 12px;margin-top:20px;">
											<fmt:formatDate value="${c.createTime}" pattern="yyyy年MM月dd日 HH时mm分ss秒 E"/> 
											<c:if test="${empty user or c.userId != user.id}">
												<a href="javascript:;" class="${empty user ? 'goComment' : 'reply'}" alt="${c.id}" data-reply="${c.nickName}">
													<i title="回复" class="iconfont icon-huifu"></i>
												</a>
											</c:if>
											<c:if test="${not empty user and c.userId == user.id}">
												<a href="javascript:;" class="del font-small hide" alt="${c.id}">
													<i title="删除" class="iconfont icon-shanchu"></i>
												</a>
											</c:if>
										</div>
										<div class="clearfix"></div>
									</div>
									</c:forEach>
									</c:if>
								</c:forEach>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<c:if test="${rs.last}">
						<input type="hidden" class="comment-anchor" value="${root.id+1}"/>
					</c:if>
				</c:forEach>
			</div>
			
			<!-- 编辑器  -->
			<div style="margin:68px 0 10px 0;outline:0;" class="rel editer" tabindex="0">
				<textarea id="comment" style="display: none;"></textarea>
				<div class="abs ${empty user ? '' : 'hide'}" style="top: 23%;width: 100%;height: 72%;padding-top: 5%;text-align: center;">
					<a href="javascript:window.location.href='/94071001/login?skipUri=' + window.location.pathname" class="layui-btn layui-btn-radius layui-btn-normal">登录</a>
					<a href="javascript:window.location.href='/83746134/toRegister?skipUri=' + window.location.pathname" class="layui-btn layui-btn-radius layui-btn-normal">注册</a>
				</div>
			</div>
			<button class="layui-btn layui-btn-radius ${empty user ? '' : 'submit-comment'}">提交评论</button>
			<c:if test="${user.id != 9}">
			<button class="layui-btn layui-btn-radius" onclick="javascript:WS.send(9);">发送消息</button>
			</c:if>
		</div>
	</div>
<input type="hidden" class="adsorbent" value="nav1"/><!-- 吸附导航选中 -->
<input type="hidden" id="pictureName" value="/images/${story.pictureName}"/>
<input type="hidden" id="storyId" value="${story.id}"/>
<input type="hidden" id="parentId" value="0"/>
<input type="hidden" id="anchor"/>
<div style="width: 400px;background-color: #fff;" id="reply-nav" class="hide">
<button onclick="return false;" tabindex="-1" contenteditable="false" style="position: absolute;left: 55px;top: 10px;vertical-align: baseline;cursor: text;display: inline-block;font-size: 1em;border: 0 none;background: none;overflow: visible;padding: 0;margin-right: 1px;text-align: left;color:#999;"> 回复 <span id="reply-to"></span>: </button>
<button class="submit-reply" style="cursor:pointer;float: right;padding: 11px;background-color: #009688;color: #fff;border: none;">发表回复</button>
<textarea id="reply" style="display: none;"></textarea>
</div>
<%@ include file="bottom.jsp"%>
<script>
$(function(){
	function reply(index, parentId, anchor){
		$.ajax({
			url : '/story/comment',
			type : 'post',
			async : false,
			data : {'content':layedit.getContent(index),'storyId':$('#storyId').val(),'parentId':parentId},
			success : function(){
				window.location.assign(location.href.substring(0,location.href.indexOf('#')) +'#' + anchor);
				location.reload();
			}
		});
	}
	var layedit,commemtIndex,replyIndex;
	layui.use('layedit', function(){
	  layedit = layui.layedit;
	  commemtIndex = layedit.build('comment',{
		  height : 150,
		  tool: ['face']
	  }); 
	  replyIndex = layedit.build('reply',{
		  height : 100,
		  tool: ['face']
	  }); 
	  
	});
	
	$('.toggBg').click(function(){
		var _bg = $('#content');
		var _this = $(this);
		if(_bg.css('backgroundImage') == 'none'){
			_bg.css({'background':'url(' + $('#pictureName').val() + ') no-repeat','background-size':'cover'})
			_this.html('不显示背景图片');
		}
		else {
			_this.html('显示背景图片');
			_bg.css('background','')
		}
	});
	
	//让回复框一直固定在回复的下面
	window.onresize = function(){
		$('.reply-resize').trigger('click');
	}
	//点击回复框以外的元素关闭回复框
	$(document).click(function(){
		$('#reply-nav').hide();
	});
	//阻止事件冒泡 防止点击当前元素关闭回复框
	$('#reply-nav').click(function(e){
		 e.stopPropagation();
	});
	//阻止事件冒泡 防止点击表情关闭回复框
	$('body').on('click','.layui-util-face',function(e){
		 e.stopPropagation();
	});
	$('a').filter('.goComment').click(function(e){
		$('.editer').focus();
	});
	
	$('a').filter('.reply').click(function(e){
		 e.stopPropagation();
		var _this = $(this);
		var _p = _this.parents('.py');
		var top = _p.offset().top, left = _p.offset().left, w = _p.outerWidth(), h = _p.outerHeight();
		if(_this.hasClass('root-reply')) {
			w = _this.parents('.ctarea').outerWidth();
			left = _this.parents('.ctarea').offset().left
		}
		var nw = left, nh = top + h - 2; 
		$('#reply-nav').css({
			'display':'block',
			'position':'absolute',
			'top' : nh,
			'left': left,
			'width':w,
		});
		$('a').removeClass('reply-resize');
		_this.addClass('reply-resize');
		$('#parentId').val(_this.attr("alt"))
		$('#anchor').val(_this.parents('.ctarea').find('.replay-anchor').val());
		$('#reply-to').html(_this.attr('data-reply'));
	});
	$('.submit-reply').click(function(){
		if(!editEmpty(layedit.getContent(replyIndex))){
			layer.msg('回复内容不能为空',{icon:5});
			return false;
		}
		reply(replyIndex, $('#parentId').val(), $('#anchor').val());
	});
	$('.submit-comment').click(function(){
		if(!editEmpty(layedit.getContent(commemtIndex))){
			layer.msg('评论内容不能为空',{icon:5});
			return false;
		}
		reply(commemtIndex, 0, $('.comment-anchor').val());
	});
	$('.hover-del').mouseover(function(){
		$(this).find('.del').css('display','inline-block');
	}).mouseout(function(){
		$(this).find('.del').css('display','none');
	});
	$('.del').click(function(){
		layer.confirm('确认删除此条回复吗？回复下的内容也将被删除',{icon:3,title:'提示'},function(index){
			layer.close(index);
		});
	});
});
</script>
</body>
</html>