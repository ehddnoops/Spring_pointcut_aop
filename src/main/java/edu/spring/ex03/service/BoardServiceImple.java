package edu.spring.ex03.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex03.domain.BoardVO;
import edu.spring.ex03.pageutil.PageCriteria;
import edu.spring.ex03.persistence.BoardDAO;

@Service // @Component
// ������ �����ӿ�ũ���� ���� ����(Service/Business layer)��
// ���۳�Ʈ���� �˷��ִ� ������̼�
public class BoardServiceImple implements BoardService{
   private static final Logger LOGGER =
         LoggerFactory.getLogger(BoardServiceImple.class);

   @Autowired
   private BoardDAO dao;
   
   @Override
   public int create(BoardVO vo) {
      LOGGER.info("create() ȣ�� : vo = " + vo.toString());
      return dao.insert(vo);
   }

   @Override
   public List<BoardVO> read(PageCriteria criteria) {
      LOGGER.info("read() ȣ�� : PageCriteria = " + criteria);
      return dao.select(criteria);
   }

   @Override
   public BoardVO read(int bno) {
      LOGGER.info("read() ȣ�� : bno = " + bno);
      return dao.select(bno);
   }

   @Override
   public int update(BoardVO vo) {
      LOGGER.info("update() ȣ�� : vo = " + vo.toString());
      return dao.update(vo);
   }

   @Override
   public int delete(int bno) {
      LOGGER.info("delete() ȣ�� : bno = " + bno);
      return dao.delete(bno);
   }

   @Override
   public int getTotalNumsOfRecords() {
      LOGGER.info("getTotalNumsOfRecords() ȣ��");
      return dao.getTotalNumsOfRecords();
   }
}