package cn.digitalpublishing.springmvc.controller.books;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.digitalpublishing.po.Books;
import cn.digitalpublishing.springmvc.controller.BaseController;


/**
 * Books接口	主要实现增、删、改功能
 * @author zhouwenqian
 */
@Controller
@RequestMapping("/services")
public class BooksServicesController extends BaseController {
	/**
	 * Add
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="addService")
	public @ResponseBody String addService(@RequestParam(value = "json", required = true)String json) throws Exception {
		String msg = "";
		if (json == null || json.equals("")) {
			msg = "{ 'info' : 'json is null' }";
		}
		//解决中文乱码问题
		byte bb[];
		bb = json.getBytes("ISO-8859-1"); 
		json= new String(bb, "UTF-8"); 
		JSONObject object = JSONObject.fromObject(json);
		String optFlag = object.get("opt").toString();
		if (optFlag.equals("0")) {
			JSONArray bookArray = JSONArray.fromObject(object.get("data"));
			// 拼接where条件
			Map<String, Object> condition = new HashMap<String, Object>();
			for (Object obj : bookArray) {
				
				JSONObject bookJsonObj = JSONObject.fromObject(obj);
				// 获取id
				String orderNo = bookJsonObj.get("id").toString();
				condition.put("orderNo", orderNo);
				// 获取已有books的orderNoList
				List<Books> booksList = this.booksService.getList(condition, null);
				if (booksList.size() != 0) {
					msg = "{ 'status':'Add error','info':'id is repeat'}";
				} else {
					Books book = new Books();
					parseJsonStr(bookJsonObj,book);
					this.booksService.save(book);
					msg =  "{ 'status' : 'Add success', 'info' : 'id is valid' }";
				}
			}
			
		} else {
			msg = "{ 'info' : 'opt is invalid' }";
		}
		return msg;
	}
	/**
	 * Update
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateService")
	public @ResponseBody String updateService(@RequestParam(value = "json", required = true)String json) throws Exception {
		String msg = "";
		if (json == null || json.equals("")) {
			msg = "{ 'info' : 'json is null' }";
		}
		//解决中文乱码问题
		byte bb[];
		bb = json.getBytes("ISO-8859-1"); 
		json= new String(bb, "UTF-8"); 
		JSONObject object = JSONObject.fromObject(json);
		String optFlag = object.get("opt").toString();
		if (optFlag.equals("1")){
			JSONArray bookArray = JSONArray.fromObject(object.get("data"));
			// 拼接where条件
			Map<String, Object> condition = new HashMap<String, Object>();
			for (Object obj : bookArray) {
				JSONObject bookJsonObj = JSONObject.fromObject(obj);
				// 获取id
				String orderNo = bookJsonObj.get("id").toString();
				condition.put("orderNo", orderNo);
				// 获取已有books的orderNoList
				List<Books> booksList = this.booksService.getList(condition, null);
				if (booksList.size() == 0) {
					msg = "{ 'status':'Update error','info':'id is  invalid'}";
				} else {
					for (Books book : booksList) {
						parseJsonStr(bookJsonObj,book);
						this.booksService.updateBooks(book, book.getId(), null);
					}
					msg = "{ 'status':'Update success','info':'id is valid'}";
				}
			}
		}else{
			msg = "{ 'info' : 'opt is invalid' }";
		}
		return msg;
	}
	/**
	 * Delete
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteService")
	public @ResponseBody String deleteService(@RequestParam(value = "json", required = true)String json) throws Exception {
		String msg = "";
		if (json == null || json.equals("")) {
			msg = "{ 'info' : 'json is null' }";
		}
		//解决中文乱码问题
		byte bb[];
		bb = json.getBytes("ISO-8859-1"); 
		json= new String(bb, "UTF-8"); 
		JSONObject object = JSONObject.fromObject(json);
		String optFlag = object.get("opt").toString();
		if (optFlag.equals("2")) {
			JSONArray bookArray = JSONArray.fromObject(object.get("data"));
			// 拼接where条件
			Map<String, Object> condition = new HashMap<String, Object>();
			for (Object obj : bookArray) {
				JSONObject bookJsonObj = JSONObject.fromObject(obj);
				// 获取id
				String orderNo = bookJsonObj.get("id").toString();
				condition.put("orderNo", orderNo);
				// 获取已有books的orderNoList
				List<Books> booksList = this.booksService.getList(condition, null);
				if (booksList.size() == 0) {
					msg = "{ 'status' : 'Delete error' , 'info' : 'id is  invalid' }";
				} else {
					for (Books book : booksList) {
						this.booksService.deleteBooks(book.getId());
					}

					msg = "{ 'status' : 'Delete success' , 'info' : 'id is valid' }";
				}
			}
		}else{
			msg = "{ 'info' : 'opt is invalid' }";
		}
		
		return msg;
	}
	/**
	 * 设置Books对象的各个属性值
	 * @param bookJsonObj
	 * @param book
	 * @throws Exception
	 */
	private void parseJsonStr(JSONObject bookJsonObj,Books book) throws Exception {
		
		//orderNo;title;surname;givenNames;publishDate;isbn;price;onPrice;
    	//offPrice;publisher;location;edition; page;frame;format;sheet;cover;url;

		book.setId(bookJsonObj.get("id").toString());
		book.setTitle(bookJsonObj.get("name").toString());
		book.setAuthor(bookJsonObj.get("author").toString());
		// 字符串转换为日期
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(bookJsonObj.get("publishDate").toString());
		book.setPublishDate(date);
		book.setIsbn(bookJsonObj.get("isbn").toString());
		book.setPrice(new BigDecimal(bookJsonObj.get("price").toString()));
		book.setOnPrice(new BigDecimal(bookJsonObj.get("onPrice").toString()));
		book.setOffPrice(new BigDecimal(bookJsonObj.get("offPrice").toString()));
		book.setPublisher(bookJsonObj.get("publisher").toString());
		book.setEdition(bookJsonObj.get("edition").toString());
		book.setPage(Integer.parseInt(bookJsonObj.get("pages").toString()));
		book.setFrame(bookJsonObj.get("frame").toString());
		book.setFormat(bookJsonObj.get("format").toString());
		book.setSheet(new BigDecimal(bookJsonObj.get("sheet").toString()));
		book.setCover(bookJsonObj.get("cover").toString());
		book.setUrl(bookJsonObj.get("url").toString());
		
	}
}
