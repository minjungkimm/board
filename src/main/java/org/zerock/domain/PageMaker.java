package org.zerock.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;//ȭ�鿡 �������� ������ ��ȣ�� ����
	//10�ϰ�� = 10���� ��������ȣ�� ���
	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();//�߿��� ��
	}
	
	private void calcData(){
		endPage =(int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		//criŬ�������� ���� page���� �����ѵ� ����ȭ/��������������ȣ���� 1~10����=10*10 
		//ex)(3/10)*10
		//=>1*10=>10
		//endPage = 10
		startPage =(endPage-displayPageNum)+1;
		//10-10+1= 1
		//�� ������������ 1 , ���������� 10
		int tempEndPage =(int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		
		if(endPage>tempEndPage){
			endPage=tempEndPage;
		}
		prev= startPage ==1?false:true;
		next= endPage * cri.getPerPageNum() >= totalCount ?false:true;
	}
	
	public String makeQuery(int page) {
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page",page)
				.queryParam("perPageNum",cri.getPerPageNum())
				.build();
		
		System.out.println(uriComponents.toString());
		return uriComponents.toString();
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public Criteria getCri() {
		return cri;
	}
	
}
