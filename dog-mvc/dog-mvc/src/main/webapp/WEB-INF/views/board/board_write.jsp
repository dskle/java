<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>
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
			<form action="board_write_process" method="post"
				enctype="multipart/form-data">
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="creator">이름</label> <input
							type="text" class="form-control" id="creator" name="creator" />
					</div>
				</div>
				<div class="form-group">
					<label for="title">제목</label> <input type="text"
						class="form-control" id="title" name="title">
				</div>
				<div class="form-group">
					<label for="message">내용</label> <textarea rows="10" cols="40" class="form-control" name="message"></textarea>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="attached_file">첨부파일</label> <input type="file" id="attached_file" name="attached_file"
							class="form-control">
					</div>	
				</div>
				<button type="submit" class="btn btn-warning">저장</button>
			</form>
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