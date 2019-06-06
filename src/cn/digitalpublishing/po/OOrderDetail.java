package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Date;

import cn.digitalpublishing.util.DateFormatUitl;


/**
 * 订单明细
 */
@SuppressWarnings("serial")
public class OOrderDetail  implements Serializable{

	/**
     * 主键
     */
    private String id;

    /**
     * 产品序号 1、2、3、4
     */
    private Integer odetailNum;
    
    /**
     * 创建人
     */
    private String createdby;

    /**
     * 创建时间
     */
    private Date createdon;
    private String createdonStr;
    /**
     * 修改人
     */
    private String updatedby;

    /**
     * 修改时间
     */
    private Date updatedon;
    private String updatedonStr;

    /**
     * 商品名
     */
    private String name;

    /**
     *作者/艺术家
     */
    private String author;

    /**
     *出版社
     */
    private String publisher;

    /**
     *产品ID，根据itemType属性来分别去不同的库中查询
     */
    private String pubid;

    /**
     * 购买IP
     */
    private String ip;

    /**
     *原价
     */
    private Double listPrice;

    /**
     * 销售价格
     */
    private Double salePrice;

    /**
     * 数量
     */
    private Integer quantity;
    private String quantityStr;

    /**
     * 类型 2-图片 3-视频 4-出版物
     */
    private Integer itemType;

    /**
     * 状态 1-未处理 2-待处理 3-处理完成 4-购物车中 99-已撤销
     */
    private Integer status;
    private String  statuStr;
    /**
     * 结算价
     */
    private Double settledPrice;

    /**
     * 每个产品生成一个自己的订单流水号,这个号在点击下单的时候生成
     */
    private String ourCode;

    /**
     * 撤销说明
     */
    private String revocationDesc;

    /**
     * 备注
     */
    private String remark;

    /**
     *折扣
     */
    private Double discount;
    
    

	public OOrderDetail() {
		//
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getOdetailNum() {
		return odetailNum;
	}

	public void setOdetailNum(Integer odetailNum) {
		this.odetailNum = odetailNum;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		if(createdon != null&&!"".equals(createdon)){
		this.createdonStr=DateFormatUitl.formatDateTime(createdon);
		}
		this.createdon = createdon;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Date getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Date updatedon) {
		if(updatedon != null&&!"".equals(updatedon)){
		this.updatedonStr=DateFormatUitl.formatDateTime(updatedon);
		}
		this.updatedon = updatedon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPubid() {
		return pubid;
	}

	public void setPubid(String pubid) {
		this.pubid = pubid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Double getListPrice() {
		return listPrice;
	}

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		if(quantity != null&&!"".equals(quantity)){
		this.quantityStr=String.valueOf(quantity);
		}
		this.quantity = quantity;
	}
	

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		if(status != null&&!"".equals(status)){
		this.statuStr=String.valueOf(status);
		}
		this.status = status;
	}

	public Double getSettledPrice() {
		return settledPrice;
	}

	public void setSettledPrice(Double settledPrice) {
		this.settledPrice = settledPrice;
	}

	public String getOurCode() {
		return ourCode;
	}

	public void setOurCode(String ourCode) {
		this.ourCode = ourCode;
	}

	public String getRevocationDesc() {
		return revocationDesc;
	}

	public void setRevocationDesc(String revocationDesc) {
		this.revocationDesc = revocationDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}


	public String getUpdatedonStr() {
		return updatedonStr;
	}

	public void setUpdatedonStr(String updatedonStr) {
		if(updatedonStr != null&&!"".equals(updatedonStr)){
		this.updatedon=DateFormatUitl.stringToDate(updatedonStr);
		}
		this.updatedonStr = updatedonStr;
	}

	public String getCreatedonStr() {
		return createdonStr;
	}

	public void setCreatedonStr(String createdonStr) {
		if(createdonStr != null&&!"".equals(createdonStr)){
		this.createdon=DateFormatUitl.stringToDate(createdonStr);
		}
		this.createdonStr = createdonStr;
	}
	
	public String getStatuStr() {
		return statuStr;
	}

	public void setStatuStr(String statuStr) {
		if(statuStr != null&&!"".equals(statuStr)){
		this.status=Integer.parseInt(statuStr);
		}
		this.statuStr = statuStr;
	}
	

	public String getQuantityStr() {
		return quantityStr;
	}

	public void setQuantityStr(String quantityStr) {
		if(quantityStr != null&&!"".equals(quantityStr)){
		this.quantity=Integer.parseInt(quantityStr);
		}
		this.quantityStr = quantityStr;
	}

	
}
