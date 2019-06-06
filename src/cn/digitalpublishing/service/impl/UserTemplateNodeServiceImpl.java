
package cn.digitalpublishing.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.UserTemplate;
import cn.digitalpublishing.po.UserTemplateNode;
import cn.digitalpublishing.service.UserTemplateNodeService;

public class UserTemplateNodeServiceImpl extends BaseServiceImpl implements UserTemplateNodeService {
	
	@Override
	public void analysisTemplateAddNode(UserTemplate obj) throws Exception {
		// TODO Auto-generated method stub
		try{
			//读取xml
			Document document = load(obj.getPath()+obj.getFileName());
			//解析xml 返回节点信息
			getElementList(document.getRootElement(),obj,null,"1001");
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//新增用户模板失败
		}
	}
	
	@Override
	public List<UserTemplateNode> getUserTemplateNodesPagingList(Map<String, Object> condition,String sort, Integer pageCount, Integer page) throws Exception {
		// TODO Auto-generated method stub
		List<UserTemplateNode> list = null;
		try{
			list = this.daoFacade.getUserTemplateNodeDao().getUserTemplateNodesPagingList(condition, sort, pageCount, page);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//获取FTP配置信息分页列表失败�?
		}
		return list;
	}
	
	
	/**
	 * 一次全部加载xml文件
	 * @param url
	 * @return
	 */
	public  Document load(String url) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			document = saxReader.read(url); //读取XML文件,获得document对象
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}
	
	
	/**
	 * 递归得到每个node节点path
	 * @param element
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public  void getElementList(Element element,UserTemplate userTemplate,UserTemplateNode parent,String nodeCode) throws Exception { 
        List elements = element.elements(); 
        String nodeName;
        UserTemplateNode templateNode = null;
		if (elements.size() == 0) { 
            //没有子元素 
            String xpath = element.getPath(); 	
            nodeName = element.getName();
            //是否是根节点
            Boolean isRoot = element.isRootElement();
            templateNode = getUserTemplateNode(xpath,nodeName,userTemplate,parent,nodeCode);
            //保存记录
            this.daoFacade.getUserTemplateNodeDao().insert(templateNode);
        } else { 
        	//存储当前节点
        	  String xpath = element.getPath(); 
        	  nodeName = element.getName();
//            String value = element.getTextTrim(); 
        	  Boolean isRoot = element.isRootElement();
        	  if(!isRoot){
        		  templateNode = getUserTemplateNode(xpath,nodeName,userTemplate,parent,nodeCode); 
        		  //insert
        		  this.daoFacade.getUserTemplateDao().insert(templateNode);
        		  parent = templateNode;
        	  }else{
        		  templateNode = getUserTemplateNode(xpath,nodeName,userTemplate,parent,nodeCode);; 
        		  this.daoFacade.getUserTemplateDao().insert(templateNode);
        		  parent = templateNode;
        	  }
            //有子元素 
        	nodeCode="1000";
            for (Iterator it = elements.iterator(); it.hasNext();) { 
                Element elem = (Element) it.next(); 
              //递归遍历 
                Integer tempCode = Integer.valueOf(nodeCode);
                tempCode++;
                nodeCode=tempCode.toString();
                getElementList(elem,userTemplate,parent,nodeCode); 
            } 
        } 
    } 
	
	/**
	 * 返回封装UsertemplateNode对象
	 */
	public UserTemplateNode getUserTemplateNode(String nodePath,String nodeName,UserTemplate userTemplate,UserTemplateNode parent,String nodeCode){
		UserTemplateNode templateNode = new UserTemplateNode();
		//节点路径
		templateNode.setNodePath(nodePath);
		templateNode.setNodeName(nodeName);
		//usertemplate关系
		templateNode.setUserTemplate(userTemplate);
		if(parent!=null){
			templateNode.setNodeCode(parent.getNodeCode()+nodeCode);
		}else{
			templateNode.setNodeCode(nodeCode);
		}
		//设置父类关系
		templateNode.setParent(parent);
		return templateNode;
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.UserTemplateNodeService#getTargetTemplateNodeCount(java.util.Map)
	 */
	@Override
	public Integer getTemplateNodeCount(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		try {
			num = this.daoFacade.getUserTemplateNodeDao().getTemplateNodeCount(condition);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return num;
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.UserTemplateNodeService#getTargetTemplateNodePagingList(java.util.Map, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<UserTemplateNode> getTemplateNodeListByUser(Map<String, Object> condition, String sort) throws Exception {
		List<UserTemplateNode> list = null;
		try {
			list = this.daoFacade.getUserTemplateNodeDao().getTemplateNodeListByUser(condition, sort);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.UserTemplateNodeService#getTargetTemplateNodePagingList(java.util.Map, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<UserTemplateNode> getTemplateNodeListByUserTemplateId(Map<String, Object> condition, String sort) throws Exception {
		List<UserTemplateNode> list = null;
		try {
			list = this.daoFacade.getUserTemplateNodeDao().getTemplateNodeListByUserTemplateId(condition, sort);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return list;
	}

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.UserTemplateNodeService#getUserTemplateListBySourceId(java.util.Map, java.lang.String)
	 */
	@Override
	public List<UserTemplateNode> getTemplateNodeListByTarget(Map<String,Object> condition, String sort,Integer pageCount, Integer page) throws CcsException {
		List<UserTemplateNode> list = null;
		try {
			list = this.daoFacade.getUserTemplateNodeDao().getTemplateNodeListByTarget(condition, sort, pageCount, page);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return list;
	}

	@Override
	public UserTemplateNode getbyid(String id) throws Exception {
		UserTemplateNode templateNode = null;
		try {
			templateNode = (UserTemplateNode) this.daoFacade.getUserTemplateNodeDao().get(UserTemplateNode.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		return templateNode;
	}
	
}
