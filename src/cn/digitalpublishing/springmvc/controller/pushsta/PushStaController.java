package cn.digitalpublishing.springmvc.controller.pushsta;

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
import cn.digitalpublishing.po.PushSta;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.pushStaForm.PushStaForm;

@Controller
@RequestMapping("/pages/pushsta")
public class PushStaController extends BaseController {

	
	
	@RequestMapping(value="/form/states")
	public ModelAndView manager(HttpServletRequest request,HttpServletResponse response, PushStaForm form)throws Exception {
		forwardString="pushtask/ PushEditList";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			form.setUrl(request.getRequestURL().toString());
			Map<String,Object> conditon = form.getCondition();
			if(form.getFileName()!=null&&!"".equals(form.getFileName())){
				conditon.put("fileName", "%"+form.getFileName()+"%");
			}
			
			if(form.getFtpFileDir()!=null&&!"".equals(form.getFtpFileDir())){
				conditon.put("ftpFileDir", "%"+form.getFtpFileDir()+"%");
			}			
			conditon.put("tempflag","tempflag");
			form.setCount(this.pushStaService.getpushStaCount(form.getCondition()));
			List<PushSta> list = this.pushStaService.getPagingList(form.getCondition()," order by b.ftpsDate ",form.getPageCount(),form.getCurpage());
			model.put("list", list);
			model.put("form", form);
		}catch(Exception e){
			e.printStackTrace();
            request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
            forwardString="error";
		}
		return new ModelAndView(forwardString, model);
	}

}
