package com.itheima.bean;

import java.util.Set;

public class Customer {

	private Long cust_id;
	private String cust_name;
	/*private String cust_source;
	private String cust_industry;
	private String cust_level;*/
	
	//表示这个客户属于哪一种来源 ， 字典对象
	private BaseDict cust_source;
	private BaseDict cust_industry;
	private BaseDict cust_level;
	
	private String cust_image;
	
	
	private String cust_phone;
	private String cust_address;
	
	
	//客户和联系人有关系， 客户和联系人的关系是什么关系?  一对多
	//表现这个客户都有哪些联系人，这些联系人可能有很多。。。 集合
	
	private Set<LinkMan> linkMans;
	
	
	
	
	
	
	
	
	//private String cust_user_id;
	//private String cust_create_id;
	
	public Set<LinkMan> getLinkMans() {
		return linkMans;
	}
	public void setLinkMans(Set<LinkMan> linkMans) {
		this.linkMans = linkMans;
	}
	public String getCust_image() {
		return cust_image;
	}
	public void setCust_image(String cust_image) {
		this.cust_image = cust_image;
	}
	private User cust_user_id;
	private User cust_create_id;
	
	
	
	
	
	
	
	public User getCust_user_id() {
		return cust_user_id;
	}
	public void setCust_user_id(User cust_user_id) {
		this.cust_user_id = cust_user_id;
	}
	public User getCust_create_id() {
		return cust_create_id;
	}
	public void setCust_create_id(User cust_create_id) {
		this.cust_create_id = cust_create_id;
	}
	public Long getCust_id() {
		return cust_id;
	}
	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public BaseDict getCust_source() {
		return cust_source;
	}
	public void setCust_source(BaseDict cust_source) {
		this.cust_source = cust_source;
	}
	public BaseDict getCust_industry() {
		return cust_industry;
	}
	public void setCust_industry(BaseDict cust_industry) {
		this.cust_industry = cust_industry;
	}
	public BaseDict getCust_level() {
		return cust_level;
	}
	public void setCust_level(BaseDict cust_level) {
		this.cust_level = cust_level;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_address() {
		return cust_address;
	}
	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}
	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_source=" + cust_source
				+ ", cust_industry=" + cust_industry + ", cust_level=" + cust_level + ", cust_phone=" + cust_phone
				+ ", cust_address=" + cust_address + ", cust_user_id=" + cust_user_id + ", cust_create_id="
				+ cust_create_id + "]";
	}
	
	
	
	
	
	
}
