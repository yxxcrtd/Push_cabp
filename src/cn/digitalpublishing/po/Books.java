package cn.digitalpublishing.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import cn.digitalpublishing.util.DateFormatUitl;

@SuppressWarnings("serial")
public class Books implements Serializable  {
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 征订号
	 */
	private String orderNo;

	/**
	 * 书名
	 */
	private String title;
	
	/**
	 * 作者
	 */
	private String author;

	/**
	 * 出版时间
	 */
	private Date publishDate;

	private String publishDateStr;
	

	/**
	 * ISBN号
	 */
	private String isbn;

	/**
	 * 纸书定价
	 */
	private BigDecimal price;
	
	/**
	 * 电子书在线阅读价
	 */
	private BigDecimal onPrice;
	
	/**
	 * 电子书离线阅读价
	 */
	private BigDecimal offPrice;

	/**
	 * 出版社名称
	 */
	private String publisher;
	
	/**
	 * 出版社地址
	 */
	private String location;

	/**
	 * 版次
	 */
	private String edition;

	/**
	 * 起始页数
	 */
	private Integer page;
	private String pageStr;
	
	/**
	 * 终止页数
	 */
	private Integer pagend;
	private String pagendStr;
	
	/**
	 * 类别
	 */
	private String category;

	/**
	 * 装帧
	 */
	private String frame;

	/**
	 * 开本
	 */
	private String format;

	/**
	 * 印张
	 */
	private BigDecimal sheet;

	/**
	 * 封面
	 */
	private String cover;
	
	/**
	 * 回链地址
	 */
	private String url;
	
	/**
	 * 简介
	 */
	private String introduction;
	
	/**
	 * 关键词
	 */
	private String keyword;
	
	/**
	 * 语种
	 */
	private String language;
	
	/**
	 * 尺寸
	 */
	private String size;
	
	/**
	 * 版权年
	 */
	private Date copyrightYear;
	
	/**
	 * 丛书名称
	 */
	private String series;
	
	/**
	 * 子标题
	 */
	private String subTitle;
	
	/**
	 * 折扣率
	 */
	private String discount;
	
	private String filePath;
	
	/**
	 * 创建时间
	 */
	private Date createTime = new Date();
	private String createTimeStr;
	
	//0是不存在，1 是已存在
	private String cunZai;
	
	private Set<Task> taskSet;
	
	public Books() {
		// 
	}
	
	public Set<Task> getTaskSet() {
		return taskSet;
	}

	public void setTaskSet(Set<Task> taskSet) {
		this.taskSet = taskSet;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDateStr=DateFormatUitl.formatDateTime(publishDate);
		this.publishDate = publishDate;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getOnPrice() {
		return onPrice;
	}

	public void setOnPrice(BigDecimal onPrice) {
		this.onPrice = onPrice;
	}

	public BigDecimal getOffPrice() {
		return offPrice;
	}

	public void setOffPrice(BigDecimal offPrice) {
		this.offPrice = offPrice;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.pageStr=String.valueOf(page);
		this.page = page;
	}
	
	public String getPageStr() {
		return pageStr;
	}

	public void setPageStr(String pageStr) {
		this.page=Integer.parseInt(pageStr);
		this.pageStr = pageStr;
	}

	public String getFrame() {
		return frame;
	}

	public void setFrame(String frame) {
		this.frame = frame;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public BigDecimal getSheet() {
		return sheet;
	}

	public void setSheet(BigDecimal sheet) {
		this.sheet = sheet;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTimeStr=DateFormatUitl.formatDateTime(createTime);
		this.createTime = createTime;
	}
	
	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTime=DateFormatUitl.stringToDate(createTimeStr);
		this.createTimeStr = createTimeStr;
	}

	public String getPublishDateStr() {
		return publishDateStr;
	}

	public void setPublishDateStr(String publishDateStr) {
		this.publishDate=DateFormatUitl.stringToDatetime(publishDateStr);
		this.publishDateStr = publishDateStr;
	}

	public String getCunZai() {
		return cunZai;
	}

	public void setCunZai(String cunZai) {
		this.cunZai = cunZai;
	}

	public Integer getPagend() {
		return pagend;
	}

	public void setPagend(Integer pagend) {
		this.pagendStr=String.valueOf(pagend);
		this.pagend = pagend;
	}
	
	public String getPagendStr() {
		return pagendStr;
	}

	public void setPagendStr(String pagendStr) {
		this.pagendStr = pagendStr;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Date getCopyrightYear() {
		return copyrightYear;
	}

	public void setCopyrightYear(Date copyrightYear) {
		this.copyrightYear = copyrightYear;
	}

}
