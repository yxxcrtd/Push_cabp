package cn.digitalpublishing.resource;

import java.io.Serializable;

/**
 * OnixCode 对象
 * 
 * @author YangXinXin
 */
@SuppressWarnings("serial")
public class OnixCode implements Serializable {
	
	/**
	 * 主键
	 */
	private String onixCodeID;
	
	/**
	 * CodeList
	 */
	private Integer codeList;
	
	/**
	 * Code值
	 */
	private String codeValue;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 备注
	 */
	private String notes;
	
	/**
	 * 中文手工翻译
	 */
	private String comment;
	
	/**
	 * Default Constructor
	 */
	public OnixCode() {
		// 
	}

	public String getOnixCodeID() {
		return onixCodeID;
	}

	public Integer getCodeList() {
		return codeList;
	}

	public String getCodeValue() {
		return codeValue;
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

	public void setOnixCodeID(String onixCodeID) {
		this.onixCodeID = onixCodeID;
	}

	public void setCodeList(Integer codeList) {
		this.codeList = codeList;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
