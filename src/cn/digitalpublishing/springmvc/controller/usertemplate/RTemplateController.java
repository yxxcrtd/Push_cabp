package cn.digitalpublishing.springmvc.controller.usertemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.RTemplate;
import cn.digitalpublishing.po.UserTemplate;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.usertemplate.RTemplateForm;

/**
 * 模版映射
 * 
 * @author cuixian
 */
@Controller
@RequestMapping("/pages/rtemplate")
public class RTemplateController extends BaseController {

	/**
	 * 模板映射列表
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/manager")
	public ModelAndView manager(HttpServletRequest request, RTemplateForm form) throws Exception {
		try {
			//定义查询map集合			
			Map<String, Object> cond = new HashMap<>();
			if (form.getUserTemplateId() != null) {
				cond.put("userTemplateId", form.getUserTemplateId().getId());	
			}
			if (form.getTargetTemplateId() != null) {
				cond.put("targetTemplateId", form.getTargetTemplateId().getId());	
			}
			// 获取RTemplate用户模板列表
			if(form.getTargetTemplateId() == null && form.getUserTemplateId() == null){
				
			   List<RTemplate>	userTemplateIdList = (List<RTemplate>) this.rtemplateService.getRTemplateListByUserTemplateId(form.getCondition(), " group by t.userTemplateId.id");
			   model.put("userTemplateIdList", userTemplateIdList);
			}
			//映射模版信息列表
			form.setUrl(request.getRequestURL().toString());
			form.setCount(this.rtemplateService.getAllTemplateCount(cond));
			List<RTemplate> RtemplateList = this.rtemplateService.getTemplatePagingList(cond, " order by t.id desc", form.getPageCount(), form.getCurpage());
			model.put("RtemplateList", RtemplateList);
			model.put("form", form);
			forwardString = "template/RTemplateList";
		} catch (Exception e) {
			request.setAttribute("mintessage", (e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
			e.printStackTrace();
			forwardString = "error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	/**
	 * 编辑模版信息
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) String id, HttpServletRequest request, RTemplateForm form) throws Exception {
		try {
			
			// 获取UserTemplate用户模板列表
			form.setFlag("0");
			List<UserTemplate> userTemplateList = (List<UserTemplate>) this.userTemplateService.getUserTemplateListByFlag(form.getCondition(), " order by a.id desc");
			model.put("userTemplateList", userTemplateList);
             
			//修改
			if (null != id && !"".equals(id)) {
				RTemplate rtemplate = this.rtemplateService.findById(id);
				if (null != rtemplate && !"".equals(rtemplate.getId())) {
					form.setObj(rtemplate);
					form.setId(id);
				}
				// 修改时获取UserTemplate目标模板列表
				UserTemplate list = this.userTemplateService.findById(form.getObj().getTargetTemplateId().getId());
				Map<String,Object> condition = new HashMap<String,Object>();
				condition.put("type", list.getType().toString());
				condition.put("flag", "1");
				List<UserTemplate> targetTemplateList = (List<UserTemplate>) this.userTemplateService.getUserTemplateListByFlag(condition, " order by a.id desc");
				model.put("targetTemplateList", targetTemplateList);
			}
			
			model.put("form", form);
			forwardString = "template/RTemplateEdit";
		} catch (Exception e) {
			request.setAttribute("message", (e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
			forwardString = "error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	
	
	/**
	 * 根据Json传值获取UserTemplate目标模板列表
	 * 
	 * @param response
	 * @param type
	 * @param form
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/form/getType")
	public List<UserTemplate> getTypeFlagList(HttpServletResponse response, @RequestParam(value = "type") String type) throws Exception {
		List<UserTemplate> targetTemplateList = null;
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("type", type);
		condition.put("flag", "1");
		if (null != type && !"".equals(type)) {
			// 新增获取UserTemplate目标模板列表
			targetTemplateList = (List<UserTemplate>) this.userTemplateService.getUserTemplateListByFlag(condition, " order by a.id desc");
		}
		return targetTemplateList;
	}
	
	/**
	 * 根据Json传值获取RTemplate目标模板列表
	 * 
	 * @param response
	 * @param userTemplateId
	 * @param form
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/form/getTarget")
	public List<RTemplate> getTargetList(HttpServletResponse response,UserTemplate userTemplateId) throws Exception {
		List<RTemplate> list = null;
		Map<String, Object> cond = new HashMap<>();
		if (userTemplateId.getId() !=null) {
			cond.put("userTemplateId", userTemplateId.getId());
		}
			// 获取RTemplate目标模板列表
		list = this.rtemplateService.getRTemplateListByUserTemplateId(cond, " order by t.id desc");
		for (RTemplate r : list) {
			r.getTargetTemplateId().setUserTemplateNodes(null);
			r.getUserTemplateId().setUserTemplateNodes(null);			
		}
		model.put("list", list);
		return list;
	}
	
	
	
	/**
	 * 保存
	 * 
	 * @param note
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/form/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, RTemplateForm form) {
		try {
			//获取页面传来的值
			Map<String, Object> cond = new HashMap<>();
			if (form.getObj().getUserTemplateId().getId() != null) {
				cond.put("userTemplateId", form.getObj().getUserTemplateId().getId());	
			}
			if (form.getObj().getTargetTemplateId().getId() != null) {
				cond.put("targetTemplateId", form.getObj().getTargetTemplateId().getId());	
			}
			//传值验证重复，重复提示不能提交
			List<RTemplate> RTemplateList = this.rtemplateService.getRTemplateListById(cond, "");
				    
			//更新
			if (null != form.getId() && !"".equals(form.getId()) && !"0".equals(form.getId())) {
				//不做修改保存
				RTemplate dbt = this.rtemplateService.findById(form.getId());
				if (dbt.getUserTemplateId().getId().equals(form.getObj().getUserTemplateId().getId()) && dbt.getTargetTemplateId().getId().equals(form.getObj().getTargetTemplateId().getId())) {
					form.setIsSuccess("true");
				    form.setMsg(Lang.getLanguage("msg.info.update.success",request.getSession().getAttribute("lang").toString()));
				    forwardString = "msg";
				} else {
					//修改后保存重复判断
					if(0 < RTemplateList.size()) {
						form.setMsg(Lang.getLanguage("RTemplate.info.id.error", request.getSession().getAttribute("lang").toString()));
						forwardString = "template/RTemplateEdit";
					} else {
						//修改后保存成功
						String[] properties = null;
					    this.rtemplateService.update(form.getObj(), form.getId(), properties);
					    form.setIsSuccess("true");
					    form.setMsg(Lang.getLanguage("msg.info.update.success",request.getSession().getAttribute("lang").toString()));
					    forwardString = "msg";
					}
				}
			}else{
				//添加保存判断重复
				if(0 < RTemplateList.size()){
					form.setMsg(Lang.getLanguage("RTemplate.info.id.error", request.getSession().getAttribute("lang").toString()));
					forwardString = "template/RTemplateEdit";
				}else{
					//添加陈功
					this.rtemplateService.save(form.getObj());
					form.setIsSuccess("true");
					form.setMsg(Lang.getLanguage("msg.info.add.success", request.getSession().getAttribute("lang").toString()));
					forwardString = "msg";
				}
			}
		} catch (Exception e) {
			form.setIsSuccess("false");
			e.printStackTrace();
			form.setMsg((e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}
	
	/**
	 * 删除模版信息
	 * 
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/form/delete")
	public ModelAndView delete(@RequestParam(value = "id", required = false) String id, HttpServletRequest request, RTemplateForm form) throws Exception {
		try {
			this.rtemplateService.delete(id);
			form.setMsg(Lang.getLanguage("RTemplate.Info.Delete.Success", request.getSession().getAttribute("lang").toString()));
		} catch (Exception e) {
			form.setMsg((e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
		}
		model.put("form", form);
		return manager(request, form);
	}
}


