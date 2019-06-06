package cn.digitalpublishing.springmvc.controller.pushtask;

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
import cn.digitalpublishing.po.Task;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.PushTaskForm.PushTaskForm;

@Controller
@RequestMapping("/pages/pushTask")
public class PushTaskController extends BaseController {

	@RequestMapping(value="/form/manager")
	public ModelAndView manager(HttpServletRequest request,HttpServletResponse response, PushTaskForm form)throws Exception {
		forwardString="pushtask/PushTaskList";
		Map<String,Object> model = new HashMap<String,Object>();
		List<Task> list =null;
		try{	
			form.setStatusMap(Param.getParam("pushtask.operate.status",true,request.getSession().getAttribute("lang").toString()));
			form.setUrl(request.getRequestURL().toString());
			int count=this.pushTaskService.getpushTaskCount(form.getCondition());
			if(count>0){
				list = this.pushTaskService.getPagingList(form.getCondition()," order by a.createTime asc ",form.getPageCount(),form.getCurpage());
			}
			model.put("list", list);
			form.setCount(this.ftpService.getFtpCount(form.getCondition()));
			form.setCount(count);
			model.put("form", form);
		}catch(Exception e){
            request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			e.printStackTrace();
            forwardString="error";
		}
		return new ModelAndView(forwardString, model);
	}
	

}
