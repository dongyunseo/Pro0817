package com.human.dto;

public class ReviewCountDto {
	private int pageDataCount;  // �� �������� ������ �Խñ� ��
	private int currentpageNum; // ���� ������
	private int totalDataCount; // ��ü ������ ����

	private int pageSize; 		// �� �������� ������ �Խñ� ����
	private int firstPageNum; 	// ù��° �Խñ� ��ȣ
	private int lastPageNum; 	// ������ �Խñ� ��ȣ
	private int prevPageNum; 	// ���� ������
	private int nextPageNum; 	// ���� ������
	private int startPageNum; 	// ����¡�� ���� ������ ��ȣ
	private int endPageNum; 	// ����¡�� ������ ������ ��ȣ

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

		this.pageSize = 3; // �� �������� ��� �Խñ� �ѹ��� ����ϰڴ°� ? 
		 
		this.firstPageNum = 1; // �Խñ� ����� 1 ���������� �����Ѵ�.
		this.lastPageNum = (totalDataCount - 1) / pageDataCount + 1;
		// ��ϵ� �Խñ� ���� / ���������� ��µǴ� ������ ����
		// 10�� �̻��̿��� ���������� ��ư�� ����

		this.startPageNum = ((this.currentpageNum - 1) / this.pageSize) * 3 + 1;
		// 1�������� 1�� ~ 3�� �Խñ� ��� 2�������� 4�� ~ 7�� �Խñ�
		// 1�������� 1�� �Խñ��� 0*3+1 2�������� 1*3+1=4�� �Խñ� ����
		
		this.endPageNum = this.startPageNum + 9;
		// << <( �� ���̿� ���ڰ� 9�� ���� ) > >>

		if (this.endPageNum > this.lastPageNum) {
			this.endPageNum = this.lastPageNum;
		}
		// ���� ������ Ŭ���ϸ�
		this.prevPageNum = this.startPageNum - this.pageSize;
		if (this.prevPageNum < 1) {
			prevPageNum = 1;
		}
		// ���������� Ŭ���ϸ�
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
