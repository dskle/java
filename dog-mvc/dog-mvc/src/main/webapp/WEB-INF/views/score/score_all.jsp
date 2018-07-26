<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>전체조회</title>
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
					<h4>정보 조회</h4>
				</div>
				<div class="card-body">
					<table class="table text-center">
						<tr>
							<td align="left">${totalCounts}건수</td>
							<td colspan="7" align="right">표시건수
								<button class="btn btn-secondary dropdown-toggle" type="button"
									id="dropdownMenuButton" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">${recordsPerPage}</button>
								<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
									<a class="dropdown-item" href="score_all?recordsPerPage=5">5</a>
									<a class="dropdown-item" href="score_all?recordsPerPage=6">6</a>
									<a class="dropdown-item" href="score_all?recordsPerPage=7">7</a>
									<a class="dropdown-item" href="score_all?recordsPerPage=8">8</a>
									<a class="dropdown-item" href="score_all?recordsPerPage=9">9</a>
									<a class="dropdown-item" href="score_all?recordsPerPage=10">10</a>
								</div>
							</td>
						</tr>
						<tr>
							<td>학번</td>
							<td>이름</td>
							<td>건강</td>
							<td>어질리티</td>
							<td>노즈워크</td>
							<td>평균</td>
							<td>총 학점</td>
						</tr>
						<c:forEach items="${al_sub_scoreData}" var="scoreData">
							<tr>
								<td>${scoreData.getDog_no()}</td>
								<td>${scoreData.getName()}</td>
								<td>${scoreData.getHealth()}</td>
								<td>${scoreData.getAgility()}</td>
								<td>${scoreData.getNose_work()}</td>
								<td><fmt:formatNumber value="${scoreData.getAvg()}"
										pattern=".00" /></td>
								<td>${scoreData.getGrade()}</td>
							</tr>
						</c:forEach>
					</table>
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">
							<li class="page-item"><a class="page-link"
								style="color: #51565a;" href="score_all?separation=first"><i
									class="fas fa-angle-double-left"></i></a></li>
							<li class="page-item"><a class="page-link"
								style="color: #51565a;" href="score_all?separation=previous"><i
									class="fas fa-angle-left"></i></a></li>
							<li class="page-item"><a class="page-link"> <span
									class="badge">${currentPage}</span>&nbsp;/&nbsp;<span
									class="badge">${totalPage}</span></a></li>
							<li class="page-item"><a class="page-link"
								style="color: #51565a;" href="score_all?separation=next"><i
									class="fas fa-angle-right"></i></a></li>
							<li class="page-item"><a class="page-link"
								style="color: #51565a;" href="score_all?separation=last"><i
									class="fas fa-angle-double-right"></i></a></li>
						</ul>
					</nav>

				</div>
				<!-- content -->
			</div>
			<!-- body --> <!-- footer --> <jsp:include
				page="../../common/footer.jsp"></jsp:include> <!-- footer -->
		</div>
		<jsp:include page="../../common/bootstrap.jsp"></jsp:include>
</body>
</html>