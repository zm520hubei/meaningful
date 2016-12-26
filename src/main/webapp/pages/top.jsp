<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="renderer" content="webkit"/>

<link href="/favicon.ico" rel="shortcut icon">
<link href="/js/layui/css/layui.css" type="text/css" rel="stylesheet"/>
<link href="/css/head.css" rel="stylesheet" type="text/css" media="all" />	
<link href="/css/memenu.css" rel="stylesheet" type="text/css" media="all" />
<link href="/css/index.css" rel="stylesheet" type="text/css" media="all" />
<link href="/css/note.css" rel="stylesheet" type="text/css"/>
<link href="//at.alicdn.com/t/font_jomxs3wcf59ltyb9.css" type="text/css" rel="stylesheet"/>

<div class="header">
		<!-- 登录后 un-login改为 al-login -->
		<div class="container ${empty user ? 'un-login' : 'al-login'}">
			<div class="head-top">
				<div class="logo">
					<!-- 朕就是LOGO -->
					<a href="/u/story/w" class="write"><i class="iconfont icon-icon01 write-icon" title="写你所想"></i></a>
				</div>
		  <div class="h_menu4">
				<ul class="memenu skyblue">
				 	<li class="nav1"><a  href="/">首页</a></li>	
				 	<li class="nav2"><a href="/n/ls">便利贴</a></li>
				    <li class="nav3"><a href="#">巴拉巴拉</a></li>
<!-- 			    	<li><a href="#">发泄墙</a></li> -->
<!-- 				    <li><a href="#">南瓜信使</a></li> -->
					<li class="nav4 lost"><a href="blog.html">关于我们</a></li>				
			 	 </ul> 
			</div>
			<div class="search">
				<form id="search-form" method="post" action="/list">
			     	<input type="text" name="parameter" placeholder="标签、标题或内容" value="${entity.parameter}"/>
				    <i class="iconfont icon-sousuo search-icon"></i>
				</form>
			</div>
			
			<div class="header-left">		 
				<ul>
					<c:if test="${empty user}">
			        	<li><a href="/94071001/login">登录</a></li>
			          	<li><a href="/83746134/toRegister" >注册</a></li>
		            </c:if>
					<c:if test="${not empty user}">
						<li><a href="" class="user-picture rel">
		      				<img src="${fn:substring(sessionScope.user.headName,46,-1)}">
		      				<cite>${user.nickName}</cite>
		      				<div id="Unread-msg">${unread}</div>
		      				 <input id="nickName" value="${user.nickName}" type="hidden">
	  					</a></li>
	  					<li><a href="/u/48824877/setting" class="author"><i class="iconfont icon-shezhi"></i> 设置</a></li>
	  					<li><a id="logout" href="/logout"><i class="iconfont icon-tuichu"></i> 登出</a></li>
	       			</c:if>
				</ul>
				<div class="clearfix"> </div>
 			</div> 
		</div>
		</div>
</div>
<div class="seize"></div>