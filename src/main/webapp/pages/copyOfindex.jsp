<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico"/>
<title>meaningful</title>
<style type="text/css">
/* 标签重定义 */
img{border:none;}
a{text-decoration:none;color:#444;}
a:hover{color:#999;}
#title{width:600px;margin:20px auto;text-align:center;}
/* 定义关键帧 */
@-webkit-keyframes shade{
	from{opacity:1;}
	15%{opacity:0.4;}
	to{opacity:1;}
}
@-moz-keyframes shade{
	from{opacity:1;}
	15%{opacity:0.4;}
	to{opacity:1;}
}
@-ms-keyframes shade{
	from{opacity:1;}
	15%{opacity:0.4;}
	to{opacity:1;}
}
@-o-keyframes shade{
	from{opacity:1;}
	15%{opacity:0.4;}
	to{opacity:1;}
}
@keyframes shade{
	from{opacity:1;}
	15%{opacity:0.4;}
	to{opacity:1;}
}
/* wrap */
#wrap{width:auto;height:auto;margin:0 auto;position:relative;}
#wrap .box{width:280px;height:auto;padding:10px;border:none;float:left;}
#wrap .box .info{width:280px;height:auto;border-radius:8px;box-shadow:0 0 11px #666;background:#fff;}
#wrap .box .info .pic{width:260px;height:auto;margin:0 auto;padding-top:10px;}
#wrap .box .info .pic:hover{
	-webkit-animation:shade 3s ease-in-out 1;
	-moz-animation:shade 3s ease-in-out 1;
	-ms-animation:shade 3s ease-in-out 1;
	-o-animation:shade 3s ease-in-out 1;
	animation:shade 3s ease-in-out 1;
}
#wrap .box .info .pic img{width:260px;border-radius:3px;}
#wrap .box .info .title{width:260px;height:55px;margin:0 auto;line-height:40px;text-align:center;color:#666;font-size:18px;font-size:10px;overflow:hidden;}
</style>
</head>
<body class="body-ico-white">
	<%@ include  file="top.jsp"%>
	<div style="margin: 0 auto;position: relative;height:100%; margin-top:68px; ">
		<div style="width: 80%; height:100%; position: absolute;left:10%;top:0;" id="nav">
   		 	<div id="wrap">
	   		 	<c:forEach items="${paging.datas}" var="item" varStatus="s">
	   		 		<div class="box">
						<div class="info">
							<div class="pic">
								<a href="/story/${s.index+1}"><img src="/images/${item.pictureName}" /></a>
							</div>
							<div class="title">
							<div class="left" style="display:inline-block; width: 50%;word-break: break-all;height: 100%;line-height: 25px;">
								标签1、标签2、标签3、标签4、标签2、标签3、标签1、标签2、标签3
							</div>
							<div class="right" style="padding-right:20px;">
								<div style="height: 25px;line-height: 25px;">
								<img src="/images/head/defaultHead.jpg" style="border-radius:50%;width: 25px;height: 25px;"/>
								张三丰
								</div>
								<div style="height: 25px;line-height: 25px;">
								2016.5.12
								</div>
							</div>
								
							</div>
						</div>
					</div>
				</c:forEach>
   		 	</div>
		</div>
	</div>
<%@ include  file="bottom.jsp"%>
<script src="/js/imagesloaded.pkgd.js"></script>
<script src="/js/masonry.pkgd.min.js"></script>
<script type="text/javascript">
$(window).scroll(function(){
// 	$(".box").each(function(){
		
	PBL('wrap','box');
// 	})
});
window.onload = function(){
	//运行瀑布流主函数
	PBL('wrap','box');
	//模拟数据
	var data = [
					{'src':'1.jpg','title':'素材家园-sucaijiayuan.com'},
					{'src':'2.jpg','title':'素材家园-sucaijiayuan.com'},
					{'src':'3.jpg','title':'素材家园-sucaijiayuan.com'},
					{'src':'4.jpg','title':'素材家园-sucaijiayuan.com'},
					{'src':'5.jpg','title':'素材家园-sucaijiayuan.com'},
					{'src':'6.jpg','title':'素材家园-sucaijiayuan.com'},
					{'src':'7.jpg','title':'素材家园-sucaijiayuan.com'},
					{'src':'8.jpg','title':'素材家园-sucaijiayuan.com'},
					{'src':'9.jpg','title':'素材家园-sucaijiayuan.com'},
					{'src':'10.jpg','title':'素材家园-sucaijiayuan.com'}
				];
	
	
	//设置滚动加载
	window.onscroll = function(){
		//校验数据请求
		if(getCheck()){
			var wrap = document.getElementById('wrap');
			for(i in data){
				//创建box
				var box = document.createElement('div');
				box.className = 'box';
				wrap.appendChild(box);
				//创建info
				var info = document.createElement('div');
				info.className = 'info';
				box.appendChild(info);
				//创建pic
				var pic = document.createElement('div');
				pic.className = 'pic';
				info.appendChild(pic);
				//创建img
				var img = document.createElement('img');
				img.src = 'images/'+data[i].src;
				img.style.height = 'auto';
				pic.appendChild(img);
				//创建title
				var title = document.createElement('div');
				title.className = 'title';
				info.appendChild(title);
				//创建a标记
				var a = document.createElement('a');
				a.innerHTML = data[i].title;
				title.appendChild(a);
			}
			PBL('wrap','box');
		}
	}
}
/**
* 瀑布流主函数
* @param  wrap	[Str] 外层元素的ID
* @param  box 	[Str] 每一个box的类名
*/
function PBL(wrap,box){
	//	1.获得外层以及每一个box
	var wrap = document.getElementById(wrap);
	var boxs  = getClass(wrap,box);// 返回 warp下的所有box   数组
	//	2.获得屏幕可显示的列数
	var boxW = boxs[0].offsetWidth;//box的宽度为 300
	var width = $('body').css("width");
	width = width.substr(0,width.indexOf("p"));
	var colsNum = Math.floor(width*0.8/boxW);//获得每行可显示的列数
	
	
	wrap.style.width = boxW*colsNum+'px';//为外层赋值宽度
	//	3.循环出所有的box并按照瀑布流排列
	var everyH = [];//定义一个数组存储每一列的高度
	for (var i = 0; i < boxs.length; i++) {
		if(i < colsNum){
			everyH[i] = boxs[i].offsetHeight;
			console.info(everyH+"------------------")
		}else{
			var minH = Math.min.apply(null,everyH);//获得最小的列的高度
			var minIndex = getIndex(minH,everyH); //获得最小列的索引
			getStyle(boxs[i],minH,boxs[minIndex].offsetLeft,i);
			everyH[minIndex] += boxs[i].offsetHeight;//更新最小列的高度
			console.info(everyH+"=======================")
		}
	}
}
/**
* 获取类元素
* @param  warp		[Obj] 外层
* @param  className	[Str] 类名
*/
function getClass(wrap,className){
	var obj = wrap.getElementsByTagName('*');
	var arr = [];
	for(var i=0;i<obj.length;i++){
		if(obj[i].className == className){
			arr.push(obj[i]);
		}
	}
	return arr;
}
/**
* 获取最小列的索引
* @param  minH	 [Num] 最小高度
* @param  everyH [Arr] 所有列高度的数组
*/
function getIndex(minH,everyH){
	for(index in everyH){
		if (everyH[index] == minH ) return index;
	}
}
/**
* 数据请求检验
*/
function getCheck(){
	var documentH = document.documentElement.clientHeight;
	var scrollH = document.documentElement.scrollTop || document.body.scrollTop;
	return documentH+scrollH>=getLastH() ?true:false;
}
/**
* 获得最后一个box所在列的高度
*/
function getLastH(){
	var wrap = document.getElementById('wrap');
	var boxs = getClass(wrap,'box');
	return boxs[boxs.length-1].offsetTop+boxs[boxs.length-1].offsetHeight;
}
/**
* 设置加载样式
* @param  box 	[obj] 设置的Box
* @param  top 	[Num] box的top值
* @param  left 	[Num] box的left值
* @param  index [Num] box的第几个
*/
var getStartNum = 0;//设置请求加载的条数的位置
function getStyle(box,top,left,index){
    if (getStartNum>=index) return;
    $(box).css({
    	'position':'absolute',
        'top':top,
        "left":left,
        "opacity":"0"
    });
    $(box).stop().animate({
        "opacity":"1"
    },999);
    getStartNum = index;//更新请求数据的条数位置
}
</script>
</body>
</html>
