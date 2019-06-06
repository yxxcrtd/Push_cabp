package cn.digitalpublishing.springmvc.form.pushStaForm;

import java.util.Date;

import cn.digitalpublishing.po.PushSta;
import cn.digitalpublishing.springmvc.form.BaseForm;
import cn.digitalpublishing.util.DateFormatUitl;

public class PushStaForm extends BaseForm{
	
	private PushSta obj = new PushSta();
	
	private String fileName;
	
	private String ftpFileDir;
	
	private Date ftpsDateond;
	private Date ftpsDatetod;
	private String ftpsDateon;
	private String ftpsDateto;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public PushSta getObj() {
		return obj;
	}

	public void setObj(PushSta obj) {
		this.obj = obj;
	}

	public String getFtpFileDir() {
		return ftpFileDir;
	}

	public void setFtpFileDir(String ftpFileDir) {
		this.ftpFileDir = ftpFileDir;
	}

	public String getFtpsDateon() {
		return ftpsDateon;
	}

	public void setFtpsDateon(String ftpsDateon) {
		this.ftpsDateond = DateFormatUitl.stringToDate(ftpsDateon);
		System.out.println(ftpsDateond);
		this.ftpsDateon = ftpsDateon;
		if(this.ftpsDateond!=null){
			this.getCondition().put("ftpsDateond", ftpsDateond);
		}
	}
	
	public String getFtpsDateto() {
		return ftpsDateto;
	}

	public void setFtpsDateto(String ftpsDateto) {
		if(ftpsDateto!=null && ftpsDateto!=""){
			this.ftpsDatetod = DateFormatUitl.stringToDatetime(ftpsDateto+" 23:59:59");
			System.out.println(ftpsDatetod);
			this.ftpsDateto = ftpsDateto;
			this.getCondition().put("ftpsDatetod", ftpsDatetod);
			}
	}
	
	public Date getFtpsDateond() {
		return ftpsDateond;
	}

	public void setFtpsDateond(Date ftpsDateond) {
		this.ftpsDateond = ftpsDateond;
	}

	public Date getFtpsDatetod() {
		return ftpsDatetod;
	}

	public void setFtpsDatetod(Date ftpsDatetod) {
		this.ftpsDatetod = ftpsDatetod;
	}

	
}
