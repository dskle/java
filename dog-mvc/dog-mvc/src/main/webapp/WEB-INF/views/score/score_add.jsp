<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="springform"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>성적등록</title>
<style>
.error {
	color: #ff0000;
	font_style: italic;
	font_weight: bold;
}
</style>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- <script>
	function get_data() {
		name_input : document.getElementById('name_input').value
		//비동기
		$.getJSON({
			url : 'http://localhost:8080/api/get_name()',
			data : {
				//값 전달
				name_input = name_input
			},
			success : function(data) {
				document.getElementById('name').innerHTML = data.name;
				document.getElementById('dog_no').innerHTML = data.dog_no;
				document.getElementById('tel').innerHTML = data.tel;
			},
			error : function() {
				alert('이름이 없습니다');
			}
		})
	}
</script> -->
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
			<!-- content --> <springform:form action="score_add_process"
				modelAttribute="scoreDataView" method="post">
				<div class="card text-center" style="width: 800px">
					<div class="card-header">
						<h4>성적 등록</h4>
					</div>
					<div class="card-body">

						<!-- <div>
							이름입력 <input type="text" id="name_input" />
							<table>
								<tr>
									<td><div id="dog_no"></div></td>
									<td><div id="name"></div></td>
									<td><div id="tel"></div></td>
								</tr>
							</table>
						</div>
						<input type="button" value="검색" onclick="get_data()"
							class="btn btn-sm btn-warning" /> -->
						<%-- <br />
						<div>
							<p>종</p>
							<span><springform:input path="species" /><br /> <springform:errors
									path="species" cssClass="error" /> </span>
						</div> --%>

						<div>
							<p>
								이름 
								<springform:input path="name" value="${name}" readonly="true" />
								<%-- <input type="text" name="name" value="${name}" disabled="disabled"> --%>
							</p>
						</div>
						<div>
							<p>
								학사번호 
								<springform:input path="dog_no" value="${dog_no}" readonly="true" />
								<%-- <input type="text" name="dog_no" value="${dog_no}" disabled="disabled"> --%>
							</p>
						</div>
						<br />
						<div>
							<p>건강</p>
							<span><springform:input path="health" /><br /> <springform:errors
									path="health" value="" cssClass="error"></springform:errors></span>
						</div>
						<br />
						<div>
							<p>어질리티</p>
							<span><springform:input path="agility" /><br /> <springform:errors
									path="agility" cssClass="error"></springform:errors></span>
						</div>
						<br />
						<div>
							<p>노즈워크</p>
							<span><springform:input path="nose_work" /><br /> <springform:errors
									path="nose_work" cssClass="error"></springform:errors></span>
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