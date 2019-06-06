package cn.digitalpublishing.thread;

import cn.digitalpublishing.config.ProcessQueue;

public class SourceDataListener {

	private Counter counter = null;
	
	private int i = 0;
	
	private String msg="";
	
	public SourceDataListener(){
		counter = new Counter();
	}
	
	/**
	 * 执行数据扫描
	 */
	public void executeScan(){
		//System.out.println("ftp源数据扫描线程-=====================");
		if(ProcessQueue.SOURCEDATALOAD==1){
			if(counter.getCount()==0){
				System.out.println("Open TPI Source download thread "+i+"！");
				System.out.println("开启第三方数据源自动下载扫描线程"+i+"！");
				msg="";
				i++;
				counter.countAdd();
				Thread t = new SourceDataProcess(counter);   
				t.start();
			}else{
				if(msg.trim().length()==0){
					msg="有第三方数据源正在扫描，等待......";
					System.out.println(msg);
				}
			}
		}
	}
	
}
