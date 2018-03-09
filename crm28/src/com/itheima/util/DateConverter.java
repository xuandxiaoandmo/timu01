package com.itheima.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverter extends StrutsTypeConverter {
	

	//声明日期转化的格式
	SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
	
	//接收页面提交数据的时候，调用这个方法来转化
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		
		//varlus : 从页面收到的数据
		try {
			return format.parse(values[0]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	//把数据发送给浏览器的时候，调用这个方法来转化
	@Override
	public String convertToString(Map context, Object o) {
		
		//o 就是要传输给页面的数据  就是日期对象
		return format.format(o);
	}

}
