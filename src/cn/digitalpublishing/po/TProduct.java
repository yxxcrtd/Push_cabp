package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class TProduct implements Serializable  {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 存放路径
	 */
	private String path;
	/**
	 * 操作状态
	 */
	private Integer status;
	/**
	 * 修改时间
	 */
	private Date updateDate;
	/**
	 * 处理状态 
	 */
	private Integer operStatus;
	
	private TSource source;
	/**
	 * 图书编号
	 */
	private String code;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public TSource getSource() {
		return source;
	}
	public void setSource(TSource source) {
		this.source = source;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getOperStatus() {
		return operStatus;
	}
	public void setOperStatus(Integer operStatus) {
		this.operStatus = operStatus;
	}
	
}
