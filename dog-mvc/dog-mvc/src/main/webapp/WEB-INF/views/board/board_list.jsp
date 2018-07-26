<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>게시판</title>
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
			<table class="table">
				<thead>
					<tr>
						<td align="left">${totalCounts}건수</td>
						<td colspan="7" align="right">표시건수
							<button class="btn btn-secondary dropdown-toggle" type="button"
								id="dropdownMenuButton" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">${recordsPerPage}</button>
							<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								<a class="dropdown-item" href="board_list?recordsPerPage=5">5</a>
								<a class="dropdown-item" href="board_list?recordsPerPage=6">6</a>
								<a class="dropdown-item" href="board_list?recordsPerPage=7">7</a>
								<a class="dropdown-item" href="board_list?recordsPerPage=8">8</a>
								<a class="dropdown-item" href="board_list?recordsPerPage=9">9</a>
								<a class="dropdown-item" href="board_list?recordsPerPage=10">10</a>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach varStatus="status" items="${al_sub_boardDataView}" var="boardData">
						<tr>
							<th>${(totalCounts-status.index)-((currentPage-1)*recordsPerPage)}</th>
							<td><a href="board_view/${boardData.getId()}" Style="color: black; ">${boardData.getTitle()}<c:if
										test="${board.attached_file ne null || board.attached_file !=''}">
										<img src="/resources/images/disket.png" width="5%" />
									</c:if></a></td>
							<td>${boardData.getCreator()}</td>
							<td><fmt:formatDate value="${boardData.getCreated_date()}"
									pattern="yyyy-MM-dd" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link"
						style="color: #51565a;" href="info_all?separation=first"><i
							class="fas fa-angle-double-left"></i></a></li>
					<li class="page-item"><a class="page-link"
						style="color: #51565a;" href="info_all?separation=previous"><i
							class="fas fa-angle-left"></i></a></li>
					<li class="page-item"><a class="page-link"> <span
							class="badge">${currentPage}</span>&nbsp;/&nbsp;<span
							class="badge">${totalPage}</span></a></li>
					<li class="page-item"><a class="page-link"
						style="color: #51565a;" href="info_all?separation=next"><i
							class="fas fa-angle-right"></i></a></li>
					<li class="page-item"><a class="page-link"
						style="color: #51565a;" href="info_all?separation=last"><i
							class="fas fa-angle-double-right"></i></a></li>
				</ul>
			</nav>
			<div class="text-right">
				<a href="board_write" class="btn btn-warning">글쓰기</a>
			</div>
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