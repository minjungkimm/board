package org.zerock.domain;

public class Criteria {
	/*Criteria=�˻�����,�з�����*/
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
		//limit �������� ������ġ�� ������ �� ���
		//ex)3�������� �����ʹ� limit 20,10�� ���� ���°� �Ǿ���մϴ�
		//����) ���۵����͹�ȣ = (��������ȣ-1)*�������� �������� ����
		// 20=(3-1)*10
		return (this.page-1)*perPageNum;
	}
	
	//method for MyBatis SQL Mapper-
	public int getPerPageNum(){
		//limit ���� ���ڸ� �ǹ�, �� �������� �������� �����ǹ�
		return this.perPageNum;
	}
	
}
