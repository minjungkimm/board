package org.zerock.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;//화면에 보여지는 페이지 번호의 숫자
	//10일경우 = 10개의 페이지번호가 출력
	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();//중요한 것
	}
	
	private void calcData(){
		endPage =(int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		//cri클래스에서 얻은 page값을 대입한뒤 정수화/보여줄페이지번호갯수 1~10까지=10*10 
		//ex)(3/10)*10
		//=>1*10=>10
		//endPage = 10
		startPage =(endPage-displayPageNum)+1;
		//10-10+1= 1
		//즉 시작페이지는 1 , 끝페이지는 10
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
