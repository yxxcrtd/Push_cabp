package cn.digitalpublishing.po;

import java.io.Serializable;

/**
 * 映射对象
 * 
 * @author YangXinXin
 */
@SuppressWarnings("serial")
public class Mapping implements Serializable {
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 目标模板ID
	 */
	private String targetTemplateId;
	
	/**
	 * 用户模板ID
	 */
	private String userTemplateId;
	
	/**
	 * 目标模板节点ID
	 */
	private String targetTemplateNodeId;
	
	/**
	 * 用户模板节点ID
	 */
	private String userTemplateNodeId;
	
	/**
	 * Default Constructor
	 */
	public Mapping() {
		// 
	}

	public String getId() {
		return id;
	}

	public String getTargetTemplateId() {
		return targetTemplateId;
	}

	public String getUserTemplateId() {
		return userTemplateId;
	}

	public String getTargetTemplateNodeId() {
		return targetTemplateNodeId;
	}

	public String getUserTemplateNodeId() {
		return userTemplateNodeId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTargetTemplateId(String targetTemplateId) {
		this.targetTemplateId = targetTemplateId;
	}

	public void setUserTemplateId(String userTemplateId) {
		this.userTemplateId = userTemplateId;
	}

	public void setTargetTemplateNodeId(String targetTemplateNodeId) {
		this.targetTemplateNodeId = targetTemplateNodeId;
	}

	public void setUserTemplateNodeId(String userTemplateNodeId) {
		this.userTemplateNodeId = userTemplateNodeId;
	}

}
