package cn.digitalpublishing.springmvc.form.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.digitalpublishing.po.OOrder;
import cn.digitalpublishing.springmvc.form.BaseForm;

public class OrderForm extends BaseForm{
	
	OOrder obj =new OOrder();
	
	private String code;
	
	private String name;
	
	private String createdby;
	
	private Integer ostatus;
	private String sstatus;
	
    private CommonsMultipartFile File = null;
	
	private Map<String,Object> condition = new HashMap<String,Object>();

	public OrderForm() {
		//
	}

	public OOrder getObj() {
		return obj;
	}

	public void setObj(OOrder obj) {
		this.obj = obj;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		this.getCondition().put("code", code);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.getCondition().put("name", name);
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
		this.getCondition().put("createdby", createdby);
	}

	public CommonsMultipartFile getFile() {
		return File;
	}

	public void setFile(CommonsMultipartFile file) {
		File = file;
	}

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}

	public Integer getOstatus() {
		return ostatus;
	}

	public void setOstatus(Integer ostatus) {
		this.ostatus = ostatus;
	}

	public String getSstatus() {
		return sstatus;
	}

	public void setSstatus(String sstatus) {
		this.sstatus = sstatus;
		if(sstatus!=null&&!"".equals(sstatus)){
		this.ostatus=Integer.parseInt(sstatus);
		this.getCondition().put("ostatus", ostatus);
		}
	}

	
}
