 package cn.digitalpublishing.springmvc.controller.onix;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.resource.Onix;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.onix.OnixForm;

/**
 * Onix 管理
 * 
 * @author YangXinXin
 */
@Controller
@RequestMapping("/pages/onix")
public class OnixController extends BaseController {

	/**
	 * 管理列表
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/manager")
	public ModelAndView manager(HttpServletRequest request, OnixForm form) throws Exception {
		try {
			form.setUrl(request.getRequestURL().toString());
			form.setCount(this.onixService.getAllOnixCount(form.getCondition()));
			List<Onix> onixList = this.onixService.getOnixPagingList(form.getCondition(), " order by o.onixID ", form.getPageCount(), form.getCurpage());
			model.put("onixList", onixList);
			model.put("form", form);
			forwardString = "onix/OnixList";
		} catch (Exception e) {
			request.setAttribute("mintessage", (e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
			e.printStackTrace();
			forwardString = "error";
		}
		return new ModelAndView(forwardString, model);
	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) String id, HttpServletRequest request, OnixForm form) throws Exception {
		try {
			if (null != id && !"".equals(id)) {
				Onix onix = this.onixService.findById(id);
				if (null != onix && !"".equals(onix.getOnixID())) {
					form.setObj(onix);
					form.setId(id);
				}
			} else {
				form.getObj().setNecessary(false);
				form.getObj().setOnly(false);
			}
			model.put("form", form);
			forwardString = "onix/OnixEdit";
		} catch (Exception e) {
			request.setAttribute("message", (e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
			forwardString = "error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	/**
	 * 保存
	 * 
	 * @param note
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/form/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, OnixForm form) {
		try {
			if (null != form.getId() && !"".equals(form.getId()) && !"0".equals(form.getId())) {
				String[] properties = null;
				this.onixService.update(form.getObj(), form.getId(), properties);
				form.setIsSuccess("true");
				form.setMsg(Lang.getLanguage("Onix.Info.Update.Success",request.getSession().getAttribute("lang").toString()));
			} else {
				this.onixService.save(form.getObj());
				form.setIsSuccess("true");
				form.setMsg(Lang.getLanguage("Onix.Info.Save.Success", request.getSession().getAttribute("lang").toString()));
			}
		} catch (Exception e) {
			form.setIsSuccess("false");
			e.printStackTrace();
			form.setMsg((e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
		}
		model.put("form", form);
		forwardString = "msg";
		return new ModelAndView(forwardString, model);
	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/form/delete")
	public ModelAndView delete(@RequestParam(value = "id", required = false) String id, HttpServletRequest request, OnixForm form) throws Exception {
		try {
			this.onixService.delete(id);
			form.setMsg(Lang.getLanguage("Onix.Info.Delete.Success", request.getSession().getAttribute("lang").toString()));
		} catch (Exception e) {
			form.setMsg((e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
		}
		model.put("form", form);
		return manager(request, form);
	}

}
