<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动信息管理管理</title>
<meta name="decorator" content="default" />
<link type="text/css" rel="stylesheet"
	href="/static/kindeditor/themes/simple/simple.css" />
<link type="text/css" rel="Stylesheet"
	href="/static/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="../../../../../css/js/legao-date/css/jquery-ui.css">
<link rel="stylesheet"
	href="../../../../../css/js/legao-date/css/date-range-picker.css">
<script src="../../../../../css/js/legao-date/js/jquery-ui.min.js"></script>
<script src="../../../../../css/js/legao-date/js/date-range-picker.js"></script>
<script type="text/javascript" charset="utf-8"
	src="/static/kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="/static/kindeditor/lang/zh-CN.js"></script>
<script src="../../../../../group/applyRefund/js/droparea.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});
	KindEditor.ready(function(K) {
		var editor2 = K.editor({
			allowFileManager : true,
			cssPath : '/static/kindeditor/plugins/code/prettify.css',
			uploadJson : '/static/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '/static/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
		});

		K('#inserimage').click(
				function() {
					editor2.loadPlugin('image', function() {
						editor2.plugin.imageDialog({
							imageUrl : K('#url1').val(),
							clickFn : function(url, title, width, height,
									border, align) {
								$('#smallimage').attr("src", url);
								$("#smallimage").show();
								$('#matchLogo').val(url);
								/* alert(url); */
								editor2.hideDialog();
							}
						});
					});
				});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/xlfActivity/">活动信息管理列表</a></li>
		<li class="active"><a
			href="${ctx}/activity/xlfActivity/form?id=${xlfActivity.id}">活动信息管理<shiro:hasPermission
					name="activity:xlfActivity:edit">${not empty xlfActivity.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="activity:xlfActivity:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="xlfActivity"
		action="${ctx}/activity/xlfActivity/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">活动名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="30"
					class="input-xlarge required" />
				<span><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否校园活动：</label>
			<div class="controls">
				<form:radiobutton path="ifSchool" htmlEscape="false"
					value="0" label="是" class="input-xlarge required" />
				<form:radiobutton path="ifSchool" htmlEscape="false"
					value="1" label="否" class="input-xlarge required" />
				<span><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动图片：</label>
			<div class="controls">
				<%-- <form:input path="activity_pic" htmlEscape="false" maxlength="100" class="input-xlarge "/> 
				<input type="hidden" id="activitypic" name="activitypic" path="activitypic"
					htmlEscape="false" maxlength="255" class="input-xlarge required"
					value="${xlfActivity.activitypic}" /> <img src="${xlfActivity.activitypic}"
					id="smallimage"
					<c:if test="${empty article.image}"> stype="display:none"</c:if>
					width="50px" height="40px"> <input type="button"
					id="inserimage" value="选择文件" />
				--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动省份：</label>
			<div class="controls">
				<form:input path="province" htmlEscape="false"
					maxlength="10" class="input-xlarge required" />
				<span><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动市：</label>
			<div class="controls">
				<form:input path="city" htmlEscape="false" maxlength="20"
					class="input-xlarge required" />
				<span><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动区：</label>
			<div class="controls">
				<form:input path="district" htmlEscape="false"
					maxlength="20" class="input-xlarge required" />
				<span><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动位置：</label>
			<div class="controls">
				<form:input path="location" htmlEscape="false"
					maxlength="30" class="input-xlarge required" />
				<span><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动门牌号：</label>
			<div class="controls">
				<form:input path="door" htmlEscape="false"
					maxlength="20" class="input-xlarge required" />
				<span><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">纬度：</label>
			<div class="controls">
				<form:input path="latitude" htmlEscape="false"
					class="input-xlarge required" />
				<span><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经度：</label>
			<div class="controls">
				<form:input path="longitude" htmlEscape="false"
					class="input-xlarge required" />
				<span><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动人数：</label>
			<div class="controls">
				<form:input path="person" htmlEscape="false" maxlength="10"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动开始时间：</label>
			<div class="controls">
				<input name="startTime" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${xlfActivity.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				<span><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动结束时间：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${xlfActivity.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<c:if test="${ xlfActivity.id != null }">
			<div class="control-group">
				<label class="control-label">审核状态：</label>
				<div class="controls">
					<form:radiobutton path="status" htmlEscape="false"
						value="0" label="未审核" class="input-xlarge " />
					<form:radiobutton path="status" htmlEscape="false"
						value="1" label="审核通过" class="input-xlarge " />
					<form:radiobutton path="status" htmlEscape="false"
						value="2" label="结束报名" class="input-xlarge " />
				</div>
		</c:if>
		</div>
		<div class="control-group">
			<label class="control-label">活动详情：</label>
			<div class="controls">
				<form:textarea path="details" htmlEscape="false"
					maxlength="200" id="content" rows="4" stlye="width:95%"
					class="input-xlarge " />
				<sys:ckeditor replace="content" uploadPath="/uploads" />
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="activity:xlfActivity:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>