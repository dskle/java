<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="springform"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<style>
.error {
	color: #ff0000;
	font_style: italic;
	font_weight: bold;
}
</style>
<script type="text/javascript">
//아이디 체크 여부 확인(중복 일 경우 0, 중복이 아닐경우 1)
var idck=0;
$(funcion){
	$("#idck").click(function(){
		
		//userid를 param
		var user_id = $("#user_id").val();
		
		$.ajax({
			async:true,
			type:'post',
			data: user_id,
			url:"idcheck.do",
			dataType:"json",
			contentType:"application/json; charset=UTF-8",
			success:function(data){
				if(data.cnt > 0){
					alert("아이디가 존재합니다. 다른 아이디를 입력해주세요");
					//아이디가 있으면 빨강 없으면 파랑 처리
					$("#divInputId").addClass("has-error")
					$("#divInputId").removeClass("has-success")
					$("#user_id").focus();
				}else{
					alert("사용가능한 아이디입니다.");
					//아이디가 있으면 빨강 없으면 파랑 처리
					$("#divInputId").addClass("has-success")
					$("#divInputId").removeClass("has-error")
					$("#password").focus();
					//중복되지않으면
					idck = 1;
				}
			}, error:funtion(error){
				alert("error:"+error);
			}
		})
		
	})
}
</script>
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
			<!-- content --> <springform:form action="join_process"
				modelAttribute="userData" method="post">
				<div class="card text-center" style="width: 800px">
					<div class="card-header">
						<h4>회원가입</h4>
					</div>
					<div class="card-body">
						<div class="container">
							<div class="row">
								<div class="col-4">
									<p class="text-right">아이디</p>
								</div>
								<form method="post" action="">
									<div class="col">
										<springform:input path="user_id" />
									</div>
									<div class="col">
										<input type="button" class="btn btn-sm btn-warning"
											value="중복확인" onclick="idck()">
									</div>
								</form>
								<div class="col"></div>
							</div>
							<springform:errors path="user_id" cssClass="error"></springform:errors>
							<br />
							<div class="row">
								<div class="col-4">
									<p class="text-right">비밀번호</p>
								</div>
								<div class="col">
									<springform:password path="password" showPassword="true" />
								</div>
								<div class="col"></div>
								<div class="col"></div>
							</div>
							<springform:errors path="password" cssClass="error" />
						</div>
						<br /> <br />
						<div>
							<input type="submit" value="등록" class="btn btn-lg btn-warning" />
						</div>
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