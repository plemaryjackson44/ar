<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/views/portal-common/portal-tag.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>发布新帖-信电校友录</title>
<%@ include file="/WEB-INF/views/portal-common/portal-meta.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/portal-common/header.jsp"%>

	<%@ include file="/WEB-INF/views/portal-common/navmenu.jsp"%>


	<div class="banner-bottom">
		<div class="container higher" id="container">
			<section>
				<!-- 导航栏 -->
				<div class="header">
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i>&nbsp;<a href="index.action">主页</a></li>
						<li><a href="forum.action">校友论坛</a></li>
						<li class="active">发布新帖</li>
					</ol>
				</div>

				<div class="contentpane1">
					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">招聘信息</h4>
								</div>
								<div class="panel-body panel-body-nopadding">

									<div id="progressWizard" class="basic-wizard">

										<div class="tab-content">

												<form class="form" id="postForm" method="post" action="post/save.action">
													<div class="form-group">
														<label class="col-sm-4"><span class="asterisk">*
														</span>帖子标题</label>
														<div class="col-sm-4">
															<input type="text" name="infoTitle" id="infoTitle" maxlength="50"
																class="form-control tooltips" data-trigger="hover"
																data-toggle="tooltip" data-original-title="2-50字" />
														</div>
													</div>

													<div class="form-group">
														<label class="col-sm-4"><span class="asterisk">*
														</span>帖子主题</label>
														<div class="col-sm-4">
															<div class="input-group">
																<select class="select2-container select2" name="infoType" id="infoType" style="width: 100%">
																	<option value="">--</option>
																	<c:forEach items="${themes}" var="theme">
																		<option value="${theme.value}">${theme.name}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>

													<div class="form-group">
														<label class="col-sm-4"><span class="asterisk">*
														</span>帖子内容</label>
														<div class="col-sm-7">
															<div class="input-group">
																<textarea rows="7" style="height: 150.4px;"
																  name="content" id="content"
																  class="form-control tooltips" data-trigger="hover"
																  data-toggle="tooltip" data-original-title="20-500字">
																</textarea>
															</div>
														</div>
													</div>

												</form>

										</div>
										<!-- tab-content -->

										<ul class="pager wizard">
											<li><a href="javascript:;" onclick="savePost()" id="submitBtn">发布新帖</a></li>&nbsp;
											<li><a href="forum.action">放弃发布</a></li>
										</ul>

									</div>
								</div>
							</div>
						</div>
					</div>
					<%@ include file="/WEB-INF/views/portal-common/instruction.jsp"%>
				</div>
			</section>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/portal-common/footer.jsp"%>

</body>
<%@ include file="/WEB-INF/views/portal-common/portal-js.jsp"%>
<script src="assets/script/forum/post-add.js"></script>
<%--<script src="assets/js/jquery.tagsinput.min.js"></script>--%>
<%--<script>--%>
    <%--$(function () {--%>
        <%--jQuery('#tags').tagsInput({width: 'auto'});--%>
    <%--});--%>
<%--</script>--%>

</html>