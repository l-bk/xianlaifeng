<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户信息模块管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/user/xlfUser/">用户信息模块列表</a></li>
		<%-- <shiro:hasPermission name="user:xlfUser:edit"><li><a href="${ctx}/user/xlfUser/form">用户信息模块添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="xlfUser" action="${ctx}/user/xlfUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户名称</th>
				<th>年龄</th>
				<th>性别</th>
				<th>身高</th>
				<th>电话号码</th>
				<th>角色</th>
				<th>所属单位</th>
				<th>用户简历</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xlfUser">
			<tr>
				<shiro:hasPermission name="user:xlfUser:edit"><td>
    				<a href="${ctx}/user/xlfUser/form?id=${xlfUser.id}">修改</a>
					<a href="${ctx}/user/xlfUser/delete?id=${xlfUser.id}" onclick="return confirmx('确认要删除该用户信息模块吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>