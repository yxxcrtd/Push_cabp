package cn.digitalpublishing.springmvc.form.usertemplate;

import java.util.HashMap;
import java.util.Map;

import cn.digitalpublishing.po.RTemplate;
import cn.digitalpublishing.po.UserTemplate;
import cn.digitalpublishing.springmvc.form.BaseForm;



/**
 * RTemplateForm
 * 
 * @author cuixian
 */
public class RTemplateForm extends BaseForm {
	
	private RTemplate obj = new RTemplate();
	
	private Map<String,Object> condition = new HashMap<String,Object>();

	private String url;
	
	private String flag;
	
	private UserTemplate userTemplateId;
	
	private UserTemplate targetTemplateId;
	
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

	public UserTemplate getTargetTemplateId() {
		return targetTemplateId;
	}

	public void setTargetTemplateId(UserTemplate targetTemplateId) {
		this.targetTemplateId = targetTemplateId;
		this.getCondition().put("targetTemplateId", targetTemplateId);
	}

	public void setUserTemplateId(UserTemplate userTemplateId) {
		this.userTemplateId = userTemplateId;
		this.getCondition().put("userTemplateId", userTemplateId);
	}

	public UserTemplate getUserTemplateId() {
		return userTemplateId;
	}
}
