package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Date;

import cn.digitalpublishing.util.DateFormatUitl;

@SuppressWarnings("serial")
public class Email implements Serializable  {
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 联系人
	 */
	private String contacts;

	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 发邮件时间
	 */
	private Date sendDate;
	private String sendDateStr;
	
	private String content;
	
	
	

	public Email() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
		sendDateStr=DateFormatUitl.formatDate(sendDate);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendDateStr() {
		return sendDateStr;
	}

	public void setSendDateStr(String sendDateStr) {
		this.sendDateStr = sendDateStr;
	}

	
}
