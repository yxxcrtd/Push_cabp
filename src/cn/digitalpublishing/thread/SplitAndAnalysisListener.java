package cn.digitalpublishing.thread;

import cn.digitalpublishing.config.ProcessQueue;

public class SplitAndAnalysisListener {

	private Counter counter = null;
	
	private int i = 0;
	
	private String msg="";
	
	public SplitAndAnalysisListener(){
		counter = new Counter();
	}
	
	/**
	 * 执行数据扫描
	 */
	public void executeScan(){
		if(ProcessQueue.ANALYSISDATALOAD==1){
			if(counter.getCount()==0){
				System.out.println("Open TPI Source download thread "+i+"！");
				System.out.println("开启拆分解析线程"+i+"！");
				msg="";
				i++;
				counter.countAdd();
				Thread t = new SplitAndAnalysisProcess(counter);   
				t.start();
			}else{
				if(msg.trim().length()==0){
					msg="有拆分解析线程执行，等待......";
					System.out.println(msg);
				}
			}
		}
	}
	
}
