package cn.digitalpublishing.thread;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.TProduct;
import cn.digitalpublishing.po.TSource;
import cn.digitalpublishing.po.UserTemplate;
import cn.digitalpublishing.po.UserTemplateNode;
import cn.digitalpublishing.service.factory.ServiceFactory;
import cn.digitalpublishing.service.factory.impl.ServiceFactoryImpl;

@SuppressWarnings({"rawtypes", "unused"})
public class DownLoadFileProcess extends Thread {

		ServiceFactory serviceFactory = null;
			
		private Counter counter;
			
		public DownLoadFileProcess (Counter counter){
			serviceFactory=(ServiceFactory)new ServiceFactoryImpl();
			this.counter=counter;
		}
			
		@Override
		public void run(){
			this.scan();
			counter.countDown();
		}
			
		private void scan(){
			System.out.println("----DownloadProcess线程开启-----");
			try {
				TSource source = null;
				Map<String, Object> condition = new HashMap<String,Object>();
				condition.put("status", 2);
				List souceList = serviceFactory.getThreadService().getSouceDateList(condition, " order by a.updateDate asc ");
				if(souceList!=null&&souceList.size()>0){
					source=(TSource) souceList.get(0);
					//查询出tproduct关联tsouce中 tsouce的status状态=2的记录集合  （代表图书文件未下载）
					Map<String, Object> map = new HashMap<String,Object>();
					map.put("sourceid", source.getId());
					map.put("sourcestatus", "2");
					List<TProduct> productList = serviceFactory.getThreadService().getTproductList(map, " order by a.updateDate asc ");
					//下载
					//得到服务器上的文件列表
					if(productList!=null&&productList.size()>0){
						serviceFactory.getThreadService().downLoadFile(productList);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			
		}		
	public void analysisSplitNode(String fileName,String splitNode) throws Exception {
		// TODO Auto-generated method stub
		try{
			//读取xml
			Document document = load(splitNode);
			//解析xml 返回节点信息
//			getElementList(document.getRootElement(),obj,null,"1001");
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"usertemlateNode.info.add.error", e);//新增用户模板失败
		}
	}
		
		
	/**
	 * 递归得到每个node节点path
	 * @param element
	 * @throws Exception 
	 */
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
//            templateNode = getUserTemplateNode(xpath,nodeName,userTemplate,parent,nodeCode);
            //保存记录
//            this.daoFacade.getUserTemplateNodeDao().insert(templateNode);
        } else { 
        	//存储当前节点
        	  String xpath = element.getPath(); 
        	  nodeName = element.getName();
//	            String value = element.getTextTrim(); 
        	  Boolean isRoot = element.isRootElement();
        	  if(!isRoot){
//        		  templateNode = getUserTemplateNode(xpath,nodeName,userTemplate,parent,nodeCode); 
        		  //insert
//        		  this.daoFacade.getUserTemplateDao().insert(templateNode);
        		  parent = templateNode;
        	  }else{
//        		  templateNode = getUserTemplateNode(xpath,nodeName,userTemplate,parent,nodeCode);; 
//        		  this.daoFacade.getUserTemplateDao().insert(templateNode);
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
}
