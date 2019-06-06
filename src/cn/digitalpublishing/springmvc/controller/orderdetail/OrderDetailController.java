package cn.digitalpublishing.springmvc.controller.orderdetail;

import java.io.InputStream;
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
import cn.digitalpublioshing.springmvc.form.orderdetail.OOrderDetailForm;
import cn.digitalpublishing.po.OOrderDetail;
import cn.digitalpublishing.springmvc.controller.BaseController;


@Controller
@RequestMapping("/pages/order")
public class OrderDetailController extends BaseController {

	/**
	 * 推送列表
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/form/manager")
	public ModelAndView manager(HttpServletRequest request,HttpServletResponse response, OOrderDetailForm form)throws Exception {
		forwardString="orderDetail/OrderDetailList";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			form.setUrl(request.getRequestURL().toString());
			form.setCount(this.orderDetailService.getOrderCount(form.getCondition()));
			List<OOrderDetail> list = this.orderDetailService.getOrderPagingList(form.getCondition()," order by o.odetailNum ",form.getPageCount(),form.getCurpage());
			model.put("list", list);
			model.put("form", form);
		}catch(Exception e){
            request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			e.printStackTrace();
            forwardString="error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	/**
	 * 显示首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/orderEdit")
	public ModelAndView index(OOrderDetailForm form) throws Exception {
		String forwardString="orderDetail/OrderDetailEdit";
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("form", form);
		return new ModelAndView(forwardString,model);
	}

	
	
	/**
	 * Excel导入产品信息
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	 @RequestMapping(value="/form/save")
		public ModelAndView editSubmit(HttpServletRequest request, OOrderDetailForm form) throws Exception {
		 forwardString="msg";
        try {
            InputStream is = form.getOrderFile().getInputStream(); 
			this.orderDetailService.upload(is);
			form.setIsSuccess("true");
			form.setMsg(Lang.getLanguage("WordType.info.add.success",request.getSession().getAttribute("lang").toString()));
   
        } catch (Exception e) {
        	form.setIsSuccess("false");
        	/*String msg = (e instanceof CcsException)?((CcsException)e).getPrompt():e.getMessage();
        	form.setMsg(msg);*/
        	form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
        } finally {
			form.setOrderFile(null);
		}
        model.put("form", form);
		return new ModelAndView(forwardString,model);
    }
	

		@RequestMapping(value="/form/orderUpdate")
		public ModelAndView update(HttpServletRequest request,HttpServletResponse response, OOrderDetailForm form)throws Exception {
			String forwardString="orderDetail/OrderDetailUpdate";
			OOrderDetail obj = null;
			Map<String,Object> model = new HashMap<String,Object>();
			try{
				if(request.getParameter("orderid")!=null&&!"".equals(request.getParameter("orderid").toString())){
					obj = this.orderDetailService.getOrder(request.getParameter("orderid").toString());
					form.setId(request.getParameter("orderid").toString());
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
		
		@RequestMapping(value="/form/updateSubmit")
		public ModelAndView editSubmit(HttpServletRequest request,HttpServletResponse response,OOrderDetailForm form) throws Exception {
			HashMap<String,Object> model = new HashMap<String,Object>();
			String forwardString="msg";
			try{
				String[] properties=null;
				form.getObj().setUpdatedon(new Date());
				this.orderDetailService.updateOrder(form.getObj(), form.getId(), properties);
				form.setIsSuccess("true");
				form.setMsg(Lang.getLanguage("WordType.info.update.success",request.getSession().getAttribute("lang").toString()));
			}catch(Exception e){
				form.setIsSuccess("false");
				form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			}
			model.put("form", form);
			return new ModelAndView(forwardString,model);
		}
		

		 @RequestMapping(value="/form/delete")
			public ModelAndView delete(HttpServletRequest request,HttpServletResponse response,OOrderDetailForm form) throws Exception {
				try{
					this.orderDetailService.deleteOrder(request.getParameter("id"));
					form.setMsg(Lang.getLanguage("msg.info.delete.success",request.getSession().getAttribute("lang").toString()));
				}catch(Exception e){
					form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
				}
				model.put("form", form);
				return this.manager(request,response,form);
			}
}
