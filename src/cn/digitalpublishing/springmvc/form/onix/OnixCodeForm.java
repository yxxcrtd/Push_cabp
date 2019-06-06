package cn.digitalpublishing.springmvc.form.onix;

import cn.digitalpublishing.resource.OnixCode;
import cn.digitalpublishing.springmvc.form.BaseForm;

/**
 * OnixCodeForm
 * 
 * @author YangXinXin
 */
public class OnixCodeForm extends BaseForm {

	private OnixCode obj = new OnixCode();
	
	// 查询条件1：CodeList
	private String codeList;
	
	// 查询条件2：描述
	private String description;
	
	// 查询条件3：备注
	private String notes;
	
	// 查询条件4：中文翻译
	private String comment;

	public OnixCode getObj() {
		return obj;
	}

	public void setObj(OnixCode obj) {
		this.obj = obj;
	}

	public String getCodeList() {
		return codeList;
	}

	public String getDescription() {
		return description;
	}

	public String getNotes() {
		return notes;
	}

	public String getComment() {
		return comment;
	}

	public void setCodeList(String codeList) {
		this.codeList = codeList;
		this.getCondition().put("codeList", codeList);
	}

	public void setDescription(String description) {
		this.description = description;
		this.getCondition().put("description", description);
	}

	public void setNotes(String notes) {
		this.notes = notes;
		this.getCondition().put("notes", notes);
	}

	public void setComment(String comment) {
		this.comment = comment;
		this.getCondition().put("comment", comment);
	}

}
