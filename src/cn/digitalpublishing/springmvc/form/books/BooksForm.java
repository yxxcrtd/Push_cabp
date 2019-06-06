package cn.digitalpublishing.springmvc.form.books;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.digitalpublishing.po.Books;
import cn.digitalpublishing.po.FtpConfigure;
import cn.digitalpublishing.po.FtpDirConfigure;
import cn.digitalpublishing.po.UserTemplate;
import cn.digitalpublishing.springmvc.form.BaseForm;

public class BooksForm extends BaseForm{
	
	private Books obj = new Books();
	
	private String orderNo;
	
	private String title;
	private String category;
	private String isbn;
	private String flag;
	private String arr_select;
	private String emailContent;
	private String cunZai;
	private String ftpId;
	private String ftpdirId;
	private HashMap<String,String> enailheadMap = new HashMap<String,String>();
	
	public HashMap<String, String> getEnailheadMap() {
		return enailheadMap;
	}

	public void setEnailheadMap(HashMap<String, String> enailheadMap) {
		this.enailheadMap = enailheadMap;
	}

	private CommonsMultipartFile txtFile = null;
	private CommonsMultipartFile upLoadPhoto = null;
	
	private Map<String,Object> condition = new HashMap<String,Object>();

	public Books getObj() {
		return obj;
	}

	public void setObj(Books obj) {
		this.obj = obj;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
		this.getCondition().put("orderNo", orderNo);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.getCondition().put("title", title);
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
		this.getCondition().put("isbn", isbn);
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public CommonsMultipartFile getTxtFile() {
		return txtFile;
	}

	public void setTxtFile(CommonsMultipartFile txtFile) {
		this.txtFile = txtFile;
	}

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}

	public CommonsMultipartFile getUpLoadPhoto() {
		return upLoadPhoto;
	}

	public void setUpLoadPhoto(CommonsMultipartFile upLoadPhoto) {
		this.upLoadPhoto = upLoadPhoto;
	}

	public String getArr_select() {
		return arr_select;
	}

	public void setArr_select(String arr_select) {
		this.arr_select = arr_select;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public String getCunZai() {
		return cunZai;
	}

	public void setCunZai(String cunZai) {
		this.cunZai = cunZai;
		this.getCondition().put("cunZai", cunZai);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
		this.getCondition().put("category", category);
	}

	public String getFtpId() {
		return ftpId;
	}

	public void setFtpId(String ftpId) {
		this.ftpId = ftpId;
	}

	public String getFtpdirId() {
		return ftpdirId;
	}

	public void setFtpdirId(String ftpdirId) {
		this.ftpdirId = ftpdirId;
	}
}
