package com.mycom.myapp.connector;

public class PagingInfo {
	private int nCurrentPage = 0; // 현재페이지번호
	private int nTotalPage = 0; // 전체 페이지번호(수)
	private int nRecordsPerPage = 0; // 1페이당 표시하는 레코드 수
	private int nTotalRecords = 0; // 전체 건수
	
	public PagingInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PagingInfo(int nCurrentPage, int nTotalPage, int nRecordsPerPage, int nTotalRecords) {
		super();
		this.nCurrentPage = nCurrentPage;
		this.nTotalPage = nTotalPage;
		this.nRecordsPerPage = nRecordsPerPage;
		this.nTotalRecords = nTotalRecords;
	}

	public int getnCurrentPage() {
		return nCurrentPage;
	}

	public void setnCurrentPage(int nCurrentPage) {
		this.nCurrentPage = nCurrentPage;
	}

	public int getnTotalPage() {
		return nTotalPage;
	}

	public void setnTotalPage(int nTotalPage) {
		this.nTotalPage = nTotalPage;
	}

	public int getnRecordsPerPage() {
		return nRecordsPerPage;
	}

	public void setnRecordsPerPage(int nRecordsPerPage) {
		this.nRecordsPerPage = nRecordsPerPage;
	}

	public int getnTotalRecords() {
		return nTotalRecords;
	}

	public void setnTotalRecords(int nTotalRecords) {
		this.nTotalRecords = nTotalRecords;
	}
	
	
	

}
