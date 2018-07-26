<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>성적 등록</title>
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
			<div class="card text-center" style="width: 800px">
				<div class="card-header">
					<h4>성적 등록</h4>
				</div>
				<div class="card-body text-center">
					<form action="score_add_pre_process" method="post">
						<div>
							이름 <input type="text" name="name" />
						</div>
						<br/>
						<input type="submit" value="검색" class="btn btn-sm btn-warning" />
						<!-- <table>
							<tr>
								<td>이름 입력</td>
								<td><input type="text" name="name" /></td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" value="검색"
									class="btn btn-sm btn-warning" /></td>
							</tr>
						</table> -->
					</form>
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