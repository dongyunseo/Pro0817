package com.human.dto;

public class ReviewCountDto {
	private int pageDataCount;  // 한 페이지에 보여줄 게시글 수
	private int currentpageNum; // 현재 페이지
	private int totalDataCount; // 전체 데이터 개수

	private int pageSize; 		// 한 페이지에 보여줄 게시글 개수
	private int firstPageNum; 	// 첫번째 게시글 번호
	private int lastPageNum; 	// 마지막 게시글 번호
	private int prevPageNum; 	// 이전 페이지
	private int nextPageNum; 	// 다음 페이지
	private int startPageNum; 	// 페이징의 시작 페이지 번호
	private int endPageNum; 	// 페이징의 마지막 페이지 번호

	@Override
	public String toString() {
		return "ReviewCountDto [pageDataCount=" + pageDataCount + ", currentpageNum=" + currentpageNum
				+ ", totalDataCount=" + totalDataCount + ", pageSize=" + pageSize + ", firstPageNum=" + firstPageNum
				+ ", lastPageNum=" + lastPageNum + ", prevPageNum=" + prevPageNum + ", nextPageNum=" + nextPageNum
				+ ", startPageNum=" + startPageNum + ", endPageNum=" + endPageNum + "]";
	}

	// << < 1 2 3 4 5 > >>
	public void makePage(int page, int pageDataCount, int totalDataCount) {
		if (totalDataCount == 0)
			return;
		this.totalDataCount = totalDataCount;
		this.currentpageNum = page;
		this.pageDataCount = pageDataCount;

		this.pageSize = 3; // 한 페이지에 몇개의 게시글 넘버를 출력하겠는가 ? 
		 
		this.firstPageNum = 1; // 게시글 출력은 1 페이지부터 시작한다.
		this.lastPageNum = (totalDataCount - 1) / pageDataCount + 1;
		// 등록된 게시글 개수 / 한페이지당 출력되는 페이지 개수
		// 10개 이상이여야 다음페이지 버튼이 생김

		this.startPageNum = ((this.currentpageNum - 1) / this.pageSize) * 3 + 1;
		// 1페이지는 1번 ~ 3번 게시글 출력 2페이지는 4번 ~ 7번 게시글
		// 1페이지는 1번 게시글은 0*3+1 2페이지는 1*3+1=4번 게시글 시작
		
		this.endPageNum = this.startPageNum + 9;
		// << <( 이 사이에 숫자가 9개 까지 ) > >>

		if (this.endPageNum > this.lastPageNum) {
			this.endPageNum = this.lastPageNum;
		}
		// 이전 페이지 클릭하면
		this.prevPageNum = this.startPageNum - this.pageSize;
		if (this.prevPageNum < 1) {
			prevPageNum = 1;
		}
		// 다음페이지 클릭하면
		this.nextPageNum = this.endPageNum + this.pageSize;
		if (this.nextPageNum > this.lastPageNum) {
			this.nextPageNum = this.lastPageNum;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartPageNum() {
		return startPageNum;
	}

	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}

	public int getEndPageNum() {
		return endPageNum;
	}

	public void setEndPageNum(int endPageNum) {
		this.endPageNum = endPageNum;
	}

	public int getPageDataCount() {
		return pageDataCount;
	}

	public void setPageDataCount(int pageDataCount) {
		this.pageDataCount = pageDataCount;
	}

	public int getCurrentpageNum() {
		return currentpageNum;
	}

	public void setCurrentpageNum(int currentpageNum) {
		this.currentpageNum = currentpageNum;
	}

	public int getTotalDataCount() {
		return totalDataCount;
	}

	public void setTotalDataCount(int totalDataCount) {
		this.totalDataCount = totalDataCount;
	}

	public int getFirstPageNum() {
		return firstPageNum;
	}

	public void setFirstPageNum(int firstPageNum) {
		this.firstPageNum = firstPageNum;
	}

	public int getLastPageNum() {
		return lastPageNum;
	}

	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}

	public int getPrevPageNum() {
		return prevPageNum;
	}

	public void setPrevPageNum(int prevPageNum) {
		this.prevPageNum = prevPageNum;
	}

	public int getNextPageNum() {
		return nextPageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

}
