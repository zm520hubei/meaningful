package com.z.mif.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.util.JavaScriptUtils;

import com.z.mif.entity.UserEObj;

@SuppressWarnings("all")
public class SendMessage {

	public static String SendMsg(String phone) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = "https://api.netease.im/sms/sendcode.action";
		HttpPost httpPost = new HttpPost(url);

		String appKey = "19e68f3517db97d8ecda769285215186";
		String appSecret = "61a77fe7d6ef";
		String nonce =  String.valueOf((new Random().nextInt(100000)));// 随机数
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder
				.getCheckSum(appSecret, nonce, curTime);// 参考 计算CheckSum的java代码

		// 设置请求的header
		httpPost.addHeader("AppKey", appKey);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");

		// 设置请求的参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile", phone));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

		// 执行请求
//		HttpResponse response = httpClient.execute(httpPost);

		// 打印执行结果
//		String result = EntityUtils.toString(response.getEntity(), "utf-8");
//		SendMessage code = JSONUtil.encode(result, SendMessage.class);
//		return code.getCode() != 200 ? null : code.getObj();
		return String.valueOf(new Random().nextInt(9000)+1000);
	}

	private Integer code;
	private String msg;
	private String obj;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

}
