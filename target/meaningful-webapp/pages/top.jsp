<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/css/index.css" type="text/css" rel="stylesheet"/>
<link href="/css/iconfont.css" rel="stylesheet"/>

<div class="wrapper-header settings-top_background" id="wrapper_header">
        <div class="header">
			<!-- 顶部logo -->
            <div class="wrapper-logo settings-top_background headerdisplay">
                <div class="logo settings-text_color">
                        <a class="logo-link settings-h1_color" href="/">
                         <img src="/logo(64x64).png"/>
                        </a>  
                </div>
            </div>
	 		<!-- 顶部导航 -->
	        <div class="wrapper-nav settings-top_background headerdisplay">
	            <!-- 一级菜单 -->
				<ul id="top-nav-link" class="nav-link clearfix">
				  <li>
				    <a class="txt-overflow settings-top_color settings-nav_border settings-tophover_color on" id="navlink_434189" href="/">首页</a>
				  </li>
				  <li>
				    <a class="txt-overflow settings-top_color settings-nav_border settings-tophover_color" id="navlink_434190" href="http://wap.koudaitong.com/v2/tag/32c7n48p">吧啦吧啦</a>
				  </li>
				  <li>
				    <a class="txt-overflow settings-top_color settings-nav_border settings-tophover_color" id="navlink_434191" href="/types/T000007">吧啦吧啦</a>
				  </li>
				  <li>
				    <a class="txt-overflow settings-top_color settings-nav_border settings-tophover_color" id="navlink_434192" href="/types/T000003">吧啦吧啦</a>
				  </li>
				  <li>
				    <a class="txt-overflow settings-top_color settings-nav_border settings-tophover_color" id="navlink_434193" href="/pages/queries">吧啦吧啦</a>
				  </li>
				  <li>
				    <a class="txt-overflow settings-top_color settings-nav_border settings-tophover_color" id="navlink_434194" href="/pages/aboutthreel">吧啦吧啦</a>
				  </li>
			 	</ul>   
	        </div>
 			<!-- 顶部搜索与登录 -->       
            <div class="searchlogin headerdisplay settings-top_color">
            		<c:if test="${not empty user}">
            			<div class="userHead">
							<a href="/user/" >
		        				<img src="/images/defaultHead.jpg">
		        				<cite>${user.account}</cite>
		    				</a>
		    				<a id="setting" href="javascript:;"><i class="iconfont icon-shezhi"></i> 设置</a>
		    				<a id="logout" href="javascript:;"><i class="iconfont icon-tuichu"></i> 登出</a>
	    				</div>
            		</c:if>
            		<c:if test="${empty user}">
			            <a id="login" class="header-link settings-top_color settings-tophover_color" href="javascript:;">
			           		<span class="headerpc-link">登录</span>
			            </a>
			           	<span class="header-link settings-top_color">|</span>
			            <a id="register" class="header-link settings-top_color settings-tophover_color" href="javascript:;">注册</a>
		            </c:if>
            </div>
        </div>
    </div>
