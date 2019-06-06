package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 推送对象
 * 
 * @author YangXinXin
 */
@SuppressWarnings("serial")
public class Content implements Serializable {
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 节点路径
	 */
	private String nodePath;
	

	/**
	 * 节点值
	 */
	private String nodeValue;
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

	public String getNodePath() {
		return nodePath;
	}
	
	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}
	
	public String getNodeValue() {
		return nodeValue;
	}

	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}

	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 源数据对象
	 */
	private TProduct tProduct;
	
	public TProduct gettProduct() {
		return tProduct;
	}

	public void settProduct(TProduct tProduct) {
		this.tProduct = tProduct;
	}

	/**
	 * Default Constructor
	 */
	public Content() {
		// 
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
