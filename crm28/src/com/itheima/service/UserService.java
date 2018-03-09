package com.itheima.service;

import com.itheima.bean.User;

public interface UserService {

	void register(User user);

	User login(User user);

}
