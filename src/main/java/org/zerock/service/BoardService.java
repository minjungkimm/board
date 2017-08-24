package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;

public interface BoardService {
/*������ ����-����Ͻ� ����(Service)
 * ����Ͻ� ���� = ��Ʈ�ѷ��� DAO ������ ������
 * �߰��� ����Ͻ� ������ �����ؼ� ���� �� ������ �� �þ
 * �׷����� ���� ������ �и��Ͽ� �����ϴ� ������?
 * 1.�� ������ ������ �ٸ� �κ��� ó���� �� �ִ� (����)������ ����
 * 2.�� ȸ�縶�� �ٸ� ����/��Ģ�� �����ͺ��̽��� �����ϰ� ó���� �� �ִ� (����)���濵�� �����ʿ�
 * 3.��Ʈ�ѷ��� ���� �ܺ� ȣ���� ���Ӱ���(Persistence)�� �������� ��Ȳ�� ������
 * 4.���� ��Ʈ�ѷ��� ���� ���Ӱ���(Persistence)�� �����ͺ��̽��� �̿��ϰ� �Ǹ�?
 * =>Ʈ������� ó���� ������ ó�� �� ��� ������ ��Ʈ�ѷ��� ���ߵ�
 * =>����Ͻ� ����(Service)�� ��Ʈ�ѷ��� �Ͽ��� ó���ؾ� �ϴ� ���� �о�ȭ 
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
