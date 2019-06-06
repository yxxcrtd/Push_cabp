package cn.digitalpublishing.po;

public class FtpDirConfigure {
	/**
	 * 主键id 
	 */
	private String id;
	/**
	 * ftpid
	 */
	private String ftpid;
	/**
	 * ftp服务器文件目录
	 */
	private String ftpdir;
	/**
	 * 文件夹名
	 */
	private String dirName;
	/**
	 * 文件夹描述
	 */
	private String description;
	
	/**
	 * ftp配置对象
	 */
	private FtpConfigure ftpConfigure;
	
	public FtpConfigure getFtpConfigure() {
		return ftpConfigure;
	}
	public void setFtpConfigure(FtpConfigure ftpConfigure) {
		this.ftpConfigure = ftpConfigure;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFtpid() {
		return ftpid;
	}
	public void setFtpid(String ftpid) {
		this.ftpid = ftpid;
	}
	public String getFtpdir() {
		return ftpdir;
	}
	public void setFtpdir(String ftpdir) {
		this.ftpdir = ftpdir;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
}
