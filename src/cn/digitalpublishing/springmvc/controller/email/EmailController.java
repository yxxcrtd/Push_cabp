package cn.digitalpublishing.springmvc.controller.email;

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
import cn.digitalpublishing.po.Email;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.email.EmailForm;

@Controller
@RequestMapping("/pages/email")
public class EmailController extends BaseController {

	@RequestMapping(value="/form/manager")
	public ModelAndView manager(HttpServletRequest request,HttpServletResponse response, EmailForm form)throws Exception {
		forwardString="email/EmailList";
		Map<String,Object> model = new HashMap<String,Object>();
		try{	
			form.setUrl(request.getRequestURL().toString());
			form.setCount(this.emailService.getEmailCount(form.getCondition()));
			List<Email> list = this.emailService.getEmailPagingList(form.getCondition()," order by a.contacts ",form.getPageCount(),form.getCurpage());
			model.put("list", list);
			model.put("form", form);
		}catch(Exception e){
            request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
            forwardString="error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	
	
	
	 @RequestMapping(value="/form/delete")
		public ModelAndView delete(HttpServletRequest request,HttpServletResponse response,EmailForm form) throws Exception {
			try{
				this.emailService.deleteEmail(request.getParameter("id"));
				form.setMsg(Lang.getLanguage("msg.info.delete.success",request.getSession().getAttribute("lang").toString()));//FTP配置信息删除成功!
			}catch(Exception e){
				form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			}
			model.put("form", form);
			return this.manager(request,response,form);
		}
	 
	@RequestMapping(value="/form/userDel")
	public ModelAndView userDel(HttpServletRequest request,HttpServletResponse response,EmailForm form) throws Exception {
	 String usersId = form.getArr_select();
	 String [] usersArray = usersId.split(","); 
     System.out.println(usersId);
	 System.out.println(usersArray.length);
			for (int i = 0; i < usersArray.length; i++) {
				System.out.println(usersArray[0]);
				try{
					this.emailService.deleteEmail(usersArray[i]);
					form.setMsg(Lang.getLanguage("msg.info.delete.success",request.getSession().getAttribute("lang").toString()));//FTP配置信息删除成功!
				}catch(Exception e){
					form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
				}
			}
			model.put("form", form);
			return this.manager(request,response,form);
	}
	 
	 
	 
	 
	 
	 
	
		
		
	
}

