package cn.digitalpublishing.springmvc.form.email;

import java.util.Date;

import cn.digitalpublishing.po.Email;
import cn.digitalpublishing.springmvc.form.BaseForm;
import cn.digitalpublishing.util.DateFormatUitl;

public class EmailForm extends BaseForm{
	
	private Email obj = new Email();
	
	private String contacts;
	
	private String createdon;
	private String createdto;
	private Date createdond;
	private Date createdtod;
	private Date sendDate;
	private String flag;
	private String arr_select;
	public Email getObj() {
		return obj;
	}
	public void setObj(Email obj) {
		this.obj = obj;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
		this.getCondition().put("contacts", contacts);
	}

	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
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
		System.out.println(createdto);
		if(createdto!=null && createdto!=""){
			this.createdtod = DateFormatUitl.stringToDatetime(createdto+" 23:59:59");//HH:mm:ss
			System.out.println(createdtod);
			this.createdto = createdto;
			this.getCondition().put("createdtod", createdtod);
		}
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
	public String getArr_select() {
		return arr_select;
	}
	public void setArr_select(String arr_select) {
		this.arr_select = arr_select;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
