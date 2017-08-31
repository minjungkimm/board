package org.zerock.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;
import org.zerock.persistence.ReplyDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
locations={"file:src/main/webapp/WEB-INF/**/root-context.xml"})
public class BoardDAOTest {
	@Inject
	private ReplyDAO dao;
	//private BoardDAO dao;
	private static Logger logger=LoggerFactory.getLogger(BoardDAOTest.class);
	
/*	@Test
	public void testCreate()throws Exception{
		ReplyVO vo=new ReplyVO();
		vo.setTitle("새로운 글을 넣습니다");
		vo.setContent("새로운 글을 넣습니다");
		vo.setWriter("user00");
		vo.setBno(10);
		vo.setReplyer("user00");
		vo.setReplytext("댓글을 추가합니다");
		logger.info("댓글은 ? "+vo.toString());
		dao.create(vo);
	}*/
	
/*	@Test
	public void testRead()throws Exception{
		logger.info(dao.read(2).toString());
	}*/
	
	@Test
	public void testUpdate()throws Exception{
		ReplyVO reply=new ReplyVO();
		reply.setRno(3);
		reply.setReplytext("댓글을 수정했습니다");
		dao.update(reply);
	}
	
/*	@Test
	public void testDelete()throws Exception{
		dao.delete(1);
	}*/
	
/*	@Test
	public void testListPage()throws Exception{
		int page=3;
		List<BoardVO> list=dao.listPage(page);
		for(BoardVO boardVO:list){
			logger.info(boardVO.getBno()+":"+boardVO.getTitle());
		}
	}*/
	
/*	@Test
	public void testCriteria()throws Exception{
		Criteria cri=new Criteria();
		cri.setPage(2);
		cri.setPerPageNum(10);
		
		List<BoardVO> list=dao.listCriteria(cri);
		for(BoardVO boardVO:list){
			logger.info(boardVO.getBno()+":"+boardVO.getTitle());
		}
	}*/
	
/*	@Test
	public void testURI()throws Exception{
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.path("/board/listCri")
				.queryParam("page",1)
				.queryParam("perPageNum",10)
				.build();
		logger.info("/board/listCri?page=1&perPageNum=10");
		logger.info(uriComponents.toString());
	}*/
	
/*	@Test
	public void testURI2()throws Exception{
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.path("/{module}/{page}")
				.queryParam("bno",12)
				.queryParam("perPageNum",20)
				.build();
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}*/
	
/*	@Test
	public void testDynamic1()throws Exception{
		SearchCriteria cri=new SearchCriteria();
		cri.setPage(1);
		cri.setKeyword("제목");
		cri.setSearchType("t");
		
		logger.info("==================");
		
		List<BoardVO> list=dao.listSearch(cri);
		
		for(BoardVO board:list) {
			logger.info(board.getBno()+" : "+board.getTitle());
		}
		
		logger.info("==================");
		logger.info("COUNT: "+dao.listSearchCount(cri));
	}*/
	
}
