package cn.digitalpublishing.springmvc.controller.callback;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import test.sel.UpdateStatus;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.books.BooksForm;

@Controller
@RequestMapping("callback")
public class CallBackController extends BaseController {

		@RequestMapping(value="sina")
		public ModelAndView callBack(HttpServletRequest request,HttpServletResponse response,BooksForm form) throws Exception {
			try {
				UpdateStatus.savaAccess_token(request.getParameter("code"));
				form.setMsg("授权成功");
			} catch (Exception e) {
				form.setMsg("授权失败");
				// TODO: handle exception
			}
			return new ModelAndView("accessSuccess",model);
		}
		
}

