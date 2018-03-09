package com.itheima.util;

import java.util.UUID;

public class MyFileUtil {

	
	//icon_01.jpg  ---?>lkjslafljalsdfjasdf.jpg
	/**
	 * 获取文件的uuid名称
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName){
		String prefix = UUID.randomUUID().toString().replaceAll("-", "") ;
		String suffix = fileName.substring(fileName.lastIndexOf(".")); //.jpg
		return prefix + suffix;
	}
}

