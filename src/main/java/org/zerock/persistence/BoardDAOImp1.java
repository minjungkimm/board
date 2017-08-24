package org.zerock.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
@Repository
public class BoardDAOImp1 implements BoardDAO{
	@Inject
	private SqlSession session;
	
	private static String namespace="org.zerock.mapper.BoardMapper";

	@Override
	public void create(BoardVO vo) throws Exception {
		session.insert(namespace+".create",vo);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return session.selectOne(namespace+".read",bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		session.update(namespace+".update",vo);
	}

	@Override
	public void delete(Integer bno) throws Exception {
		session.delete(namespace+".delete",bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return session.selectList(namespace+".listAll");
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if(page<=0){
			page=1;
		}
		page =(page-1)*10;
		//(1-1) 0*10; = page 는 0 if문에서 한번 걸러진것이라 1!! => 10으로 계산됨
		//(2-1) 1*10; = page 는 10
		//(3-1) 2*10; = page 는 20 
		return session.selectList(namespace+".listPage",page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		
		return session.selectList(namespace+".listCriteria",cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		
		return session.selectOne(namespace+".countPaging",cri);
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		
		return session.selectList(namespace+".listSearch",cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		
		return session.selectOne(namespace+".listSearchCount",cri);
	}
	
}
