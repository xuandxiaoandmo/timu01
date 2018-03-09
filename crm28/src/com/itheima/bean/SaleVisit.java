package com.itheima.bean;

import java.util.Date;

public class SaleVisit {

	private String visit_id;
	private Date visit_time;
	private Date visit_nexttime;
	private String visit_addr; //在什么地址拜访
	private String visit_detail; //拜访结果
	
	
	//1. 谁去执行的拜访
	private User user;
	
	//2. 拜访的是哪一个客户
	private Customer customer;
	
	//3. 拜访的是客户里面的哪个联系人
	private LinkMan linkMan;

	public String getVisit_id() {
		return visit_id;
	}

	public void setVisit_id(String visit_id) {
		this.visit_id = visit_id;
	}

	public Date getVisit_time() {
		return visit_time;
	}

	public void setVisit_time(Date visit_time) {
		this.visit_time = visit_time;
	}

	public Date getVisit_nexttime() {
		return visit_nexttime;
	}

	public void setVisit_nexttime(Date visit_nexttime) {
		this.visit_nexttime = visit_nexttime;
	}

	public String getVisit_addr() {
		return visit_addr;
	}

	public void setVisit_addr(String visit_addr) {
		this.visit_addr = visit_addr;
	}

	public String getVisit_detail() {
		return visit_detail;
	}

	public void setVisit_detail(String visit_detail) {
		this.visit_detail = visit_detail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LinkMan getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(LinkMan linkMan) {
		this.linkMan = linkMan;
	}
	
	
	
	
	
	
}
