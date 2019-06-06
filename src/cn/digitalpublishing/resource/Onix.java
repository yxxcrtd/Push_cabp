package cn.digitalpublishing.resource;

import java.io.Serializable;

/**
 * Onix 对象
 * 
 * @author YangXinXin
 */
@SuppressWarnings("serial")
public class Onix implements Serializable {
	
	/**
	 * 主键
	 */
	private String onixID;
	
	/**
	 * Onix节点路径
	 */
	private String nodePath;
	
	/**
	 * Onix标签的简写
	 */
	private String shortTag;
	
	/**
	 * Onix节点的基数
	 */
	private String cardinality;
	
	/**
	 * Onix CodeList 文件名
	 */
	private Integer codeList;
    
    /**
     * 是否必选
     */
    private boolean necessary;
    
    /**
     * 是否唯一
     */
    private boolean only;
    
    /**
     * 中文手工注释
     */
    private String comment;
    
    /**
     * 长度限制
     */
    private Integer length;
    
    /**
     * 其他限制（同级节点必须二选一）
     */
//    private boolean limitation;
    
    /**
     * 其他限制（同级节点必须存在一个；在局部节点中是否必选）
     */
//    private String conditions;
    
    /**
     * 示例
     */
    private String example;
	
	/**
	 * Default Constructor
	 */
	public Onix() {
		// 
	}

	public String getOnixID() {
		return onixID;
	}

	public String getNodePath() {
		return nodePath;
	}

	public String getShortTag() {
		return shortTag;
	}

	public String getCardinality() {
		return cardinality;
	}

	public Integer getCodeList() {
		return codeList;
	}

	public boolean getNecessary() {
		return necessary;
	}

	public boolean getOnly() {
		return only;
	}

	public String getComment() {
		return comment;
	}

	public Integer getLength() {
		return length;
	}

//	public boolean getLimitation() {
//		return limitation;
//	}
//
//	public String getConditions() {
//		return conditions;
//	}

	public String getExample() {
		return example;
	}

	public void setOnixID(String onixID) {
		this.onixID = onixID;
	}

	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}

	public void setShortTag(String shortTag) {
		this.shortTag = shortTag;
	}

	public void setCardinality(String cardinality) {
		this.cardinality = cardinality;
	}

	public void setCodeList(Integer codeList) {
		this.codeList = codeList;
	}

	public void setNecessary(boolean necessary) {
		this.necessary = necessary;
	}

	public void setOnly(boolean only) {
		this.only = only;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

//	public void setLimitation(boolean limitation) {
//		this.limitation = limitation;
//	}
//
//	public void setConditions(String conditions) {
//		this.conditions = conditions;
//	}

	public void setExample(String example) {
		this.example = example;
	}

}
