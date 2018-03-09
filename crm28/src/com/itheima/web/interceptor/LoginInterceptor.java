package com.itheima.web.interceptor;

import org.apache.struts2.ServletActionContext;
import org.xml.sax.SAXException;

import com.itheima.bean.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;


/**
 *1. 实现接口 Interceptor
 *
 *2. 继承实现类  abstractinterceptor
 *
 *		如果要忽略具体的某个方法不拦截，那么需要在代码里面控制。
 *
 *3. 继承子类 MethodFilterInterceptor
 *
 *		如果要忽略具体的方法不拦截，只要在xml里面定义就可以了。
 *
 */
public class LoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		//校验有没有登录
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user == null){
			//没有登录  ---> 跳转到登录页面
			System.out.println("没有登录，现在要去登录~");
			return "login";
		}
		System.out.println("已经登录了，直接放行~");
		//有登录  ---> 放行
		return arg0.invoke();
	}
}
