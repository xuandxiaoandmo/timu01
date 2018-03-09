<%@page import="com.itheima.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>客户关系管理系统</TITLE> 
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>

<%
	
	User user = (User)session.getAttribute("user");
	if(user == null){
		response.sendRedirect("login.jsp");
	}

%>


<!-- FRAMESET : 划分区域 
	
	rows : 80, * 
	整个区域占据80行， 剩下的区域占据所有行。 其实这里的单位不是行， 是像素。

-->
<FRAMESET frameSpacing=0 rows=80,* frameBorder=0>
	<FRAME name=top src="top.jsp" frameBorder=0 noResize scrolling=no>
	
	
	<!-- 这是底下的部分，按照列来划分。 左边占据220列， 右边占据剩下的列。 这里其实还是像素 -->
	<FRAMESET frameSpacing=0 frameBorder=0 cols=220,*>
		<FRAME name=menu src="${pageContext.request.contextPath}/menu.jsp" frameBorder=0 noResize>
		<FRAME name=main src="${pageContext.request.contextPath}/welcome.htm" frameBorder=0>
	</FRAMESET>
	
	
	<NOFRAMES>
		<p>This page requires frames, but your browser does not support them.</p>
	</NOFRAMES>
</FRAMESET>
</HTML>
