<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>报名信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/join/xlfJoin/list?moduleType=${xlfJoin.moduleId}">报名信息列表</a></li>
		<%-- <shiro:hasPermission name="join:xlfJoin:edit"><li><a href="${ctx}/join/xlfJoin/form">报名信息添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="xlfJoin"
		action="${ctx}/join/xlfJoin/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<c:if test="${moduleType == '2'}">
					<th>兼职名称</th>
					<th>兼职类型</th>
					<th>报名总人数</th>
					<th>待审核人数</th>
				</c:if>
				<c:if test="${moduleType =='1'}">
					<th>活动名称</th>
				</c:if>
				<th>发布人</th>
				<th>发布机构</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="xlfJoin">
				<tr>
					<c:if test="${xlfJoin.moduleType == '2'}">
						<td>${xlfJoin.ptjName}</td>
						<td>${xlfJoin.jobType}</td>
						<td>${xlfJoin.applySumNum}</td>
						<td>${xlfJoin.applyUncheck}</td>
					</c:if>
					<td>${xlfJoin.userName}</td>
					<td>${xlfJoin.userCompany}</td>
					<shiro:hasPermission name="join:xlfJoin:edit">
						<td><a href="${ctx}/join/xlfJoin/form?id=${xlfJoin.id}">修改</a>
							<a href="${ctx}/join/xlfJoin/delete?id=${xlfJoin.id}"
							onclick="return confirmx('确认要删除该报名信息吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>