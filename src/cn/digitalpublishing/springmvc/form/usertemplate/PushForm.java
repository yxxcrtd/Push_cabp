package cn.digitalpublishing.springmvc.form.usertemplate;

import cn.digitalpublishing.po.TSource;
import cn.digitalpublishing.springmvc.form.BaseForm;

/**
 * Push Form
 * 
 * @author YangXinXin
 */
public class PushForm extends BaseForm {

	// 封装的对象
	private TSource obj = new TSource();

	// 查询条件1：文件名
	private String fileName;
	
	// 查询的条件2：状态3（可以推送的）
	private Integer status;

	private String userTemplateId;
	
	public TSource getObj() {
		return obj;
	}

	public void setObj(TSource obj) {
		this.obj = obj;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
		this.getCondition().put("fileName", fileName);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
		this.getCondition().put("status", status);
	}

	public String getUserTemplateId() {
		return userTemplateId;
	}

	public void setUserTemplateId(String userTemplateId) {
		this.userTemplateId = userTemplateId;
		this.getCondition().put("userTemplateId", userTemplateId);
	}

}
