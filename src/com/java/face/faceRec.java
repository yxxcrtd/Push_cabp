package com.java.face;

/**
 * 人脸识别工具�?
 * @version 0.1 老接�?
 * @author cailei
 */
@Deprecated
public class faceRec {

	static {

		System.out.println("init the dll library start...");
		String property = System.getProperty("java.library.path");
		System.out.println(property);
		//System.loadLibrary("ImageDiff");
		System.out.println("init the dll library end...");

    }
    
    /**
     * 人脸样本训练
     * 
     * @param cascade	角度识别XML（允许用户上传XML文件，配置图片角度信息）
     * @param faces		人脸序列文本文件（一组样本图片路径存储在txt文本里）
     * @param train		人脸特征输出文件（生成的特征库路径）
     * @return			成功返回大于零的高度值，否则返回小于等于�?
     */
    public native int faceTrain(String cascade, String faces, String train);
    
    /**
     * 人脸识别
     * 
     * @param cascade		角度识别XML（允许用户上传XML文件，配置图片角度信息）
     * @param modelfile		人脸特征文件（特征库路径�?
     * @param markImg		mark图片地址
     * @param image			待检测图片（被检测的图片路径�?
     * @param output		识别后输出文件为""则不输出（识别后输出文件路径�?
     * @return				返回多张人脸中最匹配的�?，该值越小表示匹配度越高�?10000.0表示完全没有匹配的人�?0.0 表示有匹配的人脸
     */
    public native double faceDiff(String cascade, String modelfile, String markImg, String image, String output);

}
