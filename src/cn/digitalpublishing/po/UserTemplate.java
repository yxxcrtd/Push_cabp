package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

import cn.digitalpublishing.util.DateUtil;


@SuppressWarnings("serial")
public class UserTemplate implements Serializable {
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 模板标识 0代表用户模板 1 代表 终极模板（目标模板）
	 */
	private Integer flag;
	/**
	 * 模板类型： 0代表图书 1代表期刊
	 */
	private Integer type;
	
	/**
	 * 模板原名称
	 */
	private String originalName;
	
	/**
	 * 用户模板名称
	 */
	private String name;
	/**
	 * 模板文件在服務器上存放的名称
	 */
	private String fileName;
	
	/**
	 * 模板上传时间
	 */
	private Date createTime; 
	/**
	 * 时间格式化 格式化到秒
	 */
	private String strTime;
	
	/**
	 * 模板存储路径
	 */
	private String path;
	
	/**
	 * ftp对象
	 */
	private FtpConfigure ftpConfigure;
	/**
	 * 模板节点对象
	 */
	@JsonIgnore
	private Set<UserTemplateNode>  userTemplateNodes = new LinkedHashSet<UserTemplateNode>();
	
	/**
	 * ftp名称   
	 */
	private String ftpName;
	/**
	 * 拆分节点
	 */
	private String splitNode;
	/**
	 * 根元素
	 */
	private String rootNode;
	/**
	 * 图书编号
	 */
	private String bookCodeNode;
	/**
	 * 公共元素
	 */
	private String commonNode;
	/**
	 * 验证tld
	 */
	private String checkTLD;
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
	public String getRootNode() {
		return rootNode;
	}
	public void setRootNode(String rootNode) {
		this.rootNode = rootNode;
	}
	public String getBookCodeNode() {
		return bookCodeNode;
	}
	public void setBookCodeNode(String bookCodeNode) {
		this.bookCodeNode = bookCodeNode;
	}
	public String getCommonNode() {
		return commonNode;
	}
	public void setCommonNode(String commonNode) {
		this.commonNode = commonNode;
	}
	public String getCheckTLD() {
		return checkTLD;
	}
	public void setCheckTLD(String checkTLD) {
		this.checkTLD = checkTLD;
	}
	public String getSplitNode() {
		return splitNode;
	}
	public void setSplitNode(String splitNode) {
		this.splitNode = splitNode;
	}
	public String getFtpName() {
		return ftpName;
	}
	public void setFtpName(String ftpName) {
		this.ftpName = ftpName;
	}
	public Set<UserTemplateNode> getUserTemplateNodes() {
		return userTemplateNodes;
	}
	public void setUserTemplateNodes(Set<UserTemplateNode> userTemplateNodes) {
		this.userTemplateNodes = userTemplateNodes;
	}
	public FtpConfigure getFtpConfigure() {
		return ftpConfigure;
	}
	public void setFtpConfigure(FtpConfigure ftpConfigure) {
		this.ftpConfigure = ftpConfigure;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date date) {
		this.createTime = date;
		this.strTime=DateUtil.getStrDate(date);
	}
	public String getStrTime() {
		return strTime;
	}
	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}
	/**
	 * 模板文件路径
	 */
	private String templatePath;
	public String getTemplatePath() {
		return templatePath;
	}
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
}
