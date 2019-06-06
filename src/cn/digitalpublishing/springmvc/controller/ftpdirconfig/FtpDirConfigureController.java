package cn.digitalpublishing.springmvc.controller.ftpdirconfig;

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
import cn.digitalpublishing.po.FtpConfigure;
import cn.digitalpublishing.po.FtpDirConfigure;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.ftpdirconfig.FtpDirConfigureForm;


@Controller
@RequestMapping("/pages/ftpdirConfig")
public class FtpDirConfigureController extends BaseController {

	@RequestMapping(value="/form/manager")
	public ModelAndView manager(HttpServletRequest request,HttpServletResponse response, FtpDirConfigureForm form)throws Exception {
		forwardString="ftpdirConfig/FtpdirConfigList";
		List<FtpDirConfigure> list = null;
		try{
			form.setUrl(request.getRequestURL().toString());
			int dataCount = this.ftpDirService.getFtpdirCount(form.getCondition());
			if(dataCount>0){
				list = this.ftpDirService.getFtpdirPagingList(form.getCondition()," order by a.dirName ",form.getPageCount(),form.getCurpage());
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
	
	@RequestMapping(value="/form/edit")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response, FtpDirConfigureForm form)throws Exception {
		String forwardString="ftpdirConfig/FtpdirConfigEdit";
		Map<String,Object> model = new HashMap<String,Object>();
		//FTPmap 存放ftp名字集合 key 是ftp主键id
		Map<String,Object> ftpmap = new HashMap<String,Object>();
		
		try{
			if(request.getParameter("eid")!=null&&!"".equals(request.getParameter("eid").toString())){
				form.setObj(this.ftpDirService.getFtpDir(request.getParameter("eid").toString()));
				form.setId(request.getParameter("eid").toString());
			}
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("flag", form.getFlag());
			List<FtpConfigure> ftpConfigureList=this.ftpService.getftpNameAndId(condition,"order by a.name");
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
	
	@RequestMapping(value="/form/editSubmit")
	public ModelAndView editSubmit(HttpServletRequest request,HttpServletResponse response,FtpDirConfigureForm form) throws Exception {
		HashMap<String,Object> model = new HashMap<String,Object>();
		String forwardString="msg";
		try{
			if(form.getId()!=null && !"".equals(form.getId()) && !"0".equals(form.getId())){
				String[] properties=null;
				this.ftpDirService.updateFtpdir(form.getObj(), form.getId(), properties);
				form.setIsSuccess("true");
				form.setMsg(Lang.getLanguage("msg.info.update.success",request.getSession().getAttribute("lang").toString()));//FTP配置信息修改成功!
			}else{
				this.ftpDirService.addFtpdir(form.getObj());
				form.setIsSuccess("true");
				form.setMsg(Lang.getLanguage("msg.info.add.success",request.getSession().getAttribute("lang").toString()));//FTP配置添加成功!
			}
		}catch(Exception e){
			form.setIsSuccess("false");
			form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString,model);
	}
	
	@RequestMapping(value="/form/delete")
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response,FtpDirConfigureForm form) throws Exception {
		HashMap<String,Object> model = new HashMap<String,Object>(); 
		try{
			this.ftpDirService.deleteFtpdir(request.getParameter("id"));
			form.setMsg(Lang.getLanguage("msg.info.delete.success",request.getSession().getAttribute("lang").toString()));//FTPdir配置信息删除成功!
		}catch(Exception e){
			form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
		}
		model.put("form", form);
		return this.manager(request,response,form);
	}
	
}
