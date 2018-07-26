<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="springform"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>등록결과</title>
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
			<!-- content --> <!--사진추가하기위한 타입-->
			<div class="card text-center" style="width: 500px">
				<img class="card-img-top" src="show_image/${dog_no}" alt="dog image">
				<div class="card-body text-center">
					<div>
						<h4>${infoDataView.getName()}</h4>
					</div>
					<div class="row">
						<div class="col-md-3 col-lg-3" align="center"></div>
						<div class="col-md-3 col-lg-3" align="center">학사번호</div>
						<div class="col-md-4 col-lg-4" align="center">${dog_no}</div>
						<div class="col-md-1 col-lg-1" align="center"></div>
					</div>
					<div class="row">
						<div class="col-md-3 col-lg-3" align="center"></div>
						<div class="col-md-3 col-lg-3" align="center">종</div>
						<div class="col-md-4 col-lg-4" align="center">${infoDataView.getSpecies()}</div>
						<div class="col-md-1 col-lg-1" align="center"></div>
					</div>
					<div class="row">
						<div class="col-md-3 col-lg-3" align="center"></div>
						<div class="col-md-3 col-lg-3" align="center">생년월일</div>
						<div class="col-md-4 col-lg-4" align="center">${infoDataView.getBirth()}</div>
						<div class="col-md-1 col-lg-1" align="center"></div>
					</div>
					<div class="row">
						<div class="col-md-3 col-lg-3" align="center"></div>
						<div class="col-md-3 col-lg-3" align="center">전화번호</div>
						<div class="col-md-4 col-lg-4" align="center">${infoDataView.getTel()}</div>
					</div>
				</div>
			</div>
			</main>
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