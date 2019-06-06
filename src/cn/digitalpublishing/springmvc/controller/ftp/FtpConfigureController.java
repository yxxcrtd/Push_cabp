package cn.digitalpublishing.springmvc.controller.ftp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.FtpConfigure;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.ftp.FtpConfigureForm;

@Controller
@RequestMapping("/pages/ftpConfig")
public class FtpConfigureController extends BaseController {

	@RequestMapping(value="/form/manager")
	public ModelAndView manager(HttpServletRequest request,HttpServletResponse response, FtpConfigureForm form)throws Exception {
		forwardString="ftpConfig/FtpConfigList";
		Map<String,Object> model = new HashMap<String,Object>();
		try{	
			form.setUrl(request.getRequestURL().toString());
			form.setCount(this.ftpService.getFtpCount(form.getCondition()));
			List<FtpConfigure> list = this.ftpService.getFtpPagingList(form.getCondition()," order by a.code ",form.getPageCount(),form.getCurpage());
			model.put("list", list);
			model.put("form", form);
		}catch(Exception e){
            request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			e.printStackTrace();
            forwardString="error";
		}
		return new ModelAndView(forwardString, model);
	}
	@RequestMapping(value="/form/FtpConfigEdit")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response, FtpConfigureForm form)throws Exception {
		String forwardString="ftpConfig/FtpConfigEdit";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			if(request.getParameter("eid")!=null&&!"".equals(request.getParameter("eid").toString())){
				form.setObj(this.ftpService.getFtp(request.getParameter("eid").toString()));
				form.setId(request.getParameter("eid").toString());
			}
			model.put("form", form);
		}catch(Exception e){
            request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			forwardString="error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	@RequestMapping(value="/form/editSubmit")
	public ModelAndView editSubmit(HttpServletRequest request,HttpServletResponse response,FtpConfigureForm form) throws Exception {
		HashMap<String,Object> model = new HashMap<String,Object>();
		String forwardString="msg";
		try{
			if(form.getId()!=null && !"".equals(form.getId()) && !"0".equals(form.getId())){
				String[] properties=null;
				this.ftpService.updateFtp(form.getObj(), form.getId(), properties);
				form.setIsSuccess("true");
				form.setMsg(Lang.getLanguage("msg.info.update.success",request.getSession().getAttribute("lang").toString()));//FTP配置信息修改成功!
			}else{
				this.ftpService.addFtp(form.getObj());
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
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response,FtpConfigureForm form) throws Exception {
		try{
			this.ftpService.deleteFtp(request.getParameter("id"));
			form.setMsg(Lang.getLanguage("msg.info.delete.success",request.getSession().getAttribute("lang").toString()));//FTP配置信息删除成功!
		}catch(Exception e){
			form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
		}
		model.put("form", form);
		return this.manager(request,response,form);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/form/test" ,headers="Accept=application/json" )
	@ResponseBody
	public List test(HttpServletRequest request,HttpServletResponse response,FtpConfigureForm form) throws Exception {
		List l = null;
		try{
			System.out.println("======================");
			l = new ArrayList();
			l.add("1");
		}catch(Exception e){
			e.printStackTrace();
		}
		return l;
	}
	
}
