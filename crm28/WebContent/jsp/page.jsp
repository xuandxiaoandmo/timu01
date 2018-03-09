<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

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
</script>
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

</body>
</html>