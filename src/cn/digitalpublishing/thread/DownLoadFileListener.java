package cn.digitalpublishing.thread;

import cn.digitalpublishing.config.ProcessQueue;

public class DownLoadFileListener {

	private Counter counter = null;
	
	private int i = 0;
	
	private String msg="";
	
	public DownLoadFileListener(){
		counter = new Counter();
	}
	
	/**
	 * 执行数据扫描
	 */
	public void executeScan(){
		System.out.println("文件下载");
		if(ProcessQueue.DOWNLOAD==1){
			if(counter.getCount()==0){
				System.out.println("Open File download thread "+i+"！");
				System.out.println("开启下载文件线程"+i+"！");
				msg="";
				i++;
				counter.countAdd();
				Thread t = new DownLoadFileProcess(counter);   
				t.start();
			}else{
				if(msg.trim().length()==0){
					msg="有文件下载线程执行，等待......";
					System.out.println(msg);
				}
			}
		}
	}
	
}
