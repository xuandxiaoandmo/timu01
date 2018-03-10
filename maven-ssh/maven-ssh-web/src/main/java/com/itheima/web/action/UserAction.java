package com.itheima.web.action;


import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.domain.User;
import com.itheima.servlet.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**  
 * ClassName:UserAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月9日 下午8:57:06 <br/>       
 */
@Controller
@Scope(value="prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class UserAction extends ActionSupport implements ModelDriven<User>{

	User model=new User();
	
	@Override
	public User getModel() {
		System.out.println("getModel");
		return model;
	}
	
	@Resource 
	private UserService service;

	
	
	public void setModel(User model) {
		System.out.println("setModel");
		this.model = model;
	}





	@Action(value="foundUserById",results ={ @Result(name="success",type="dispatcher",location="/success.jsp")})
	public String foundUserById(){
		System.out.println("拉拉阿拉蕾");
		System.out.println(model);
		model=service.foundUserById(model.getId());
		System.out.println(model);
		
		setModel(model);
		
		return SUCCESS;
	}
	
	
	
}
  
