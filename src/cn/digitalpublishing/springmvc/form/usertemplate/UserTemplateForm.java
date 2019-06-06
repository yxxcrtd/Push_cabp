package cn.digitalpublishing.springmvc.form.usertemplate;

import java.util.HashMap;
import java.util.Map;

import cn.digitalpublishing.po.UserTemplate;
import cn.digitalpublishing.springmvc.form.BaseForm;

public class UserTemplateForm extends BaseForm {
	private UserTemplate obj = new UserTemplate();

	private String name;

	private String originalName;

	private String flag;

	private String type;

	private String f;

	private Map<String, Object> condition = new HashMap<String, Object>();

	private String ftpid;

	private Map<String, String> templateTypeMap = new HashMap<String, String>();

	private String sourceId;

	private String targetId;

	private Map<String, String> templateCategoryMap = new HashMap<String, String>();

	public Map<String, String> getTemplateCategoryMap() {
		return templateCategoryMap;
	}

	public void setTemplateCategoryMap(Map<String, String> templateCategoryMap) {
		this.templateCategoryMap = templateCategoryMap;
	}

	public Map<String, String> getTemplateTypeMap() {
		return templateTypeMap;
	}

	public void setTemplateTypeMap(Map<String, String> templateTypeMap) {
		this.templateTypeMap = templateTypeMap;
	}

	public String getFtpid() {
		return ftpid;
	}

	public void setFtpid(String ftpid) {
		this.ftpid = ftpid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
		this.getCondition().put("flag", flag);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.getCondition().put("name", name);
		this.name = name;
	}

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}

	public UserTemplate getObj() {
		return obj;
	}

	public void setObj(UserTemplate obj) {
		this.obj = obj;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
		this.getCondition().put("originalName", originalName);
	}

	public String getSourceId() {
		return sourceId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
		this.getCondition().put("sourceId", sourceId);
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
		this.getCondition().put("targetId", targetId);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.getCondition().put("type", type);
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

}
