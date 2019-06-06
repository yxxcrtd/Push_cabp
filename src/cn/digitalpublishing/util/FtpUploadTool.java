package cn.digitalpublishing.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import cn.com.daxtech.framework.util.Log;
import cn.digitalpublishing.constants.ConstantsFTP;
import cn.digitalpublishing.po.FtpConfigure;
import cn.digitalpublishing.po.PushSta;
import cn.digitalpublishing.po.TSource;

public class FtpUploadTool implements UploadTool {
	
	private FTPClient ftpClient = new FTPClient();
	
	private String ip;
	
	private String usr;
	
	private String pwd;
	
	private String port;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	private String mode;
	
	
	
	public FtpUploadTool(){
	}
	
	public FtpUploadTool(String ip,String port,String usr,String pwd){
		this.ip=ip;
		this.port=port;
		this.usr=usr;
		this.pwd=pwd;

	}
	
	public FtpUploadTool(String ip,String port,String usr,String pwd,String mode){
		this.ip=ip;
		this.port=port;
		this.usr=usr;
		this.pwd=pwd;
		this.mode = mode;
	}
	
	public void openConnect() throws Exception{
		try {
			if(!ftpClient.isConnected()){
				ftpClient.connect(ip, Integer.valueOf(port));
				ftpClient.login(usr,pwd);
				int reply = ftpClient.getReplyCode();  
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftpClient.disconnect();
					System.out.println("no reply") ;
				} 
		        if(this.mode==null||this.mode.equals(ConstantsFTP.MODE_PASSIVE)){
		        	ftpClient.enterLocalPassiveMode();
				}else{
					ftpClient.enterLocalActiveMode();
					ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 关闭FTP连接
	 */
	public void closeConnect()throws Exception{
		try {
			if(ftpClient.isConnected())
				ftpClient.disconnect();
		} catch (IOException e) {
			throw e;
		}
	}
	
	/**
     * 删除目录
     * @param dirName
    * @throws IOException 
     */  
    public void removeDirectory(String pathname) throws Exception {  
       try{
    	   pathname=new String(pathname.getBytes("UTF-8"),"ISO-8859-1");
    	   ftpClient.removeDirectory(pathname);
       }catch (IOException e) {
    	   throw e;
       }
    }

    /**
     * 拷贝文件
     * @param srcFile
     * @param descFile
     * @throws IOException
     */
    public void shiftStore(String srcFile,String descFile,String fileName)throws Exception{
    	try{
    		srcFile=new String(srcFile.getBytes("UTF-8"),"ISO-8859-1");
    		descFile=new String(descFile.getBytes("UTF-8"),"ISO-8859-1");
    		fileName=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
    		if(!ftpClient.isConnected())
        		openConnect();
    		String first = srcFile.substring(0, 1);
    		if(first.equals("/")){
    			srcFile = srcFile.substring(1);
    		}
    		if(this.isDirectory(srcFile)){
	    		ftpClient.changeWorkingDirectory(srcFile);
	    		if(!this.isDirectory(descFile)){
	    			this.makeDirectory(descFile);
	    		}
	     	    ftpClient.rename(fileName, descFile+"/"+fileName);
    		}else{
    			throw new Exception("路径错误!");
    		}
        }catch (Exception e) {
        	e.printStackTrace();
     	   throw e;
        }
    }
    /**
     * 拷贝文件2
     * @param srcFile
     * @param descFile
     * @throws IOException
     */
    public void shiftStore2(String srcFile,String descFile,String fileName)throws Exception{
    	try{
    		srcFile=new String(srcFile.getBytes("UTF-8"),"ISO-8859-1");
    		descFile=new String(descFile.getBytes("UTF-8"),"ISO-8859-1");
    		fileName=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
    		if(!ftpClient.isConnected())
        		openConnect();
    		String first = srcFile.substring(0, 1);
    		if(first.equals("/")){
    			srcFile = srcFile.substring(1);
    		}
    		if(!ftpClient.makeDirectory(srcFile)){
	    		ftpClient.changeWorkingDirectory(srcFile);
//	    		if(!ftpClient.makeDirectory(descFile)){
	    			this.makeDirectory(descFile);
//	    		}
	     	    ftpClient.rename(fileName, descFile+"/"+fileName);
    		}else{
    			ftpClient.removeDirectory(srcFile);
    			throw new Exception("路径错误!");
    		}
        }catch (Exception e) {
        	e.printStackTrace();
     	   throw e;
        }
    }
    /**
     * 删除文件
     * @param list
     * @throws Exception 
     */
	public void deleteFile(String remoteDir, String fileName) throws Exception{
		try{
			remoteDir=new String(remoteDir.getBytes("UTF-8"),"ISO-8859-1");
			fileName=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
			if(!ftpClient.isConnected())
        		openConnect();
        	ftpClient.changeWorkingDirectory(remoteDir);
        	//在FTP登录的时候已经设置好了模式，此处模式设置撤销
        	ftpClient.enterLocalPassiveMode();
        	FTPFile[] files = ftpClient.listFiles();
        	for(FTPFile file:files){
        		if(fileName!=null){
					if(file.isFile()&&fileName.equals(file.getName())){
						//文件下载
	         		   	ftpClient.deleteFile(file.getName()); 
					}
        		}else{
        			if(file.isFile()){
        				ftpClient.deleteFile(file.getName()); 
					}
        		}
			}
		}catch (IOException e) {
			throw e;
		}
	}
	
	 /** 
	  * 创建文件文件夹
	  * @param path 
	  * @throws IOException 
	  */  
	public void makeDirectory(String dirName) throws Exception {
		try {
			dirName=new String(dirName.getBytes("UTF-8"),"ISO-8859-1");
			ftpClient.makeDirectory(dirName);
		} catch (IOException e) {
			throw e;
		}
	}
	/**
	 * 判断是否存在
	 * @param dirName
	 * @return
	 */
	public boolean isExist(String dir,String filename)throws Exception{
		boolean isExist=false;
		try{
			filename=new String(filename.getBytes("UTF-8"),"ISO-8859-1");
			dir=new String(dir.getBytes("UTF-8"),"ISO-8859-1");
			if(!ftpClient.isConnected())
        		openConnect();
			ftpClient.changeWorkingDirectory(dir);
			//在FTP登录的时候已经设置好了模式，此处模式设置撤销
			//ftpClient.enterLocalPassiveMode();
			FTPFile[] files = ftpClient.listFiles();
			for(FTPFile file:files){
				if(file.getName().equalsIgnoreCase(filename)){
					isExist=true;
					break;
				}
			}
		}catch(Exception e){
			throw e;
		}
		return isExist;
	}
	/**
	 * 判断是否存在并返回FTP上的文件名
	 * @param dirName
	 * @return
	 */
	public String isExistAndReturn(String dir,String filename)throws Exception{
		String ftpFileName=null;
		try{
			filename=new String(filename.getBytes("UTF-8"),"ISO-8859-1");
			dir=new String(dir.getBytes("UTF-8"),"ISO-8859-1");
			if(!ftpClient.isConnected())
        		openConnect();
			ftpClient.changeWorkingDirectory(dir);
			//在FTP登录的时候已经设置好了模式，此处模式设置撤销
			//ftpClient.enterLocalPassiveMode();
			FTPFile[] files = ftpClient.listFiles();
			for(FTPFile file:files){
				if(file.getName().equalsIgnoreCase(filename)){
					ftpFileName=file.getName();
					break;
				}
			}
		}catch(Exception e){
			throw e;
		}
		return ftpFileName;
	}
	/**
	 * 受否是文件
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public boolean isFile(String fileName)throws Exception{
		boolean isFile=false;
		try{
			fileName=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
			//在FTP登录的时候已经设置好了模式，此处模式设置撤销
			//ftpClient.enterLocalPassiveMode();
			FTPFile[] files = ftpClient.listFiles();
			for(FTPFile file:files){
				if(file.getName().equals(fileName)&&file.isFile()){
					isFile=true;
					break;
				}
			}
		}catch(Exception e){
			throw e;
		}
		return isFile;
	}
	
	public boolean isDirectory(String dirName)throws Exception{
		boolean isDirectory=false;
		try{
			dirName=new String(dirName.getBytes("UTF-8"),"ISO-8859-1");
			//在FTP登录的时候已经设置好了模式，此处模式设置撤销
			//ftpClient.enterLocalPassiveMode();
			FTPFile[] files = ftpClient.listFiles();
			for(FTPFile file:files){
				if(file.getName().equals(dirName)&&file.isDirectory()){
					isDirectory=true;
					break;
				}
			}
		}catch(Exception e){
			throw e;
		}
		return isDirectory;
	}
	/**
	 * 进入文件夹
	 * @param dirPath
	 * @throws Exception
	 */
	public void changeDirectory(String dirPath)throws Exception{
		try{
			dirPath=new String(dirPath.getBytes("UTF-8"),"ISO-8859-1");
			ftpClient.changeWorkingDirectory(dirPath);
		}catch(Exception e){
			throw e;
		}
	}
	/**
	 * 上传文件到Ftp
	 * @param file
	 * @param savePath
	 * @param charset
	 * @param fileName
	 */
	public void uploadFile(File file,String savePath,String charset,String fileName)throws Exception { 
        FileInputStream fis = null;
        try {
        	if(!ftpClient.isConnected())
        		openConnect();
            fis = new FileInputStream(file); 
            //设置上传目录 
            ftpClient.changeWorkingDirectory(savePath);
//            ftpClient.makeDirectory(savePath);
            //System.out.println(ftpClient.makeDirectory(savePath));
            ftpClient.setBufferSize(1024); 
            ftpClient.setControlEncoding(charset); 
            //设置文件类型（二进制） 
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
            ftpClient.storeFile(fileName, fis); 
        }catch(IOException e) {
            throw new RuntimeException("FTP客户端出错！", e); 
        }finally { 
            IOUtils.closeQuietly(fis);
            try {
            	if(ftpClient.isConnected())
            		closeConnect();
            } catch (IOException e) { 
                throw new RuntimeException("关闭FTP连接发生异常！", e); 
            } 
        } 
    } 

	public void downloadFile(String realName,String remoteFileName,HttpServletRequest request, HttpServletResponse response)throws Exception { 
        InputStream fis = null; 
        ServletOutputStream sout = null;
        try {
        	if(!ftpClient.isConnected())
        		openConnect();
            ftpClient.setBufferSize(1024); 
            //设置文件类型（二进制） 
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //ftpClient.setControlEncoding("UTF-8");
            //获取输入流
            //ftpClient.retrieveFileStream(new String(fileName.getBytes(TopologyConfig.getFTPCode()), "iso-8859-1")); 
            fis=ftpClient.retrieveFileStream(new String(remoteFileName.getBytes("UTF-8"), "ISO-8859-1"));
            //获取输出流
			sout = response.getOutputStream();
			
			//获取需要下载的文件
			remoteFileName = remoteFileName.substring(remoteFileName.lastIndexOf("/")+1);
			String suffix=remoteFileName.substring(remoteFileName.indexOf("."));
			//long size = Long.parseLong(remoteFileName.substring(0, remoteFileName.indexOf(".")));
			
			response.setContentType("text/html");
			response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(realName+suffix,"UTF-8").replace("+"," "));
			//response.setContentLength((int)size);
			int readBytes = 0;

			//read from the file; write to the ServletOutputStream
			while ((readBytes = fis.read()) != -1)
				sout.write(readBytes);
        } catch (IOException e) { 
        	e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e); 
        } finally { 
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(sout);
            try { 
            	if(ftpClient.isConnected())
            		closeConnect();
            } catch (IOException e) { 
                throw new RuntimeException("关闭FTP连接发生异常！", e); 
            } 

        }
    }
	
	public void downloadFile(String dir,HashMap<String,String> map)throws Exception { 
		FileOutputStream fos=null;
        try {
        	if(map!=null&&!map.isEmpty()){
        		if(!ftpClient.isConnected())
            		openConnect();
        		
        		for(Map.Entry<String,String> entry : map.entrySet()){
         		   String remoteFileName=entry.getKey();
         		   String suffix=remoteFileName.substring(remoteFileName.indexOf("."));
         		   String localFileName=dir+"/"+entry.getValue()+suffix;
         		   File localFile = new File(localFileName);   
         		   fos = new FileOutputStream(localFile);   
         		   ftpClient.retrieveFile(new String(remoteFileName.getBytes("UTF-8"), "ISO-8859-1"), fos);
         		   IOUtils.closeQuietly(fos);
        		}
        	}
        } catch (IOException e) { 
        	e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e); 
        } finally { 
            IOUtils.closeQuietly(fos);
            try { 
            	if(ftpClient.isConnected())
            		closeConnect();
            } catch (IOException e) { 
                throw new RuntimeException("关闭FTP连接发生异常！", e); 
            } 

        }
    }
	
