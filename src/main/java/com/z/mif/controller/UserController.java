package com.z.mif.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



//import com.qq.connect.QQConnectException;
//import com.qq.connect.api.OpenID;
//import com.qq.connect.api.qzone.PageFans;
//import com.qq.connect.api.qzone.UserInfo;
//import com.qq.connect.javabeans.AccessToken;
//import com.qq.connect.javabeans.qzone.PageFansBean;
//import com.qq.connect.javabeans.qzone.UserInfoBean;
//import com.qq.connect.javabeans.weibo.Company;
//import com.qq.connect.oauth.Oauth;
import com.z.mif.constants.Constants;
import com.z.mif.entity.CommentEObj;
import com.z.mif.entity.UserEObj;
import com.z.mif.service.ICommentService;
//import com.z.mif.service.IUserService;
import com.z.mif.service.IUserService;
import com.z.mif.util.Configuration;
import com.z.mif.util.ImgUtil;
import com.z.mif.util.SendMail;
import com.z.mif.util.SendMessage;

@Controller
public class UserController extends BaseController{

	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@Autowired
	@Qualifier("commentService")
	private ICommentService commentService;
	
//	@RequestMapping("qqLogin")
//	public String qqLogin(HttpServletResponse response,HttpServletRequest request) throws IOException, QQConnectException{
//		return "redirect:" + new Oauth().getAuthorizeURL(request);
//	}
//	
//	@RequestMapping("qqRedirect")
//	public String qqRedirect(HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		PrintWriter out = response.getWriter();
//
//		try {
//			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
//
//			String accessToken = null, openID = null;
//			long tokenExpireIn = 0L;
//
//			if (accessTokenObj.getAccessToken().equals("")) {
//				// 我们的网站被CSRF攻击了或者用户取消了授权
//				// 做一些数据统计工作
//				System.out.print("没有获取到响应参数");
//			} else {
//				accessToken = accessTokenObj.getAccessToken();
//				tokenExpireIn = accessTokenObj.getExpireIn();//token过期时间？
//				System.out.println("accessToken = " + accessToken);
//				System.out.println("tokenExpireIn = " + tokenExpireIn);
//				request.getSession().setAttribute("demo_access_token",accessToken);
//				request.getSession().setAttribute("demo_token_expirein",String.valueOf(tokenExpireIn));
//
//				// 利用获取到的accessToken 去获取当前用户的openid -------- start
//				OpenID openIDObj = new OpenID(accessToken);
//				openID = openIDObj.getUserOpenID();
//				
//				out.println("欢迎你，openID为 " + openID + " 的用户!");
//				request.getSession().setAttribute("demo_openid", openID);
//				out.println("<a href=" + "/shuoshuoDemo.html"+ " target=\"_blank\">去看看发表说说的demo吧</a>");
//				// 利用获取到的accessToken 去获取当前用户的openid --------- end
//
//				out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- start </p>");
//				UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
//				UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
//				out.println("<br/>");
//				if (userInfoBean.getRet() == 0) {
//					out.println(userInfoBean.getNickname() + "<br/>");
//					out.println(userInfoBean.getGender() + "<br/>");
//					out.println("黄钻等级： " + userInfoBean.getLevel() + "<br/>");
//					out.println("会员 : " + userInfoBean.isVip() + "<br/>");
//					out.println("黄钻会员： " + userInfoBean.isYellowYearVip() + "<br/>");
//					out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL30()+ "/><br/>");
//					out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
//					out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL100() + "/><br/>");
//				} else {
//					out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
//				}
//				out.println("<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- end </p>");
//
//				out.println("<p> start ----------------------------------- 验证当前用户是否为认证空间的粉丝------------------------------------------------ start <p>");
//				PageFans pageFansObj = new PageFans(accessToken, openID);
//				PageFansBean pageFansBean = pageFansObj
//						.checkPageFans("97700000");
//				if (pageFansBean.getRet() == 0) {
//					out.println("<p>验证您" + (pageFansBean.isFans() ? "是" : "不是") + "QQ空间97700000官方认证空间的粉丝</p>");
//				} else {
//					out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + pageFansBean.getMsg());
//				}
//				out.println("<p> end ----------------------------------- 验证当前用户是否为认证空间的粉丝------------------------------------------------ end <p>");
//
//				out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- start </p>");
//				com.qq.connect.api.weibo.UserInfo weiboUserInfo = new com.qq.connect.api.weibo.UserInfo(
//						accessToken, openID);
//				com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo
//						.getUserInfo();
//				if (weiboUserInfoBean.getRet() == 0) {
//					// 获取用户的微博头像----------------------start
//					out.println("<image src="
//							+ weiboUserInfoBean.getAvatar().getAvatarURL30()
//							+ "/><br/>");
//					out.println("<image src="
//							+ weiboUserInfoBean.getAvatar().getAvatarURL50()
//							+ "/><br/>");
//					out.println("<image src="
//							+ weiboUserInfoBean.getAvatar().getAvatarURL100()
//							+ "/><br/>");
//					// 获取用户的微博头像 ---------------------end
//
//					// 获取用户的生日信息 --------------------start
//					out.println("<p>尊敬的用户，你的生日是： "
//							+ weiboUserInfoBean.getBirthday().getYear() + "年"
//							+ weiboUserInfoBean.getBirthday().getMonth() + "月"
//							+ weiboUserInfoBean.getBirthday().getDay() + "日");
//					// 获取用户的生日信息 --------------------end
//
//					StringBuffer sb = new StringBuffer();
//					sb.append("<p>所在地:" + weiboUserInfoBean.getCountryCode()
//							+ "-" + weiboUserInfoBean.getProvinceCode() + "-"
//							+ weiboUserInfoBean.getCityCode()
//							+ weiboUserInfoBean.getLocation());
//
//					// 获取用户的公司信息---------------------------start
//					ArrayList<Company> companies = weiboUserInfoBean
//							.getCompanies();
//					if (companies.size() > 0) {
//						// 有公司信息
//						for (int i = 0, j = companies.size(); i < j; i++) {
//							sb.append("<p>曾服役过的公司：公司ID-"
//									+ companies.get(i).getID() + " 名称-"
//									+ companies.get(i).getCompanyName()
//									+ " 部门名称-"
//									+ companies.get(i).getDepartmentName()
//									+ " 开始工作年-"
//									+ companies.get(i).getBeginYear()
//									+ " 结束工作年-" + companies.get(i).getEndYear());
//						}
//					} else {
//						// 没有公司信息
//					}
//					// 获取用户的公司信息---------------------------end
//
//					out.println(sb.toString());
//
//				} else {
//					out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + weiboUserInfoBean.getMsg());
//				}
//
//				out.println("<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- end </p>");
//
//			}
//		} catch (QQConnectException e) {
//		}
//		return "/";
//	}
	/**
	 * 跳转登录页面
	 * @param map
	 * @return
	 */
	@RequestMapping("/94071001/login")
	public String login(ModelMap map, @RequestParam(value="skipUri",required=false) String url){
		map.addAttribute(Constants.PAGE_TYPE, Constants.TYPE_LOGIN);
		map.addAttribute("skipUri", url);
		return "public";
	}
	
