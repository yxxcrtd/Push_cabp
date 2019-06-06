package cn.digitalpublishing.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.Books;
import cn.digitalpublishing.service.BooksService;
import cn.digitalpublishing.util.DateFormatUitl;

public class BooksServiceImpl extends BaseServiceImpl implements BooksService {

	@Override
	public Integer getBooksCount(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		try{
			num = this.daoFacade.getBooksDao().getCount(condition);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);
		}
		return num;
	}

	@Override
	public List<Books> getBooksPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<Books> list = null;
		try{
			list = this.daoFacade.getBooksDao().getPagingList(condition, sort, pageCount, page);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);
		}
		return list;
	}
	
	public Books getBooks(String id) throws Exception {
		// TODO Auto-generated method stub
		Books obj = null;
		try{
			obj = (Books)this.daoFacade.getBooksDao().get(Books.class.getName(), id);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);//获取FTP配置信息失败�?
		}
		return obj;
	}
	
	
	public Books getBooksBySId(String booksSId) throws Exception {
		// TODO Auto-generated method stub
		Books obj = null;
		try{
			obj = (Books)this.daoFacade.getBooksDao().get(Books.class.getName(), booksSId);
			System.out.println(obj.getTitle());
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);//获取FTP配置信息失败�?
		}
		return obj;
	}
	
	@Override
	public List<Books> upload (InputStream inputStream) throws Exception {
		List<Books> listbooks = new ArrayList<Books>();
		try {
            XSSFWorkbook xwb = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = xwb.getSheetAt(0);
            
            //循环解析Excel
            for (int i = sheet.getFirstRowNum()+1; i <= sheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = sheet.getRow(i);
                int r=i+1;
                String msgother ="";
                Books book =null;
                if (row != null) {
                    XSSFCell orderNo = row.getCell(0);
                    XSSFCell title = row.getCell(1);
                    XSSFCell author = row.getCell(2);
                    XSSFCell publishDate = row.getCell(3);
                    XSSFCell isbn = row.getCell(4);
                    XSSFCell price = row.getCell(5);
                    XSSFCell onPrice = row.getCell(6);
                    XSSFCell offPrice = row.getCell(7);
                    XSSFCell publisher = row.getCell(8);
                    XSSFCell edition = row.getCell(9);
                    XSSFCell page = row.getCell(10);
                    XSSFCell frame = row.getCell(11);
                    XSSFCell format = row.getCell(12);
                    XSSFCell Sheet = row.getCell(13);
                    XSSFCell cover = row.getCell(14);
                    XSSFCell url = row.getCell(15);
                  //  XSSFCell pagend = row.getCell(16);
                    XSSFCell category = row.getCell(16);
                    XSSFCell location = row.getCell(17);
                    XSSFCell introduction = row.getCell(18);
                    //插入产品信息
                    book = new Books();
                    if(orderNo != null&&!"".equals(orderNo.toString())){
                    book.setOrderNo(orderNo.toString());
                    }else{
                    	msgother = "Pages.books.zhuangzheng.num";
                    	throw new CcsException(msgother);
                    }
                    
                    if(title != null&&!"".equals(title.toString())){
                    book.setTitle(title.toString());
                    }else{
                    	msgother = "Pages.Content.Prompt.Title";
                    	throw new CcsException(msgother);
                    }
                    
                    
                    if(author != null&&!"".equals(author.toString())){
                    book.setAuthor(author.toString());
                    }
                    if(publishDate != null){
                    book.setPublishDate(DateFormatUitl.stringToDateFormat(publishDate.toString()));
                    }
                    
                    if(isbn != null&&!"".equals(isbn.toString())){
                    	if(listbooks.size()>0){
                    	for(Books b:listbooks){
                         	if(!b.getIsbn().equals(isbn.toString())){
                         		book.setIsbn(isbn.toString());
                         	}else{
                         		msgother = "Pages.books.isbn.buchongfu";
                         		throw new CcsException(msgother);
                         	}
                         }
                    	}else{
                    		book.setIsbn(isbn.toString());
                    	}
                    	
                    }else{
                    	msgother = "Pages.PushOrder.book.isbn";
                    	throw new CcsException(msgother);
                    }
   
                    if(price != null&&!"".equals(price.toString())){
                    book.setPrice(new BigDecimal(price.toString()));
                    }
                    if(onPrice != null&&!"".equals(onPrice.toString())){
                    book.setOnPrice(new BigDecimal(onPrice.toString()));
                    }
                    if(offPrice != null&&!"".equals(offPrice.toString())){
                    book.setOffPrice(new BigDecimal(offPrice.toString()));
                    }
                    if(publisher != null&&!"".equals(publisher.toString())){
                    book.setPublisher(publisher.toString());
                    }else{
                    	msgother = "Pages.PushOrder.tushu.publisher";
                    	throw new CcsException(msgother);
                    }
                    if(edition != null&&!"".equals(edition.toString())){
                    book.setEdition(edition.toString());
                    }
                    if(page != null&&!"".equals(page.toString())){
                    book.setPage(Integer.valueOf(page.toString()));
                    }
                    if(frame != null&&!"".equals(frame.toString())){
                    book.setFrame(frame.toString());
                    }
                    if(format != null&&!"".equals(format.toString())){
                    book.setFormat(format.toString());
                    }
                    if(Sheet != null&&!"".equals(Sheet.toString())){
                    book.setSheet(new BigDecimal(Sheet.toString()));
                    }
                    if(cover != null&&!"".equals(cover.toString())){
                    book.setCover(cover.toString());
                    }
                    if(url != null&&!"".equals(url.toString())){
                    book.setUrl(url.toString());
                    }
                    if(location != null&&!"".equals(location.toString())){
                    book.setLocation(location.toString());
                    }
                 /*   if(pagend != null&&!"".equals(pagend.toString())){
                    book.setPagend(Integer.valueOf(pagend.toString()));
                    }*/
                    if(category != null&&!"".equals(category.toString())){
                    book.setCategory(category.toString());
                    }
                    if(introduction != null&&!"".equals(introduction.toString())){
                    book.setIntroduction(introduction.toString());
                    }
                    book.setCunZai("0");
                    listbooks.add(book);
                }
            }
        } catch (Exception ex) {
        	
        	throw new CcsException((ex instanceof CcsException) ? ((CcsException) ex).getPrompt() : "Pages.books.Excel.cuoexcle", ex);
        	
        }
		return listbooks;
	}

	@Override
	public void deleteBooks(String id) throws Exception {
		try {
			daoFacade.getBooksDao().delete(Books.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}

	@Override
	public void updateBooks(Books obj, String id, String[] properties) throws Exception {
		try {
			daoFacade.getBooksDao().update(obj, Books.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}
	
	@Override
	public void save(Books book) throws Exception {
		try {
			this.daoFacade.getBooksDao().insert(book);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
		
	}

	@Override
	public List<Books> getList(Map<String, Object> condition,String sort) throws Exception {
		List<Books> list;
		try{
			list = this.daoFacade.getBooksDao().getList(condition, sort);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.Excel.cuo", e);
		}
		return list;
	}

	@Override
	public void saveBooks(List<Books> listbooks) throws Exception {
		List<Books> bookList = null;
		try{
        if(listbooks!=null){
            for(Books b:listbooks){
            Map<String,Object> condition = new HashMap<String,Object>();
			 condition.put("isbn", b.getIsbn());
             bookList = this.getList(condition, "");
               if(bookList!=null&&bookList.size()>0){
            	throw new CcsException("Pages.books.buneng.xml.xinagton");
               }else{ 
				 this.daoFacade.getBooksDao().insert(b);
               }
            }
	    }
		}catch(Exception ex){
			throw new CcsException((ex instanceof CcsException) ? ((CcsException) ex).getPrompt() : "Pages.books.Excel.cuo", ex);
		}
	
   }
}