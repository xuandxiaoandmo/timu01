﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<SCRIPT language=javascript>
	function to_page(page){
		if(page){  //判断是否有参数 。  js方法和java方法不一样。 run(int a)  --- run(4)  
			//to_page(page)  ---> to_page(6) , to_page();
			$("#page").val(page);
		}else{
			
			//点击go按钮就会进入这个else分支。 如果要到达的页，超过了最大页， 禁止跳转。
			var requestPage  = $("#page").val();
			var totalPage  = "${totalPage}";
			if(Number(requestPage) > Number(totalPage)){
				alert(requestPage +" 超过了最大页 ==" + totalPage);
				//让他到达最后一页
				$("#page").val(totalPage);
			}
			
			
		}
		document.customerForm.submit();	  //提交表单
	}
	
	function changePageSize(){
		document.customerForm.submit();	  //提交表单
	}
	
	
	
	function loadDict(type_code , tagId , oldVal){
		//发起请求，获取字典数据 按类型查询字典数据
		var url = "${pageContext.request.contextPath }/baseDict_findByType";
		$.post(url , {"dict_type_code" :type_code } , function(result){
			
			
			/* var oldVal = "${cust_level.dict_id}";//22
			var oldVal = "${cust_source.dict_id}";//22
			var oldVal = "${cust_industry.dict_id}";//22 */
			
			//result -- list<BaseDict>  -- json
			$(result).each(function(i , n){ //i : 遍历的下标，  n : 遍历出来的对象   字典对象
				//var a = 3;
				//找到标签，然后追加内容 val(aa) , text(aa) , html(xxx) , append(xxx)
				
				/* if(oldVal == n.dict_id){
					$(tagId).append("<option value='"+n.dict_id+"' selected='selected'>"+n.dict_item_name+"</option>")
				}else{
					$(tagId).append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>")
				} */
				
				$(tagId).append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>")
				
				
			});
			
			
			//选中该选中的option  在指定的id标签身上找option标签， 按照value值来找，如果找到了就修改这个option标签的属性，修改selected属性，值为selected
			$(tagId).find("option[value='"+oldVal+"']").attr("selected","selected");
			
		} , "json");
	}

	$(function(){
		//alert("执行了入口函数")
		loadDict("001" , "#cust_industry" , "${cust_industry.dict_id}"); //001  -- 客户行业
		loadDict("002" , "#cust_source" , "${cust_source.dict_id}"); //002  -- 客户来源
		loadDict("006" , "#cust_level" , "${cust_level.dict_id}");//006  -- 客户级别
	})
	
</SCRIPT>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm" action="${pageContext.request.contextPath }/customer_findByPage.action" method=post>	
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg" border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg" height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg">
						<IMG src="${pageContext.request.contextPath }/images/new_022.jpg" border=0>
					</TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>客户名称：</TD>
													<TD><INPUT class=textbox id=sChannel2 style="WIDTH: 80px" maxLength=50 name="cust_name"></TD>
													
													<TD>客户来源：</TD>
													<TD>
														<select name="cust_source.dict_id" class=textbox id="cust_source" style="WIDTH: 100px;height:21px">
															<option value="">---请选择---</option>
														</select>
													</TD>
													
													<TD>客户行业：</TD>
													<TD>
														<select name="cust_industry.dict_id" class=textbox id="cust_industry" style="WIDTH: 100px;height:21px">
															<option value="">---请选择---</option>
														</select>
													</TD>
													
													<TD>客户级别：</TD>
													<TD>
														<select name="cust_level.dict_id" class=textbox id="cust_level" style="WIDTH: 100px;height:21px">
															<option value="">---请选择---</option>
														</select>
													</TD>
													
													<TD>客户电话：</TD>
													<TD><INPUT class=textbox id=sChannel2 style="WIDTH: 80px" maxLength=50 name="cust_phone"></TD>
													
													
													
													<TD><INPUT class=button id=sButton2 type=submit value=" 筛选 " name=sButton2></TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD>
										<TABLE id=grid style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc" cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													<TD>客户级别</TD>
													<TD>客户来源</TD>
													<TD>所属行业</TD>
													<TD>联系地址</TD>
													<TD>联系电话</TD>
													<TD>创建人</TD>
													<TD>负责人</TD>
													<TD>操作</TD>
												</TR>
												
												<c:forEach items="${list }" var="customer">
													<TR style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
														<TD>${customer.cust_name }</TD>
														<TD>${customer.cust_level.dict_item_name }</TD>
														<TD>${customer.cust_source.dict_item_name }</TD>
														<TD>${customer.cust_industry.dict_item_name }</TD>
														<TD>${customer.cust_address }</TD>
														<TD>${customer.cust_phone }</TD>
														<TD>${customer.cust_create_id.user_name }</TD>
														<TD>${customer.cust_user_id.user_name }</TD>
														<TD>
														<a href="${pageContext.request.contextPath }/customer_edit?cust_id=${customer.cust_id}">修改</a>
														&nbsp;&nbsp;
														<%-- <a href="${pageContext.request.contextPath }/customer/CustomerServlet?method=removeCustomer&custId=${customer.cust_id}">删除</a> --%>
														<a href="javascript:del(${customer.cust_id})">删除</a>
														<script type="text/javascript">
															function del(id) {
																var flag = confirm("确定删除该客户吗?");
																if(flag){
																	location.href="${pageContext.request.contextPath }/customer_delete?cust_id="+id
																}
															}
														</script>
														</TD>
													</TR>	
												</c:forEach>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD>
										<SPAN id=pagelink>
											<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B>${totalSize}</B>]条记录,[<B>${totalPage}</B>]页
												,每页显示
												<select name="pageSize" onchange="changePageSize()">
													<option value="5" <c:if test="${pageSize==5 }">selected</c:if>>5</option>
													<option value="10" <c:if test="${pageSize==10 }">selected</c:if>>10</option>
													<option value="15" <c:if test="${pageSize==15 }">selected</c:if>>15</option>
													<option value="20" <c:if test="${pageSize==20 }">selected</c:if>>20</option>
												</select>
												条
												[
													<%-- if(当前页已经是第一页了){
														前一页
													}else{
														<A href="javascript:to_page(${page-1})">前一页</A>
													} --%>
													
													<%-- struts的标签， 里面不能写EL表达式，只能写OGNL表达式。
													EL  ${currentPage == 1 }  struts标签不能用
													OGNL currentPage == 1  --%>
													<s:if test="currentPage == 1">
														前一页
													</s:if>
													<s:else>
														<A href="javascript:to_page(${currentPage-1})">前一页</A>
													</s:else>
													
												
												]
												<B>${currentPage}</B>
												[
												
													<!-- 当前页已经是最后一页 -->
													<s:if test="currentPage == totalPage">
														后一页
													</s:if>
													<s:else>
														<A href="javascript:to_page(${currentPage+1})">后一页</A>
													</s:else>
												
												] 
												到
												<input type="text" size="3" id="page" name="currentPage" />
												页
												
												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
										</SPAN>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
						<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg" border=0></TD>
					<TD align=middle width="100%" background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
	<s:debug></s:debug>
</BODY>
</HTML>
