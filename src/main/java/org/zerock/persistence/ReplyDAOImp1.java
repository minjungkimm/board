package org.zerock.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

@Repository
public class ReplyDAOImp1 implements ReplyDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static String namespace="org.zerock.mapper.ReplyMapper";
	
	@Override
	public List<ReplyVO> list(Integer bno) throws Exception {
		
		return sqlSession.selectList(namespace+".list",bno);
	}

	@Override
	public void create(ReplyVO reply) throws Exception {
		
		sqlSession.insert(namespace+".create",reply);
	}

	@Override
	public void update(ReplyVO reply) throws Exception {
		
		sqlSession.update(namespace+".update",reply);
	}

	@Override
	public void delete(Integer rno) throws Exception {
		
		sqlSession.delete(namespace+".delete",rno);
	}

	@Override
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		
		Map<String,Object> map = new HashMap<>();
		map.put("bno",bno);
		map.put("cri",cri);
		
		return sqlSession.selectList(namespace+".listPage",map);
	}

	@Override
	public int count(Integer bno) throws Exception {
		
		return sqlSession.selectOne(namespace+".count",bno);
	}

}
