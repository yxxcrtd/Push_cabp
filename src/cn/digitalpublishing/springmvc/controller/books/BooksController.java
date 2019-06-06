package cn.digitalpublishing.springmvc.controller.books;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import test.sel.UpdateStatus;
import cn.com.daxtech.framework.Internationalization.Lang;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.model.Param;
import cn.digitalpublishing.config.ProcessQueue;
import cn.digitalpublishing.po.Books;
import cn.digitalpublishing.po.Email;
import cn.digitalpublishing.po.FtpConfigure;
import cn.digitalpublishing.po.FtpDirConfigure;
import cn.digitalpublishing.po.OOrder;
import cn.digitalpublishing.po.Task;
import cn.digitalpublishing.springmvc.controller.BaseController;
import cn.digitalpublishing.springmvc.form.books.BooksForm;
import cn.digitalpublishing.util.EmailUtil;
import cn.digitalpublishing.util.FileUtil;
import cn.digitalpublishing.util.UploadFileUtil;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@Controller
@RequestMapping("/pages/books")
public class BooksController extends BaseController {

	@RequestMapping(value="/form/manager")
	public ModelAndView manager(HttpServletRequest request,HttpServletResponse response, BooksForm form)throws Exception {
		forwardString="books/BooksList";
		Map<String,Object> model = new HashMap<String,Object>();
		try{	
			form.setUrl(request.getRequestURL().toString());
			form.setCount(this.booksService.getBooksCount(form.getCondition()));
			List<Books> list = this.booksService.getBooksPagingList(form.getCondition()," order by b.orderNo ",form.getPageCount(),form.getCurpage());
			List<Books> books = this.booksService.getList(null," order by b.orderNo ");
			if(books.size()>0){
				for(Books book : books){
	            	 createXml(book);
	             }
			}
			model.put("list", list);
			model.put("form", form);
		}catch(Exception e){
            request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
            forwardString="error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	@RequestMapping(value="/form/pushFtpList")
	public ModelAndView pushFtpList(HttpServletRequest request,HttpServletResponse response, BooksForm form)throws Exception {
		forwardString="books/BooksPushSet";
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			form.setArr_select(form.getArr_select());
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("flag", "1");
			List<FtpConfigure> ftpConfigureList=this.ftpService.getftpNameAndId(condition,"order by a.name");
			model.put("ftpConfigureList", ftpConfigureList);
			model.put("form", form);
		}catch(Exception e){
            request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
            forwardString="error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	/**
	 * 根据Json传值获取目标Ftp文件夹
	 * 
	 * @param response
	 * @param ftpdir
	 * @param form
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/form/getFtpdir")
	public List<FtpDirConfigure> getFtpDirList(HttpServletResponse response,String ftpId) throws Exception {
		List<FtpDirConfigure> list = null;
		Map<String,Object> condition = new HashMap<String,Object>();
		if (ftpId !=null) {
			condition.put("ftpid", ftpId);
			list = this.ftpDirService.getFtpDirByFtpid(condition);
		}
		return list;
	}
	
	/**
	 * 推送
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/booksSet")
	public ModelAndView booksSet(HttpServletRequest request, HttpServletResponse response, BooksForm form) throws Exception {
		HashMap<String,Object> model = new HashMap<String,Object>();
		forwardString = "msg";
		try {
			// 1，获取目标FTP对象
			FtpConfigure ftp = this.ftpService.getFtp(form.getFtpId());
			
			// 2，获取FTPDIR对象
			FtpDirConfigure ftpdir = this.ftpDirService.getFtpDir(form.getFtpdirId());
			
			//3.文件夹路径
			String pushPath=ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.PUSHHOME+File.separator;
			
			//4.获取资源信息
			String booksId = form.getArr_select();
			
			String [] lArray = booksId.split(","); //根据逗号拆分字符串
			
			for (int i = 0; i < lArray.length; i++) {
				Books books = this.booksService.getBooks(lArray[i]);
				//推送包名称
				String fileName = books.getIsbn()+".zip";
				// 创建推送任务
				createPushTask(ftp, ftpdir, pushPath+File.separator+fileName,fileName,books,books.getTitle(),books.getIsbn(),books.getPublisher());
			}
			form.setIsSuccess("true");
			form.setMsg(Lang.getLanguage("Push.Info.Set.Push.Success",request.getSession().getAttribute("lang").toString()));
			model.put("form", form);
		} catch (Exception e) {
			request.setAttribute("message", (e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
			e.printStackTrace();
			forwardString = "error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	/**
	 * 创建推送任务
	 * 
	 * @param ftp
	 * @param ftpdir
	 * @param ut
	 * @param path
	 * @throws Exception
	 */
	private void createPushTask(FtpConfigure ftp, FtpDirConfigure ftpdir, String path,String fileName,Books books,String bookName,String isbn,String publisher) throws Exception {
		Task task = new Task();
		task.setDabaoDate(new Date());
		//task.setBooks(books);
		task.setFileName(fileName);
		task.setPushFilePath(path);
		task.setStatus(1);
		task.setTargateServer(ftp.getName());
		task.setFtpdir(ftpdir.getFtpdir());
		task.setIp(ftp.getIp());
		task.setPort(ftp.getPort());
		task.setUsername(ftp.getUsername());
		task.setPassword(ftp.getPassword());
		task.setCreateTime(new Date());
		task.setBookName(bookName);
		task.setIsbn(isbn);
		task.setPublisher(publisher);
		task.setClassify("0");
		this.pushTaskService.save(task);
	}
	
	/**
	 * 显示首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/booksEdit")
	public ModelAndView index(BooksForm form) throws Exception {
		String forwardString="books/BooksEdit";
		
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("form", form);
		return new ModelAndView(forwardString,model);
	}
	/**
	 * 获取用户列表
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/form/userEmailManagerUser")
	public ModelAndView userEmailManagerUser(HttpServletRequest request,HttpServletResponse response, BooksForm form)throws Exception {
		forwardString="order/UserEmailList";
		Map<String,Object> model = new HashMap<String,Object>();
		try{	
			form.setUrl(request.getRequestURL().toString());
			form.setCount(this.orderService.getOrderCount(form.getCondition()));
			List<OOrder> list = this.orderService.getOrderPagingList(form.getCondition()," order by oo.id ",form.getPageCount(),form.getCurpage());
			form.setEnailheadMap(Param.getParam("emailhead",true,request.getSession().getAttribute("lang").toString()));
			form.setEnailheadMap(Param.getParam("emailend",true,request.getSession().getAttribute("lang").toString()));
			model.put("list", list);
			model.put("form", form);
			
		}catch(Exception e){
            request.setAttribute("message",(e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			e.printStackTrace();
            forwardString="error";
		}
		return new ModelAndView(forwardString, model);
	}
	
	
	
//	/**
//	 * 获取用户列表
//	 * @param request
//	 * @param response
//	 * @param form
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/form/userXml")
//	public ModelAndView userXml(HttpServletRequest request,HttpServletResponse response, BooksForm form)throws Exception {
//		HashMap<String,Object> model = new HashMap<String,Object>();
//		//String forwardString="msg";
//		String usersId = form.getArr_select();
//		String [] lArray = usersId.split(","); 
//		//用户email
//		try {
//			
//			
//			Books booksoo = this.booksService.getBooks(lArray[0]);
//			Document document = DocumentHelper.createDocument();
//			Element root = document.addElement("books");
//			Element comment = root.addElement("comment");
//			comment.setText("");
//			//String path=ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.BOOKXML+File.separator+bookso.getIsbn()+File.separator;
//			String path = new StringBuffer(getUploadPath()).append(File.separator).toString();
//		for (int i = 0; i < lArray.length; i++) {
//			Books bookso = this.booksService.getBooks(lArray[i]);
//				//判断路径是否存在
//				File file =new File(path);
//				if(!file.exists()){
//					file.mkdirs();
//				}
//				Element book = root.addElement("book");
//				Element orderNo = book.addElement("orderNo");
//				orderNo.setText(String.valueOf("".equals(bookso.getOrderNo()) ?  0 : bookso.getOrderNo()));
//				Element title = book.addElement("title");
//				title.setText(String.valueOf("".equals(bookso.getTitle()) ?  0 : bookso.getTitle()));
//				Element author = book.addElement("author");
//				author.setText(String.valueOf("".equals(bookso.getAuthor()) ?  0 : bookso.getAuthor()));
//				Element ISBN = book.addElement("ISBN");
//				ISBN.setText(String.valueOf("".equals(bookso.getIsbn()) ?  0 : bookso.getIsbn()));
//				Element publishDate = book.addElement("publishDate");
//				publishDate.setText(String.valueOf("".equals(bookso.getPublishDate()) ?  0 : bookso.getPublishDate()));
//				Element price = book.addElement("price");
//				price.setText(String.valueOf("".equals(bookso.getPrice()) ?  0 : bookso.getPrice()));
//				Element onPrice = book.addElement("onPrice");
//				onPrice.setText(String.valueOf("".equals(bookso.getOnPrice()) ?  0 : bookso.getOnPrice()));
//				Element offPrice = book.addElement("offPrice");
//				offPrice.setText(String.valueOf("".equals(bookso.getOffPrice()) ?  0 : bookso.getOffPrice()));
//				Element publisher = book.addElement("publisher");
//				publisher.setText(String.valueOf("".equals(bookso.getPublisher()) ?  0 : bookso.getPublisher()));
//				Element publisher_loc = book.addElement("publisher_loc");
//				publisher_loc.setText(String.valueOf("".equals(bookso.getLocation()) ?  0 : bookso.getLocation()));
//				Element edition = book.addElement("edition");
//				edition.setText(String.valueOf("".equals(bookso.getEdition()) ?  0 : bookso.getEdition()));
//				Element pages = book.addElement("pages");
//				pages.setText(String.valueOf("".equals(bookso.getPage()) ?  0 : bookso.getPage()));
//				Element frame = book.addElement("frame");
//				frame.setText(String.valueOf("".equals(bookso.getFrame()) ?  0 : bookso.getFrame()));
//				Element format = book.addElement("format");
//				format.setText(String.valueOf("".equals(bookso.getFormat()) ?  0 : bookso.getFormat()));
//				Element sheet = book.addElement("sheet");
//				sheet.setText(String.valueOf("".equals(bookso.getSheet()) ?  0 : bookso.getSheet()));
//				Element cover = book.addElement("cover");
//				cover.setText(String.valueOf("".equals(bookso.getCover()) ?  0 : bookso.getCover()));
//				Element URL = book.addElement("URL");
//				URL.setText(String.valueOf("".equals(bookso.getUrl()) ?  0 : bookso.getUrl()));
//				Element introduction = book.addElement("introduction");
//				introduction.setText(String.valueOf("".equals(bookso.getIntroduction()) ?  0 : bookso.getIntroduction()));
//				
//			}
//		
//					Writer write = new OutputStreamWriter(new FileOutputStream(new File(path + File.separator + booksoo.getIsbn() + ".xml")),"UTF-8");
//					XMLWriter xmlWriter = new XMLWriter(write,OutputFormat.createPrettyPrint());
//					xmlWriter.write(document);
//					xmlWriter.close();
//					form.setMsg(Lang.getLanguage("Pages.books.createxml.success",request.getSession().getAttribute("lang").toString()));
//	} catch (Exception e) {
//		e.printStackTrace();
//		form.setMsg((e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
//	}
//	
//	model.put("form", form);
//	return manager(request, null, form);
//}
	
	
	
	
	/**
	 * 发送邮件
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/form/sendEmails")
	public ModelAndView sendEmails(HttpServletRequest request,HttpServletResponse response, BooksForm form)throws Exception {
		HashMap<String,Object> model = new HashMap<String,Object>();
		String forwardString="msg";
		try {
			//用户ID
			String[] usersId = request.getParameterValues("subBox");
			//图书ID
			String orderNo = request.getParameter("booksId");
			
			StringBuffer mailContent = new StringBuffer("");
			
			String [] orderNoArray = orderNo.split(","); 
			for (int i = 0; i < orderNoArray.length; i++) {
				Books book = this.booksService.getBooks(orderNoArray[i]);
				mailContent.append("    "+"《 "+book.getTitle()+" 》"+" ");
				mailContent.append(book.getPublisher()+"  ");
				mailContent.append(book.getAuthor()+"  ");
				mailContent.append(book.getUrl()+"  ");
				mailContent.append("\n\r");
			}
			String str=form.getEmailContent();
			/*int e1 = str.indexOf("#");
			int e2 = str.lastIndexOf("#");
			String strcontent=str.substring(e1, e2+1);
			String strg=str.replace(strcontent, mailContent);*/
			HashMap<String, String> headmap=Param.getParam("emailhead",true,request.getSession().getAttribute("lang").toString());
			HashMap<String, String> endmap=Param.getParam("emailend",true,request.getSession().getAttribute("lang").toString());
			String mailhead=headmap.get("1");
			String mailend=endmap.get("1");
			String strg=mailhead+"\n\r"+"      "+str+"\n\r"+mailContent+mailend;
			
            String [] emailArr = new String[usersId.length]; 
			
			for (int i = 0; i < usersId.length; i++) {
				OOrder oorder = this.orderService.getOrder(usersId[i]);
				emailArr[i] = oorder.getEmail();
				Email e= new Email();
				e.setContacts(oorder.getContacts());
				e.setEmail(oorder.getEmail());
				e.setContent(strg);
				e.setSendDate(new Date());
				this.emailService.save(e);
			}
			EmailUtil.sendmail(ProcessQueue.EMAILSUBJECT, strg.toString(), emailArr);
			EmailUtil.sendmail(ProcessQueue.EMAILSUBJECT, mailContent.toString(), emailArr);
			form.setMsg(Lang.getLanguage("Pages.books.send.success",request.getSession().getAttribute("lang").toString()));
			form.setIsSuccess("true");
		}catch(Exception e){
			e.printStackTrace();
			form.setIsSuccess("false");
			form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
		}
		model.put("form", form);
		return new ModelAndView(forwardString,model);
	}
	
	
	@RequestMapping(value="/form/Update")
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response, BooksForm form)throws Exception {
		String forwardString="books/BooksUpdate";
		Books obj = null;
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			if(request.getParameter("booksid")!=null&&!"".equals(request.getParameter("booksid").toString())){
				obj = this.booksService.getBooks(request.getParameter("booksid").toString());
				form.setId(obj.getId());
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
	
	
	/**
	 * Excel导入产品信息
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	 @RequestMapping(value="/form/save")
		public ModelAndView saveSubmit(HttpServletRequest request, BooksForm form) throws Exception {
		 forwardString="msg";
		// List<Books> bookList = null;
        try {
            InputStream is = form.getTxtFile().getInputStream(); 
             List<Books> listbooks=this.booksService.upload(is);
             this.booksService.saveBooks(listbooks);
             form.setIsSuccess("true");
			 form.setMsg(Lang.getLanguage("Employee.info.add.success",request.getSession().getAttribute("lang").toString()));
        } catch (Exception e) {
        	/*form.setIsSuccess("false");
        	String msg = e.getMessage();
        	msg = msg.substring(msg.indexOf(":")+2);
        	form.setMsg(msg);*/
        	e.printStackTrace();
        	form.setIsSuccess("false");
        	/*String msg = (e instanceof CcsException)?((CcsException)e).getPrompt():e.getMessage();
        	form.setMsg(msg);*/
        	form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
        } finally {
			form.setTxtFile(null);
		}
        model.put("form", form);
		return new ModelAndView(forwardString,model);
    }
	
