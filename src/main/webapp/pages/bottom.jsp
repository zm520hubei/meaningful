<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/layui/layui.js"></script>
<script type="text/javascript" src="/js/memenu.js"></script>
<script>$(document).ready(function(){$(".memenu").memenu();});</script>
<!-- <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script> -->
<script src="/js/sockjs-0.3.min.js"></script>
<script src="/js/socket.init.js"></script>
<script src="/js/index.js"></script>
<c:if test="${not empty user}">
<script>WS.init();</script>
</c:if>
