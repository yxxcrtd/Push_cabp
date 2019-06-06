package cn.digitalpublishing.springmvc.controller.order;

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
import cn.digitalpublishing.po.OOrder;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.order.OrderForm;

@Controller
@RequestMapping("/pages/orderok")
public class OrderController extends BaseController {

	@RequestMapping(value="/form/manager")
	public ModelAndView manager(HttpServletRequest request,HttpServletResponse response, OrderForm form)throws Exception {
		forwardString="order/OrderList";
		Map<String,Object> model = new HashMap<String,Object>();
		try{	
			form.setUrl(request.getRequestURL().toString());
			form.setCount(this.orderService.getOrderCount(form.getCondition()));
			List<OOrder> list = this.orderService.getOrderPagingList(form.getCondition()," order by oo.id ",form.getPageCount(),form.getCurpage());
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
	public ModelAndView index(OrderForm form) throws Exception {
		String forwardString="order/OrderEdit";
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("form", form);
		return new ModelAndView(forwardString,model);
	}

	
	
	
	
	@RequestMapping(value="/form/Update")
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response, OrderForm form)throws Exception {
		String forwardString="order/OrderUpdate";
		OOrder obj = null;
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			if(request.getParameter("orderid")!=null&&!"".equals(request.getParameter("orderid").toString())){
				obj = this.orderService.getOrder(request.getParameter("orderid").toString());
				form.setId(request.getParameter("orderid").toString());
			}
			form.setObj(obj);
			model.put("form", form);
		}catch(Exception e){
            request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			forwardString="error";
		}
		return new ModelAndView(forwardString, model);
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
		public ModelAndView editSubmit1(HttpServletRequest request, OrderForm form) throws Exception {
		 forwardString="msg";
        try {
            InputStream is = form.getFile().getInputStream(); 
			this.orderService.upload(is);
			form.setIsSuccess("true");
			form.setMsg(Lang.getLanguage("Employee.info.add.success",request.getSession().getAttribute("lang").toString()));
   
			
        } catch (Exception e) {
        	/*e.printStackTrace();
        	form.setIsSuccess("false");
        	form.setMsg("上传失败");*/
        	form.setIsSuccess("false");
        	/*String msg = (e instanceof CcsException)?((CcsException)e).getPrompt():e.getMessage();
        	request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
        	form.setMsg(msg);*/
        	form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
        } finally {
			form.setFile(null);
		}
        model.put("form", form);
		return new ModelAndView(forwardString,model);
    }
	
	 @RequestMapping(value="/form/delete")
		public ModelAndView delete(HttpServletRequest request,HttpServletResponse response,OrderForm form) throws Exception {
			try{
				this.orderService.deleteOrder(request.getParameter("id"));
				form.setMsg(Lang.getLanguage("msg.info.delete.success",request.getSession().getAttribute("lang").toString()));
			}catch(Exception e){
				form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			}
			model.put("form", form);
			return this.manager(request,response,form);
		}
		
		@RequestMapping(value="/form/updateSubmit")
		public ModelAndView editSubmit(HttpServletRequest request,HttpServletResponse response,OrderForm form) throws Exception {
			HashMap<String,Object> model = new HashMap<String,Object>();
			String forwardString="msg";
			try{
				String[] properties=null;
				form.getObj().setUpdatedon(new Date());
				this.orderService.updateOrder(form.getObj(), form.getId(), properties);
				form.setIsSuccess("true");
				form.setMsg(Lang.getLanguage("WordType.info.update.success",request.getSession().getAttribute("lang").toString()));
			}catch(Exception e){
				form.setIsSuccess("false");
				form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			}
			model.put("form", form);
			return new ModelAndView(forwardString,model);
		}
		
}

