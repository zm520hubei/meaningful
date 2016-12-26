<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>便利贴</title>
</head>
<body>
	<jsp:include page="top.jsp" />
	<div style="margin: 0 auto;position: relative; ">
		<div style="width: 90%; height:auto; position: absolute;left:5%;top:0;line-height: 40px; margin:0 0 68px 0;">
			<ul id="note">
				<c:forEach items="${paging.datas}" var="item">
					<li class="box">
						<a href="javascript:;">
							<i class="iconfont icon-tuding"></i>
							<h2>${item.nickName}</h2>
							<p>${item.content}</p>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<input type="hidden" value="${paging.start+paging.limit}" class="start"/>
	<input type="hidden" value="${paging.total}" class="total"/>
	<input type="hidden" class="adsorbent" value="nav2"/><!-- 吸附导航选中 -->
	<div id="note-dialog" style="outline:none;" class="hide" align="center">
		<i class="iconfont icon-tuding"></i>
		<span style="position: absolute;bottom: 8px;right: 10px;font-size: 14px;color: #9E9E9E;">你还能输入<span class="char" data-dead="100">100</span>字</span>
		<textarea class="cont" style="display:block;height:17em;width:18em;padding:1em;margin:0;outline:none;line-height: 28px;border:none;resize: none;font-size: 100%;" placeholder="写点什么吧。。。"></textarea>
	</div>
	<jsp:include page="bottom.jsp" />
<script src="/js/jquery.waterfall.min.js"></script>
<script src="/js/note.js"></script>
</body>
</html>