package cn.digitalpublishing.facade;

import cn.digitalpublishing.dao.BooksDao;
import cn.digitalpublishing.dao.EmailDao;
import cn.digitalpublishing.dao.FtpConfigureDao;
import cn.digitalpublishing.dao.FtpDirConfigureDao;
import cn.digitalpublishing.dao.MappingDao;
import cn.digitalpublishing.dao.OnixCodeDao;
import cn.digitalpublishing.dao.OnixDao;
import cn.digitalpublishing.dao.ContentDao;
import cn.digitalpublishing.dao.OrderDao;
import cn.digitalpublishing.dao.OrderDetailDao;
import cn.digitalpublishing.dao.ProductDao;
import cn.digitalpublishing.dao.PushDao;
import cn.digitalpublishing.dao.PushStaDao;
import cn.digitalpublishing.dao.PushTaskDao;
import cn.digitalpublishing.dao.TProductDao;
import cn.digitalpublishing.dao.RTemplateDao;
import cn.digitalpublishing.dao.TSourceDao;
import cn.digitalpublishing.dao.UserTemplateDao;
import cn.digitalpublishing.dao.UserTemplateNodeDao;

public class DaoFacade {
	
	private OrderDetailDao orderDetailDao;
	
	private BooksDao booksDao;
	
	private FtpConfigureDao ftpConfigureDao;

	private FtpDirConfigureDao ftpDirConfigureDao;

	private UserTemplateDao userTemplateDao;

	private TSourceDao TSourceDao;

	private MappingDao mappingDao;

	private OnixDao onixDao;

	private OnixCodeDao onixCodeDao;

	private UserTemplateNodeDao userTemplateNodeDao;

	private ContentDao contentDao;

	private TProductDao TProductDao;

	private RTemplateDao rtemplateDao;

	private PushDao pushDao;

	private PushTaskDao pushTaskDao;

	private ProductDao productDao;
	
	private OrderDao orderDao;
	
	private EmailDao emailDao;
	
	private PushStaDao pushStaDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public BooksDao getBooksDao() {
		return booksDao;
	}

	public void setBooksDao(BooksDao booksDao) {
		this.booksDao = booksDao;
	}

	public PushTaskDao getPushTaskDao() {
		return pushTaskDao;
	}

	public void setPushTaskDao(PushTaskDao pushTaskDao) {
		this.pushTaskDao = pushTaskDao;
	}

	public TProductDao getTProductDao() {
		return TProductDao;
	}

	public void setTProductDao(TProductDao tProductDao) {
		TProductDao = tProductDao;
	}

	public FtpConfigureDao getFtpConfigureDao() {
		return ftpConfigureDao;
	}

	public FtpDirConfigureDao getFtpDirConfigureDao() {
		return ftpDirConfigureDao;
	}

	public UserTemplateDao getUserTemplateDao() {
		return userTemplateDao;
	}

	public TSourceDao getTSourceDao() {
		return TSourceDao;
	}

	public MappingDao getMappingDao() {
		return mappingDao;
	}

	public OnixDao getOnixDao() {
		return onixDao;
	}

	public OnixCodeDao getOnixCodeDao() {
		return onixCodeDao;
	}

	public UserTemplateNodeDao getUserTemplateNodeDao() {
		return userTemplateNodeDao;
	}

	public void setFtpConfigureDao(FtpConfigureDao ftpConfigureDao) {
		this.ftpConfigureDao = ftpConfigureDao;
	}

	public void setFtpDirConfigureDao(FtpDirConfigureDao ftpDirConfigureDao) {
		this.ftpDirConfigureDao = ftpDirConfigureDao;
	}

	public void setUserTemplateDao(UserTemplateDao userTemplateDao) {
		this.userTemplateDao = userTemplateDao;
	}

	public void setTSourceDao(TSourceDao tSourceDao) {
		TSourceDao = tSourceDao;
	}

	public void setMappingDao(MappingDao mappingDao) {
		this.mappingDao = mappingDao;
	}

	public void setOnixDao(OnixDao onixDao) {
		this.onixDao = onixDao;
	}

	public void setOnixCodeDao(OnixCodeDao onixCodeDao) {
		this.onixCodeDao = onixCodeDao;
	}

	public void setUserTemplateNodeDao(UserTemplateNodeDao userTemplateNodeDao) {
		this.userTemplateNodeDao = userTemplateNodeDao;
	}

	public ContentDao getContentDao() {
		return contentDao;
	}

	public void setContentDao(ContentDao contentDao) {
		this.contentDao = contentDao;
	}

	public RTemplateDao getRtemplateDao() {
		return rtemplateDao;
	}

	public void setRtemplateDao(RTemplateDao rtemplateDao) {
		this.rtemplateDao = rtemplateDao;
	}

	public PushDao getPushDao() {
		return pushDao;
	}

	public void setPushDao(PushDao pushDao) {
		this.pushDao = pushDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public OrderDetailDao getOrderDetailDao() {
		return orderDetailDao;
	}

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public EmailDao getEmailDao() {
		return emailDao;
	}

	public void setEmailDao(EmailDao emailDao) {
		this.emailDao = emailDao;
	}

	public PushStaDao getPushStaDao() {
		return pushStaDao;
	}

	public void setPushStaDao(PushStaDao pushStaDao) {
		this.pushStaDao = pushStaDao;
	}

	

}
