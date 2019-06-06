package cn.digitalpublishing.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.Books;

public interface BooksService extends BaseService {
	
	public Integer getBooksCount(Map<String,Object> condition)throws Exception;
	
	public List<Books> getBooksPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception;
	
	public Books getBooks(String id) throws Exception;
	
	public List<Books> upload(InputStream inputStream) throws Exception ;
	
	public void deleteBooks(String id)throws Exception;
	
	public void updateBooks(Books obj,String id,String[] properties)throws Exception;
	
	public void save(Books books) throws Exception;
	
	public void saveBooks(List<Books> listbooks)throws Exception;
	
	public List<Books> getList(Map<String, Object> condition,String sort) throws Exception;
	

}
