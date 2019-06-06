package cn.digitalpublishing.springmvc.form.PushTaskForm;

import java.util.Date;
import java.util.HashMap;

import cn.digitalpublishing.po.Task;
import cn.digitalpublishing.springmvc.form.BaseForm;
import cn.digitalpublishing.util.DateFormatUitl;

public class PushTaskForm extends BaseForm {
	
	private Task obj = new Task();

	private String targateServer;
	private String targateTemplateName;
	private String type;
	private String bookName;
	private String createTime;
	private String createdon;
	private String createdto;
	private Date createdond;
	private Date createdtod;
	private String isbn;
	private String classify;
	private String publisher;
	
	//推送状态
	private String status;
	
	private HashMap<String,String> statusMap = new HashMap<String,String>();
	
	public HashMap<String, String> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(HashMap<String, String> statusMap) {
		this.statusMap = statusMap;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.getCondition().put("status",status);
		this.status = status;
	}

	public Task getObj() {
		return obj;
	}
	
	public void setObj(Task obj) {
		this.obj = obj;
	}
	
	public String getTargateServer() {
		return targateServer;
	}
	
	public void setTargateServer(String targateServer) {
		this.getCondition().put("targateServer",targateServer);
		this.targateServer = targateServer;
	}
	
	public String getTargateTemplateName() {
		return targateTemplateName;
	}
	
	public void setTargateTemplateName(String targateTemplateName) {
		this.getCondition().put("targateTemplateName",targateTemplateName);
		this.targateTemplateName = targateTemplateName;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.getCondition().put("type",type);
		this.type = type;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.getCondition().put("bookName",bookName);
		this.bookName = bookName;
	}

	public String getCreateTime() {
		return createTime;
	}


	
	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdond = DateFormatUitl.stringToDate(createdon);
		System.out.println(createdond);
		this.createdon = createdon;
		if(this.createdond!=null){
			this.getCondition().put("createdond", createdond);
		}
	}

	public String getCreatedto() {
		return createdto;
	}

	public void setCreatedto(String createdto) {
		if(createdto!=null && createdto!=""){
		this.createdtod = DateFormatUitl.stringToDatetime(createdto+" 23:59:59");
		System.out.println(createdtod);
		this.createdto = createdto;
		this.getCondition().put("createdtod", createdtod);
		}
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
		this.getCondition().put("createTime",createTime);
	}

	public Date getCreatedond() {
		return createdond;
	}

	public void setCreatedond(Date createdond) {
		this.createdond = createdond;
	}

	public Date getCreatedtod() {
		return createdtod;
	}

	public void setCreatedtod(Date createdtod) {
		this.createdtod = createdtod;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
		this.getCondition().put("isbn",isbn);
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.getCondition().put("classify",classify);
		this.classify = classify;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
		this.getCondition().put("publisher",publisher);
	}

	
	
	

}
