package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cn.digitalpublishing.util.DateFormatUitl;

@SuppressWarnings("serial")
public class OOrder implements Serializable {
	
	 /**
   	 * 主键
   	 */
   	private String id;

   	/**
   	 * 订单编号,C+yyyyMMddHHmmsss
   	 */
   	private String code;
   
   	/**
   	 * 购买人姓名
   	 */
   	private String name;
   	/**
   	 * 购买IP
   	 */
   	private String ip;

   	/**
   	 * 销售价格
   	 */
   	private Double salePrice;

    /**
   	 * 结算价格
   	 */
   	private Double settledPrice;

   	/**
   	 * 状态 1-待处理 2-处理中 3-处理完成 99-撤销
   	 */
   	private Integer status;
   	private String  statuStr;

   	/**
   	 * 数量
   	 */
   	private Integer quantity;
    private String quantityStr;

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
   	 * 更新人
   	 */
   	private String updatedby;

   	/**
   	 * 更新时间
   	 */
   	private Date updatedon;
   	private String updatedonStr;

   	/**
   	 * 支付类型 1-线下 2-支票 3-alipay
   	 */
   	private Integer payType;

   	/**
   	 * 订单细节
   	 */
   	private Set<OOrderDetail> orderDetails = new HashSet<OOrderDetail>();

    //新增属性
    /**
     *购买人
     */
    private String purchaser;

    /**
     *购买人电话
     */
    private String purchaserPhone;

    /**
     *地址--用于邮寄发票等
     */
    private String purchaserAddress;

    /***********公司的联系方式*********************/
    /**
     *联系人
     */
    private String contacts;

    /**
     *联系人电话
     */
    private String phone;

    /**
     *联系人地址
     */
    private String address;

    /**
     *付款方式
     */
    private String paymentMethod;
    
    
    private String  email;

	public OOrder() {
		//
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getSettledPrice() {
		return settledPrice;
	}

	public void setSettledPrice(Double settledPrice) {
		this.settledPrice = settledPrice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		if(status!=null && !"".equals(status)){
		this.statuStr=String.valueOf(status);
		}
		this.status = status;
	}
	
	public String getStatuStr() {
		return statuStr;
	}

	public void setStatuStr(String statuStr) {
		if(statuStr!=null && !"".equals(statuStr)){
		this.status=Integer.parseInt(statuStr);
		}
		this.statuStr = statuStr;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		if(quantity!=null && !"".equals(quantity)){
		this.quantityStr=String.valueOf(quantity);
		}
		this.quantity = quantity;
	}
	

	public String getQuantityStr() {
		return quantityStr;
	}

	public void setQuantityStr(String quantityStr) {
		if(quantityStr!=null && !"".equals(quantityStr)){
		this.quantity=Integer.parseInt(quantityStr);
		}
		this.quantityStr = quantityStr;
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
		if(createdon!=null && !"".equals(createdon)){
		this.createdonStr=DateFormatUitl.formatDateTime(createdon);
		}
		this.createdon = createdon;
	}
	

	public String getCreatedonStr() {
		return createdonStr;
	}

	public void setCreatedonStr(String createdonStr) {
		if(createdonStr!=null && !"".equals(createdonStr)){
		this.createdon=DateFormatUitl.stringToDate(createdonStr);
		}
		this.createdonStr = createdonStr;
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
		if(updatedon!=null && !"".equals(updatedon)){
		this.updatedonStr=DateFormatUitl.formatDateTime(updatedon);
		}
		this.updatedon = updatedon;
	}
	
	
	public String getUpdatedonStr() {
		return updatedonStr;
	}

	public void setUpdatedonStr(String updatedonStr) {
		if(updatedonStr!=null && !"".equals(updatedonStr)){
		this.updatedon=DateFormatUitl.stringToDate(updatedonStr);
		}
		this.updatedonStr = updatedonStr;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Set<OOrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OOrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getPurchaserPhone() {
		return purchaserPhone;
	}

	public void setPurchaserPhone(String purchaserPhone) {
		this.purchaserPhone = purchaserPhone;
	}

	public String getPurchaserAddress() {
		return purchaserAddress;
	}

	public void setPurchaserAddress(String purchaserAddress) {
		this.purchaserAddress = purchaserAddress;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
	
}
