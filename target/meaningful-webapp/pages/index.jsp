<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico"/>
<title>meaningful</title>
</head>
<body class="body-ico-white">
	<%@ include  file="top.jsp"%>
    <div class="div1" style="width: 100%;height: 1500px;position: relative;margin:0 auto;">
		<div style="width: 80%; height:100%; position: absolute;left:10%;top:5%;background-color: white">
			<div class="leftMenu" style="float: left;width: 20%;height: 40%;background-color: gray;">
	    		这是左边菜单
	   		 </div>
	   		 <div class="content" style="float: left;width: 80%;height: 100%;background-color: white;">
	   		 </div>
		</div>
	</div>
   
	<%@ include  file="bottom.jsp"%>
	<script src="/js/index.js"></script>
</body>
</html>