	 @RequestMapping(value="/form/delete")
		public ModelAndView delete(HttpServletRequest request,HttpServletResponse response,BooksForm form) throws Exception {
			try{
				this.booksService.deleteBooks(request.getParameter("id"));
				form.setMsg(Lang.getLanguage("msg.info.delete.success",request.getSession().getAttribute("lang").toString()));
			}catch(Exception e){
				form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			}
			model.put("form", form);
			return this.manager(request,response,form);
		}
	 
	@RequestMapping(value="/form/userDel")
	public ModelAndView userDel(HttpServletRequest request,HttpServletResponse response,BooksForm form) throws Exception {
	 String usersId = form.getArr_select();
	 String [] usersArray = usersId.split(","); 
			
			try{
				for (int i = 0; i < usersArray.length; i++) {
					Books book = this.booksService.getBooks(usersArray[i]);
					String path=ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.PUSHHOME+File.separator;
					File file = new File(path+File.separator+book.getIsbn()+".zip");
				    if(file.exists()){
				        file.delete();
				    }
					this.booksService.deleteBooks(usersArray[i]);
				}
				form.setMsg(Lang.getLanguage("msg.info.delete.success",request.getSession().getAttribute("lang").toString()));//FTP配置信息删除成功!
			}catch(Exception e){
				form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			}
			model.put("form", form);
			return this.manager(request,response,form);
	}
	 
	 
	 
	 
	 
	 
		@RequestMapping(value="/form/updateSubmit")
		public ModelAndView editSubmit(HttpServletRequest request,HttpServletResponse response,BooksForm form) throws Exception {
			HashMap<String,Object> model = new HashMap<String,Object>();
			String forwardString="msg";
			Books book = form.getObj();
			List<Books> bookList = null;
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("isbn", book.getIsbn());
			condition.put("isnotown", book.getId());
			try{
				if (form.getUpLoadPhoto() != null) {
					String photoPath = new StringBuffer(getImagePath()).append(File.separator).toString();
					//封面名称
					String fileName = book.getOrderNo()+".gif";
					UploadFileUtil.writeFile(photoPath, fileName, form.getUpLoadPhoto().getBytes());
					book.setCover(fileName);
				}
				String[] properties=null;
				 bookList = this.booksService.getList(condition, "");
	             if(bookList.size()>0){
	            	 form.setIsSuccess("true");
					 form.setMsg(Lang.getLanguage("Pages.books.yicunzai.xiangtongisbn",request.getSession().getAttribute("lang").toString()));
	             }else{
	            	// File file = new File(ProcessQueue.WEBROOT+File.separator+ProcessQueue.BOOKIMG+File.separator+book.getCover());
	            	File file  = new File(getUploadPath()+File.separator+(form.getObj().getIsbn()+".xml"));
	            	if(file.exists()){
						file.delete();
					}
	            	form.getObj().setCunZai("0");
					this.booksService.updateBooks(book, book.getId(), properties);
					form.setIsSuccess("true");
					form.setMsg(Lang.getLanguage("Employee.info.update.success",request.getSession().getAttribute("lang").toString()));//修改成功!
	             }
			}catch(Exception e){
				e.printStackTrace();
				form.setIsSuccess("false");
				form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			}
			model.put("form", form);
			return new ModelAndView(forwardString,model);
		}
		
