package cn.digitalpublishing.po;

import java.util.Date;

import cn.digitalpublishing.util.DateFormatUitl;

public class Task {
	
	
	private String id;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 目标服务
	 */
	private String targateServer;
	/**
	 * 推送状态
	 */
	private Integer status;
	
	private String userTemplateName;
	
	private String targateTemplateName;
	
	private String ip;
	
	private String port;
	
	
	private String username;
	
	private String password;
	

	private Date createTime;
	private String createTimeStr;
	
	private String pushFilePath;
	
	
	private String ftpdir;
	
	private Books books;
	
	
	private String  classify;
	/**
	 * isbn
	 */
	private String isbn;
	/**
	 * 书籍名称
	 */
	private String bookName;
	/**
	 * 出版社
	 */
	private String publisher;
	
	/**
	 * 打包时间
	 */
	private Date dabaoDate;
	
	/**
	 * 推送系统的上传开始时间点
	 */
	private Date tuisDate;

	/**
	 * 推送系统的上传结束时间
	 */
	private Date tuieDate;
	
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Books getBooks() {
		return books;
	}
	public void setBooks(Books books) {
		this.books = books;
	}
	public String getFtpdir() {
		return ftpdir;
	}
	public void setFtpdir(String ftpdir) {
		this.ftpdir = ftpdir;
	}
	public String getPushFilePath() {
		return pushFilePath;
	}
	public void setPushFilePath(String pushFilePath) {
		this.pushFilePath = pushFilePath;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		createTimeStr=DateFormatUitl.formatDate(createTime);
		this.createTime = createTime;
	}
	
	
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getUserTemplateName() {
		return userTemplateName;
	}
	public void setUserTemplateName(String userTemplateName) {
		this.userTemplateName = userTemplateName;
	}
	
	public String getTargateTemplateName() {
		return targateTemplateName;
	}
	public void setTargateTemplateName(String targateTemplateName) {
		this.targateTemplateName = targateTemplateName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTargateServer() {
		return targateServer;
	}
	public void setTargateServer(String targateServer) {
		this.targateServer = targateServer;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	} 
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public Date getDabaoDate() {
		return dabaoDate;
	}
	public void setDabaoDate(Date dabaoDate) {
		this.dabaoDate = dabaoDate;
	}
	public Date getTuisDate() {
		return tuisDate;
	}
	public void setTuisDate(Date tuisDate) {
		this.tuisDate = tuisDate;
	}
	public Date getTuieDate() {
		return tuieDate;
	}
	public void setTuieDate(Date tuieDate) {
		this.tuieDate = tuieDate;
	}
	
	
}
