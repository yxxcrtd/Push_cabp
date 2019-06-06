package cn.digitalpublishing.springmvc.controller.push;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.config.ProcessQueue;
import cn.digitalpublishing.po.Books;
import cn.digitalpublishing.po.Content;
import cn.digitalpublishing.po.FtpConfigure;
import cn.digitalpublishing.po.FtpDirConfigure;
import cn.digitalpublishing.po.Mapping;
import cn.digitalpublishing.po.PushSta;
import cn.digitalpublishing.po.RTemplate;
import cn.digitalpublishing.po.TProduct;
import cn.digitalpublishing.po.TSource;
import cn.digitalpublishing.po.Task;
import cn.digitalpublishing.po.UserTemplate;
import cn.digitalpublishing.po.UserTemplateNode;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.books.BooksForm;
import cn.digitalpublishing.springmvc.form.ftpdirconfig.FtpDirConfigureForm;
import cn.digitalpublishing.springmvc.form.usertemplate.PushForm;
import cn.digitalpublishing.util.FileUtil;

/**
 * 推送
 * 
 * @author YangXinXin
 */
@Controller
@RequestMapping("/pages/push")
public class PushController extends BaseController {

	/**
	 * 推送列表
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/manager")
	public ModelAndView manager(HttpServletRequest request, PushForm form) throws Exception {
		try {
			form.setUrl(request.getRequestURL().toString());
			// 状态为3的是可以推送的
			form.setStatus(3);

			int count = this.pushService.getTSourceCountByStatus(form.getCondition());
			List<TSource> pushList = null;
			if (0 < count) {
				form.setCount(count);
				pushList = this.pushService.getTSourcePagingListByStatus(form.getCondition(), " ORDER BY ts.updateDate", form.getPageCount(), form.getCurpage());
			}
//			ftpcode 
			if(pushList!=null){
				
				for (TSource tSource : pushList) {
					Map<String,Object> conditonher = new HashMap<String, Object>();
					conditonher.put("tsourcecode",tSource.getFtpcode());
					UserTemplate usertemp= this.pushService.getList(form.getCondition(),"");
					if(usertemp!=null){
						Integer type =  usertemp.getType();
						tSource.setType(type);
					}
				}
			}
			
			model.put("pushList", pushList);
			model.put("form", form);
			forwardString = "push/PushList";
		} catch (Exception e) {
			request.setAttribute("message", (e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
			e.printStackTrace();
			forwardString = "error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	/**
	 * 显示推送到的FTP
	 * 
	 * @param code
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/set")
	public ModelAndView set(@RequestParam(value = "code", required = true) String code, @RequestParam(value = "id", required = true) String tsourceId, HttpServletRequest request, PushForm form) throws Exception {
		try {
			List<RTemplate> targetTemplateList = null;
			String utId = "";
			String type = request.getParameter("type");
			// 获取对象
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ftpcode", code);
			List<UserTemplate> userTemplateList = this.userTemplateService.getUserTemplateListByFtpcod(map);
			if (null != userTemplateList && 0 < userTemplateList.size()) {
				UserTemplate ut = userTemplateList.get(0);
				if (null != ut) {
					utId = ut.getId();
					model.put("utId", utId);
					form.setUserTemplateId(utId);
					targetTemplateList = this.rtemplateService.getRTemplateListByUserTemplateId(form.getCondition(), "");
				}
			}
			model.put("targetTemplateList", targetTemplateList);
			
			// Come form ftpdir
			Map<String,Object> condition = new HashMap<String,Object>();
			Map<String,Object> ftpmap = new HashMap<String,Object>();
			condition.put("flag", "1");
			List<FtpConfigure> ftpConfigureList=this.ftpService.getftpNameAndId(condition,"order by a.name");
			for (FtpConfigure ftpConfigure : ftpConfigureList) {
				ftpmap.put(ftpConfigure.getId(), ftpConfigure.getName());
			}
			model.put("type", type);
			model.put("tsourceId", tsourceId);
			model.put("ftpmap", ftpmap);
			model.put("form", form);
			forwardString = "push/PushSet";
		} catch (Exception e) {
			request.setAttribute("message", (e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
			e.printStackTrace();
			forwardString = "error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	/**
	 * 保存
	 * 
	 * @param code
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/save")
	public ModelAndView save(@RequestParam(value = "userTemplateId", required = true) String userTemplateId, 
			@RequestParam(value = "targetTemplateId", required = true) String targetTemplateId, 
			@RequestParam(value = "ftpdirid", required = true) String ftpdirid, 
			@RequestParam(value = "utId", required = true) String utId, 
			@RequestParam(value = "tsourceId", required = true) String tsourceId, 
			@RequestParam(value = "targetTemplateName", required = true) String targetTemplateName, 
			HttpServletRequest request, PushForm form ,BooksForm booksform) throws Exception {
		
		
		Map<String, Object> condition = new HashMap<String, Object>();
		try {
			
			String type = request.getParameter("type");
			
			// 1，根据 用户模板ID和目标模板ID 取 模板节点映射表中已经映射的列表，拿到相应的 目标模板节点ID和用户模板节点ID
			condition.put("targetTemplateId", userTemplateId);
			condition.put("userTemplateId", utId);
			List<Mapping> mappingList = this.mappingService.findMappingListByUserTemplateIdAndTargetTemplateId(condition);
			//将关系封装成map 在比对关系时候用 key 是目标模板id value 是用户模板id
			Map<String,String> map = new HashMap<String,String>();
			for(Mapping mapping:mappingList){
				map.put(mapping.getTargetTemplateNodeId(), mapping.getUserTemplateNodeId());
			}
			// 2，获取FTP对象
			FtpConfigure ftp = this.ftpService.getFtp(targetTemplateId);
			
			// 3，获取FTPDIR对象
			FtpDirConfigure ftpdir = this.ftpDirService.getFtpDir(ftpdirid);
			
			// 4，根据 用户模板ID 获取 模板对象（获取里面的模板路径）
			UserTemplate ut = this.userTemplateService.getUserTemplateAndNodes(utId);
			
			// 6，根据 目标模板ID 获取 目标模板的节点关系（这里的userTemplateId是目标模板ID）
			UserTemplate userTemplate = this.userTemplateService.getUserTemplateAndNodes(userTemplateId);
			Set<UserTemplateNode> UserTemplateNodes = userTemplate.getUserTemplateNodes();
			
			// 7，根据 tsourceId 获取 tproduct 列表
			List<TProduct> tproductList = this.productService.getProductListBySourceId(tsourceId);
			String  isbnName = tproductList.get(0).getCode();
			String path = new StringBuffer().append(ProcessQueue.WEBROOT).append(File.separator).append(ProcessQueue.UPLOADROOT).append(File.separator).append(ProcessQueue.PUSHHOME).append(File.separator).append(isbnName).toString();
			
			Date date = new Date();
			
			for (TProduct product : tproductList) {
				
				// 8，根据本次选择的映射关系列表，获取已经映射的目标节点路径，生成目标XML
				Document document = DocumentHelper.createDocument();
				
				File file = new File(path);
				if(!file.exists()){
					file.mkdirs();
				}
				//String fileName = product.getId()+".xml";
				String fileName = product.getIsbn()+".xml";
				XMLWriter writer = null;
				
				for (UserTemplateNode userTemplateNode: UserTemplateNodes) {
					// 找到根节点
					if (null == userTemplateNode.getParent()) {
						Element nodeElement = document.addElement(userTemplateNode.getNodeName());
						getChildNode(userTemplateNode, nodeElement, product,map);
						break;
					}
				}
				
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("UTF-8");
				writer = new XMLWriter(new FileWriter(path+File.separator+fileName), format);
				writer.write(document);
				writer.close();
				// 创建推送任务
			}
			//移动文件
			String pushPath = new StringBuffer().append(ProcessQueue.WEBROOT).append(File.separator).append(ProcessQueue.UPLOADROOT).append(File.separator).append(ProcessQueue.PUSHHOME).toString();
			//打包
			Map<String,Object> conditionother = new HashMap<String,Object>();
			conditionother.put("tsourceId", tsourceId);
			PushSta pushSta =this.pushStaService.getList(conditionother);
			//pushSta.setDabaoDate(new Date());
			//this.pushStaService.updatePushSta(pushSta, pushSta.getId(), null);
			new FileUtil().compressedFile(path, pushPath);
			String zipName = pushSta.getFileName();
			String fileName = isbnName+".zip";
			Books books =this.booksService.getBooks(booksform.getId());
			for (TProduct product : tproductList) {
				createPushTask(ftp, ftpdir, ut,targetTemplateName, pushPath+File.separator+fileName,fileName,books,product.getBookName(),product.getIsbn(),product.getPublisher(),date,type);
			}
			form.setMsg(Lang.getLanguage("Push.Info.Set.Push.Success", request.getSession().getAttribute("lang").toString()));
			model.put("form", form);
			forwardString = "push/PushSet";
		} catch (Exception e) {
			request.setAttribute("message", (e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
			e.printStackTrace();
			forwardString = "error";
		}
		return new ModelAndView(forwardString, model);
	}

	// 递归得到所有子节点
	private void getChildNode(UserTemplateNode node, Element nodeElement, TProduct product,Map<String,String> map) throws Exception {

		if(null != node.getParent()) {
			nodeElement = nodeElement.addElement(node.getNodeName());
			//如果存在关系
			if(map.get(node.getId())!=null&&!"".equals(map.get(node.getId()))){
				//查询用户模板节点对象
				UserTemplateNode userNode = this.userTemplateNodeService.getbyid(map.get(node.getId()));
				// 获取用户上传的数据列表
				List<Content> contentList = this.contentService.getContentListByProductId(product.getId());
				for (Content c : contentList) {
					if (userNode.getNodePath().equals(c.getNodePath())) {
						// 插入数据
						if(c.getNodeValue()!=null&&!"".equals(c.getNodeValue())){
							nodeElement.setText(c.getNodeValue());
						}
						break;
					}
				}
			}
			
			
		}
 		for (UserTemplateNode tempNode : node.getChildren()) {
			getChildNode(tempNode, nodeElement, product,map);
		}
	}
	
	/**
	 * 创建推送任务
	 * 
	 * @param ftp
	 * @param ftpdir
	 * @param ut
	 * @param path
	 * @throws Exception
	 */
	private void createPushTask(FtpConfigure ftp, FtpDirConfigure ftpdir, UserTemplate ut,String targateTemplateName, String path,String fileName,Books books,String bookName,String isbn,String publisher, Date date,String type) throws Exception {
		Task task = new Task();
		task.setDabaoDate(date);
		task.setBooks(books);
		task.setFileName(fileName);
		task.setPushFilePath(path);
		task.setStatus(1);
		task.setTargateServer(ftp.getName());
		task.setFtpdir(ftpdir.getFtpdir());
		task.setUserTemplateName(ut.getName());
		task.setTargateTemplateName(targateTemplateName);
		task.setIp(ftp.getIp());
		task.setPort(ftp.getPort());
		task.setUsername(ftp.getUsername());
		task.setPassword(ftp.getPassword());
		task.setCreateTime(new Date());
		task.setBookName(bookName);
		task.setIsbn(isbn);
		task.setPublisher(publisher);
		task.setClassify(type);
		this.pushTaskService.save(task);
	}
	
	/**
	 * 获取目标模板列表
	 * 
	 * @param response
	 * @param ftpId
	 * @param form
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/form/getFtpDir")
	public List<FtpDirConfigure> getTargetList(HttpServletResponse response, @RequestParam(value = "ftpId") String ftpId, FtpDirConfigureForm form) throws Exception {
		List<FtpDirConfigure> ftpDirList = null;
		Map<String, Object> condition = new HashMap<String, Object>();
		try {
			if (null != ftpId && !"".equals(ftpId)) {
				// 获取目标模板列表
				condition.put("ftpid", ftpId);
				ftpDirList = (List<FtpDirConfigure>) this.ftpDirService.getFtpDirByFtpid(condition);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		form.setFtpid(ftpId);
		// 返回
		return ftpDirList;
	}

}
