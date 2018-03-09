package com.itheima.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.itheima.bean.BaseDict;
import com.itheima.service.BaseDictService;
import com.itheima.util.Constant;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseDictAction extends ActionSupport  {
	
	private String  dict_type_code;
	private BaseDictService baseDictService;
	
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}
	
	private List<BaseDict> list;
	public List<BaseDict> getList() {
		return list;
	}
	
	public String findByType(){
		//1. 查询数据
		list = baseDictService.findByType(dict_type_code);
		return Constant.JSON_SUCCESS;
	}
	
	
	/*public String findByType(){
		
		
		try {
			//1. 查询数据
			List<BaseDict> list = baseDictService.findByType(dict_type_code);
			
			//2. list --> json 
			String json = new Gson().toJson(list);
			
			//3. 写给页面。
			HttpServletResponse response = ServletActionContext.getResponse();
			
			response.setContentType("text/html;charset=utf-8");
			
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}*/

}
