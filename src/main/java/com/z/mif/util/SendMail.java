package com.z.mif.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class SendMail {
	
	public void sendMail(String to,String code) throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		String text = "";
		JavaMailSender javaMailSender = (JavaMailSender) SpringBeanUtils.getBean("javaMailSender");
		SimpleMailMessage simpleMailMessage = (SimpleMailMessage) SpringBeanUtils.getBean("simpleMailMessage");
		FreeMarkerConfigurer freeMarkerConfigurer = (FreeMarkerConfigurer) SpringBeanUtils.getBean("freeMarkerConfigurer");
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("codeEmai.ftl");
		//模板中用${XXX}站位，map中key为XXX的value会替换占位符内容。
		Map<String, String> map = new HashMap<String, String>();
		map.put("code",code);     
		text = FreeMarkerTemplateUtils.processTemplateIntoString(template,map);   
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
        helper.setFrom(simpleMailMessage.getFrom());  
        helper.setTo(to);  
        helper.setText(text);  
        helper.setSubject(simpleMailMessage.getSubject());  
        message.setContent(text, "text/html;charset=gb2312");
        javaMailSender.send(message);  
	}
}
