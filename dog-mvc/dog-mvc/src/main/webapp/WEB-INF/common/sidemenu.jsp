<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

li a {
	display: block;
	color: #000;
	padding: 8px 16px;
	text-decoration: none;
}

/* Change the link color on hover */
li a:hover {
	background-color: #f3dd928c;
	color: #51565a;
}
</style>
<!-- 사이드 -->
<nav class="col-md-2 d-none d-md-block bg sidebar"
	style="background-color: #e6ce7d17;">
	<div class="sidebar-sticky">
		<ul class="nav flex-column">
		</ul>
		<h6
			class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
			<span style="color: #51565a; font-weight: bold;">정보관리</span> <a
				class="d-flex align-items-center text-muted" href="#"> <svg
					xmlns="http://www.w3.org/2000/svg" width="24" height="24"
					viewBox="0 0 24 24" fill="none" stroke="currentColor"
					stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
					class="feather feather-plus-circle"></svg>
			</a>
		</h6>
		<ul class="nav flex-column mb-2">
			<li class="nav-item"><a class="nav-link" style="color: #51565a;"
				href="/info/info_all">정보 조회</a></li>
			<li class="nav-item"><a class="nav-link" style="color: #51565a;"
				href="/info/info_add">정보 등록</a></li>
		</ul>
		<hr />
		<h6
			class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
			<span style="color: #51565a; font-weight: bold;">성적관리</span> <a
				class="d-flex align-items-center text-muted" href="#"> <svg
					xmlns="http://www.w3.org/2000/svg" width="24" height="24"
					viewBox="0 0 24 24" fill="none" stroke="currentColor"
					stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
					class="feather feather-plus-circle"></svg>
			</a>
		</h6>
		<ul class="nav flex-column mb-2">
			<li class="nav-item"><a class="nav-link" style="color: #51565a;"
				href="/score/score_all">성적 조회</a></li>
			<li class="nav-item"><a class="nav-link" style="color: #51565a;"
				href="/score/score_add_pre">성적 등록</a></li>
		</ul>
		<hr />
		<ul class="nav flex-column mb-2">
			<li class="nav-item"><a class="nav-link"
				style="color: #51565a; font-weight: bold;" href="/board/board_list">게시판</a></li>
		</ul>
		<hr />
		<ul class="nav flex-column mb-2">
			<li class="nav-item"><a class="nav-link"
				style="color: #51565a; font-weight: bold;" href="#">오시는 길</a></li>
		</ul>
	</div>
</nav>