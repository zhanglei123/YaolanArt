package com.system.po;

import java.util.Map;

public class Mail {
	/**
	 * 发件箱名称
	 */
	private String sendEmailName;
	/**
	 * 发件箱地址
	 */
	private String sendEmailAccount;
	/**
	 * 发件箱密码
	 * 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）
	 * 对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
	 */
	private String sendEmailPassword;
	/**
	 * 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
	 */
	private String sendEmailSMTPHost;
	/**
	 * 是否使用 SSL连接  1 使用  2 不使用
	 * 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启)
	 */
	private Integer isUseSSL;
	/**
	 * SMTP 服务器的端口  isUseSSL=1 时必填
	 * (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接, 
	 * 需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助, 
	 * QQ邮箱的SMTP(SLL)端口为465或587,其他邮箱自行去查看)
	 */
	private Integer smtpPort;
	/**
	 * 收件人邮箱地址集合
	 * 支持多人发送
	 * 
	 * map的  key  为收件箱地址
	 * 		value 为收件人名称
	 */
	private Map<String, String> receiveMailAccountMap;
	/**
	 * 邮件发送主题
	 * 如：打折钜惠
	 */
	private String subject;
	/**
	 * 邮件发送内容，支持html格式
	 * 如：XX用户你好, 今天全场5折, 快来抢购, 错过今天再等一年。。。
	 */
	private String content;

	
	public String getSendEmailName() {
		return sendEmailName;
	}

	public void setSendEmailName(String sendEmailName) {
		this.sendEmailName = sendEmailName;
	}

	public String getSendEmailAccount() {
		return sendEmailAccount;
	}

	public void setSendEmailAccount(String sendEmailAccount) {
		this.sendEmailAccount = sendEmailAccount;
	}

	public String getSendEmailPassword() {
		return sendEmailPassword;
	}

	public void setSendEmailPassword(String sendEmailPassword) {
		this.sendEmailPassword = sendEmailPassword;
	}

	public String getSendEmailSMTPHost() {
		return sendEmailSMTPHost;
	}

	public void setSendEmailSMTPHost(String sendEmailSMTPHost) {
		this.sendEmailSMTPHost = sendEmailSMTPHost;
	}

	public Integer getIsUseSSL() {
		return isUseSSL;
	}

	public void setIsUseSSL(Integer isUseSSL) {
		this.isUseSSL = isUseSSL;
	}

	public Map<String, String> getReceiveMailAccountMap() {
		return receiveMailAccountMap;
	}

	public void setReceiveMailAccountMap(Map<String, String> receiveMailAccountMap) {
		this.receiveMailAccountMap = receiveMailAccountMap;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	@Override
	public String toString() {
		return "Mail [sendEmailName=" + sendEmailName + ", sendEmailAccount=" + sendEmailAccount
				+ ", sendEmailPassword=" + sendEmailPassword + ", sendEmailSMTPHost=" + sendEmailSMTPHost
				+ ", isUseSSL=" + isUseSSL + ", smtpPort=" + smtpPort + ", receiveMailAccountMap="
				+ receiveMailAccountMap + ", subject=" + subject + ", content=" + content + "]";
	}
	
	
}
