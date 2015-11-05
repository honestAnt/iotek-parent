package com.iotekclass.common.util.mail;

import com.google.common.collect.Maps;
import com.iotekclass.common.constants.Constants;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class SimpleMailProcesser {

	private static Logger logger = LoggerFactory.getLogger(SimpleMailProcesser.class);

	private JavaMailSender mailSender;

	private String systemEmail;

	private Template forgetPwdTemplate;

	private Template registerTemplate;

	private Template feedbackTemplate;
	
	private Template sendLiveCodeMailTemplate;
	
	private Template sendTestMailTemplate;
	
	private Template sendApplyUniverMailTemplate;

	/**
	 * 激活邮箱模板
	 */
	private Template activeTemplate;

	/**
	 * 发送重置密码邮件
	 * 
	 * @param email 目标邮箱
	 * @param userName 用户名
	 * @param link 重置密码链接
	 */
	public void sendUserForgetPwdMail(String email, String userName, String link) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, Constants.DEFAULT_ENCODE_ENCODING);
			messageHelper.setSubject("重置职坐标密码");
			messageHelper.setFrom(systemEmail);
			messageHelper.setTo(email);
			Map<String, String> map = Maps.newHashMap();
			map.put("userName", userName);
			map.put("link", link);
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
			String format2 = df.format(now);
			map.put("now", format2);
			String content = generateContent(map, forgetPwdTemplate);
			messageHelper.setText(content, true);
			mailSender.send(msg);
			logger.info("邮件已发送至{}", email);
		} catch (MessagingException e) {
			logger.error("发送邮件失败", e);
		}
	}

	/**
	 * 发送账号激活邮件
	 * 
	 * @param email 目标邮箱
	 * @param link 激活链接
	 */
	public void sendRegisterMail(String email, String link) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, Constants.DEFAULT_ENCODE_ENCODING);
			messageHelper.setSubject("激活职坐标账号");
			messageHelper.setFrom(systemEmail);
			messageHelper.setTo(email);
			Map<String, String> map = Maps.newHashMap();
			map.put("email", email);
			map.put("link", link);
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
			String format2 = df.format(now);
			map.put("now", format2);

			DateTimeFormatter format = DateTimeFormat.forPattern(Constants.LONG_DATE_FORMAT_ZH);// 只精确到哪一天就好啦
			map.put("createTime", new DateTime().toString(format));

			String content = generateContent(map, registerTemplate);
			messageHelper.setText(content, true);
			mailSender.send(msg);
			logger.info("邮件已发送至{}", email);
		} catch (MessagingException e) {
			logger.error("发送邮件失败", e);
		}
	}

	/**
	 * 发送注册反馈邮件
	 * 
	 * @param email 用户名/邮箱
	 * @param password 密码
	 */
	public void sendFeedbackMail(String email, String password) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, Constants.DEFAULT_ENCODE_ENCODING);
			messageHelper.setSubject("职坐标账号注册反馈");
			messageHelper.setFrom(systemEmail);
			messageHelper.setTo(email);
			Map<String, String> map = Maps.newHashMap();
			map.put("username", email);
			map.put("password", password);
			DateTimeFormatter format = DateTimeFormat.forPattern(Constants.LONG_DATE_FORMAT_ZH);// 只精确到哪一天就好啦
			map.put("now", new DateTime().toString(format));

			String content = generateContent(map, feedbackTemplate);
			messageHelper.setText(content, true);
			mailSender.send(msg);
			logger.info("邮件已发送至{}", email);
		} catch (MessagingException e) {
			logger.error("发送邮件失败", e);
		}
	}

	/**
	 * 发送激活邮箱邮件
	 * 
	 * @param email 目标邮箱
	 * @param userName 用户名
	 * @param link 激活链接
	 */
	public void sendActiveMail(String email,String userName, String link) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, Constants.DEFAULT_ENCODE_ENCODING);
			messageHelper.setSubject("验证邮箱");
			messageHelper.setFrom(systemEmail);
			messageHelper.setTo(email);
			Map<String, String> map = Maps.newHashMap();
			map.put("userName", userName);
			map.put("link", link);
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
			String format2 = df.format(now);
			map.put("now", format2);
			String content = generateContent(map, activeTemplate);
			messageHelper.setText(content, true);
			mailSender.send(msg);
			logger.info("邮件已发送至{}", email);
		} catch (MessagingException e) {
			logger.error("发送邮件失败", e);
		}
	}
	
	/**
	 * 
	 * @Description: 发送直播课邀请码邮件
	 * @Author：gufeifei
	 * @Date：2015年7月5日 下午5:13:54
	 * @param email
	 * @param tempMap
	 * @throws
	 */
	public void sendLiveCodeMail(String email, Map<String, String> tempMap) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, Constants.DEFAULT_ENCODE_ENCODING);
			messageHelper.setSubject("公开直播课邀请码");
			messageHelper.setFrom(systemEmail);
			messageHelper.setTo(email);
			Map<String, String> map = Maps.newHashMap();
			map.put("userName", tempMap.get("userName"));
			map.put("code", tempMap.get("code"));
			map.put("beginTime", tempMap.get("beginTime"));
			map.put("endTime", tempMap.get("endTime"));
			map.put("link", tempMap.get("link"));
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
			String format2 = df.format(now);
			map.put("now", format2);
			String content = generateContent(map, sendLiveCodeMailTemplate);
			messageHelper.setText(content, true);
			mailSender.send(msg);
			logger.info("邮件已发送至{}", email);
		} catch (MessagingException e) {
			logger.error("发送邮件失败", e);
		}
	}
	
	/**
	 * 
	 * @Description: 发送申请高校联盟邮件
	 * @Author：gufeifei
	 * @Date：2015年8月17日 上午11:21:40
	 * @param email
	 * @param tempMap
	 * @throws
	 */
	public void sendApplyUniverMail(String email, Map<String, String> tempMap) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, Constants.DEFAULT_ENCODE_ENCODING);
			messageHelper.setSubject("申请入盟高校联盟");
			messageHelper.setFrom(systemEmail);
			messageHelper.setTo(email);
			Map<String, String> map = Maps.newHashMap();
			map.put("univerName", tempMap.get("univerName"));
			map.put("name", tempMap.get("name"));
			map.put("mobile", tempMap.get("mobile"));
			map.put("email", tempMap.get("email"));
			map.put("memo", tempMap.get("memo"));
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
			String format2 = df.format(now);
			map.put("now", format2);
			String content = generateContent(map, sendApplyUniverMailTemplate);
			messageHelper.setText(content, true);
			mailSender.send(msg);
			logger.info("邮件已发送至{}", email);
		} catch (MessagingException e) {
			logger.error("发送邮件失败", e);
		}
	}
	
	/**
	 * 
	 * @Description: 测试模板
	 * @Author：gufeifei
	 * @Date：2015年7月10日 下午1:45:59
	 * @param email
	 * @param tempMap
	 * @throws
	 */
	public void sendTestMail(String email, Map<String, String> tempMap) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, Constants.DEFAULT_ENCODE_ENCODING);
			messageHelper.setSubject(tempMap.get("emailTitle"));
			messageHelper.setFrom(systemEmail);
			messageHelper.setTo(email);
			Map<String, String> map = Maps.newHashMap();
			map.put("userName", tempMap.get("userName"));
			map.put("content", tempMap.get("content"));
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
			String format2 = df.format(now);
			map.put("now", format2);
			String content = generateContent(map, sendTestMailTemplate);
			messageHelper.setText(content, true);
			mailSender.send(msg);
			logger.info("邮件已发送至{}", email);
		} catch (MessagingException e) {
			logger.error("发送邮件失败", e);
		}
	}
	
	/**
	 * 生成邮件内容
	 * 
	 * @param map
	 * @param template
	 * @throws MessagingException
	 */
	private String generateContent(Map<String, String> map, Template template) throws MessagingException {
		try {
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
		} catch (IOException e) {
			logger.error("生成邮件内容失败, FreeMarker模板不存在", e);
			throw new MessagingException("FreeMarker模板不存在", e);
		} catch (TemplateException e) {
			logger.error("生成邮件内容失败, FreeMarker处理失败", e);
			throw new MessagingException("FreeMarker处理失败", e);
		}
	}

	/**
	 * 注入Freemarker引擎配置,构造Freemarker 邮件内容模板.
	 */
	public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) throws IOException {
		// 根据freemarkerConfiguration的templateLoaderPath载入文件.
		forgetPwdTemplate = freemarkerConfiguration.getTemplate("forgetPwdMailTemplate.ftl",
				Constants.DEFAULT_ENCODE_ENCODING);

		registerTemplate = freemarkerConfiguration.getTemplate("registerMailTemplate.ftl",
				Constants.DEFAULT_ENCODE_ENCODING);

		feedbackTemplate = freemarkerConfiguration.getTemplate("feedbackMailTemplate.ftl",
				Constants.DEFAULT_ENCODE_ENCODING);
		
		activeTemplate = freemarkerConfiguration.getTemplate("activeMailTemplate.ftl",
				Constants.DEFAULT_ENCODE_ENCODING);
		
		sendLiveCodeMailTemplate = freemarkerConfiguration.getTemplate("sendLiveCodeMailTemplate.ftl",
				Constants.DEFAULT_ENCODE_ENCODING);
		
		sendTestMailTemplate = freemarkerConfiguration.getTemplate("sendTestMailTemplate.ftl",
				Constants.DEFAULT_ENCODE_ENCODING);
		
		sendApplyUniverMailTemplate = freemarkerConfiguration.getTemplate("sendApplyUniverMailTemplate.ftl",
				Constants.DEFAULT_ENCODE_ENCODING);
	}

	public void setForgetPwdTemplate(Template forgetPwdTemplate) {
		this.forgetPwdTemplate = forgetPwdTemplate;
	}

	public void setRegisterTemplate(Template registerTemplate) {
		this.registerTemplate = registerTemplate;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setFeedbackTemplate(Template feedbackTemplate) {
		this.feedbackTemplate = feedbackTemplate;
	}

	public Template getActiveTemplate() {
		return activeTemplate;
	}

	public void setActiveTemplate(Template activeTemplate) {
		this.activeTemplate = activeTemplate;
	}

	public Template getSendLiveCodeMailTemplate() {
		return sendLiveCodeMailTemplate;
	}

	public void setSendLiveCodeMailTemplate(Template sendLiveCodeMailTemplate) {
		this.sendLiveCodeMailTemplate = sendLiveCodeMailTemplate;
	}

	public Template getSendTestMailTemplate() {
		return sendTestMailTemplate;
	}

	public void setSendTestMailTemplate(Template sendTestMailTemplate) {
		this.sendTestMailTemplate = sendTestMailTemplate;
	}

	public Template getSendApplyUniverMailTemplate() {
		return sendApplyUniverMailTemplate;
	}

	public void setSendApplyUniverMailTemplate(Template sendApplyUniverMailTemplate) {
		this.sendApplyUniverMailTemplate = sendApplyUniverMailTemplate;
	}
}
