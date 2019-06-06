package cn.digitalpublishing.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.config.ProcessQueue;
import cn.digitalpublishing.po.Content;
import cn.digitalpublishing.po.TProduct;
import cn.digitalpublishing.po.TSource;
import cn.digitalpublishing.po.Task;
import cn.digitalpublishing.po.UserTemplate;
import cn.digitalpublishing.service.ThreadService;
import cn.digitalpublishing.util.FileUtil;
import cn.digitalpublishing.util.FtpUploadTool;
import cn.digitalpublishing.util.XmlSplitHelper;

public class ThreadServiceImpl extends BaseServiceImpl implements ThreadService {
	/**
	 * 得到TSource数据
	 */
	@Override
	public List<TSource> getSouceDateList(Map<String, Object> condition,String sort)throws Exception{
		// TODO Auto-generated method stub
		List<TSource> list = null;
		try{
			list = this.daoFacade.getTSourceDao().getList(condition, sort);
		}catch(Exception e){
			e.printStackTrace();
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//获取FTP配置信息分页列表失败�?
		}
		return list;
	}

	@Override
	public List<UserTemplate> getUserTemplate(Map<String, Object> conditionftpCode) throws Exception{
		// TODO Auto-generated method stub
		List<UserTemplate> list = null;
		try{
			list = this.daoFacade.getUserTemplateDao().getList(conditionftpCode);
		}catch(Exception e){
			e.printStackTrace();
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//获取FTP配置信息分页列表失败�?
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void splitMetadata(TSource tSource, UserTemplate template) throws CcsException {
		// TODO Auto-generated method stub
		try{
			//取得模板解析节点

			String splitNode = template.getSplitNode();
			//源数据保存路径
			String sourcePath = tSource.getFilePath();
			String dataName = tSource.getFileName();
			String checkDLD = template.getCheckTLD();
			if(tSource.getFileName().toLowerCase().lastIndexOf(".xls")>-1||tSource.getFileName().toLowerCase().lastIndexOf(".xlsx")>-1){
				//Excel
//				this.serviceFactory.getAshgateService().ImportExcel(obj,webRoot, Param.getParam("TPIService.info.directory.config").get("templateCode"));
			}else if(tSource.getFileName().toLowerCase().lastIndexOf(".xml")>-1){
				//拆分成多条XML信息
					if(template.getType()==0){//图书
						XmlSplitHelper helper = new XmlSplitHelper();
						helper.setResults(new HashMap<String,String>());
						List<Element> list = new ArrayList<Element>();
						String[] arrCommens = template.getCommonNode()==null?null:template.getCommonNode().split(";");
						System.out.println(checkDLD);
						if(arrCommens!=null){
							for(int i=0;i<arrCommens.length;i++){
								Element pub = helper.getPubElement(sourcePath+dataName,arrCommens[i],checkDLD);
								list.add(pub);
							}
						}
						//保存路径
						String filename = tSource.getFileName().replace(".xml", "");
						String storePath =ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.SOURCEDATAPATH+File.separator+filename+File.separator;
						System.out.println(sourcePath+dataName);
						//没有此路径 默认创建
						FileUtil.createPath(storePath);
						//生成的新xml通过isbn编号命名
						helper.split(sourcePath+dataName,"",storePath,checkDLD,splitNode,template.getBookCodeNode(),list,template.getRootNode());
						Map<String,String> result = helper.getResults();
						Iterator it = result.entrySet().iterator();
						int count = 0;
						while (it.hasNext()){   
							Map.Entry pairs = (Map.Entry)it.next(); 
							TProduct metadata = new TProduct();
							metadata.setPath(pairs.getValue().toString());
							String code = pairs.getValue().toString();
							code = code.substring(code.lastIndexOf("/")+1, code.indexOf(".xml"));
							metadata.setCode(code);
							metadata.setOperStatus(1);
							metadata.setStatus(1);
							metadata.setUpdateDate(new Date());
							metadata.setSource(tSource);
							this.daoFacade.getTProductDao().insert(metadata);
							//解析单个数据xml数据 封装push对象
							Document document = load(metadata.getPath());
							//读取xml
							List<Content> pushList = new ArrayList<Content>(); 
							pushList = getElementList(document.getRootElement(), pushList,metadata);
							for (Content p : pushList) {
								this.daoFacade.getContentDao().insert(p);
								if(p.getNodePath().equals(template.getIsbn()))
								{
									metadata.setIsbn(p.getNodeValue());
								}
								if(p.getNodePath().equals(template.getBookName()))
								{
									metadata.setBookName(p.getNodeValue());
								}
								if(p.getNodePath().equals(template.getPublisher()))
								{
									metadata.setPublisher(p.getNodeValue());
								}
							}
							count++;
						}
						if(count<=0){
							throw new Exception();
						}
					}else if(template.getType()==1){
						//期刊
					}
					tSource.setUpdateDate(new Date());
					tSource.setStatus(2);
					this.daoFacade.getTSourceDao().update(tSource,TSource.class.getName(),tSource.getId(), null);
				}
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//获取FTP配置信息分页列表失败�?
			//修改更新时间
		}
	}
	
	public  Document load(String url) throws Exception {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			document = saxReader.read(url); //读取XML文件,获得document对象
		} catch (Exception e) {
			e.printStackTrace();
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//加载xml异常
		}
		return document;
	}
	
	
	/**
	 * 递归得到每个node节点path
	 * @param element
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public  List<Content> getElementList(Element element,List<Content> list,TProduct metadata) throws Exception { 
        List elements = element.elements(); 
        String nodeName;
        Content push = null;
		if (elements.size() == 0) { 
            //没有子元素 
            String xpath = element.getPath(); 	
            nodeName = element.getName();
            //是否是根节点
            String value = element.getTextTrim(); 
            push = getPush(xpath,value,metadata);
      	  	list.add(push);
            //保存记录
        } else { 
        	//存储当前节点
        	  String xpath = element.getPath(); 
        	  String value = element.getTextTrim(); 
        	  push = getPush(xpath,value,metadata);
        	  list.add(push);
        	  }
            //有子元素 
            for (Iterator it = elements.iterator(); it.hasNext();) { 
                Element elem = (Element) it.next(); 
                getElementList(elem,list,metadata); 
            } 
            
            return list;
        } 
	
	public Content getPush(String xpath,String nodeValue,TProduct metadata){
		Content push = new Content();
		push.setNodePath(xpath);
		push.setNodeValue(nodeValue);
		push.settProduct(metadata);
		return push;
	}

	@Override
	public List <TProduct> getTproductList(Map<String, Object> map,String sort) throws CcsException {
		// TODO Auto-generated method stub
		List<TProduct> list = null;
		try{
			list = this.daoFacade.getTProductDao().getTproductList(map,sort);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//新增用户模板失败
		}
		return list;
	}

	@Override
	public void downLoadFile(List<TProduct> list) throws CcsException {
		// TODO Auto-generated method stub
		try{
			TSource source = list.get(0).getSource();
			String ip = source.getIp();
			String port = source.getPort();
			String username = source.getUsername();
			String password = source.getPassword();
			int dataCount = list.size();
			Map<String,String> fileNames = new HashMap<String, String>();
			for(TProduct Product :list){
				fileNames.put(Product.getCode(), Product.getCode());
			}
			//文件保存路径
			String filePath = new StringBuffer().append(ProcessQueue.WEBROOT).append(File.separator).append(ProcessQueue.UPLOADROOT).append(File.separator).append(ProcessQueue.PUSHHOME).append(File.separator).append(source.getFileName().replace(".xml", "")).toString();
			//执行下载
			FtpUploadTool tool = new FtpUploadTool(ip,port,username,password);
			int tempcount = tool.downloadFileAndReturnCount(source.getFtpFileDir(),filePath,fileNames);
			if(tempcount>=dataCount){
				//更新状态
				source.setStatus(3);
			}else{
				source.setStatus(2);
			}
			source.setUpdateDate(new Date());
		//跟新tsource
			this.daoFacade.getTSourceDao().update(source, TSource.class.getName(), source.getId(), null);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//新增用户模板失败
		}
	}

	@Override
	public List<Task> getTaskPagingList(Map<String, Object> condition,String sort, Integer pageCount, Integer page) throws CcsException {
		// TODO Auto-generated method stub
		List<Task> list = null;
		try{
			list = this.daoFacade.getPushTaskDao().getPagingList(condition, sort, pageCount, page);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);//新增用户模板失败
		}
		return list;
	}

	@Override
	public void sendFile(Task task) throws CcsException {
		// TODO Auto-generated method stub
		try {
			FtpUploadTool tool = new FtpUploadTool(task.getIp(),task.getPort(),task.getUsername(),task.getPassword());
			File file = new File(task.getPushFilePath());
			tool.uploadFile(file, task.getFtpdir(), "UFT-8", task.getFileName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);

		}
	}

	@Override
	public void update(Task task, String id, String[] properties)
			throws CcsException {
		// TODO Auto-generated method stub
		try{
			this.daoFacade.getFtpDirConfigureDao().update(task, Task.class.getName(),id, properties);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);
		}
	}
	
	@Override
	public void updateTSource(TSource tSource, String id, String[] properties)
			throws CcsException {
		// TODO Auto-generated method stub
		try{
			this.daoFacade.getFtpDirConfigureDao().update(tSource, TSource.class.getName(),id, properties);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);
		}
	}
	
}
