package cn.digitalpublishing.springmvc.controller.usertemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.Mapping;
import cn.digitalpublishing.po.RTemplate;
import cn.digitalpublishing.po.UserTemplateNode;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.usertemplate.RTemplateForm;

/**
 * 模板映射
 * 
 * @author YangXinXin
 */
@Controller
@RequestMapping("/pages/rtemplateNode")
public class RTemplateNodeController extends BaseController {

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
			int dataCount = 0;
			List<UserTemplateNode> userTemplateNodeList = null;
			List<UserTemplateNode> targetTemplateNodeList = null;
			//如果用户模板id和目标模板id存在
			if (null != form.getTargetTemplateId().getId() && !"".equals(form.getTargetTemplateId().getId()) && null != form.getUserTemplateId().getId() && !"".equals(form.getUserTemplateId().getId())) {
				//通过用户模板id取得用户模板节点列表
				Map<String,Object> conditionuser = new HashMap<String,Object>();
				conditionuser.put("userTemplateId", form.getUserTemplateId().getId());
				userTemplateNodeList = this.userTemplateNodeService.getTemplateNodeListByUser(conditionuser,"");
				model.put("userTemplateNodeList", userTemplateNodeList);
				// 通过用户模板id取得目标模板节点列表
				Map<String,Object> conditionutargate = new HashMap<String,Object>();
				conditionutargate.put("targetTemplateId", form.getTargetTemplateId().getId());
				dataCount = this.userTemplateNodeService.getTemplateNodeCount(conditionutargate);
				targetTemplateNodeList = this.userTemplateNodeService.getTemplateNodeListByTarget(conditionutargate," ORDER BY a.nodeCode", form.getPageCount(), form.getCurpage());
				model.put("targetTemplateNodeList", targetTemplateNodeList);
				//查询已经创建关系的集合并封装成map
					
				Map<String,Object> conditionBind = new HashMap<String,Object>();
				//用户模板id
				conditionBind.put("userTemplateId", form.getUserTemplateId().getId());
				//目标模板id
				conditionBind.put("targetTemplateId", form.getTargetTemplateId().getId());
				List <Mapping>TemplateBindList = this.mappingService.findMappingListByUserTemplateIdAndTargetTemplateId(conditionBind);
				Map<String,String> map = new HashMap<String,String>();
				for(Mapping mapping : TemplateBindList){
					map.put(mapping.getUserTemplateNodeId(), mapping.getTargetTemplateNodeId());
				}
				model.put("map", map);
				model.put("targetTemplateNodeList", targetTemplateNodeList);
				
				// 为了回显目标模板ID和名称
				List<RTemplate> targetTemplateList = new ArrayList<RTemplate>();
				Map<String, Object> cond = new HashMap<String, Object>();
				cond.put("userTemplateId", form.getUserTemplateId().getId());
				targetTemplateList = (List<RTemplate>) this.rtemplateService.getRTemplateListById(cond, "");
				model.put("targetTemplateList", targetTemplateList);
			} else {
				model.put("userTemplateNodeList", null);
				model.put("targetTemplateNodeList", null);
			}
			form.setCount(dataCount);
			form.setUrl(request.getRequestURL().toString());
			model.put("form", form);
			forwardString = "template/RTemplateNodeList";
		} catch (Exception e) {
			request.setAttribute("mintessage", (e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
			e.printStackTrace();
			forwardString = "error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	
	@RequestMapping(value = "/form/managerIndex")
	public ModelAndView managerIndex(HttpServletRequest request, RTemplateForm form) throws Exception {
		List<UserTemplateNode> userTemplateNodeList = null;
		List<UserTemplateNode> targetTemplateNodeList = null;
		try {
			//获取模板映射表中的全部用户模板列表
			List<RTemplate>	userTemplateIdList = (List<RTemplate>) this.rtemplateService.getRTemplateListByUserTemplateId(form.getCondition(), " group by t.userTemplateId.id");
			model.put("userTemplateIdList", userTemplateIdList);
			form.setUrl(request.getRequestURL().toString());
			model.put("form", form);
			forwardString = "template/RTemplateNodeList";
		} catch (Exception e) {
			request.setAttribute("mintessage", (e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
			e.printStackTrace();
			forwardString = "error";
		}
		model.put("userTemplateNodeList", userTemplateNodeList);
		model.put("targetTemplateNodeList", targetTemplateNodeList);
		return new ModelAndView(forwardString, model);
	}
	

	/**
	 * 在页面上使用jQuery获取目标模板列表，返回JSON格式
	 * 
	 * @param response
	 * @param sourceId
	 * @param form
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/form/getTargetList")
	public List<RTemplate> getTargetList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<RTemplate> targetTemplateList = null;
		String userTemplateId = request.getParameter("userTemplateId");
		if (null != userTemplateId && !"".equals(userTemplateId)) {
			// 获取映射的目标模板列表
			Map<String, Object> cond = new HashMap<String, Object>();
			cond.put("userTemplateId", userTemplateId);
			targetTemplateList = (List<RTemplate>) this.rtemplateService.getRTemplateListById(cond, "");
		}
		// 返回
		return targetTemplateList;
	}
	
	/**
	 * 保存、修改或删除
	 * 
	 * @param note
	 * @param redirectAttributes
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/form/save")
	public String save(@RequestParam(value = "targetTemplateId") String targetTemplateId,
			@RequestParam(value = "userTemplateId") String userTemplateId,
			@RequestParam(value = "targetTemplateNodeId") String targetTemplateNodeId,
			@RequestParam(value = "userTemplateNodeId") String userTemplateNodeId) throws Exception {
		String msg = "";
		try {
			// 目标模板ID和目标模板节点ID不能为空
			if (null != targetTemplateId && !"".equals(targetTemplateId) && null != targetTemplateNodeId && !"".equals(targetTemplateNodeId)) {
				model.put("targetTemplateId", targetTemplateId);
				model.put("targetTemplateNodeId", targetTemplateNodeId);
				// 根据 目标模板ID和目标模板节点ID 获取当前映射对象
				Mapping mapping = this.mappingService.findByTargetTemplateIdAndtargetTemplateNodeId(model);
				// 如果该目标模板下的目标模板节点所对应的对象存在
				if (null != mapping) {
					// 有用户模板节点ID（分两种情况：1，选择了新的用户模板节点ID；2，又选了自己...），既是修改
					if (null != userTemplateNodeId && !"".equals(userTemplateNodeId)) {
						// 又选了自己，不用修改
						if (!userTemplateNodeId.equals(mapping.getUserTemplateNodeId())) {
							mapping.setUserTemplateNodeId(userTemplateNodeId);
							String[] properties = null;
							this.mappingService.update(mapping, mapping.getId(), properties);
						}
					} else {
						// 如果选择了空，则是删除
						this.mappingService.delete(mapping.getId());
					}
				} else { // 第一次是新增
					mapping = new Mapping();
					mapping.setTargetTemplateId(targetTemplateId);
					mapping.setUserTemplateId(userTemplateId);
					mapping.setTargetTemplateNodeId(targetTemplateNodeId);
					mapping.setUserTemplateNodeId(userTemplateNodeId);
					this.mappingService.save(mapping);
				}
			}
			msg = "success";
		} catch (Exception e) {
			// TODO: handle exception
			msg = "err";
		}
		return msg;
	}
	public static void main(String[] args) {
		String str =new String("123");
		String str1 ="123";
		System.out.println(str.equals(str1));
	}

}
