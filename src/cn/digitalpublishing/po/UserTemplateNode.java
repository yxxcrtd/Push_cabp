package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

@SuppressWarnings("serial")
public class UserTemplateNode implements Serializable {
	
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 节点编码
	 */
	private String nodeCode;
	/**
	 * 节点路径
	 */
	private String nodePath;
	/**
	 * 节点名称
	 */
	private String nodeName;
	/**
	 * 节点描述
	 */
	private String Description;
	/**
	 * 用户模板节点路径是否唯一
	 */
	private Boolean isOnly;
	/**
	 * 是否必填
	 */
	private Boolean isNecessary;
	/**
	 * 默认值
	 */
	private String defualtValue;
	/**
	 * 父类对象
	 */
	private UserTemplateNode parent;
	/**
	 * 子类对象集合
	 */
	@JsonIgnore
	private Set<UserTemplateNode> children = new HashSet<UserTemplateNode>();

	/**
	 * 用户模板对象
	 */
	private UserTemplate userTemplate;

	private String userTemplateid;
	private String userTemplateNodeId;

	public String getUserTemplateNodeId() {
		return userTemplateNodeId;
	}

	public void setUserTemplateNodeId(String userTemplateNodeId) {
		this.userTemplateNodeId = userTemplateNodeId;
	}

	public String getUserTemplateid() {
		return userTemplateid;
	}

	public void setUserTemplateid(String userTemplateid) {
		this.userTemplateid = userTemplateid;
	}

	private Set<Mapping> mapping = new HashSet<Mapping>();

	public Set<Mapping> getMapping() {
		return mapping;
	}

	public void setMapping(Set<Mapping> mapping) {
		this.mapping = mapping;
	}

	public UserTemplateNode getParent() {
		return parent;
	}

	public void setParent(UserTemplateNode parent) {
		this.parent = parent;
	}

	public Set<UserTemplateNode> getChildren() {
		return children;
	}

	public void setChildren(Set<UserTemplateNode> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getNodePath() {
		return nodePath;
	}

	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Boolean getIsOnly() {
		return isOnly;
	}

	public void setIsOnly(Boolean isOnly) {
		this.isOnly = isOnly;
	}

	public Boolean getIsNecessary() {
		return isNecessary;
	}

	public void setIsNecessary(Boolean isNecessary) {
		this.isNecessary = isNecessary;
	}

	public String getDefualtValue() {
		return defualtValue;
	}

	public void setDefualtValue(String defualtValue) {
		this.defualtValue = defualtValue;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public UserTemplate getUserTemplate() {
		return userTemplate;
	}

	public void setUserTemplate(UserTemplate userTemplate) {
		this.userTemplate = userTemplate;
	}

}
