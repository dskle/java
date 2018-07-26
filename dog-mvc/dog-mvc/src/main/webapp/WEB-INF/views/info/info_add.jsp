<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="springform"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>정보등록</title>
<style>
.error {
	color: #ff0000;
	font_style: italic;
	font_weight: bold;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<!-- nav -->
		<jsp:include page="../../common/menu.jsp"></jsp:include>
		<!-- nav -->
		<!-- body -->
		<div class="row">
			<jsp:include page="../../common/sidemenu.jsp"></jsp:include>
			<main role="main" class="col-md-10 ml-sm-auto col-lg-10 px-4">
			<!-- content --> <springform:form action="info_add_process"
				modelAttribute="infoDataView" method="post"
				enctype="multipart/form-data">
				<!--사진추가하기위한 타입-->
				<div class="card text-center" style="width: 800px">
					<div class="card-header">
						<h4>정보 등록</h4>
					</div>
					<div class="card-body">
						<div>
							<p>이름</p>
							<span><springform:input path="name" /><br/> <springform:errors
									path="name" cssClass="error"></springform:errors> </span>
						</div>
						<br />
						<div>
							<p>종</p>
							<span><springform:input path="species" /><br/><springform:errors
									path="species" cssClass="error" /> </span>
						</div>
						<br />
						<div>
							<p>생년월일(yyyyMMdd)</p>
							<span><springform:input path="birth" maxlength="8" /><br /> <springform:errors
									path="birth" cssClass="error"></springform:errors></span>
						</div>
						<br />
						<div>
							<p>전화번호</p>
							<span><springform:input path="tel" maxlength="11" /><br /> <springform:errors
									path="tel" cssClass="error"></springform:errors></span>
						</div>
						<br />
						<div>
							<span><input type="file" name="picture" /></span>
						</div>
					</div>
					<div class="card-footer text-muted">
						<input type="submit" value="등록" class="btn btn-lg btn-warning" />
					</div>
				</div>
			</springform:form> </main>
			<!-- content -->
		</div>
		<!-- body -->
		<!-- footer -->
		<jsp:include page="../../common/footer.jsp"></jsp:include>
		<!-- footer -->
	</div>
	<jsp:include page="../../common/bootstrap.jsp"></jsp:include>
</body>
</html>