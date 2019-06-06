package cn.digitalpublishing.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import cn.com.daxtech.framework.model.Param;
import cn.digitalpublishing.service.BooksService;
import cn.digitalpublishing.service.ContentService;
import cn.digitalpublishing.service.EmailService;
import cn.digitalpublishing.service.FtpDirService;
import cn.digitalpublishing.service.FtpService;
import cn.digitalpublishing.service.MappingService;
import cn.digitalpublishing.service.OnixCodeService;
import cn.digitalpublishing.service.OnixService;
import cn.digitalpublishing.service.OrderDetailService;
import cn.digitalpublishing.service.OrderService;
import cn.digitalpublishing.service.ProductService;
import cn.digitalpublishing.service.PushService;
import cn.digitalpublishing.service.PushStaService;
import cn.digitalpublishing.service.PushTaskService;
import cn.digitalpublishing.service.RTemplateService;
import cn.digitalpublishing.service.UserTemplateNodeService;
import cn.digitalpublishing.service.UserTemplateService;

public class BaseController extends MultiActionController {

	public Logger log = Logger.getLogger(this.getClass());

	protected String forwardString = "";

	protected Map<String, Object> model = new HashMap<String, Object>();

	@Autowired
	@Qualifier("ftpService")
	protected FtpService ftpService;

	@Autowired
	@Qualifier("ftpdirService")
	protected FtpDirService ftpDirService;

	@Autowired
	@Qualifier("userTemplate")
	protected UserTemplateService userTemplateService;

	@Autowired
	@Qualifier("userTemplateNodeService")
	protected UserTemplateNodeService userTemplateNodeService;

	@Autowired
	@Qualifier("onixService")
	protected OnixService onixService;

	@Autowired
	@Qualifier("onixCodeService")
	protected OnixCodeService onixCodeService;

	@Autowired
	@Qualifier("mappingService")
	protected MappingService mappingService;

	@Autowired
	@Qualifier("contentService")
	protected ContentService contentService;

	@Autowired
	@Qualifier("rtemplateService")
	protected RTemplateService rtemplateService;

	@Autowired
	@Qualifier("pushService")
	protected PushService pushService;

	@Autowired
	@Qualifier("pushTaskService")
	protected PushTaskService pushTaskService;

	@Autowired
	@Qualifier("productService")
	protected ProductService productService;
	
	@Autowired
	@Qualifier("booksService")
	protected BooksService booksService;
	
	@Autowired
	@Qualifier("orderDetailService")
	protected OrderDetailService orderDetailService;
	
	@Autowired
	@Qualifier("orderService")
	protected OrderService orderService;
	
	@Autowired
	@Qualifier("emailService")
	protected EmailService emailService;
	
	@Autowired
	@Qualifier("pushStaService")
	protected PushStaService pushStaService;
	
   public static final String getUploadPath() {
		Map<String, String> config = Param.getParam("product.structure.element.path");
    	return config.get("src").replace("-", ":");
    }
   
   public static final String getImagePath() {
		Map<String, String> config = Param.getParam("product.image.element.path");
   	return config.get("src").replace("-", ":");
   }
}
