package com.itheima.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.util.StringUtils;

import com.itheima.bean.Customer;
import com.itheima.bean.PageBean;
import com.itheima.bean.User;
import com.itheima.service.CustomerService;
import com.itheima.util.Constant;
import com.itheima.util.MyFileUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	
	private Customer customer;
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@Override
	public Customer getModel() {
		if(customer == null){
			customer = new Customer();
		}
		return customer;
	}
	
	//要想获取文件数据，需要这么声明。
	private File upload; // 属性名称就是页面的 name属性值  <input type="file" name="upload"/>
	private String uploadContentType;  //文件类型  = name属性值 + ContentType 
	private String uploadFileName; //文件名称 = name属性值 + FileName
	
	private int currentPage  =1; //默认拿第一页的数据
	private int pageSize = 5;  //默认每页拿5条数据
	
	//提供set方法，以便页面修改了获取的具体页码数
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	private Customer editCustomer;
	
	public Customer getEditCustomer() {
		return editCustomer;
	}
	
	private List<Customer> list;
	
	public List<Customer> getList() {
		return list;
	}
	
	//查询所有客户
	public String findAll() {
		
		list = customerService.findAll();
		
		return Constant.JSON_SUCCESS;
	}
	
	//修改客户的第一步 ：仅仅是根据客户的id来查询客户的数据，然后跳转到修改页面表现出来
	public String edit() {
		
		//根据id查询客户数据
		editCustomer = customerService.findById(customer.getCust_id());
		
		//存储数据到值栈  push | set  | 属性的方式
		
		return Constant.EDIT_SUCCESS;
		
	}
	//该方法是修改客户的第二步，就真的去修改客户了。
	public String update() {
		
		
		//不妥 有问题...
		//customer.setCust_user_id(cust_user_id);
		//customer.setCust_create_id(cust_create_id);
		
		customerService.update(customer);
		
		return Constant.UPDATE_SUCCESS;
	}
	
	//删除客户
	public String delete() {
		
		customerService.delete(customer);
		
		return Constant.DELETE_SUCCESS;
	}
	
	
	
	/**
	 * 分页显示客户列表
	 * 
	 * 	有两个地方会走这个方法：
	 * 		1. 点击左侧的客户列表
	 * 		2. 列表的顶部筛选也会执行这个方法
	 * @return
	 */
	public String findByPage() {
		
		/**
		 * 离线对象是QBC查询语法的重要组件，它能够让我们在dao层之上的两层进行查询条件的封装，当然，也可以不封装。
		 * 
		 * 假如没有这个离线对象，以前我们要应对多中查询条件，dao层的sql语句怎么写的呢？
		 * 
		 * String sql = "select * from user where 1 = 1";
		 * if(name 不是空){
		 * 		sql =  sql + " name = ?" ;
		 * }else if(age 不是空){
		 * 		
		 * }
		 * ...
		 */
		
		//离线对象如果只是这么创建出来，背后的sql语句 select * from customer;
		//只会使用离线对象的两个方法  setXXX（针对聚合查询 总记录数、最大值、最小值、）  addXXX(针对where条件的设置)
		DetachedCriteria criteria  =DetachedCriteria.forClass(Customer.class);
		//criteria.add(Restrictions.like(propertyName, value));
		//select * from customer where xxx like xxx
		
		//判断条件
		//校验客户名称 QBC查询   HQL  ---？ where name = zhang age = 18
		if(!StringUtils.isEmpty(customer.getCust_name())){
			criteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		//校验客户电话
		if(!StringUtils.isEmpty(customer.getCust_phone())){
			criteria.add(Restrictions.like("cust_phone", "%"+customer.getCust_phone()+"%"));
		}
		
		//校验客户来源
		if(customer.getCust_source()!= null && !StringUtils.isEmpty(customer.getCust_source().getDict_id())){
			
			/*
			 * 查询客户表 
			 * 
			 * 		按照客户名称去查询  % 
			 * 		criteria.add(Restrictions.like("cust_name", customer.getCust_name()));
			 */
			criteria.add(Restrictions.eq("cust_source.dict_id", customer.getCust_source().getDict_id()));
		}
		//校验客户行业
		if(customer.getCust_industry()!=null && !StringUtils.isEmpty(customer.getCust_industry().getDict_id())){
			
			criteria.add(Restrictions.eq("cust_industry.dict_id", customer.getCust_industry().getDict_id()));
		}
		
		//校验客户级别
		if(customer.getCust_level()!= null && !StringUtils.isEmpty(customer.getCust_level().getDict_id())){
			
			criteria.add(Restrictions.eq("cust_level.dict_id", customer.getCust_level().getDict_id()));
		}
		
		
		//2. 查询数据，有返回值
		PageBean<Customer> pageBean = customerService.findByPage(criteria , currentPage , pageSize);
		
		
		//3. 把pageBean存储到作用域 值栈。 push | set | 属性
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return Constant.PAGE_SUCCESS;
	}
	
	
	public String save() throws IOException{
		
		
		
		//对所有的数据都进行校验
		//校验客户名称
		if(StringUtils.isEmpty(customer.getCust_name())){
			addActionError("客户名称不能为空!");
			return Constant.INPUT_ERROR;
		}
		
		//校验客户地址
		if(StringUtils.isEmpty(customer.getCust_address())){
			addActionError("客户地址不能为空!");
			return Constant.INPUT_ERROR;
		}
		
		//校验客户电话
		if(StringUtils.isEmpty(customer.getCust_phone())){
			addActionError("客户电话不能为空!");
			return Constant.INPUT_ERROR;
		}
		
		//校验客户行业
		if(StringUtils.isEmpty(customer.getCust_industry().getDict_id())){
			addActionError("客户行业不能为空!");
			return Constant.INPUT_ERROR;
		}
		
		//校验客户来源
		if(StringUtils.isEmpty(customer.getCust_source().getDict_id())){
			addActionError("客户来源不能为空!");
			return Constant.INPUT_ERROR;
		}
		//校验客户级别
		if(StringUtils.isEmpty(customer.getCust_level().getDict_id())){
			addActionError("客户级别不能为空!");
			return Constant.INPUT_ERROR;
		} 
		//设置创建人  & 负责人  - 谁负责添加的这个客户，那么创建人&负责人就是他
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		//customer.setCust_user_id(user.getUser_id()+"");
		//customer.setCust_create_id(user.getUser_id()+"");
		
		customer.setCust_user_id(user);
		customer.setCust_create_id(user);
		
		
		
		//存储图片
		
	
		
		/*
		 * 
		 * 需求： 数据库里面存放的地址路径应该写什么?
		 * 
		 * 	1. 图片的真实路径在哪里
		 * 		D:/heima28/img/aa.jpg
		 * 
		 *  2. 取出来显示  ${editCustomer.cust_image} == img/aa.jpg  ---- 从数据库来的  -- 添加的时候来的。
		 *  	<img src="${pageContext.request.contextPath }/${editCustomer.cust_image}"/>
		 *  	<img src="/crm28/img/aa.jpg"
		 *  
		 *  3. 虚拟路径怎么设置
		 *  	<Context docBase="D:/heima28/img" path="/crm28/img"/>
		 *  	
		 * 		
		 * 
		 * 
		 */
		
		if(upload != null){
			//存储文件 tmp---jpg | png
			String fileName  = MyFileUtil.getFileName(uploadFileName);
			File file  = new File("D:/heima28/img" , fileName);
			FileUtils.copyFile(upload, file);
			
			
			customer.setCust_image("img/"+fileName);
		}
		
		
		
		customerService.save(customer);
		
		
		return Constant.SAVE_SUCCESS;
	}

}
