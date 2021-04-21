package edu.spring.ex03.service;

import java.util.List;

import edu.spring.ex03.domain.BoardVO;
import edu.spring.ex03.pageutil.PageCriteria;

//���� dao�� ���̺��� �ϳ��� �����ϴµ�
//service�� �����������Ҷ� ����
public interface BoardService {
	public abstract int create(BoardVO vo);
	public abstract List<BoardVO> read(PageCriteria criteria);
	public abstract BoardVO read(int bno);
	public abstract int update(BoardVO vo);
	public abstract int delete(int bno);
	public abstract int getTotalNumsOfRecords();
	
	

}
