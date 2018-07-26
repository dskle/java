<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HOME</title>
</head>
<body>
	<div class="container-fluid">
		<!-- nav -->
		<jsp:include page="../common/menu.jsp"></jsp:include>
		<!-- nav -->
		<!-- body -->
		<div class="row">
			<jsp:include page="../common/sidemenu.jsp"></jsp:include>
			<main role="main" class="col-md-10 ml-sm-auto col-lg-10 px-4">
			<div align="center">
				<img src="resources/index_images/index.png" />
			</div>
			</main>
		</div>
		<!-- body -->
		<!-- footer -->
		<jsp:include page="../common/footer.jsp"></jsp:include>
		<!-- footer -->
	</div>
	<jsp:include page="../common/bootstrap.jsp"></jsp:include>
</body>
</html>

