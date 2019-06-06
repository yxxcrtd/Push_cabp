package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class PushSta implements Serializable  {
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 当前文件最终状态
	 */
	private String endSta;

	/**
	 * 用户FTP下载开始时间点
	 */
	private Date ftpsDate;
	
	/**
	 * 用户FTP下载结束时间点
	 */
	private Date ftpeDate;


	/**
	 * 推送系统的上传开始时间点
	 */
	private Date tuisDate;

	/**
	 * 推送系统的上传结束时间
	 */
	private Date tuieDate;
	
	/**
	 * 拆分开始时间点
	 */
	private Date caisDate;
	
	/**
	 * 拆分结束时间点
	 */
	private Date caieDate;

	/**
	 * 打包时间
	 */
	private Date dabaoDate;
	
	/**
	 * 推送时间
	 */
	private Date tuiDate;

	/**
	 * 最终推送状态
	 */
	private Integer puSta;
	
	/**
	 * 文件名
	 */
	private String fileName;
	
	private String taskId;
	
	/**
	 * ftp文件夹目录
	 */
	private String ftpFileDir;

	public PushSta() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEndSta() {
		return endSta;
	}

	public void setEndSta(String endSta) {
		this.endSta = endSta;
	}

	public Date getFtpsDate() {
		return ftpsDate;
	}

	public void setFtpsDate(Date ftpsDate) {
		this.ftpsDate = ftpsDate;
	}

	public Date getFtpeDate() {
		return ftpeDate;
	}

	public void setFtpeDate(Date ftpeDate) {
		this.ftpeDate = ftpeDate;
	}

	public Date getTuisDate() {
		return tuisDate;
	}

	public void setTuisDate(Date tuisDate) {
		this.tuisDate = tuisDate;
	}

	public Date getTuieDate() {
		return tuieDate;
	}

	public void setTuieDate(Date tuieDate) {
		this.tuieDate = tuieDate;
	}

	public Date getCaisDate() {
		return caisDate;
	}

	public void setCaisDate(Date caisDate) {
		this.caisDate = caisDate;
	}

	public Date getCaieDate() {
		return caieDate;
	}

	public void setCaieDate(Date caieDate) {
		this.caieDate = caieDate;
	}

	
	public Date getDabaoDate() {
		return dabaoDate;
	}

	public void setDabaoDate(Date dabaoDate) {
		this.dabaoDate = dabaoDate;
	}

	public Date getTuiDate() {
		return tuiDate;
	}

	public void setTuiDate(Date tuiDate) {
		this.tuiDate = tuiDate;
	}

	

	public Integer getPuSta() {
		return puSta;
	}

	public void setPuSta(Integer puSta) {
		this.puSta = puSta;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFtpFileDir() {
		return ftpFileDir;
	}

	public void setFtpFileDir(String ftpFileDir) {
		this.ftpFileDir = ftpFileDir;
	}
	
	
	
}

