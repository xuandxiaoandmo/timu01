package com.itheima.web.action;

import org.apache.struts2.ServletActionContext;

import com.itheima.bean.User;
import com.itheima.service.UserService;
import com.itheima.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户处理的action
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	private User user ;
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User getModel() {
		if(user == null){
			user = new User();
		}
		return user;
	}
	
	//注册
	public String register(){
		
		user.setUser_state('1');
		
		userService.register(user);
		
		return NONE;
	}
	
	//登录  主体的功能   ---- > 边边角角 | 细节
	public String login(){
		
		//1. 执行登录
		User loginUser = userService.login(user);
		
		//2. 登录成功  | 失败的处理
		if(loginUser != null){
			//成功 ： 1. 跳转页面   ，2. servlet : 作用域  |  struts : 作用域   值栈和request作用域一样。
			ServletActionContext.getRequest().getSession().setAttribute("user",loginUser );
			return Constant.LOGIN_SUCCESS;
		}
		
		
		//3. 登录失败处理  1.跳转页面 ，2.回显错误信息
		
		//存储到值栈 push | set  | 成员变量 [0] 
		//set背后是： 把值存储到一个map集合， 然后push这个map集合到栈顶上。
		//ActionContext.getContext().getValueStack().set( "msg" , "账号或者密码错误!");
		
		
		//采用struts的方法回显错误
		addFieldError("msg", "账号或者密码错误!");
		
		
		return null;
	}
	

}
