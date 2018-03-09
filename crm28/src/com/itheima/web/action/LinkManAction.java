package com.itheima.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.util.StringUtils;

import com.itheima.bean.LinkMan;
import com.itheima.bean.PageBean;
import com.itheima.service.LinkManService;
import com.itheima.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private LinkMan linkMan ; 
	private LinkManService linkManService ; 
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	private int currentPage = 1;
	private int pageSize = 5;
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	@Override
	public LinkMan getModel() {
		if(linkMan == null){
			linkMan = new LinkMan();
		}
		return linkMan;
	}
	
	private LinkMan editLinkMan;
	
	public LinkMan getEditLinkMan() {
		return editLinkMan;
	}
	
	private Long cid;
	public void setCid(Long cid) {
		this.cid = cid;
	}
	
	private List<LinkMan> list;
	
	public List<LinkMan> getList() {
		return list;
	}
	
	//根据客户的id来查询联系人
	public String findByCid(){
		
		//定义离线对象，表示查询那张表
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		
		//添加查询条件，按照客户的id来查询联系人
		criteria.add(Restrictions.eq("customer.cust_id", cid));
		
		//可能查询到很多的联系人，所以这里是一个list集合， 并且页面要求的是一个json数据
		list = linkManService.findByCid(criteria);
		
		
		return Constant.JSON_SUCCESS;
	}
	
	//该方法是修改的第一步，仅仅是根据id来查询联系人而已
	public String edit(){
		
		//根据id查询联系人
		editLinkMan = linkManService.findById(linkMan.getLkm_id());
		
		//存数据到值栈
		
		
		return Constant.EDIT_SUCCESS;
	}
	public String update(){
		
		linkManService.update(linkMan);
		
		return Constant.UPDATE_SUCCESS;
	}
	
	//删除联系人
	public String delete(){
		
		
		linkManService.delete(linkMan);
		
		return Constant.DELETE_SUCCESS ;
	}
	public String findByPage(){
		
		//1. 查数据
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		
		//设置过滤条件
		
		//名称
		if(!StringUtils.isEmpty(linkMan.getLkm_name())){
			criteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		//邮箱
		if(!StringUtils.isEmpty(linkMan.getLkm_email())){
			criteria.add(Restrictions.like("lkm_email", "%"+linkMan.getLkm_email()+"%"));
		}
		//qq
		if(!StringUtils.isEmpty(linkMan.getLkm_qq())){
			criteria.add(Restrictions.like("lkm_qq", "%"+linkMan.getLkm_qq()+"%"));
		}
		//电话
		if(!StringUtils.isEmpty(linkMan.getLkm_phone())){
			criteria.add(Restrictions.like("lkm_phone", "%"+linkMan.getLkm_phone()+"%"));
		}
		
		//性别
		if(!StringUtils.isEmpty(linkMan.getLkm_gender())){
			criteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		
		//所属客户
		if(linkMan.getCustomer()!=null && !StringUtils.isEmpty(linkMan.getCustomer().getCust_id())){
			criteria.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		}
		
		
		PageBean<LinkMan> pageBean = linkManService.findByPage(criteria , currentPage , pageSize);
		
		//2. 存数据 (值栈)  push  | set | 属性
		ActionContext.getContext().getValueStack().push(pageBean);
		
		
		return Constant.PAGE_SUCCESS;
		
	}
	
	public String save(){
		
		linkManService.save(linkMan);
		
		return Constant.SAVE_SUCCESS;
	}

}
