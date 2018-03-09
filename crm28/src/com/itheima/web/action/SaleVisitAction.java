package com.itheima.web.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import com.itheima.bean.PageBean;
import com.itheima.bean.SaleVisit;
import com.itheima.bean.User;
import com.itheima.service.SaleVisitService;
import com.itheima.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
	private SaleVisit saleVisit;
	private SaleVisitService saleVisitService;
	
	private int currentPage = 1 ;
	private int pageSize = 5 ;
	
	private Date start_visit_time ;
	private Date end_visit_time ;
	
	public void setStart_visit_time(Date start_visit_time) {
		this.start_visit_time = start_visit_time;
	}
	
	public void setEnd_visit_time(Date end_visit_time) {
		this.end_visit_time = end_visit_time;
	}
	
	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
	}
	

	@Override
	public SaleVisit getModel() {
		if(saleVisit == null){
			saleVisit = new SaleVisit();
		}
		return saleVisit;
	}

	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String findByPage(){
		
		//如果开始时间 》 结束时间，直接禁止查询
		
		if(start_visit_time!=null && end_visit_time!=null &&  start_visit_time.after(end_visit_time)){
			
			addActionError("开始时间不能大于结束时间!");
			
			return Constant.INPUT_ERROR;
		}
		
		
		
		DetachedCriteria criteria = DetachedCriteria.forClass(SaleVisit.class);
		
		//校验客户名称
		if(saleVisit.getCustomer()!= null && !StringUtils.isEmpty(saleVisit.getCustomer().getCust_id())){
			criteria.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
		}
		
		
		//校验开始时间&结束时间
		if(start_visit_time !=null && end_visit_time!=null){
			//两个时间都有 lo low  hi -- high
			criteria.add(Restrictions.between("visit_time", start_visit_time, end_visit_time));
		}else{
			//可能两个都没有，或者只有一个
			
			if(start_visit_time!=null){
				criteria.add(Restrictions.ge("visit_time", start_visit_time));
			}
			
			if(end_visit_time!=null){
				criteria.add(Restrictions.le("visit_time", end_visit_time));
			}
			
		}
		
		
		PageBean<SaleVisit> pageBean =saleVisitService.findByPage(criteria , currentPage , pageSize );
		
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return Constant.PAGE_SUCCESS;
	}
	
	public String save(){
		
		//User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		
		User user  =new User();
		user.setUser_id(3L);
		
		saleVisit.setUser(user);
		
		saleVisitService.save(saleVisit);
		
		return NONE;
	}

}
