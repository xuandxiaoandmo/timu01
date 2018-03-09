<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>联系人列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style.css"
	type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/Manage.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript">
	function to_page(page) {
		if (page) {
			$("#page").val(page);
		}
		document.customerForm.submit();
	}
</script>

<!-- 日期插件，使用jquery -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jquery/jquery.datepick.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/jquery.datepick.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/jquery.datepick-zh-CN.js"></script>

<script type="text/javascript">
	$(function() {
		//使用class属性处理  'yy-mm-dd' 设置格式"yyyy/mm/dd"
		$('#startTimeId').datepick({
			//dateFormat : 'yy-mm-dd'
			dateFormat : 'yy年mm月dd日'
		});
		$('#endTimeId').datepick({
			dateFormat : 'yy年mm月dd日'
		});

	});
	
	//一进来就获取客户的数据下来
	$(function(){
		var url = "${pageContext.request.contextPath }/customer_findAll.action"
		$.get(url  , function(result){ // result -- 集合<Customer>
			$(result).each(function(i , n){
				$("#customer").append("<option value='"+n.cust_id+"'>"+n.cust_name+"</option>")
			});
		} , "json");
	})

	
</script>

<meta content="MSHTML 6.00.2900.3492" name="GENERATOR" />
</head>
<body>
	<form id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath}/saleVisit_findByPage.action"
		method="post">

		<table cellspacing="0" cellpadding="0" width="98%" border="0">
			<tbody>
				<tr>
					<td width="15"><img
						src="${pageContext.request.contextPath}/images/new_019.jpg"
						border="0"></td>
					<td width="100%"
						background="${pageContext.request.contextPath}/images/new_020.jpg"
						height="20"></td>
					<td width="15"><img
						src="${pageContext.request.contextPath}/images/new_021.jpg"
						border="0"></td>
				</tr>
			</tbody>
		</table>
		<table cellspacing="0" cellpadding="0" width="98%" border="0">
			<tbody>
				<tr>
					<td width="15"
						background="${pageContext.request.contextPath}/images/new_022.jpg"><img
						src="${pageContext.request.contextPath}/images/new_022.jpg"
						border="0"></td>
					<td valign="top" width="100%" bgcolor="#ffffff">
						<table cellspacing="0" cellpadding="5" width="100%" border="0">
							<tr>
								<td class="manageHead">当前位置：客户拜访管理 &gt; 客户拜访列表</td>
							</tr>
							<tr>
								<td height="2"></td>
							</tr>
						</table>
						<table bordercolor="#cccccc" cellspacing="0" cellpadding="0"
							width="100%" align="center" border="0">
							<tbody>
								<tr>
									<td height="25">
										<table cellspacing="0" cellpadding="2" border="0">
											<tbody>
												<tr>
													<td>客户名称：</td>
													<td>
														<select id="customer" name="customer.cust_id" class="textbox" style="width: 140px; height: 21px;">
																<option value="">--请选择--</option>
														</select>
													</td>

													<td>拜访时间：</td>
													<td>
													
													
													<s:textfield class="textbox" id="startTimeId"
															style="width: 140px" name="start_visit_time"
															readonly="readonly" />
															 - 
															 
													<s:textfield class="textbox"
															id="endTimeId" style="width: 140px" name="end_visit_time"
															readonly="readonly" /></td>


													<td><input class="button" id="sButton2" type="submit"
														value=" 筛选 " name="sButton2"></td>
														
													<td>
														<font color="red">${actionErrors[0] }</font>
													</td>	
												</tr>
											</tbody>
										</table>
									</td>
								</tr>

								<tr>
									<td>
										<table id="grid"
											style="BORDER-TOP-width: 0px; FONT-WEIGHT: normal; BORDER-LEFT-width: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-width: 0px; BORDER-BOTTOM-COLOR: #cccccc; width: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-width: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellspacing="1" cellpadding="2" rules="all" border="0">
											<tbody>
												<tr
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<td>拜访客户</td>
													<td>拜访时间</td>
													<td>被拜访人</td>
													<td>拜访地点</td>
													<td>拜访详情</td>
													<td>下次拜访时间</td>
												</tr>

												<s:iterator var="visit" value="list">
													<tr style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													
														<!-- 如果取得值位于context区域，那么要记得加上 # -->
													
														
														
														<td>${visit.customer.cust_name }</td>
														<td><fmt:formatDate value="${visit.visit_time }" pattern="yyyy-MM-dd"/></td>
														<%-- <td><s:property value="#visit.visit_time" /></td> --%>
														<td><s:property value="#visit.linkMan.lkm_name" /></td>
														<td><s:property value="#visit.visit_addr" /></td>
														<td><s:property value="#visit.visit_detail" /></td>
														<td><s:property value="#visit.visit_nexttime" /></td>
													</tr>
												</s:iterator>

											</tbody>
										</table>
									</td>
								</tr>

								<tr>
									<td>
										<%@ include file="/jsp/page.jsp" %>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
					<td width="15"
						background="${pageContext.request.contextPath}/images/new_023.jpg"><img
						src="${pageContext.request.contextPath}/images/new_023.jpg"
						border="0"></td>
				</tr>
			</tbody>
		</table>
		<table cellspacing="0" cellpadding="0" width="98%" border="0">
			<tbody>
				<tr>
					<td width="15"><img
						src="${pageContext.request.contextPath}/images/new_024.jpg"
						border="0"></td>
					<td align="center" width="100%"
						background="${pageContext.request.contextPath}/images/new_025.jpg"
						height=15></td>
					<td width="15"><img
						src="${pageContext.request.contextPath}/images/new_026.jpg"
						border="0"></td>
				</tr>
			</tbody>
		</table>
	</FORM>
	<s:debug></s:debug>
</body>
</HTML>
