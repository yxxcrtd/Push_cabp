package cn.digitalpublishing.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.OOrder;
import cn.digitalpublishing.service.OrderService;
import cn.digitalpublishing.util.DateFormatUitl;

public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

	@Override
	public Integer getOrderCount(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		try{
			num = this.daoFacade.getOrderDao().getCount(condition);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);
		}
		return num;
	}

	@Override
	public List<OOrder> getOrderPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<OOrder> list = null;
		try{
			list = this.daoFacade.getOrderDao().getPagingList(condition, sort, pageCount, page);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);
		}
		return list;
	}
	
	public OOrder getOrder(String id) throws Exception {
		OOrder obj = null;
		try{
			obj = (OOrder)this.daoFacade.getOrderDao().get(OOrder.class.getName(), id);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);//获取FTP配置信息失败�?
		}
		return obj;
	}
	

	@Override
	public void upload(InputStream inputStream) throws Exception {
		try {
            XSSFWorkbook xwb = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = xwb.getSheetAt(0);
            //循环解析Excel
            for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
            	String msgother="";
            	int r= i+1;
                XSSFRow row = sheet.getRow(i);
                System.out.println(i);
                System.out.println(sheet.getPhysicalNumberOfRows());
                if (row != null) {
                    XSSFCell code = row.getCell(0);
                    XSSFCell name = row.getCell(1);
                    XSSFCell ip = row.getCell(2);
                    XSSFCell salePrice = row.getCell(3);
                    XSSFCell settledPrice = row.getCell(4);
                    XSSFCell quantity = row.getCell(5);
                    XSSFCell purchaser = row.getCell(6);
                    XSSFCell purchaserPhone = row.getCell(7);
                    XSSFCell purchaserAddress = row.getCell(8);
                    XSSFCell contacts = row.getCell(9);
                    XSSFCell phone = row.getCell(10);
                    XSSFCell address = row.getCell(11);
                    XSSFCell paymentMethod = row.getCell(12);
                    XSSFCell email = row.getCell(13);
                    XSSFCell createdby = row.getCell(14);
                    XSSFCell sta = row.getCell(15);
                    XSSFCell createdate = row.getCell(16);
                    //插入产品信息
                    OOrder order = new OOrder();
                    if(address != null&&!"".equals(address.toString())){
                    order.setAddress(address.toString());
                    }
                    if(code != null&&!"".equals(code.toString())){
                    order.setCode(code.toString());
                    }else{
                    	msgother = "Pages.books.dingdan.hao.bukong";
                    	throw new CcsException(msgother);
                    }
                    if(contacts != null&&!"".equals(contacts.toString())){
                    order.setContacts(contacts.toString());
                    }
                    if(ip != null&&!"".equals(ip.toString())){
                    order.setIp(ip.toString());
                    }
                    if(name != null&&!"".equals(name.toString())){
                    order.setName(name.toString());
                    }else{
                    	msgother = "Pages.books.goumai.xingming.bukong";
                    	throw new CcsException(msgother);
                    }
                    if(paymentMethod != null&&!"".equals(paymentMethod.toString())){
                    order.setPaymentMethod(paymentMethod.toString());
                    }
                    if(phone != null&&!"".equals(phone.toString())){
                    order.setPhone(phone.toString());
                    }
                    if(purchaser != null&&!"".equals(purchaser.toString())){
                    order.setPurchaser(purchaser.toString());
                    }
                    if(purchaserAddress != null&&!"".equals(purchaserAddress.toString())){
                    order.setPurchaserAddress(purchaserAddress.toString());
                    }
                    if(purchaserPhone != null&&!"".equals(purchaserPhone.toString())){
                    order.setPurchaserPhone(purchaserPhone.toString());
                    }
                    
                    if(quantity != null&&!"".equals(quantity.toString())){
                    order.setQuantity(Integer.valueOf(quantity.toString()));
                    }else{
                    	msgother = "Pages.books.shu.liang.bukong";
                    	throw new CcsException(msgother);
                    }
               
                    if(salePrice != null&&!"".equals(salePrice.toString())){
                    order.setSalePrice(Double.parseDouble(salePrice.toString()));
                    }
                    if(settledPrice != null&&!"".equals(settledPrice.toString())){
                    order.setSettledPrice(Double.parseDouble(settledPrice.toString()));
                    }
                    if(email != null&&!"".equals(email.toString())){
                    order.setEmail(email.toString());
                    }else{
                    	msgother = "Pages.books.youxiang.bukong";
                    	throw new CcsException(msgother);
                    }
                    if(createdby != null&&!"".equals(createdby.toString())){
                    order.setCreatedby(createdby.toString());
                    }
                    if(sta != null&&!"".equals(sta.toString())){
                    order.setStatuStr(sta.toString());
                    }
                    order.setCreatedon(new Date());
                    if(createdate != null&&!"".equals(createdate.toString())){
                    order.setCreatedon(DateFormatUitl.stringToDateFormat(createdate.toString()));
                    }
                    this.daoFacade.getOrderDao().insert(order);
                }
            }
        } catch (Exception ex) {
            //throw new CcsException("解析Excel出错！");
            throw new CcsException((ex instanceof CcsException) ? ((CcsException) ex).getPrompt() : "Pages.books.Excel.cuo", ex);
        }finally{
        	if(inputStream!=null){
        		
        		inputStream.close();
        	}
        }
	}

	@Override
	public void deleteOrder(String id) throws Exception {
		try {
			daoFacade.getOrderDao().delete(OOrder.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}

	@Override
	public void updateOrder(OOrder obj, String id, String[] properties) throws Exception {
		try {
			daoFacade.getOrderDao().update(obj, OOrder.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}
}