       /**
		 * 生成XML信息
		 * 
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="/form/addxml")
		public ModelAndView addxml(@RequestParam(value = "id", required = false) String id, HttpServletRequest request, BooksForm form) throws Exception {
			try {
				Books books = this.booksService.getBooks(id);
				
				
				if ("0".equals(books.getCunZai())) {
					//XML存储路径
					String path = new StringBuffer(getUploadPath()).append(File.separator).toString();
					//String path=ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.BOOKXML+File.separator+books.getIsbn()+File.separator;
					books.setFilePath(path);
					System.err.println(path);
					//判断路径是否存在
					File file =new File(path);
					if(!file.exists()){
						file.mkdirs();
					}
					Document document = DocumentHelper.createDocument();
					Element root = document.addElement("books");
					Element comment = root.addElement("comment");
					comment.setText("");
					Element book = root.addElement("book");
					Element orderNo = book.addElement("orderNo");
					orderNo.setText(String.valueOf("".equals(books.getOrderNo()) ?  0 : books.getOrderNo()));
					Element title = book.addElement("title");
					title.setText(String.valueOf("".equals(books.getTitle()) ?  0 : books.getTitle()));
					Element author = book.addElement("author");
					author.setText(String.valueOf("".equals(books.getAuthor()) ?  0 : books.getAuthor()));
					Element ISBN = book.addElement("ISBN");
					ISBN.setText(String.valueOf("".equals(books.getIsbn()) ?  0 : books.getIsbn()));
					Element publishDate = book.addElement("publishDate");
					publishDate.setText(String.valueOf("".equals(books.getPublishDate()) ?  0 : books.getPublishDate()));
					Element price = book.addElement("price");
					price.setText(String.valueOf("".equals(books.getPrice()) ?  0 : books.getPrice()));
					Element onPrice = book.addElement("onPrice");
					onPrice.setText(String.valueOf("".equals(books.getOnPrice()) ?  0 : books.getOnPrice()));
					Element offPrice = book.addElement("offPrice");
					offPrice.setText(String.valueOf("".equals(books.getOffPrice()) ?  0 : books.getOffPrice()));
					Element publisher = book.addElement("publisher");
					publisher.setText(String.valueOf("".equals(books.getPublisher()) ?  0 : books.getPublisher()));
					Element publisher_loc = book.addElement("publisher_loc");
					publisher_loc.setText(String.valueOf("".equals(books.getLocation()) ?  0 : books.getLocation()));
					Element edition = book.addElement("edition");
					edition.setText(String.valueOf("".equals(books.getEdition()) ?  0 : books.getEdition()));
					Element pages = book.addElement("pages");
					pages.setText(String.valueOf("".equals(books.getPage()) ?  0 : books.getPage()));
					Element frame = book.addElement("frame");
					frame.setText(String.valueOf("".equals(books.getFrame()) ?  0 : books.getFrame()));
					Element format = book.addElement("format");
					format.setText(String.valueOf("".equals(books.getFormat()) ?  0 : books.getFormat()));
					Element sheet = book.addElement("sheet");
					sheet.setText(String.valueOf("".equals(books.getSheet()) ?  0 : books.getSheet()));
					Element cover = book.addElement("cover");
					cover.setText(String.valueOf("".equals(books.getCover()) ?  0 : books.getCover()));
					Element URL = book.addElement("URL");
					URL.setText(String.valueOf("".equals(books.getUrl()) ?  0 : books.getUrl()));
					Element introduction = book.addElement("introduction");
					introduction.setText(String.valueOf("".equals(books.getIntroduction()) ?  0 : books.getIntroduction()));
					
					try{
						Writer write = new OutputStreamWriter(new FileOutputStream(new File(path + File.separator + books.getIsbn() + ".xml")),"UTF-8");
						XMLWriter xmlWriter = new XMLWriter(write,OutputFormat.createPrettyPrint());
						xmlWriter.write(document);
						xmlWriter.close();
						books.setCunZai("1");
						this.booksService.updateBooks(books, form.getId(), null);
						form.setMsg(Lang.getLanguage("Pages.books.createxml.success",request.getSession().getAttribute("lang").toString()));
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}else{
					 form.setIsSuccess("true");
					 form.setMsg(Lang.getLanguage("Pages.books.yichuangjian.bunengjian",request.getSession().getAttribute("lang").toString()));
				}
				
//				form.setMsg(Lang.getLanguage("msg.info.produce.success", request.getSession().getAttribute("lang").toString()));
			} catch (Exception e) {
				e.printStackTrace();
				form.setMsg((e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
			}
			model.put("form", form);
			return manager(request, null, form);
		}
		
		
		@RequestMapping(value="/form/shareSina")
		public ModelAndView shareSina(HttpServletRequest request,HttpServletResponse response,BooksForm form) throws Exception {
			try{
				String[] arrids = request.getParameterValues("subBox");
				//查询待发送微博的数据对象
				for (int i = 0; i < arrids.length; i++) {
					StringBuffer msg = new StringBuffer("");
					Books book = this.booksService.getBooks(arrids[i]);
					//组装msg消息
					msg.append("书名："); 
					msg.append(book.getTitle());
					
					msg.append("作者："); 
					msg.append(book.getAuthor());
					
					msg.append("出版社："); 
					msg.append(book.getPublisher());
					
					msg.append("价格："); 
					msg.append(book.getPrice());
					
					msg.append("链接："); 
					msg.append(book.getUrl());
					
					String msgConten = msg.toString();
					File file = new File(ProcessQueue.WEBROOT+File.separator+ProcessQueue.BOOKIMG+File.separator+book.getCover());
					System.out.println(file.toString());
					if(file.exists()){
						UpdateStatus.sentSinaWeiBo(msgConten,file);
					}else{
						UpdateStatus.sentSinaWeiBo(msgConten);
					}
				}
				//组装发送新浪微博信息
				form.setMsg(Lang.getLanguage("Pages.books.send.success",request.getSession().getAttribute("lang").toString()));
				form.setIsSuccess("true");
			}catch(Exception e){
				form.setIsSuccess("false");
				form.setMsg((e instanceof CcsException)?Lang.getLanguage(((CcsException)e).getPrompt(),request.getSession().getAttribute("lang").toString()):e.getMessage());
			}
			model.put("form", form);
			return this.manager(request,response,form);
		}
		
		@RequestMapping(value="/form/callBack")
		public ModelAndView callBack(HttpServletRequest request,HttpServletResponse response,BooksForm form) throws Exception {
			try {
				System.out.println(request.getParameter("code"));
				UpdateStatus.savaAccess_token(request.getParameter("code"));
				form.setMsg(Lang.getLanguage("Pages.books.shou.success",request.getSession().getAttribute("lang").toString()));
			} catch (Exception e) {
				form.setMsg(Lang.getLanguage("Pages.books.shou.shibei",request.getSession().getAttribute("lang").toString()));
				// TODO: handle exception
			}
			return new ModelAndView("accessSuccess",model);
		}
		
		@RequestMapping(value ="/form/userXmlScan")
		public void xmlView(HttpServletRequest request,BooksForm form,HttpServletResponse response) {
			String extension = FilenameUtils.getExtension(form.getAction());
			try {
				//冲数据库中读取
				Books books = this.booksService.getBooks(request.getParameter("id"));

			     response.reset(); 
			     //response.setContentType("Application/x-msdownload;charset=utf-8");    
			     response.setContentType("application/octet-stream");
			     boolean IEVersion6_0 = (request.getHeader("User-Agent").indexOf("MSIE 6.0")>0);    
			     String filename = books.getIsbn()+ ".xml";
			     if(IEVersion6_0){                   
			        response.addHeader("Content-Disposition", "attachment;filename="+filename);
			     }else{
			        response.addHeader("Content-Disposition", "filename="+filename);           
			     }        
			     ServletOutputStream  os  = response.getOutputStream();
			     FileInputStream fis = new FileInputStream(new File(books.getFilePath()+books.getIsbn()+ ".xml"));
			     int size=fis.available();
			     byte[] bb=new byte[size];
			     while(fis.read(bb)!=-1){
			       os.write(bb);
			     }
			     fis.close(); 
			     os.flush(); 
			     os.close(); 
			     response.setStatus(HttpServletResponse.SC_OK ); 
			     response.flushBuffer();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 建工社生成XML信息
		 * 
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="/form/userXml")
		public ModelAndView userXml(HttpServletRequest request,HttpServletResponse response, BooksForm form) throws Exception {
			try {
				String usersId = form.getArr_select();
				String [] lArray = usersId.split(",");
				for (int i = 0; i < lArray.length; i++) {
					Books books = this.booksService.getBooks(lArray[i]);
					
					if ("0".equals(books.getCunZai())) {
						//XML存储路径
						String path=ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.PUSHHOME+File.separator+books.getIsbn()+File.separator;
						books.setFilePath(path);
						//判断路径是否存在
						File file =new File(path);
						if(!file.exists()){
							file.mkdirs();
						}
						//============开始拷贝图片进入文件夹==========
						//获取图片的所有路径
						String temp = ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.BOOKXML+File.separator+books.getCover().toString();
						// 将每个文件拷贝到compress下的时间文件夹中
						File copy = new File(temp);
						File copypath = new File(path);
						if (copy.exists()) {
							FileUtils.copyFileToDirectory(copy, copypath);
						}
						//============开始生成xml==================
						Document document = DocumentHelper.createDocument();
						//增加文档类型说明
						Document doctype = document.addDocType("book", "-//NLM//DTD Book DTD v3.0 20070202//EN", "book3.dtd");
						//定义一个root作为xml文档的根元素
						Element root = doctype.addElement("book");
						//加入root属性内容
						root.addAttribute("xml:lang", "en");
						//加入root空间内容
						root.addNamespace("mml", "http://www.w3.org/1998/Math/MathML");
						root.addNamespace("xlink", "http://www.w3.org/1999/xlink");
						root.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
						//加入root属性内容
						root.addAttribute("dtd-version", "3.0");
						//在生成的名称为root的根元素下生成下一级元素标签名称为book-meta
						Element bookmeta = root.addElement("book-meta");
						//在生成的名称为book-meta的根元素下生成下一级元素标签名称为book-id
						Element bookid = bookmeta.addElement("book-id");
						//加入book-id属性内容
						bookid.addAttribute("pub-id-type", "publisher-id");
						//为book-id设置内容
						bookid.setText(String.valueOf("".equals(books.getOrderNo()) ?  0 : books.getOrderNo()));
						//在生成的名称为book-meta的根元素下生成下一级元素标签名称为book-title-group
						Element bookgroup = bookmeta.addElement("book-title-group");
						//在生成的名称为book-title-group的根元素下生成下一级元素标签名称为book-title
						Element booktitle = bookgroup.addElement("book-title");
						//为book-title设置内容
						booktitle.setText(String.valueOf("".equals(books.getTitle()) ?  0 : books.getTitle()));
						//在生成的名称为book-meta的根元素下生成下一级元素标签名称为contrib-group
						Element contribgroup = bookmeta.addElement("contrib-group");
						//判断作者非空||截取名称字符串
						if(null!=(books.getAuthor())){
							if(books.getAuthor().substring(0, 1).equals(",")){
								//在生成的名称为contrib-group的根元素下生成下一级元素标签名称为contrib
								Element contrib = contribgroup.addElement("contrib");
								contrib.addAttribute("contrib-type", "author");
								//在生成的名称为contrib的根元素下生成下一级元素标签名称为name
								Element name = contrib.addElement("name");
								//在生成的名称为name的根元素下生成下一级元素标签名称为surname
								Element surname = name.addElement("surname");
								//为surname设置内容
								surname.setText("");
								//在生成的名称为name的根元素下生成下一级元素标签名称为given-names
								Element givenNames = name.addElement("given-names");
								//为given-names设置内容
								givenNames.setText(books.getAuthor().substring(1, books.getAuthor().length()));
							}else{
								String[] ary = books.getAuthor().split(",");//调用API方法按照逗号分隔字符串
								for(String item: ary){
									//在生成的名称为contrib-group的根元素下生成下一级元素标签名称为contrib
									Element contrib = contribgroup.addElement("contrib");
									contrib.addAttribute("contrib-type", "author");
									//在生成的名称为contrib的根元素下生成下一级元素标签名称为name
									Element name = contrib.addElement("name");
									String arrays[] = item.split(" ");//调用API方法按照空格分隔字符串
									for(int t=0;t<arrays.length;t++){
										if(t==0){
											//在生成的名称为name的根元素下生成下一级元素标签名称为surname
											Element surname = name.addElement("surname");
											//为surname设置内容
											surname.setText(arrays[t]);
										}else{
											//在生成的名称为name的根元素下生成下一级元素标签名称为given-names
											Element givenNames = name.addElement("given-names");
											//为given-names设置内容
											givenNames.setText(arrays[t]);
										}
									}
								 
								}
							}
						}else{
							//在生成的名称为contrib-group的根元素下生成下一级元素标签名称为contrib
							Element contrib = contribgroup.addElement("contrib");
							contrib.addAttribute("contrib-type", "author");
							//在生成的名称为contrib的根元素下生成下一级元素标签名称为name
							Element name = contrib.addElement("name");
							//在生成的名称为name的根元素下生成下一级元素标签名称为surname
							Element surname = name.addElement("surname");
							//为surname设置内容
							surname.setText("");
							//在生成的名称为name的根元素下生成下一级元素标签名称为given-names
							Element givenNames = name.addElement("given-names");
							//为given-names设置内容
							givenNames.setText("");
						}
						//在生成的名称为book-meta的根元素下生成下一级元素标签名称为publisher
						Element publisher = bookmeta.addElement("publisher"); 
						//在生成的名称为publisher的根元素下生成下一级元素标签名称为publisher-name
						Element publisherName = publisher.addElement("publisher-name");
						//为publisher-name设置内容
						publisherName.setText(String.valueOf("".equals(books.getPublisher()) ?  0 : books.getPublisher()));
						//在生成的名称为publisher的根元素下生成下一级元素标签名称为publisher-loc
						Element publisherLoc = publisher.addElement("publisher-loc");
						//为publisher-loc设置内容
						publisherLoc.setText(String.valueOf("".equals(books.getLocation()) ?  0 : books.getLocation()));
						//在生成的名称为book-meta的根元素下生成下一级元素标签名称为isbn
						Element isbn = bookmeta.addElement("isbn");
						//加入isbn属性内容
						isbn.addAttribute("pub-type", "ppub");
						//为isbn设置内容
						isbn.setText(String.valueOf("".equals(books.getIsbn()) ?  0 : books.getIsbn()));
						//在生成的名称为book-meta的根元素下生成下一级元素标签名称为pub-date
						Element pubdate = bookmeta.addElement("pub-date"); 
						//在pub-date标签内部添加新的元素，即pub-date的下一级标签
						SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
						Calendar datetimes = Calendar.getInstance();
						Date datetime=null;
						try {
							datetime = date.parse(books.getPublishDate().toString());
							datetimes.setTime(datetime);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						//加入pub-date属性内容
						pubdate.addAttribute("pub-type", "pub");
						//在生成的名称为pub-date的根元素下生成下一级元素标签名称为day
						Element day = pubdate.addElement("day");
						//为day设置内容
						day.setText(String.valueOf(datetimes.get(Calendar.DAY_OF_MONTH)));
						//在生成的名称为pub-date的根元素下生成下一级元素标签名称为month
						Element month = pubdate.addElement("month");
						//为month设置内容
						month.setText(String.valueOf(datetimes.get(Calendar.MONTH)+1));
						//在生成的名称为pub-date的根元素下生成下一级元素标签名称为year
						Element year = pubdate.addElement("year");
						//为year设置内容
						year.setText(String.valueOf(datetimes.get(Calendar.YEAR)));
						//在生成的名称为root的根元素下生成下一级元素标签名称为body
						Element body = root.addElement("body");
						//在生成的名称为body的根元素下生成下一级元素标签名称为book-part
						Element bookPart = body.addElement("book-part");
						//在生成的名称为book-part的根元素下生成下一级元素标签名称为book-part-meta
						Element bookPartMeta = bookPart.addElement("book-part-meta");
						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为title-group
						Element titleGroup = bookPartMeta.addElement("title-group");
						//在生成的名称为title-group的根元素下生成下一级元素标签名称为title
						Element title = titleGroup.addElement("title");
						//为title设置内容
						title.setText(String.valueOf("".equals(books.getTitle()) ?  0 : books.getTitle()));
						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为volume
						Element volume = bookPartMeta.addElement("volume");
						//为volume设置内容
						volume.setText("1");
						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为issue
						Element issue = bookPartMeta.addElement("issue");
						//为issue设置内容
						issue.setText("1");
						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为fpage
						Element fpage = bookPartMeta.addElement("fpage");
						//为fpage设置内容
						fpage.setText("1");
						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为lpage
						Element lpage = bookPartMeta.addElement("lpage");
						//为lpage设置内容
						lpage.setText(String.valueOf("".equals(books.getPage()) ?  0 : books.getPage()));
						Element cover = bookPartMeta.addElement("cover");
						Element mediaobject = cover.addElement("mediaobject");
						Element imageobject = mediaobject.addElement("imageobject");
						imageobject.addAttribute("condition", "web");
						Element imagedata = imageobject.addElement("imagedata");
						imagedata.addAttribute("fileref", String.valueOf("".equals(books.getCover()) ?  0 : books.getCover()));
						//为self-uri设置内容
						imagedata.addAttribute("format", "GIF");
						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为self-uri
						Element selfUri = bookPartMeta.addElement("self-uri");
						//为self-uri设置内容
						selfUri.addAttribute("xlink:href", String.valueOf("".equals(books.getUrl()) ?  0 : books.getUrl()));
						//为self-uri设置内容
						selfUri.addAttribute("content-type", "pdf");
						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为abstract
						Element tract = bookPartMeta.addElement("abstract");
						//为abstract设置内容
						tract.setText(String.valueOf("".equals(books.getIntroduction()) ?  0 : books.getIntroduction()));
						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为kwd-group
						Element kwdGroup = bookPartMeta.addElement("kwd-group");
						//在生成的名称为kwd-group的根元素下生成下一级元素标签名称为kwd
						Element kwd = kwdGroup.addElement("kwd");
						//为kwd设置内容
						kwd.setText("");
						
						try{
							Writer write = new OutputStreamWriter(new FileOutputStream(new File(path + File.separator + books.getIsbn() + ".xml")),"UTF-8");
							XMLWriter xmlWriter = new XMLWriter(write,OutputFormat.createPrettyPrint());
							xmlWriter.write(document);
							xmlWriter.close();
							books.setCunZai("1");
							this.booksService.updateBooks(books, books.getId(), null);
							form.setMsg("xml生成成功");
						}catch(Exception ex){
							ex.printStackTrace();
						}
						//XML文件包的存储路径
						String compressPath=ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.PUSHHOME+File.separator;
						// 生成zip压缩包
						new FileUtil().compressedFile(copypath.toString(), compressPath.toString());
						//清除相应文件夹
						delFolder(copypath.toString());
					}else{
						 form.setIsSuccess("true");
						 form.setMsg("xml已创建，不能再创建");//模板修改成功!
					}
				}
				
//				form.setMsg(Lang.getLanguage("msg.info.produce.success", request.getSession().getAttribute("lang").toString()));
			} catch (Exception e) {
				e.printStackTrace();
				form.setMsg((e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
			}
			model.put("form", form);
			return manager(request, null, form);
		}
		
//		/**
//		 * 中图生成XML信息
//		 * 
//		 * @param request
//		 * @return
//		 * @throws Exception
//		 */
//		@RequestMapping(value="/form/userXml")
//		public ModelAndView userXml(HttpServletRequest request,HttpServletResponse response, BooksForm form) throws Exception {
//			try {
//				String usersId = form.getArr_select();
//				String [] lArray = usersId.split(",");
//				for (int i = 0; i < lArray.length; i++) {
//					Books books = this.booksService.getBooks(lArray[i]);
//					
//					if ("0".equals(books.getCunZai())) {
//						//XML存储路径
//						String path=ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.PUSHHOME+File.separator+books.getIsbn()+File.separator;
//						books.setFilePath(path);
//						//判断路径是否存在
//						File file =new File(path);
//						if(!file.exists()){
//							file.mkdirs();
//						}
//						//============开始拷贝图片进入文件夹==========
//						//获取图片的所有路径
//						String temp = ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.BOOKXML+File.separator+books.getCover().toString();
//						// 将每个文件拷贝到compress下的时间文件夹中
//						File copy = new File(temp);
//						File copypath = new File(path);
//						if (copy.exists()) {
//							FileUtils.copyFileToDirectory(copy, copypath);
//						}
//						//============开始生成xml==================
//						Document document = DocumentHelper.createDocument();
//						//增加文档类型说明
//						Document doctype = document.addDocType("book", "-//NLM//DTD Book DTD v3.0 20070202//EN", "book3.dtd");
//						//定义一个root作为xml文档的根元素
//						Element root = doctype.addElement("book");
//						//加入root属性内容
//						root.addAttribute("xml:lang", "en");
//						//加入root空间内容
//						root.addNamespace("mml", "http://www.w3.org/1998/Math/MathML");
//						root.addNamespace("xlink", "http://www.w3.org/1999/xlink");
//						root.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
//						//加入root属性内容
//						root.addAttribute("dtd-version", "3.0");
//						//在生成的名称为root的根元素下生成下一级元素标签名称为book-meta
//						Element bookmeta = root.addElement("book-meta");
//						//在生成的名称为book-meta的根元素下生成下一级元素标签名称为book-id
//						Element bookid = bookmeta.addElement("book-id");
//						//加入book-id属性内容
//						bookid.addAttribute("pub-id-type", "publisher-id");
//						//为book-id设置内容
//						bookid.setText(String.valueOf("".equals(books.getOrderNo()) ?  0 : books.getOrderNo()));
//						//在生成的名称为book-meta的根元素下生成下一级元素标签名称为book-title-group
//						Element bookgroup = bookmeta.addElement("book-title-group");
//						//在生成的名称为book-title-group的根元素下生成下一级元素标签名称为book-title
//						Element booktitle = bookgroup.addElement("book-title");
//						//为book-title设置内容
//						booktitle.setText(String.valueOf("".equals(books.getTitle()) ?  0 : books.getTitle()));
//						//在生成的名称为book-meta的根元素下生成下一级元素标签名称为contrib-group
//						Element contribgroup = bookmeta.addElement("contrib-group");
//						//判断作者非空||截取名称字符串
//						if(null!=(books.getAuthor())){
//							if(books.getAuthor().substring(0, 1).equals(",")){
//								//在生成的名称为contrib-group的根元素下生成下一级元素标签名称为contrib
//								Element contrib = contribgroup.addElement("contrib");
//								contrib.addAttribute("contrib-type", "author");
//								//在生成的名称为contrib的根元素下生成下一级元素标签名称为name
//								Element name = contrib.addElement("name");
//								//在生成的名称为name的根元素下生成下一级元素标签名称为surname
//								Element surname = name.addElement("surname");
//								//为surname设置内容
//								surname.setText("");
//								//在生成的名称为name的根元素下生成下一级元素标签名称为given-names
//								Element givenNames = name.addElement("given-names");
//								//为given-names设置内容
//								givenNames.setText(books.getAuthor().substring(1, books.getAuthor().length()));
//							}else{
//								String[] ary = books.getAuthor().split(",");//调用API方法按照逗号分隔字符串
//								for(String item: ary){
//									//在生成的名称为contrib-group的根元素下生成下一级元素标签名称为contrib
//									Element contrib = contribgroup.addElement("contrib");
//									contrib.addAttribute("contrib-type", "author");
//									//在生成的名称为contrib的根元素下生成下一级元素标签名称为name
//									Element name = contrib.addElement("name");
//									String arrays[] = item.split(" ");//调用API方法按照空格分隔字符串
//									for(int t=0;t<arrays.length;t++){
//										if(t==0){
//											//在生成的名称为name的根元素下生成下一级元素标签名称为surname
//											Element surname = name.addElement("surname");
//											//为surname设置内容
//											surname.setText(arrays[t]);
//										}else{
//											//在生成的名称为name的根元素下生成下一级元素标签名称为given-names
//											Element givenNames = name.addElement("given-names");
//											//为given-names设置内容
//											givenNames.setText(arrays[t]);
//										}
//									}
//								 
//								}
//							}
//						}else{
//							//在生成的名称为contrib-group的根元素下生成下一级元素标签名称为contrib
//							Element contrib = contribgroup.addElement("contrib");
//							contrib.addAttribute("contrib-type", "author");
//							//在生成的名称为contrib的根元素下生成下一级元素标签名称为name
//							Element name = contrib.addElement("name");
//							//在生成的名称为name的根元素下生成下一级元素标签名称为surname
//							Element surname = name.addElement("surname");
//							//为surname设置内容
//							surname.setText("");
//							//在生成的名称为name的根元素下生成下一级元素标签名称为given-names
//							Element givenNames = name.addElement("given-names");
//							//为given-names设置内容
//							givenNames.setText("");
//						}
//						//在生成的名称为book-meta的根元素下生成下一级元素标签名称为publisher
//						Element publisher = bookmeta.addElement("publisher"); 
//						//在生成的名称为publisher的根元素下生成下一级元素标签名称为publisher-name
//						Element publisherName = publisher.addElement("publisher-name");
//						//为publisher-name设置内容
//						publisherName.setText("cnpiec");
//						//在生成的名称为publisher的根元素下生成下一级元素标签名称为publisher-loc
//						Element publisherLoc = publisher.addElement("publisher-loc");
//						//为publisher-loc设置内容
//						publisherLoc.setText("cnpiec");
//						//在生成的名称为book-meta的根元素下生成下一级元素标签名称为isbn
//						Element isbn = bookmeta.addElement("isbn");
//						//加入isbn属性内容
//						isbn.addAttribute("pub-type", "ppub");
//						//为isbn设置内容
//						isbn.setText(String.valueOf("".equals(books.getIsbn()) ?  0 : books.getIsbn()));
//						//在生成的名称为book-meta的根元素下生成下一级元素标签名称为pub-date
//						Element pubdate = bookmeta.addElement("pub-date"); 
//						//在pub-date标签内部添加新的元素，即pub-date的下一级标签
//						SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//						Calendar datetimes = Calendar.getInstance();
//						Date datetime=null;
//						try {
//							datetime = date.parse(books.getPublishDate().toString());
//							datetimes.setTime(datetime);
//						} catch (ParseException e1) {
//							e1.printStackTrace();
//						}
//						//加入pub-date属性内容
//						pubdate.addAttribute("pub-type", "pub");
//						//在生成的名称为pub-date的根元素下生成下一级元素标签名称为day
//						Element day = pubdate.addElement("day");
//						//为day设置内容
//						day.setText(String.valueOf(datetimes.get(Calendar.DAY_OF_MONTH)));
//						//在生成的名称为pub-date的根元素下生成下一级元素标签名称为month
//						Element month = pubdate.addElement("month");
//						//为month设置内容
//						month.setText(String.valueOf(datetimes.get(Calendar.MONTH)+1));
//						//在生成的名称为pub-date的根元素下生成下一级元素标签名称为year
//						Element year = pubdate.addElement("year");
//						//为year设置内容
//						year.setText(String.valueOf(datetimes.get(Calendar.YEAR)));
//						//在生成的名称为root的根元素下生成下一级元素标签名称为body
//						Element body = root.addElement("body");
//						//在生成的名称为body的根元素下生成下一级元素标签名称为book-part
//						Element bookPart = body.addElement("book-part");
//						//在生成的名称为book-part的根元素下生成下一级元素标签名称为book-part-meta
//						Element bookPartMeta = bookPart.addElement("book-part-meta");
//						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为title-group
//						Element titleGroup = bookPartMeta.addElement("title-group");
//						//在生成的名称为title-group的根元素下生成下一级元素标签名称为title
//						Element title = titleGroup.addElement("title");
//						//为title设置内容
//						title.setText(String.valueOf("".equals(books.getTitle()) ?  0 : books.getTitle())+" - "+String.valueOf("".equals(books.getPublisher()) ?  0 : books.getPublisher()));
//						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为volume
//						Element volume = bookPartMeta.addElement("volume");
//						//为volume设置内容
//						volume.setText("1");
//						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为issue
//						Element issue = bookPartMeta.addElement("issue");
//						//为issue设置内容
//						issue.setText("1");
//						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为fpage
//						Element fpage = bookPartMeta.addElement("fpage");
//						//为fpage设置内容
//						fpage.setText("1");
//						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为lpage
//						Element lpage = bookPartMeta.addElement("lpage");
//						//为lpage设置内容
//						lpage.setText(String.valueOf("".equals(books.getPage()) ?  0 : books.getPage()));
//						Element cover = bookPartMeta.addElement("cover");
//						Element mediaobject = cover.addElement("mediaobject");
//						Element imageobject = mediaobject.addElement("imageobject");
//						imageobject.addAttribute("condition", "web");
//						Element imagedata = imageobject.addElement("imagedata");
//						imagedata.addAttribute("fileref","0.gif");
//						//为self-uri设置内容
//						imagedata.addAttribute("format", "GIF");
//						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为self-uri
//						Element selfUri = bookPartMeta.addElement("self-uri");
//						//为self-uri设置内容
//						selfUri.addAttribute("xlink:href", String.valueOf("".equals(books.getUrl()) ?  0 : books.getUrl()));
//						//为self-uri设置内容
//						selfUri.addAttribute("content-type", "pdf");
//						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为abstract
//						Element tract = bookPartMeta.addElement("abstract");
//						//为abstract设置内容
//						tract.setText(String.valueOf("".equals(books.getIntroduction()) ?  0 : books.getIntroduction()));
//						//在生成的名称为book-part-meta的根元素下生成下一级元素标签名称为kwd-group
//						Element kwdGroup = bookPartMeta.addElement("kwd-group");
//						//在生成的名称为kwd-group的根元素下生成下一级元素标签名称为kwd
//						Element kwd = kwdGroup.addElement("kwd");
//						//为kwd设置内容
//						kwd.setText("");
//						
//						try{
//							Writer write = new OutputStreamWriter(new FileOutputStream(new File(path + File.separator + books.getIsbn() + ".xml")),"UTF-8");
//							XMLWriter xmlWriter = new XMLWriter(write,OutputFormat.createPrettyPrint());
//							xmlWriter.write(document);
//							xmlWriter.close();
//							books.setCunZai("1");
//							this.booksService.updateBooks(books, books.getId(), null);
//							form.setMsg("xml生成成功");
//						}catch(Exception ex){
//							ex.printStackTrace();
//						}
//						//XML文件包的存储路径
//						String compressPath=ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.PUSHHOME+File.separator;
//						// 生成zip压缩包
//						new FileUtil().compressedFile(copypath.toString(), compressPath.toString());
//						//清除相应文件夹
//						delFolder(copypath.toString());
//					}else{
//						 form.setIsSuccess("true");
//						 form.setMsg("xml已创建，不能再创建");//模板修改成功!
//					}
//				}
////				form.setMsg(Lang.getLanguage("msg.info.produce.success", request.getSession().getAttribute("lang").toString()));
//			} catch (Exception e) {
//				e.printStackTrace();
//				form.setMsg((e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
//			}
//			model.put("form", form);
//			return manager(request, null, form);
//		}
		
