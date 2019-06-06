package cn.digitalpublishing.springmvc.controller.usertemplate;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.model.Param;
import cn.digitalpublishing.config.ProcessQueue;
import cn.digitalpublishing.po.FtpConfigure;
import cn.digitalpublishing.po.Mapping;
import cn.digitalpublishing.po.UserTemplate;
import cn.digitalpublishing.po.UserTemplateNode;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.usertemplate.UserTemplateForm;
import cn.digitalpublishing.thread.AnalysisTemplateListener;
import cn.digitalpublishing.util.FileUtil;

@Controller
@RequestMapping("/pages/userTemplate")
public class UserTemplateController extends BaseController {

	@RequestMapping(value="/form/manager")
	public ModelAndView manager(HttpServletRequest request,HttpServletResponse response, UserTemplateForm form)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		forwardString="userTemplate/userTemplateList";
		try{//模板类型  0 图书  1 期刊
			form.setTemplateTypeMap(Param.getParam("content.type.status",true,request.getSession().getAttribute("lang").toString()));
			//模板分类
			form.setTemplateCategoryMap(Param.getParam("page.usertemplate.category",true,request.getSession().getAttribute("lang").toString()));	
			
			form.setUrl(request.getRequestURL().toString());
			//获取数据总条数
			int dataCount = this.userTemplateService.getDataCount(form.getCondition());
			List<UserTemplate> list =null;
			if(dataCount>0){
				list = this.userTemplateService.getUserTemplatePagingList(form.getCondition()," order by a.createTime ",form.getPageCount(),form.getCurpage());
			}
			form.setCount(dataCount);
			model.put("list", list);
			model.put("form", form);
		}catch(Exception e){
            request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			e.printStackTrace();
            forwardString="error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	@RequestMapping(value="/form/add")
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response, UserTemplateForm form)throws Exception {
		String forwardString="userTemplate/userTemplateAdd";
		UserTemplate obj = null;
		Map<String,Object> model = new HashMap<String,Object>();
		//模板类型  0 图书  1 期刊
		form.setTemplateTypeMap(Param.getParam("content.type.status",true,request.getSession().getAttribute("lang").toString()));
		//模板分类
		form.setTemplateCategoryMap(Param.getParam("page.usertemplate.category",true,request.getSession().getAttribute("lang").toString()));
	
		try{
			if(request.getParameter("eid")!=null&&!"".equals(request.getParameter("eid").toString())){
				obj = this.userTemplateService.getUserTemplate(request.getParameter("eid").toString());
				form.setId(request.getParameter("eid").toString());
			}
			if(obj==null){
				//默认选中用户模板
				obj = new UserTemplate();
				obj.setFlag(0);
			}
			form.setObj(obj);
			//查询没有绑定用户解析模板的ftp集合
			Map<String, String> ftpmap = new HashMap<String,String>();
			List<FtpConfigure> ftpConfigureList=this.ftpService.getFtpUnbindTemplate("order by a.name");
			for (FtpConfigure ftpConfigure : ftpConfigureList) {
				ftpmap.put(ftpConfigure.getId(), ftpConfigure.getName());
			}
			model.put("ftpmap", ftpmap);
			model.put("form", form);
		}catch(Exception e){
            request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			forwardString="error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	
	@RequestMapping(value="/form/edit")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response, UserTemplateForm form)throws Exception {
		String forwardString="userTemplate/userTemplateEdit";
		UserTemplate obj = null;
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			if(request.getParameter("eid")!=null&&!"".equals(request.getParameter("eid").toString())){
				obj = this.userTemplateService.getUserTemplate(request.getParameter("eid").toString());
				form.setId(request.getParameter("eid").toString());
			}
			form.setObj(obj);
			//查询没有绑定用户解析模板的ftp集合
			model.put("form", form);
		}catch(Exception e){
            request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			forwardString="error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	
	@RequestMapping(value="/form/config")
	public ModelAndView conf(HttpServletRequest request,HttpServletResponse response, UserTemplateForm form)throws Exception {
		String forwardString="userTemplate/userTemplateConfig";
		UserTemplate obj = null;
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			if(request.getParameter("eid")!=null&&!"".equals(request.getParameter("eid").toString())){
				obj = this.userTemplateService.getUserTemplate(request.getParameter("eid").toString());
				form.setId(request.getParameter("eid").toString());
			}
			form.setObj(obj);
			//查询没有绑定用户解析模板的ftp集合
			model.put("form", form);
		}catch(Exception e){
			request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			forwardString="error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	@RequestMapping(value="/form/editSubmit")
	public ModelAndView editSubmit(HttpServletRequest request,HttpServletResponse response,UserTemplateForm form) throws Exception {
		forwardString="msg";
		UserTemplate userTemplate = form.getObj();
		FtpConfigure ftpConfigure = null;
		try{
			
			if(form.getId()!=null && !"".equals(form.getId()) && !"0".equals(form.getId())){
				String[] properties=null;
				
				if(userTemplate.getFlag()==0){
					userTemplate.setRootNode(userTemplate.getSplitNode().split("/")[1]);
				}
				this.userTemplateService.updateUserTemplate(userTemplate, form.getId(), properties);
				form.setIsSuccess("true");
				form.setMsg(Lang.getLanguage("msg.info.update.success",request.getSession().getAttribute("lang").toString()));//模板修改成功!
			}else{
				Map<String, Object> cond = new HashMap<>();
				if(userTemplate.getName()!=null){
					cond.put("name", userTemplate.getName());
				}
				List<UserTemplate> UserTemplateList = this.userTemplateService.getUserTemplateListByFlag(cond, "");
				if(0 < UserTemplateList.size()){
					form.setMsg(Lang.getLanguage("Pages.Template.info.error", request.getSession().getAttribute("lang").toString()));
					forwardString = "msg";
				}else{
				//用户模板文件存储路径
				String filePath=ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.USERTEMPLATEHOME+File.separator;
				//文件原名
				String OriginalFilename = form.getUpFile().getOriginalFilename();
				//文件名称 通过系统时间得到
				String fileName = FileUtil.getFileName(OriginalFilename);
				//获取上传的文件流对象 上传文件
				FileUtil.writeFile(filePath,fileName, form.getUpFile().getFileItem().get());
				//取得页面选择绑定的对象
				String ftpid = form.getFtpid();
				//用户模板的时候 才进行ftp绑定关联
				if(userTemplate.getFlag()==0){
					ftpConfigure = this.ftpService.getFtp(ftpid);
				}
				//封装UserTemplate 对象属性
				userTemplate.setFtpConfigure(ftpConfigure);
				userTemplate.setOriginalName(OriginalFilename);
				userTemplate.setFileName(fileName);
				userTemplate.setPath(filePath);
				userTemplate.setCreateTime(new Date());
				//设置根节点
				String rootNode =null;
				if(userTemplate.getFlag()==0){
					rootNode = userTemplate.getSplitNode().split("/")[1];
				}
				userTemplate.setRootNode(rootNode);
				this.userTemplateService.addUserTemplate(userTemplate);
				
				form.setIsSuccess("true");
				form.setMsg(Lang.getLanguage("msg.info.add.success",request.getSession().getAttribute("lang").toString()));//模板添加成功!
				//添加模板成功后启动解析模板线程
				AnalysisTemplateListener templateThread = new AnalysisTemplateListener();
				templateThread.executeScan(userTemplate);
			}
			}
		}catch(Exception e){
			e.printStackTrace();
			form.setIsSuccess("false");
			form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString,model);
	}
	
	
	@RequestMapping(value="/form/create")
	public void createXml(HttpServletRequest request,HttpServletResponse response,UserTemplateForm form) throws Exception {
		forwardString="msg";
		try{
//			UserTemplate userTemplate = this.userTemplateService.getUserTemplateAndNodes(form.getId());
//			List<UserTemplateNode> userTemplateNodes = this.userTemplateNodeService.;
//			Set<UserTemplateNode> UserTemplateNodes=userTemplate.getUserTemplateNodes();
			//yul test
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("parentid", form.getId());
			List<UserTemplateNode> listtest=this.userTemplateNodeService.getUserTemplateNodesPagingList(condition, "order by a.nodeCode asc", form.getPageCount(),form.getCurpage());
			System.out.println(listtest);
			//end
//			System.out.println(list);
			form.setIsSuccess("true");
			form.setMsg(Lang.getLanguage("msg.info.add.success",request.getSession().getAttribute("lang").toString()));
		}catch(Exception e){
			e.printStackTrace();
			form.setIsSuccess("false");
			form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
		}
	}
	
	@RequestMapping(value="/form/delete")
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response,UserTemplateForm form) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>(); 
		try {
			String id = request.getParameter("id");
			String f = request.getParameter("f");
			// 先判断该模板是否已经映射
			if ("0".equals(f)) {
				map.put("userTemplateId", id);
			} else {
				map.put("targetTemplateId", id);
			}
			List<Mapping> list = this.mappingService.findByTargetTemplateId(map);
			if (0 < list.size()) {
				// 不能删除已关联的模版！
				form.setMsg(Lang.getLanguage("Pages.Template.Delete.Error",request.getSession().getAttribute("lang").toString()));
			} else {
				this.userTemplateService.deleteUserTemplate(id);
				form.setMsg(Lang.getLanguage("msg.info.delete.success",request.getSession().getAttribute("lang").toString()));
			}
		}catch(Exception e){
			form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
		}
		model.put("form", form);
		return this.manager(request,response,form);
	}
	
//	//生成xml
//	public static void createXml(String path){
//		XMLWriter writer = null;
//		List list =null;
//		try{
//			Document document = DocumentHelper.createDocument(); 
//			Element booksElement = document.addElement((String) list.get(0));
//			for(int i = 0;i<list.size();i++){
//				booksElement=booksElement.addElement((String) list.get(i));
//			}
//			Element booksElement = document.addElement("student");
//			Element books1Element = booksElement.addElement("call");
//			Element book2Element = books1Element.addElement("name"); 
//			book2Element.setText("zhangsan");
//			booksElement.addComment("first XML");
//			OutputFormat format = OutputFormat.createPrettyPrint(); 
//			format.setEncoding("UTF-8"); 
//			writer = new XMLWriter(new FileWriter(path), format);  
//			writer.write(document); 
//		} catch(Exception e) {  
//			
//		}finally{
//			if(writer!=null){
//				try {
//					writer.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}  
//			}
//		}
//		
//	}
}
