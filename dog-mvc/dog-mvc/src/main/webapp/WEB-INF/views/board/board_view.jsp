<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글읽기</title>
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
			<!-- content -->
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>${board.creator}</label>
				</div>
				<div class="form-group col-md-6">
					<label>${board.created_date}</label>
				</div>
			</div>
			<hr />
			<div class="form-group">
				<label>${board.title}</label>
			</div>
			<hr />
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="attached_file"><img
						src="${upload}${board.attached_file}" alter="그림없음" width="65%"/></label>
				</div>
			</div>
			<div class="form-group">
				<label>${board.message}</label>
			</div>
			<!-- content --> </main>
		</div>
		<!-- body -->
		<!-- footer -->
		<jsp:include page="../../common/footer.jsp"></jsp:include>
		<!-- footer -->
	</div>
	<jsp:include page="../../common/bootstrap.jsp"></jsp:include>
</body>
</html>