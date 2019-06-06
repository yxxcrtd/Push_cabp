package cn.digitalpublishing.thread;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.digitalpublishing.config.ProcessQueue;
import cn.digitalpublishing.po.FtpConfigure;
import cn.digitalpublishing.po.FtpDirConfigure;
import cn.digitalpublishing.po.PushSta;
import cn.digitalpublishing.po.TSource;
import cn.digitalpublishing.service.factory.ServiceFactory;
import cn.digitalpublishing.service.factory.impl.ServiceFactoryImpl;

public class SourceDataProcess extends Thread {

		ServiceFactory serviceFactory = null;
			
		private Counter counter;
			
		public SourceDataProcess (Counter counter){
			serviceFactory=(ServiceFactory)new ServiceFactoryImpl();
			this.counter=counter;
		}
			
		@Override
		public void run(){
			this.scan();
			counter.countDown();
		}
			
		private void scan(){
			
			System.out.println("----SourceDataProcess线程开启-----");
			//取得fptconfigure 和ftpdirconfigure信息列表
			try{
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("flag", 0);
				List<FtpDirConfigure> ftpdirconfigurelist = this.serviceFactory.getFtpDirService().getList(map, " order by a.dirName asc");
				if(ftpdirconfigurelist!=null&&ftpdirconfigurelist.size()>0){
					for (FtpDirConfigure ftpDirConfigure : ftpdirconfigurelist) {
						try{
							FtpConfigure ftpConfigure = ftpDirConfigure.getFtpConfigure();
							//ftp文件目录
							String removePath = ftpDirConfigure.getFtpdir();
							//连接指定ftp服务器 取得指定文件目录下的文件名称
							List<String> fileNames = serviceFactory.getFtpDirService().getFileNames(ftpConfigure,removePath);
							//得到需要下载的文件的名map集合
							Map<String,String> notExistFileNames = serviceFactory.getFtpDirService().notExistFileNames(fileNames,ftpConfigure.getCode(),ftpDirConfigure.getFtpdir());
							System.out.println("=========需要下载的文件========="+notExistFileNames);
							if(notExistFileNames!=null&&notExistFileNames.size()>0){
								
								//保存到本地路径
								String localPath =ProcessQueue.WEBROOT+File.separator+ProcessQueue.UPLOADROOT
										+File.separator+ProcessQueue.SOURCEDATAPATH+
										File.separator+ftpConfigure.getCode()+File.separator
										+ftpConfigure.getCode()+File.separator+ftpDirConfigure.getFtpdir()+File.separator;
								//下载文件
								List<List> listAll=serviceFactory.getFtpDirService().downloadRemoteFile(ftpConfigure,localPath,removePath,notExistFileNames);
								
						
								//.保存Source源文件信息到TSource表中
								serviceFactory.getFtpDirService().saveTSources(listAll.get(0));
								//
								if(listAll!=null&&listAll.size()>1){
									List list1 = listAll.get(0);
									List list2 = listAll.get(1);
									
									for (int i = 0; i < list2.size(); i++) {
										PushSta pushsta=(PushSta) list2.get(i);
										pushsta.setTaskId(((TSource)list1.get(i)).getId());
										serviceFactory.getPushStaService().save(pushsta);
									}
									
								}

							}
							System.out.println("======文件下载线程结束=======");
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
		
}
