package org.zerock.domain;

public class Criteria {
	/*Criteria=검색기준,분류기준*/
	private int page;
	private int perPageNum;
	
	public Criteria() {
		this.page=1;
		this.perPageNum=10;
	}
	
	public void setPage(int page){
		if(page<=0){
			this.page=1;
			return;
		}
		this.page=page;
	}
	
	public void setPerPageNum(int perPageNum){
		if(perPageNum<=0||perPageNum>100){
			this.perPageNum=10;
			return;
		}
		this.perPageNum=perPageNum;
	}
	
	public int getPage(){
		return page;
	}
	
	//method for MyBatis SQL Mapper-
	public int getPageStart(){
		//limit 구문에서 시작위치를 지정할 때 사용
		//ex)3페이지의 데이터는 limit 20,10과 같은 형태가 되어야합니다
		//공식) 시작데이터번호 = (페이지번호-1)*페이지당 보여지는 개수
		// 20=(3-1)*10
		return (this.page-1)*perPageNum;
	}
	
	//method for MyBatis SQL Mapper-
	public int getPerPageNum(){
		//limit 뒤의 숫자를 의미, 한 페이지당 보여지는 갯수의미
		return this.perPageNum;
	}
	
}
