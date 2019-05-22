package com.cafe24.mysite.vo;

public class PageVo {

	private int pageNo;
	private int startContentNo;
	private int endContentNo;
	private int totalContentCount;
	private int contentPerPage;
	
	public PageVo(int pageNo, int startContentNo, int endContentNo, int totalContentCount, int contentPerPage) {
		this.pageNo = pageNo;
		this.startContentNo = startContentNo;
		this.contentPerPage = contentPerPage; 
		this.totalContentCount = totalContentCount;
		if(totalContentCount > startContentNo + contentPerPage) {
			this.endContentNo = totalContentCount;
		}
		this.endContentNo= startContentNo + contentPerPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if(pageNo < 0) {
			this.pageNo=1;
		}
		this.pageNo = pageNo;
	}

	public int getStartContentNo() {
		return startContentNo;
	}

	public void setStartContentNo(int startContentNo) {
		this.startContentNo = startContentNo;
	}

	public int getEndContentNo() {
		return endContentNo;
	}

	public void setEndContentNo(int endContentNo) {
		this.endContentNo = endContentNo;
	}

	public int getTotalContentCount() {
		return totalContentCount;
	}

	public void setTotalContentCount(int totalContentCount) {
		this.totalContentCount = totalContentCount;
	}

	@Override
	public String toString() {
		return "PageVo [pageNo=" + pageNo + ", startContentNo=" + startContentNo + ", endContentNo="
				+ endContentNo + ", totalContentCount=" + totalContentCount + "]";
	}

	public int getContentPerPage() {
		return contentPerPage;
	}

	public void setContentPerPage(int contentPerPage) {
		this.contentPerPage = contentPerPage;
	}
		
	
}
