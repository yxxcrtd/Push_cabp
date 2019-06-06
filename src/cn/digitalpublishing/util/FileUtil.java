package cn.digitalpublishing.util;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import cn.com.daxtech.framework.exception.CcsException;

/**
 * 处理文件操作
 * @author yul
 */
@SuppressWarnings({ "rawtypes", "unused", "resource", "static-access" })
public class FileUtil {
	/*
	 * Description : 创建文件夹
	 * 
	 * @param folderPath
	 * 要创建的文件夹的完整路径 例如 d:/x/y
	 * 
	 */
	public static void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			System.out.println(myFilePath);
			if (!myFilePath.exists()&&!myFilePath.isDirectory()) {
				myFilePath.mkdirs();
				System.out.println("目录创建成功！");
			}
		} catch (Exception e) {
			System.out.println("新建目录操作出错");
			e.printStackTrace();
		}
	}

	/*
	 *  新建文件  
	 *  
	 *  @param  filePathAndName
	 *  文件路径及名称  如c:/fqf.txt  
	 *  
	 *  @param  fileContent
	 *  文件内容  
	 *  
	 */
	public static void newFile(String filePathAndName, String fileContent) {

		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();

		} catch (Exception e) {
			System.out.println("新建目录操作出错");
			e.printStackTrace();

		}
	}

	/*  
	 *  删除文件  
	 *  @param  filePathAndName  String  
	 *  文件路径及名称  如c:/fqf.txt 
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			myDelFile.delete();

		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();

		}

	}

	/*  
	 *  删除文件夹里面的所有文件  
	 *  @param  path  String  文件夹路径  如  c:/fqf  
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件  
				delFolder(path + "/" + tempList[i]);//再删除空文件夹  
			}
		}
	}

	/*  
	 *  删除文件夹  
	 *  @param  filePathAndName  String  文件夹路径及名称  如c:/fqf  
	 *  @param  fileContent  String  
	 *  @return  boolean  
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); //删除完里面所有内容  
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); //删除空文件夹  

		} catch (Exception e) {
			System.out.println("删除文件夹操作出错");
			e.printStackTrace();

		}
	}

	/*  
	 *  复制单个文件  
	 *  @param  oldPath  String  原文件路径  如：c:/fqf.txt  
	 *  @param  newPath  String  复制后路径  如：f:/fqf.txt  
	 *  @return  boolean  
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { //文件存在时  
				InputStream inStream = new FileInputStream(oldPath); //读入原文件  
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[8 * 1024];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; //字节数  文件大小  
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}

	}

	/*  
	 *  复制整个文件夹内容  
	 *  @param  oldPath  String  原文件路径  如：c:/fqf  
	 *  @param  newPath  String  复制后路径  如：f:/fqf/ff  
	 *  @return  boolean  
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); //如果文件夹不存在  则建立新文件夹  
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 8];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {//如果是子文件夹  
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();

		}
	}

	/*  
	 *  移动文件到指定目录  
	 *  @param  oldPath  String  如：c:/fqf.txt  
	 *  @param  newPath  String  如：d:/fqf.txt  
	 */
	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);

	}

	/*  
	 *  移动文件到指定目录  
	 *  @param  oldPath  String  如：c:/fqf.txt  
	 *  @param  newPath  String  如：d:/fqf.txt  
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);

	}

	public static String getFix(String fileName) {
		int pos = fileName.lastIndexOf(".");
		if (pos == -1) {
			return null;
		} else {
			return fileName.substring(pos+1, fileName.length());
		}
	}

	public static void copy(File src, File dst) {
		int BUFFER_SIZE = 8 * 1024;
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	

	public static void compressionFiles(ZipOutputStream zosm, File file,
			String basePath) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			try {
				zosm.setEncoding("gbk");// 指定编码为gbk，否则部署到linux下会出现乱码
				zosm.putNextEntry(new ZipEntry(basePath + "/"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			basePath = basePath + ((basePath.length() == 0) ? "" : "/");
			for (File f : files) {
				if (f.isDirectory()) {
					
					compressionFiles(zosm, f, basePath + f.getName());
				} else {
					compressionFiles(zosm, f, basePath);
				}
			}
		} else {
			FileInputStream fism = null;
			BufferedInputStream bism = null;
			try {
				byte[] bytes = new byte[1024];
				fism = new FileInputStream(file);
				bism = new BufferedInputStream(fism, 1024);
				if (basePath.length() != 0
						&& !"/".equals(basePath.substring(
								basePath.length() - 1, basePath.length()))
						&& !"\\".equals(basePath.substring(
								basePath.length() - 1, basePath.length()))) {
					basePath = basePath + "/" + file.getName();
				} else {
					basePath = basePath + file.getName();
				}
				zosm.putNextEntry(new ZipEntry(basePath));
				int count;
				while ((count = bism.read(bytes, 0, 1024)) != -1) {
					zosm.write(bytes, 0, count);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bism != null) {
					try {
						bism.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fism != null) {
					try {
						fism.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	
	public static Boolean isExist(String filepath)throws Exception{
		try{
			File f=new File(filepath);
			return f.exists();
		}catch(Exception e){
			throw e;
		}
	}
	
	public static void createPath(String filepath)throws Exception{
		try{
			File f=new File(filepath);
			if(!f.exists()){
				f.mkdirs();
			}
		}catch(Exception e){
			throw e;
		}
	}
	
	
	/**
     * 解压 zip 文件，注意不能解压 rar 文件哦，只能解压 zip 文件 解压 rar 文件 会出现 java.io.IOException: Negative  seek offset 异常 
     * create date:2012-12-18 
     * author:ruixue cheng
     * @param zipfile 
     * @param destDir 
     * @throws IOException 
     */ 
	public static List<String> unZip(String zipfile, String destDir){
		
		List<String> list = new ArrayList<String>();
		
		destDir = destDir.endsWith( "//" ) ? destDir : destDir + "//" ; 

		byte b[] = new byte [1024]; 

		int length; 

		ZipFile zipFile = null;
		OutputStream outputStream = null;
		try{
			zipFile = new ZipFile( new File(zipfile)); 
			Enumeration enumeration = zipFile.getEntries();
			ZipEntry zipEntry = null ; 
			while (enumeration.hasMoreElements()) { 
				zipEntry = (ZipEntry) enumeration.nextElement();
				File loadFile = new File(destDir + zipEntry.getName()); 
				if (zipEntry.isDirectory()){
					//这段都可以不要，因为每次都貌似从最底层开始遍历的 
					loadFile.mkdirs(); 
				}else{
					if(!loadFile.getParentFile().exists()) 
						loadFile.getParentFile().mkdirs(); 
					list.add(zipEntry.getName());
					outputStream = new FileOutputStream(loadFile); 
					InputStream inputStream = zipFile.getInputStream(zipEntry); 
					while ((length = inputStream.read(b)) > 0) {
						outputStream.write(b, 0, length);
					}
				}
				if(outputStream!=null){
		    		try {
		    			outputStream.flush();
		    			outputStream.close();
		    		} catch (IOException e) {
		    			e.printStackTrace();
		    		}
		    	}
			} 
			System. out .println("文件解压成功"); 
	    } catch (IOException e) { 
	    	e.printStackTrace(); 
	    }finally{
	    	if(outputStream!=null){
	    		try {
	    			outputStream.flush();
	    			outputStream.close();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	if(zipFile!=null){
	    		try {
	    			zipFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    }
	    return list;
	}

	public static String readTxtFile(String filePath){ 
		StringBuffer sb = new StringBuffer();
        try { 
                String encoding="GBK"; 
                File file=new File(filePath); 
                if(file.isFile() && file.exists()){ //判断文件是否存在 
                    InputStreamReader read = new InputStreamReader( 
                    new FileInputStream(file),encoding);//考虑到编码格式 
                    BufferedReader bufferedReader = new BufferedReader(read); 
                    String lineTxt = null; 
                    while((lineTxt = bufferedReader.readLine()) != null){ 
                    	sb.append(lineTxt); 
                    } 
                    read.close(); 
        }else{ 
            System.out.println("找不到指定的文件"); 
        } 
        } catch (Exception e) { 
            System.out.println("读取文件内容出错"); 
            e.printStackTrace(); 
        } 
        return sb.toString();
      
    } 

	
	public static String getStringText(String filePath) throws Exception{
		Tika tika = new Tika();
		String stringText = null;
		InputStream iStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
		Metadata md = new Metadata();
		md.add(Metadata.CONTENT_ENCODING, "utf-8");
		if (isExist(filePath)) {
			stringText = tika.parseToString(iStream,md); 
		}
		return stringText;
	}
	
	public static int writeExcle(String tempPathName,String targePath,String fileName,Map<String, Object> ashgateData){
		int stauts =100;
		FileInputStream is = null;		
		FileOutputStream os = null;
		XSSFWorkbook xssfWorkbook = null;
		try {
			is = new FileInputStream(new File(tempPathName));
			xssfWorkbook = new XSSFWorkbook(is);
			XSSFSheet xssfsheet = xssfWorkbook.getSheetAt(0);
			XSSFRow xssfRow = xssfsheet.createRow(1);
			if(ashgateData.get("filecode")!=null){
				XSSFCell xssfcell1 = xssfRow.createCell(0);
				//文件编号
				xssfcell1.setCellValue(ashgateData.get("filecode").toString());
			}
			if(ashgateData.get("filename")!=null){
				XSSFCell xssfcell2 = xssfRow.createCell(1);
				//文件名称
				xssfcell2.setCellValue(ashgateData.get("filename").toString());
			}
			if(ashgateData.get("title")!=null){
				//文件标题
				XSSFCell xssfcell3 = xssfRow.createCell(2);
				xssfcell3.setCellValue(ashgateData.get("title").toString());
			}
			if(ashgateData.get("author")!=null){
				
				XSSFCell xssfcell4 = xssfRow.createCell(3);
				//作者
				xssfcell4.setCellValue(ashgateData.get("author").toString());
			}
//			if(ashgateData.get("contenttype")!=null){
//				
//				XSSFCell xssfcell5 = xssfRow.createCell(4);
//				//操作类型
//				xssfcell5.setCellValue(ashgateData.get("contenttype").toString());
//			}
			if(ashgateData.get("contentabstract")!=null){
				
				XSSFCell xssfcell5 = xssfRow.createCell(4);
				//摘要
				xssfcell5.setCellValue(ashgateData.get("contentabstract").toString());
			}
			
//			if(ashgateData.get("isdownload")!=null){
//				
//				XSSFCell xssfcell6 = xssfRow.createCell(5);
//				//文件是否下载
//				xssfcell6.setCellValue(ashgateData.get("isdownload").toString());
//			}
			File file = new File(targePath);
			//判断文件夹是否存在 若不存在创建文件夹目录
			if(!file.exists()){
				file.mkdirs();
			}
			
			os = new FileOutputStream(new File(targePath+fileName));
			xssfWorkbook.write(os);
		} catch (Exception e) {
			stauts=404;
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			return stauts;
	}
	/**
	 * @param filepath 文件路径
	 * @return 返回map集合 key为头信息 value 头对应的的下行列的值
	 * @throws IOException 
	 */
	public static List<List<String>> getExcelMsg2007(File file){
		FileInputStream is = null;
		XSSFWorkbook xssfWorkbook = null;
		XSSFSheet xssfsheet = null;
		List<List<String>> list = new ArrayList<List<String>>();
		try {
			is = new FileInputStream(file);
			xssfWorkbook = new XSSFWorkbook(is);
			xssfsheet = xssfWorkbook.getSheetAt(0);
			for(int rownum=0; rownum<=xssfsheet.getLastRowNum();rownum++){
				List<String> rows = new ArrayList<String>();
				XSSFRow xssfRow = xssfsheet.getRow(rownum);
				for (int cellnum = 0; cellnum < xssfRow.getLastCellNum(); cellnum++) {
					XSSFCell xssfcell = xssfRow.getCell(cellnum);
					if(xssfcell!=null){
						String strVal;
						//取得列中的值
							if(xssfcell.getCellType() == xssfcell.CELL_TYPE_BOOLEAN){   
								strVal= String.valueOf( xssfcell.getBooleanCellValue());   
							 }else if(xssfcell.getCellType() == xssfcell.CELL_TYPE_NUMERIC){   
								 strVal= String.valueOf( xssfcell.getNumericCellValue());   
							 }else{   
								 strVal= String.valueOf( xssfcell.getStringCellValue());   
						  }   
							rows.add(strVal);
					}else{
						rows.add("");
					}
				}
				
				list.add(rows);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
		
	}
	
	
	/**2003的支持
	 * @param filepath 文件路径
	 * @return 返回map集合 key为头信息 value 头对应的的下行列的值
	 * @throws IOException 
	 */
	public static List<List<String>> getExcelMsg2003(File file){
		FileInputStream is = null;
		HSSFWorkbook hssfWorkbook = null;
		HSSFSheet hssfSheet  = null;
		List<List<String>> list = new ArrayList<List<String>>();
		try {
			is = new FileInputStream(file);
			hssfWorkbook = new HSSFWorkbook( is);  
			hssfSheet = hssfWorkbook.getSheetAt(0);
			for(int rownum=0; rownum<=hssfSheet.getLastRowNum();rownum++){
				List<String> rows = new ArrayList<String>();
				HSSFRow hssfRow = hssfSheet.getRow(rownum);
				for (int cellnum = 0; cellnum < hssfRow.getLastCellNum(); cellnum++) {
					HSSFCell hssfCell = hssfRow.getCell(cellnum);
					if(hssfCell!=null){
						String strVal;
						//取得列中的值
							if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN){   
								strVal= String.valueOf( hssfCell.getBooleanCellValue());   
							 }else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC){   
								 strVal= String.valueOf( hssfCell.getNumericCellValue());   
							 }else{   
								 strVal= String.valueOf( hssfCell.getStringCellValue());   
						  }   
							rows.add(strVal);
					}else{
						rows.add(null);
					}
				}
				
				list.add(rows);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
		
	}
	

	
	public static String getUUID(){
		String uuid=UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
	
	/**
	 * 将上传文件写入到服务器
	 * @param fileAllPath 文件绝对路径
	 * @param fileName 文件名称
	 * @param b 文件内容 
	 */
	public static void writeFile(String fileAllPath,String fileName, byte[] b) {
		//文件上传
		FileOutputStream outputStream = null;
		try {
			//判断路径是否存在 不存在 则创建
			File file =new File(fileAllPath);
			if(!file.exists()){
				file.mkdirs();
			}
			outputStream = new FileOutputStream(fileAllPath+fileName);
			outputStream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
			File file = new File(fileAllPath);
			if(file.exists()){
				file.delete();
			}
		}finally{
			if(outputStream!=null){
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 通过文件原名 返回 系统当前时间生成的新文件名
	 * @param fileName
	 * @return filename
	 */
	public static String getFileName(String fileName){
		String suffixes = fileName.substring(fileName.lastIndexOf("."));
		return System.currentTimeMillis()+suffixes;
	}
	
	
	/**
	 * @desc 将源文件/文件夹生成指定格式的压缩文件,格式zip
	 * @param resourePath 源文件/文件夹
	 * @param targetPath  目的压缩文件保存路径
	 * @return void
	 * @throws Exception 
	 */
	public void compressedFile(String resourcesPath,String targetPath) throws Exception{
		ZipOutputStream out = null;
		try {
			File resourcesFile = new File(resourcesPath);     //源文件
			File targetFile = new File(targetPath);           //目的
			//如果目的路径不存在，则新建
			if(!targetFile.exists()){     
				targetFile.mkdirs();  
			}
			
			String targetName = resourcesFile.getName()+".zip";   //目的压缩文件名
			FileOutputStream outputStream = new FileOutputStream(targetPath+File.separator+targetName);
			out = new ZipOutputStream(new BufferedOutputStream(outputStream));
			
			createCompressedFile(out, resourcesFile, "");
			
		} catch (Exception e) {
			// TODO: handle exception  压缩文件异常
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt():"file.info.compressedFiles.error", e);//获取FTP配置信息分页列表失败�?
		}finally{
			if(out!=null){
				out.close();  
			}
		}
		
	}
	
	/**
	 * @desc 生成压缩文件。
	 * 	             如果是文件夹，则使用递归，进行文件遍历、压缩
	 *       如果是文件，直接压缩
	 * @param out  输出流
	 * @param file  目标文件
	 * @return void
	 * @throws Exception 
	 */
	public void createCompressedFile(ZipOutputStream out,File file,String dir) throws Exception{
		//如果当前的是文件夹，则进行进一步处理
		FileInputStream fis=null;
		try {
			if(file.isDirectory()){
				//得到文件列表信息
				File[] files = file.listFiles();
				//将文件夹添加到下一级打包目录
				out.putNextEntry(new ZipEntry(dir+"/"));
				
				dir = dir.length() == 0 ? "" : dir +"/";
				
				//循环将文件夹中的文件打包
				for(int i = 0 ; i < files.length ; i++){
					createCompressedFile(out, files[i], dir + files[i].getName());         //递归处理
				}
			}
			else{   //当前的是文件，打包处理
				//文件输入流
				fis = new FileInputStream(file);
				
				out.putNextEntry(new ZipEntry(dir));
				//进行写操作
				int j =  0;
				byte[] buffer = new byte[1024];
				while((j = fis.read(buffer)) > 0){
					out.write(buffer,0,j);
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(fis!=null){
				//关闭输入流
				fis.close();
			}
		}
	}
	
	public static void main(String[] args) {
		
	}
	
}
