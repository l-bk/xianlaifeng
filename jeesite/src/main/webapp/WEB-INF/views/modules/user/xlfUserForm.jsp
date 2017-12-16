<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户信息模块管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/user/xlfUser/">用户信息模块列表</a></li>
		<li class="active"><a href="${ctx}/user/xlfUser/form?id=${xlfUser.id}">用户信息模块<shiro:hasPermission name="user:xlfUser:edit">${not empty xlfUser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="user:xlfUser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="xlfUser" action="${ctx}/user/xlfUser/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户名称：</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户性别1:male 2:female 0:保密：</label>
			<div class="controls">
				<form:input path="userSex" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户角色（企业发布，兼职学生）1:公司代理人 0：学生：</label>
			<div class="controls">
				<form:input path="userRole" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">user_company：</label>
			<div class="controls">
				<form:input path="userCompany" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户年龄：</label>
			<div class="controls">
				<form:input path="userAge" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">user_high：</label>
			<div class="controls">
				<form:input path="userHigh" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">user_school_id：</label>
			<div class="controls">
				<form:input path="userSchoolId" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户电话号码：</label>
			<div class="controls">
				<form:input path="userPhone" htmlEscape="false" maxlength="15" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户简历：</label>
			<div class="controls">
				<form:input path="userDetails" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户头像：</label>
			<div class="controls">
				<form:input path="userImg" htmlEscape="false" maxlength="150" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">user_weigh：</label>
			<div class="controls">
				<form:input path="userWeigh" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="user:xlfUser:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>