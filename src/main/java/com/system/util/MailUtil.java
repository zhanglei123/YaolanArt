package com.system.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.system.po.Mail;


public class MailUtil {
	private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);
	
	//发件人基本信息
	private static String SEND_EMAIL_NAME = "瑶蓝艺术中心";
	private static String SEND_EMAIL_ACCOUNT = "1014681230@163.com";
	private static String SEND_EMAIL_PASSWORD = "zl900610";
	private static String SEND_EMAIL_SMTP_HOST = "smtp.163.com";

	public static void alarm(String subject,String content) {
		Mail mail = new Mail();
		mail.setSendEmailName(SEND_EMAIL_NAME);
		mail.setSendEmailAccount(SEND_EMAIL_ACCOUNT);
		mail.setSendEmailPassword(SEND_EMAIL_PASSWORD);
		mail.setSendEmailSMTPHost(SEND_EMAIL_SMTP_HOST);
		mail.setIsUseSSL(2);
		
		Map<String, String> recAccountMap = new HashMap<>();
		recAccountMap.put("lei.zhang2@100credit.com", "张磊");
		mail.setReceiveMailAccountMap(recAccountMap);
		
		mail.setSubject(subject);
		mail.setContent(content);
		
		Map<String, String> sendMail = sendMail(mail);
		if ("true".equals(sendMail.get("result"))) {
			logger.info("邮件发送成功");
		}else {
			logger.error("邮件发送失败，失败信息："+sendMail.get("message"));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public static Map<String, String> sendMail(Mail mail) {
		Map<String, String> resMap = new HashMap<>();
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Properties props = new Properties(); // 参数配置
		props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host", mail.getSendEmailSMTPHost()); // 发件人的邮箱的SMTP服务器地址
		props.setProperty("mail.smtp.auth", "true"); // 需要请求认证

		// PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
		// 如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
		// SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
		// 需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
		// QQ邮箱的SMTP(SLL)端口为465或587,其他邮箱自行去查看)
		if (mail.getIsUseSSL() == 1) {
			props.setProperty("mail.smtp.port", mail.getSmtpPort() + "");
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.socketFactory.port", mail.getSmtpPort() + "");
		}

		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getInstance(props);
		session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log

		// 3. 创建一封邮件
		MimeMessage message;
		try {
			message = createMimeMessage(session, mail);
		} catch (Exception e) {
			logger.error("创建邮件异常！", e);
			resMap.put("result", "false");
			resMap.put("message", "创建邮件异常！");
			return resMap;
		}

		// 4. 根据 Session 获取邮件传输对象
		Transport transport;
		try {
			transport = session.getTransport();
		} catch (NoSuchProviderException e) {
			logger.error("获取邮件传输对象异常！", e);
			resMap.put("result", "false");
			resMap.put("message", "获取邮件传输对象异常！");
			return resMap;
		}

		// 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
		//
		// PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
		// 仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
		// 类型到对应邮件服务器的帮助网站上查看具体失败原因。
		//
		// PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
		// (1) 邮箱没有开启 SMTP 服务;
		// (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
		// (3) 邮箱服务器要求必须要使用 SSL 安全连接;
		// (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
		// (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
		//
		// PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
		try {
			transport.connect(mail.getSendEmailAccount(), mail.getSendEmailPassword());
		} catch (MessagingException e) {
			logger.error("连接邮件服务器异常！", e);
			resMap.put("result", "false");
			resMap.put("message", "连接邮件服务器异常！");
			return resMap;
		}

		// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人,
		// 抄送人, 密送人
		try {
			transport.sendMessage(message, message.getAllRecipients());
		} catch (MessagingException e) {
			logger.error("发送邮件异常！", e);
			resMap.put("result", "false");
			resMap.put("message", "发送邮件异常！");
			return resMap;
		}

		// 7. 关闭连接
		try {
			transport.close();
		} catch (MessagingException e) {
			logger.error("发送邮件异常！", e);
			resMap.put("result", "false");
			resMap.put("message", "关闭连接异常！");
			return resMap;
		}

		resMap.put("result", "true");
		resMap.put("message", "邮件发送成功！");
		return resMap;
	}

	/**
	 * @description:创建邮件发送对象
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年7月30日 上午11:35:39
	 * @param session
	 * @param mail
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createMimeMessage(Session session, Mail mail) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);
		// 2. From: 发件人
		message.setFrom(new InternetAddress(mail.getSendEmailAccount(), mail.getSendEmailName(), "UTF-8"));
		// 3. To: 收件人（可以增加多个收件人、抄送、密送）setRecipient
		// To: 增加收件人（可选）addRecipient
		Map<String, String> receiveMailAccountMap = mail.getReceiveMailAccountMap();
		Set<Entry<String, String>> entrySet = receiveMailAccountMap.entrySet();
		for (Entry<String, String> entry : entrySet) {
			String address = entry.getKey();
			String name = entry.getValue();
			message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(address, name, "UTF-8"));
		}
		// Cc: 抄送（可选）
		// message.setRecipient(MimeMessage.RecipientType.CC, new
		// InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));
		// Bcc: 密送（可选）
		// message.setRecipient(MimeMessage.RecipientType.BCC, new
		// InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));

		// 4. Subject: 邮件主题
		message.setSubject(mail.getSubject(), "UTF-8");
		// 5. Content: 邮件正文（可以使用html标签）
		message.setContent(mail.getContent(), "text/html;charset=UTF-8");
		// 6. 设置发件时间
		message.setSentDate(new Date());
		// 7. 保存设置
		message.saveChanges();
		return message;
	}

	/**
	 * @description:校验邮箱的格式
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年7月30日 下午2:26:03
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {  
		boolean flag = false;
		try {
		    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
    }

	
	public static void main(String[] args) {
		Mail mail = new Mail();
		mail.setSendEmailName("张三");
		mail.setSendEmailAccount("1014681230@qq.com");
		mail.setSendEmailPassword("juilorrfefpbbeba");
		mail.setSendEmailSMTPHost("smtp.qq.com");
		mail.setIsUseSSL(2);
		
		Map<String, String> recAccountMap = new HashMap<>();
		recAccountMap.put("lei.zhang2@100credit.com", "李四");
		mail.setReceiveMailAccountMap(recAccountMap);
		
		mail.setSubject("测试邮件");
		mail.setContent("你好！！！");
		
		Map<String, String> sendMail = sendMail(mail);
		System.out.println("发送邮件返回："+sendMail);
	}
}