	public void deleteFile(String remoteDir,Map<String,String> fileNames)throws Exception { 
		FileOutputStream fos=null;
        try {
        	remoteDir=new String(remoteDir.getBytes("UTF-8"),"ISO-8859-1");
        	if(!ftpClient.isConnected())
        		openConnect();
        	ftpClient.changeWorkingDirectory(remoteDir);
        	//在FTP登录的时候已经设置好了模式，此处模式设置撤销
        	//ftpClient.enterLocalPassiveMode();
        	FTPFile[] files = ftpClient.listFiles();
        	for(FTPFile file:files){
        		if(fileNames!=null){
					if(file.isFile()&&fileNames.containsKey(file.getName())){
						//文件下载
	         		   	ftpClient.deleteFile(new String(file.getName().getBytes("UTF-8"),"ISO-8859-1")); 
					}
        		}/*else{
        			if(file.isFile()){
        				ftpClient.deleteFile(file.getName()); 
					}
        		}*/
			}
        } catch (IOException e) { 
        	e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e); 
        } finally { 
            IOUtils.closeQuietly(fos);
            try { 
            	if(ftpClient.isConnected())
            		closeConnect();
            } catch (IOException e) { 
                throw new RuntimeException("关闭FTP连接发生异常！", e); 
            } 

        }
	}
	
	public Map<String,Boolean> downloadFile(String remoteDir,String localDir,Map<String,String> fileNames)throws Exception { 
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		FileOutputStream fos=null;
        try {
        	remoteDir=new String(remoteDir.getBytes("UTF-8"),"ISO-8859-1");
        	if(!ftpClient.isConnected())
        		openConnect();
        	ftpClient.changeWorkingDirectory(remoteDir);
        	FileUtil.newFolder(localDir);
        	ftpClient.setBufferSize(1024); 
        	ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        	//在FTP登录的时候已经设置好了模式，此处模式设置撤销
        	//ftpClient.enterLocalPassiveMode();
        	Log.printInfo("List remote files");
        	
        	FTPFile[] files = ftpClient.listFiles();
        	boolean isdown=false;
        	for(FTPFile file:files){
        		Log.printInfo("Find a remote file:"+new String(file.getName().getBytes("ISO8859-1"), "GBK"));
        		if(fileNames!=null){
        			String filename = new String(file.getName().getBytes("ISO8859-1"), "GBK");
					if(file.isFile()&&fileNames.containsKey(filename)){
						isdown=true;
						//文件下载
						String localFileName = localDir + "/" + fileNames.get(filename).substring(0, fileNames.get(filename).lastIndexOf("."))+fileNames.get(filename).substring(fileNames.get(filename).lastIndexOf(".")).toLowerCase();

						Log.printInfo("Create local file:"+localFileName);
						File localFile = new File(localFileName);   
	         		    fos = new FileOutputStream(localFile);
	         		    Log.printInfo("Begin to Download Romote file to local file");
	         		    ftpClient.retrieveFile(file.getName(), fos); 
	         		    Log.printInfo("Finish to Download Romote file to local file");
	         		    IOUtils.closeQuietly(fos);
					}
        		}else{
        			if(file.isFile()){
						//文件下载
						String localFileName = localDir + "/" + file.getName();
						File localFile = new File(localFileName);   
	         		    fos = new FileOutputStream(localFile);
	         		    ftpClient.retrieveFile(file.getName(), fos);
	         		    IOUtils.closeQuietly(fos);
					}
        		}
			}
        	
			map.put("isokdown", isdown);
        	
        } catch (IOException e){ 
        	map.put("isokdown", false);
        	e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e); 
        } finally { 
            IOUtils.closeQuietly(fos);
            try { 
            	if(ftpClient.isConnected())
            		closeConnect();
            } catch (IOException e) { 
                throw new RuntimeException("关闭FTP连接发生异常！", e); 
            } 
        }
        return map;
	}
	
	
	public int downloadFileAndReturnCount(String remoteDir,String localDir,Map<String,String> fileNames)throws Exception { 
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		FileOutputStream fos=null;
		int count = 0;
        try {
        	remoteDir=new String(remoteDir.getBytes("UTF-8"),"ISO-8859-1");
        	if(!ftpClient.isConnected())
        		openConnect();
        	ftpClient.changeWorkingDirectory(remoteDir);
        	FileUtil.newFolder(localDir);
        	ftpClient.setBufferSize(1024); 
        	ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        	//在FTP登录的时候已经设置好了模式，此处模式设置撤销
        	//ftpClient.enterLocalPassiveMode();
        	Log.printInfo("List remote files");
        	
        	FTPFile[] files = ftpClient.listFiles();
        	for(FTPFile file:files){
        		Log.printInfo("Find a remote file:"+new String(file.getName().getBytes("ISO8859-1"), "GBK"));
        		if(fileNames!=null){
        			String filename = new String(file.getName().getBytes("ISO8859-1"), "GBK");
					if(file.isFile()){
						if(fileNames.containsKey(filename.substring(0,filename.lastIndexOf(".")))){
							//文件下载
							String localFileName = localDir + "/" + filename;
							Log.printInfo("Create local file:"+localFileName);
							File localFile = new File(localFileName);   
							fos = new FileOutputStream(localFile);
							Log.printInfo("Begin to Download Romote file to local file");
							ftpClient.retrieveFile(file.getName(), fos); 
							Log.printInfo("Finish to Download Romote file to local file");
							IOUtils.closeQuietly(fos);
							count++;
							System.out.println("count===="+count);
						}
					}
        		}
			}
        } catch (IOException e){ 
        	map.put("isokdown", false);
        	e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e); 
        } finally { 
            IOUtils.closeQuietly(fos);
            try { 
            	if(ftpClient.isConnected())
            		closeConnect();
            } catch (IOException e) { 
                throw new RuntimeException("关闭FTP连接发生异常！", e); 
            } 
        }
        return count;
	}
	
	
	public List<List> downloadFile2(String remoteDir,String localPath,Map<String,String> fileNames,FtpConfigure ftpConfigure)throws Exception { 
		FileOutputStream fos=null;
		List<List> listAll = new ArrayList<List>();
		List<TSource> fileNameList = new ArrayList<TSource>();
		List<PushSta> pushList = new ArrayList<PushSta>();
        try {
        	remoteDir=new String(remoteDir.getBytes("UTF-8"),"ISO-8859-1");
        	if(!ftpClient.isConnected())
        		openConnect();
        	ftpClient.changeWorkingDirectory(remoteDir);
        	ftpClient.setBufferSize(1024); 
        	ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        	Log.printInfo("List remote files");
        	FTPFile[] files = ftpClient.listFiles();
        	for(FTPFile file:files){
        		if(fileNames!=null){
        			//将Ftp服务器文件名编码
        			String filename = new String(file.getName().getBytes("ISO8859-1"), "GBK");
        			System.out.println("下载excle开始=========="+filename);
					if(file.isFile()&&fileNames.containsKey(filename)){
						
						PushSta pushSta=new PushSta();
						pushSta.setFtpsDate(new Date());
						
						//文件下载
	         		  	FileUtil.newFolder(localPath);
						String localFileName = localPath+ fileNames.get(filename).substring(0, fileNames.get(filename).lastIndexOf("."))+fileNames.get(filename).substring(fileNames.get(filename).lastIndexOf(".")).toLowerCase();
						System.out.println(localFileName);
						Log.printInfo("Create local file:"+localFileName);
						File localFile = new File(localFileName);
	         		    fos = new FileOutputStream(localFile);
	         		    Log.printInfo("Begin to Download Romote file to local file");
	         		    ftpClient.retrieveFile(file.getName(), fos);
	         		    Log.printInfo("Finish to Download Romote file to local file");
	         		    IOUtils.closeQuietly(fos);
	         		    System.out.println("======下载excle文件=========="+localFileName);
						pushSta.setFtpeDate(new Date());
						pushList.add(pushSta);
						
	         		    //文件下载成功 后 封装 TSource 对象集合
	         		    TSource tSource = new TSource();
	         		    tSource.setFileName(filename);
	         		    tSource.setFilePath(localPath);
	         		    tSource.setStatus(1);
	         		    //ftpcode ftp唯一标识
	         		    tSource.setFtpcode(ftpConfigure.getCode());
	         		    tSource.setFtpFileDir(remoteDir);
	         		    //ip
	         		    tSource.setUpdateDate(new Date());
	         		    tSource.setIp(ftpConfigure.getIp());
	         		    //端口号
	         		    tSource.setPort(ftpConfigure.getPort());
	         		   
	         		    tSource.setUsername(ftpConfigure.getUsername());
	         		    
	         		    tSource.setPassword(ftpConfigure.getPassword());
	         		    //ftp上文件目录
	         		    
	         		    fileNameList.add(tSource);
	         		    
	         		   listAll.add(fileNameList);
	         		   listAll.add(pushList);
					}
        		}
			}
        } catch (IOException e) { 
        	e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e); 
        } finally { 
            IOUtils.closeQuietly(fos);
            try { 
            	if(ftpClient.isConnected())
            		closeConnect();
            } catch (IOException e) { 
                throw new RuntimeException("关闭FTP连接发生异常！", e); 
            } 

        }
        return listAll;
	}
	public List<String > downloadFile3(String remoteDir,String localDir,List<String>fileNames)throws Exception { 
		FileOutputStream fos=null;
		List<String> list = new ArrayList<String>();
        try {
        	remoteDir=new String(remoteDir.getBytes("UTF-8"),"ISO-8859-1");
        	if(!ftpClient.isConnected())
        		openConnect();
        	ftpClient.changeWorkingDirectory(remoteDir);
//        	FileUtil.newFolder(localDir);
        	ftpClient.setBufferSize(1024); 
        	ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        	//在FTP登录的时候已经设置好了模式，此处模式设置撤销
        	//ftpClient.enterLocalPassiveMode();
        	Log.printInfo("List remote files");
        	FTPFile[] files = ftpClient.listFiles();
        	for(FTPFile file:files){
        		if(fileNames!=null&&fileNames.size()>0){
        			for(String fileName:fileNames){
        				if(file.isFile()&&fileName.contains(file.getName())){
        					//文件下载
        					String stringFileName=fileName.toLowerCase();
        					if(stringFileName.endsWith(".xls")||stringFileName.endsWith(".xlsx")){
        						FileUtil.newFolder(localDir);
        						String localFileName = localDir+fileName;
        						Log.printInfo("Create local file:"+localFileName);
        						File localFile = new File(localFileName);
        						fos = new FileOutputStream(localFile);
        					    ftpClient.retrieveFile(file.getName(), fos);
        					    IOUtils.closeQuietly(fos);
        					    list.add(localFileName);
        					}
        					
        				}
        		}
				}
        }
        } catch (IOException e) { 
        	e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e); 
        } finally { 
            IOUtils.closeQuietly(fos);
            try { 
            	if(ftpClient.isConnected())
            		closeConnect();
            } catch (IOException e) { 
                throw new RuntimeException("关闭FTP连接发生异常！", e); 
            } 

        }
		return list;
		
	}
	/**
	 * 获取远程服务器文件夹下的文件列表
	 * @param remoteDir
	 * @return
	 * @throws Exception
	 */
	public List<String> getFileNames(String remoteDir)throws Exception {
		List<String> fileNames = new ArrayList<String>();
		try {
			remoteDir=new String(remoteDir.getBytes("UTF-8"),"ISO-8859-1");
			Log.printInfo("Connnect to FTP ...");
        	if(!ftpClient.isConnected())
        	{
        		openConnect();
        	}
        	Log.printInfo("FTP is opened ...");
        	if(remoteDir!=null&&!"".equals(remoteDir)){
        		ftpClient.changeWorkingDirectory(remoteDir);
        		Log.printInfo("Switch the directory to :"+remoteDir);
        	}
        	ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        	//在FTP登录的时候已经设置好了模式，此处模式设置撤销
        	//ftpClient.enterLocalPassiveMode();
        	ftpClient.setControlEncoding("GBK");
        	Log.printInfo("Ready to read the files in the directory ...");
        	FTPFile[] files = ftpClient.listFiles();
        	Log.printInfo("Find the files list ...");
        	Log.printInfo("Begin to collect file's name ...");
        	for(FTPFile file:files){
    			if(file.isFile()){
					//文件下载
    				fileNames.add(file.getName());
				}
			}
        	Log.printInfo("Finish collectting file's name ...");
        } catch (IOException e) { 
        	e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e); 
        } finally{
            try { 
            	if(ftpClient.isConnected())
            		closeConnect();
            	Log.printInfo("FTP connect is close ...");
            } catch (IOException e) {
                throw new RuntimeException("关闭FTP连接发生异常！", e); 
            } 

        }
		return fileNames;
	}
	public void moveFile(String srcPath,String targetPath,String oldName,String newName)throws Exception{
		try{
			srcPath=new String(srcPath.getBytes("UTF-8"),"ISO-8859-1");
			targetPath=new String(targetPath.getBytes("UTF-8"),"ISO-8859-1");
			if(!ftpClient.isConnected())
        		openConnect();
			String src=srcPath;
			String first = src.substring(0, 1);
    		if(first.equals("/")){
    			src = src.substring(1);
    		}
    		String target=targetPath;
    		first = target.substring(0, 1);
    		if(!first.equals("/")){
    			target ="/" + target;
    		}
			ftpClient.changeWorkingDirectory(src);
			ftpClient.makeDirectory(target);
			ftpClient.rename(oldName, target + "/" + newName);
		}catch(Exception e){
			e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e); 
		}finally{
			try { 
            	if(ftpClient.isConnected())
            		closeConnect();
            } catch (IOException e) { 
//            	System.out.println("FTP connect is close ...");
                throw new RuntimeException("关闭FTP连接发生异常！", e); 
            } 
		}
	}
	
}
