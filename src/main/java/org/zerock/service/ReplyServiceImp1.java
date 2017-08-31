package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.persistence.ReplyDAO;

@Service
public class ReplyServiceImp1 implements ReplyService {
	@Inject
	private ReplyDAO dao;
	
	@Override
	public List<ReplyVO> listReply(Integer bno) throws Exception {
		
		return dao.list(bno);
	}

	@Override
	public void addReply(ReplyVO reply) throws Exception {
		
		dao.create(reply);
	}

	@Override
	public void modifyReply(ReplyVO reply) throws Exception {
		
		dao.update(reply);
	}

	@Override
	public void removeReply(Integer rno) throws Exception {
		
		dao.delete(rno);
	}

	@Override
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception {
		
		return dao.listPage(bno, cri);
	}

	@Override
	public int count(Integer bno) throws Exception {
		
		return dao.count(bno);
	}

}
