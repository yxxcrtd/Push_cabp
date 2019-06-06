package cn.digitalpublishing.springmvc.form.threadcfg.rtenmplate;

import java.util.HashMap;
import java.util.Map;

import cn.digitalpublishing.po.RTemplate;
import cn.digitalpublishing.springmvc.form.BaseForm;



/**
 * RTemplateForm
 * 
 * @author cuixian
 */
public class RTemplateForm extends BaseForm {
	
	private RTemplate obj = new RTemplate();
	
	private Map<String,Object> condition = new HashMap<String,Object>();
	
	// 查询条件1：用户模板名称
	private String userTemplateName;
		
	// 查询条件2：目标模板名称
	private String targetTemplateName;

	private String url;
	
	private String flag;
	
    private String userTemplateId;
	
	private String targetTemplateId;
	
	
	public RTemplate getObj() {
		return obj;
	}

	public void setObj(RTemplate obj) {
		this.obj = obj;
	}
	
	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}

	public String getUserTemplateName() {
		return userTemplateName;
	}

	public void setUserTemplateName(String userTemplateName) {
		this.userTemplateName = userTemplateName;
		this.getCondition().put("userTemplateName", userTemplateName);
	}

	public String getTargetTemplateName() {
		return targetTemplateName;
	}

	public void setTargetTemplateName(String targetTemplateName) {
		this.targetTemplateName = targetTemplateName;
		this.getCondition().put("targetTemplateName", targetTemplateName);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
		this.getCondition().put("flag", flag);
	}

	public String getUserTemplateId() {
		return userTemplateId;
	}

	public void setUserTemplateId(String userTemplateId) {
		this.userTemplateId = userTemplateId;
	}

	public String getTargetTemplateId() {
		return targetTemplateId;
	}

	public void setTargetTemplateId(String targetTemplateId) {
		this.targetTemplateId = targetTemplateId;
	}
	
}
