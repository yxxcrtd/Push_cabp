package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@SuppressWarnings("serial")
public class TSource implements Serializable  {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件路径
	 */
	private String filePath;
	/**
	 * 状态 1-未解析 2-已解析 3-解析成功 4-解析出错 5-解析中
	 */
	private Integer status;
	/**
	 * ftp文件夹目录
	 */
	private String ftpFileDir;
	
	/**
	 * 产品数据
	 */
	private Set<TProduct> products;
	
	/**
	 * ftp标识
	 */
	private String ftpcode;
	
	/**
	 * 修改时间
	 */
	private Date updateDate;
	
	private String ip;
	
	private String Port;
	
	private String username;
	//非po
	private Integer type;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	private String password;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return Port;
	}
	public void setPort(String port) {
		Port = port;
	}
	
	
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getFtpFileDir() {
		return ftpFileDir;
	}
	public void setFtpFileDir(String ftpFileDir) {
		this.ftpFileDir = ftpFileDir;
	}
	public String getFtpcode() {
		return ftpcode;
	}
	public void setFtpcode(String ftpcode) {
		this.ftpcode = ftpcode;
	}
	public Set<TProduct> getProducts() {
		return products;
	}
	public void setProducts(Set<TProduct> products) {
		this.products = products;
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
