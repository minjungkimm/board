package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;

public interface BoardService {
/*계층별 구현-비즈니스 계층(Service)
 * 비즈니스 계층 = 컨트롤러와 DAO 사이의 접착제
 * 중간에 비즈니스 영역을 구분해서 개발 시 개발의 양 늘어남
 * 그럼에도 굳이 계층을 분리하여 개발하는 이유는?
 * 1.이 계층은 고객마다 다른 부분을 처리할 수 있는 (서비스)완충지 역할
 * 2.각 회사마다 다른 로직/규칙을 데이터베이스에 무관하게 처리할 수 있는 (서비스)완충영역 존재필요
 * 3.컨트롤러와 같은 외부 호출이 영속계층(Persistence)에 종속적인 상황을 막아줌
 * 4.만약 컨트롤러가 직접 영속계층(Persistence)의 데이터베이스를 이용하게 되면?
 * =>트랜잭션의 처리나 예외의 처리 등 모든 로직이 컨트롤러에 집중됨
 * =>비즈니스 계층(Service)은 컨트롤러로 하여금 처리해야 하는 일을 분업화 
 * */
	public void regist(BoardVO vo)throws Exception;
	public BoardVO read(Integer bno)throws Exception;
	public void modify(BoardVO vo)throws Exception;
	public void remove(Integer bno)throws Exception;
	public List<BoardVO> listAll() throws Exception;
	public List<BoardVO> listCriteria(Criteria cri)throws Exception;
	public int listCountCriteria(Criteria cri)throws Exception;
	public List<BoardVO> listSearch(SearchCriteria cri)throws Exception;
	public int listSearchCount(SearchCriteria cri)throws Exception;
}
