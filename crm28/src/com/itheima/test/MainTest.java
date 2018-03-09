package com.itheima.test;

import org.junit.Test;
import org.springframework.util.DigestUtils;

public class MainTest {

	
	@Test
	public void testDemo(){
		
		String pwd="heima123+%123*";  //202cb962ac59075b964b07152d234b70
		
		System.out.println(DigestUtils.md5DigestAsHex(pwd.getBytes()));
	}
}
