<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div style="box-sizing: border-box;min-width: 700px;margin: 0 auto;">
	<div style="height: 100px"></div>
	
	<div align="center" style="margin-left: -300px;position: relative;">
		<img src="${fn:substring(sessionScope.user.headName,46,-1)}" id="target" alt="[Jcrop Example]" style="width: 400px;height: 400px;"/>
		<div class="portlet-title" style="position: absolute;
    width: 60%;
    top: -16%;
    left: 30%;text-align:left; margin:0 auto;   border-bottom: 1px solid #eef1f5;padding: 0;margin-bottom: 10px;">
			<div class="caption font-green bold" style="font-size: 18px;line-height: 18px;padding: 10px 0;color: #32c5d2;font-weight: 700;">
				修改头像
			</div>
		</div>
	</div>
	<div id="preview-pane" style="margin-left:500px;">
		<form id="head_form" enctype="multipart/form-data">
			
			<input type="hidden" name="width" value="400">
			<input type="hidden" name="height" value="400">
			<input type="hidden" name="fileType" value="head">
<!-- 			<input type="button" value="选择图片" id="choosePic" style="width: 160px;height: 45px;font-size: 18px;background-color: #cd4450;color: white;position: absolute;top: 410px;left: -418px;cursor: pointer;"> -->
			<label id="choosePic" for="upload" style="width: 160px;height: 45px;font-size: 18px;background-color: #cd4450;color: white;position: absolute;top: 410px;left: -418px;cursor: pointer;line-height: 45px;border-radius: 3px;">
				选择图片
				<input type="file" id="upload" class="hide" name="file" accept="image/jpeg,image/jpg,image/png,image/bmp,image/gif">
			</label>
		</form>
		<input type="button" value="保存" class="save" style="width: 160px;height: 45px;font-size: 18px;background-color: #cd4450;color: white;position: absolute;top: 410px;left: -174px;cursor: pointer;">
		<div class="preview-container">
			<img src="${fn:substring(sessionScope.user.headName,46,-1)}" class="jcrop-preview" id="preview" alt="Preview" />
		</div>
	</div>
</div>
