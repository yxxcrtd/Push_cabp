package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 模板映射对象
 * 
 * @author YangXinXin
 */
@SuppressWarnings("serial")
public class RTemplate implements Serializable {

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 用户模板ID
	 */
	private UserTemplate userTemplateId;

	/**
	 * 目标模板ID
	 */
	private UserTemplate targetTemplateId;
	
	/**
	 * 映射表
	 */
	@JsonIgnore
	private Set<UserTemplate> usertemplate;
	/**
	 * Default Constructor
	 */
	public RTemplate() {
		//
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserTemplate getUserTemplateId() {
		return userTemplateId;
	}

	public void setUserTemplateId(UserTemplate userTemplateId) {
		this.userTemplateId = userTemplateId;
	}

	public UserTemplate getTargetTemplateId() {
		return targetTemplateId;
	}

	public void setTargetTemplateId(UserTemplate targetTemplateId) {
		this.targetTemplateId = targetTemplateId;
	}

	public Set<UserTemplate> getUsertemplate() {
		return usertemplate;
	}

	public void setUsertemplate(Set<UserTemplate> usertemplate) {
		this.usertemplate = usertemplate;
	}
	
}
