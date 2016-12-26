<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>meaningful</title>
<link href="/css/story.css" rel="stylesheet" type="text/css"/>
</head>
<body class="body-ico-white">
<jsp:include page="top.jsp" />
	<div class="waterfull full-length" id="waterfull">
		<ul>
			<c:forEach items="${paging.datas}" var="item">
			<li class="box">
				<div class="port-1 effect-3" >
                	<div class="image-box">
                    	<img src="images/${item.pictureName}" alt="Image-3">
                    </div>
                    <div class="text-desc">
                    	<h3>${item.title}</h3>
                        <p>${item.content}</p>
                    	<a href="/story/${item.id}" class="btn">Learn more</a>
                    </div>
                </div>
                <div class="extend">
                 	<div class="left labs">
                 		<c:forEach items="${fn:split(item.tags,',')}" var="tag" varStatus="s">
                 			${tag}
                 			<c:if test="${!s.last}">、</c:if>
                 		</c:forEach>
                 	</div>
                 	<div class="left auth">
                 		<a href="javascript:;">
                 			<img src="/images/head/defaultHead.jpg"/>
                 			${item.nickName}
                 		</a>
                 	</div>
                </div>
			</li>
			</c:forEach>
		</ul>
		
	</div>

<input type="hidden" value="${paging.start+paging.limit}" class="start"/>
<input type="hidden" value="${paging.total}" class="total"/>

<input type="hidden" class="adsorbent" value="nav1"/><!-- 吸附导航选中 -->
<jsp:include page="bottom.jsp" />
<script src="/js/jquery.waterfall.min.js"></script>
<script src="/js/story.js"></script>
</body>
</html>