	@RequestMapping(value = "/logined",method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String logined(UserEObj user,@RequestParam(value="skipUri",required=false) String skipUri, HttpSession session,RedirectAttributes attr, HttpServletRequest request) throws Exception{
		String loginParam = user.getLoginParam(),pwd = user.getDecryptPassword();
		user = userService.get(user);
		if(user != null){
			//未读消息的数量
			Integer unread = commentService.Unread(user.getId());
			session.setAttribute(Constants.UNREAD, unread > 0 ? unread : null);
			session.setAttribute(Constants.SESSION_USER, user);
			if(!StringUtils.isEmpty(skipUri)) return "redirect:" + skipUri;
			String sessionSkipUri = (String) session.getAttribute(Constants.SESSION_URI);
			if(!StringUtils.isEmpty(sessionSkipUri)){
				return "redirect:" + sessionSkipUri;
			}else
				return "redirect:/";
		}else{
			attr.addFlashAttribute("loginParam", loginParam);
			attr.addFlashAttribute("pwd", pwd);
			attr.addFlashAttribute("errMsg", "帐号或密码错误");
			return "redirect:/94071001/login";
		}
	}
	
	/**
	 * 跳转注册页面
	 * @param type @PathVariable修饰 表示形参同URL中的请求参数 
	 * @return
	 */
	@RequestMapping("/83746134/toRegister")
	public String toRegister(ModelMap map, @RequestParam(value="skipUri",required=false) String url){
		map.addAttribute(Constants.PAGE_TYPE, Constants.TYPE_REGISTER);
		map.addAttribute("skipUri", url);
		return "public";
	}
	
	/**
	 * 设置 个人信息保存
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/u/profile/sv")
	public @ResponseBody String ps(UserEObj entity, HttpSession session) throws Exception{
		entity.setId(getSessionUser(session).getId());
		userService.update(entity);
		entity = userService.get(entity.getId());
		session.setAttribute(Constants.SESSION_USER, entity);
		return null;
	}
	
	/**
	 * 绑定手机号和邮箱 
	 * @param type 手机 或邮箱
	 * @param map
	 * @return
	 */
	@RequestMapping("/u/bind/{type}")
	public String bind(@PathVariable String type, ModelMap map, HttpSession session){
		if(StringUtils.isEmpty(type)) 
			return "error/404";
		else if(!type.equals("email") && !type.equals("phone")) 
			return "error/404";
		// 判断是否已经绑定过  如果是  则跳转修改绑定页面    防止是输入路径进来的
		UserEObj u = (UserEObj) session.getAttribute(Constants.SESSION_USER);
		if(type.equals("email") && !StringUtils.isEmpty(u.getEmail())) return "redirect:/u/changeEmail";
		else if(type.equals("phone") && !StringUtils.isEmpty(u.getCellphone())) return "redirect:/u/changePhone";
		
		map.addAttribute(Constants.PAGE_TYPE, Constants.TYPE_BIND);
		map.addAttribute("type", type);
		return "public";
	}
	
	@RequestMapping("/u/bind/confirm")
	public String bindNext(HttpSession session, ModelMap map, @RequestParam("loginParam") String loginParam,
							@RequestParam(value = "phoneCode", required = false) String phoneCode,
							@RequestParam(value = "emailCode", required = false) String emailCode) throws Exception{
		String code = Constants.SESSION_PHONE_CODE, val = phoneCode, type = "phone";
		UserEObj u = new UserEObj();
		u.setCellphone(loginParam);
		if(StringUtils.isEmpty(phoneCode)){
			code =  Constants.SESSION_EMAIL_CODE;
			val = emailCode;
			type = "email";
			u.setCellphone(null);
			u.setEmail(loginParam);
		}
		
		String sessionCode = (String) session.getAttribute(code);
		if(StringUtils.isEmpty(val) 
				|| !sessionCode.substring(0,sessionCode.indexOf("_")).equalsIgnoreCase(val)
				|| (System.currentTimeMillis() - Long.valueOf(sessionCode.substring(sessionCode.indexOf("_")+1))) > Constants.SESSION_CODE_PERIOD){
			map.put("loginParam", loginParam);
			map.put("errMsg", "验证码错误");
			map.addAttribute(Constants.PAGE_TYPE, Constants.TYPE_BIND);
			map.addAttribute("type", type);
			return "public";
		}else{
			u.setId(((UserEObj) session.getAttribute(Constants.SESSION_USER)).getId());
			userService.update(u);
			u = userService.get(u.getId());
			session.setAttribute(Constants.SESSION_USER, u);
		}
		return "redirect:/u/48824877/setting";
	}
	
	/**
	 * 更换绑定手机号
	 * @return
	 */
	@RequestMapping("/u/changePhone")
	public String changePhone(ModelMap map){
		map.addAttribute(Constants.PAGE_TYPE, Constants.TYPE_CHANGE_PHONE);
		return "public";
	}
	
	/**
	 * 更换绑定邮箱
	 * @return
	 */
	@RequestMapping("/u/changeEmail")
	public String changeEmail(ModelMap map){
		map.addAttribute(Constants.PAGE_TYPE, Constants.TYPE_CHANGE_EMAIL);
		return "public";
	}
	
	@RequestMapping("/u/{changeType}/{way}")
	public String changeEmailByPhoneOrChangePhoneByEmail(RedirectAttributes attr,@PathVariable String changeType,@PathVariable String way) throws Exception{
		if(StringUtils.isEmpty(way) || StringUtils.isEmpty(changeType)) 
			return "error/404";
		else if(!changeType.equals("changeEmail") && !changeType.equals("changePhone")) 
			return "error/404";
		else if(!way.equals("email") &&!way.equals("phone")) 
			return "error/404";
		
		attr.addFlashAttribute("way",way);
		attr.addFlashAttribute("step",2);
		return "redirect:/u/" + changeType;
	}
	
	@RequestMapping("/u/{changeType}/nextStep")
	public String changeEmailNextStep(@RequestParam(value = "step")String step,
			@RequestParam(value = "imgCode",required = false)String imgCode,
			@RequestParam(value = "phoneCode",required = false)String phoneCode,
			@RequestParam(value = "emailCode",required = false)String emailCode,
			@PathVariable String changeType,
			HttpSession session,RedirectAttributes attr){
		boolean flag = true;
		if(step.equals("2")){//点击第一个下一步
			if(StringUtils.isEmpty(imgCode) || !imgCode.equalsIgnoreCase(session.getAttribute(Constants.SESSION_IMG_CODE).toString()))
				flag = false;
		}else if(step.equals("3")) {//点击第二个下一步
			String code = null,codeSession = null,phoneOrEamilCode = null;
			if(!StringUtils.isEmpty(phoneCode)){
				code = Constants.SESSION_PHONE_CODE;
				phoneOrEamilCode = phoneCode;
			}
			if(!StringUtils.isEmpty(emailCode)){
				code = Constants.SESSION_EMAIL_CODE;
				phoneOrEamilCode = emailCode;
			}
		
			codeSession = (String) session.getAttribute(code);
			if(StringUtils.isEmpty(phoneOrEamilCode) 
					|| !codeSession.substring(0,codeSession.indexOf("_")).equalsIgnoreCase(phoneOrEamilCode)
					|| (System.currentTimeMillis() - Long.valueOf(codeSession.substring(codeSession.indexOf("_")+1))) > Constants.SESSION_CODE_PERIOD){
				flag = false;
			}
		}
		if(!flag){
			step = String.valueOf((Integer.parseInt(step) - 1));
			attr.addFlashAttribute("errMsg","验证码错误");
		}
		attr.addFlashAttribute("step",step);
//		attr.addFlashAttribute("email",((UserEObj)session.getAttribute(Constants.SESSION_USER)).getEmail());
		return "redirect:/u/" + changeType;
	}
	
	/**
	 * 更换绑定邮箱和手机号的 确认
	 * @param loginParam
	 * @param emailCode
	 * @param phoneCode
	 * @param session
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/u/change/confirm")
	public @ResponseBody Map<String,String> changeEmailConfirm(@RequestParam("loginParam")String loginParam,
			@RequestParam(value = "emailCode",required = false)String emailCode,
			@RequestParam(value = "phoneCode",required = false)String phoneCode,
			HttpSession session) throws Exception{
		
		String code = null,codeSession = null,phoneOrEamilCode = null,url = null;
		UserEObj user = new UserEObj();
		Map<String,String> map = new HashMap<String, String>();
		if(!StringUtils.isEmpty(phoneCode)){
			code = Constants.SESSION_PHONE_CODE;
			phoneOrEamilCode = phoneCode;
			url = "changePhone";
			user.setCellphone(loginParam);
		}else if(!StringUtils.isEmpty(emailCode)){
			code = Constants.SESSION_EMAIL_CODE;
			phoneOrEamilCode = emailCode;
			url = "changeEmail";
			user.setEmail(loginParam);
		}else{
			map.put("url", "error/404");
			return map;
		}
	
		codeSession = (String) session.getAttribute(code);
		if(StringUtils.isEmpty(phoneOrEamilCode) 
				|| !codeSession.substring(0,codeSession.indexOf("_")).equalsIgnoreCase(phoneOrEamilCode)
				|| (System.currentTimeMillis() - Long.valueOf(codeSession.substring(codeSession.indexOf("_")+1))) > Constants.SESSION_CODE_PERIOD){
			map.put("errMsg", "验证码错误");
			return map;
		}
		
		user.setId(((UserEObj) session.getAttribute(Constants.SESSION_USER)).getId());
		userService.update(user);
		user = userService.get(user.getId());
		session.setAttribute(Constants.SESSION_USER, user);
		map.put("url", "/u/48824877/setting");
		return map;
	}
	
	/**
	 * 忘记密码
	 * @return
	 */
	@RequestMapping("/7540240/forget")
	public String forget(@RequestParam(value = "loginParam",required = false)String loginParam,ModelMap map){
		map.addAttribute(Constants.PAGE_TYPE, Constants.TYPE_FORGET_PASSWORD);
		return "public";
	}
	/**
	 * 忘记密码  下一步
	 * @return
	 */
	@RequestMapping("/forget/nextStep")
	public String forgetNextStep(@RequestParam("loginParam")String loginParam,
			@RequestParam(value = "step")String step,
			@RequestParam(value = "imgCode",required = false)String imgCode,
			@RequestParam(value = "phoneOrEamilCode",required = false)String phoneOrEamilCode,
			RedirectAttributes attr,
			HttpSession session){
		String eviType = "phone",code = Constants.SESSION_PHONE_CODE;
		boolean flag = true;
		if(loginParam.indexOf("@") > -1){
			eviType = "email";
			code = Constants.SESSION_EMAIL_CODE;
		}
		if(step.equals("2")){
			if(StringUtils.isEmpty(imgCode) || !imgCode.equalsIgnoreCase(session.getAttribute(Constants.SESSION_IMG_CODE).toString()))
				flag = false;
		}else if(step.equals("3")){
			String codeSession = (String) session.getAttribute(code);
			if(StringUtils.isEmpty(phoneOrEamilCode) 
				|| !codeSession.substring(0,codeSession.indexOf("_")).equalsIgnoreCase(phoneOrEamilCode)
				|| (System.currentTimeMillis() - Long.valueOf(codeSession.substring(codeSession.indexOf("_")+1))) > Constants.SESSION_CODE_PERIOD){
				flag = false;
			}
		}
		if(!flag){
			step = String.valueOf((Integer.parseInt(step) - 1));
			attr.addFlashAttribute("errMsg","验证码错误");
		}
		attr.addFlashAttribute("step",step);
		attr.addFlashAttribute("eviType",eviType);
		attr.addFlashAttribute("loginParam",loginParam);
		return "redirect:/7540240/forget";
	}
	
	/**
	 * 忘记密码  确认
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/forget/confirm")
	public @ResponseBody String forgetConfirm(UserEObj user,HttpSession session) throws Exception{
		userService.updatePwd(user);//修改信息
		return null;
	}
	
	@RequestMapping("/u/48824877/setting")
	public String setting(ModelMap map){
		map.addAttribute(Constants.PAGE_TYPE, Constants.TYPE_SET);
		return "public";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String register(UserEObj user,HttpSession session) throws Exception{
		String errorMsg = null,code = Constants.SESSION_PHONE_CODE,codeSession = null;
		if(user.getLoginParam().indexOf("@") > -1){
			code = Constants.SESSION_EMAIL_CODE;
			user.setEmail(user.getLoginParam());
		}else 
			user.setCellphone(user.getLoginParam());
		codeSession = (String) session.getAttribute(code);
		
		if(!session.getAttribute(Constants.SESSION_IMG_CODE).toString().equalsIgnoreCase(user.getImgCode())){//验证图片验证码
			errorMsg = "图片验证码错误";
		}else if(codeSession == null){//手机邮箱验证码为空
			errorMsg = "手机或邮箱验证码错误";
		}else if(!codeSession.substring(0,codeSession.indexOf("_")).equalsIgnoreCase(user.getPhoneOrEamilCode())){//手机邮箱验证码与session中的不符
			errorMsg = "手机或邮箱验证码错误";
		}else if((System.currentTimeMillis() - Long.valueOf(codeSession.substring(codeSession.indexOf("_")+1))) > Constants.SESSION_CODE_PERIOD){//手机邮箱验证码过期 有效期5min
			errorMsg = "验证码已过期";
		}else{
			userService.add(user);
//			user = userService.get(userId);
//			session.setAttribute(Constants.SESSION_USER, user);
			session.removeAttribute(code);//清除session验证码
		}
		return errorMsg;
	}
	
	@RequestMapping(value = "/isExistUser", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String isExistUser(UserEObj user) throws Exception{
		String isExist = null;
		List<UserEObj> users = userService.list(user);
		if(!StringUtils.isEmpty(users) && users.size() > 0)
			isExist = "exist";
		return isExist;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	/**
	 * 获取session的值
	 * @param key
	 * @param session
	 * @return
	 */
	@RequestMapping(value ="/getSessionAttr", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getSessionAttr(@RequestParam("key") String key,HttpSession session){
		String code = (String) session.getAttribute(key);
		if(!StringUtils.isEmpty(code) && (key.equals(Constants.SESSION_EMAIL_CODE) || key.equals(Constants.SESSION_PHONE_CODE))){
			if((System.currentTimeMillis() - Long.valueOf(code.substring(code.indexOf("_")+1))) > Constants.SESSION_CODE_PERIOD)
				code = null;//验证码过期
			else
				code = code.substring(0,code.indexOf("_"));
		}
		return StringUtils.isEmpty(code) ? null : code.toLowerCase();
	}
	
	/**
	 * 发送验证码
	 * @param target 手机或邮箱
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sendCode")
	public @ResponseBody String sendCode(@RequestParam("target") String target,HttpSession session) throws Exception{
		String code = null;
		if(target.indexOf("@") > -1){//else 邮箱
			code = String.valueOf(new Random().nextInt(9999-1000+1)+1000);
			new SendMail().sendMail(target,code);
			session.setAttribute(Constants.SESSION_EMAIL_CODE, code + "_" + System.currentTimeMillis());
		}else{
			code = SendMessage.SendMsg(target);
			System.out.println("手机验证码为：" + code);
			session.setAttribute(Constants.SESSION_PHONE_CODE, code + "_" + System.currentTimeMillis());
		}
		
		return null;
	}
	
	@RequestMapping("/u/head")
	public String head(ModelMap map){
		map.addAttribute(Constants.PAGE_TYPE, Constants.TYPE_UPDATE_HEAD);
		return "public";
	}
	
	/**
	 * 
	 * @param path 头像路径 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/u/updateHead")
	public @ResponseBody String updateHead(@RequestParam("path")String path, @RequestParam("x")Integer x, @RequestParam("y")Integer y,
			@RequestParam("w")Integer width, @RequestParam("h")Integer height,HttpSession session) throws Exception{
		String headName = System.currentTimeMillis() + ".png";
		String toPath = Configuration.getProperty(Constants.HEAD_URL) + File.separator + headName;//裁剪后保存的图片
		

		// TODO 这里暂时加上绝对路径   放在服务器上时  修改配置文件的文件上传目录后  则去掉
		path = "D:/workspace/meaningful-webapp/src/main/webapp" + path;
		System.out.println(path + "==");
		ImgUtil.cutImg(path, toPath, x, y, width, height);
		
		Integer userId = ((UserEObj)session.getAttribute(Constants.SESSION_USER)).getId();
		UserEObj u = new UserEObj();
		u.setHeadName(toPath);
		u.setId(userId);
		userService.update(u);
		u = userService.get(userId);
		session.setAttribute(Constants.SESSION_USER, u);
		return null;
	}
	
}

