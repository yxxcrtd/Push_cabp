package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.FtpConfigure;
import cn.digitalpublishing.po.FtpDirConfigure;
import cn.digitalpublishing.po.TSource;

public interface FtpDirService extends BaseService {
	
	/**
	 * 获取FTP配置
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public FtpDirConfigure getFtpDir(String id)throws Exception;
	/**
	 * 获取分页列表
	 * @param condition
	 * @param sort
	 * @param pageCount
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<FtpDirConfigure> getFtpdirPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception;
	/**
	 * 获取总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public Integer getFtpdirCount(Map<String,Object> condition)throws Exception;
	
	/**
	 * 更新FTP配置
	 * @param obj
	 * @param id
	 * @param properties
	 * @throws Exception
	 */
	public void updateFtpdir(FtpDirConfigure obj,String id,String[] properties)throws Exception;
	
	/**
	 * 新增FTP配置
	 * @param obj
	 * @throws Exception
	 */
	public void addFtpdir(FtpDirConfigure obj)throws Exception;
	
	/**
	 * 删除FTP配置
	 * @param id
	 * @param path
	 * @throws Exception
	 */
	public void deleteFtpdir(String id)throws Exception;
	
	
	public List<FtpDirConfigure> getList(Map<String,Object> condition,
			String string) throws Exception;
	/**
	 * 
	 * @param obj ftp 对象
	 * @param removePath ftp文件夹目录
	 * @return 返回ftp服务器上指定文件集合，如.xml 和 excel文件
	 * @throws Exception
	 */
	public List<String> getFileNames(FtpConfigure obj, String removePath)
			throws Exception;
	/**
	 *通过ftp服务器返回的文件名集合 和Tsource表中的对应ftp文件对比 
	 * @param fileNames ftp上文件集合
	 * @param fptcode	ftp唯一标记
	 * @param ftpdir	ftp 文件夹目录
	 * @return 返回需要下载的文件名称集合
	 * @throws Exception
	 */
	public Map<String, String> notExistFileNames(List<String> fileNames,
			String fptcode, String ftpdir) throws Exception;
	/**
	 * 在第三方下载数据
	 * @param ftpConfigure
	 * @param sourceCode
	 * @param webRoot
	 * @param targetPath
	 * @param notExistFileNames
	 * @throws Exception
	 */
	
	public List<List> downloadRemoteFile(FtpConfigure ftpConfigure, String webRoot,
			String targetPath, Map<String, String> notExistFileNames)
			throws Exception;
	
	public void saveTSources(List<TSource> list) throws Exception;
	public List<FtpDirConfigure> getFtpDirByFtpid(Map<String, Object> condition) throws Exception;
}
