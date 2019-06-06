package cn.digitalpublishing.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import thredds.filesystem.FileSystemProto.File;
import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.po.OOrderDetail;
import cn.digitalpublishing.service.OrderDetailService;

public class OrderDetailServiceImpl extends BaseServiceImpl implements OrderDetailService {

	@Override
	public Integer getOrderCount(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		try{
			num = this.daoFacade.getOrderDetailDao().getCount(condition);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt()	: "Pages.books.chu.cuo", e);
		}
		return num;
	}

	@Override
	public List<OOrderDetail> getOrderPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {
		List<OOrderDetail> list = null;
		try{
			list = this.daoFacade.getOrderDetailDao().getPagingList(condition, sort, pageCount, page);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"Pages.books.chu.cuo", e);
		}
		return list;
	}

	@Override
	public OOrderDetail getOrder(String id) throws Exception {
		OOrderDetail obj = null;
		try{
			obj = (OOrderDetail)this.daoFacade.getOrderDetailDao().get(OOrderDetail.class.getName(), id);
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
            for (int i = sheet.getFirstRowNum()+1; i <= sheet.getPhysicalNumberOfRows(); i++) {
            	int r=i+1;
            	String msgother="";
                XSSFRow row = sheet.getRow(i);
                if (row != null) {
                	// 产品序号
                    XSSFCell odetailNum= row.getCell(0);
                    //创建人
                    XSSFCell createdby = row.getCell(1);
                    //商品名	
                    XSSFCell name = row.getCell(2);
                    //作者/艺术家
                    XSSFCell author = row.getCell(3);
                	//出版社
                    XSSFCell publisher = row.getCell(4);
                    //	产品ID	
                    XSSFCell pubid = row.getCell(5);
                    //购买IP	
                    XSSFCell ip = row.getCell(6);
                    //原价	
                    XSSFCell listPrice = row.getCell(7);
                    //销售价格	
                    XSSFCell salePrice = row.getCell(8);
                    //数量	
                    XSSFCell quantity = row.getCell(9);
                    //结算价格	
                    XSSFCell settledPrice = row.getCell(10);
                    //订单流水号
                    XSSFCell ourCode = row.getCell(11);
                    //撤销说明	
                    XSSFCell revocationDesc = row.getCell(12);
                    //备注
                    XSSFCell remark = row.getCell(13);
                	//折扣(%)
                    XSSFCell discount = row.getCell(14);

                    //插入产品信息
                    OOrderDetail ord=new OOrderDetail();
                    if(author != null&&!"".equals(author.toString())){
                    ord.setAuthor(author.toString());
                    }
                    if(createdby != null&&!"".equals(createdby.toString())){
                    ord.setCreatedby(createdby.toString());
                    }
                    if(discount != null&&!"".equals(discount.toString())){
                    ord.setDiscount(Double.valueOf(discount.toString()));
                    }
                    if(ip != null&&!"".equals(ip.toString())){
                    ord.setIp(ip.toString());
                    }
                    if(listPrice != null&&!"".equals(listPrice.toString())){
                    ord.setListPrice(Double.parseDouble(listPrice.toString()));
                    }
                    if(name != null&&!"".equals(name.toString())){
                         ord.setName(name.toString());
                    }else{
                    	msgother = "Pages.books.shangpin.bukong";
                    	throw new CcsException(msgother);
                    }
                    
                    if(odetailNum != null&&!"".equals(odetailNum.toString())){
                         ord.setOdetailNum(Integer.parseInt(odetailNum.toString()));
                    }else{
                    	msgother = "Pages.books.chanpin.bianhao.bukong";
                    	throw new CcsException(msgother);
                    }
                    if(ourCode != null&&!"".equals(ourCode.toString())){
                    ord.setOurCode(ourCode.toString());
                    }
                    /*if(pubid != null&&!"".equals(pubid.toString())){
                    ord.setPubid(pubid.toString());
                    }*/
                    
                    if(publisher != null&&!"".equals(publisher.toString())){
                        ord.setPublisher(publisher.toString());
                    }else{
                    	msgother = "Pages.PushOrder.tushu.publisher";
                    	throw new CcsException(msgother);
                    }
                    if(quantity != null&&!"".equals(quantity.toString())){
                    ord.setQuantity(Integer.parseInt(quantity.toString()));
                    }
                    ord.setRemark(String.valueOf("".equals(remark) ?  0 : remark));
                    
                    ord.setRevocationDesc(String.valueOf("".equals(revocationDesc) ?  0 : revocationDesc));
                    if(salePrice != null&&!"".equals(salePrice.toString())){
                    ord.setSalePrice(Double.parseDouble(salePrice.toString()));
                    }
                    if(settledPrice != null&&!"".equals(settledPrice.toString())){
                    ord.setSettledPrice(Double.parseDouble(settledPrice.toString()));
                    }
                    ord.setCreatedon(new Date());
                    ord.setStatus(1);
                    this.daoFacade.getOrderDetailDao().insert(ord);
                    
                    
                }
            }
        } catch (Exception ex) {
        	
            throw new CcsException((ex instanceof CcsException) ? ((CcsException) ex).getPrompt() : "Pages.books.Excel.cuo", ex);
        }
	
		
	}

	@Override
	public void updateOrder(OOrderDetail obj, String id, String[] properties) throws Exception {
		try {
			daoFacade.getOrderDetailDao().update(obj, OOrderDetail.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}

	@Override
	public void deleteOrder(String id) throws Exception {
		try {
			daoFacade.getOrderDetailDao().delete(OOrderDetail.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "Pages.books.chu.cuo", e);
		}
	}
}
