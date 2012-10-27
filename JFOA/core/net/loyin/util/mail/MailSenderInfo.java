package net.loyin.util.mail;

/** 
 * 发送邮件需要使用的基本信息 
 */
import java.util.Properties;

import net.loyin.util.PropertiesContent;


public class MailSenderInfo {
	/** 发送邮件的服务器的IP和端口 */
	public String mailServerHost = PropertiesContent.get("mail.smtp.host");
	public String mailServerPort = PropertiesContent.get("mail.smtp.port");
	/** 邮件发送者的地址 */
	public String fromAddress=PropertiesContent.get("mail.username");;
	/** 邮件接收者的地址 */
	public String[] toAddress;
	/** 抄送人地址 */
	public String[] ccAddress;
	/** 密送人地址 */
	public String[] bccAddress;

	/** 登陆邮件发送服务器的用户名 */
	public String userName = PropertiesContent.get("mail.username");
	/** 登陆邮件发送服务器的密码 */
	public String password = PropertiesContent.get("mail.password");
	/** 是否需要身份验证 */
	public boolean validate = true;
	/** 邮件主题 */
	public String subject;
	/** 邮件的文本内容 */
	public String content;
	/** 邮件附件的文件名 */
	public String[] attachFileNames;
	SimpleMailSender sms = new SimpleMailSender();

	/** 获得邮件会话属性 */
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}

	/** 发送邮件 */
	public void sendMail() {
		this.sms.sendHtmlMail(this);
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] fileNames) {
		this.attachFileNames = fileNames;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getToAddress() {
		return toAddress;
	}

	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public void setContent(String textContent) {
		this.content = textContent;
	}

	public String[] getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(String[] ccAddress) {
		this.ccAddress = ccAddress;
	}

	public String[] getBccAddress() {
		return bccAddress;
	}

	public void setBccAddress(String[] bccAddress) {
		this.bccAddress = bccAddress;
	}

}