		/**
		 * 删除新增时产生的临时文件夹
		 * 
		 * @param request
		 * @param form
		 * @return
		 * @throws Exception
		 */
		public void delFolder(String folderPath) {
			try {
				delAllFile(folderPath); // 删除完里面所有内容
				String filePath = folderPath;
				filePath = filePath.toString();
				java.io.File myFilePath = new java.io.File(filePath);
				myFilePath.delete(); //删除空文件夹
			} catch (Exception e) {
				log.error("删除临时文件出错----------------");
			}
		}
		
		/**
		 * 删除新增时产生的临时文件夹里的内容
		 * 
		 * @param request
		 * @param form
		 * @return
		 * @throws Exception
		 */
		public  boolean delAllFile(String path) {
	        boolean flag = false;
	        File file = new File(path);
	        if (!file.exists()) {
	          return flag;
	        }
	        if (!file.isDirectory()) {
	          return flag;
	        }
	        String[] tempList = file.list();
	        File temp = null;
	        for (int i = 0; i < tempList.length; i++) {
	           if (path.endsWith(File.separator)) {
	              temp = new File(path + tempList[i]);
	           } else {
	               temp = new File(path + File.separator + tempList[i]);
	           }
	           try {
	                if (temp.isFile()||javax.imageio.ImageIO.read(temp)!=null) {
	                    temp.delete();
	                }
	           } catch (IOException e) {
		                log.error("删除临时文件出错----------------");
	           }
	           if (temp.isDirectory()) {
	              delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	              delFolder(path + "/" + tempList[i]);//再删除空文件夹
	              flag = true;
	           }
	        }
	        return flag;
	    }
		
//		/**
//		 * 生成建工社xml
//		 * @param request
//		 * @param response
//		 * @param form
//		 * @return
//		 * @throws Exception
//		 */
//		@RequestMapping(value="/form/userXml")
//		public ModelAndView userXml(HttpServletRequest request,HttpServletResponse response, BooksForm form)throws Exception {
//			HashMap<String,Object> model = new HashMap<String,Object>();
//			//String forwardString="msg";
//			String usersId = form.getArr_select();
//			String [] lArray = usersId.split(","); 
//			//用户email
//			try {
//				
//				
//				Books booksoo = this.booksService.getBooks(lArray[0]);
//				Document document = DocumentHelper.createDocument();
//				Element root = document.addElement("books");
//				Element comment = root.addElement("comment");
//				comment.setText("");
//				//String path=ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.BOOKXML+File.separator+bookso.getIsbn()+File.separator;
//				String path=ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.BOOKXML+File.separator;
//			for (int i = 0; i < lArray.length; i++) {
//				Books bookso = this.booksService.getBooks(lArray[i]);
//					//判断路径是否存在
//					File file =new File(path);
//					if(!file.exists()){
//						file.mkdirs();
//					}
//					Element book = root.addElement("book");
//					Element orderNo = book.addElement("orderNo");
//					orderNo.setText(String.valueOf("".equals(bookso.getOrderNo()) ?  0 : bookso.getOrderNo()));
//					Element title = book.addElement("title");
//					title.setText(String.valueOf("".equals(bookso.getTitle()) ?  0 : bookso.getTitle()));
//					if(null!=(bookso.getAuthor())){
//						String arrays[] = bookso.getAuthor().split(" ");//调用API方法按照空格分隔字符串
//						for(int t=0;t<arrays.length;t++){
//								if(t==0){
//									Element author = book.addElement("author_surname");
//									author.setText(arrays[t]);
//								}else{
//									Element author = book.addElement("author_givename");
//									author.setText(arrays[t]);
//								}
//						}
//					
//					}
//					Element ISBN = book.addElement("ISBN");
//					ISBN.setText(String.valueOf("".equals(bookso.getIsbn()) ?  0 : bookso.getIsbn()));
//					SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//					Calendar datetimes = Calendar.getInstance();
//					Date datetime=null;
//					try {
//						datetime = date.parse(bookso.getPublishDate().toString());
//						datetimes.setTime(datetime);
//					} catch (ParseException e1) {
//						e1.printStackTrace();
//					}
//					Element publishDate = book.addElement("publishDate_day");
//					publishDate.setText(String.valueOf(datetimes.get(Calendar.DAY_OF_MONTH)));
//					Element publishDate1 = book.addElement("publishDate_month");
//					publishDate1.setText(String.valueOf(datetimes.get(Calendar.MONTH)+1));
//					Element publishDate2 = book.addElement("publishDate_year");
//					publishDate2.setText(String.valueOf(datetimes.get(Calendar.YEAR)));
//					Element price = book.addElement("price");
//					price.setText(String.valueOf("".equals(bookso.getPrice()) ?  0 : bookso.getPrice()));
//					Element onPrice = book.addElement("onPrice");
//					onPrice.setText(String.valueOf("".equals(bookso.getOnPrice()) ?  0 : bookso.getOnPrice()));
//					Element offPrice = book.addElement("offPrice");
//					offPrice.setText(String.valueOf("".equals(bookso.getOffPrice()) ?  0 : bookso.getOffPrice()));
//					Element publisher = book.addElement("publisher");
//					publisher.setText(String.valueOf("".equals(bookso.getPublisher()) ?  0 : bookso.getPublisher()));
//					Element publisher_loc = book.addElement("publisher_loc");
//					publisher_loc.setText(String.valueOf("".equals(bookso.getLocation()) ?  0 : bookso.getLocation()));
//					Element edition = book.addElement("edition");
//					edition.setText(String.valueOf("".equals(bookso.getEdition()) ?  0 : bookso.getEdition()));
//					Element pages = book.addElement("pages");
//					pages.setText(String.valueOf("".equals(bookso.getPage()) ?  0 : bookso.getPage()));
//					Element frame = book.addElement("frame");
//					frame.setText(String.valueOf("".equals(bookso.getFrame()) ?  0 : bookso.getFrame()));
//					Element format = book.addElement("format");
//					format.setText(String.valueOf("".equals(bookso.getFormat()) ?  0 : bookso.getFormat()));
//					Element sheet = book.addElement("sheet");
//					sheet.setText(String.valueOf("".equals(bookso.getSheet()) ?  0 : bookso.getSheet()));
//					Element cover = book.addElement("cover");
//					cover.setText(String.valueOf("".equals(bookso.getCover()) ?  0 : bookso.getCover()));
//					Element URL = book.addElement("URL");
//					URL.setText(String.valueOf("".equals(bookso.getUrl()) ?  0 : bookso.getUrl()));
//					Element introduction = book.addElement("introduction");
//					introduction.setText(String.valueOf("".equals(bookso.getIntroduction()) ?  0 : bookso.getIntroduction()));
//					
//					
//					
//				}
//			Writer write = new OutputStreamWriter(new FileOutputStream(new File(path + File.separator + "user.xml")),"UTF-8");
//						XMLWriter xmlWriter = new XMLWriter(write,OutputFormat.createPrettyPrint());
//						xmlWriter.write(document);
//						xmlWriter.close();
//						form.setMsg(Lang.getLanguage("Pages.books.createxml.success",request.getSession().getAttribute("lang").toString()));
//		} catch (Exception e) {
//			e.printStackTrace();
//			form.setMsg((e instanceof CcsException) ? Lang.getLanguage(((CcsException) e).getPrompt(), request.getSession().getAttribute("lang").toString()) : e.getMessage());
//		}
//		
//		model.put("form", form);
//		return manager(request, null, form);
//	}
		
		
	/**
	 * 自动生成xml文件	
	 * @param books
	 */
	public void createXml(Books books){
			//XML存储路径
			//String path = new StringBuffer(getUploadPath()).append(File.separator).toString();
			String path=ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT+File.separator+ProcessQueue.BOOKXML;
			books.setFilePath(path);
			System.err.println(path);
			//判断路径是否存在
			File file =new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("books");
			Element comment = root.addElement("comment");
			comment.setText("");
			Element book = root.addElement("book");
			Element orderNo = book.addElement("orderNo");
			orderNo.setText(String.valueOf("".equals(books.getOrderNo()) ?  0 : books.getOrderNo()));
			Element title = book.addElement("title");
			title.setText(String.valueOf("".equals(books.getTitle()) ?  0 : books.getTitle()));
			Element author = book.addElement("author");
			if(null!=(books.getAuthor())){
				if(books.getAuthor().substring(0, 1).equals(",")){
					author.setText(String.valueOf("".equals(books.getAuthor().substring(1, books.getAuthor().length())) ?  0 : books.getAuthor().substring(1, books.getAuthor().length())));
				}else{
					author.setText(String.valueOf("".equals(books.getAuthor()) ?  0 : books.getAuthor()));
				}
			}else{
				author.setText("");
			}
			Element ISBN = book.addElement("ISBN");
			ISBN.setText(String.valueOf("".equals(books.getIsbn()) ?  0 : books.getIsbn()));
			String publishDateStr = String.valueOf("".equals(books.getPublishDate()) ?  0 : books.getPublishDate());
			Element publishDate = book.addElement("publishDate");
			Element year  = publishDate.addElement("year");
			Element month  = publishDate.addElement("month");
			Element day  = publishDate.addElement("day");
			if(publishDateStr!="0" && !publishDateStr.equals("0")){
				String dateStr = publishDateStr.substring(0,publishDateStr.indexOf(" "));
				String[] dateArray = dateStr.split("-");
					year.setText(dateArray[0]);
					month.setText(dateArray[1]);
					day.setText(dateArray[2]);
			}
			Element price = book.addElement("price");
			price.setText(String.valueOf("".equals(books.getPrice()) ?  0 : books.getPrice()));
			Element onPrice = book.addElement("onPrice");
			onPrice.setText(String.valueOf("".equals(books.getOnPrice()) ?  0 : books.getOnPrice()));
			Element offPrice = book.addElement("offPrice");
			offPrice.setText(String.valueOf("".equals(books.getOffPrice()) ?  0 : books.getOffPrice()));
			Element publisher = book.addElement("publisher");
			publisher.setText(String.valueOf("".equals(books.getPublisher()) ?  0 : books.getPublisher()));
			Element publisher_loc = book.addElement("publisher_loc");
			publisher_loc.setText(String.valueOf("".equals(books.getLocation()) ?  0 : books.getLocation()));
			Element edition = book.addElement("edition");
			edition.setText(String.valueOf("".equals(books.getEdition()) ?  0 : books.getEdition()));
	        Element fpage = book.addElement("fpage");
	        fpage.setText("1");
			Element lpage = book.addElement("lpage");
			lpage.setText(String.valueOf("".equals(books.getPage()) ?  0 : books.getPage()));
			Element frame = book.addElement("frame");
			frame.setText(String.valueOf("".equals(books.getFrame()) ?  0 : books.getFrame()));
			Element format = book.addElement("format");
			format.setText(String.valueOf("".equals(books.getFormat()) ?  0 : books.getFormat()));
			Element sheet = book.addElement("sheet");
			sheet.setText(String.valueOf("".equals(books.getSheet()) ?  0 : books.getSheet()));
			Element cover = book.addElement("cover");
			cover.setText(String.valueOf("".equals(books.getCover()) ?  0 : books.getCover()));
			Element URL = book.addElement("URL");
			URL.setText(String.valueOf("".equals(books.getUrl()) ?  0 : books.getUrl()));
			Element introduction = book.addElement("introduction");
			introduction.setText(String.valueOf("".equals(books.getIntroduction()) ?  0 : books.getIntroduction()));
			
			try{
				Writer write = new OutputStreamWriter(new FileOutputStream(new File(path + File.separator + books.getIsbn() + ".xml")),"UTF-8");
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path + File.separator + books.getIsbn() + ".txt")));
				bw.write(books.getIsbn());
				XMLWriter xmlWriter = new XMLWriter(write,OutputFormat.createPrettyPrint());
				xmlWriter.write(document);
				xmlWriter.close();
				books.setCunZai("1");
				bw.flush();
			}catch(Exception ex){
				ex.printStackTrace();
			}
	}
}

