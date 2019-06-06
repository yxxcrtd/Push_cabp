package cn.digitalpublishing.springmvc.form.onix;

import cn.digitalpublishing.resource.Onix;
import cn.digitalpublishing.springmvc.form.BaseForm;

/**
 * OnixForm
 * 
 * @author YangXinXin
 */
public class OnixForm extends BaseForm {

	private Onix obj = new Onix();

	// 查询条件1：节点路径
	private String nodePath;
	
	// 查询条件2：注释
	private String comment;

	public Onix getObj() {
		return obj;
	}

	public void setObj(Onix obj) {
		this.obj = obj;
	}

	public String getNodePath() {
		return nodePath;
	}

	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
		this.getCondition().put("nodePath", nodePath);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
		this.getCondition().put("comment", comment);
	}

}
