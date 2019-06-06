package cn.digitalpublishing.springmvc.controller.threadcfg;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.model.Param;
import cn.digitalpublishing.config.ProcessQueue;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.threadcfg.LoadConfigForm;


@Controller
@RequestMapping("/pages/loadConfig")
public class LoadConfigController extends BaseController {
	
	@RequestMapping(value="/form/threadcfg")
	public ModelAndView configManager(HttpServletRequest request,HttpServletResponse response, LoadConfigForm form)throws Exception {
		String forwardString="threadcfg/LoadConfig_Adm";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			form.setLoadMap(Param.getParam("Interface.thread.status",true,request.getSession().getAttribute("lang").toString()));
			//下载源数据线程
			form.setSourceDataLoad(ProcessQueue.SOURCEDATALOAD);
			//解析线程
			form.setAnalysisDataLoad(ProcessQueue.ANALYSISDATALOAD);
			//下载图书线程
			form.setDownload(ProcessQueue.DOWNLOAD);
			//模板转换线程
			form.setConvertDataLoad(ProcessQueue.CONVERTDATALOAD);
			//图书推送线程
			form.setPushLoad(ProcessQueue.PUSHLOAD);
			
			
			model.put("form", form);
		}catch(Exception e){
			e.printStackTrace();
            request.setAttribute("message",(e instanceof CcsException)?((CcsException)e).getPrompt():e.getMessage());
			forwardString="error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	@RequestMapping(value="/form/submit")
	public ModelAndView submit(HttpServletRequest request,HttpServletResponse response,LoadConfigForm form) throws Exception {
		HashMap<String,Object> model = new HashMap<String,Object>(); 
		try{
			//下载源数据xml线程
			ProcessQueue.SOURCEDATALOAD=form.getSourceDataLoad();
			//解析拆分线程
			ProcessQueue.ANALYSISDATALOAD=form.getAnalysisDataLoad();
			//下载图书
			ProcessQueue.DOWNLOAD=form.getDownload();
			System.out.println(form.getDownload());
			//转换数据
			ProcessQueue.CONVERTDATALOAD=form.getConvertDataLoad();
			//推送图书
			ProcessQueue.PUSHLOAD=form.getPushLoad();
			
			
			
			
			
		}catch(Exception e){
			form.setMsg((e instanceof CcsException)?((CcsException)e).getPrompt():e.getMessage());
		}
		model.put("form", form);
		return this.configManager(request,response,form);
	}

}
