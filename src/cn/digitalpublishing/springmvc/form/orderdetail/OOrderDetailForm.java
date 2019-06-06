package cn.digitalpublishing.springmvc.form.orderdetail;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.digitalpublishing.springmvc.form.BaseForm;
import cn.digitalpublishing.util.DateFormatUitl;
import cn.digitalpublishing.po.OOrderDetail;

public class OOrderDetailForm extends BaseForm {
	
	private OOrderDetail obj = new OOrderDetail();
	
	private String name;
	
	private String publisher;
	
	private String author;
	
	private Date createdon;
	
	private String createStr;
	
	private CommonsMultipartFile orderFile = null;
	
	private Map<String,Object> condition = new HashMap<String,Object>();

	public static Map<String, String> OrderMap = new HashMap<String, String>();
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.getCondition().put("name", name);
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
		this.getCondition().put("publisher", publisher);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
		this.getCondition().put("author", author);
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public OOrderDetail getObj() {
		return obj;
	}

	public void setObj(OOrderDetail obj) {
		this.obj = obj;
	}

	public static Map<String, String> getOrderMap() {
		return OrderMap;
	}

	public static void setOrderMap(Map<String, String> orderMap) {
		OrderMap = orderMap;
	}

	
	public CommonsMultipartFile getOrderFile() {
		return orderFile;
	}

	public void setOrderFile(CommonsMultipartFile orderFile) {
		this.orderFile = orderFile;
	}
	
	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}
	
	public String getCreateStr() {
		return createStr;
	}

	public void setCreateStr(String createStr) {
		this.createdon = DateFormatUitl.stringToDate(createStr);
		System.out.println(createdon);
		this.createStr = createStr;
		if(this.createdon!=null){
			this.getCondition().put("createdon", createdon);
		}
	}
}
